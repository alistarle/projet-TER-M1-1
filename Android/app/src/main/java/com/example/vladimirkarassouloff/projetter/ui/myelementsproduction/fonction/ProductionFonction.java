package com.example.vladimirkarassouloff.projetter.ui.myelementsproduction.fonction;

import android.content.Context;
import android.util.AttributeSet;

import com.example.vladimirkarassouloff.projetter.ui.myelementsproduction.Production;
import com.example.vladimirkarassouloff.projetter.myelementsstring.fonction.FonctionString;
import com.example.vladimirkarassouloff.projetter.myelementsstring.logic.LogicString;

/**
 * Created by Vladimir on 15/02/2016.
 */
public class ProductionFonction extends Production {

    private FonctionString element;

    public ProductionFonction(Context context,String name) {
        super(context);
        this.element = new FonctionString(name);
        this.basicElement = this.element;
    }

    public ProductionFonction(Context context, AttributeSet attrs,String name) {
        super(context, attrs);
        this.element = new FonctionString(name);
    }

    @Override
    public int tabChanger() {
        return 0;
    }

    @Override
    public String getBasicText() {
        return this.element.getName()+"()";
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


}