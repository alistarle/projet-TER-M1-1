package com.example.vladimirkarassouloff.projetter.myelementsstring;

import android.graphics.Color;
import android.util.Log;

import com.example.vladimirkarassouloff.projetter.myelementsstring.fonction.FonctionInstanciationString;
import com.example.vladimirkarassouloff.projetter.myelementsstring.fonction.FonctionString;
import com.example.vladimirkarassouloff.projetter.myelementsstring.logic.LogicString;
import com.example.vladimirkarassouloff.projetter.myelementsstring.operator.OperatorString;
import com.example.vladimirkarassouloff.projetter.myelementsstring.variable.VariableInstanciationString;
import com.example.vladimirkarassouloff.projetter.myelementsstring.variable.VariableString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vladimir on 17/02/2016.
 */
public class ElementString implements Serializable {

    protected int colorBackgroundDefault = getBackgroundColorDefault();

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


    //precise si les composant devraient etre entouré de parentheses
    protected boolean shouldWrapComponents(ElementString element){
        return false;
    }

    public String getComponentString(){
        String s = "";

        for(int i = 0 ; i < components.size() ; i++){
            if(shouldWrapComponents(components.get(i)) && components.size() > 1){
                s+="(";
            }
            s += components.get(i).getBasicText();
            if(shouldWrapComponents(components.get(i)) && components.size() > 1){
                s+=")";
            }
            if(i < components.size()-1){
                s+=separator();
            }
        }

        return s;
    }
    protected String separator(){
        return " ";
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
        if(supportDropVariableInstanciation()){
            components.add(evi);
        }

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

    public boolean supportDropFonction(){
        return false;
    }
    public void onDrop(FonctionString el){
        if(supportDropFonction()){
            components.add(el);
        }
    }

    public boolean supportDropFonctionInstanciation(){
        return false;
    }
    public void onDrop(FonctionInstanciationString el){
        if(supportDropFonctionInstanciation()){
            components.add(el);
        }
    }




    public boolean supportDrop(ElementString es){

        if(es instanceof OperatorString) {
            return supportDropOperator();
        } else if (es instanceof VariableString) {
            return supportDropVariable();
        } else if (es instanceof VariableInstanciationString) {
            return supportDropVariableInstanciation();
        } else if (es instanceof LogicString) {
            return supportDropLogic((LogicString) es);
        } else if (es instanceof NumberString){
            return supportDropNumber();
        }
        else if (es instanceof FonctionString){
            return supportDropFonction();
        }
        else if (es instanceof FonctionInstanciationString){
            return supportDropFonctionInstanciation();
        }
        else {
            Log.wtf("message", "instanceof non trouvée : drop not supported");
        }
        return false;
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
        else if (s instanceof FonctionString){
            onDrop((FonctionString)s);
        }
        else {
            Log.wtf("message", "Drop not supported");
        }
    }



    protected boolean allowDropOnComponent(){
        return true;
    }

    public void addAllElementSupportingDrop(List<ElementString> array, ElementString testDrop){
        if(this.supportDrop(testDrop)){
            array.add(this);
        }
        if(this.allowDropOnComponent()) {
            for (ElementString comp : components) {
                comp.addAllElementSupportingDrop(array, testDrop);
            }
        }
    }

    public void addAllElementEditable(List<ElementString> array){
        if(this.components.size() > 0){
            array.add(this);
        }
        for(ElementString comp : components){
            comp.addAllElementEditable(array);
        }
    }



    public void setColor(int newColor){this.colorBackgroundDefault = newColor;}
    public int getCurrentBackgroundColor(){return colorBackgroundDefault;}
    public int getBackgroundColorDefault(){
        return Color.rgb(200,200,200);
    }


    public int getBackgroundColorOnTouch(){
        return Color.rgb(128,128,128);
    }



}
