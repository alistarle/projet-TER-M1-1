package receiver;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.*;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {


    public static boolean shouldContinue = true;

    public static void main(String[] args) throws Exception{


        if (args.length != 1) {
            System.err.println("Precisez le port");
            System.exit(1);
        }


        int portNumber = Integer.parseInt(args[0]);
        while(shouldContinue) {
            System.out.println("On cherche un client");
            try (
                    ServerSocket serverSocket = new ServerSocket(portNumber);
                    Socket clientSocket = serverSocket.accept();
                    PrintWriter out =
                            new PrintWriter(clientSocket.getOutputStream(), true);
                    BufferedReader in = new BufferedReader(
                            new InputStreamReader(clientSocket.getInputStream()));
            ) {
                System.out.println("On a trouve un client");
                String envoie, reponse;

                // Initiate conversation with client
                receiver.CustomProtocol cp = new receiver.CustomProtocol();
                reponse = cp.processInput(null);
                out.println(reponse);

                while ((envoie = in.readLine()) != null) {
                    reponse = cp.processInput(envoie);

                    if (reponse.equals("disconnect")) {
                        out.println(reponse);
                        break;
                    }
                    else if(reponse.equals("execute_code")){
                        try {
                            out.println(Compiler.compileCode(envoie));
                            System.out.println("Compilation réussie !");
                            out.println("compilation_success");
                        }catch (Exception e){
                            System.out.println("Erreur de compilation !!!");
                            out.println("compilation_failed : "+e.getMessage());
                        }
                    }

                }
            }
            catch(BindException e2){
                throw new Exception("Port deja utilise");
            }
            catch (Exception e) {
                System.out.println("Exeception");
            }
        }
        System.out.println("Fin de l'execution");
    }
}
