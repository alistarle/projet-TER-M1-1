package com.example.vladimirkarassouloff.projetter.myelements.operator;

import android.content.Context;

import com.example.vladimirkarassouloff.projetter.myelementsstring.operator.OperatorMinusString;
import com.example.vladimirkarassouloff.projetter.myelementsstring.operator.OperatorString;

/**
 * Created by Vladimir on 25/02/2016.
 */
public class ElementMinusOperator extends ElementOperator {

    public ElementMinusOperator(Context context) {
        super(context, new OperatorMinusString());
    }
}
