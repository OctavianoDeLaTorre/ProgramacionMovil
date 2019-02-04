package com.octaviano.gridlayout_calculadora;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView lblResultado;
    private TextView lblOperacion;
    private String resultado ;
    private String numero;
    private double num1;
    private double num2;
    private int operacion;
    private final int SUMA = 1;
    private final int RESTA = 2;
    private final int MULTIPICACION = 3;
    private final int DIVISION = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lblResultado = findViewById(R.id.lblResultado);
        lblOperacion = findViewById(R.id.lblOperacion);
        ((Button)findViewById(R.id.Clean)).setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                lblResultado.setText("");
                lblOperacion.setText("");
                return true;
            }
        });
    }

    public void onclikNumero(View v){
        resultado = lblResultado.getText().toString();
        numero = ((Button) findViewById(v.getId())).getText().toString();
        lblOperacion.setText(lblOperacion.getText().toString()+numero);
        if (resultado.equals("0")) {
            lblResultado.setText(numero);
        } else{
            lblResultado.setText(resultado+numero);
        }

    }

    public void onclickOperacion(View v){
        numero = lblResultado.getText().toString();
        if(numero.length() > 0 && !numero.equals(".")){
            num1 = Double.parseDouble(numero);
            switch (v.getId()){
                case R.id.Suma:
                    lblOperacion.setText(lblOperacion.getText().toString()+" + ");
                    operacion = SUMA;
                    break;
                case R.id.Resta:
                    lblOperacion.setText(lblOperacion.getText().toString()+" - ");
                    operacion = RESTA;
                    break;
                case R.id.Multiplica:
                    lblOperacion.setText(lblOperacion.getText().toString()+" * ");
                    operacion = MULTIPICACION;
                    break;
                case R.id.Divide:
                    lblOperacion.setText(lblOperacion.getText().toString()+" / ");
                    operacion = DIVISION;
                    break;
            }
            lblResultado.setText("");
        } else {
            Toast.makeText(this,"Debes ingresar un numero",Toast.LENGTH_LONG).show();
        }
    }

    public void onclickIgual(View v){
        numero = lblResultado.getText().toString();
        if(numero.length() > 0 && !numero.equals(".") && operacion > 0){
            num2 = Double.parseDouble(numero);
            switch (operacion){
                case SUMA:
                    lblResultado.setText(String.valueOf(num1+num2));
                    break;
                case RESTA:
                    lblResultado.setText(String.valueOf(num1-num2));
                    break;
                case MULTIPICACION:
                    lblResultado.setText(String.valueOf(num1*num2));
                    break;
                case DIVISION:
                    lblResultado.setText(String.valueOf(num1/num2));
                    break;
            }
            lblOperacion.setText(lblOperacion.getText().toString() + "=");
            operacion = 0;
        }else {
            Toast.makeText(this,"Debes ingresar un numero",Toast.LENGTH_LONG).show();
        }
    }

    public void onclickCambioSigno(View v){
        numero = lblResultado.getText().toString();
        if(numero.length() > 0 && !numero.equals(".")){
            num1 = Double.parseDouble(numero)*-1;
            lblResultado.setText(String.valueOf(num1));
            lblOperacion.setText(String.valueOf(num1));
            lblOperacion.setText("");
        }
    }

    public void onclickBorrar(View v){
        numero = lblResultado.getText().toString();
        if(numero.length() > 0){
            lblResultado.setText(numero.substring(0,numero.length()-1));
            lblOperacion.setText(numero.substring(0,numero.length()-1));
        }
    }

    public void onclickPunto(View v){
        numero = lblResultado.getText().toString();
        if(!numero.contains(".")){
            lblResultado.setText(numero+".");
            lblOperacion.setText(lblOperacion.getText().toString()+".");
        }

    }
}
