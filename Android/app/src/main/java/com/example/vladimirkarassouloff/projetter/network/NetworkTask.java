package com.example.vladimirkarassouloff.projetter.network;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.Toast;

import com.example.vladimirkarassouloff.projetter.ui.AlgoActivity;
import com.example.vladimirkarassouloff.projetter.ui.MyApp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.os.Handler;
/**
 * Created by Vladimir on 12/03/2016.
 */
public class NetworkTask extends AsyncTask<Void,String,String> {

    private NetworkProtocoleClient npc;
    private NetworkInfo ni;


    public static boolean TASK_ALREADY_CONNECTED;
    public static int TIMEOUT = 5000;
    public static boolean connected;

    private BroadcastReceiver onNotice;

    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    private static int RETRY_CONNECT = 3;
    private static long RETRY_SLEEP_TIME = 5000;
    private int currentTry;

    private static String MESSAGE;

    public NetworkTask(){
        this.currentTry = 0;
        this.connected = false;
        this.npc = new NetworkProtocoleClient();
        onNotice = new BroadcastReceiver() {

            @Override
            public void onReceive(Context context, Intent intent) {
                if(intent.getAction().equals("send")){
                    out.println(intent.getStringExtra("message"));
                }
            }
        };
        LocalBroadcastManager.getInstance(MyApp.context).registerReceiver(onNotice, new IntentFilter("send"));
    }



    @Override
    protected String doInBackground(Void... params) {
        Log.wtf("Task", "doInBackground");

        Pattern patternFailed = Pattern.compile("compilation_failed");
        Matcher matcherFailed;// = patternFailed.matcher("Hugo Etiévant");


        if(TASK_ALREADY_CONNECTED){
            Log.wtf("Network","Task already running !");
            return "";
        }
        TASK_ALREADY_CONNECTED = true;
        this.connected = false;
        Handler handler=  new Handler(MyApp.context.getMainLooper());


        InetSocketAddress address;
        String fromServer = "", fromUser = "SALUT MDR";

        if(this.ni == null){
            Log.wtf("NETWORK","Ni null");
            return null;
        }
        else if(this.ni.getIp() == null){
            Log.wtf("NETWORK","Ip null");
            return null;
        }

        this.currentTry = 0;
        this.connected = false;
        handler.post(new Runnable() {
            public void run() {
                Toast.makeText(MyApp.context, "Connexion a " + ni.getIp() + ":" + ni.getPort(), Toast.LENGTH_SHORT).show();
            }
        });


        for (currentTry = 0; currentTry < RETRY_CONNECT; currentTry++) {
            try {
                address = new InetSocketAddress(this.ni.getIp(), this.ni.getPort());
                socket = new Socket();

                socket.connect(address, TIMEOUT);
                out = new PrintWriter(socket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                connected = true;

                /////connected
                Intent intent = new Intent("connected");
                LocalBroadcastManager.getInstance(MyApp.context).sendBroadcast(intent);
                handler.post(new Runnable() {
                    public void run() {
                        Toast.makeText(MyApp.context, "Connexion réussie a " + ni.getIp() + ":" + ni.getPort(), Toast.LENGTH_SHORT).show();
                    }
                });

                try {
                    //Scanner stdIn = new Scanner(System.in);
                    while ((fromServer = in.readLine()) != null) {
                        Log.wtf("Network", "from Server: " + fromServer);
                        matcherFailed = patternFailed.matcher(fromServer);
                        MESSAGE = fromServer;
                        if (fromServer.equals("disconnect")) {
                            Log.wtf("message","disconnect");

                            break;
                        }
                        else if(fromServer.equals("compilation_success")){
                            handler.post(new Runnable() {
                                public void run() {
                                    Toast.makeText(MyApp.context, "Compilation réussie ! ", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                        else if(matcherFailed.find()){
                            handler.post(new Runnable() {
                                public void run() {
                                    Toast.makeText(MyApp.context, "Echec de compilation : "+MESSAGE, Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                        //fromUser = stdIn.next();
                        /*if (fromUser != null) {
                            Log.wtf("Network", "sending: " + fromUser);
                            out.println(fromUser);
                        }*/
                    }
                }
                catch (Exception e2){
                    Log.wtf("Network","Exception 2 Network");
                }

                break;
            } catch (Exception e) {
                if (currentTry < RETRY_CONNECT) {

                    handler.post(new Runnable() {
                        public void run() {
                            Toast.makeText(MyApp.context, "Impossible de se connecter a " + ni.getIp() + ":" +
                                    ni.getPort() + ", essaie " + (currentTry+1) + "/" + RETRY_CONNECT + ", nouvelle tentative dans " + RETRY_SLEEP_TIME / 1000 + "s", Toast.LENGTH_SHORT).show();
                        }
                    });

                    try {
                        Thread.sleep(RETRY_SLEEP_TIME);
                    }
                    catch (Exception e1){
                        Log.wtf("ThreadSleep","Exception");
                    }
                }
            }



        }

        if (! connected ) {
            Intent intent = new Intent("disconnected");
            LocalBroadcastManager.getInstance(MyApp.context).sendBroadcast(intent);
            handler.post(new Runnable() {
                public void run() {
                    Toast.makeText(MyApp.context, "Impossible de se connecter", Toast.LENGTH_SHORT).show();
                }
            });
        }
        TASK_ALREADY_CONNECTED = false;
        return null;
    }






    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
        Log.wtf("Task", "onProgressUpdate");

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Log.wtf("Task", "onPreExecute");


    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        Log.wtf("Task", "onPostExecute");
        Intent intent = new Intent("disconnected");
        LocalBroadcastManager.getInstance(MyApp.context).sendBroadcast(intent);
    }

    public NetworkInfo getNi() {
        return ni;
    }

    public void setNi(NetworkInfo ni) {
        this.ni = ni;
    }
}
