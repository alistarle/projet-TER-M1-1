package com.example.vladimirkarassouloff.projetter.myelementsstring.fonction;

import com.example.vladimirkarassouloff.projetter.myelementsstring.ElementString;

/**
 * Created by Vladimir on 20/05/2016.
 */
public class ReturnString extends ElementString {

    public ReturnString() {

}





    @Override
    public String toString() {
        return "return ("+getComponentString()+")";
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
    public boolean supportDropFonction() {
        return true;
    }


}