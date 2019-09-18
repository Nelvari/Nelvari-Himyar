package com.example.loginonlyonce.Model;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.loginonlyonce.Ui.Desaingrafis;
import com.example.loginonlyonce.Ui.Mainmenu;

public class ControlInfoDg extends AppCompatActivity {

    private SharedPreferences mInfoDg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mInfoDg = getSharedPreferences("login", Context.MODE_PRIVATE);

        if (mInfoDg.getString("username", "").equalsIgnoreCase("")
                || mInfoDg.getString("username", "") == null
                || mInfoDg.getString("username", "").isEmpty()){

            Intent intent = new Intent(ControlInfoDg.this, Desaingrafis.class);
            startActivity(intent);
            finish();

        }else {

            Intent intent = new Intent(ControlInfoDg.this, Mainmenu.class);
            startActivity(intent);
            finish();

        }

    }
}
