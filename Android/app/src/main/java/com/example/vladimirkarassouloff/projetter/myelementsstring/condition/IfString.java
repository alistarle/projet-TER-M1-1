package com.example.vladimirkarassouloff.projetter.myelementsstring.condition;

import com.example.vladimirkarassouloff.projetter.myelementsstring.ElementString;
import com.example.vladimirkarassouloff.projetter.myelementsstring.logic.LogicString;
import com.example.vladimirkarassouloff.projetter.myelementsstring.operator.OperatorString;

/**
 * Created by Vladimir on 20/05/2016.
 */
public class IfString extends ElementString {
    @Override
    public int tabChanger() {
        return 1;
    }

    @Override
    public String toString() {
        return "if ("+getComponentString()+") {";
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
    public boolean supportDropVariable() {
        return true;
    }

    @Override
    public boolean supportDropFonction() {
        return true;
    }

    @Override
    protected String separator() {
        return " ";
    }

    @Override
    protected boolean shouldWrapComponents(ElementString es){
        if(es instanceof LogicString){
            return false;
        }
        else if(es instanceof OperatorString){
            return false;
        }
        return true;
    }
    @Override
    public boolean shouldHaveSemicolumn(){
        return false;
    }
}
