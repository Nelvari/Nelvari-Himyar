package com.example.loginonlyonce.Model;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.loginonlyonce.Ui.CetakData;
import com.example.loginonlyonce.Ui.History;

import org.json.JSONException;
import org.json.JSONObject;

public class ControlHistory extends AppCompatActivity {

    private ProgressDialog progressBar;

    private SharedPreferences mHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mHistory = getSharedPreferences("login", Context.MODE_PRIVATE);

//        progressBar = new ProgressDialog(ControlHistory.this);
//
//        progressBar.setMessage("Please wait");
//        progressBar.show();

//        startActivity(new Intent(getApplicationContext(),CetakData.class));
//        finish();

    }
}
