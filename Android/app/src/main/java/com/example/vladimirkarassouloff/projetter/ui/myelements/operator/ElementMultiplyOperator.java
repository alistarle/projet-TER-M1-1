package com.example.vladimirkarassouloff.projetter.ui.myelements.operator;

import android.content.Context;

import com.example.vladimirkarassouloff.projetter.myelementsstring.operator.OperatorMultiplyString;

/**
 * Created by Vladimir on 25/02/2016.
 */
public class ElementMultiplyOperator extends ElementOperator {
    public ElementMultiplyOperator(Context context) {
        super(context, new OperatorMultiplyString());
    }
}
