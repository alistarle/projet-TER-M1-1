package com.example.vladimirkarassouloff.projetter.ui.myelements.operator;

import android.content.Context;

import com.example.vladimirkarassouloff.projetter.myelementsstring.operator.OperatorPlusString;

/**
 * Created by Vladimir on 25/02/2016.
 */
public class ElementPlusOperator extends ElementOperator{
    public ElementPlusOperator(Context context) {
        super(context, new OperatorPlusString());
    }

}
