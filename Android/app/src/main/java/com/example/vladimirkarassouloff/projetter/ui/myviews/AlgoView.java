package com.example.vladimirkarassouloff.projetter.ui.myviews;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AlertDialog;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.vladimirkarassouloff.projetter.action.Action;
import com.example.vladimirkarassouloff.projetter.action.AddLineAction;
import com.example.vladimirkarassouloff.projetter.action.MoveLineAction;
import com.example.vladimirkarassouloff.projetter.myelementsstring.BraceCloserString;
import com.example.vladimirkarassouloff.projetter.myelementsstring.ElementString;
import com.example.vladimirkarassouloff.projetter.myelementsstring.NumberString;
import com.example.vladimirkarassouloff.projetter.myelementsstring.fonction.FonctionInstanciationString;
import com.example.vladimirkarassouloff.projetter.ui.AlgoActivity;
import com.example.vladimirkarassouloff.projetter.ui.MyApp;
import com.example.vladimirkarassouloff.projetter.utils.Debug;
import com.example.vladimirkarassouloff.projetter.R;
import com.example.vladimirkarassouloff.projetter.ui.myelements.*;
import com.example.vladimirkarassouloff.projetter.ui.myelements.condition.ElementIf;
import com.example.vladimirkarassouloff.projetter.ui.myelements.variable.ElementVariableInstanciation;
import com.example.vladimirkarassouloff.projetter.ui.myelementsproduction.Production;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vladimir on 12/02/2016.
 */
public class AlgoView extends ScrollView {
    private  LinearLayout ll;

    private Drawable separator;
    private TextView myCustomSeparator;

    private static float MARGE = 15f;





    private int lineInsert;
    private View dropBlock;

    private TextView lastLine; //sert a empecher l'impossibilite d'inserer une ligne en bas de la scrollview

    private enum ActionUser {
        drop,
        line,
        linemove
    }
    private ActionUser currentState;

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


        lastLine = new TextView(getContext());
        lastLine.setMinHeight(300);
        ll.addView(lastLine);



        this.setOnDragListener(new View.OnDragListener() {

            @Override
            public boolean onDrag(View v, DragEvent event) {
                if((View)event.getLocalState() instanceof Production){
                    currentState = ActionUser.linemove;
                }

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
                        resetSeparator();
                        doInsert(event, v);
                        //refreshText();
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

        Production main = new Production(getContext(),new FonctionInstanciationString("main","void"));

        //ProductionFonctionInstanciation main = new ProductionFonctionInstanciation(getContext(),"main","void");
        main.addComponent(new NumberString("43"));
        main.addComponent(new NumberString("12"));
        main.addComponent(new NumberString("65"));
        main.addComponent(new NumberString("124"));
        main.addComponent(new NumberString("55512"));
        ll.addView(main,0);
        //ProductionBraceCloser pbc = new ProductionBraceCloser(getContext());
        Production pbc = new Production(getContext(),new BraceCloserString());
        ll.addView(pbc,1);



        autoIndent();
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
                        ElementString newElement = de.onDraggedOnBlock(p);
                        List<ElementString> supporting = p.getListElementSupporting(newElement);
                        Log.wtf("message","On a trouve "+supporting.size()+" elements supportant le drop");
                        if(supporting.size() > 0){
                            b.setBackground(separator);
                            currentState = ActionUser.drop;
                            dropBlock = b;
                        }
                    }
                }

            }
            //ou si on insere une nouvelle instruction
        else {
            int i = getBlockSuivant(event.getX(),event.getY());
            if(i == ll.getChildCount()){//on replace 1 cran en dessous, car on ne drag pas en dessous de la "lastLine"
                i--;
            }
            currentState = ActionUser.line;
            lineInsert = i;
            ll.addView(myCustomSeparator, i);
        }

    }




    private void doInsert(DragEvent event, View v){
        View view = (View) event.getLocalState();
            if(currentState == ActionUser.drop && view instanceof DraggableElement){
                final DraggableElement de = (DraggableElement) view;
                View clickedBlock = getBlock(event.getX(),event.getY());
                if(clickedBlock instanceof Production) {
                    final Production p = (Production) clickedBlock;
                    final ElementString newElement = de.onDraggedOnBlock(p);
                    /*
                    if (de.isDropSupported(p)) {
                        p.onDrop(de.onDraggedOnBlock(p));
                    }*/
                    final List<ElementString> supporting = p.getListElementSupporting(newElement);
                    Log.wtf("message","On a trouve "+supporting.size()+" elements supportant le drop");
                    if(supporting.size() == 0){
                        Log.wtf("message","pas de support du drop");
                    }
                    else if(supporting.size() == 1){
                        supporting.get(0).onDrop(newElement);
                        de.onDropOver(p);
                    }
                    else{
                        //choix du drop
                        List<String> listString = new ArrayList<String>();
                        for(ElementString es : supporting){
                            listString.add(es.getBasicText());
                        }
                        ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(getContext(),R.layout.choice_element,listString);
                        AlertDialog dialog;
                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                        builder.setTitle("Choisir sur quoi dropper l'element");
                        builder.setAdapter(itemsAdapter, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Log.wtf("mdr","on a click sur "+which);
                                supporting.get(which).onDrop(newElement);
                                de.onDropOver(p);
                                Intent intent = new Intent("autoIndent");
                                LocalBroadcastManager.getInstance(getContext()).sendBroadcast(intent);
                            }
                        });
                        dialog = builder.create();
                        dialog.show();
                    }
                    //de.onDropOver(p);
                }
            }
            else if(currentState == ActionUser.line && view instanceof DraggableElement){
                final DraggableElement de = (DraggableElement) view;
                List<Production> newViews = de.onDraggedOnLine(ll);
                //Log.i("Drop ligne "+lineInsert,"Drop ligne "+lineInsert+"\n\n");
                if(newViews != null && newViews.size() > 0){
                    /*for (View vNew : newViews) {
                        vNew.setMinimumHeight(40);
                        vNew.setPadding(5, 5, 5, 10);
                    }*/
                    AddLineAction ala = new AddLineAction(lineInsert,newViews);
                    AlgoActivity.ACTION_TO_CONSUME.add(ala);
                    Intent intent = new Intent("doAction");
                    LocalBroadcastManager.getInstance(MyApp.context).sendBroadcast(intent);
                }

            }
            else if(currentState == ActionUser.linemove && view instanceof Production){
                MoveLineAction ala;
                if(ll.indexOfChild(view) - lineInsert < 0)
                    ala= new MoveLineAction(ll.indexOfChild(view),lineInsert-1);
                else {
                    ala = new MoveLineAction(ll.indexOfChild(view), lineInsert);
                }
                AlgoActivity.ACTION_TO_CONSUME.add(ala);
                Intent intent = new Intent("doAction");
                LocalBroadcastManager.getInstance(MyApp.context).sendBroadcast(intent);
            }
            else{
                Log.wtf("ACTION NON GEREE\n", "ACTION NON GEREE\n");
                Log.wtf("ACTION NON GEREE\n", "ACTION NON GEREE\n");
                Log.wtf("ACTION NON GEREE\n", "ACTION NON GEREE\n");
            }


        autoIndent();
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

            //Log.i("Pos",v.getClass().toString()+" se trouve a "+relativeLeft+","+relativeTop+"    et le curseur est a "+y);
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
            if(ll.getChildAt(i) instanceof Production) {
                View v = ll.getChildAt(i);
                View rootLayout = v.getRootView().findViewById(android.R.id.content);
                int[] viewLocation = new int[2];
                v.getLocationInWindow(viewLocation);

                int[] rootLocation = new int[2];
                rootLayout.getLocationInWindow(rootLocation);

                int relativeLeft = viewLocation[0] - rootLocation[0];
                int relativeTop = viewLocation[1] - rootLocation[1];

                //if(relativeTop+v.getHeight()/2+ AlgoView.MARGE > y){
                if (relativeTop + v.getHeight() / 2 > y) {
                    return i;

                }
            }
        }
        return i;
    }

    /*public void refreshText(){
        for(int i = 0 ; i < ll.getChildCount() ; i++){
            View v = ll.getChildAt(i);
            if(v instanceof Production){
                Production p = (Production) v;
                p.refreshText();
            }
        }
    }*/

    private void resetSeparator(){
        if(myCustomSeparator.getParent() != null) {
            ll.removeView(myCustomSeparator);
        }
        for(int i = 0 ; i < ll.getChildCount() ; i++) {
            ll.getChildAt(i).setBackground(null);
            if(ll.getChildAt(i) instanceof Production){
                Production p = (Production)ll.getChildAt(i);
                p.refreshColor();
            }
        }
    }


    public void autoIndent(){
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
                if(j >= 0) {
                    while (j < tab) {
                        tabs += "\t\t";
                        j++;
                    }
                    int newDefaultColor = p.getBackgroundColorDefault();
                    for(int k = 0 ; k < tab ; k++){
                        //on eclaircit la couleur
                        if( (!(p.getBasicElement() instanceof BraceCloserString)) || (p.getBasicElement() instanceof BraceCloserString && k < tab-1)) {
                            float[] hsv = new float[3];
                            Color.colorToHSV(newDefaultColor, hsv);
                            hsv[2] *= 1.05f;
                            newDefaultColor = Color.HSVToColor(hsv);
                        }

                    }
                    p.setColor(newDefaultColor);
                }
                else {
                    tabs = "";
                    ((Production) v).addErrorMessage("Erreur d'indentation.");
                }
                tab += p.tabChanger();

                p.setText(tabs+p.getBasicText());
            }
        }
        replaceLastLine();
    }

    protected void replaceLastLine(){
        if(lastLine.getParent() != null){
            ll.removeView(lastLine);
        }
        ll.addView(lastLine);
        //lastLine.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        //lastLine.setBackgroundColor(Color.BLACK);

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
