package com.example.vladimirkarassouloff.projetter.ui.myviews.prompt;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.vladimirkarassouloff.projetter.R;
import com.example.vladimirkarassouloff.projetter.utils.DefaultValues;

/**
 * Created by Vladimir on 11/03/2016.
 */
public class PromptConnectionView extends LinearLayout {

    private EditText ipEditText, portEditText;

    public PromptConnectionView(Context context) {
        super(context);
    }

    public PromptConnectionView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PromptConnectionView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }




    public void init(){

        ipEditText = (EditText) findViewById(R.id.editTextIp);
        portEditText = (EditText) findViewById(R.id.editTextPort);

        SharedPreferences settings = getContext().getSharedPreferences("DEFAULT", 0);
        String ip = settings.getString("DEFAULT_IP", DefaultValues.DEFAULT_IP);
        int port = settings.getInt("DEFAULT_PORT", DefaultValues.DEFAULT_PORT);

        ipEditText.setText(ip);
        portEditText.setText(String.valueOf(port));

    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        init();
    }
}
