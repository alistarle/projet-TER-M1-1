package com.example.vladimirkarassouloff.projetter.network;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.Toast;

import com.example.vladimirkarassouloff.projetter.ui.AlgoActivity;
import com.example.vladimirkarassouloff.projetter.ui.MyApp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import android.os.Handler;
/**
 * Created by Vladimir on 12/03/2016.
 */
public class NetworkTask extends AsyncTask<Void,String,String> {
    private NetworkInfo ni;

    public boolean connected;


    private static int RETRY_CONNECT = 5;
    private static long RETRY_SLEEP_TIME = 5000;
    private int currentTry;

    public NetworkTask(NetworkInfo ni) {
        this.ni = ni;
        this.currentTry = 0;
        this.connected = false;
    }
    public NetworkTask(){
        this.currentTry = 0;
        this.connected = false;
    }



    @Override
    protected String doInBackground(Void... params) {
        Log.wtf("Task", "doInBackground");
        this.connected = false;
        Handler handler=  new Handler(MyApp.context.getMainLooper());


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
                Socket socket = new Socket(this.ni.getIp(), this.ni.getPort());
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                connected = true;
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

        if (connected) {
            Intent intent = new Intent("connected");
            LocalBroadcastManager.getInstance(MyApp.context).sendBroadcast(intent);
            handler.post(new Runnable() {
                public void run() {
                    Toast.makeText(MyApp.context, "Connexion réussie a " + ni.getIp() + ":" + ni.getPort(), Toast.LENGTH_SHORT).show();
                }
            });
        }
        else{
            Intent intent = new Intent("disconnected");
            LocalBroadcastManager.getInstance(MyApp.context).sendBroadcast(intent);
            handler.post(new Runnable() {
                public void run() {
                    Toast.makeText(MyApp.context, "Impossible de se connecter", Toast.LENGTH_SHORT).show();
                }
            });
        }
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
