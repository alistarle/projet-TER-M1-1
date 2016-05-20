package com.example.vladimirkarassouloff.projetter.customlistener;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.EditText;

import com.example.vladimirkarassouloff.projetter.network.NetworkInfo;
import com.example.vladimirkarassouloff.projetter.ui.AlgoActivity;

/**
 * Created by Vladimir on 11/03/2016.
 */
public class ValidationDialogConnection extends ValidationDialogListener {

    private EditText ipEditText, portEditText;
    private AlgoActivity algoActivity;

    public ValidationDialogConnection(Dialog dialog, View promptsView, EditText ipEditText, EditText portEditText,AlgoActivity algoActivity) {
        super(dialog, promptsView);
        this.ipEditText = ipEditText;
        this.portEditText = portEditText;
        this.algoActivity = algoActivity;
    }


    @Override
    public void checkValid() throws Exception {
        String[] ip = ipEditText.getText().toString().split("\\.");
        if(ip.length != 4){
            throw new Exception("Ip incomplete");
        }
        for(String s : ip){
            try{
                Integer.parseInt(s);
            }
            catch (Exception exc){
                throw new Exception("Ip incorrecte");
            }
        }
        try{
            Integer.parseInt(portEditText.getText().toString());
        }
        catch (Exception exc){
            throw new Exception("Port incorrect");
        }

    }

    @Override
    public void onValid() {
        SharedPreferences settings = promptsView.getContext().getSharedPreferences("DEFAULT", 0);
        SharedPreferences.Editor editor = settings.edit();

        editor.putString("DEFAULT_IP", ipEditText.getText().toString());
        editor.putInt("DEFAULT_PORT", Integer.parseInt(portEditText.getText().toString()));

        editor.apply();
        algoActivity.doConnect(new NetworkInfo(this.ipEditText.getText().toString(),Integer.parseInt(this.portEditText.getText().toString())));

    }
}
