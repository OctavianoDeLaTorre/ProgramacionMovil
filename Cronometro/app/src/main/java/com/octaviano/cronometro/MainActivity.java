package com.octaviano.cronometro;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
     TextView label;
     Thread thread;
     Cronometro c;
    boolean estado;
    boolean running;
    int contador;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        label = findViewById(R.id.label);
         estado = true;

        thread = new Thread() {

            public  void run(){
                if (!estado) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                int i = 0;
               while (true){
                   i++;
                    try {
                        final int finalI = i;
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                label.setText("Tiempo "+ finalI);
                            }
                        });
                        Thread.sleep(1000);

                        Log.i(" Tiempo",""+i);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
    }

    public void onClick(View v){
        c= new Cronometro(this, contador);
            c.start();




        /*
        MainActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10 ; i++){
                    try {
                        label.setText("Tiempo "+i);
                        Thread.sleep(100);

                        Log.i(" Tiempo",""+i);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });*/
    }

    public void detener(View v){
        contador = c.getTime();
        c.detener();
    }

    public void reset(View v){
        contador = 0;
        c.detener();
        label.setText("00:00:00");
    }

}
