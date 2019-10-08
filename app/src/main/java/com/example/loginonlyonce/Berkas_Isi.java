package com.example.loginonlyonce;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class Berkas_Isi extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_berkas__isi);

        Toolbar toolbar = findViewById(R.id.toolbarBerkasFile);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Berkas");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
