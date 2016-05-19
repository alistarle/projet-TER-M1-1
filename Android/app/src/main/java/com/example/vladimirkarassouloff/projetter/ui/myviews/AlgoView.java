package com.example.vladimirkarassouloff.projetter.ui.myviews;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.vladimirkarassouloff.projetter.myelementsstring.NumberString;
import com.example.vladimirkarassouloff.projetter.myelementsstring.fonction.FonctionInstanciationString;
import com.example.vladimirkarassouloff.projetter.ui.myelements.fonction.ElementFonctionInstanciation;
import com.example.vladimirkarassouloff.projetter.ui.myelementsproduction.ProductionBraceCloser;
import com.example.vladimirkarassouloff.projetter.ui.myelementsproduction.fonction.ProductionFonctionInstanciation;
import com.example.vladimirkarassouloff.projetter.utils.Debug;
import com.example.vladimirkarassouloff.projetter.R;
import com.example.vladimirkarassouloff.projetter.ui.myelements.*;
import com.example.vladimirkarassouloff.projetter.ui.myelements.condition.ElementIf;
import com.example.vladimirkarassouloff.projetter.ui.myelements.variable.ElementVariableInstanciation;
import com.example.vladimirkarassouloff.projetter.ui.myelementsproduction.Production;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Vladimir on 12/02/2016.
 */
public class AlgoView extends ScrollView {
    private  LinearLayout ll;

    private Drawable separator;
    private TextView myCustomSeparator;

    private static float MARGE = 25f;

    private int lineInsert;
    private View dropBlock;
    private enum Action {
        drop,
        line
    }
    private Action currentState;

    public AlgoView(Context context){
        super(context);
        init();
    }
    public AlgoView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init();
    }



    protected void init() {
        //this.setBackgroundColor(Color.CYAN);
        ll = new LinearLayout(this.getContext());
        this.addView(ll);
        ll.setOrientation(LinearLayout.VERTICAL);
        separator = getResources().getDrawable(R.drawable.test);
        myCustomSeparator = new TextView(getContext());
        myCustomSeparator.setText(" ");
        myCustomSeparator.setBackground(separator);
        myCustomSeparator.setHeight(20);
        ll.addView(myCustomSeparator);

        this.setOnDragListener(new View.OnDragListener() {

            @Override
            public boolean onDrag(View v, DragEvent event) {

                int action = event.getAction();
                switch (event.getAction()) {
                    case DragEvent.ACTION_DRAG_STARTED:
                        resetSeparator();
                        break;
                    case DragEvent.ACTION_DRAG_ENTERED:
                        // v.setBackgroundDrawable(enterShape);
                        //Log.i("ENTERED algo", "ENTERED algo");
                        break;
                    case DragEvent.ACTION_DRAG_EXITED:
                        // v.setBackgroundDrawable(normalShape);
                        //Log.i("EXITED algo", "EXITED algo");
                        break;
                    case DragEvent.ACTION_DRAG_LOCATION:
                        //Log.i("dragloc algo " + event.getX() + " " + event.getY(), "dragloc algo");
                        resetSeparator();
                        showInsertResult(event, v);
                        break;
                    case DragEvent.ACTION_DROP:
                        doInsert(event, v);
                        refreshText();
                        autoIndent();
                        break;
                    case DragEvent.ACTION_DRAG_ENDED:
                        resetSeparator();
                    default:
                        break;
                }
                return true;
            }
        });


        //main.refreshTextView();

        if(Debug.DEBUG_APP){
            ll.addView((new ElementVariableInstanciation(getContext())).onDraggedOnLine(ll).get(0),lineInsert);
            ll.addView((new ElementIf(getContext())).onDraggedOnLine(ll).get(0),2);
            ll.addView((new ElementIf(getContext())).onDraggedOnLine(ll).get(1),3);

        }


    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        ProductionFonctionInstanciation main = new ProductionFonctionInstanciation(getContext(),"main","void");
        main.addComponent(new NumberString("43"));
        main.addComponent(new NumberString("12"));
        ll.addView(main,0);
        ProductionBraceCloser pbc = new ProductionBraceCloser(getContext());
        ll.addView(pbc,1);
        refreshText();
    }

    private void showInsertResult(DragEvent event, View v){
        //on regarde si on dragg sur un block
        View view = (View) event.getLocalState();
        if (testIfInsideBlock(event.getX(), event.getY())) {
               // Log.i("d","d");
                if(view instanceof DraggableElement) {
                    DraggableElement de = (DraggableElement) view;
                    View b = getBlock(event.getX(), event.getY());
                    if (b instanceof Production) {
                        Production p = (Production) b;
                        if (de.isDropSupported(p)) {
                            //p.onDrop(de.onDraggedOnBlock());

                            /*****/
                            /**TO DO ONSHOWDROP**/
                            /*****/

                            b.setBackground(separator);
                            currentState = Action.drop;
                            dropBlock = b;
                            //Log.i("On a trouve un block", "On a trouve un block");

                        }
                    }
                }

            }
            //ou si on insere une nouvelle instruction
            else {
                int i = getBlockSuivant(event.getX(),event.getY());
                //Log.i("Block suivant trouve " + i, "Block suivant trouve " + i);
                currentState = Action.line;
                lineInsert = i;
                ll.addView(myCustomSeparator, i);
            }

    }

    private void doInsert(DragEvent event, View v){
        View view = (View) event.getLocalState();
        if (view instanceof DraggableElement) {
            DraggableElement de = (DraggableElement) view;
            ScrollView container = (ScrollView) v;


            if(currentState == Action.drop){
                View clickedBlock = getBlock(event.getX(),event.getY());
                if(clickedBlock instanceof Production) {
                    Production p = (Production) clickedBlock;
                    if (de.isDropSupported(p)) {
                        p.onDrop(de.onDraggedOnBlock(p));
                        //v.setBackground(separator);
                        //Log.i("Drop block ", "Drop block \n\n");

                    }
                }
            }
            else if(currentState == Action.line){
                List<View> newViews = de.onDraggedOnLine(ll);
                //Log.i("Drop ligne "+lineInsert,"Drop ligne "+lineInsert+"\n\n");
                if(newViews != null){
                    for (View vNew : newViews) {
                        vNew.setMinimumHeight(100);
                        //vNew.setPadding(5, 5, 5, 5);

                        ll.addView(vNew,lineInsert);
                        lineInsert++;
                    }
                }
            }
            else{
                Log.wtf("ACTION NON GEREE\n", "ACTION NON GEREE\n");
                Log.wtf("ACTION NON GEREE\n", "ACTION NON GEREE\n");
                Log.wtf("ACTION NON GEREE\n", "ACTION NON GEREE\n");
            }

            /*if(testIfInsideBlock(event.getX(),event.getY())){
                View clickedBlock = getBlock(event.getX(),event.getY());
                de.onDraggedOnBlock(clickedBlock);
                //v.setBackground(separator);
                Log.i("Drop block ","Drop block \n\n");
            }
            //ou si on insere une nouvelle instruction
            else{
                int i = getBlockSuivant(event.getX(), event.getY());
                List<View> newViews = de.onDraggedOnLine(ll);
                Log.i("Drop ligne "+i,"Drop ligne "+i+"\n\n");
                for (View vNew : newViews) {
                    vNew.setMinimumHeight(80);
                    vNew.setPadding(5, 5, 5, 5);
                    ll.addView(vNew,i);
                    i++;
                }
                //ll.addView(myCustomSeparator,i);
            }*/


        }
    }


    private boolean testIfInsideBlock(float x,float y){
        return (getBlock(x,y) != null);
    }

    //donne le block ou se trouve le curseur
    private View getBlock(float x,float y){

        for(int i = 0 ; i < ll.getChildCount() ; i++){
            View v = ll.getChildAt(i);

            View rootLayout = v.getRootView().findViewById(android.R.id.content);
            int[] viewLocation = new int[2];
            v.getLocationInWindow(viewLocation);

            int[] rootLocation = new int[2];
            rootLayout.getLocationInWindow(rootLocation);

            int relativeLeft = viewLocation[0] - rootLocation[0];
            int relativeTop  = viewLocation[1] - rootLocation[1];

            Log.i("Pos",v.getClass().toString()+" se trouve a "+relativeLeft+","+relativeTop+"    et le curseur est a "+y);
           if(relativeTop+v.getHeight()/2+ AlgoView.MARGE > y && relativeTop +v.getHeight()/2 - AlgoView.MARGE < y){
                return v;

            }
        }
        return null;
    }


    //donne l'index pour placer une vue entre deux element en fonction de la position du curseur
    private int getBlockSuivant(float x,float y){
        if(ll.getChildCount() == 0)
            return 0;
        int i;
        for(i = 0 ; i < ll.getChildCount() ; i++){
            View v = ll.getChildAt(i);
            View rootLayout = v.getRootView().findViewById(android.R.id.content);
            int[] viewLocation = new int[2];
            v.getLocationInWindow(viewLocation);

            int[] rootLocation = new int[2];
            rootLayout.getLocationInWindow(rootLocation);

            int relativeLeft = viewLocation[0] - rootLocation[0];
            int relativeTop  = viewLocation[1] - rootLocation[1];

            //Log.i("Pos",v.getClass().toString()+" se trouve a "+relativeLeft+","+relativeTop+"    et le curseur est a "+y);
            if(relativeTop+v.getHeight()/2+ AlgoView.MARGE > y){
                return i;

            }
        }
        return i;
    }

    private void refreshText(){
        for(int i = 0 ; i < ll.getChildCount() ; i++){
            View v = ll.getChildAt(i);
            if(v instanceof Production){
                Production p = (Production) v;
                p.refreshText();
            }
        }
    }

    private void resetSeparator(){
        ll.removeView(myCustomSeparator);
        for(int i = 0 ; i < ll.getChildCount() ; i++) {
            ll.getChildAt(i).setBackground(null);
        }
    }


    private void autoIndent(){
        int tab = 0;
        for(int i = 0 ; i < ll.getChildCount() ; i++){
            View v = ll.getChildAt(i);
            if(v instanceof Production){
                Production p = (Production) v;
                String tabs = "";
                int j = 0;
                if(p.tabChanger() < 0){
                    j += -p.tabChanger();
                }
                while(j < tab){
                    tabs+="\t\t";
                    j++;
                }
                tab += p.tabChanger();

                p.setText(tabs+p.getBasicText());
            }
        }
    }

    public String getAlgorithme(){
        String algo = "";
        for(int i = 0 ; i < ll.getChildCount() ; i++){
            View v = ll.getChildAt(i);
            if(v instanceof Production){
                Production p = (Production) v;
                algo+=p.getBasicText();
            }
        }
        return algo;
    }

    public LinearLayout getLl() {
        return ll;
    }

}
