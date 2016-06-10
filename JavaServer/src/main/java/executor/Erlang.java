package executor;

import com.ericsson.otp.erlang.*;
import compilator.ast.Reserver;
import enums.Direction;

import java.io.IOException;
import java.util.ArrayList;


public class Erlang {

    private static final String NODE = "control";
    private static final String COOKIE = "ROBOT";

    private static OtpConnection conn;
    public OtpErlangObject pid;
    public OtpErlangObject received;

    public static void main(String[] args) throws Exception {
        Erlang erl = new Erlang();
        erl.led(true);

        while(Integer.valueOf(erl.odometres().elementAt(1).toString()) < 3500)
        {
            if(Integer.valueOf(erl.infrarouges().elementAt(1).toString()) == 0) {
                erl.moteurs(Direction.FORWARD);
            } else {
                erl.servo(0);
                if(erl.ultrason().longValue() >= 20) {
                    erl.moteurs(Direction.RIGHT);
                } else {
                    erl.servo(180);
                    if(erl.ultrason().longValue() >= 20) {
                        erl.moteurs(Direction.LEFT);
                    } else {
                        erl.moteurs(Direction.BACK);
                    }
                }
            }
            erl.servo(90);
        }

        erl.led(false);
        erl.disconnect();
    }

    public Erlang() {
        connect();
    }

    public int execute(Reserver.Function func, ArrayList<Integer> args) throws OtpErlangExit, IOException, OtpAuthException, InterruptedException {
        System.out.println("On exec une function erlang : "+ func.name());
        switch(func)
        {
            case ALLUMERLED:
                this.led(true);
                return 0;
            case ETEINDRELED:
                this.led(false);
                return 0;
            case INFRAROUGECENTRE:
                return Integer.valueOf(this.infrarouges().elementAt(1).toString());
            case INFRAROUGEDROIT:
                return Integer.valueOf(this.infrarouges().elementAt(2).toString());
            case INFRAROUGEGAUCHE:
                return Integer.valueOf(this.infrarouges().elementAt(0).toString());
            case MOTEUR:
                this.moteurs(Direction.values()[args.get(0)]);
                return 0;
            case ODOMETRE:
                return (Integer.valueOf(this.odometres().elementAt(0).toString()) + Integer.valueOf(this.odometres().elementAt(1).toString()))/2;
            case TOURNERTETE:
                this.servo(args.get(0));
                return 0;
            case ULTRASON:
                return Integer.valueOf(this.ultrason().toString());
        }
        return 0;
    }

    public void moteurs(Direction dir) throws IOException, OtpErlangExit, OtpAuthException, InterruptedException {
        conn.sendRPC("robocom","moteurs",new OtpErlangList(new OtpErlangObject[]{pid, new OtpErlangLong(dir.getLeft()), new OtpErlangLong(dir.getRight())}));
        received = conn.receiveRPC();

        Thread.sleep(1000);

        conn.sendRPC("robocom","moteurs",new OtpErlangList(new OtpErlangObject[]{pid, new OtpErlangLong(0), new OtpErlangLong(0)}));
        received = conn.receiveRPC();
    }

    //Angle 0 => Right, 90 => Front, 180 => Left
    public void servo(int angle) throws OtpErlangExit, IOException, OtpAuthException, InterruptedException {
        conn.sendRPC("robocom","servo",new OtpErlangList(new OtpErlangObject[]{pid, new OtpErlangLong(angle)}));
        received = conn.receiveRPC();

        Thread.sleep(1000);
    }

    public void led(boolean light) throws IOException, OtpErlangExit, OtpAuthException {
        conn.sendRPC("robocom","led",new OtpErlangList(new OtpErlangObject[]{pid, new OtpErlangLong((light) ? 1 : 0)}));
        received = conn.receiveRPC();
    }

    public OtpErlangTuple odometres() throws OtpErlangExit, IOException, OtpAuthException {
        conn.sendRPC("robocom","odometres",new OtpErlangList(pid));
        received = conn.receiveRPC();
        System.out.println(received);
        return (OtpErlangTuple) received;
    }

    public OtpErlangTuple infrarouges() throws OtpErlangExit, IOException, OtpAuthException {
        conn.sendRPC("robocom","infrarouge",new OtpErlangList(pid));
        received = conn.receiveRPC();
        System.out.println(received);
        return (OtpErlangTuple) received;
    }

    public OtpErlangLong ultrason() throws OtpErlangExit, IOException, OtpAuthException {
        conn.sendRPC("robocom","ultrason",new OtpErlangList(pid));
        received = conn.receiveRPC();
        System.out.println(received);
        return (OtpErlangLong) received;
    }

    private void connect() {
        System.out.print("Please wait, connecting to "+NODE+"....\n");

        String javaClient ="java";
        try {
            OtpSelf self = new OtpSelf(javaClient, COOKIE.trim());
            OtpPeer other = new OtpPeer(NODE.trim());
            conn = self.connect(other);
            conn.sendRPC("robocom","spawn_serveur",new OtpErlangList());
            pid = conn.receiveRPC();
            System.out.println("Connection Established with "+NODE+" and PID Robot :"+pid+"\n");
        }
        catch (Exception exp) {
            System.out.println("connection error is :" + exp.toString());
            exp.printStackTrace();
        }

    }

    public void disconnect() throws IOException {
        System.out.println("Disconnecting....");
        conn.sendRPC("robocom","reset",new OtpErlangList(pid));
        if(conn != null){
            conn.close();
        }
        System.out.println("Successfuly Disconnected");
    }

}