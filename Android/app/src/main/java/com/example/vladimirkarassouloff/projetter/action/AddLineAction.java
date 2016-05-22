package com.example.vladimirkarassouloff.projetter.action;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.util.ArrayMap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.vladimirkarassouloff.projetter.myelementsstring.ElementString;
import com.example.vladimirkarassouloff.projetter.ui.MyApp;
import com.example.vladimirkarassouloff.projetter.ui.myelementsproduction.Production;
import com.example.vladimirkarassouloff.projetter.ui.myviews.AlgoView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vladimir on 20/05/2016.
 */
public class AddLineAction extends Action {

    public int line;
    public List<ElementString> elementString;
    //public ViewGroup view;
    //public AlgoView algoView;

    /*public AddLineAction(int line, List<> elementString) {
        this.line = line;
        this.elementString = elementString;
    }*/

    public AddLineAction(int line, List<Production> productions) {
        this.line = line;
        elementString = new ArrayList<>();
        for(Production v : productions){
            elementString.add(v.getBasicElement());
        }
    }

    @Override
    public void doAction() {
        Intent intent = new Intent("addProductions");
        intent.putExtra("line",line);
        intent.putExtra("elements", (ArrayList)elementString);
        LocalBroadcastManager.getInstance(MyApp.context).sendBroadcast(intent);
    }

    @Override
    public void undoAction() {
        Intent intent = new Intent("removeProductions");
        intent.putExtra("line",line);
        intent.putExtra("elements", (ArrayList)elementString);
        LocalBroadcastManager.getInstance(MyApp.context).sendBroadcast(intent);
    }
}
