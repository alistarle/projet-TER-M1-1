package com.example.vladimirkarassouloff.projetter.ui.myelementsproduction.variable;

import android.content.Context;
import android.util.AttributeSet;

import com.example.vladimirkarassouloff.projetter.ui.myelementsproduction.Production;
import com.example.vladimirkarassouloff.projetter.myelementsstring.logic.LogicString;
import com.example.vladimirkarassouloff.projetter.myelementsstring.variable.VariableString;

/**
 * Created by Vladimir on 15/02/2016.
 */
public class ProductionVariable extends Production {

    private VariableString element;

    public ProductionVariable(Context context,String name) {
        super(context);
        this.element = new VariableString(name);
    }

    public ProductionVariable(Context context, AttributeSet attrs,String name) {
        super(context, attrs);
        this.element = new VariableString(name);
    }

    @Override
    public String getBasicText() {
        return this.element.toString();
    }

    @Override
    public boolean supportDropLogic(LogicString op)
    {
        return true;
    }

    @Override
    public boolean supportDropVariable() {
        return true;
    }

    @Override
    public boolean supportDropNumber() {
        return true;
    }
}