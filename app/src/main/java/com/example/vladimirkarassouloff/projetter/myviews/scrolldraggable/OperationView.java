package com.example.vladimirkarassouloff.projetter.myviews.scrolldraggable;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.example.vladimirkarassouloff.projetter.myelements.ElementNumber;
import com.example.vladimirkarassouloff.projetter.myelements.logic.ElementLogicAnd;
import com.example.vladimirkarassouloff.projetter.myelements.logic.ElementLogicEq;
import com.example.vladimirkarassouloff.projetter.myelements.logic.ElementLogicInf;
import com.example.vladimirkarassouloff.projetter.myelements.logic.ElementLogicInfEq;
import com.example.vladimirkarassouloff.projetter.myelements.logic.ElementLogicNot;
import com.example.vladimirkarassouloff.projetter.myelements.logic.ElementLogicNotEq;
import com.example.vladimirkarassouloff.projetter.myelements.logic.ElementLogicOr;
import com.example.vladimirkarassouloff.projetter.myelements.logic.ElementLogicSup;
import com.example.vladimirkarassouloff.projetter.myelements.logic.ElementLogicSupEq;
import com.example.vladimirkarassouloff.projetter.myelements.operator.ElementDivideOperator;
import com.example.vladimirkarassouloff.projetter.myelements.operator.ElementEqualOperator;
import com.example.vladimirkarassouloff.projetter.myelements.operator.ElementMinusOperator;
import com.example.vladimirkarassouloff.projetter.myelements.operator.ElementMultiplyOperator;
import com.example.vladimirkarassouloff.projetter.myelements.operator.ElementPlusOperator;

/**
 * Created by Vladimir on 15/02/2016.
 */
public class OperationView extends ScrollDraggableElementView {

    private View headerLogic;
    private View headerCalcul;

    public OperationView(Context context){
        super(context);

    }

    public OperationView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }


    @Override
    protected void init() {
        super.init();

        this.addBigHeader("Operations");


        headerCalcul = this.addHeader("Calcul : ");
        this.addDraggableElementAfter(headerCalcul,new ElementDivideOperator(getContext()));
        this.addDraggableElementAfter(headerCalcul,new ElementMultiplyOperator(getContext()));
        this.addDraggableElementAfter(headerCalcul,new ElementMinusOperator(getContext()));
        this.addDraggableElementAfter(headerCalcul,new ElementPlusOperator(getContext()));
        this.addDraggableElementAfter(headerCalcul,new ElementEqualOperator(getContext()));
        this.addDraggableElementAfter(headerCalcul,new ElementNumber(getContext()));

        this.addBlankLine();

        headerLogic = this.addHeader("Logique : ");

        this.addDraggableElementAfter(headerLogic,new ElementLogicInf(getContext()));
        this.addDraggableElementAfter(headerLogic,new ElementLogicInfEq(getContext()));
        this.addDraggableElementAfter(headerLogic,new ElementLogicSup(getContext()));
        this.addDraggableElementAfter(headerLogic,new ElementLogicSupEq(getContext()));
        this.addDraggableElementAfter(headerLogic,new ElementLogicNot(getContext()));
        this.addDraggableElementAfter(headerLogic,new ElementLogicOr(getContext()));
        this.addDraggableElementAfter(headerLogic,new ElementLogicAnd(getContext()));
        this.addDraggableElementAfter(headerLogic,new ElementLogicNotEq(getContext()));
        this.addDraggableElementAfter(headerLogic,new ElementLogicEq(getContext()));

        this.addBlankLine();

    }






}
