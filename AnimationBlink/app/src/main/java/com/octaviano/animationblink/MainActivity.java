package com.octaviano.animationblink;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ImageView view = findViewById(R.id.imageView);

        final Animation blink = AnimationUtils.loadAnimation(this,R.anim.blink);

        final Animation rotacion = AnimationUtils.loadAnimation(this,R.anim.rotacion);

        Button btnRotacion = findViewById(R.id.rotacion);

        btnRotacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.startAnimation(rotacion);
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.startAnimation(blink);
            }
        });
    }

}
