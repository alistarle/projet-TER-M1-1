package com.example.vladimirkarassouloff.projetter.myelementsstring.condition;

import com.example.vladimirkarassouloff.projetter.myelementsstring.ElementString;

/**
 * Created by Vladimir on 20/05/2016.
 */
public class ElseString extends ElementString {

    @Override
    public int tabChanger() {
        return 1;
    }

    @Override
    public String toString() {
        return "else {";
    }

    @Override
    public boolean shouldHaveSemicolumn(){
        return false;
    }
}
