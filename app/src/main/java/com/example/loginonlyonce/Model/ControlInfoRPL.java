package com.example.loginonlyonce.Model;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.loginonlyonce.FormulirPendaftaran;
import com.example.loginonlyonce.RPL;

public class ControlInfoRPL extends AppCompatActivity {

    private SharedPreferences mInfoRPL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mInfoRPL = getSharedPreferences("login", Context.MODE_PRIVATE);

        if (mInfoRPL.getString("username", "").equalsIgnoreCase("")
                || mInfoRPL.getString("username", "") == null
                || mInfoRPL.getString("username", "").isEmpty()){

            Intent intent = new Intent(ControlInfoRPL.this, RPL.class);
            startActivity(intent);
            finish();

        }else {

            Intent intent = new Intent(ControlInfoRPL.this, FormulirPendaftaran.class);
            startActivity(intent);
            finish();

        }

    }
}
