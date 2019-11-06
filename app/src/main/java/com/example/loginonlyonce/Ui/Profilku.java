package com.example.loginonlyonce.Ui;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.loginonlyonce.Model.PrefManager;
import com.example.loginonlyonce.R;
import com.example.loginonlyonce.SplashScreen;
import com.facebook.login.LoginManager;

public class Profilku extends AppCompatActivity {

    Button log;
    ImageView imageView;
    TextView email;
    TextView nama;
    SharedPreferences mlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        log = findViewById(R.id.logOut);
        imageView = findViewById(R.id.icon);
        nama = findViewById(R.id.nama);
        email = findViewById(R.id.email);


        mlogin = getSharedPreferences("login", Context.MODE_PRIVATE);

        if (getSharedPreferences("login", Context.MODE_PRIVATE) != null){

            nama.setText(mlogin.getString("username", "missing"));
            email.setText(mlogin.getString("data2", "-"));

            Glide
                    .with(this)
                    .load(mlogin.getString("data4", ""))
                    .placeholder(R.mipmap.usermainmenu)
                    .into(imageView);

        }

        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final DialogInterface.OnClickListener dialog =new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        switch (i){
                            case DialogInterface.BUTTON_POSITIVE:
                                LoginManager.getInstance().logOut();

                                Intent intent = new Intent(Profilku.this, SplashScreen.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);

                                PrefManager prefManager = new PrefManager(getApplicationContext());
                                prefManager.setFirstTimeLaunch(true);
                                finish();

                                SharedPreferences.Editor editor = mlogin.edit();
                                editor.clear();
                                editor.commit();
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:

                                break;
                        }

                    }

                };

                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setMessage("Apakah anda yakin ingin logout?").setPositiveButton("Ya", dialog)
                        .setTitle("Konfirmasi logout")
                        .setNegativeButton("Tidak", dialog).show();


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
