package com.example.vladimirkarassouloff.projetter.ui.myelements.operator;

import android.content.Context;

import com.example.vladimirkarassouloff.projetter.myelementsstring.operator.OperatorMinusString;

/**
 * Created by Vladimir on 25/02/2016.
 */
public class ElementMinusOperator extends ElementOperator {

    public ElementMinusOperator(Context context) {
        super(context, new OperatorMinusString());
    }
}
