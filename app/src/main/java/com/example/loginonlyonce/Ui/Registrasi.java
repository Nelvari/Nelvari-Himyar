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
                mLogin = getSharedPreferences("login", Context.MODE_PRIVATE);
                if (txtusername.getText().toString().isEmpty() || txtpassword.getText().toString().isEmpty()) {
                    Toast.makeText(Registrasi.this, "please fill my heart first to send a request :(", Toast.LENGTH_SHORT).show();
                } else if (txtpassword == konfirmpass){
                    AndroidNetworking.post("http://api-ppdb.smkrus.com/api/v1/register")
                            .addBodyParameter("username", txtusername.getText().toString())
                            .addBodyParameter("password", txtpassword.getText().toString())
                            .addBodyParameter("email", txtEmail.getText().toString())
                            .addBodyParameter("no_hp", txtnotelp.getText().toString())
                            .addBodyParameter("role", "superadmin")
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
                                            SharedPreferences.Editor editor = mLogin.edit();
                                            editor.putString("username", txtusername.getText().toString());
                                            editor.putString("nohp", txtnotelp.getText().toString() );
                                            editor.putString("email", txtEmail.getText().toString());
                                            editor.putString("data1", txtusername.getText().toString());
                                            editor.putInt("userid", getTaskId());
                                            editor.apply();
                                            Intent intent = new Intent(Registrasi.this, Mainmenu.class);
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
                                }
                            });


                }

            }
        });
    }
}




