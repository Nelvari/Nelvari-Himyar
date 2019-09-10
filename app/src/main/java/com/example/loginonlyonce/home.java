package com.example.loginonlyonce;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.loginonlyonce.Model.ControlInfo;
import com.example.loginonlyonce.Model.ControlInfoAnimasi;
import com.example.loginonlyonce.Model.ControlInfoDKV;
import com.example.loginonlyonce.Model.ControlInfoDg;
import com.example.loginonlyonce.Model.ControlInfoRPL;

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

                Intent hi = new Intent(home.this, ControlInfo.class);
                startActivity(hi);

            }
        });

        persiapan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent hi = new Intent(home.this, ControlInfoDg.class);
                startActivity(hi);

            }
        });

        animasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent hi = new Intent(home.this, ControlInfoAnimasi.class);
                startActivity(hi);

            }
        });

        dkv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent hi = new Intent(home.this, ControlInfoDKV.class);
                startActivity(hi);

            }
        });

        rpl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent hi = new Intent(home.this, ControlInfoRPL.class);
                startActivity(hi);

            }
        });

    }
}
