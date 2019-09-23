package com.example.loginonlyonce.Model;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.loginonlyonce.Ui.Home;
import com.example.loginonlyonce.Ui.Mainmenu;

public class ControlHome extends AppCompatActivity {

    private SharedPreferences mHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mHome = getSharedPreferences("login", Context.MODE_PRIVATE);

        if (mHome.getString("username1", "").equalsIgnoreCase("")
                || mHome.getString("username1", "") == null
                || mHome.getString("username1", "").isEmpty()){

            Intent intent = new Intent(ControlHome.this, Home.class);
            startActivity(intent);
            finish();

        }else {

            Intent intent = new Intent(ControlHome.this, Mainmenu.class);
            startActivity(intent);
            finish();

        }

    }
}
