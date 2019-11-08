package com.example.loginonlyonce.Ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

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
                editor.putString("jurusan", "Produksi Grafika");
                editor.apply();

                Intent hi = new Intent(Home.this, Produksi.class);
                startActivity(hi);
                finish();

            }
        });

        persiapan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences.Editor editor = mLogin.edit();
                editor.putString("jurusan", "Desain Grafika");
                editor.apply();

                Intent hi = new Intent(Home.this, Desaingrafis.class);
                startActivity(hi);
                finish();

            }
        });

        animasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences.Editor editor = mLogin.edit();
                editor.putString("jurusan", "Animasi");
                editor.apply();

                Intent hi = new Intent(Home.this, Animasi.class);
                startActivity(hi);
                finish();

            }
        });

        dkv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences.Editor editor = mLogin.edit();
                editor.putString("jurusan", "Desain Komunikasi Visual");
                editor.apply();

                Intent hi = new Intent(Home.this, DKV.class);
                startActivity(hi);
                finish();

            }
        });

        rpl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences.Editor editor = mLogin.edit();
                editor.putString("jurusan", "Rekayasa Perangkat Lunak");
                editor.apply();

                Intent hi = new Intent(Home.this, RPL.class);
                startActivity(hi);
                finish();

            }
        });

    }
}
