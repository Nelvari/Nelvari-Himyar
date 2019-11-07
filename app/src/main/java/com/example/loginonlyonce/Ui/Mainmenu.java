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
import com.example.loginonlyonce.Berkas;
import com.example.loginonlyonce.Model.ControlData;
import com.example.loginonlyonce.Model.ControlHistory;
import com.example.loginonlyonce.R;

public class Mainmenu extends AppCompatActivity {

    LinearLayout btnDaftar;
    LinearLayout btnCetakData;
    ImageView ivProfile;
    LinearLayout berkasList;
    LinearLayout Inbox;
    LinearLayout btnInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainmenu);

        ivProfile = findViewById(R.id.ivProfile);
        btnCetakData = findViewById(R.id.btnCetakData);
        btnDaftar = findViewById(R.id.btnDaftar);
        berkasList = findViewById(R.id.berkasList);
        Inbox = findViewById(R.id.Inbox);
        btnInfo = findViewById(R.id.btnInfo);

        btnDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Mainmenu.this, ControlData.class);
                startActivity(intent);

            }
        });

        btnCetakData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Mainmenu.this, ControlHistory.class);
                //Intent intent = new Intent(Mainmenu.this, History.class);
                startActivity(intent);
            }
        });


        ivProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Mainmenu.this, Profilku.class);
                startActivity(intent);

            }
        });

        berkasList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Mainmenu.this, Berkas.class);
                startActivity(intent);

            }
        });

        Inbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Mainmenu.this, InboxActivity.class);
                startActivity(intent);
            }
        });

        btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Mainmenu.this, Help.class);
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
