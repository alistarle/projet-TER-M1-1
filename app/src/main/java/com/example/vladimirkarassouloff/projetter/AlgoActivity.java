package com.example.vladimirkarassouloff.projetter;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewAnimator;

import com.example.vladimirkarassouloff.projetter.myviews.AlgoView;
import com.example.vladimirkarassouloff.projetter.myviews.scrolldraggable.ElementsView;


public class AlgoActivity extends ActionBarActivity {


    private TextView test;
    private TextView drag;
    private LinearLayout mainLayout;

    //scroll droite
    private ElementsView elementsScroll;

    //scroll gauche
    private AlgoView algoScroll;


    private Button buttonPrev,buttonNext;
    private Animation slide_in_left, slide_out_right;
    private ViewAnimator viewAnimator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_algo);

        mainLayout = (LinearLayout) findViewById(R.id.mainLayout);

        elementsScroll = (ElementsView) findViewById(R.id.elementsScroll);
        algoScroll = (AlgoView) findViewById(R.id.algoScroll);

        ///////////Gestion viewanimator/////////////
        viewAnimator = (ViewAnimator)findViewById(R.id.viewanimator);
        buttonPrev = (Button)findViewById(R.id.prev);
        buttonNext = (Button)findViewById(R.id.next);

        slide_in_left = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left);
        slide_out_right = AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right);

        viewAnimator.setInAnimation(slide_in_left);
        viewAnimator.setOutAnimation(slide_out_right);

        buttonPrev.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                viewAnimator.showPrevious();
            }});

        buttonNext.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                viewAnimator.showNext();
            }});
        /////////////////////////////////////////////

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_algo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
