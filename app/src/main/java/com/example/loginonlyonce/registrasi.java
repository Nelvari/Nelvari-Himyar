package com.example.loginonlyonce;

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

import org.json.JSONException;
import org.json.JSONObject;


public class registrasi extends AppCompatActivity {

    EditText txtusername;

    EditText Email;
    EditText notelp;
    String nohp = "";
    public String email;
    EditText txtpassword;
    EditText konfirmpass;
    Button btnbuat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrasi);
        txtusername = findViewById(R.id.txtUsername);
        txtpassword = findViewById(R.id.txtPassword);
        konfirmpass = findViewById(R.id.konfirmpass);
        btnbuat = findViewById(R.id.btnbuat);


        btnbuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences mLogin = getSharedPreferences("login", Context.MODE_PRIVATE);
                if (txtusername.getText().toString().isEmpty() || txtpassword.getText().toString().isEmpty()) {
                    Toast.makeText(registrasi.this, "please fill my heart first to send a request :(", Toast.LENGTH_SHORT).show();
                } if (txtpassword == konfirmpass)
                    AndroidNetworking.post("http://api-ppdb.smkrus.com/api/v1/register")
                            .addBodyParameter("username", txtusername.getText().toString())
                            .addBodyParameter("password", txtpassword.getText().toString())
                            .addBodyParameter("email", Email.getText().toString())
                            .addBodyParameter("no_hp", notelp.getText().toString())
                            .addBodyParameter("role", "user")
                            .setTag("test")
                            .setPriority(Priority.MEDIUM)
                            .build()
                            .getAsJSONObject(new JSONObjectRequestListener() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    // do anything with response
                                    try {
                                        String status = response.getString("STATUS");
                                        if (status.equalsIgnoreCase("SUCCES")) {
                                            JSONObject getdata = response.getJSONObject("PAYLOAD");
                                            nohp = getdata.getString("u_no_hp");
                                            email = getdata.getString("u_email");

                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }

                                @Override
                                public void onError(ANError error) {
                                    Log.d("gagal login", "onResponse: " + error.toString());
                                }
                            });

                SharedPreferences.Editor editor = mLogin.edit();
                editor.putString("username", txtusername.getText().toString());
                editor.putString("nohp", nohp);
                editor.putString("email", email);
                editor.putString("data1", txtusername.getText().toString());
                editor.putInt("userid", getTaskId());
                editor.apply();
                Intent intent = new Intent(registrasi.this, FormulirPendaftaran.class);
                startActivity(intent);
                finish();
            }
        });
    }
}




