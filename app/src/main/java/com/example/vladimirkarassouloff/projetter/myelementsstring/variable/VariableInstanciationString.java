package com.example.vladimirkarassouloff.projetter.myelementsstring.variable;

import com.example.vladimirkarassouloff.projetter.myelementsstring.ElementString;

/**
 * Created by Vladimir on 17/02/2016.
 */
public class VariableInstanciationString extends ElementString {
    private String type;
    private String name;

    public VariableInstanciationString(String type,String name) {
        this.name = name;
        this.type = type;
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
        return this.type+" "+this.name;
    }
}
