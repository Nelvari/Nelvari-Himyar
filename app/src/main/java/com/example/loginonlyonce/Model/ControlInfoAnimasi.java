package com.example.loginonlyonce.Model;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.loginonlyonce.FormulirPendaftaran;
import com.example.loginonlyonce.animasi;

public class ControlInfoAnimasi extends AppCompatActivity {

    private SharedPreferences mInfoAnimasi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mInfoAnimasi = getSharedPreferences("infoAnimasi", Context.MODE_PRIVATE);

        if (mInfoAnimasi.getInt("userInfoAnimasi", 0) == 0){

            Intent intent = new Intent(ControlInfoAnimasi.this, animasi.class);
            startActivity(intent);
            finish();

        }else {

            Intent intent = new Intent(ControlInfoAnimasi.this, FormulirPendaftaran.class);
            startActivity(intent);
            finish();

        }

    }
}
