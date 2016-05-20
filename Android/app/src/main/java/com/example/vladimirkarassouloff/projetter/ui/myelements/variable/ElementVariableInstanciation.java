package com.example.vladimirkarassouloff.projetter.ui.myelements.variable;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

    private Production tv;

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
    public List<View> onDraggedOnLine(View v) {
        List<View> array = new ArrayList<View>();
        tv = new Production(v.getContext(),new VariableInstanciationString(type,name));
        array.add(tv);


        LayoutInflater li = LayoutInflater.from(v.getContext());
        View promptsView = li.inflate(R.layout.promptvariable, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(v.getContext());
        // set prompts.xml to alertdialog builder
        alertDialogBuilder.setView(promptsView);
        final EditText userInput = (EditText) promptsView.findViewById(R.id.editTextVariableInput);
        final PromptTypeVariableView ptv = (PromptTypeVariableView) promptsView.findViewById(R.id.promptviewtypevariable);

        if(Debug.DEBUG_APP ){
            userInput.setText("varName");
        }

        // set dialog message
        alertDialogBuilder.setCancelable(false).setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                               /* name = userInput.getText().toString();
                                type = ptv.getType();

                                tv.setName(name);
                                tv.setType(type);
                                refreshTextView();

                                Intent intent = new Intent("newVariable");
                                // You can also include some extra data.
                                intent.putExtra("variable", userInput.getText().toString());
                                LocalBroadcastManager.getInstance(getContext()).sendBroadcast(intent);*/

                    }
                })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                /*ViewGroup owner = (ViewGroup) tv.getParent();
                                owner.removeView(tv);*/
                                Intent intent = new Intent("removeLastAction");
                                LocalBroadcastManager.getInstance(MyApp.context).sendBroadcast(intent);
                                dialog.cancel();
                            }
                        });


        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        // show it
        alertDialog.show();
        Button theButton = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
        theButton.setOnClickListener(new ValidationDialogVariable(alertDialog,promptsView,tv));



        return array;
    }

    @Override
    public ElementString onDraggedOnBlock(Production block) {
        return new VariableInstanciationString(type,name);
    }

    @Override
    public boolean isDropSupported(Production p) {
        return p.supportDropVariableInstanciation();
    }

    public void refreshTextView(){
        tv.refreshText();
    }
}
