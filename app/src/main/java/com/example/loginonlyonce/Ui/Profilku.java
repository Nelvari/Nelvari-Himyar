package com.example.loginonlyonce.Ui;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.bumptech.glide.Glide;
import com.example.loginonlyonce.Model.ControlData;
import com.example.loginonlyonce.Model.PrefManager;
import com.example.loginonlyonce.R;
import com.example.loginonlyonce.SplashScreen;
import com.facebook.login.LoginManager;

import org.json.JSONException;
import org.json.JSONObject;

public class Profilku extends AppCompatActivity {

    Button log, btnInbok;
    ImageView imageView;
    TextView email;
    TextView nama, txtStatus;
    SharedPreferences mlogin;
    Boolean isdaftar=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        log = findViewById(R.id.logOut);
        imageView = findViewById(R.id.icon);
        nama = findViewById(R.id.nama);
        email = findViewById(R.id.email);
        txtStatus = findViewById(R.id.txtStatus);
        btnInbok = findViewById(R.id.btnInbok);
        btnInbok.setEnabled(false);


        mlogin = getSharedPreferences("login", Context.MODE_PRIVATE);

        if (getSharedPreferences("login", Context.MODE_PRIVATE) != null){

            nama.setText(mlogin.getString("username", "missing"));
            if (mlogin.getString("data2", "-").equalsIgnoreCase("-")){

                email.setText(mlogin.getString("email", "-"));
            }else {
                email.setText(mlogin.getString("data2", "-"));
            }

            Glide
                    .with(this)
                    .load(mlogin.getString("data4", ""))
                    .placeholder(R.mipmap.usermainmenu)
                    .into(imageView);

        }

        AndroidNetworking.get("http://api-ppdb.smkrus.com/api/v1/cek-daftar?id=" + mlogin.getInt("userid", 0))
                .setTag("test")
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String status = response.getString("STATUS");
                            if (status.equalsIgnoreCase("SUCCESS")){
                                txtStatus.setText("Anda belum mendafatar untuk menjadi peserta didik baru");
                                isdaftar=false;
                                btnInbok.setText("Daftar sekarang");
                                btnInbok.setEnabled(true);
                            } else if (status.equalsIgnoreCase("ERROR")){
                                JSONObject jsonObject = response.getJSONObject("PAYLOAD");
                                btnInbok.setEnabled(true);
                                btnInbok.setText("Cek di sini");
                                txtStatus.setText("Anda sudah mendaftar di jurusan : \n" + jsonObject.getString("sw_jurusan"));
                                Log.d("status", "onResponse: " + jsonObject.getString("sw_jurusan") + mlogin.getInt("userid", 0));
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });

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

        btnInbok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btnInbok.getText().equals("Daftar sekarang")){

                    Intent intent = new Intent(Profilku.this, ControlData.class);
                    startActivity(intent);

                }else {

                    Intent intent = new Intent(Profilku.this, InboxActivity.class);
                    startActivity(intent);

                }


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
