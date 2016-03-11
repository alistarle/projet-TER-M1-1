package com.example.vladimirkarassouloff.projetter.myelementsstring.logic;

import com.example.vladimirkarassouloff.projetter.myelementsstring.ElementString;

/**
 * Created by Vladimir on 18/02/2016.
 */
public abstract class LogicString extends ElementString {
    protected String operator;

    public LogicString(String operator) {
        this.operator = operator;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    @Override
    public String toString() {
        return operator;
    }
}
