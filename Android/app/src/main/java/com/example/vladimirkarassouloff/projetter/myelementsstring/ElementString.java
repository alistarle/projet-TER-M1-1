package com.example.vladimirkarassouloff.projetter.myelementsstring;

import android.util.Log;

import com.example.vladimirkarassouloff.projetter.myelementsstring.logic.LogicString;
import com.example.vladimirkarassouloff.projetter.myelementsstring.operator.OperatorString;
import com.example.vladimirkarassouloff.projetter.myelementsstring.variable.VariableInstanciationString;
import com.example.vladimirkarassouloff.projetter.myelementsstring.variable.VariableString;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vladimir on 17/02/2016.
 */
public class ElementString {
    public List<ElementString> components;

    public ElementString(List<ElementString> components) {
        this.components = components;
    }
    public ElementString(){
        this.components = new ArrayList<>();
    }

    public void add(ElementString es){
        components.add(es);
    }

    public void getAllComponent(List<ElementString> array,boolean includeSelf){
        if(includeSelf){
            array.add(this);
        }
        for(ElementString es : components){
            es.getAllComponent(array,true);
        }
    }

    public String getComponentString(){
        String s = "";
        for(int i = 0 ; i < components.size() ; i++){
            s += components.get(i).getBasicText();
            if(i < components.size()-1){
                s+=",";
            }
        }
        return s;
    }

    //sert a l'indentation
    public int tabChanger(){
        return 0;
    }

    public String getBasicText(){
        return toString();
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
    public void onDrop(VariableString ev){
        if(supportDropVariable()){
            components.add(ev);
        }
    }


    public boolean supportDropVariableInstanciation(){
        return false;
    }
    public void onDrop(VariableInstanciationString evi){
        Log.i("DROP NOT IMPLEMENTED", "VariableInstanciationString");
    }

    public boolean supportDropNumber(){return false;}
    public void onDrop(NumberString ns) {
        if(supportDropNumber()) {
            components.add(ns);
        }
    }


    public boolean supportDropLogic(LogicString op){
        return false;
    }
    public void onDrop(LogicString os){
        if(supportDropLogic(os)) {
            components.add(os);
        }
    }


    public boolean supportDropOperator(){
        return false;
    }
    public void onDrop(OperatorString el){
        if(supportDropOperator()){
            components.add(el);
        }
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









}
