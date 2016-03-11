package com.example.vladimirkarassouloff.projetter.myelements.logic;

import android.content.Context;

import com.example.vladimirkarassouloff.projetter.myelementsstring.logic.LogicNotEqString;

/**
 * Created by Vladimir on 19/02/2016.
 */
public class ElementLogicNotEq extends ElementLogic {
    public ElementLogicNotEq(Context context) {
        super(context, new LogicNotEqString());
    }
}