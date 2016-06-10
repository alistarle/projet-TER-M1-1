package com.example.vladimirkarassouloff.projetter.customlistener;

import android.app.Dialog;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.widget.EditText;

import com.example.vladimirkarassouloff.projetter.R;
import com.example.vladimirkarassouloff.projetter.myelementsstring.NumberString;
import com.example.vladimirkarassouloff.projetter.myelementsstring.fonction.FonctionInstanciationString;
import com.example.vladimirkarassouloff.projetter.ui.myelementsproduction.Production;
import com.example.vladimirkarassouloff.projetter.ui.myviews.prompt.PromptTypeVariableView;

/**
 * Created by Vladimir on 17/02/2016.
 */
public class ValidationDialogFunction  extends ValidationDialogProduction {

    protected EditText et;
    protected PromptTypeVariableView ptv;
    protected Production returnProd;

    public ValidationDialogFunction(Dialog dialog, View promptsView,Production p,Production returnProd) {
        super(dialog, promptsView,p);
        this.et = (EditText) promptsView.findViewById(R.id.editTextFunctionInput);
        this.ptv = (PromptTypeVariableView) promptsView.findViewById(R.id.promptviewtypefunction);
        this.returnProd = returnProd;
    }

    @Override
    public void checkValid() throws Exception{
        if(et.getText().length() == 0){
            throw new Exception("Donnez un nom a la fonction !");
        }
        else if(Character.isDigit(et.getText().charAt(0))){
            throw new Exception("La premiere lettre ne peut pas etre un nombre");
        }
        else if(!isNameCorrect(et.getText().toString())){
            throw new Exception("Symbole interdits");
        }
    }

    protected boolean isNameCorrect(String s){
        for(int i = 0 ; i < s.length() ; i++){
            if(!Character.isDigit(s.charAt(i)) && !Character.isLetter(s.charAt(i))){
                return false;
            }
        }
        return true;
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


        if(fis.getType().equals("int")){
            returnProd.addComponent(new NumberString("0"));
        }

        Intent intent = new Intent("newFunction");
        intent.putExtra("function", et.getText().toString());
        LocalBroadcastManager.getInstance(promptsView.getContext()).sendBroadcast(intent);
        Intent intent2 = new Intent("autoIndent");
        LocalBroadcastManager.getInstance(promptsView.getContext()).sendBroadcast(intent2);


    }
}
