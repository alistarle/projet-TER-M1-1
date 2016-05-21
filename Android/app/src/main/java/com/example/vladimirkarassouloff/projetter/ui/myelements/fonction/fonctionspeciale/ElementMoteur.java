package com.example.vladimirkarassouloff.projetter.ui.myelements.fonction.fonctionspeciale;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.example.vladimirkarassouloff.projetter.R;
import com.example.vladimirkarassouloff.projetter.customlistener.ValidationDialogMoteur;
import com.example.vladimirkarassouloff.projetter.customlistener.ValidationDialogRotation;
import com.example.vladimirkarassouloff.projetter.myelementsstring.ElementString;
import com.example.vladimirkarassouloff.projetter.myelementsstring.fonction.special.Moteur;
import com.example.vladimirkarassouloff.projetter.myelementsstring.fonction.special.TournerTete;
import com.example.vladimirkarassouloff.projetter.ui.MyApp;
import com.example.vladimirkarassouloff.projetter.ui.myelements.fonction.ElementFonction;
import com.example.vladimirkarassouloff.projetter.ui.myelementsproduction.Production;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vladimir on 20/05/2016.
 */

public class ElementMoteur  extends ElementFonction {

    public ElementMoteur(Context context) {
        super(context, "Moteur");
    }

    public ElementMoteur(Context context, AttributeSet attrs) {
        super(context, attrs, "Moteur");
    }


    @Override
    public List<View> onDraggedOnLine(View v) {
        List<View> ar = new ArrayList<View>();
        Moteur moteur = new Moteur();
        Production p = new Production(getContext(), moteur);
        ar.add(p);

        LayoutInflater li = LayoutInflater.from(getContext());
        View promptsView = li.inflate(R.layout.prompt_moteur, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
        // set prompts.xml to alertdialog builder
        alertDialogBuilder.setView(promptsView);
        final RadioGroup vitesse = (RadioGroup) promptsView.findViewById(R.id.radioVitesse);
        final RadioGroup direction = (RadioGroup) promptsView.findViewById(R.id.radioDirection);
        // set dialog message
        alertDialogBuilder.setCancelable(false).setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent intent = new Intent("removeLastAction");
                                LocalBroadcastManager.getInstance(MyApp.context).sendBroadcast(intent);
                                dialog.cancel();
                            }
                        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

        Button theButton = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
        theButton.setOnClickListener(new ValidationDialogMoteur(alertDialog, promptsView,moteur, direction,vitesse));

        return ar;
    }

    @Override
    public void onDropOver(Production block) {

    }

    @Override
    public ElementString onDraggedOnBlock(Production block) {
        return null;
    }

    @Override
    public boolean isDropSupported(Production p) {
        return false;
    }

}
