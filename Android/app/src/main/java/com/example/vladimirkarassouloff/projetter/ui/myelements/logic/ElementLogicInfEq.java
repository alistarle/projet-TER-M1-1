package com.example.vladimirkarassouloff.projetter.ui.myelements.logic;

import android.content.Context;

import com.example.vladimirkarassouloff.projetter.myelementsstring.logic.LogicInfEqString;

/**
 * Created by Vladimir on 19/02/2016.
 */
public class ElementLogicInfEq extends ElementLogic {
    public ElementLogicInfEq(Context context) {
        super(context, new LogicInfEqString());
    }
}