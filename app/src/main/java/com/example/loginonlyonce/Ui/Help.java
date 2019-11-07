package com.example.loginonlyonce.Ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.loginonlyonce.R;

public class Help extends AppCompatActivity {

    Button btninbox;
    Button btnberkas;
    Button btncetak;
    Button daftar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        Toolbar toolbar = findViewById(R.id.toolbarhelp);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Bantuan");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btninbox=(Button)findViewById(R.id.btninbox);
        btnberkas=(Button)findViewById(R.id.btnberkas);
        btncetak=(Button)findViewById(R.id.btncetak);
        daftar=(Button)findViewById(R.id.btndaftar);



        btninbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Help.this, DetailInfoInbox.class);
                startActivity(intent);

            }
        });

        btnberkas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Help.this, DetailInfoBerkas.class);
                startActivity(intent);

            }
        });

        btncetak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Help.this, DetailInfoCetakData.class);
                startActivity(intent);

            }
        });

        daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Help.this, DetailInfoDaftar.class);
                startActivity(intent);

            }
        });

    }
}
