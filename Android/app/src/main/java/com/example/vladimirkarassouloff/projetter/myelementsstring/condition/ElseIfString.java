package com.example.vladimirkarassouloff.projetter.myelementsstring.condition;

import com.example.vladimirkarassouloff.projetter.myelementsstring.ElementString;

/**
 * Created by Vladimir on 20/05/2016.
 */
public class ElseIfString extends IfString {


    @Override
    public String toString() {
        return "else if ("+getComponentString()+") {";
    }

}
