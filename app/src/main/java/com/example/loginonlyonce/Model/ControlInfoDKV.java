package com.example.loginonlyonce.Model;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.loginonlyonce.DKV;
import com.example.loginonlyonce.FormulirPendaftaran;

public class ControlInfoDKV extends AppCompatActivity {

    private SharedPreferences mInfoDKV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mInfoDKV = getSharedPreferences("infoDKV", Context.MODE_PRIVATE);

        if (mInfoDKV.getInt("userInfoDKV", 0) == 0){

            Intent intent = new Intent(ControlInfoDKV.this, DKV.class);
            startActivity(intent);
            finish();

        }else {

            Intent intent = new Intent(ControlInfoDKV.this, FormulirPendaftaran.class);
            startActivity(intent);
            finish();

        }

    }
}
