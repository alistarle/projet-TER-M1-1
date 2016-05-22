package com.example.vladimirkarassouloff.projetter.customlistener;

import android.app.Dialog;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.widget.EditText;

import com.example.vladimirkarassouloff.projetter.R;
import com.example.vladimirkarassouloff.projetter.ui.myelementsproduction.Production;
import com.example.vladimirkarassouloff.projetter.myelementsstring.NumberString;

/**
 * Created by Vladimir on 24/02/2016.
 */
public class ValidationDialogNumber extends ValidationDialogProduction {
    protected NumberString es;
    protected EditText et;
    public ValidationDialogNumber(Dialog dialog, View promptsView, Production container,NumberString es) {
        super(dialog, promptsView,container);
        this.es = es;
        this.et = (EditText) promptsView.findViewById(R.id.editTextNumberInput);
    }

    @Override
    public void checkValid() throws Exception{

    }
    @Override
    public void onValid(){
        es.setNombre(et.getText().toString());
        Intent intent = new Intent("autoIndent");
        LocalBroadcastManager.getInstance(promptsView.getContext()).sendBroadcast(intent);
    }
}
