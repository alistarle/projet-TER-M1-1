package com.example.vladimirkarassouloff.projetter.ui.myelements.logic;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.example.vladimirkarassouloff.projetter.ui.myelements.DraggableElement;
import com.example.vladimirkarassouloff.projetter.ui.myelementsproduction.Production;
import com.example.vladimirkarassouloff.projetter.myelementsstring.ElementString;
import com.example.vladimirkarassouloff.projetter.myelementsstring.logic.LogicString;

import java.util.List;

/**
 * Created by Vladimir on 17/02/2016.
 */
public abstract class ElementLogic extends TextView implements DraggableElement {

    protected LogicString element;

    public ElementLogic(Context context, LogicString op) {
        super(context);
        this.element = op;
        this.setText(element.getOperator());
    }

    public ElementLogic(Context context, AttributeSet attrs, LogicString op) {
        super(context, attrs);
        this.element = op;
        this.setText(element.getOperator());

    }


    @Override
    public boolean isDropSupported(Production p) {
        return p.supportDropLogic(this.element);
    }

    @Override
    public ElementString onDraggedOnBlock(Production block) {
        return element;
    }



    @Override
    public List<Production> onDraggedOnLine(View v) {
        return null;
    }

    @Override
    public void onDropOver(final Production block) {

    }

    @Override
    public boolean isDraggableOnLine() {
        return false;
    }


}
