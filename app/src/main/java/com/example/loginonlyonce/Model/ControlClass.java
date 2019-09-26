package com.example.loginonlyonce.Model;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.loginonlyonce.Ui.Login;
import com.example.loginonlyonce.Ui.Mainmenu;

public class ControlClass extends AppCompatActivity {

    private SharedPreferences mInfoRPL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mInfoRPL = getSharedPreferences("login", Context.MODE_PRIVATE);

        if (mInfoRPL.getString("username", "").equalsIgnoreCase("")
                || mInfoRPL.getString("username", "") == null
                || mInfoRPL.getString("username", "").isEmpty()) {

            Intent in = new Intent(ControlClass.this, Login.class);
            startActivity(in);
            finish();

        } else {

            Intent in = new Intent(ControlClass.this, Mainmenu.class);
            startActivity(in);
            finish();

        }

    }
}
