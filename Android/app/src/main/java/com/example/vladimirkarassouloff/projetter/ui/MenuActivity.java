package com.example.vladimirkarassouloff.projetter.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.vladimirkarassouloff.projetter.R;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }



    public void newAlgo(View view) {
        Intent myIntent = new Intent(MenuActivity.this, AlgoActivity.class);
        //myIntent.putExtra("key", value); //Optional parameters
        MenuActivity.this.startActivity(myIntent);

    }


    public void loadAlgo(View view) {
        //Intent intent = new Intent(this, DisplayMessageActivity.class);
    }
}
