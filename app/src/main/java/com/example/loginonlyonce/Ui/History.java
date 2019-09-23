package com.example.loginonlyonce.Ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.loginonlyonce.R;

public class History extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cetakkartutes);

        Toolbar toolbar = findViewById(R.id.toolbarhistory);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Cetak Data");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

}
