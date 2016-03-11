package com.example.vladimirkarassouloff.projetter.myelements.logic;

import android.content.Context;

import com.example.vladimirkarassouloff.projetter.myelementsstring.logic.LogicInfString;

/**
 * Created by Vladimir on 19/02/2016.
 */
public class ElementLogicInf extends ElementLogic {
    public ElementLogicInf(Context context) {
        super(context, new LogicInfString());
    }
}