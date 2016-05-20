package com.example.vladimirkarassouloff.projetter.myelementsstring.condition;

import com.example.vladimirkarassouloff.projetter.myelementsstring.ElementString;
import com.example.vladimirkarassouloff.projetter.myelementsstring.logic.LogicString;

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
    public boolean supportDropOperator() {
        return true;
    }

    @Override
    public boolean supportDropVariable() {
        return true;
    }
}
