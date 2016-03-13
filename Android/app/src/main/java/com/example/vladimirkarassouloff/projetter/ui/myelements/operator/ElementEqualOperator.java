package com.example.vladimirkarassouloff.projetter.ui.myelements.operator;

import android.content.Context;

import com.example.vladimirkarassouloff.projetter.myelementsstring.operator.OperatorEqualString;

/**
 * Created by Vladimir on 25/02/2016.
 */
public class ElementEqualOperator extends ElementOperator {
    public ElementEqualOperator(Context context) {
        super(context, new OperatorEqualString());
    }
}
