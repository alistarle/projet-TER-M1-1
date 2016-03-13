import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    public static void main(String[] args) {


        if (args.length != 1) {
            System.err.println("Precisez le port");
            System.exit(1);
        }


        int portNumber = Integer.parseInt(args[0]);
        try (
                ServerSocket serverSocket = new ServerSocket(portNumber);
                Socket clientSocket = serverSocket.accept();
                PrintWriter out =
                        new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(clientSocket.getInputStream()));
        ) {
            String envoie, reponse;

            // Initiate conversation with client
            CustomProtocol cp = new CustomProtocol();
            reponse = cp.processInput(null);
            out.println(reponse);

            while ((envoie = in.readLine()) != null) {
                reponse = cp.processInput(envoie);
                out.println(reponse);
                if (reponse.equals("Bye."))
                    break;
            }
        }
        catch (Exception e){

        }
    }
}
