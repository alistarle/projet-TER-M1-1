package com.example.vladimirkarassouloff.projetter.ui.myelements.logic;

import android.content.Context;

import com.example.vladimirkarassouloff.projetter.myelementsstring.logic.LogicSupEqString;

/**
 * Created by Vladimir on 19/02/2016.
 */
public class ElementLogicSupEq extends ElementLogic {
    public ElementLogicSupEq(Context context) {
        super(context, new LogicSupEqString());
    }
}