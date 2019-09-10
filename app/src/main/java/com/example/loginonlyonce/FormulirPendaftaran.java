package com.example.loginonlyonce;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import com.bumptech.glide.Glide;

public class FormulirPendaftaran extends AppCompatActivity {

    CardView siswa;
    CardView ortu;
    CardView sekolah;
    ImageView user;
    TextView nama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulir_pendaftaran);

        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(" ");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        siswa = findViewById(R.id.siswa);
        ortu = findViewById(R.id.ortu);
        sekolah = findViewById(R.id.sekolah);
        user = findViewById(R.id.icon);
        nama = findViewById(R.id.nama);

        SharedPreferences mlogin = getSharedPreferences("login", Context.MODE_PRIVATE);

        nama.setText(mlogin.getString("username", "missing"));

        if (getSharedPreferences("login", Context.MODE_PRIVATE) != null){

            nama.setText(mlogin.getString("data1", "missing"));

            Glide.with(this).load(mlogin.getString("data4", "missing")).into(user);

        }

        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent hi = new Intent(FormulirPendaftaran.this, MainActivity.class);
                startActivity(hi);

            }
        });

        siswa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent hi = new Intent(FormulirPendaftaran.this, DataSiswa.class);
                startActivity(hi);

            }
        });

        ortu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent hi = new Intent(FormulirPendaftaran.this, DataOrangTua.class);
                startActivity(hi);

            }
        });

        sekolah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent hi = new Intent(FormulirPendaftaran.this, DataAsalSekolah.class);
                startActivity(hi);

            }
        });



    }
}
