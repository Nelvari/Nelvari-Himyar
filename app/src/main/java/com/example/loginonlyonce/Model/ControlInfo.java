package com.example.loginonlyonce.Model;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.loginonlyonce.Produksi;
import com.example.loginonlyonce.mainmenu;

public class ControlInfo extends AppCompatActivity {

    private SharedPreferences mInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mInfo = getSharedPreferences("login", Context.MODE_PRIVATE);

        if (mInfo.getString("username", "").equalsIgnoreCase("")
                || mInfo.getString("username", "") == null
                || mInfo.getString("username", "").isEmpty()){

            Intent intent = new Intent(ControlInfo.this, Produksi.class);
            startActivity(intent);
            finish();

        }else {

            Intent intent = new Intent(ControlInfo.this, mainmenu.class);
            startActivity(intent);
            finish();

        }

    }
}
