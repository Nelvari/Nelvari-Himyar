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
    SharedPreferences mInfoBerkas;
    private LinearLayout lnberkas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adapter__berkas);
        foto=(CardView) findViewById(R.id.cdfoto);
        akte=(CardView) findViewById(R.id.cdakte);
        kk=(CardView) findViewById(R.id.cdkk);
        sertifikat=(CardView) findViewById(R.id.cdsertifikat);
        raport=(CardView) findViewById(R.id.cdraport);
        catksehtan=(CardView) findViewById(R.id.cdcatksehtan);
        gambar=(CardView) findViewById(R.id.cdgambar);
        lnberkas=(LinearLayout)findViewById(R.id.lnberkas);
        progressBar = new ProgressDialog(Berkas.this);

        mInfoBerkas = getSharedPreferences("login", Context.MODE_PRIVATE);

        progressBar.setMessage("Please wait");
        progressBar.show();
        AndroidNetworking.get("http://api-ppdb.smkrus.com/api/v1/berkas?id" + mInfoBerkas.getInt("userid", 0))
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
                                        raport.setVisibility(View.VISIBLE);
                                    }else if (jsonObjectPayload.getString("lmp_raport").equalsIgnoreCase("N")){
                                        raport.setVisibility(View.VISIBLE);
                                    }else if (jsonObjectPayload.getString("lmp_raport").equalsIgnoreCase("Y")){
                                        raport.setVisibility(View.INVISIBLE);
                                    }

                                    if (jsonObjectPayload.getString("lmp_akte").equalsIgnoreCase("-")){
                                        akte.setVisibility(View.VISIBLE);
                                    }else if (jsonObjectPayload.getString("lmp_akte").equalsIgnoreCase("N")){
                                        akte.setVisibility(View.VISIBLE);
                                    }else if (jsonObjectPayload.getString("lmp_akte").equalsIgnoreCase("Y")){
                                        akte.setVisibility(View.INVISIBLE);
                                    }

                                    if (jsonObjectPayload.getString("lmp_kk").equalsIgnoreCase("-")){
                                        kk.setVisibility(View.VISIBLE);
                                    }else if (jsonObjectPayload.getString("lmp_kk").equalsIgnoreCase("N")){
                                        kk.setVisibility(View.VISIBLE);
                                    }else if (jsonObjectPayload.getString("lmp_kk").equalsIgnoreCase("Y")){
                                        kk.setVisibility(View.INVISIBLE);
                                    }

                                    if (jsonObjectPayload.getString("lmp_foto").equalsIgnoreCase("-")){
                                        foto.setVisibility(View.VISIBLE);
                                    }else if (jsonObjectPayload.getString("lmp_foto").equalsIgnoreCase("N")){
                                        foto.setVisibility(View.VISIBLE);
                                    }else if (jsonObjectPayload.getString("lmp_foto").equalsIgnoreCase("Y")){
                                        foto.setVisibility(View.INVISIBLE);
                                    }

                                    if (jsonObjectPayload.getString("lmp_kesehatan").equalsIgnoreCase("-")){
                                        catksehtan.setVisibility(View.VISIBLE);
                                    }else if (jsonObjectPayload.getString("lmp_kesehatan").equalsIgnoreCase("N")){
                                        catksehtan.setVisibility(View.VISIBLE);
                                    }else if (jsonObjectPayload.getString("lmp_kesehatan").equalsIgnoreCase("Y")){
                                        catksehtan.setVisibility(View.INVISIBLE);
                                    }

                                    if (jsonObjectPayload.getString("lmp_prestasi").equalsIgnoreCase("-")){
                                        sertifikat.setVisibility(View.VISIBLE);
                                    }else if (jsonObjectPayload.getString("lmp_prestasi").equalsIgnoreCase("N")){
                                        sertifikat.setVisibility(View.VISIBLE);
                                    }else if (jsonObjectPayload.getString("lmp_prestasi").equalsIgnoreCase("Y")){
                                        sertifikat.setVisibility(View.INVISIBLE);
                                    }

                                    if (jsonObjectPayload.getString("lmp_gambar_anm").equalsIgnoreCase("-")){
                                        gambar.setVisibility(View.VISIBLE);
                                    }else if (jsonObjectPayload.getString("lmp_gambar_anm").equalsIgnoreCase("N")){
                                        gambar.setVisibility(View.VISIBLE);
                                    }else if (jsonObjectPayload.getString("lmp_gambar_anm").equalsIgnoreCase("Y")){
                                        gambar.setVisibility(View.INVISIBLE);
                                    }
                                }

                                Toast.makeText(getApplicationContext(), "Successs", Toast.LENGTH_LONG).show();

                                Log.d("tes", "onResponse: " + status);

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
