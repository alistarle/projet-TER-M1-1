package com.example.vladimirkarassouloff.projetter.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.SystemClock;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ViewAnimator;

import com.example.vladimirkarassouloff.projetter.R;
import com.example.vladimirkarassouloff.projetter.customlistener.ValidationDialogConnection;
import com.example.vladimirkarassouloff.projetter.network.NetworkInfo;
import com.example.vladimirkarassouloff.projetter.network.NetworkTask;
import com.example.vladimirkarassouloff.projetter.ui.myviews.AlgoView;
import com.example.vladimirkarassouloff.projetter.ui.myviews.prompt.PromptConnectionView;
import com.example.vladimirkarassouloff.projetter.ui.myviews.scrolldraggable.ElementsView;
import com.example.vladimirkarassouloff.projetter.utils.Debug;
import com.example.vladimirkarassouloff.projetter.utils.DefaultValues;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;


public class AlgoActivity extends AppCompatActivity {


    //connecte a un robot
    public enum ConnectionState {
        connected,
        connecting,
        disconnected
    }

    private ConnectionState state = ConnectionState.disconnected;

    private BroadcastReceiver onNotice;

    ////////////////UI
    private MenuItem menuConnect;
    private MenuItem menuExecuteCode;
    private MenuItem menuDisconnect;

    private TextView test;
    private TextView drag;
    private LinearLayout mainLayout;

    //scroll droite
    private ElementsView elementsScroll;

    //scroll gauche
    private AlgoView algoScroll;
    private Button buttonPrev, buttonNext;
    private Animation slide_in_left, slide_out_right;
    private ViewAnimator viewAnimator;


    private DrawerLayout menuLayout; //Layout Principal
    private ListView menuElementsList; //Menu
    private ActionBarDrawerToggle menuToggle; //Gère l'ouverture et la fermeture du menu


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_algo);


        //Gestion des main scoll
        mainLayout = (LinearLayout) findViewById(R.id.mainLayout);
        elementsScroll = (ElementsView) findViewById(R.id.elementsScroll);
        algoScroll = (AlgoView) findViewById(R.id.algoScroll);


        ///////////Gestion viewanimator/////////////
        viewAnimator = (ViewAnimator) findViewById(R.id.viewanimator);
        buttonPrev = (Button) findViewById(R.id.prev);
        buttonNext = (Button) findViewById(R.id.next);

        slide_in_left = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left);
        slide_out_right = AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right);

        viewAnimator.setInAnimation(slide_in_left);
        viewAnimator.setOutAnimation(slide_out_right);

        buttonPrev.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                viewAnimator.showPrevious();
            }
        });

        buttonNext.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                viewAnimator.showNext();
            }
        });
        /////////////////////////////////////////////


        if (Debug.DEBUG_NETWORK) {
            SharedPreferences settings = this.getBaseContext().getSharedPreferences("DEFAULT", 0);
            String ip = settings.getString("DEFAULT_IP", DefaultValues.DEFAULT_IP);
            int port = settings.getInt("DEFAULT_PORT", DefaultValues.DEFAULT_PORT);
            if (ip.equals(DefaultValues.DEFAULT_IP) && port == DefaultValues.DEFAULT_PORT) {
                showConnexionPrompt();
            } else {
                this.doConnect(new NetworkInfo(ip, port));
            }
        }


        onNotice = new BroadcastReceiver() {

            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getAction().equals("connected")) {
                    setState(ConnectionState.connected);
                } else if (intent.getAction().equals("disconnected")) {
                    setState(ConnectionState.disconnected);
                }
            }
        };
        LocalBroadcastManager.getInstance(this).registerReceiver(onNotice, new IntentFilter("connected"));
        LocalBroadcastManager.getInstance(this).registerReceiver(onNotice, new IntentFilter("disconnected"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_algo, menu);
        this.menuConnect = (MenuItem) menu.findItem(R.id.action_connect);
        this.menuDisconnect = (MenuItem) menu.findItem(R.id.action_disconnect);
        this.menuExecuteCode = (MenuItem) menu.findItem(R.id.action_execute);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_connect) {
            showConnexionPrompt();
            return true;
        } else if (id == R.id.action_disconnect) {
            Intent intent = new Intent("send");
            intent.putExtra("message", "disconnect");
            LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
            return true;
        } else if (id == R.id.action_execute) {
            Intent intent = new Intent("send");
            intent.putExtra("message", algoScroll.getAlgorithme());
            LocalBroadcastManager.getInstance(this).sendBroadcast(intent);

        } else if (id == R.id.action_show_name && viewAnimator.getDisplayedChild() != 0) {
            viewAnimator.setDisplayedChild(0);
            return true;
        } else if (id == R.id.action_show_stuct && viewAnimator.getDisplayedChild() != 1) {
            viewAnimator.setDisplayedChild(1);
            return true;
        } else if (id == R.id.action_show_operator && viewAnimator.getDisplayedChild() != 2) {
            viewAnimator.setDisplayedChild(2);
            return true;
        }


        return super.onOptionsItemSelected(item);
    }


    public void showConnexionPrompt() {
        LayoutInflater li = LayoutInflater.from(this);
        View promptsView = li.inflate(R.layout.promptconnection, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        // set prompts.xml to alertdialog builder
        alertDialogBuilder.setView(promptsView);
        final EditText ip = (EditText) promptsView.findViewById(R.id.editTextIp);
        final EditText port = (EditText) promptsView.findViewById(R.id.editTextPort);
        final PromptConnectionView ptv = (PromptConnectionView) promptsView.findViewById(R.id.promptconnectionview);
        // set dialog message


        alertDialogBuilder.setCancelable(false).setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

        Button theButton = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
        theButton.setOnClickListener(new ValidationDialogConnection(alertDialog, promptsView, ip, port, this));
    }


    public void doConnect(NetworkInfo ni) {
        Log.wtf("Task", "new task");
        state = ConnectionState.connecting;
        NetworkTask nt = new NetworkTask();
        nt.setNi(ni);
        nt.execute();

    }


    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        refreshUIConnectionState();
        return true;
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

    }

    private void saveAlgo(int slot) throws IOException {
        // Write to disk with FileOutputStream
        FileOutputStream f_out = new FileOutputStream("slot" + slot + ".data");
        // Write object with ObjectOutputStream
        ObjectOutputStream obj_out = new ObjectOutputStream(f_out);

        ArrayList<View> list = new ArrayList<>();
        for (int i = 0 ; i< algoScroll.getLl().getChildCount();i++) {
            list.add(algoScroll.getLl().getChildAt(i));
        }
        // Write object out to disk
        obj_out.writeObject(list);
    }


    private void loadAlgo(int slot) throws IOException, ClassNotFoundException {
        // Read from disk using FileInputStream
        FileInputStream f_in = new
                FileInputStream("slot" + slot + ".data");

        // Read object using ObjectInputStream
        ObjectInputStream obj_in = new ObjectInputStream(f_in);

        // Read an object
        Object obj = obj_in.readObject();

        //if (obj instanceof ArrayList<View>) {
            // Cast object to a Vector
            obj = (ArrayList<View>) obj;
            algoScroll.getLl().removeAllViews();
            for(int i = 0 ; i<((ArrayList<View>) obj).size() ; i++ ){
                algoScroll.getLl().addView(((ArrayList<View>) obj).get(i));
            }
            // Do something with vector....
        //}
    }


    public void actionSave(View view) {
        int slot = 1;
        try {
            saveAlgo(slot);
        } catch (IOException e) {
            System.out.println("failed to save file to slot " + slot);
        }
    }

    public void actionLoad(View view) {
        int slot = 1;
        try {
            loadAlgo(slot);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("failed to load file from slot " + slot);
        }
    }
        ///////////////////Connexion stuff

    public void setState(ConnectionState state) {
        this.state = state;
        refreshUIConnectionState();
    }

    public void refreshUIConnectionState() {
        if (this.state == ConnectionState.connected) {
            this.onConnected();
        } else if (this.state == ConnectionState.disconnected) {
            this.onDisconnected();
        } else if (this.state == ConnectionState.connecting) {
            this.onConnecting();
        }
    }

    private void onConnected() {
        if (menuConnect != null)
            menuConnect.setVisible(false);
        if (menuExecuteCode != null)
            menuExecuteCode.setVisible(true);
        if (menuDisconnect != null)
            menuDisconnect.setVisible(true);
    }

    private void onDisconnected() {
        if (menuConnect != null) {
            menuConnect.setVisible(true);
            menuConnect.setEnabled(true);
        }
        if (menuExecuteCode != null)
            menuExecuteCode.setVisible(false);
        if (menuDisconnect != null)
            menuDisconnect.setVisible(false);
    }

    private void onConnecting() {
        if (menuConnect != null)
            menuConnect.setEnabled(false);
        if (menuExecuteCode != null)
            menuExecuteCode.setVisible(false);
        if (menuDisconnect != null)
            menuDisconnect.setVisible(false);
    }
    //////////////////////////////////
}
