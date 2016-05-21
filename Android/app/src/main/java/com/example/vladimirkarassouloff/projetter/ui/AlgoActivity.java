package com.example.vladimirkarassouloff.projetter.ui;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewAnimator;

import com.example.vladimirkarassouloff.projetter.R;
import com.example.vladimirkarassouloff.projetter.action.Action;
import com.example.vladimirkarassouloff.projetter.customlistener.ValidationDialogConnection;
import com.example.vladimirkarassouloff.projetter.myelementsstring.ElementString;
import com.example.vladimirkarassouloff.projetter.network.NetworkInfo;
import com.example.vladimirkarassouloff.projetter.network.NetworkTask;
import com.example.vladimirkarassouloff.projetter.ui.myelementsproduction.Production;
import com.example.vladimirkarassouloff.projetter.ui.myviews.mypopups.PopupLoad;
import com.example.vladimirkarassouloff.projetter.ui.myviews.AlgoView;
import com.example.vladimirkarassouloff.projetter.ui.myviews.prompt.PromptConnectionView;
import com.example.vladimirkarassouloff.projetter.ui.myviews.scrolldraggable.ElementsView;
import com.example.vladimirkarassouloff.projetter.ui.myviews.scrolldraggable.NameView;
import com.example.vladimirkarassouloff.projetter.utils.Debug;
import com.example.vladimirkarassouloff.projetter.utils.DefaultValues;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;


public class AlgoActivity extends AppCompatActivity {


    public static DisplayMetrics metrics;

    private Button redoActionButton;
    private Button undoActionButton;
    private Stack<com.example.vladimirkarassouloff.projetter.action.Action> redoStack;
    private Stack<com.example.vladimirkarassouloff.projetter.action.Action> undoStack;
    public static List<Action> ACTION_TO_CONSUME = new ArrayList<>();


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
    private MenuItem menuSave;
    private MenuItem menuLoad;

    private TextView test;
    private TextView drag;
    private LinearLayout mainLayout;

    //scroll droite
    private ElementsView elementsScroll;
    private NameView nameView;


    //scroll gauche
    private AlgoView algoScroll;
    private Button buttonPrev, buttonNext;
    private Animation slide_in_left, slide_out_right;
    private ViewAnimator viewAnimator;


    private DrawerLayout menuLayout; //Layout Principal
    private ListView menuElementsList; //Menu
    private ActionBarDrawerToggle menuToggle; //Gère l'ouverture et la fermeture du menu

    @Override
    public void onDestroy() {
        actionSave(0);
        super.onDestroy();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_algo);



        //DO UNDO REDO
        redoStack = new Stack<Action>();
        undoStack = new Stack<Action>();
        redoActionButton = (Button) findViewById(R.id.redo);
        undoActionButton = (Button) findViewById(R.id.undo);
        redoActionButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                redoAction();
            }
        });
        undoActionButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                undoAction();
            }
        });

        //Gestion des main scoll
        mainLayout = (LinearLayout) findViewById(R.id.mainLayout);
        elementsScroll = (ElementsView) findViewById(R.id.elementsScroll);
        nameView = (NameView) findViewById(R.id.namesScroll);
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
                else if(intent.getAction().equals("doAction")){
                    consumeActions();
                    algoScroll.autoIndent();
                }
                else if(intent.getAction().equals("removeLastAction")){
                    removeLastAction();
                    algoScroll.autoIndent();
                }
                else if(intent.getAction().equals("autoIndent")){
                    algoScroll.autoIndent();
                }
                else if(intent.getAction().equals("addProductions")){
                    //LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                    int line = intent.getIntExtra("line",0);
                    int i = 0;
                    List<ElementString> list = (ArrayList<ElementString>) intent.getSerializableExtra("elements");
                    for(ElementString es : list){
                        Production p = new Production(algoScroll.getLl().getContext(),es);
                        addProduction(p,line+i);
                        i++;
                    }
                    algoScroll.autoIndent();
                }
                else if(intent.getAction().equals("removeProductions")){
                    int line = intent.getIntExtra("line",0);
                    List<ElementString> list = (ArrayList<ElementString>) intent.getSerializableExtra("elements");
                    for(ElementString es : list){
                        Production p = new Production(algoScroll.getLl().getContext(),es);
                        removeProduction(p,line);
                    }
                    algoScroll.autoIndent();
                }
                else if(intent.getAction().equals("moveProduction")){
                    int lineFrom = intent.getIntExtra("from",0);
                    int lineTo = intent.getIntExtra("to",0);
                    if(algoScroll.getLl().getChildAt(lineFrom) != null){
                        View v = algoScroll.getLl().getChildAt(lineFrom);
                        algoScroll.getLl().removeView(v);
                        algoScroll.getLl().addView(v,lineTo);
                        algoScroll.autoIndent();
                    }
                }
            }
        };


        LocalBroadcastManager.getInstance(this).registerReceiver(onNotice, new IntentFilter("connected"));
        LocalBroadcastManager.getInstance(this).registerReceiver(onNotice, new IntentFilter("disconnected"));
        LocalBroadcastManager.getInstance(this).registerReceiver(onNotice, new IntentFilter("doAction"));
        LocalBroadcastManager.getInstance(this).registerReceiver(onNotice, new IntentFilter("removeLastAction"));
        LocalBroadcastManager.getInstance(this).registerReceiver(onNotice, new IntentFilter("autoIndent"));
        LocalBroadcastManager.getInstance(this).registerReceiver(onNotice, new IntentFilter("addProductions"));
        LocalBroadcastManager.getInstance(this).registerReceiver(onNotice, new IntentFilter("removeProductions"));
        LocalBroadcastManager.getInstance(this).registerReceiver(onNotice, new IntentFilter("moveProduction"));

        actionLoad(0);

    }

    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }


    public void addProduction(Production prod,int line){
        algoScroll.getLl().addView(prod,line);
    }

    public void removeProduction(Production prod,int line){
        algoScroll.getLl().removeView(algoScroll.getLl().getChildAt(line));
    }



    public void removeLastAction(){
        undoAction();
        if(redoStack.size()>0){
            redoStack.pop();
        }
    }

    public void consumeActions(){
        Iterator<Action> it = ACTION_TO_CONSUME.iterator();
        while(it.hasNext()){
            Action a = it.next();
            doAction(a);
            it.remove();
        }
        Log.wtf("message","on a consumme les actions");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_algo, menu);
        this.menuConnect = (MenuItem) menu.findItem(R.id.action_connect);
        this.menuDisconnect = (MenuItem) menu.findItem(R.id.action_disconnect);
        this.menuExecuteCode = (MenuItem) menu.findItem(R.id.action_execute);
        this.menuSave = (MenuItem) menu.findItem(R.id.action_save);
        this.menuLoad = (MenuItem) menu.findItem(R.id.action_load);


        return true;
    }


    private void doAction(Action a){
        a.doAction();
        undoStack.push(a);
        redoStack.clear();
        Log.wtf("message","Action !");
    }

    private void redoAction(){
        if(redoStack.size() > 0) {
            Action a = redoStack.pop();
            if (a != null) {
                a.doAction();
                undoStack.push(a);
            }
        }
        Log.wtf("message","redo! !");
    }

    private void undoAction(){
        if(undoStack.size() > 0) {
            Action a = undoStack.pop();
            if (a != null) {
                a.undoAction();
                redoStack.push(a);
            }
        }
        Log.wtf("message","undo !");
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
        }else if(id == R.id.action_save){
            showSavePrompt();
        }else if(id == R.id.action_load){
            showLoadPrompt();
        }


        return super.onOptionsItemSelected(item);
    }


    public void showSavePrompt() {
        LayoutInflater li = LayoutInflater.from(this);
        View promptsView = li.inflate(R.layout.popup_save, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        // set prompts.xml to alertdialog builder
        alertDialogBuilder.setView(promptsView);

        final RadioButton radio1 = (RadioButton)promptsView.findViewById(R.id.saveSlot1);
        final RadioButton radio2 = (RadioButton)promptsView.findViewById(R.id.saveSlot2);
        final RadioButton radio3 = (RadioButton)promptsView.findViewById(R.id.saveSlot3);

        alertDialogBuilder.setCancelable(false).setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        if(radio1.isChecked()){
                            actionSave(1);
                        }else if(radio2.isChecked()){
                            actionSave(2);
                        }else if(radio3.isChecked()){
                            actionSave(3);
                        }
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

    }

    public void showLoadPrompt(){

        LayoutInflater li = LayoutInflater.from(this);
        View promptsView = li.inflate(R.layout.popup_load, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        // set prompts.xml to alertdialog builder
        alertDialogBuilder.setView(promptsView);

        final RadioButton radio1 = (RadioButton)promptsView.findViewById(R.id.saveSlot1);
        final RadioButton radio2 = (RadioButton)promptsView.findViewById(R.id.saveSlot2);
        final RadioButton radio3 = (RadioButton)promptsView.findViewById(R.id.saveSlot3);

        alertDialogBuilder.setCancelable(false).setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if(radio1.isChecked()){
                            actionLoad(1);
                        }else if(radio2.isChecked()){
                            actionLoad(2);
                        }else if(radio3.isChecked()){
                            actionLoad(3);
                        }
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


    ////////////////////Save/Export/Load STUFF
    private void exportAlgo(String name) throws IOException {
        // Write to disk with FileOutputStream
        FileOutputStream f_out = new FileOutputStream("/sdcard/" + name + ".robotaf");
        // Write object with ObjectOutputStream
        ObjectOutputStream obj_out = new ObjectOutputStream(f_out);
        // Write object out to disk
        System.out.println(algoScroll.getAlgorithme());
        obj_out.writeObject(algoScroll.getAlgorithme());
    }


    public void actionExport(View view) {
        int slot = 1;
        try {
            exportAlgo("test");
        } catch (IOException e) {
            System.out.println("failed to export in file "+ slot);
            e.printStackTrace();
        }
    }


    private void actionSave(int slot){
        try {
            saveAlgo(slot);
        } catch (IOException e) {
            System.out.println("failed to export in file "+ slot);
            e.printStackTrace();
        }
    }
    private void saveAlgo(int slot) throws IOException {
        FileOutputStream f_out = new FileOutputStream("/sdcard/slot" + slot + ".data");
        ObjectOutputStream obj_out = new ObjectOutputStream(f_out);

        HashMap<String,ElementString> list = new HashMap<>();
        for (int i = 0 ; i< algoScroll.getLl().getChildCount();i++) {
            Object element = algoScroll.getLl().getChildAt(i);
                if(element instanceof Production){
                    list.put("ALGO",((Production) element).getBasicElement());
                }
        }
        obj_out.writeObject(list);
    }


    private void loadAlgo(int slot) throws IOException, ClassNotFoundException {
        FileInputStream f_in = new
                FileInputStream("/sdcard/slot" + slot + ".data");
        ObjectInputStream obj_in = new ObjectInputStream(f_in);
        Object obj = obj_in.readObject();
        if(obj instanceof ArrayList){
            ArrayList<ElementString> list = ((ArrayList<ElementString>)obj);
            algoScroll.getLl().removeAllViews();
            for(int i = 0 ; i < list.size() ; i ++){
                algoScroll.getLl().addView(new Production(this,list.get(i)));
            }
            algoScroll.autoIndent();

        }

    }
    public void actionSave(View view) {
        int slot = 1;
        try {
            saveAlgo(slot);
        } catch (IOException e) {
            System.out.println("failed to save file to slot " + slot);
        }
    }

    public void actionLoad(int slot) {
        try {
            loadAlgo(slot);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("failed to load file from slot " + slot);
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

    ////////////////////////////////

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
