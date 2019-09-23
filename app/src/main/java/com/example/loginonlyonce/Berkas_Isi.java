package com.example.loginonlyonce;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

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
