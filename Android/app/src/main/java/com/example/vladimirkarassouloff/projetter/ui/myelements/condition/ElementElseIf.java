package com.example.vladimirkarassouloff.projetter.ui.myelements.condition;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.example.vladimirkarassouloff.projetter.myelementsstring.BraceCloserString;
import com.example.vladimirkarassouloff.projetter.myelementsstring.condition.ElseIfString;
import com.example.vladimirkarassouloff.projetter.ui.myelementsproduction.Production;
import com.example.vladimirkarassouloff.projetter.myelementsstring.ElementString;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vladimir on 13/02/2016.
 */

public class ElementElseIf extends ElementIf{

    public ElementElseIf(Context context){
        super(context);
        this.setText(" else if ( ){ } ");
    }
    public ElementElseIf(Context context, AttributeSet attrs){
        super(context, attrs);
    }

    @Override
    public List<View> onDraggedOnLine(View v){
        List<View> array = new ArrayList<View>();
        /*ProductionElseIf pe = new ProductionElseIf(v.getContext());
        ProductionBraceCloser pb = new ProductionBraceCloser(v.getContext());
        array.add(pe);
        array.add(pb);*/
        Production p1 = new Production(v.getContext(), new ElseIfString());
        Production p2 = new Production(v.getContext(), new BraceCloserString());
        array.add(p1);
        array.add(p2);
        return array;
    }

    @Override
    public ElementString onDraggedOnBlock(Production block) {
        return null;
    }

}