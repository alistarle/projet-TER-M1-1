package com.example.vladimirkarassouloff.projetter.myelements.fonction;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.example.vladimirkarassouloff.projetter.myelements.DraggableElement;
import com.example.vladimirkarassouloff.projetter.myelementsproduction.Production;
import com.example.vladimirkarassouloff.projetter.myelementsproduction.fonction.ProductionFonction;
import com.example.vladimirkarassouloff.projetter.myelementsproduction.variable.ProductionVariable;
import com.example.vladimirkarassouloff.projetter.myelementsstring.ElementString;
import com.example.vladimirkarassouloff.projetter.myelementsstring.fonction.FonctionString;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vladimir on 15/02/2016.
 */
public class ElementFonction extends TextView implements DraggableElement {

    private FonctionString element;

    public ElementFonction(Context context, String name){
        super(context);
        this.setText(name);
        this.element = new FonctionString(name);
    }
    public ElementFonction(Context context, AttributeSet attrs, String name)
    {
        super(context, attrs);
        this.setText(name);
        this.element = new FonctionString(name);
    }



    @Override
    public List<View> onDraggedOnLine(View v) {
        List<View> ar = new ArrayList<View>();
        ProductionFonction pv = new ProductionFonction(getContext(),element.toString());
        ar.add(pv);
        return ar;
    }



    @Override
    public boolean isDropSupported(Production p) {
        return false;
    }

    @Override
    public ElementString onDraggedOnBlock(Production block) {
        return null;
    }

}
