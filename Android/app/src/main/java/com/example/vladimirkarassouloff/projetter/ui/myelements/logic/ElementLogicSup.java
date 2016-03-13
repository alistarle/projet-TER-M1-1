package com.example.vladimirkarassouloff.projetter.ui.myelements.logic;

import android.content.Context;

import com.example.vladimirkarassouloff.projetter.myelementsstring.logic.LogicSupString;

/**
 * Created by Vladimir on 19/02/2016.
 */
public class ElementLogicSup extends ElementLogic {
    public ElementLogicSup(Context context) {
        super(context, new LogicSupString());
    }
}