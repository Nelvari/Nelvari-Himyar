package com.example.loginonlyonce.Ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.loginonlyonce.R;

import org.json.JSONException;
import org.json.JSONObject;


public class Registrasi extends AppCompatActivity {

    EditText txtusername;
    SharedPreferences mLogin;
    EditText txtEmail;
    EditText txtnotelp;
    EditText txtpassword;
    EditText konfirmpass;
    Button btnbuat;

    int id;
    String username;
    String nohp;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrasi);
        txtusername = findViewById(R.id.txtUsername);
        txtpassword = findViewById(R.id.txtPassword);
        txtnotelp = findViewById(R.id.notelp);
        konfirmpass = findViewById(R.id.konfirmpass);
        btnbuat = findViewById(R.id.btnbuat);
        btnbuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d("tes", "onClick: ");

                mLogin = getSharedPreferences("login", Context.MODE_PRIVATE);
                if (txtusername.getText().toString().isEmpty() || txtpassword.getText().toString().isEmpty()) {
                    Toast.makeText(Registrasi.this, "please fill my heart first to send a request :(", Toast.LENGTH_SHORT).show();

                    Log.d("ten", "onClick: ");

                } else {

                    Log.d("net", "onClick: ");

                    AndroidNetworking.post("http://api-ppdb.smkrus.com/api/v1/register")
                            .addBodyParameter("nama", txtusername.getText().toString())
                            .addBodyParameter("username", txtusername.getText().toString())
                            .addBodyParameter("password", txtpassword.getText().toString())
                            .addBodyParameter("no_hp", txtnotelp.getText().toString())
                            .addBodyParameter("role", "1")
                            .setTag("test")
                            .setPriority(Priority.MEDIUM)
                            .build()
                            .getAsJSONObject(new JSONObjectRequestListener() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    // do anything with response
                                    try {
                                        String status = response.getString("STATUS");
                                        if (status.equalsIgnoreCase("SUCCESS")) {

                                            JSONObject getdata=response.getJSONObject("PAYLOAD");
                                            password=getdata.getString("u_password");
                                            nohp=getdata.getString("u_no_hp");
                                            username=getdata.getString("u_username");
                                            id = getdata.getInt("u_id");

                                            SharedPreferences.Editor editor = mLogin.edit();
                                            editor.putString("username", username);
                                            editor.putString("nohp", nohp);
                                            editor.putString("password", password);
                                            editor.putInt("userid", id);
                                            editor.apply();
                                            Intent intent = new Intent(Registrasi.this, Login.class);
                                            startActivity(intent);
                                            finish();
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }

                                @Override
                                public void onError(ANError error) {
                                    Log.d("gagal login", "onResponse: " + error.toString());
                                    Log.d("gagal login", "onResponse: " + error.getErrorBody());
                                    Log.d("gagal login", "onResponse: " + error.getErrorCode());
                                    Log.d("gagal login", "onResponse: " + error.getErrorDetail());
                                }
                            });


                }

            }
        });
    }
}




