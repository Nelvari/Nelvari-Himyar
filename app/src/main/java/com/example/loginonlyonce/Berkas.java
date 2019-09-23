package com.example.loginonlyonce;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONObject;

public class Berkas extends AppCompatActivity {

    private ProgressDialog progressBar;

    private SharedPreferences mInfoBerkas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_berkas);

        progressBar = new ProgressDialog(Berkas.this);

        progressBar.setMessage("Please wait");
        progressBar.show();

        mInfoBerkas = getSharedPreferences("login", Context.MODE_PRIVATE);

        AndroidNetworking.get("api-ppdb.smkrus.com/api/v1/berkas")
                .addPathParameter("u_id", "1")
                .setTag("test")
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {

                        progressBar.dismiss();

                        Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG).show();

                    }

                    @Override
                    public void onError(ANError anError) {

                        progressBar.dismiss();

                        Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();

                    }
                });

    }
}
