package com.octaviano.popupmenu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.Toast;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showPopupMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(MainActivity.this,view);
       popupMenu.getMenu().add("Hola");
        popupMenu.show();
    }
}
