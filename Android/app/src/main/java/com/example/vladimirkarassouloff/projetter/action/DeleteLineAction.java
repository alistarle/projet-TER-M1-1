package com.example.vladimirkarassouloff.projetter.action;

import android.os.Parcel;
import android.view.View;
import android.view.ViewGroup;

import com.example.vladimirkarassouloff.projetter.ui.myelementsproduction.Production;
import com.example.vladimirkarassouloff.projetter.ui.myviews.AlgoView;

import java.util.List;

/**
 * Created by Vladimir on 20/05/2016.
 */
public class DeleteLineAction extends AddLineAction {

    public DeleteLineAction(int line, List<Production> prod) {
        super(line, prod);
    }

    @Override
    public void doAction() {
        super.undoAction();
    }

    @Override
    public void undoAction() {
        super.doAction();
    }
}
