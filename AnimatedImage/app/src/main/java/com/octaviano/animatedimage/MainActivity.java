package com.octaviano.animatedimage;

import android.graphics.ImageDecoder;
import android.graphics.drawable.AnimatedImageDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //loadGif();
    }

    private void loadGif() {

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.P) {
            Toast.makeText(this,"Cargando",Toast.LENGTH_SHORT).show();
            try {

                ImageDecoder.Source source = ImageDecoder.createSource(getResources(),
                        R.drawable.ic_launcher_background);
                Drawable decodedAnimation = ImageDecoder.decodeDrawable(source);
                ImageView imageView = findViewById(R.id.imageView);
                imageView.setImageDrawable(decodedAnimation);
                if (decodedAnimation instanceof AnimatedImageDrawable) {
                    ((AnimatedImageDrawable) decodedAnimation).start();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(this,"Necesita API 28",Toast.LENGTH_SHORT).show();
        }
    }
}