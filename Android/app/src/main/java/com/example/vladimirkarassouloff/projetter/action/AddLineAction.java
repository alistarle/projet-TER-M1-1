package com.example.vladimirkarassouloff.projetter.action;

import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.vladimirkarassouloff.projetter.ui.myelementsproduction.Production;
import com.example.vladimirkarassouloff.projetter.ui.myviews.AlgoView;

import java.util.List;

/**
 * Created by Vladimir on 20/05/2016.
 */
public class AddLineAction extends Action {

    public int line;
    public List<View> prod;
    public ViewGroup view;
    public AlgoView algoView;

    public AddLineAction(int line, List<View> prod, ViewGroup view,AlgoView algoView) {
        this.line = line;
        this.prod = prod;
        this.view = view;
        this.algoView = algoView;
    }

    @Override
    public void doAction() {
        int i = line;
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        //lp.setMargins(5, 5, 5, 15);
        for(View production : prod) {
            production.setLayoutParams(lp);
            if(production instanceof Production){
                Production p = (Production) production;
                p.refreshText();
            }
            view.addView(production,i);
            i++;
        }
        algoView.autoIndent();
    }

    @Override
    public void undoAction() {
        for(View production : prod) {
            view.removeView(production);
        }
    }
}
