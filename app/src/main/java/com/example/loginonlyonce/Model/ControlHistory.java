package com.example.loginonlyonce.Model;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.loginonlyonce.Ui.CetakData;
import com.example.loginonlyonce.Ui.History;

public class ControlHistory extends AppCompatActivity {

    private SharedPreferences mHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mHistory = getSharedPreferences("login", Context.MODE_PRIVATE);
        if (mHistory.getString("username1", "").equalsIgnoreCase("")
                || mHistory.getString("username1", "") == null
                || mHistory.getString("username1", "").isEmpty()){
            Intent intent = new Intent(ControlHistory.this, CetakData.class);
            startActivity(intent);
            finish();

        }else {

            Intent intent = new Intent(ControlHistory.this, History.class);
            startActivity(intent);
            finish();

        }

    }
}
