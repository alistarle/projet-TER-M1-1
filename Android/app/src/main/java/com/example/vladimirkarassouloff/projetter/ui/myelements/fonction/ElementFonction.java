package com.example.vladimirkarassouloff.projetter.ui.myelements.fonction;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.example.vladimirkarassouloff.projetter.ui.myelements.DraggableElement;
import com.example.vladimirkarassouloff.projetter.ui.myelementsproduction.Production;
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
    public List<Production> onDraggedOnLine(View v) {
        List<Production> ar = new ArrayList<Production>();
        Production p = new Production(getContext(), new FonctionString(element.getName()));
        ar.add(p);
        return ar;
    }



    @Override
    public boolean isDropSupported(Production p) {
        return p.supportDropFonction();
    }

    @Override
    public ElementString onDraggedOnBlock(Production block) {
        return new FonctionString(element.getName());
    }

    @Override
    public void onDropOver(final Production block) {

    }
}
