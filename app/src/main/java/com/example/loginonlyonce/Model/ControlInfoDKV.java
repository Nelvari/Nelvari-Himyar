package com.example.loginonlyonce.Model;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.loginonlyonce.DKV;
import com.example.loginonlyonce.mainmenu;

public class ControlInfoDKV extends AppCompatActivity {

    private SharedPreferences mInfoDKV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mInfoDKV = getSharedPreferences("login", Context.MODE_PRIVATE);

        if (mInfoDKV.getString("username", "").equalsIgnoreCase("")
                || mInfoDKV.getString("username", "") == null
                || mInfoDKV.getString("username", "").isEmpty()){

            Intent intent = new Intent(ControlInfoDKV.this, DKV.class);
            startActivity(intent);
            finish();

        }else {

            Intent intent = new Intent(ControlInfoDKV.this, mainmenu.class);
            startActivity(intent);
            finish();

        }

    }
}
