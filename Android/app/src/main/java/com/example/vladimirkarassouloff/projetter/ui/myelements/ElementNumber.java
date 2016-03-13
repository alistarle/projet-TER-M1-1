package com.example.vladimirkarassouloff.projetter.ui.myelements;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.vladimirkarassouloff.projetter.R;
import com.example.vladimirkarassouloff.projetter.customlistener.ValidationDialogNumber;
import com.example.vladimirkarassouloff.projetter.ui.myelementsproduction.Production;
import com.example.vladimirkarassouloff.projetter.myelementsstring.ElementString;
import com.example.vladimirkarassouloff.projetter.myelementsstring.NumberString;

import java.util.List;

/**
 * Created by Vladimir on 24/02/2016.
 */
public class ElementNumber extends TextView implements DraggableElement {


    private NumberString tv;


    public ElementNumber(Context context) {
        super(context);
        this.setText("Nombre");
    }

    public ElementNumber(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setText("Nombre");

    }


    @Override
    public boolean isDropSupported(Production p) {
        return p.supportDropNumber();
    }

    @Override
    public ElementString onDraggedOnBlock(Production block) {
        tv = new NumberString();


        LayoutInflater li = LayoutInflater.from(getContext());
        View promptsView = li.inflate(R.layout.promptnumber, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
        // set prompts.xml to alertdialog builder
        alertDialogBuilder.setView(promptsView);
        final EditText userInput = (EditText) promptsView.findViewById(R.id.editTextNumberInput);

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


        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        // show it
        alertDialog.show();
        Button theButton = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
        theButton.setOnClickListener(new ValidationDialogNumber(alertDialog,promptsView,block,tv));



        return tv;
    }
    @Override
    public List<View> onDraggedOnLine(View v) {
        return null;
    }


}