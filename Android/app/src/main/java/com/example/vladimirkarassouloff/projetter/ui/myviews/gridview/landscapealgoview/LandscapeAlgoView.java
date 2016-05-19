package com.example.vladimirkarassouloff.projetter.ui.myviews.gridview.landscapealgoview;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.util.AttributeSet;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vladimirkarassouloff.projetter.R;
import com.example.vladimirkarassouloff.projetter.ui.myviews.gridview.gridcell.Cell;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vladimir on 18/05/2016.
 */
public class LandscapeAlgoView extends GridLayout {


    public List<View> views;

    public LandscapeAlgoView(Context context){
        super(context);

    }

    public LandscapeAlgoView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        init();
    }

    protected void init() {
        views = new ArrayList<View>();
        for(int i = 0 ; i < this.getChildCount() ; i++){
            views.add(this.getChildAt(i));
        }
        refreshLayout();
        //((Activity)getContext()).set
        //this.setAdapter(new ImageAdapterAlgoView(getContext(),this));


        /*this.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                Toast.makeText(arg0.getContext(), position + " selected", Toast.LENGTH_LONG).show();

                switch(position) {
                    case 0:break;
                    case 1:
                        //Browse

                        break;
                    case 2:break;
                    case 3:

                        break;
                    case 4:
                        //Sign in
                        break;
                    case 5:
                        //Reminders
                        break;
                    case 6:break;
                    case 7:
                        //Sign up
                        break;
                    case 8:break;
                }
            }

        });*/

        //onSearchRequested(); //to open search by default
    }

    public void refreshLayout(){
        if(this.getChildCount() == 0){
            int i [] = {0,0};
            addBlackCellAt(i);
        }
    }
    private void addBlackCellAt(int [] pos){
        /*LayoutInflater mInflater = LayoutInflater.from(getContext());
        View view = mInflater.inflate(R.layout.gridcell, null);
        TextView tv = (TextView) view.findViewById(R.id.grid_item_text);
        ImageView icon = (ImageView) view.findViewById(R.id.grid_item_image);
        tv.setText("Espace vide");
        //holder.text.setText(getTextId(position));
        icon.setImageBitmap(BitmapFactory.decodeResource(getContext().getResources(), R.drawable.black));

        GridLayout.LayoutParams param =new GridLayout.LayoutParams();
        param.height = LayoutParams.WRAP_CONTENT;
        param.width = LayoutParams.WRAP_CONTENT;
        param.rightMargin = 5;
        param.leftMargin = 5;
        param.topMargin = 5;
        param.bottomMargin = 5;
        param.setGravity(Gravity.CENTER);
        param.columnSpec = GridLayout.spec(i);
        param.rowSpec = GridLayout.spec(j);

        view.setLayoutParams(param);
*/
        Cell view = Cell.createCellAt(getContext(),pos);
        view.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                Cell cell = (Cell) v;
                ImageView icon = (ImageView) v.findViewById(R.id.grid_item_image);
                int action = event.getAction();
                switch (event.getAction()) {
                    case DragEvent.ACTION_DRAG_STARTED:
                        break;
                    case DragEvent.ACTION_DRAG_ENTERED:
                        icon.setImageBitmap(BitmapFactory.decodeResource(getContext().getResources(), R.drawable.loop_back));
                        break;
                    case DragEvent.ACTION_DRAG_EXITED:
                        icon.setImageBitmap(BitmapFactory.decodeResource(getContext().getResources(), R.drawable.black));
                        break;
                    case DragEvent.ACTION_DROP:
                        GridLayout.LayoutParams param = (GridLayout.LayoutParams)v.getLayoutParams();
                        int newPos [] = {cell.getPos()[0]+1,cell.getPos()[1]};
                        if(isFreeAt(newPos)){
                            addBlackCellAt(newPos);
                        }


                        int newPos2 [] = {0,cell.getPos()[1]+1};
                        if(isFreeAt(newPos2)){
                            addBlackCellAt(newPos2);
                        }


                        // Dropped, reassign View to ViewGroup
                        /*View view = (View) event.getLocalState();
                        ViewGroup owner = (ViewGroup) view.getParent();
                        owner.removeView(view);
                        //LinearLayout container = (LinearLayout) v;
                        elementsScroll.addView(view);
                        view.setVisibility(View.VISIBLE);*/
                        break;
                    case DragEvent.ACTION_DRAG_ENDED:
                        icon.setImageBitmap(BitmapFactory.decodeResource(getContext().getResources(), R.drawable.black));
                    default:
                        break;
                }
                return true;
            }
        });

        this.addView(view);
        ;
    }

    private boolean isFreeAt(int pos[]){
        for(int i = 0 ; i < getChildCount() ; i++){
            Cell c = (Cell) getChildAt(i);
            if(pos[0] == c.getPos()[0] && pos[1] == c.getPos()[1]){
                return false;
            }
        }
        return true;
    }



}



