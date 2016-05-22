package com.example.vladimirkarassouloff.projetter.action;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import com.example.vladimirkarassouloff.projetter.myelementsstring.ElementString;
import com.example.vladimirkarassouloff.projetter.ui.MyApp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vladimir on 20/05/2016.
 */
public class ModifyProductionAction extends Action{


    ElementString element;
    ArrayList<ElementString> newComponents;
    ArrayList<ElementString> oldComponents;


    public ModifyProductionAction(ElementString element,ArrayList<ElementString> newComponents) {
        this.newComponents = newComponents;
        this.element = element;
        this.oldComponents = element.components;
    }

    @Override
    public void doAction() {
        this.element.components = newComponents;
        Intent intent = new Intent("autoIndent");
        LocalBroadcastManager.getInstance(MyApp.context).sendBroadcast(intent);
    }

    @Override
    public void undoAction() {
        this.element.components = oldComponents;
        Intent intent = new Intent("autoIndent");
        LocalBroadcastManager.getInstance(MyApp.context).sendBroadcast(intent);
    }
}
