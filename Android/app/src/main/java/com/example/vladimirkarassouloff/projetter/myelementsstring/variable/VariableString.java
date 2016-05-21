package com.example.vladimirkarassouloff.projetter.myelementsstring.variable;

import com.example.vladimirkarassouloff.projetter.myelementsstring.ElementString;

/**
 * Created by Vladimir on 17/02/2016.
 */
public class VariableString extends ElementString {
    private String name;

    public VariableString(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name+ " " + getComponentString();
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
    public boolean supportDropOperator() {
        return true;
    }

    @Override
    public boolean supportDropVariable() {
        return true;
    }
}
