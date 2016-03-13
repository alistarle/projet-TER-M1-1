package com.example.vladimirkarassouloff.projetter.ui.myelementsproduction.condition;

import android.content.Context;
import android.util.AttributeSet;

/**
 * Created by Vladimir on 15/02/2016.
 */
public class ProductionElseIf extends ProductionIf {
    public ProductionElseIf(Context context) {
        super(context);
    }

    public ProductionElseIf(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public String getBasicText() {
        return "else "+super.getBasicText();
    }


}
