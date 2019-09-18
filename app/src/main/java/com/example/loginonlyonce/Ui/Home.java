package com.example.loginonlyonce.Ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.loginonlyonce.Model.ControlInfo;
import com.example.loginonlyonce.Model.ControlInfoAnimasi;
import com.example.loginonlyonce.Model.ControlInfoDKV;
import com.example.loginonlyonce.Model.ControlInfoDg;
import com.example.loginonlyonce.Model.ControlInfoRPL;
import com.example.loginonlyonce.R;

public class Home extends AppCompatActivity {

    CardView prduksi, persiapan, animasi, rpl, dkv;
    SharedPreferences mLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mLogin = getSharedPreferences("login", Context.MODE_PRIVATE);

        prduksi = findViewById(R.id.produksi);
        persiapan = findViewById(R.id.persiapan);
        animasi = findViewById(R.id.animasi);
        rpl = findViewById(R.id.rpl);
        dkv = findViewById(R.id.dkv);

        prduksi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences.Editor editor = mLogin.edit();
                editor.putString("jurusan", "Produksi");
                editor.apply();

                Intent hi = new Intent(Home.this, ControlInfo.class);
                startActivity(hi);

            }
        });

        persiapan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences.Editor editor = mLogin.edit();
                editor.putString("jurusan", "Persiapan");
                editor.apply();

                Intent hi = new Intent(Home.this, ControlInfoDg.class);
                startActivity(hi);

            }
        });

        animasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences.Editor editor = mLogin.edit();
                editor.putString("jurusan", "Animasi");
                editor.apply();

                Intent hi = new Intent(Home.this, ControlInfoAnimasi.class);
                startActivity(hi);

            }
        });

        dkv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences.Editor editor = mLogin.edit();
                editor.putString("jurusan", "DKV");
                editor.apply();

                Intent hi = new Intent(Home.this, ControlInfoDKV.class);
                startActivity(hi);

            }
        });

        rpl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences.Editor editor = mLogin.edit();
                editor.putString("jurusan", "RPL");
                editor.apply();

                Intent hi = new Intent(Home.this, ControlInfoRPL.class);
                startActivity(hi);

            }
        });

    }
}
