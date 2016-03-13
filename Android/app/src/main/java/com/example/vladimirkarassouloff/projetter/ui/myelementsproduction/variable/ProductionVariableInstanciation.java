package com.example.vladimirkarassouloff.projetter.ui.myelementsproduction.variable;

import android.content.Context;
import android.util.AttributeSet;

import com.example.vladimirkarassouloff.projetter.ui.myelementsproduction.Production;
import com.example.vladimirkarassouloff.projetter.myelementsstring.logic.LogicString;
import com.example.vladimirkarassouloff.projetter.myelementsstring.variable.VariableInstanciationString;

/**
 * Created by Vladimir on 14/02/2016.
 */
public class ProductionVariableInstanciation extends Production {


    protected VariableInstanciationString element;

    public ProductionVariableInstanciation(Context context,String type,String name) {
        super(context);
        this.element = new VariableInstanciationString(type,name);

    }

    public ProductionVariableInstanciation(Context context) {
        super(context);
        this.setText("Nouvelle Variable");
        this.element = new VariableInstanciationString("Nouvelle","Variable");
    }

    public ProductionVariableInstanciation(Context context, AttributeSet attrs,String type,String name) {
        super(context, attrs);
        this.element = new VariableInstanciationString(type,name);

    }

    @Override
    public String getBasicText() {
        return element.toString();
    }

    @Override
    public boolean supportDropLogic(LogicString op) {
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

    public String getType() {
        return element.getType();
    }

    public void setType(String type) {
        element.setType(type);
    }

    public String getName() {
        return element.getName();
    }

    public void setName(String name) {
        element.setName(name);
    }



}
