package com.example.vladimirkarassouloff.projetter.myelements.logic;

import android.content.Context;

import com.example.vladimirkarassouloff.projetter.myelementsstring.logic.LogicNotString;

/**
 * Created by Vladimir on 18/02/2016.
 */
public class ElementLogicNot extends ElementLogic {

    public ElementLogicNot(Context context) {
        super(context, new LogicNotString());
    }
}
