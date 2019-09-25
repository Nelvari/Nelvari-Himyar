package com.example.loginonlyonce.Model;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.loginonlyonce.Ui.Berkasfile;

import org.json.JSONException;
import org.json.JSONObject;

public class ControlData extends AppCompatActivity {

    ProgressDialog progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        progressBar = new ProgressDialog(ControlData.this);

        progressBar.setMessage("Please wait");
        progressBar.show();

        AndroidNetworking.get("http://api-ppdb.smkrus.com/api/v1/cek-daftar")
                .addPathParameter("id")
                .setTag("test")
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {

                            if (progressBar.isShowing()){
                                progressBar.dismiss();

                                String status = response.getString("STATUS");

                                if (status.equalsIgnoreCase("SUCCESS")){

                                    Intent intent = new Intent(ControlData.this, Berkasfile.class);
                                    startActivity(intent);

                                    Log.d("tes", "onResponse: " + status);

                                } else {

                                    Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG).show();

                                    Log.d("tes", "onResponse: " + status);

                                }

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onError(ANError anError) {



                    }
                });

    }
}
