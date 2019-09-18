package com.example.loginonlyonce;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class History extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        Toolbar toolbar = findViewById(R.id.toolbarhistory);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Cetak Data");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

}
