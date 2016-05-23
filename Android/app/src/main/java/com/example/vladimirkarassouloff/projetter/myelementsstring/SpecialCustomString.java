package com.example.vladimirkarassouloff.projetter.myelementsstring;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vladimir on 20/05/2016.
 */
public class SpecialCustomString extends ElementString {
    public String specialValue;

    public SpecialCustomString(ArrayList<ElementString> components, String specialValue) {
        super(components);
        this.specialValue = specialValue;
    }

    public SpecialCustomString(String specialValue) {
        this.specialValue = specialValue;
    }

    @Override
    public String toString() {
        return specialValue;
    }
}
