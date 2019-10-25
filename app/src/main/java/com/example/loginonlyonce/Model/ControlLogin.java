package com.example.loginonlyonce.Model;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.loginonlyonce.Ui.Login;
import com.example.loginonlyonce.Ui.Mainmenu;

public class ControlLogin extends AppCompatActivity {

    SharedPreferences mLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mLogin = getSharedPreferences("login", MODE_PRIVATE);

        if (mLogin.getString("username", "")==null
                ||mLogin.getString("username","").equalsIgnoreCase("")
                ||mLogin.getString("username","")==""){

            Intent intent = new Intent(ControlLogin.this, Login.class);
            startActivity(intent);
            finish();

        }else {
            Intent intent = new Intent(ControlLogin.this, Mainmenu.class);
            startActivity(intent);
            finish();
        }

    }
}
