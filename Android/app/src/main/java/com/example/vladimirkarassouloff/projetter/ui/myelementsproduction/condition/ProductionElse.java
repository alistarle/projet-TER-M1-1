package com.example.vladimirkarassouloff.projetter.ui.myelementsproduction.condition;

import android.content.Context;
import android.util.AttributeSet;

import com.example.vladimirkarassouloff.projetter.ui.myelementsproduction.Production;

/**
 * Created by Vladimir on 15/02/2016.
 */
public class ProductionElse  extends Production {
    public ProductionElse(Context context) {
        super(context);
    }

    public ProductionElse(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public String getBasicText() {
        return "else {";
    }


    @Override
    public int tabChanger() {
        return 1;
    }

}