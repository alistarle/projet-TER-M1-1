package com.example.vladimirkarassouloff.projetter.myelementsstring.fonction.special;

import com.example.vladimirkarassouloff.projetter.myelementsstring.fonction.FonctionString;

/**
 * Created by Vladimir on 20/05/2016.
 */
public class FonctionSpecialeString extends FonctionString {
    public FonctionSpecialeString(String name) {
        super(name);
    }

    @Override
    public boolean supportDropVariable() {
        return false;
    }

    @Override
    public boolean supportDropNumber() {
        return false;
    }


    @Override
    public boolean supportDropFonction() {
        return false;
    }

    protected boolean allowDropOnComponent(){
        return false;
    }

}
