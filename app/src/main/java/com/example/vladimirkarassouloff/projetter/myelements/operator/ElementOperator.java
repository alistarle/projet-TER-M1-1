package com.example.vladimirkarassouloff.projetter.myelements.operator;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.example.vladimirkarassouloff.projetter.myelements.DraggableElement;
import com.example.vladimirkarassouloff.projetter.myelementsproduction.Production;
import com.example.vladimirkarassouloff.projetter.myelementsproduction.condition.ProductionIf;
import com.example.vladimirkarassouloff.projetter.myelementsstring.ElementString;
import com.example.vladimirkarassouloff.projetter.myelementsstring.operator.OperatorString;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vladimir on 14/02/2016.
 */
public class ElementOperator extends TextView implements DraggableElement {

    protected OperatorString op;

    public ElementOperator(Context context,OperatorString op){
        super(context);
        this.setText(op.toString());
        this.op = op;
    }
    public ElementOperator(Context context, AttributeSet attrs){
        super(context, attrs);
    }



    @Override
    public List<View> onDraggedOnLine(View v){
        return null;
    }

    @Override
    public boolean isDropSupported(Production p) {
        return p.supportDropOperator();
    }

    @Override
    public ElementString onDraggedOnBlock(Production block) {
        return op;
    }

}
