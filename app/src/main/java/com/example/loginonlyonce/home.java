package com.example.loginonlyonce;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.loginonlyonce.Model.ControlClass;

public class home extends AppCompatActivity {

    CardView prduksi, persiapan, animasi, rpl, dkv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        prduksi = findViewById(R.id.produksi);
        persiapan = findViewById(R.id.persiapan);
        animasi = findViewById(R.id.animasi);
        rpl = findViewById(R.id.rpl);
        dkv = findViewById(R.id.dkv);

        prduksi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent hi = new Intent(home.this, Produksi.class);
                startActivity(hi);

            }
        });

        persiapan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent hi = new Intent(home.this, ControlClass.class);
                startActivity(hi);

            }
        });

        animasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent hi = new Intent(home.this, ControlClass.class);
                startActivity(hi);

            }
        });

        dkv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent hi = new Intent(home.this, DKV.class);
                startActivity(hi);

            }
        });

        rpl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent hi = new Intent(home.this, RPL.class);
                startActivity(hi);

            }
        });

    }
}
