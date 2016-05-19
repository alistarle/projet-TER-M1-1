package com.example.vladimirkarassouloff.projetter.ui.myelementsproduction;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.vladimirkarassouloff.projetter.R;
import com.example.vladimirkarassouloff.projetter.customlistener.ValidationDialogFunction;
import com.example.vladimirkarassouloff.projetter.myelementsstring.ElementString;
import com.example.vladimirkarassouloff.projetter.myelementsstring.NumberString;
import com.example.vladimirkarassouloff.projetter.myelementsstring.operator.OperatorString;
import com.example.vladimirkarassouloff.projetter.myelementsstring.logic.LogicString;
import com.example.vladimirkarassouloff.projetter.myelementsstring.variable.VariableInstanciationString;
import com.example.vladimirkarassouloff.projetter.myelementsstring.variable.VariableString;
import com.example.vladimirkarassouloff.projetter.ui.myviews.prompt.PromptTypeVariableView;
import com.example.vladimirkarassouloff.projetter.utils.Debug;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vladimir on 14/02/2016.
 */
public abstract class Production extends TextView {

    protected ElementString basicElement;

    public Production(Context context){
        super(context);
        this.basicElement = new ElementString();
        init();
    }
    public Production(Context context, AttributeSet attrs){
        super(context, attrs);
        this.basicElement = new ElementString();
        init();
    }
    public Production(Context context,ElementString basicElement){
        super(context);
        this.basicElement = basicElement;
        init();
    }
    public Production(Context context, AttributeSet attrs,ElementString basicElement){
        super(context, attrs);
        this.basicElement = basicElement;
        init();
    }

    protected void init(){
        this.setText("Default");
        this.setPadding(5, 10, 5, 10);
        basicElement.components = new ArrayList<ElementString>();
        this.setOnLongClickListener(
                new OnLongClickListener() {
                    public boolean onLongClick(View arg0) {
                        LayoutInflater li = LayoutInflater.from(getContext());
                        View promptsView = li.inflate(R.layout.longclickproductioncontext, null);
                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
                        alertDialogBuilder.setView(promptsView);
                        alertDialogBuilder.setCancelable(true).setItems(R.array.contextMenuProduction, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                       if(which == 0){
                                           supprimer();
                                       }
                                        else if(which == 1){
                                           modifier();
                                       }
                                    }
                                });
                        AlertDialog alertDialog = alertDialogBuilder.create();
                        alertDialog.show();
                        return false;
                    }
                }
        );
    }

    public void supprimer(){
        ViewGroup parent = (ViewGroup)getParent();
        parent.removeView(this);
    }
    public void modifier(){
        LayoutInflater li = LayoutInflater.from(getContext());
        View promptsView = li.inflate(R.layout.modifyproduction, null);

        LinearLayout componentLayout =(LinearLayout) promptsView.findViewById(R.id.elementsProduction);

        ArrayList<ElementString> arrayElements = new ArrayList<>();
        getAllComponent(arrayElements,false);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
        alertDialogBuilder.setView(promptsView);
        alertDialogBuilder.setCancelable(true).setPositiveButton("OK",
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
    }

    public void getAllComponent(ArrayList<ElementString> array,boolean includeSelf){
        if(includeSelf && basicElement != null){
            array.add(basicElement);
        }
        for(ElementString es : basicElement.components){
            es.getAllComponent(array,true);
        }
    }


    public abstract String getBasicText();

    public void refreshText(){
        this.setText(getBasicText());
    }


    public boolean supportDropIf(){
        return false;
    }

    public boolean supportDropElse(){
        return false;
    }

    public boolean supportDropElseIf(){
        return false;
    }

    public boolean supportDropVariable(){
        return false;
    }

    public boolean supportDropVariableInstanciation(){
        return false;
    }

    public boolean supportDropNumber(){return false;}

    public boolean supportDropLogic(LogicString op){
        return false;
    }


    public boolean supportDropOperator(){
        return false;
    }




    //sert a l'indentation
    public int tabChanger(){
        return 0;
    }



    public void onDrop(ElementString s) {
        if (s instanceof OperatorString) {
            onDrop((OperatorString)s);
        } else if (s instanceof VariableString) {
            onDrop((VariableString) s);
        } else if (s instanceof VariableInstanciationString) {
            onDrop((VariableInstanciationString)s);
        } else if (s instanceof LogicString) {
            onDrop((LogicString) s);
        } else if (s instanceof NumberString){
            onDrop((NumberString)s);
        }
        else {
            Log.i("Drop not supported", "Drop not supported");
        }
    }

    public void onDrop(OperatorString el){
        Log.i("DROP NOT IMPLEMENTED", "logstring");
    }
    public void onDrop(VariableString ev){
        Log.i("DROP NOT IMPLEMENTED", "VariableString");

    }
    public void onDrop(VariableInstanciationString evi){
        Log.i("DROP NOT IMPLEMENTED", "VariableInstanciationString");

    }
    public void onDrop(LogicString os){
        Log.i("DROP NOT IMPLEMENTED", "OperatorString");
    }
    public void onDrop(NumberString ns){
        Log.i("DROP NOT IMPLEMENTED", "NumberString");
    }




    protected boolean isComponentEmpty(){
        return basicElement.components.size()==0;
    }

    protected boolean lastIsVariable(){
        if(basicElement.components.size()==0)
            return false;
        return this.basicElement.components.get(basicElement.components.size()-1) instanceof VariableString;
    }

    protected boolean lastIsOperator(){
        if(basicElement.components.size()==0)
            return false;
        return this.basicElement.components.get(basicElement.components.size()-1) instanceof LogicString;
    }
    protected boolean lastIsLogic(){
        if(basicElement.components.size()==0)
            return false;
        return this.basicElement.components.get(basicElement.components.size()-1) instanceof OperatorString;
    }

    public void addComponent(ElementString es){
        this.basicElement.components.add(es);
    }

}
