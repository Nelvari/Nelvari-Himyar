package com.example.loginonlyonce.Model;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.loginonlyonce.FormulirPendaftaran;
import com.example.loginonlyonce.Produksi;

public class ControlInfo extends AppCompatActivity {

    private SharedPreferences mInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mInfo = getSharedPreferences("info", Context.MODE_PRIVATE);

        if (mInfo.getInt("userInfo", 0) == 0){

            Intent intent = new Intent(ControlInfo.this, Produksi.class);
            startActivity(intent);
            finish();

        }else {

            Intent intent = new Intent(ControlInfo.this, FormulirPendaftaran.class);
            startActivity(intent);
            finish();

        }

    }
}
