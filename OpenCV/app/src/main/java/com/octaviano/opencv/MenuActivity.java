package com.octaviano.opencv;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }


    public void tiempoReal(View v){
        startActivity(new Intent(MenuActivity.this, MainActivity.class));
    }

    public void galeria(View v){
        startActivity(new Intent(MenuActivity.this, GaleriaActivity.class));
    }
}
