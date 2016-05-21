package com.example.vladimirkarassouloff.projetter.ui.myelements.fonction.fonctionspeciale;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.example.vladimirkarassouloff.projetter.myelementsstring.fonction.special.Odometres;
import com.example.vladimirkarassouloff.projetter.ui.myelements.fonction.ElementFonction;
import com.example.vladimirkarassouloff.projetter.ui.myelementsproduction.Production;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vladimir on 20/05/2016.
 */
public class ElementOdometre extends ElementFonction {

    public ElementOdometre(Context context) {
        super(context, "Odometre");
    }

    public ElementOdometre(Context context, AttributeSet attrs) {
        super(context, attrs, "Odometre");
    }


    @Override
    public List<Production> onDraggedOnLine(View v) {
        List<Production> ar = new ArrayList<Production>();
        Production p = new Production(getContext(), new Odometres());
        ar.add(p);
        return ar;
    }

    @Override
    public void onDropOver(Production block) {

    }

    @Override
    public boolean isDropSupported(Production p) {
        return false;
    }
}