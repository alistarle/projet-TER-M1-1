package com.example.vladimirkarassouloff.projetter.myelementsstring.operator;

import com.example.vladimirkarassouloff.projetter.myelementsstring.ElementString;

/**
 * Created by Vladimir on 17/02/2016.
 */
public abstract class OperatorString extends ElementString {

    protected String operator;

    public OperatorString(String op) {
        this.operator = op;
    }




    @Override
    public String toString() {
        return this.operator;
    }
}
