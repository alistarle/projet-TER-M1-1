package com.example.vladimirkarassouloff.projetter.customlistener;

import android.app.Dialog;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.example.vladimirkarassouloff.projetter.R;
import com.example.vladimirkarassouloff.projetter.myelementsstring.NumberString;
import com.example.vladimirkarassouloff.projetter.myelementsstring.SpecialCustomString;
import com.example.vladimirkarassouloff.projetter.myelementsstring.fonction.special.Moteur;
import com.example.vladimirkarassouloff.projetter.myelementsstring.fonction.special.TournerTete;

/**
 * Created by Vladimir on 20/05/2016.
 */
public class ValidationDialogMoteur extends ValidationDialogListener{
    private RadioGroup radioVitesse, radioDirection;
    private Moteur moteur;

    public ValidationDialogMoteur(Dialog dialog, View promptsView, Moteur moteur, RadioGroup rgDir) {
        super(dialog, promptsView);
        this.moteur = moteur;
        this.radioDirection = rgDir;
        //this.radioVitesse = rgVit;
    }


    @Override
    public void checkValid() throws Exception {

    }

    @Override
    public void onValid() {

       /* String valueSpeed = "";
        int selectedIdVit = radioVitesse.getCheckedRadioButtonId();
        if(selectedIdVit == R.id.radioLent){
            valueSpeed = "SLOW";
        }
        else if(selectedIdVit == R.id.radioMoyenne){
            valueSpeed = "MODERATE";
        }
        else{
            valueSpeed = "FAST";
        }
*/
        String valueDir = "";
        int selectedIdDir = radioDirection.getCheckedRadioButtonId();
        if(selectedIdDir == R.id.radioForward){
            //valueDir = "FORWARD";
            valueDir = "0";
        }
        else if(selectedIdDir == R.id.radioBack){
            //valueDir = "BACK";
            valueDir = "1";
        }
        else if(selectedIdDir == R.id.radioLeft){
            //valueDir = "LEFT";
            valueDir = "2";
        }
        else{
            //valueDir = "RIGHT";
            valueDir = "3";
        }


        moteur.add(new SpecialCustomString(valueDir));
        //moteur.add(new SpecialCustomString(valueSpeed));
        Intent intent = new Intent("autoIndent");
        LocalBroadcastManager.getInstance(promptsView.getContext()).sendBroadcast(intent);
    }
}
