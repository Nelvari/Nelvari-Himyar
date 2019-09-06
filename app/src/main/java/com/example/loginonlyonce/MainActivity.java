package com.example.loginonlyonce;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.loginonlyonce.Model.PrefManager;
import com.facebook.login.LoginManager;

public class MainActivity extends AppCompatActivity {

    Button log;
    ImageView imageView;
    TextView email;
    TextView id;
    TextView nama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        log = findViewById(R.id.logOut);
        imageView = findViewById(R.id.icon);
        nama = findViewById(R.id.nama);
        email = findViewById(R.id.email);
        id = findViewById(R.id.id);

        SharedPreferences mlogin = getSharedPreferences("login", Context.MODE_PRIVATE);

        nama.setText(mlogin.getString("username", "missing"));

        if (getSharedPreferences("login", Context.MODE_PRIVATE) != null){

            nama.setText(mlogin.getString("data1", "missing"));
            email.setText(mlogin.getString("data2", "missing"));
            id.setText(mlogin.getString("data3", "missing"));

            Glide.with(this).load(mlogin.getString("data4", "missing")).into(imageView);

        }

        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences mLogin = getSharedPreferences("login", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = mLogin.edit();
                editor.clear();
                editor.apply();

                LoginManager.getInstance().logOut();

                Intent intent = new Intent(MainActivity.this, SplashScreen.class);
                startActivity(intent);
                finish();

                PrefManager prefManager = new PrefManager(getApplicationContext());
                prefManager.setFirstTimeLaunch(true);
                finish();

            }
        });

    }

}
