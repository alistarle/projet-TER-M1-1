package com.example.vladimirkarassouloff.projetter.myelementsstring.iterative;

import com.example.vladimirkarassouloff.projetter.myelementsstring.ElementString;
import com.example.vladimirkarassouloff.projetter.myelementsstring.logic.LogicString;

/**
 * Created by Vladimir on 21/05/2016.
 */
public class WhileString extends ElementString {
    @Override
    public int tabChanger() {
        return 1;
    }

    @Override
    public String toString() {
        return "while ("+getComponentString()+") {";
    }

    @Override
    protected String separator() {
        return " ";
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
    public boolean supportDropOperator() {
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
}