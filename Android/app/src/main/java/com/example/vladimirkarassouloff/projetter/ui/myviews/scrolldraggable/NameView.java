package com.example.vladimirkarassouloff.projetter.ui.myviews.scrolldraggable;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.util.AttributeSet;
import android.view.View;

import com.example.vladimirkarassouloff.projetter.ui.myelements.fonction.ElementFonction;
import com.example.vladimirkarassouloff.projetter.ui.myelements.fonction.ElementFonctionInstanciation;
import com.example.vladimirkarassouloff.projetter.ui.myelements.fonction.ElementReturn;
import com.example.vladimirkarassouloff.projetter.ui.myelements.variable.ElementVariable;
import com.example.vladimirkarassouloff.projetter.ui.myelements.variable.ElementVariableInstanciation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vladimir on 14/02/2016.
 */
public class NameView extends ScrollDraggableElementView {

    private List<String> variables;
    private List<String> fonctions;

    private View headerVar;
    private View headerFunc;

    private BroadcastReceiver onNotice;

    public NameView(Context context){
        super(context);

    }

    public NameView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }


    @Override
    protected void init() {
       super.init();
        variables = new ArrayList<String>();
        fonctions = new ArrayList<String>();

        onNotice = new BroadcastReceiver() {

            @Override
            public void onReceive(Context context, Intent intent) {
                // intent can contain anydata
                if(intent.getAction().equals("newVariable")){
                    addVariable(intent.getStringExtra("variable"));
                }
                else if(intent.getAction().equals("newFunction")){
                    addFunction(intent.getStringExtra("function"));
                }
            }
        };
        LocalBroadcastManager.getInstance(getContext()).registerReceiver(onNotice, new IntentFilter("newVariable"));
        LocalBroadcastManager.getInstance(getContext()).registerReceiver(onNotice, new IntentFilter("newFunction"));





        this.addBigHeader("Variables & Fonctions");

        this.addHeader("Declarations : ");
        this.addDraggableElement(new ElementVariableInstanciation(getContext()));
        this.addDraggableElement(new ElementFonctionInstanciation(getContext()));
        this.addDraggableElement(new ElementReturn(getContext()));
        this.addBlankLine();


        this.headerVar = this.addHeader("Variables existantes : ");
        this.addBlankLine();

        this.headerFunc = this.addHeader("Fonctions existantes : ");
        this.addBlankLine();

    }

    public void addVariable(String name) {
        for(String s : this.variables){
            if(s.equals(name)){
                return;
            }
        }
        ElementVariable v = new ElementVariable(getContext(), name);
        addDraggableElement(v,ll.indexOfChild(headerVar)+1);
        this.variables.add(name);

    }






    public void addFunction(String name){
        for(String s : this.fonctions){
            if(s.equals(name)){
                return;
            }
        }
        ElementFonction f = new ElementFonction(getContext(), name);
        addDraggableElement(f,ll.indexOfChild(headerFunc)+1);
        this.fonctions.add(name);

    }


    public List<String> getFonctions() {
        return fonctions;
    }

    public List<String> getVariables() {
        return variables;
    }

    public void setVariables(List<String> variables) {
        this.variables = variables;
    }

    public void setFonctions(List<String> fonctions) {
        this.fonctions = fonctions;
    }
}
