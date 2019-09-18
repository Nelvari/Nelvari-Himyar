package com.example.loginonlyonce.Model;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.loginonlyonce.Ui.Animasi;
import com.example.loginonlyonce.Ui.Mainmenu;

public class ControlInfoAnimasi extends AppCompatActivity {

    private SharedPreferences mInfoAnimasi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mInfoAnimasi = getSharedPreferences("login", Context.MODE_PRIVATE);

        if (mInfoAnimasi.getString("username", "").equalsIgnoreCase("")
                || mInfoAnimasi.getString("username", "") == null
                || mInfoAnimasi.getString("username", "").isEmpty()){

            Intent intent = new Intent(ControlInfoAnimasi.this, Animasi.class);
            startActivity(intent);
            finish();

        }else {

            Intent intent = new Intent(ControlInfoAnimasi.this, Mainmenu.class);
            startActivity(intent);
            finish();

        }

    }
}
