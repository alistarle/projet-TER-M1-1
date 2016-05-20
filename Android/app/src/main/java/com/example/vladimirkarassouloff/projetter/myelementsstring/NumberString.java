package com.example.vladimirkarassouloff.projetter.myelementsstring;

import com.example.vladimirkarassouloff.projetter.myelementsstring.logic.LogicString;

/**
 * Created by Vladimir on 24/02/2016.
 */
public class NumberString extends ElementString {
    private String nombre;

    public NumberString() {
        this.nombre = "Nouveau Nombre";
    }

    public NumberString(String name) {
        this.nombre = name;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String name) {
        this.nombre = name;
    }

    @Override
    public String toString() {
        return this.nombre +" "+getComponentString();
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
    public boolean supportDropOperator() {
        return true;
    }

    @Override
    public boolean supportDropLogic(LogicString op) {
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
}
