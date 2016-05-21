package com.example.vladimirkarassouloff.projetter.ui.myelements.fonction;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.example.vladimirkarassouloff.projetter.myelementsstring.ElementString;
import com.example.vladimirkarassouloff.projetter.myelementsstring.fonction.FonctionString;
import com.example.vladimirkarassouloff.projetter.myelementsstring.fonction.ReturnString;
import com.example.vladimirkarassouloff.projetter.ui.myelements.DraggableElement;
import com.example.vladimirkarassouloff.projetter.ui.myelementsproduction.Production;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vladimir on 20/05/2016.
 */
public class ElementReturn extends TextView implements DraggableElement {


    public ElementReturn (Context context) {
        super(context);
        this.setText("Return");
    }
    public ElementReturn(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        this.setText("Return");
    }



    @Override
    public List<View> onDraggedOnLine(View v) {
        List<View> ar = new ArrayList<View>();
        /*ProductionFonction pv = new ProductionFonction(getContext(),element.toString());
        ar.add(pv);*/
        Production p = new Production(getContext(), new ReturnString());
        ar.add(p);
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

    @Override
    public void onDropOver(final Production block) {

    }
}