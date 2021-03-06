package com.example.vladimirkarassouloff.projetter.ui.myelements.condition;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.example.vladimirkarassouloff.projetter.myelementsstring.BraceCloserString;
import com.example.vladimirkarassouloff.projetter.myelementsstring.condition.ElseString;
import com.example.vladimirkarassouloff.projetter.ui.myelements.DraggableElement;
import com.example.vladimirkarassouloff.projetter.ui.myelementsproduction.Production;
import com.example.vladimirkarassouloff.projetter.myelementsstring.ElementString;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vladimir on 13/02/2016.
 */
public class ElementElse extends TextView implements DraggableElement {

    public ElementElse(Context context){
        super(context);
        this.setText(" else{ } ");
    }
    public ElementElse(Context context, AttributeSet attrs){
        super(context, attrs);
    }

    @Override
    public List<Production> onDraggedOnLine(View v){
        List<Production> array = new ArrayList<Production>();
        //ProductionElse tv = new ProductionElse(v.getContext());
        Production p1 = new Production(v.getContext(),new ElseString());
        //ProductionBraceCloser pb = new ProductionBraceCloser(v.getContext());
        Production p2 = new Production(v.getContext(),new BraceCloserString());
        array.add(p1);
        //array.add(tv);
        array.add(p2);
        //array.add(pb);
        return array;
    }


    @Override
    public ElementString onDraggedOnBlock(Production block) {
        return null;
    }


    @Override
    public boolean isDropSupported(Production p) {
        return false;
    }

    @Override
    public void onDropOver(final Production block) {

    }

    @Override
    public boolean isDraggableOnLine() {
        return true;
    }


}