package com.example.vladimirkarassouloff.projetter.myelementsstring;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vladimir on 17/02/2016.
 */
public class ElementString {
    public List<ElementString> components;

    public ElementString(List<ElementString> components) {
        this.components = components;
    }
    public ElementString(){
        this.components = new ArrayList<>();
    }

    public void add(ElementString es){
        components.add(es);
    }

    public void getAllComponent(List<ElementString> array,boolean includeSelf){
        if(includeSelf){
            array.add(this);
        }
        for(ElementString es : components){
            es.getAllComponent(array,true);
        }
    }
}
