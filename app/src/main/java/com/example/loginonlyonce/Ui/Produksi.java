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

public class Produksi extends AppCompatActivity {

    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produksi);

        SharedPreferences mInfo = getSharedPreferences("info", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = mInfo.edit();
        editor.putInt("userInfo", 1);
        editor.apply();

        btnLogin = findViewById(R.id.btnlogin);
        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("PRODUKSI");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Produksi.this, ControlClass.class);
                startActivity(intent);
                finish();

            }
        });

    }
}
