package com.example.vladimirkarassouloff.projetter.ui.myelementsproduction;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.vladimirkarassouloff.projetter.R;
import com.example.vladimirkarassouloff.projetter.customlistener.ValidationDialogFunction;
import com.example.vladimirkarassouloff.projetter.myelementsstring.ElementString;
import com.example.vladimirkarassouloff.projetter.myelementsstring.NumberString;
import com.example.vladimirkarassouloff.projetter.myelementsstring.operator.OperatorString;
import com.example.vladimirkarassouloff.projetter.myelementsstring.logic.LogicString;
import com.example.vladimirkarassouloff.projetter.myelementsstring.variable.VariableInstanciationString;
import com.example.vladimirkarassouloff.projetter.myelementsstring.variable.VariableString;
import com.example.vladimirkarassouloff.projetter.ui.myelements.DraggableElement;
import com.example.vladimirkarassouloff.projetter.ui.myviews.prompt.PromptTypeVariableView;
import com.example.vladimirkarassouloff.projetter.utils.Debug;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vladimir on 14/02/2016.
 */
public abstract class Production extends TextView {

    protected ElementString basicElement;


    //Modification
    protected ViewGroup layoutParent;
    protected Drawable separator;
    private TextView myCustomSeparator;
    protected LinearLayout llElem;
    protected LinearLayout llSup;
    protected int columnInsert = 0;


    public Production(Context context){
        super(context);
        this.basicElement = new ElementString();
        init();
    }



    public Production(Context context, AttributeSet attrs){
        super(context, attrs);
        this.basicElement = new ElementString();
        init();
    }
    public Production(Context context,ElementString basicElement){
        super(context);
        this.basicElement = basicElement;
        init();
    }
    public Production(Context context, AttributeSet attrs,ElementString basicElement){
        super(context, attrs);
        this.basicElement = basicElement;
        init();
    }

    protected void init(){
        this.separator = getResources().getDrawable(R.drawable.test);
        myCustomSeparator = new TextView(getContext());
        myCustomSeparator.setText(" ");
        myCustomSeparator.setBackground(separator);
        myCustomSeparator.setHeight(20);

        this.setText("Default");
        this.setPadding(5, 10, 5, 10);
        this.setOnLongClickListener(
                new OnLongClickListener() {
                    public boolean onLongClick(View arg0) {
                        LayoutInflater li = LayoutInflater.from(getContext());
                        View promptsView = li.inflate(R.layout.longclickproductioncontext, null);
                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
                        alertDialogBuilder.setView(promptsView);
                        alertDialogBuilder.setCancelable(true).setItems(R.array.contextMenuProduction, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                       if(which == 0){
                                           supprimer();
                                       }
                                        else if(which == 1){
                                           modifier();
                                       }
                                    }
                                });
                        AlertDialog alertDialog = alertDialogBuilder.create();
                        alertDialog.show();
                        return false;
                    }
                }
        );
    }

    public void supprimer(){
        ViewGroup parent = (ViewGroup)getParent();
        parent.removeView(this);
    }


    public void modifier(){
        LayoutInflater li = LayoutInflater.from(getContext());
        View promptsView = li.inflate(R.layout.modifyproduction, null);

        //on bind les event du layout des elements
        llElem =(LinearLayout) promptsView.findViewById(R.id.elementsProduction);
        llElem.setOnDragListener(new View.OnDragListener() {
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
                        showInsertResultOnElement(event, v);
                        break;
                    case DragEvent.ACTION_DROP:
                        View vNew = (View) event.getLocalState();
                        if(vNew.getParent() != null){
                            ((ViewGroup)vNew.getParent()).removeView(vNew);
                        }
                        llElem.addView(vNew,columnInsert);

                        //refreshText();
                        //autoIndent();
                        break;
                    case DragEvent.ACTION_DRAG_ENDED:
                        resetSeparator();
                    default:
                        break;
                }
                return true;
            }
        });

        //on bind les event du layout des elements a supprimer
        llSup =(LinearLayout) promptsView.findViewById(R.id.elementsProductionSup);
        llSup.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {

                int action = event.getAction();
                switch (event.getAction()) {
                    case DragEvent.ACTION_DRAG_STARTED:
                        break;
                    case DragEvent.ACTION_DRAG_ENTERED:
                        break;
                    case DragEvent.ACTION_DRAG_EXITED:
                        Log.wtf("message","dragexit");
                        break;
                    case DragEvent.ACTION_DRAG_LOCATION:
                        resetSeparator();
                        showInsertResultOnElementToSup(v);
                        Log.wtf("message","dragloc");
                        break;
                    case DragEvent.ACTION_DROP:
                        View vNew = (View) event.getLocalState();
                        if(vNew.getParent() != null){
                            ((ViewGroup)vNew.getParent()).removeView(vNew);
                        }
                        //Log.wtf("message","On insere a la column :"+columnInsert+" et le viewcount a "+llSup.getChildCount());
                        llSup.addView(vNew);
                        Log.wtf("message","dragdrop");
                        break;
                    case DragEvent.ACTION_DRAG_ENDED:

                        Log.wtf("message","dragend");
                    default:
                        break;
                }
                return true;
            }
        });

        //on place les elements et on bind l'event
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(30, 0, 30, 0);

        List<ElementString> arrayElements = new ArrayList<>();
        for(ElementString es : basicElement.components) {
            arrayElements.add(es);
            Production prod = new Production(llElem.getContext(),es) {
                @Override
                public String getBasicText() {
                    return basicElement.toString();
                }
            };
            prod.setOnTouchListener(
                    new View.OnTouchListener() {
                        public boolean onTouch(View v, MotionEvent event) {
                            float x = event.getX();
                            float y = event.getY();
                            int action = event.getAction();
                            switch (event.getAction()) {
                                case MotionEvent.ACTION_DOWN:
                                    break;
                                case MotionEvent.ACTION_UP:
                                    break;
                                case MotionEvent.ACTION_CANCEL:
                                    break;
                                case MotionEvent.ACTION_MOVE:
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
            prod.setOnLongClickListener(new OnLongClickListener(){public boolean onLongClick(View arg0) {return false;}});
            prod.refreshText();
            llElem.addView(prod,layoutParams);
        }



        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
        alertDialogBuilder.setView(promptsView);
        alertDialogBuilder.setCancelable(true).setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        List<ElementString> newArray = new ArrayList<ElementString>();
                        for(int i = 0 ; i < llElem.getChildCount() ; i++){
                            if(llElem.getChildAt(i) instanceof Production){
                                Production pr = (Production) llElem.getChildAt(i);
                                newArray.add(pr.basicElement);
                            }
                        }
                        basicElement.components = newArray;
                        refreshText();
                    }
                })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    private void resetSeparator() {
        if (myCustomSeparator.getParent() != null) {
            ((ViewGroup) myCustomSeparator.getParent()).removeView(myCustomSeparator);
        }
    }

    private void showInsertResultOnElement(DragEvent event, View v){
        View view = (View) event.getLocalState();
        //if sur le layout des elem
        int i = getBlockProche(view,event.getX(), event.getY());
        if(i != -2) {
            /*if(i == -1){

            }*/
            columnInsert = i;
            if(columnInsert > llElem.getChildCount()){
                columnInsert--;
            }
            llElem.addView(myCustomSeparator, columnInsert);
        }
    }
    private void showInsertResultOnElementToSup(View v){
        llSup.addView(myCustomSeparator);
    }

    public void getAllComponent(ArrayList<ElementString> array,boolean includeSelf){
        if(includeSelf && basicElement != null){
            array.add(basicElement);
        }
        for(ElementString es : basicElement.components){
            es.getAllComponent(array,true);
        }
    }


    private int getBlockProche(View dragged,float x,float y){
        int nearestColumn = 0;
        float distanceMin = Float.MAX_VALUE;
        View nearestView = null;
        for(int i = 0 ; i < llElem.getChildCount() ; i++){
            View v = llElem.getChildAt(i);

            View rootLayout = v.getRootView().findViewById(android.R.id.content);
            int[] viewLocation = new int[2];
            v.getLocationInWindow(viewLocation);

            int[] rootLocation = new int[2];
            rootLayout.getLocationInWindow(rootLocation);

            int relativeLeft = viewLocation[0] - rootLocation[0];
            int relativeTop  = viewLocation[1] - rootLocation[1];

            Log.i("Pos",v.getClass().toString()+" se trouve a "+relativeLeft+","+relativeTop+"    et le curseur est a "+y);
            Log.wtf("message",String.valueOf(Math.abs( x-(relativeLeft+v.getWidth()/2)))+" est la distance entre les block");
            if(Math.abs( x-(relativeLeft+v.getWidth()/2)) < Math.abs(distanceMin)){
                distanceMin = x-(relativeLeft+v.getWidth()/2);
                nearestColumn = i;
                nearestView = v;
            }

        }
        if(nearestView == dragged) {
            return -2;
        }
        else if(distanceMin < 0){
            return nearestColumn;
        }
        return nearestColumn+1;
    }

    public abstract String getBasicText();

    public void refreshText(){
        this.setText(getBasicText());
    }


    public boolean supportDropIf(){
        return false;
    }

    public boolean supportDropElse(){
        return false;
    }

    public boolean supportDropElseIf(){
        return false;
    }

    public boolean supportDropVariable(){
        return false;
    }

    public boolean supportDropVariableInstanciation(){
        return false;
    }

    public boolean supportDropNumber(){return false;}

    public boolean supportDropLogic(LogicString op){
        return false;
    }


    public boolean supportDropOperator(){
        return false;
    }




    //sert a l'indentation
    public int tabChanger(){
        return 0;
    }



    public void onDrop(ElementString s) {
        if (s instanceof OperatorString) {
            onDrop((OperatorString)s);
        } else if (s instanceof VariableString) {
            onDrop((VariableString) s);
        } else if (s instanceof VariableInstanciationString) {
            onDrop((VariableInstanciationString)s);
        } else if (s instanceof LogicString) {
            onDrop((LogicString) s);
        } else if (s instanceof NumberString){
            onDrop((NumberString)s);
        }
        else {
            Log.i("Drop not supported", "Drop not supported");
        }
    }

    public void onDrop(OperatorString el){
        Log.i("DROP NOT IMPLEMENTED", "logstring");
    }
    public void onDrop(VariableString ev){
        Log.i("DROP NOT IMPLEMENTED", "VariableString");

    }
    public void onDrop(VariableInstanciationString evi){
        Log.i("DROP NOT IMPLEMENTED", "VariableInstanciationString");

    }
    public void onDrop(LogicString os){
        Log.i("DROP NOT IMPLEMENTED", "OperatorString");
    }
    public void onDrop(NumberString ns){
        Log.i("DROP NOT IMPLEMENTED", "NumberString");
    }




    protected boolean isComponentEmpty(){
        return basicElement.components.size()==0;
    }

    protected boolean lastIsVariable(){
        if(basicElement.components.size()==0)
            return false;
        return this.basicElement.components.get(basicElement.components.size()-1) instanceof VariableString;
    }

    protected boolean lastIsOperator(){
        if(basicElement.components.size()==0)
            return false;
        return this.basicElement.components.get(basicElement.components.size()-1) instanceof LogicString;
    }
    protected boolean lastIsLogic(){
        if(basicElement.components.size()==0)
            return false;
        return this.basicElement.components.get(basicElement.components.size()-1) instanceof OperatorString;
    }

    public void addComponent(ElementString es){
        this.basicElement.components.add(es);
    }

    public ElementString getBasicElement() {
        return basicElement;
    }
}
