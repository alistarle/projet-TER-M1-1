package com.example.vladimirkarassouloff.projetter.myelementsstring;

/**
 * Created by Vladimir on 20/05/2016.
 */
public class BraceCloserString extends ElementString {

    @Override
    public String getBasicText() {
        return "}";
    }

    @Override
    public int tabChanger() {
        return -1;
    }
}
