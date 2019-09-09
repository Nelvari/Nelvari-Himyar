package com.example.loginonlyonce.Model;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.loginonlyonce.FormulirPendaftaran;
import com.example.loginonlyonce.Login;

public class ControlClass extends AppCompatActivity {

    private SharedPreferences mlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mlogin = getSharedPreferences("login", Context.MODE_PRIVATE);

        if (mlogin.getInt("userid", 0) == 0){

            Intent in = new Intent(ControlClass.this, Login.class);
            startActivity(in);
            finish();

        }
        else {

            Intent in = new Intent(ControlClass.this, FormulirPendaftaran.class);
            startActivity(in);
            finish();

        }

    }
}
