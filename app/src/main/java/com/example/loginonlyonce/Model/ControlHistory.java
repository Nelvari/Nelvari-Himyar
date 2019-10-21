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

        progressBar = new ProgressDialog(ControlHistory.this);

        progressBar.setMessage("Please wait");
        progressBar.show();

        AndroidNetworking.get("http://api-ppdb.smkrus.com/api/v1/cek-daftar?id=" + mHistory.getInt("userid", 0))
                .setTag("test")
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {

                            String status = response.getString("STATUS");

                            if (status.equalsIgnoreCase("SUCCESS")){

                                Intent intent = new Intent(ControlHistory.this, CetakData.class);
                                startActivity(intent);

                                Toast.makeText(getApplicationContext(), "Tolong isi data", Toast.LENGTH_LONG).show();

                            } else {

                                Intent intent = new Intent(ControlHistory.this, History.class);
                                startActivity(intent);
                                Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG).show();

                            }

                            Log.d("tes", "onResponse: " + status);
                            Log.d("tes", "onResponse: " + mHistory.getString("username1", ""));

                            if (progressBar.isShowing()){
                                progressBar.dismiss();
                            }

                            finish();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onError(ANError anError) {

                        if (progressBar.isShowing()){

                            progressBar.dismiss();

                        }

                    }
                });

//        mHistory = getSharedPreferences("login", Context.MODE_PRIVATE);
//        if (mHistory.getString("username1", "").equalsIgnoreCase("")
//                || mHistory.getString("username1", "") == null
//                || mHistory.getString("username1", "").isEmpty()){
//            Intent intent = new Intent(ControlHistory.this, CetakData.class);
//            startActivity(intent);
//            finish();
//
//        }else {
//
//            Intent intent = new Intent(ControlHistory.this, History.class);
//            startActivity(intent);
//            finish();
//
//        }

    }
}
