package com.example.vladimirkarassouloff.projetter.ui.myviews.prompt;

import android.content.Context;
import android.util.AttributeSet;

import java.util.ArrayList;

/**
 * Created by Vladimir on 16/02/2016.
 */
public class PromptTypeFunctionView extends PromptTypeVariableView {

    static {
        TYPES.add("void");
    }


    public PromptTypeFunctionView(Context context) {
        super(context);
    }

    public PromptTypeFunctionView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void initTypes(){
        myTypes = new ArrayList<String>();
        for(String str : TYPES){
            myTypes.add(str);
        }
    }

}
