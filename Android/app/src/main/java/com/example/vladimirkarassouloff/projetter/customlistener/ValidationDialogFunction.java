package com.example.vladimirkarassouloff.projetter.customlistener;

import android.app.Dialog;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.widget.EditText;

import com.example.vladimirkarassouloff.projetter.R;
import com.example.vladimirkarassouloff.projetter.myelementsproduction.Production;
import com.example.vladimirkarassouloff.projetter.myelementsproduction.fonction.ProductionFonction;
import com.example.vladimirkarassouloff.projetter.myelementsproduction.fonction.ProductionFonctionInstanciation;
import com.example.vladimirkarassouloff.projetter.myelementsproduction.variable.ProductionVariableInstanciation;
import com.example.vladimirkarassouloff.projetter.myviews.prompt.PromptTypeVariableView;

/**
 * Created by Vladimir on 17/02/2016.
 */
public class ValidationDialogFunction extends ValidationDialogVariable {
    public ValidationDialogFunction(Dialog dialog, View promptsView,Production p) {
        super(dialog, promptsView,p);
        this.et = (EditText) promptsView.findViewById(R.id.editTextFunctionInput);
        this.ptv = (PromptTypeVariableView) promptsView.findViewById(R.id.promptviewtypefunction);

    }
    @Override
    public void onValid() {
        ProductionFonctionInstanciation pv = (ProductionFonctionInstanciation) this.production;
        pv.setType(ptv.getType());
        pv.setName(et.getText().toString());
        pv.refreshText();
        Intent intent = new Intent("newFunction");
        intent.putExtra("function", et.getText().toString());
        LocalBroadcastManager.getInstance(promptsView.getContext()).sendBroadcast(intent);
    }
}
