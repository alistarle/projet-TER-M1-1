package com.example.vladimirkarassouloff.projetter.ui.myelementsproduction.condition;

import android.content.Context;
import android.util.AttributeSet;

import com.example.vladimirkarassouloff.projetter.ui.myelementsproduction.Production;
import com.example.vladimirkarassouloff.projetter.myelementsstring.ElementString;
import com.example.vladimirkarassouloff.projetter.myelementsstring.NumberString;
import com.example.vladimirkarassouloff.projetter.myelementsstring.operator.OperatorString;
import com.example.vladimirkarassouloff.projetter.myelementsstring.logic.LogicString;
import com.example.vladimirkarassouloff.projetter.myelementsstring.variable.VariableString;

/**
 * Created by Vladimir on 14/02/2016.
 */
public class ProductionIf extends Production {
    public ProductionIf(Context context) {
        super(context);
    }

    public ProductionIf(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public String getBasicText() {
        return "if ("+getIfContent()+") {";
    }

    protected String getIfContent(){
        String s = "";
        for(ElementString e : this.components){
            s+=e.toString()+" ";
        }
        if(s.length() != 0)
            s=s.substring(0,s.length()-1);
        return s;
    }


    @Override
    public int tabChanger() {
        return 1;
    }


    @Override
    public boolean supportDropVariable() {
        return true;
    }

    @Override
    public boolean supportDropNumber() {
        return true;
    }

    @Override
    public boolean supportDropLogic(LogicString op) {
        return true;
    }


    @Override
    public void onDrop(OperatorString el){
        this.components.add(el);
        /*if(lastIsVariable()){
            this.components.add(el);
        }*/
    }
    @Override
    public void onDrop(VariableString ev){
        this.components.add(ev);
        /*if(isComponentEmpty()){
            this.components.add(ev);
        }
        else if(lastIsOperator()){
            this.components.add(ev);
        }
        else if(lastIsLogic()){
            this.components.add(ev);
        }*/

    }

    @Override
    public void onDrop(LogicString os) {
        this.components.add(os);
        /*if(lastIsVariable()){
            this.components.add(os);
        }*/
    }

    @Override
    public void onDrop(NumberString ns) {
        this.components.add(ns);
    }
}
