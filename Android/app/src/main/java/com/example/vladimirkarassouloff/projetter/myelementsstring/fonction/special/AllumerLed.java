package com.example.vladimirkarassouloff.projetter.myelementsstring.fonction.special;

/**
 * Created by Vladimir on 20/05/2016.
 */
public class AllumerLed extends FonctionSpecialeString {

    public AllumerLed() {
        super("AllumerLed");
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
