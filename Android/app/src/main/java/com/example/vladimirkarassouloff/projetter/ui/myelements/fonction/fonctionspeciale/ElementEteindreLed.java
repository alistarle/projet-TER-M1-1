package com.example.vladimirkarassouloff.projetter.ui.myelements.fonction.fonctionspeciale;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.example.vladimirkarassouloff.projetter.myelementsstring.ElementString;
import com.example.vladimirkarassouloff.projetter.myelementsstring.fonction.special.EteindreLed;
import com.example.vladimirkarassouloff.projetter.ui.myelements.fonction.ElementFonction;
import com.example.vladimirkarassouloff.projetter.ui.myelementsproduction.Production;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vladimir on 20/05/2016.
 */
public class ElementEteindreLed extends ElementFonction {

    public ElementEteindreLed(Context context) {
        super(context, "EteindreLed");
    }

    public ElementEteindreLed(Context context, AttributeSet attrs) {
        super(context, attrs, "EteindreLed");
    }


    @Override
    public List<Production> onDraggedOnLine(View v) {
        List<Production> ar = new ArrayList<Production>();
        Production p = new Production(getContext(), new EteindreLed());
        ar.add(p);
        return ar;
    }

    @Override
    public void onDropOver(Production block) {

    }

    @Override
    public ElementString onDraggedOnBlock(Production block) {
        return null;
    }

    @Override
    public boolean isDropSupported(Production p) {
        return false;
    }

}
