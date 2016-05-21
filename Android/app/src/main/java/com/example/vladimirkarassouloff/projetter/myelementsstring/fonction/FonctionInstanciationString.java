package com.example.vladimirkarassouloff.projetter.myelementsstring.fonction;

import com.example.vladimirkarassouloff.projetter.myelementsstring.ElementString;

/**
 * Created by Vladimir on 17/02/2016.
 */
public class FonctionInstanciationString extends ElementString {
    private String name;
    private String type;

    public FonctionInstanciationString() {
        this.name = "Nouvelle";
        this.type = "Fonction";
    }
    public FonctionInstanciationString(String name, String type) {
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
        return this.type+" "+this.name+" ("+getComponentString()+") {";
    }

    @Override
    public int tabChanger() {
        return 1;
    }


    @Override
    public boolean supportDropVariableInstanciation() {
        return true;
    }

    protected boolean allowDropOnComponent(){
        return false;
    }
    protected String separator(){
        return ",";
    }
}
