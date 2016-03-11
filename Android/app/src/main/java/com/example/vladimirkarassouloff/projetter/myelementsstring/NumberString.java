package com.example.vladimirkarassouloff.projetter.myelementsstring;

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
        return this.nombre;
    }
}
