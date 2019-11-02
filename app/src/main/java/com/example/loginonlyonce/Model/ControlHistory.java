package com.example.loginonlyonce.Model;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.loginonlyonce.Berkas;
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
        progressBar.setMessage("loading");
        progressBar.show();
        progressBar.setCancelable(false);
        System.out.println("makan"+"http://api-ppdb.smkrus.com/api/v1/cek-kartu?id="+mHistory.getInt("userid",0));
        AndroidNetworking.get("http://api-ppdb.smkrus.com/api/v1/cek-kartu?id="+mHistory.getInt("userid",0))
                .setTag("test")
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            String status = response.getString("STATUS");
                            if (status.equalsIgnoreCase("SUCCESS")){
                                Intent intent=new Intent(ControlHistory.this, History.class);
                                startActivity(intent);
                                finish();
                            }else if (status.equalsIgnoreCase("ERROR")){
                                String MESSAGE = response.getString("MESSAGE");
                                AlertDialog.Builder builder = new AlertDialog.Builder(ControlHistory.this);
                                builder.setMessage(MESSAGE)
                                        .setTitle("Information")
                                        .setCancelable(false)
                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {

                                                finish();

                                            }
                                        });
                                AlertDialog alert = builder.create();
                                alert.show();
                            }
                            if (progressBar.isShowing()){
                                progressBar.dismiss();
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
