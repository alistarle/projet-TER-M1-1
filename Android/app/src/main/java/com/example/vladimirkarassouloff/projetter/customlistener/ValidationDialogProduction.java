package com.example.vladimirkarassouloff.projetter.customlistener;

import android.app.Dialog;
import android.view.View;

import com.example.vladimirkarassouloff.projetter.ui.myelementsproduction.Production;

/**
 * Created by Vladimir on 24/02/2016.
 */
public abstract class ValidationDialogProduction extends ValidationDialogListener {
    protected Production production;


    public ValidationDialogProduction(Dialog dialog, View promptsView, Production production) {
        super(dialog, promptsView);
        this.production = production;
    }


}
