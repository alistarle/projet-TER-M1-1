package com.example.vladimirkarassouloff.projetter.ui.myelements.condition;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.example.vladimirkarassouloff.projetter.ui.myelements.DraggableElement;
import com.example.vladimirkarassouloff.projetter.ui.myelementsproduction.Production;
import com.example.vladimirkarassouloff.projetter.ui.myelementsproduction.ProductionBraceCloser;
import com.example.vladimirkarassouloff.projetter.ui.myelementsproduction.condition.ProductionIf;
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
    public List<View> onDraggedOnLine(View v){
        List<View> array = new ArrayList<View>();
        ProductionIf tv = new ProductionIf(v.getContext());
        ProductionBraceCloser pb = new ProductionBraceCloser(v.getContext());
        array.add(tv);
        array.add(pb);
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

}
