package com.example.vladimirkarassouloff.projetter.myelementsstring.fonction.special;

/**
 * Created by Vladimir on 20/05/2016.
 */
public class EteindreLed extends FonctionSpecialeString{
    public EteindreLed() {
        super("EteindreLed");
    }


    @Override
    public boolean supportDropFonction() {
        return false;
    }

    @Override
    public boolean supportDropNumber() {
        return false;
    }

    @Override
    public boolean supportDropVariable() {
        return false;
    }


}
