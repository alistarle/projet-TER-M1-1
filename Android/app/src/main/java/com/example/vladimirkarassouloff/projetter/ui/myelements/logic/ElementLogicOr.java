package com.example.vladimirkarassouloff.projetter.ui.myelements.logic;

import android.content.Context;

import com.example.vladimirkarassouloff.projetter.myelementsstring.logic.LogicOrString;

/**
 * Created by Vladimir on 18/02/2016.
 */
public class ElementLogicOr extends ElementLogic {
    public ElementLogicOr(Context context) {
        super(context, new LogicOrString());
    }
}
