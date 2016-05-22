package com.example.vladimirkarassouloff.projetter.action;

import java.io.Serializable;

/**
 * Created by Vladimir on 20/05/2016.
 */
public abstract class Action implements Serializable {

    public abstract void doAction();
    public abstract void undoAction();

}
