package com.example.vladimirkarassouloff.projetter.action;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import com.example.vladimirkarassouloff.projetter.ui.MyApp;

import java.util.ArrayList;

/**
 * Created by Vladimir on 22/05/2016.
 */
public class MoveLineAction extends Action {

    protected int oldLine;
    protected int newLine;

    public MoveLineAction(int oldLine, int newLine) {
        this.oldLine = oldLine;
        this.newLine = newLine;
    }

    @Override
    public void doAction() {
        Intent intent = new Intent("moveProduction");
        intent.putExtra("from",oldLine);
        intent.putExtra("to", newLine);
        LocalBroadcastManager.getInstance(MyApp.context).sendBroadcast(intent);
    }

    @Override
    public void undoAction() {
        Intent intent = new Intent("moveProduction");
        intent.putExtra("to",oldLine);
        intent.putExtra("from", newLine);
        LocalBroadcastManager.getInstance(MyApp.context).sendBroadcast(intent);
    }
}
