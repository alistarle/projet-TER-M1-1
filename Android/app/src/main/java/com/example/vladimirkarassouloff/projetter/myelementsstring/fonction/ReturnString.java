package com.example.vladimirkarassouloff.projetter.myelementsstring.fonction;

import com.example.vladimirkarassouloff.projetter.myelementsstring.ElementString;
import com.example.vladimirkarassouloff.projetter.myelementsstring.logic.LogicString;
import com.example.vladimirkarassouloff.projetter.myelementsstring.operator.OperatorString;

/**
 * Created by Vladimir on 20/05/2016.
 */
public class ReturnString extends ElementString {

    public ReturnString() {

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
    public String toString() {
        if(components.size()==0){
            return "return";
        }
        return "return ("+getComponentString()+")";
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
    public boolean supportDropFonction() {
        return true;
    }


    @Override
    protected String separator(){
        return " ";
    }
}