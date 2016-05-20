package com.example.vladimirkarassouloff.projetter.customlistener;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.widget.EditText;

import com.example.vladimirkarassouloff.projetter.myelementsstring.NumberString;
import com.example.vladimirkarassouloff.projetter.myelementsstring.fonction.special.TournerTete;
import com.example.vladimirkarassouloff.projetter.network.NetworkInfo;
import com.example.vladimirkarassouloff.projetter.ui.AlgoActivity;

/**
 * Created by Vladimir on 20/05/2016.
 */
public class ValidationDialogRotation extends ValidationDialogListener{
    private EditText angle;
    private TournerTete tournerTete;

    public ValidationDialogRotation(Dialog dialog, View promptsView, TournerTete tournerTete, EditText angle) {
        super(dialog, promptsView);
        this.angle = angle;
        this.tournerTete = tournerTete;
    }


    @Override
    public void checkValid() throws Exception {
        try{
            int i = Integer.parseInt(angle.getText().toString());
            if(i < 0 || i >=180){
                throw new Exception("L'angle doit etre entre 0 et 180°");
            }
        }
        catch (Exception exc){
            throw new Exception("Angle incorrect");
        }

    }

    @Override
    public void onValid() {
        tournerTete.add(new NumberString(angle.getText().toString()));
        Intent intent = new Intent("autoIndent");
        LocalBroadcastManager.getInstance(promptsView.getContext()).sendBroadcast(intent);
    }
}
