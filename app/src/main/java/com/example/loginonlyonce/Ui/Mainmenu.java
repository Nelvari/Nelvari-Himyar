package com.example.loginonlyonce.Ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.loginonlyonce.R;

public class Mainmenu extends AppCompatActivity {

    LinearLayout btnDaftar;
    LinearLayout btnCetakData;
    ImageView ivProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainmenu);

        ivProfile = findViewById(R.id.ivProfile);
        btnCetakData = findViewById(R.id.btnCetakData);
        btnDaftar = findViewById(R.id.btnDaftar);

        btnDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Mainmenu.this, Berkasfile.class);
                startActivity(intent);

            }
        });

        btnCetakData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Mainmenu.this,Berkasfile.class);
                startActivity(intent);
            }
        });


        ivProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Mainmenu.this, MainActivity.class);
                startActivity(intent);

            }
        });

        SharedPreferences mlogin = getSharedPreferences("login", Context.MODE_PRIVATE);

        Glide
                .with(this)
                .load(mlogin.getString("data4", ""))
                .placeholder(R.mipmap.usermainmenu)
                .into(ivProfile);

    }
}
