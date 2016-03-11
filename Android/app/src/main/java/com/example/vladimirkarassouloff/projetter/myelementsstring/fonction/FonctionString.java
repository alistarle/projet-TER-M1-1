package com.example.vladimirkarassouloff.projetter.myelementsstring.fonction;

import com.example.vladimirkarassouloff.projetter.myelementsstring.ElementString;

/**
 * Created by Vladimir on 17/02/2016.
 */
public class FonctionString extends ElementString  {
    private String name;

    public FonctionString(String name) {
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
        return this.name;
    }
}
