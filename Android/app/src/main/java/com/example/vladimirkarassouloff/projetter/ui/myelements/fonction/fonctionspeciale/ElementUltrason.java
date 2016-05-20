package com.example.vladimirkarassouloff.projetter.ui.myelements.fonction.fonctionspeciale;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.example.vladimirkarassouloff.projetter.myelementsstring.ElementString;
import com.example.vladimirkarassouloff.projetter.myelementsstring.fonction.special.Odometres;
import com.example.vladimirkarassouloff.projetter.myelementsstring.fonction.special.Ultrasons;
import com.example.vladimirkarassouloff.projetter.ui.myelements.fonction.ElementFonction;
import com.example.vladimirkarassouloff.projetter.ui.myelementsproduction.Production;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vladimir on 20/05/2016.
 */
public class ElementUltrason extends ElementFonction {

    public ElementUltrason(Context context) {
        super(context, "Ultrasons");
    }

    public ElementUltrason(Context context, AttributeSet attrs) {
        super(context, attrs, "Ultrasons");
    }


    @Override
    public List<View> onDraggedOnLine(View v) {
        List<View> ar = new ArrayList<View>();
        Production p = new Production(getContext(), new Ultrasons());
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