package com.example.vladimirkarassouloff.projetter.ui.myviews.scrolldraggable;

import android.content.ClipData;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.vladimirkarassouloff.projetter.ui.myelements.DraggableElement;

/**
 * Created by Vladimir on 14/02/2016.
 */
public class ScrollDraggableElementView extends ScrollView {

    protected LinearLayout ll;
    protected int colorOnTouchDraggable =  Color.parseColor("#bababa");
    protected int defaultColorDraggable =  Color.parseColor("#939393");
    protected int colorHeader = Color.parseColor("#757575");
    protected int colorBigHeader = Color.parseColor("#4f4f4f");
    protected int backGroundColor = Color.GRAY;


    protected float initialXEvent;
    protected float initialYEvent;
    protected static float MARGE_EVENT_DRAG = 15;

    public ScrollDraggableElementView(Context context){
        super(context);
        this.init();

    }

    public ScrollDraggableElementView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        this.init();
    }


    protected void init() {
        this.setBackgroundColor(backGroundColor);
        ll = new LinearLayout(this.getContext());
        ll.setOrientation(LinearLayout.VERTICAL);

        //ll.setPadding(0,0,175,0);

        this.addView(ll);


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
                        resetDraggableColor();
                    default:
                        break;
                }
                return true;
            }
        });
    }

    protected void addDraggableElement(View el){
        buildDraggableElement(el);
        ll.addView(el);
    }
    protected void addDraggableElement(View el,int line){
        buildDraggableElement(el);
        ll.addView(el,line);
    }

    protected void buildDraggableElement(View el){
        if(el instanceof TextView){
            TextView tv = (TextView) el;
            tv.setTextColor(Color.WHITE);
        }
        el.setPadding(40,10,5,10);
        el.setBackgroundColor(defaultColorDraggable);
        el.setOnTouchListener(
                new View.OnTouchListener() {
                    public boolean onTouch(View v, MotionEvent event) {

                        float x = event.getX();
                        float y = event.getY();
                        v.setBackgroundColor(colorOnTouchDraggable);
                        int action = event.getAction();
                        switch (event.getAction()) {
                            case MotionEvent.ACTION_DOWN:
                                initialXEvent = event.getX();
                                initialYEvent = event.getY();
                                break;
                            case MotionEvent.ACTION_UP:
                                resetDraggableColor();
                                break;
                            case MotionEvent.ACTION_CANCEL:
                                resetDraggableColor();
                                break;
                            case MotionEvent.ACTION_MOVE:
                                Log.i("Debug ",initialXEvent+" "+x);
                                if(x < initialXEvent-MARGE_EVENT_DRAG) {
                                    ClipData data2 = ClipData.newPlainText("", "");
                                    View.DragShadowBuilder shadowBuilder2 = new View.DragShadowBuilder(v);
                                    v.startDrag(data2, shadowBuilder2, v, 0);
                                }
                                break;
                            default:
                                break;
                        }


                        return true;
                    }
                }

        );
    }




    protected void addBlankLine(){
        TextView tv = new TextView(ll.getContext());
        tv.setBackgroundColor(defaultColorDraggable);
        ll.addView(tv);
    }

    protected void resetDraggableColor(){
        for(int i = 0 ; i < ll.getChildCount() ; i++){
            View v = ll.getChildAt(i);
            if(v instanceof DraggableElement){
                DraggableElement de = (DraggableElement) v;
                v.setBackgroundColor(defaultColorDraggable);
            }
        }
    }
    protected void addBigHeader(String str){
        TextView tv = new TextView(ll.getContext());
        tv.setTextColor(Color.WHITE);
        tv.setPadding(15,10,5,10);
        tv.setText(str);
        tv.setTypeface(null, Typeface.BOLD);
        tv.setBackgroundColor(colorBigHeader);
        ll.addView(tv);
    }
    protected View addHeader(String str){
        TextView tv = new TextView(ll.getContext());
        tv.setTextColor(Color.WHITE);
        tv.setPadding(15,10,5,10);
        tv.setText(str);
        tv.setTypeface(null, Typeface.BOLD);
        tv.setBackgroundColor(colorHeader);
        ll.addView(tv);
        return tv;
    }

    protected void addDraggableElementAfter(View parent,View afterView) {
        addDraggableElement(afterView,ll.indexOfChild(parent)+1);
    }


}