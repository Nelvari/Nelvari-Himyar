package com.example.loginonlyonce.Ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.loginonlyonce.Model.ControlClass;
import com.example.loginonlyonce.R;

public class Desaingrafis extends AppCompatActivity {

    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desaingrafis);

        SharedPreferences mInfoDg = getSharedPreferences("infoDg", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = mInfoDg.edit();
        editor.putInt("userInfoDg", 1);
        editor.apply();

        Toolbar toolbar = findViewById(R.id.toolbar);
        btnLogin = findViewById(R.id.btnlogin);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("DESAIN GRAFIS");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Desaingrafis.this, ControlClass.class);
                startActivity(intent);
                finish();

            }
        });

    }
}