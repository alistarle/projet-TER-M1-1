package com.example.vladimirkarassouloff.projetter.ui.myviews.gridview;

import android.content.ClipData;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Vladimir on 18/05/2016.
 */
public class LandscapeDragView extends GridLayout {

    protected float initialXEvent;
    protected float initialYEvent;


    public LandscapeDragView(Context context){
        super(context);
        //this.init();

    }

    public LandscapeDragView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        //this.init();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        init();
    }

    protected void init() {

        for(int i = 0 ; i < this.getChildCount() ; i++){
            buildDraggableElement(getChildAt(i));
        }

        this.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {

                int action = event.getAction();
                switch (event.getAction()) {
                    case DragEvent.ACTION_DRAG_STARTED:
                        break;
                    case DragEvent.ACTION_DRAG_ENTERED:
                        break;
                    case DragEvent.ACTION_DRAG_EXITED:
                        break;
                    case DragEvent.ACTION_DROP:

                        // Dropped, reassign View to ViewGroup
                        /*View view = (View) event.getLocalState();
                        ViewGroup owner = (ViewGroup) view.getParent();
                        owner.removeView(view);
                        //LinearLayout container = (LinearLayout) v;
                        elementsScroll.addView(view);
                        view.setVisibility(View.VISIBLE);*/
                        break;
                    case DragEvent.ACTION_DRAG_ENDED:
                        //v.setBackgroundDrawable(normalShape);
                    default:
                        break;
                }
                return true;
            }
        });
    }

    protected void buildDraggableElement(View el){
        el.setOnTouchListener(
                new View.OnTouchListener() {
                    public boolean onTouch(View v, MotionEvent event) {
                        float x = event.getX();
                        float y = event.getY();
                        int action = event.getAction();
                        switch (event.getAction()) {
                            case MotionEvent.ACTION_DOWN:
                                initialXEvent = event.getX();
                                initialYEvent = event.getY();
                                break;
                            case MotionEvent.ACTION_UP:
                                break;
                            case MotionEvent.ACTION_CANCEL:
                                break;
                            case MotionEvent.ACTION_MOVE:
                                Log.i("Debug ",initialXEvent+" "+x);
                                ClipData data2 = ClipData.newPlainText("", "");
                                View.DragShadowBuilder shadowBuilder2 = new View.DragShadowBuilder(v);
                                v.startDrag(data2, shadowBuilder2, v, 0);

                                break;
                            default:
                                break;
                        }
                        return true;
                    }
                }

        );
    }


}
