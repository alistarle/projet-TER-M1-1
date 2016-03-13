package com.example.vladimirkarassouloff.projetter.ui.myelements.operator;

import android.content.Context;

import com.example.vladimirkarassouloff.projetter.myelementsstring.operator.OperatorDivideString;

/**
 * Created by Vladimir on 25/02/2016.
 */
public class ElementDivideOperator extends ElementOperator {
    public ElementDivideOperator(Context context) {
        super(context, new OperatorDivideString());
    }
}
