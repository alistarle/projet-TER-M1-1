package com.example.vladimirkarassouloff.projetter.ui.myviews.scrolldraggable;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.example.vladimirkarassouloff.projetter.myelementsstring.fonction.special.TournerTete;
import com.example.vladimirkarassouloff.projetter.ui.myelements.ElementNumber;
import com.example.vladimirkarassouloff.projetter.ui.myelements.fonction.fonctionspeciale.ElementAllumerLed;
import com.example.vladimirkarassouloff.projetter.ui.myelements.fonction.fonctionspeciale.ElementEteindreLed;
import com.example.vladimirkarassouloff.projetter.ui.myelements.fonction.fonctionspeciale.ElementMoteur;
import com.example.vladimirkarassouloff.projetter.ui.myelements.fonction.fonctionspeciale.ElementTournerTete;
import com.example.vladimirkarassouloff.projetter.ui.myelements.logic.ElementLogicAnd;
import com.example.vladimirkarassouloff.projetter.ui.myelements.logic.ElementLogicEq;
import com.example.vladimirkarassouloff.projetter.ui.myelements.logic.ElementLogicInf;
import com.example.vladimirkarassouloff.projetter.ui.myelements.logic.ElementLogicInfEq;
import com.example.vladimirkarassouloff.projetter.ui.myelements.logic.ElementLogicNot;
import com.example.vladimirkarassouloff.projetter.ui.myelements.logic.ElementLogicNotEq;
import com.example.vladimirkarassouloff.projetter.ui.myelements.logic.ElementLogicOr;
import com.example.vladimirkarassouloff.projetter.ui.myelements.logic.ElementLogicSup;
import com.example.vladimirkarassouloff.projetter.ui.myelements.logic.ElementLogicSupEq;
import com.example.vladimirkarassouloff.projetter.ui.myelements.operator.ElementDivideOperator;
import com.example.vladimirkarassouloff.projetter.ui.myelements.operator.ElementEqualOperator;
import com.example.vladimirkarassouloff.projetter.ui.myelements.operator.ElementMinusOperator;
import com.example.vladimirkarassouloff.projetter.ui.myelements.operator.ElementMultiplyOperator;
import com.example.vladimirkarassouloff.projetter.ui.myelements.operator.ElementPlusOperator;

/**
 * Created by Vladimir on 20/05/2016.
 */
public class SpecialFonctionView extends ScrollDraggableElementView {

    private View headerLed;
    private View headerMoteur;
    private View headerServo;
    private View headerInfrarouge;
    private View headerOdometres;
    private View headerUltrason;

    public SpecialFonctionView(Context context){
        super(context);

    }

    public SpecialFonctionView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }


    @Override
    protected void init() {
        super.init();

        this.addBigHeader("Fonctions spéciales");


        headerLed = this.addHeader("AllumerLed : ");
        this.addDraggableElementAfter(headerLed,new ElementAllumerLed(getContext()));
        this.addDraggableElementAfter(headerLed,new ElementEteindreLed(getContext()));
        this.addBlankLine();

        headerMoteur = this.addHeader("Moteur : ");
        this.addDraggableElementAfter(headerMoteur,new ElementMoteur(getContext()));
        this.addBlankLine();

        headerServo = this.addHeader("TournerTete : ");
        this.addDraggableElementAfter(headerServo,new ElementTournerTete(getContext()));
        this.addBlankLine();


        headerInfrarouge = this.addHeader("Infrarouge : ");
        this.addDraggableElementAfter(headerInfrarouge,new ElementLogicInf(getContext()));
        this.addBlankLine();

        headerOdometres = this.addHeader("Odometre : ");
        this.addDraggableElementAfter(headerInfrarouge,new ElementLogicInf(getContext()));
        this.addBlankLine();

        headerUltrason = this.addHeader("Ultrason : ");
        this.addDraggableElementAfter(headerInfrarouge,new ElementLogicInf(getContext()));
        this.addBlankLine();
    }

}
