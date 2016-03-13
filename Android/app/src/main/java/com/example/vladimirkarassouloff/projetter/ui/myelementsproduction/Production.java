package com.example.vladimirkarassouloff.projetter.ui.myelementsproduction;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

import com.example.vladimirkarassouloff.projetter.myelementsstring.ElementString;
import com.example.vladimirkarassouloff.projetter.myelementsstring.NumberString;
import com.example.vladimirkarassouloff.projetter.myelementsstring.operator.OperatorString;
import com.example.vladimirkarassouloff.projetter.myelementsstring.logic.LogicString;
import com.example.vladimirkarassouloff.projetter.myelementsstring.variable.VariableInstanciationString;
import com.example.vladimirkarassouloff.projetter.myelementsstring.variable.VariableString;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vladimir on 14/02/2016.
 */
public abstract class Production extends TextView {

    protected List<ElementString> components;

    public Production(Context context){
        super(context);
        this.setText("Default");
        this.setPadding(5, 10, 5, 10);
        this.components = new ArrayList<ElementString>();
    }
    public Production(Context context, AttributeSet attrs){
        super(context, attrs);
        this.setText("Default");
        this.setPadding(5, 10, 5, 10);
        this.components = new ArrayList<ElementString>();
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
        return components.size()==0;
    }

    protected boolean lastIsVariable(){
        if(components.size()==0)
            return false;
        return this.components.get(components.size()-1) instanceof VariableString;
    }

    protected boolean lastIsOperator(){
        if(components.size()==0)
            return false;
        return this.components.get(components.size()-1) instanceof LogicString;
    }
    protected boolean lastIsLogic(){
        if(components.size()==0)
            return false;
        return this.components.get(components.size()-1) instanceof OperatorString;
    }


}
