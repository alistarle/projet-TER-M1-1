package com.example.vladimirkarassouloff.projetter.myelementsproduction;

import android.content.Context;
import android.util.AttributeSet;

/**
 * Created by Vladimir on 15/02/2016.
 */
public class ProductionBraceCloser extends Production {
    public ProductionBraceCloser(Context context) {
        super(context);
    }

    public ProductionBraceCloser(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public String getBasicText() {
        return "}";
    }

    @Override
    public boolean supportDropIf() {
        return true;
    }

    @Override
    public boolean supportDropElse() {
        return false;
    }

    @Override
    public boolean supportDropElseIf() {
        return true;
    }

    @Override
    public int tabChanger() {
        return -1;
    }



}