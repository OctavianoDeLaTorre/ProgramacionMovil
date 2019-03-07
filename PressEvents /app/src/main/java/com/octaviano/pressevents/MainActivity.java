package com.octaviano.pressevents;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    View.OnClickListener event = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(MainActivity.this, "Click", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        Button b2 = findViewById(R.id.button2);
        b2.setOnClickListener(this);
        button.setOnClickListener(this);
        button.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(MainActivity.this, "Long Press", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }


    public void click(View v){
        Toast.makeText(MainActivity.this, "Click", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.button:
                Toast.makeText(MainActivity.this, "Boton 1", Toast.LENGTH_SHORT).show();
                break;

            case R.id.button2:
                Toast.makeText(MainActivity.this, "Boton 2", Toast.LENGTH_SHORT).show();
                break;
        }

    }
}
