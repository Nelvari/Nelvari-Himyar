package com.example.loginonlyonce.Model;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.loginonlyonce.FormulirPendaftaran;
import com.example.loginonlyonce.desaingrafis;

public class ControlInfoDg extends AppCompatActivity {

    private SharedPreferences mInfoDg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mInfoDg = getSharedPreferences("infoDg", Context.MODE_PRIVATE);

        if (mInfoDg.getInt("userInfoDg", 0) == 0){

            Intent intent = new Intent(ControlInfoDg.this, desaingrafis.class);
            startActivity(intent);
            finish();

        }else {

            Intent intent = new Intent(ControlInfoDg.this, FormulirPendaftaran.class);
            startActivity(intent);
            finish();

        }

    }
}
