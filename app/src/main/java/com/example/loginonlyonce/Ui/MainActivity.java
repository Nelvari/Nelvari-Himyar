package com.example.loginonlyonce.Ui;

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
import com.example.loginonlyonce.R;
import com.example.loginonlyonce.SplashScreen;
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

            nama.setText(mlogin.getString("username", "missing"));
            email.setText(mlogin.getString("data2", ""));
            id.setText(mlogin.getString("data3", ""));

            Glide
                    .with(this)
                    .load(mlogin.getString("data4", ""))
                    .placeholder(R.mipmap.usermainmenu)
                    .into(imageView);

        }

        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LoginManager.getInstance().logOut();

                Intent intent = new Intent(MainActivity.this, SplashScreen.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

                PrefManager prefManager = new PrefManager(getApplicationContext());
                prefManager.setFirstTimeLaunch(true);
                finish();

            }
        });


//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.0){
//            NotificationChannel channel =
//                    new NotificationChannel("MyNotifications", "MyNotifications" , NotificationManager.IMPORTANCE_DEFAULT);
//
//            NotificationManager manager = getSystemService(NotificationManager.class);
//            manager.createNotificationChannel(channel);
//        }


    }

}
