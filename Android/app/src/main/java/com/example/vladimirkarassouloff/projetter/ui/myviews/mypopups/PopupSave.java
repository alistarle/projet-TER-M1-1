package com.example.vladimirkarassouloff.projetter.ui.myviews.mypopups;

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
public class PopupSave extends LinearLayout {

    private EditText ipEditText, portEditText;

    public PopupSave(Context context) {
        super(context);
    }

    public PopupSave(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PopupSave(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }




    public void init(){


    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        init();
    }
}
