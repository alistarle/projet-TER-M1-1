package com.example.vladimirkarassouloff.projetter.ui.myelementsproduction.fonction;

import android.content.Context;
import android.util.AttributeSet;

import com.example.vladimirkarassouloff.projetter.ui.myelementsproduction.Production;
import com.example.vladimirkarassouloff.projetter.myelementsstring.fonction.FonctionInstanciationString;

/**
 * Created by Vladimir on 16/02/2016.
 */
public class ProductionFonctionInstanciation extends Production {

    protected FonctionInstanciationString element;

    public ProductionFonctionInstanciation(Context context) {
        super(context);
        this.setText("Nouvelle fonction");
        element = new FonctionInstanciationString("Nouvelle","Fonction");
    }

    public ProductionFonctionInstanciation(Context context,String type,String name) {
        super(context);
        element = new FonctionInstanciationString(name,type);
    }

    public ProductionFonctionInstanciation(Context context, AttributeSet attrs,String type,String name) {
        super(context, attrs);
        element = new FonctionInstanciationString(name,type);

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

    @Override
    public int tabChanger() {
        return 1;
    }

    @Override
    public String getBasicText() {
        return  this.getType()+" "+this.getName()+" () {";
    }

    @Override
    public boolean supportDropVariableInstanciation() {
        return super.supportDropVariableInstanciation();
    }
}
