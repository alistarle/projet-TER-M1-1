package com.example.vladimirkarassouloff.projetter.ui.myelements.iterative;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.example.vladimirkarassouloff.projetter.myelementsstring.BraceCloserString;
import com.example.vladimirkarassouloff.projetter.myelementsstring.ElementString;
import com.example.vladimirkarassouloff.projetter.myelementsstring.iterative.WhileString;
import com.example.vladimirkarassouloff.projetter.ui.myelements.DraggableElement;
import com.example.vladimirkarassouloff.projetter.ui.myelementsproduction.Production;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vladimir on 21/05/2016.
 */
public class ElementWhile  extends TextView implements DraggableElement {

    public ElementWhile(Context context){
        super(context);
        this.setText("While");
    }
    public ElementWhile(Context context, AttributeSet attrs){
        super(context, attrs);
    }

    @Override
    public List<Production> onDraggedOnLine(View v){
        List<Production> array = new ArrayList<Production>();
        Production p1 = new Production(v.getContext(),new WhileString());
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

    @Override
    public boolean isDraggableOnLine() {
        return true;
    }
}
