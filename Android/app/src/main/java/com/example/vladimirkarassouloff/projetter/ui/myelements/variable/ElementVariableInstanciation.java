package com.example.vladimirkarassouloff.projetter.ui.myelements.variable;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.vladimirkarassouloff.projetter.ui.MyApp;
import com.example.vladimirkarassouloff.projetter.utils.Debug;
import com.example.vladimirkarassouloff.projetter.R;
import com.example.vladimirkarassouloff.projetter.customlistener.ValidationDialogVariable;
import com.example.vladimirkarassouloff.projetter.ui.myelements.DraggableElement;
import com.example.vladimirkarassouloff.projetter.ui.myelementsproduction.Production;
import com.example.vladimirkarassouloff.projetter.myelementsstring.ElementString;
import com.example.vladimirkarassouloff.projetter.myelementsstring.variable.VariableInstanciationString;
import com.example.vladimirkarassouloff.projetter.ui.myviews.prompt.PromptTypeVariableView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vladimir on 13/02/2016.
 */
public class ElementVariableInstanciation extends TextView implements DraggableElement {


    private String name;
    private String type;

    private VariableInstanciationString vis;

    public ElementVariableInstanciation(Context context){
        super(context);
        this.setText("Nouvelle Variable");
    }
    public ElementVariableInstanciation(Context context, AttributeSet attrs){
        super(context, attrs);
    }

    public String getType(){
        return type;
    }



    @Override
    public List<Production> onDraggedOnLine(View v) {
        List<Production> array = new ArrayList<Production>();
        vis = new VariableInstanciationString();
        Production tv = new Production(v.getContext(),vis);
        array.add(tv);


        LayoutInflater li = LayoutInflater.from(v.getContext());
        View promptsView = li.inflate(R.layout.promptvariable, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(v.getContext());
        alertDialogBuilder.setView(promptsView);
        final EditText userInput = (EditText) promptsView.findViewById(R.id.editTextVariableInput);
        final PromptTypeVariableView ptv = (PromptTypeVariableView) promptsView.findViewById(R.id.promptviewtypevariable);

        if(Debug.DEBUG_APP ){
            userInput.setText("varName");
        }

        // set dialog message
        alertDialogBuilder.setCancelable(false).setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {}
                })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent intent = new Intent("removeLastAction");
                                LocalBroadcastManager.getInstance(MyApp.context).sendBroadcast(intent);
                                dialog.cancel();
                            }
                        });


        AlertDialog alertDialog = alertDialogBuilder.create();
        // show it
        alertDialog.show();
        Button theButton = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
        theButton.setOnClickListener(new ValidationDialogVariable(alertDialog,promptsView,tv,vis));



        return array;
    }

    @Override
    public ElementString onDraggedOnBlock(Production block) {
        vis = new VariableInstanciationString();
        return vis;
    }

    @Override
    public boolean isDropSupported(Production p) {
        return p.supportDropVariableInstanciation();
    }



    @Override
    public void onDropOver(final Production block) {
        LayoutInflater li = LayoutInflater.from(block.getContext());
        View promptsView = li.inflate(R.layout.promptvariable, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(block.getContext());
        alertDialogBuilder.setView(promptsView);
        final EditText userInput = (EditText) promptsView.findViewById(R.id.editTextVariableInput);
        final PromptTypeVariableView ptv = (PromptTypeVariableView) promptsView.findViewById(R.id.promptviewtypevariable);

        alertDialogBuilder.setCancelable(false).setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {}
                })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent intent = new Intent("removeLastAction");
                                LocalBroadcastManager.getInstance(MyApp.context).sendBroadcast(intent);
                                dialog.cancel();
                            }
                        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
        Button theButton = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
        theButton.setOnClickListener(new ValidationDialogVariable(alertDialog,promptsView,block,vis));
    }

    @Override
    public boolean isDraggableOnLine() {
        return true;
    }
}
