package com.example.vladimirkarassouloff.projetter.customlistener;

import android.app.Dialog;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.widget.EditText;

import com.example.vladimirkarassouloff.projetter.R;
import com.example.vladimirkarassouloff.projetter.myelementsstring.fonction.FonctionInstanciationString;
import com.example.vladimirkarassouloff.projetter.ui.myelementsproduction.Production;
import com.example.vladimirkarassouloff.projetter.ui.myviews.prompt.PromptTypeVariableView;

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
        /*ProductionFonctionInstanciation pv = (ProductionFonctionInstanciation) this.production;
        pv.setType(ptv.getType());
        pv.setName(et.getText().toString());
        pv.refreshText();*/
        //Production pv = new Production()
        FonctionInstanciationString fis = (FonctionInstanciationString) production.getBasicElement();
        fis.setType(ptv.getType());
        fis.setName(et.getText().toString());
        //production.refreshText();


        Intent intent = new Intent("newFunction");
        intent.putExtra("function", et.getText().toString());
        LocalBroadcastManager.getInstance(promptsView.getContext()).sendBroadcast(intent);
        Intent intent2 = new Intent("autoIndent");
        LocalBroadcastManager.getInstance(promptsView.getContext()).sendBroadcast(intent2);
    }
}
