package com.example.vladimirkarassouloff.projetter.ui.myviews.prompt;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;

import com.example.vladimirkarassouloff.projetter.ui.MyApp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vladimir on 15/02/2016.
 */
public class PromptTypeVariableView extends ScrollView {

    protected static BroadcastReceiver onNotice;
    protected static List<String> TYPES = new ArrayList<String>();

    protected List<String> myTypes;
    protected RadioGroup rg;

    static {
        TYPES.add("int");
        TYPES.add("double");
        onNotice = new BroadcastReceiver() {

            @Override
            public void onReceive(Context context, Intent intent) {
                // intent can contain anydata
                if(intent.getAction().equals("newType")){
                    addType(intent.getStringExtra("variable"));
                }

            }
        };
        LocalBroadcastManager.getInstance(MyApp.context).registerReceiver(onNotice, new IntentFilter("newType"));
    }




    public PromptTypeVariableView(Context context){
        super(context);
        this.init();
    }
    public PromptTypeVariableView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        this.init();
    }

    public PromptTypeVariableView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        if (!isInEditMode()) {
            init();
        }
    }


    protected void initTypes(){
        myTypes = new ArrayList<String>();
        for(String str : TYPES){
            if(!str.equals("void")){
                myTypes.add(str);
            }
        }
    }


    private void init() {
        if(!isInEditMode()) {
            this.initTypes();
            rg = new RadioGroup(getContext());
            for (String str : myTypes) {
                addRadioButton(str);
            }
            this.addView(rg);
            this.checkFirst();
        }
    }
    private void checkFirst(){
        if(rg.getChildCount() != 0){
            RadioButton rb = (RadioButton) rg.getChildAt(0);
            rb.setChecked(true);
        }
    }

    private void addRadioButton(String str){
        RadioButton button = new RadioButton(getContext());
        button.setText(str);
        rg.addView(button);

    }

    private static void addType(String str){
        if(!PromptTypeVariableView.TYPES.contains(str)){
            PromptTypeVariableView.TYPES.add(str);
        }
    }

    public String getType(){
        int radioButtonID = rg.getCheckedRadioButtonId();
        View radioButton = rg.findViewById(radioButtonID);
        int idx = rg.indexOfChild(radioButton);
        RadioButton r = (RadioButton)  rg.getChildAt(idx);
        String selectedtext = r.getText().toString();
        return selectedtext;
    }



}
