package com.example.loginonlyonce.Ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import com.example.loginonlyonce.R;

public class History extends AppCompatActivity {

    CardView cvKartuPeserta, cvKartuUjian;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cetakkartutes);

        Toolbar toolbar = findViewById(R.id.toolbarhistory);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Cetak Data");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        cvKartuPeserta = findViewById(R.id.cvKartuPeserta);
        cvKartuUjian = findViewById(R.id.cvKartuUjian);

        cvKartuPeserta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(History.this, KartuPeserta.class);
                startActivity(intent);
            }
        });

        cvKartuUjian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent =  new Intent(History.this, KartuUjian.class);
                startActivity(intent);
            }
        });

    }

}
