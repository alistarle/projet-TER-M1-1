package com.example.vladimirkarassouloff.projetter.ui.myelements;

import android.view.View;

import com.example.vladimirkarassouloff.projetter.ui.myelementsproduction.Production;
import com.example.vladimirkarassouloff.projetter.myelementsstring.ElementString;

import java.util.List;

/**
 * Created by Vladimir on 12/02/2016.
 */
public interface DraggableElement {

    public List<View> onDraggedOnLine(View v);
    public ElementString onDraggedOnBlock(Production block);
    public boolean isDropSupported(Production p);
}
