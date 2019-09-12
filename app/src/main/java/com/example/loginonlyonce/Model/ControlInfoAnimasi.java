package com.example.loginonlyonce.Model;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.loginonlyonce.animasi;
import com.example.loginonlyonce.mainmenu;

public class ControlInfoAnimasi extends AppCompatActivity {

    private SharedPreferences mInfoAnimasi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mInfoAnimasi = getSharedPreferences("login", Context.MODE_PRIVATE);

        if (mInfoAnimasi.getString("username", "").equalsIgnoreCase("")
                || mInfoAnimasi.getString("username", "") == null
                || mInfoAnimasi.getString("username", "").isEmpty()){

            Intent intent = new Intent(ControlInfoAnimasi.this, animasi.class);
            startActivity(intent);
            finish();

        }else {

            Intent intent = new Intent(ControlInfoAnimasi.this, mainmenu.class);
            startActivity(intent);
            finish();

        }

    }
}
