package com.example.vladimirkarassouloff.projetter.myelements.operator;

import android.content.Context;

import com.example.vladimirkarassouloff.projetter.myelementsstring.operator.OperatorMultiplyString;
import com.example.vladimirkarassouloff.projetter.myelementsstring.operator.OperatorString;

/**
 * Created by Vladimir on 25/02/2016.
 */
public class ElementMultiplyOperator extends ElementOperator {
    public ElementMultiplyOperator(Context context) {
        super(context, new OperatorMultiplyString());
    }
}
