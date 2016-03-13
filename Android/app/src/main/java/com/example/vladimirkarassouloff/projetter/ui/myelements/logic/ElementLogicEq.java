package com.example.vladimirkarassouloff.projetter.ui.myelements.logic;

import android.content.Context;

import com.example.vladimirkarassouloff.projetter.myelementsstring.logic.LogicEqString;

/**
 * Created by Vladimir on 19/02/2016.
 */
public class ElementLogicEq extends ElementLogic {
    public ElementLogicEq(Context context) {
        super(context, new LogicEqString());
    }
}