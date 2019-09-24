package com.example.loginonlyonce;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONException;
import org.json.JSONObject;

public class Berkas extends AppCompatActivity {

    private ProgressDialog progressBar;
    private CardView foto;
    private CardView akte;
    private CardView kk;
    private CardView sertifikat;
    private CardView raport;
    private CardView catksehtan;
    private CardView gambar;
    private SharedPreferences mInfoBerkas;
    LinearLayout lnberkas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_berkas);
        foto=(CardView) findViewById(R.id.txt1);
        akte=(CardView) findViewById(R.id.txt2);
        kk=(CardView) findViewById(R.id.txt3);
        sertifikat=(CardView) findViewById(R.id.txt4);
        raport=(CardView) findViewById(R.id.txt5);
        catksehtan=(CardView) findViewById(R.id.txt6);
        gambar=(CardView) findViewById(R.id.txt7);
        lnberkas=(LinearLayout) findViewById(R.id.lnberkas);
        progressBar = new ProgressDialog(Berkas.this);

        mInfoBerkas = getSharedPreferences("login", Context.MODE_PRIVATE);

        progressBar.setMessage("Please wait");
        progressBar.show();
        AndroidNetworking.get("http://api-ppdb.smkrus.com/api/v1/berkas")
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


                                String status=response.getString("STATUS");
                                if (status.equalsIgnoreCase("SUCCESS")){
                                    JSONObject jsonObjectPayload = response.getJSONObject("PAYLOAD");
                                    if (jsonObjectPayload.getString("lmp_raport").equalsIgnoreCase("-")){
                                        Log.d("berkas gan", "onResponse: " + response);

                                    }
                                    lnberkas.setVisibility(View.VISIBLE);
                                }

                            Toast.makeText(getApplicationContext(), "Successs", Toast.LENGTH_LONG).show();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {

                        if (progressBar.isShowing()) {
                            progressBar.dismiss();
                            Log.d("erorResponsku", "onError: " + anError.getErrorDetail());
                            Log.d("erorResponsku", "onError: " + anError.getErrorBody());
                            Log.d("erorResponsku", "onError: " + anError.getErrorCode());
                        }

                        Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();

                    }
                });

    }
}
