package com.example.vladimirkarassouloff.projetter.myelementsstring.variable;

import com.example.vladimirkarassouloff.projetter.myelementsstring.ElementString;
import com.example.vladimirkarassouloff.projetter.myelementsstring.logic.LogicString;

/**
 * Created by Vladimir on 17/02/2016.
 */
public class VariableInstanciationString extends ElementString {
    private String type;
    private String name;

    public VariableInstanciationString() {
        this.type = "Nouvelle";
        this.name = "Variable";
    }
    public VariableInstanciationString(String type,String name) {
        this.type = type;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return this.type+" "+this.name+ " " + getComponentString();
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
