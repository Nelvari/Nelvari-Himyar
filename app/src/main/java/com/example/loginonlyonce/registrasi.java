package com.example.loginonlyonce;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONObject;


public class registrasi extends AppCompatActivity {

    EditText txtusername;
    EditText notelp;
    EditText email;
    EditText txtpassword;
    EditText konfirmpass;
    Button   btnbuat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrasi);
        txtusername = findViewById(R.id.txtusername);
        notelp = findViewById(R.id.notelp);
        email = findViewById(R.id.email);
        txtpassword = findViewById(R.id.txtpassword);
        konfirmpass = findViewById(R.id.konfirmpass);

         btnbuat = findViewById(R.id.btnbuat);


        AndroidNetworking.post("http://api-ppdb.smkrus.com/api/v1/login")
                .addBodyParameter("username", "txtusername")
                .addBodyParameter("password", "txtpassword")
                .addBodyParameter("role", "txtpassword")
                .setTag("test")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("sheca", "onResponse: ");
                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });


        btnbuat = findViewById(R.id.btnbuat);
        btnbuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(registrasi.this, Login.class);
                startActivity(intent);

            }
        });


    }
}
