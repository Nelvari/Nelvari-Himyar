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
import com.example.loginonlyonce.Ui.Home;

import org.json.JSONException;
import org.json.JSONObject;

public class ControlData extends AppCompatActivity {

    private ProgressDialog progressBar;

    SharedPreferences mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mData = getSharedPreferences("login", Context.MODE_PRIVATE);

        progressBar = new ProgressDialog(ControlData.this);

        progressBar.setMessage("Please wait");
        progressBar.show();
        Log.d("trialsaya", "onCreate: "+"http://api-ppdb.smkrus.com/api/v1/cek-daftar?id=" + mData.getInt("userid", 0));
        AndroidNetworking.get("http://api-ppdb.smkrus.com/api/v1/cek-daftar?id=" + mData.getInt("userid", 0))
                .setTag("test")
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String status = response.getString("STATUS");
                            if (status.equalsIgnoreCase("SUCCESS")){
                                Intent intent = new Intent(ControlData.this, Home.class);
                                startActivity(intent);
                                Toast.makeText(getApplicationContext(), "Tolong isi data", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG).show();
                            }

                            Log.d("tes", "onResponse: " + status);
                            Log.d("tes", "onResponse: " + mData.getString("username1", ""));

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

    }
}
