package com.example.vladimirkarassouloff.projetter.ui.myviews.scrolldraggable;

import android.content.Context;
import android.util.AttributeSet;

import com.example.vladimirkarassouloff.projetter.ui.myelements.condition.ElementElse;
import com.example.vladimirkarassouloff.projetter.ui.myelements.condition.ElementElseIf;
import com.example.vladimirkarassouloff.projetter.ui.myelements.condition.ElementIf;
import com.example.vladimirkarassouloff.projetter.ui.myelements.iterative.ElementWhile;

/**
 * Created by Vladimir on 12/02/2016.
 */
public class ElementsView extends ScrollDraggableElementView {


    public ElementsView(Context context){
        super(context);
        //init();

    }

    public ElementsView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        //init();
    }

    @Override
    protected void init() {
        super.init();
        addBigHeader("Structures");



        this.addHeader("Conditionnelles : ");
        this.addDraggableElement(new ElementIf(getContext()));
        this.addDraggableElement(new ElementElseIf(getContext()));
        this.addDraggableElement(new ElementElse(getContext()));
        this.addBlankLine();

        this.addHeader("Iterative : ");
        this.addDraggableElement(new ElementWhile(getContext()));
        this.addBlankLine();

    }


}
