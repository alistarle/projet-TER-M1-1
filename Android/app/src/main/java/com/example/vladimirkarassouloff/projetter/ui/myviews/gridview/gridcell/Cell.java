package com.example.vladimirkarassouloff.projetter.ui.myviews.gridview.gridcell;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.vladimirkarassouloff.projetter.R;

/**
 * Created by Vladimir on 19/05/2016.
 */
public class Cell extends LinearLayout {

    private final int pos[] = new int[2];

    public Cell(Context context) {
        super(context);
        pos[0]=pos[1]=0;
    }

    public Cell(Context context, AttributeSet attrs) {
        super(context, attrs);
        pos[0]=pos[1]=0;
    }

    public Cell(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        pos[0]=pos[1]=0;
    }

    public Cell(Context context,int [] pos) {
        super(context);
        pos[0]=pos[1]=0;
    }

    public Cell(Context context, AttributeSet attrs, int [] pos) {
        super(context, attrs);
        setPos(pos);
    }

    public Cell(Context context, AttributeSet attrs, int defStyleAttr,int [] pos) {
        super(context, attrs, defStyleAttr);
        setPos(pos);
    }

    public static Cell createCellAt(Context context, int[] pos){
        LayoutInflater mInflater = LayoutInflater.from(context);
        Cell view = (Cell)mInflater.inflate(R.layout.gridcell, null);
        TextView tv = (TextView) view.findViewById(R.id.grid_item_text);
        ImageView icon = (ImageView) view.findViewById(R.id.grid_item_image);
        tv.setText("Espace vide");
        //holder.text.setText(getTextId(position));
        icon.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.black));

        GridLayout.LayoutParams param =new GridLayout.LayoutParams();
        param.height = GridLayout.LayoutParams.WRAP_CONTENT;
        param.width = GridLayout.LayoutParams.WRAP_CONTENT;
        param.rightMargin = 5;
        param.leftMargin = 5;
        param.topMargin = 5;
        param.bottomMargin = 5;
        param.setGravity(Gravity.CENTER);
        param.columnSpec = GridLayout.spec(pos[0]);
        param.rowSpec = GridLayout.spec(pos[1]);
        view.setLayoutParams(param);
        view.setPos(pos);
        return view;
    }

    public void setPos(int[] pos){
        if(pos.length == 2){
            this.pos[0] = pos[0];
            this.pos[1] = pos[1];
        }
        else{
            Log.w("message","Position length incorrect, grosse erreur");
        }
    }
    public int[] getPos(){
        return pos;
    }


}
