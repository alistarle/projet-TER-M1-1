package com.example.vladimirkarassouloff.projetter.myelements.variable;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.example.vladimirkarassouloff.projetter.myelements.DraggableElement;
import com.example.vladimirkarassouloff.projetter.myelementsproduction.Production;
import com.example.vladimirkarassouloff.projetter.myelementsproduction.variable.ProductionVariable;
import com.example.vladimirkarassouloff.projetter.myelementsstring.ElementString;
import com.example.vladimirkarassouloff.projetter.myelementsstring.variable.VariableString;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vladimir on 15/02/2016.
 */
public class ElementVariable extends TextView implements DraggableElement {

    private String name;

    public ElementVariable(Context context, String name){
        super(context);
        this.setText(name);
        this.name = name;
    }
    public ElementVariable(Context context, AttributeSet attrs, String name)
    {
        super(context, attrs);
        this.setText(name);
        this.name = name;
    }



    @Override
    public List<View> onDraggedOnLine(View v) {
        List<View> ar = new ArrayList<View>();
        ProductionVariable pv = new ProductionVariable(getContext(),name);
        ar.add(pv);
        return ar;
    }


    @Override
    public boolean isDropSupported(Production p) {
        return p.supportDropVariable();
    }

    public ElementVariable(Context context, AttributeSet attrs){
        super(context, attrs);
    }

    @Override
    public ElementString onDraggedOnBlock(Production block) {
        return new VariableString(name);
    }
}
