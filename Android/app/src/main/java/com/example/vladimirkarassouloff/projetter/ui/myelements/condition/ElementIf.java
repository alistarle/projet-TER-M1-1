package com.example.vladimirkarassouloff.projetter.ui.myelements.condition;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.example.vladimirkarassouloff.projetter.myelementsstring.BraceCloserString;
import com.example.vladimirkarassouloff.projetter.myelementsstring.condition.IfString;
import com.example.vladimirkarassouloff.projetter.ui.myelements.DraggableElement;
import com.example.vladimirkarassouloff.projetter.ui.myelementsproduction.Production;
import com.example.vladimirkarassouloff.projetter.myelementsstring.ElementString;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Vladimir on 12/02/2016.
 */
public class ElementIf extends TextView implements DraggableElement {

    public ElementIf(Context context){
        super(context);
        this.setText(" if ( ){ } ");
    }
    public ElementIf(Context context, AttributeSet attrs){
        super(context, attrs);
    }

    @Override
    public List<Production> onDraggedOnLine(View v){
        List<Production> array = new ArrayList<Production>();
        /*ProductionIf tv = new ProductionIf(v.getContext());
        ProductionBraceCloser pb = new ProductionBraceCloser(v.getContext());
        array.add(tv);
        array.add(pb);*/
        Production p1 = new Production(v.getContext(),new IfString());
        Production p2 = new Production(v.getContext(),new BraceCloserString());
        array.add(p1);
        array.add(p2);
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

}
