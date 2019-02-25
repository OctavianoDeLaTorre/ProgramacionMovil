package com.octaviano.cronometro;

import android.app.Activity;
import android.util.Log;
import android.widget.TextView;

public class Cronometro extends Thread{
    private final Activity a;
    private TextView label;
    private volatile boolean running;
    private int time = 0;

    Cronometro(Activity a, int time){
        this.a = a;
        this.time = time;
        running = true;
    }

    public int getTime(){
        return  time;
    }

    public void detener(){
        running = false;
    }

    public void reiniciar(){
        running = false;
        time = 0;
        a.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                label = a.findViewById(R.id.label);
                label.setText("Tiempo "+ 0);
            }
        });
    }
    @Override
    public void run() {
        while (running){
            time++;
            try {
                final int finalI = time;
                a.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        label = a.findViewById(R.id.label);
                        int s = finalI % 60;
                        int m = finalI / 60;
                        int h = m / 60;
                        label.setText(h+":"+m+":"+s);
                    }
                });
                Thread.sleep(1000);

                Log.i(" Tiempo",""+time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
