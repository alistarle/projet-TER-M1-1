package com.example.vladimirkarassouloff.projetter.ui.myelementsproduction.fonction;

import android.content.Context;
import android.util.AttributeSet;

import com.example.vladimirkarassouloff.projetter.myelementsstring.ElementString;
import com.example.vladimirkarassouloff.projetter.myelementsstring.NumberString;
import com.example.vladimirkarassouloff.projetter.myelementsstring.logic.LogicString;
import com.example.vladimirkarassouloff.projetter.myelementsstring.variable.VariableString;
import com.example.vladimirkarassouloff.projetter.ui.myelementsproduction.Production;
import com.example.vladimirkarassouloff.projetter.myelementsstring.fonction.FonctionInstanciationString;

/**
 * Created by Vladimir on 16/02/2016.
 */
public class ProductionFonctionInstanciation extends Production {

    protected FonctionInstanciationString element;

    public ProductionFonctionInstanciation(Context context) {
        super(context);
        this.setText("Nouvelle fonction");
        element = new FonctionInstanciationString("Nouvelle","Fonction");
        this.basicElement = element;
    }

    public ProductionFonctionInstanciation(Context context,String type,String name) {
        super(context);
        element = new FonctionInstanciationString(name,type);
        this.basicElement = element;
    }

    public ProductionFonctionInstanciation(Context context, AttributeSet attrs,String type,String name) {
        super(context, attrs);
        element = new FonctionInstanciationString(name,type);
        this.basicElement = element;

    }


    public String getType() {
        return element.getType();
    }

    public void setType(String type) {
        element.setType(type);
    }

    public String getName() {
        return element.getName();
    }

    public void setName(String name) {
        element.setName(name);
    }

    public String getArgumentsToString(){
        String s = "";
        for(int i = 0 ; i < basicElement.components.size() ; i++){
            s+=basicElement.components.get(i).toString();
            if(i != basicElement.components.size()-1){
                s+=",";
            }
        }
        return s;
    }

    @Override
    public int tabChanger() {
        return 1;
    }

    @Override
    public String getBasicText() {
        return  this.getType()+" "+this.getName()+" ("+this.getArgumentsToString()+") {";
    }

    @Override
    public boolean supportDropVariable() {
        return true;
    }

    @Override
    public void onDrop(VariableString ev) {
        basicElement.components.add(ev);
    }

    @Override
    public boolean supportDropNumber() {
        return true;
    }

    @Override
    public void onDrop(NumberString ns) {
        basicElement.components.add(ns);
    }

    @Override
    public boolean supportDropLogic(LogicString op) {
        return true;
    }

    @Override
    public void onDrop(LogicString os) {
        basicElement.components.add(os);
    }
}
