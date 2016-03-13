package com.example.vladimirkarassouloff.projetter.ui.myviews;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ViewAnimator;

import com.example.vladimirkarassouloff.projetter.R;

/**
 * Created by Vladimir on 14/02/2016.
 */
public class ControlView extends LinearLayout {

    Button buttonPrev,buttonNext;
    Animation slide_in_left, slide_out_right;
    ViewAnimator viewAnimator;

    public ControlView(Context context){
        super(context);
        init();
    }
    public ControlView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init();
    }



    private void init() {
        buttonPrev = (Button)findViewById(R.id.prev);
        buttonNext = (Button)findViewById(R.id.next);

        slide_in_left = AnimationUtils.loadAnimation(this.getContext(), android.R.anim.slide_in_left);
        slide_out_right = AnimationUtils.loadAnimation(this.getContext(), android.R.anim.slide_out_right);

        viewAnimator.setInAnimation(slide_in_left);
        viewAnimator.setOutAnimation(slide_out_right);

        buttonPrev.setOnClickListener(new OnClickListener(){

            @Override
            public void onClick(View arg0) {
                viewAnimator.showPrevious();
            }});

        buttonNext.setOnClickListener(new OnClickListener(){

            @Override
            public void onClick(View arg0) {
                viewAnimator.showNext();
            }});
    }

}
