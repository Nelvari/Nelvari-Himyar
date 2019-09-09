package com.example.loginonlyonce;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

public class FormulirPendaftaran extends AppCompatActivity {

    CardView siswa;
    CardView ortu;
    CardView sekolah;

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
