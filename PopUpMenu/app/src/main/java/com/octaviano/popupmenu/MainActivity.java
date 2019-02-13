package com.octaviano.popupmenu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private PopupMenu.OnMenuItemClickListener mOnMenuItemClickListener = new
            PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.menu_reply:
                            item.setChecked(!item.isChecked());
                            Toast.makeText(MainActivity.this, "Reply", Toast.LENGTH_SHORT).show();
                            return true;
                        case R.id.menu_reply_all:
                            item.setChecked(!item.isChecked());
                            Toast.makeText(MainActivity.this,"Reply All",Toast.LENGTH_SHORT).show();
                            return true;
                        case R.id.menu_forward:
                            item.setChecked(!item.isChecked());
                            Toast.makeText(MainActivity.this,"Forward", Toast.LENGTH_SHORT).show();
                            return true;
                        default:
                            return false;
                    }
                }
            };

    PopupMenu popupMenu;
    EditText txtCampo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        popupMenu = new PopupMenu(MainActivity.this,findViewById(R.id.imageButtonReply));
        txtCampo = findViewById(R.id.txtCampo);
        popupMenu.inflate(R.menu.menu_popup);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_reply:
                        item.setChecked(!item.isChecked());
                        Toast.makeText(MainActivity.this, "Reply", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.menu_reply_all:
                        item.setChecked(!item.isChecked());
                        Toast.makeText(MainActivity.this,"Reply All",Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.menu_forward:
                        item.setChecked(!item.isChecked());
                        Toast.makeText(MainActivity.this,"Forward", Toast.LENGTH_SHORT).show();
                        return true;
                    default:
                        return false;
                }

            }
        });

    }

    public void showPopupMenu(View view) {
        popupMenu.show();
    }

    public void agregarElemento(View v){
        popupMenu.getMenu().add(txtCampo.getText().toString());
    }

    public void limpiar(View v){
        popupMenu.getMenu().clear();
    }
}
