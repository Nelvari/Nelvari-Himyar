package com.example.loginonlyonce.Model;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.loginonlyonce.Ui.Home;
import com.example.loginonlyonce.Ui.Mainmenu;

import org.json.JSONException;
import org.json.JSONObject;

public class ControlHome extends AppCompatActivity {

    private SharedPreferences mHome;
    private ProgressDialog progressBar;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        progressBar = new ProgressDialog(ControlHome.this);

        mHome = getSharedPreferences("login", Context.MODE_PRIVATE);

        AndroidNetworking.get("http://api-ppdb.smkrus.com/api/v1/profile")
                .addPathParameter("id", "1")
                .setTag("test")
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {

                            if(progressBar.isShowing()){
                                progressBar.dismiss();}

                            String status = response.getString("STATUS");

                            if (status.equalsIgnoreCase("SUCCESS")){

                                Intent intent = new Intent(ControlHome.this, Mainmenu.class);
                                startActivity(intent);
                                finish();

                            }

                            else {

                                Intent intent = new Intent(ControlHome.this, Home.class);
                                startActivity(intent);
                                finish();

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });


//        if (mHome.getString("username", "").equalsIgnoreCase("")
//                || mHome.getString("username", "") == null
//                || mHome.getString("username", "").isEmpty()){
//
//            Intent intent = new Intent(ControlHome.this, Home.class);
//            startActivity(intent);
//            finish();
//
//        }else {
//
//            Intent intent = new Intent(ControlHome.this, Mainmenu.class);
//            startActivity(intent);
//            finish();
//
//        }

    }
}
