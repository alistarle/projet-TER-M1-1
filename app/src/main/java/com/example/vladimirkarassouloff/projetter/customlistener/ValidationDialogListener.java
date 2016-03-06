package com.example.vladimirkarassouloff.projetter.customlistener;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.Toast;

import com.example.vladimirkarassouloff.projetter.myelementsproduction.Production;

/**
 * Created by Vladimir on 16/02/2016.
 */
public abstract class ValidationDialogListener implements View.OnClickListener {

    protected final Dialog dialog;
    protected final View promptsView;

    public ValidationDialogListener(Dialog dialog,View promptsView) {
        this.dialog = dialog;
        this.promptsView = promptsView;
    }
    @Override
    public void onClick(View v) {
        // put your code here
        try{
            checkValid();
            onValid();
            dialog.dismiss();
        }
        catch (Exception e){
            AlertDialog.Builder builder = new AlertDialog.Builder(promptsView.getContext());
            builder.setMessage(e.getMessage());
            builder.setCancelable(true);
            AlertDialog alertDialog = builder.create();
            alertDialog.setTitle("Attention");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
        }
    }

    public abstract void checkValid() throws Exception;
    public abstract void onValid();


}