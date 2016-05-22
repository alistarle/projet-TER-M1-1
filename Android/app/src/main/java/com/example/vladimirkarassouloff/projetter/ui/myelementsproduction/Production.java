package com.example.vladimirkarassouloff.projetter.ui.myelementsproduction;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ScaleDrawable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vladimirkarassouloff.projetter.R;
import com.example.vladimirkarassouloff.projetter.action.AddLineAction;
import com.example.vladimirkarassouloff.projetter.action.DeleteLineAction;
import com.example.vladimirkarassouloff.projetter.action.ModifyProductionAction;
import com.example.vladimirkarassouloff.projetter.customlistener.ValidationDialogFunction;
import com.example.vladimirkarassouloff.projetter.myelementsstring.ElementString;
import com.example.vladimirkarassouloff.projetter.myelementsstring.NumberString;
import com.example.vladimirkarassouloff.projetter.myelementsstring.operator.OperatorString;
import com.example.vladimirkarassouloff.projetter.myelementsstring.logic.LogicString;
import com.example.vladimirkarassouloff.projetter.myelementsstring.variable.VariableInstanciationString;
import com.example.vladimirkarassouloff.projetter.myelementsstring.variable.VariableString;
import com.example.vladimirkarassouloff.projetter.ui.AlgoActivity;
import com.example.vladimirkarassouloff.projetter.ui.MyApp;
import com.example.vladimirkarassouloff.projetter.ui.myelements.DraggableElement;
import com.example.vladimirkarassouloff.projetter.ui.myviews.AlgoView;
import com.example.vladimirkarassouloff.projetter.ui.myviews.prompt.PromptTypeVariableView;
import com.example.vladimirkarassouloff.projetter.utils.Debug;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

/**
 * Created by Vladimir on 14/02/2016.
 */
public class Production extends LinearLayout {


    public static String ERRORTAG_INDENTATION = "Indent";
    public static String ERRORTAG_COMPIL = "Compilateur";
    public static List<String> tagErrorList = new ArrayList<>();
    static {
        tagErrorList.add(ERRORTAG_COMPIL);
        tagErrorList.add(ERRORTAG_INDENTATION);
    }

    protected Hashtable<String,String> hashError;

    protected TextView tv;
    protected ElementString basicElement;

    protected ImageView errorDisplay;
    protected String errorMessage;

    protected float initialXEvent, initialYEvent;
    protected float MARGE_EVENT_DRAG = 100;
    protected boolean isLongClickedTriggered;

    //Modification
    protected ViewGroup layoutParent;
    protected Drawable separator;
    private TextView myCustomSeparator;
    protected LinearLayout llElem;
    protected LinearLayout llSup;
    protected int columnInsert = 0;


    public Production(Context context){
        super(context);
        this.tv = new TextView(context);
        this.errorDisplay = new ImageView(context);
        addView(tv);
        addView(errorDisplay);
        this.basicElement = new ElementString();
        init();
    }




    public Production(Context context, AttributeSet attrs){
        super(context, attrs);
        this.tv = new TextView(context);
        this.errorDisplay = new ImageView(context);
        addView(tv);
        addView(errorDisplay);
        this.basicElement = new ElementString();
        init();
    }
    public Production(Context context,ElementString basicElement){
        super(context);
        this.tv = new TextView(context);
        this.errorDisplay = new ImageView(context);
        addView(tv);
        addView(errorDisplay);
        this.basicElement = basicElement;
        init();
    }
    public Production(Context context, AttributeSet attrs,ElementString basicElement){
        super(context, attrs);
        this.tv = new TextView(context);
        this.errorDisplay = new ImageView(context);
        addView(tv);
        addView(errorDisplay);
        this.basicElement = basicElement;
        init();
    }

    protected void init(){
        this.setOrientation(LinearLayout.HORIZONTAL);

        hashError = new Hashtable<>();

        this.tv.setTextColor(Color.BLACK);
        this.separator = getResources().getDrawable(R.drawable.test);
        myCustomSeparator = new TextView(getContext());
        myCustomSeparator.setText(" ");
        myCustomSeparator.setBackground(separator);
        myCustomSeparator.setHeight(20);

        if(basicElement != null) {
            this.setBackgroundColor(basicElement.getCurrentBackgroundColor());
        }
        tv.setMinHeight(40);

        LayoutParams textParam = new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
        textParam.gravity = Gravity.CENTER_VERTICAL;
        textParam.weight = 0.1f;
        tv.setLayoutParams(textParam);
        tv.setGravity(Gravity.CENTER_VERTICAL);

        errorDisplay.setBackgroundColor(Color.BLACK);
        errorDisplay.setImageDrawable(getResources().getDrawable(R.drawable.warning));
        LayoutParams imageParam = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        imageParam.gravity = Gravity.END;
        imageParam.weight = 0.7f;
        errorDisplay.setLayoutParams(imageParam);
        eraseError();


        this.refreshText();



        errorDisplay.setOnTouchListener(
                new View.OnTouchListener() {
                    public boolean onTouch(View v, MotionEvent event) {
                        int action = event.getAction();
                        switch (event.getAction()) {
                            case MotionEvent.ACTION_DOWN:
                                showError();
                                break;
                            case MotionEvent.ACTION_UP:
                                break;
                            case MotionEvent.ACTION_CANCEL:
                                break;
                            case MotionEvent.ACTION_MOVE:

                                break;
                            default:
                                break;
                        }
                        return true;
                    }
                }

        );



        this.setOnTouchListener(
                new View.OnTouchListener() {
                    public boolean onTouch(View v, MotionEvent event) {

                        float x = event.getX();
                        float y = event.getY();
                        int action = event.getAction();
                        switch (event.getAction()) {
                            case MotionEvent.ACTION_DOWN:
                                initialXEvent = event.getX();
                                initialYEvent = event.getY();
                                setBackgroundColor(getBackgroundColorOnTouch());
                                break;
                            case MotionEvent.ACTION_UP:
                                //resetDraggableColor();
                                isLongClickedTriggered = false;
                                setBackgroundColor(getCurrentBackgroundColor());
                                break;
                            case MotionEvent.ACTION_CANCEL:
                                //resetDraggableColor();
                                isLongClickedTriggered = false;
                                setBackgroundColor(getCurrentBackgroundColor());
                                break;
                            case MotionEvent.ACTION_MOVE:
                                Log.i("Debug ",initialXEvent+" "+x);
                                if((x < initialXEvent-MARGE_EVENT_DRAG || x > initialXEvent+MARGE_EVENT_DRAG) && !isLongClickedTriggered) {
                                    ClipData data2 = ClipData.newPlainText("", "");
                                    View.DragShadowBuilder shadowBuilder2 = new View.DragShadowBuilder(v);
                                    v.startDrag(data2, shadowBuilder2, v, 0);
                                    setBackgroundColor(getCurrentBackgroundColor());
                                }
                                break;
                            default:
                                break;
                        }
                        return false;
                    }
                }

        );


        this.setOnLongClickListener(
                new OnLongClickListener() {
                    public boolean onLongClick(View arg0) {
                        isLongClickedTriggered = true;
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
                                           final List<ElementString> listEditableElements = getListElementEditable(basicElement);
                                           if(listEditableElements.size()==0){
                                               Toast.makeText(getContext(),"Rien a modifier",Toast.LENGTH_SHORT).show();
                                           }
                                           else if(listEditableElements.size()==1){
                                                modifier(listEditableElements.get(0));
                                           }
                                           else{
                                               //choix
                                               List<String> listString = new ArrayList<String>();
                                               for(ElementString es : listEditableElements){
                                                   listString.add(es.getBasicText());
                                               }
                                               ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(getContext(),R.layout.choice_element,listString);
                                               android.support.v7.app.AlertDialog dialog2;
                                               android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(getContext());
                                               builder.setTitle("Choisir quel element modifier");
                                               builder.setAdapter(itemsAdapter, new DialogInterface.OnClickListener() {
                                                   @Override
                                                   public void onClick(DialogInterface dialog2, int which) {
                                                       modifier(listEditableElements.get(which));
                                                   }
                                               });
                                               dialog2 = builder.create();
                                               dialog2.show();
                                           }
                                           //modifier();
                                       }
                                        isLongClickedTriggered = false;
                                    }
                                });

                        AlertDialog alertDialog = alertDialogBuilder.create();
                        alertDialog.show();
                        return true;
                    }
                }
        );
    }

    public void supprimer(){
        if(getParent()!=null && getParent().getParent()!=null && getParent().getParent() instanceof AlgoView) {
            int line = ((ViewGroup)getParent()).indexOfChild(this);
            ArrayList<Production> oldView = new ArrayList<>();
            oldView.add(this);
            DeleteLineAction ala = new DeleteLineAction(line, oldView);
            AlgoActivity.ACTION_TO_CONSUME.add(ala);
            Intent intent = new Intent("doAction");
            LocalBroadcastManager.getInstance(MyApp.context).sendBroadcast(intent);
        }
            /*
        ViewGroup parent = (ViewGroup)getParent();
        parent.removeView(this);*/
    }


    public void modifier(final ElementString elementToChange){
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
        layoutParams.setMargins(10, 0, 10, 0);

        List<ElementString> arrayElements = new ArrayList<>();
        for(ElementString es : elementToChange.components) {
            arrayElements.add(es);
            Production prod = new Production(llElem.getContext(),es);
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

            llElem.addView(prod,layoutParams);
        }



        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
        alertDialogBuilder.setView(promptsView);
        alertDialogBuilder.setCancelable(true).setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        ArrayList<ElementString> newArray = new ArrayList<ElementString>();
                        for(int i = 0 ; i < llElem.getChildCount() ; i++){
                            if(llElem.getChildAt(i) instanceof Production){
                                Production pr = (Production) llElem.getChildAt(i);
                                newArray.add(pr.basicElement);
                            }
                        }
                        //elementToChange.components = newArray;
                        ModifyProductionAction mpa = new ModifyProductionAction(elementToChange,newArray);
                        AlgoActivity.ACTION_TO_CONSUME.add(mpa);
                        Intent intent = new Intent("doAction");
                        LocalBroadcastManager.getInstance(MyApp.context).sendBroadcast(intent);
                        /*Intent intent = new Intent("autoIndent");
                        LocalBroadcastManager.getInstance(getContext()).sendBroadcast(intent);*/
                    }
                })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent intent = new Intent("autoIndent");
                                LocalBroadcastManager.getInstance(getContext()).sendBroadcast(intent);
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

           /* Log.i("Pos",v.getClass().toString()+" se trouve a "+relativeLeft+","+relativeTop+"    et le curseur est a "+y);
            Log.wtf("message",String.valueOf(Math.abs( x-(relativeLeft+v.getWidth()/2)))+" est la distance entre les block");*/
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








    public String getBasicText(){
        return basicElement.getBasicText();
    }


    public void refreshText() {
        if (basicElement == null) {
            tv.setText("EmptyProduction");
        } else {
            tv.setText(basicElement.getBasicText());
        }
    }


    public boolean supportDropIf(){
        return basicElement.supportDropIf();
    }

    public boolean supportDropElse(){
        return basicElement.supportDropElse();
    }

    public boolean supportDropElseIf(){
        return basicElement.supportDropElseIf();
    }

    public boolean supportDropVariable(){
        return basicElement.supportDropVariable();
    }

    public boolean supportDropFonctionInstanciation(){
        return basicElement.supportDropFonctionInstanciation();
    }
    public boolean supportDropFonction(){
        return basicElement.supportDropFonction();
    }

    public boolean supportDropVariableInstanciation(){
        return basicElement.supportDropVariableInstanciation();
    }

    public boolean supportDropNumber(){return basicElement.supportDropNumber();}

    public boolean supportDropLogic(LogicString op){
        return basicElement.supportDropLogic(op);
    }


    public boolean supportDropOperator(){
        return basicElement.supportDropOperator();
    }




    public int tabChanger(){
        return basicElement.tabChanger();
    }


    public void onDrop(ElementString s) {
        basicElement.onDrop(s);
    }

    public void onDrop(OperatorString el){
        basicElement.onDrop(el);
    }
    public void onDrop(VariableString ev){
        basicElement.onDrop(ev);
    }
    public void onDrop(VariableInstanciationString evi){
        basicElement.onDrop(evi);
    }
    public void onDrop(LogicString os){
        basicElement.onDrop(os);
    }
    public void onDrop(NumberString ns) {
        basicElement.onDrop(ns);
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

    public List<ElementString> getListElementSupporting(ElementString newElement){
        List<ElementString> supporting = new ArrayList<>();
        basicElement.addAllElementSupportingDrop(supporting,newElement);
        return supporting;
    }
    public List<ElementString> getListElementEditable(ElementString elementString){
        List<ElementString> editable = new ArrayList<>();
        elementString.addAllElementEditable(editable);
        return editable;
    }



    public void refreshColor() {
        if (basicElement != null) {
            if ("".equals(errorMessage)) {
                this.setBackgroundColor(basicElement.getBackgroundColorError());
            }
            this.setBackgroundColor(basicElement.getCurrentBackgroundColor());
        }
    }


    public int getCurrentBackgroundColor(){
        if(basicElement == null){
            return 0;
        }
        return basicElement.getCurrentBackgroundColor();
    }

    public int getBackgroundColorDefault(){
        if(basicElement == null){
            return 0;
        }
        return basicElement.getBackgroundColorDefault();
    }

    public int getBackgroundColorOnTouch(){
        if(basicElement == null){
            return 0;
        }
        return basicElement.getBackgroundColorOnTouch();
    }

    public void setText(String s){
        this.tv.setText(s);
    }

    public void setColor(int color){
        if(basicElement != null){
            basicElement.setColor(color);
        }
        setBackgroundColor(getCurrentBackgroundColor());
    }

////////////////////////////////////////////Erreurs////////////////////
    public void setErrorMessage(String tag, String s){
        hashError.put(tag,s);
        if(hasError()) {
            errorDisplay.setVisibility(VISIBLE);
        }
        else{
            errorDisplay.setVisibility(GONE);
        }
        refreshColor();
    }
    public void showError(){
        if(hasError()) {

                //Toast.makeText(getContext(),"MDRRR",Toast.LENGTH_SHORT).show();
            LayoutInflater li = LayoutInflater.from(getContext());
            View showError = li.inflate(R.layout.error, null);
            TextView textError = (TextView)showError.findViewById(R.id.textError);
                textError.setText(getError());

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
            alertDialogBuilder.setView(showError);
            alertDialogBuilder.setCancelable(true).setPositiveButton("Ok",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                        }
                    });
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }
        eraseError();
    }

    public boolean hasError(){
        if("".equals(getError())){
            return false;
        }
        return true;
    }

    public void eraseError(){
        errorDisplay.setVisibility(GONE);
        for(String errorTag : tagErrorList){
            hashError.put(errorTag,"");
        }
        refreshColor();
    }
    public String getError(){
        String s = "";
        for(String errorTag : tagErrorList){
            if( !  (hashError.get(errorTag) == null || hashError.get(errorTag).equals(""))){
                s+= hashError.get(errorTag);
            }
        }
        return s;
    }


    public boolean shouldBeInsideParenthesis(){
        if(basicElement != null){
            return basicElement.shouldBeInsideParenthesis();
        }
        return false;
    }
    ////////////////////////////////////////////ErreursFin/////////////////

}
