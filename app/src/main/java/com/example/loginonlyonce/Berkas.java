package com.example.loginonlyonce;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.androidnetworking.interfaces.UploadProgressListener;
import com.vansuita.pickimage.bean.PickResult;
import com.vansuita.pickimage.bundle.PickSetup;
import com.vansuita.pickimage.dialog.PickImageDialog;
import com.vansuita.pickimage.listeners.IPickResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

import id.zelory.compressor.Compressor;

public class Berkas extends AppCompatActivity implements IPickResult  {

    private ProgressDialog progressBar;
    private CardView foto;
    private CardView akte;
    private CardView kk;
    private CardView sertifikat;
    private CardView raport;
    private CardView catksehtan;
    private CardView gambar;
    private CardView struk;
    SharedPreferences mInfoBerkas;
    private LinearLayout lnberkas;


    //

    private Bitmap selectedImage;

    ImageView ivkupassfoto;
    ImageView ivkuakta;
    ImageView ivkukk;
    ImageView ivkusertifikat;
    ImageView ivkuraport;
    ImageView ivkukesehatan;
    ImageView ivkugambar;
    ImageView ivkustruk;

    Button btnkufoto;
    Button btnkuakta;
    Button btnkukk;
    Button btnkusertifikat;
    Button btnkuraport;
    Button btnkukesehatan;
    Button btnkugambar;
    Button btnkustruk;

    Button btnPostFoto;
    Button btnPostAkta;
    Button btnPostKk;
    Button btnPostSertifikat;
    Button btnPostKesehatan;
    Button btnPostGambar;
    Button btnPostStruk;
    Button btnPostRapot;

    private String selectedImagePathkupassfoto = "";
    private String selectedImagePathkuakta = "";
    private String selectedImagePathkukk = "";
    private String selectedImagePathkusertifikat = "";
    private String selectedImagePathkuraport = "";
    private String selectedImagePathkukesehatan = "";
    private String selectedImagePathkugambar = "";
    private String selectedImagePathkustruk = "";

    File fileselectedImagePathfoto;
    File fileselectedImagePathakte;
    File fileselectedImagePathkk;
    File fileselectedImagePathsertifikat;
    File fileselectedImagePathraport;
    File fileselectedImagePathkasehtan;
    File fileselectedImagePathgambar;
    File fileselectedImagePathstruck;

    String kupassfoto = "";
    String kuakta = "";
    String kukk = "";
    String kusertifikat = "";
    String kuraport = "";
    String kukesehatan = "";
    String kugambar = "";
    String kustruk = "";

    SharedPreferences GambarAnimasi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adapter__berkas);

        Toolbar toolbar = findViewById(R.id.toolbarBerkas);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Berkas");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        foto = (CardView) findViewById(R.id.cdfoto);
        akte = (CardView) findViewById(R.id.cdakte);
        kk = (CardView) findViewById(R.id.cdkk);
        sertifikat = (CardView) findViewById(R.id.cdsertifikat);
        raport = (CardView) findViewById(R.id.cdraport);
        catksehtan = (CardView) findViewById(R.id.cdcatksehtan);
        gambar = (CardView) findViewById(R.id.cdgambar);
        struk = findViewById(R.id.cdstruk);
        lnberkas = (LinearLayout) findViewById(R.id.lnberkas);

        ivkupassfoto = findViewById(R.id.ivkupassfoto);
        ivkuakta = findViewById(R.id.ivkuakta);
        ivkukk = findViewById(R.id.ivkukk);
        ivkusertifikat = findViewById(R.id.ivkusertifikat);
        ivkuraport = findViewById(R.id.ivkuraport);
        ivkukesehatan = findViewById(R.id.ivkukesehatan);
        ivkugambar = findViewById(R.id.ivkugambar);
        ivkustruk = findViewById(R.id.ivkustruk);

        btnPostFoto = findViewById(R.id.btnPostFoto);
        btnPostAkta = findViewById(R.id.btnPostAkta);
        btnPostKk = findViewById(R.id.btnPostKk);
        btnPostSertifikat = findViewById(R.id.btnPostSertifikat);
        btnPostKesehatan = findViewById(R.id.btnPostKesehatan);
        btnPostGambar = findViewById(R.id.btnPostGambar);
        btnPostStruk = findViewById(R.id.btnPostStruk);
        btnPostRapot = findViewById(R.id.btnPostRapot);

        btnkufoto = (Button) findViewById(R.id.btnkufoto);
        btnkuakta = (Button) findViewById(R.id.btnkuAkte);
        btnkukk = (Button) findViewById(R.id.btnkukk);
        btnkusertifikat = (Button) findViewById(R.id.btnkusertifikat);
        btnkuraport = (Button) findViewById(R.id.btnkuraport);
        btnkukesehatan = (Button) findViewById(R.id.btnkuksehatan);
        btnkugambar = (Button) findViewById(R.id.btnkugambar);
        btnkustruk = (Button) findViewById(R.id.btnkustruk);

        GambarAnimasi = getSharedPreferences("login", MODE_PRIVATE);

        if (GambarAnimasi.getString("jurusan", "") == "Animasi"){

            gambar.setVisibility(View.VISIBLE);

        }else {

            gambar.setVisibility(View.GONE);

        }

        btnkufoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                kupassfoto = "true";
                PickImageDialog.build(new PickSetup()).show(getSupportFragmentManager());
            }
        });

        btnkuakta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                kuakta = "true";
                PickImageDialog.build(new PickSetup()).show(getSupportFragmentManager());
            }
        });

        btnkukk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                kukk = "true";
                PickImageDialog.build(new PickSetup()).show(getSupportFragmentManager());
            }
        });

        btnkusertifikat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                kusertifikat = "true";
                PickImageDialog.build(new PickSetup()).show(getSupportFragmentManager());
            }
        });

        btnkuraport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                kuraport = "true";
                PickImageDialog.build(new PickSetup()).show(getSupportFragmentManager());
            }
        });

        btnkukesehatan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                kukesehatan = "true";
                PickImageDialog.build(new PickSetup()).show(getSupportFragmentManager());
            }
        });

        btnkugambar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                kugambar = "true";
                PickImageDialog.build(new PickSetup()).show(getSupportFragmentManager());
            }
        });

        btnkustruk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                kustruk = "true";
                PickImageDialog.build(new PickSetup()).show(getSupportFragmentManager());
            }
        });


        progressBar = new ProgressDialog(Berkas.this);

        mInfoBerkas = getSharedPreferences("login", Context.MODE_PRIVATE);

        progressBar.setMessage("Please wait");
        progressBar.show();
        progressBar.setCancelable(false);
        AndroidNetworking.get("http://api-ppdb.smkrus.com/api/v1/cek-berkas?id=" + mInfoBerkas.getInt("userid", 0))
                .setTag("test")
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if (progressBar.isShowing()) {
                                progressBar.dismiss();
                            }
                            Log.d("makananenak", "onResponse: "+response.toString());

                            String status = response.getString("STATUS");
                            if (status.equalsIgnoreCase("SUCCESS")) {
                                JSONObject jsonObjectPayload = response.getJSONObject("PAYLOAD");
                                if (jsonObjectPayload.getString("lmp_raport").equalsIgnoreCase("-")) {
                                    raport.setVisibility(View.GONE);
                                } else if (jsonObjectPayload.getString("lmp_raport").equalsIgnoreCase("N")) {
                                    raport.setVisibility(View.VISIBLE);
                                } else if (jsonObjectPayload.getString("lmp_raport").equalsIgnoreCase("Y")) {
                                    raport.setVisibility(View.GONE);
                                }

                                if (jsonObjectPayload.getString("lmp_akte").equalsIgnoreCase("-")) {
                                    akte.setVisibility(View.VISIBLE);
                                } else if (jsonObjectPayload.getString("lmp_akte").equalsIgnoreCase("N")) {
                                    akte.setVisibility(View.VISIBLE);
                                } else if (jsonObjectPayload.getString("lmp_akte").equalsIgnoreCase("Y")) {
                                    akte.setVisibility(View.GONE);
                                }

                                if (jsonObjectPayload.getString("lmp_kk").equalsIgnoreCase("-")) {
                                    kk.setVisibility(View.VISIBLE);
                                } else if (jsonObjectPayload.getString("lmp_kk").equalsIgnoreCase("N")) {
                                    kk.setVisibility(View.VISIBLE);
                                } else if (jsonObjectPayload.getString("lmp_kk").equalsIgnoreCase("Y")) {
                                    kk.setVisibility(View.GONE);
                                }

                                if (jsonObjectPayload.getString("lmp_foto").equalsIgnoreCase("-")) {
                                    foto.setVisibility(View.VISIBLE);
                                } else if (jsonObjectPayload.getString("lmp_foto").equalsIgnoreCase("N")) {
                                    foto.setVisibility(View.VISIBLE);
                                } else if (jsonObjectPayload.getString("lmp_foto").equalsIgnoreCase("Y")) {
                                    foto.setVisibility(View.GONE);
                                }

                                if (jsonObjectPayload.getString("lmp_kesehatan").equalsIgnoreCase("-")) {
                                    catksehtan.setVisibility(View.VISIBLE);
                                } else if (jsonObjectPayload.getString("lmp_kesehatan").equalsIgnoreCase("N")) {
                                    catksehtan.setVisibility(View.VISIBLE);
                                } else if (jsonObjectPayload.getString("lmp_kesehatan").equalsIgnoreCase("Y")) {
                                    catksehtan.setVisibility(View.GONE);
                                }

                                if (jsonObjectPayload.getString("lmp_prestasi").equalsIgnoreCase("-")) {
                                    sertifikat.setVisibility(View.VISIBLE);
                                } else if (jsonObjectPayload.getString("lmp_prestasi").equalsIgnoreCase("N")) {
                                    sertifikat.setVisibility(View.VISIBLE);
                                } else if (jsonObjectPayload.getString("lmp_prestasi").equalsIgnoreCase("Y")) {
                                    sertifikat.setVisibility(View.GONE);
                                }

                                if (jsonObjectPayload.getString("lmp_gambar_anm").equalsIgnoreCase("-")) {
                                    gambar.setVisibility(View.VISIBLE);
                                    Log.d("view", "onResponse: -");
                                } else if (jsonObjectPayload.getString("lmp_gambar_anm").equalsIgnoreCase("N")) {
                                    gambar.setVisibility(View.VISIBLE);
                                    Log.d("view", "onResponse: N");
                                } else if (jsonObjectPayload.getString("lmp_gambar_anm").equalsIgnoreCase("Y")) {
                                    gambar.setVisibility(View.GONE);
                                    Log.d("view", "onResponse: Y");
                                }

                                if (jsonObjectPayload.getString("lmp_bukti_pembayaran").equalsIgnoreCase("-")) {
                                    struk.setVisibility(View.VISIBLE);
                                } else if (jsonObjectPayload.getString("lmp_bukti_pembayaran").equalsIgnoreCase("N")) {
                                    struk.setVisibility(View.VISIBLE);
                                } else if (jsonObjectPayload.getString("lmp_bukti_pembayaran").equalsIgnoreCase("Y")) {
                                    struk.setVisibility(View.GONE);
                                }

                                Log.d("payload", "onResponseee: " + jsonObjectPayload);

                            }

                            else if(status.equalsIgnoreCase("ERROR")){

                                Toast.makeText(Berkas.this, "Anda belum mendaftar", Toast.LENGTH_SHORT).show();
                                raport.setVisibility(View.GONE);
                                akte.setVisibility(View.GONE);
                                kk.setVisibility(View.GONE);
                                foto.setVisibility(View.GONE);
                                catksehtan.setVisibility(View.GONE);
                                sertifikat.setVisibility(View.GONE);
                                gambar.setVisibility(View.GONE);
                                struk.setVisibility(View.GONE);
                                AlertDialog.Builder builder = new AlertDialog.Builder(Berkas.this);
                                builder.setMessage("Belum ada data yang harus upload ulang")
                                        .setTitle("information")
                                        .setCancelable(false)
                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {

                                                finish();

                                            }
                                        });
                                AlertDialog alert = builder.create();
                                alert.show();
                            }

                            //Toast.makeText(getApplicationContext(), "Successs", Toast.LENGTH_LONG).show();

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

        btnPostFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

                progressBar.setMessage("Please wait");
                progressBar.show();
                AndroidNetworking.upload("http://api-ppdb.smkrus.com/api/v1/berkas")
                        .addMultipartParameter("id", "1")
                        .addMultipartParameter("type", "FOTO_DIRI")
                        .addMultipartFile("file", fileselectedImagePathfoto)
                        .setPriority(Priority.HIGH)
                        .build()
                        .setUploadProgressListener(new UploadProgressListener() {
                            @Override
                            public void onProgress(long bytesUploaded, long totalBytes) {

                            }
                        })
                        .getAsJSONObject(new JSONObjectRequestListener() {
                            @Override
                            public void onResponse(JSONObject response) {

                                if (progressBar.isShowing()) {
                                    progressBar.dismiss();
                                }

                                Log.d("hasilResponsku", "onResponse: " + response.toString());
                                if (selectedImagePathkupassfoto.equalsIgnoreCase("")){

                                    Toast.makeText(getApplicationContext(), "Pilih File Gambar", Toast.LENGTH_LONG).show();

                                }else {

                                    AlertDialog.Builder builder = new AlertDialog.Builder(Berkas.this);
                                    builder.setMessage("Data berhasil terkirim!")
                                            .setCancelable(false)
                                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int id) {

                                                    foto.setVisibility(View.GONE);

                                                }
                                            });
                                    AlertDialog alert = builder.create();
                                    alert.show();

                                }

                            }

                            @Override
                            public void onError(ANError anError) {

                                Log.d("hasilResponsku", "onResponseEror: " + anError.toString());
                                Log.d("hasilResponsku", "onResponseEror: " + anError.getErrorDetail());
                                Log.d("hasilResponsku", "onResponseEror: " + anError.getErrorCode());
                                Log.d("hasilResponsku", "onResponseEror: " + anError.getErrorBody());
                                Log.d("hasilResponsku", "onResponseEror: " + fileselectedImagePathfoto);

                            }
                        });


            }
        });

        btnPostAkta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progressBar.setMessage("Please wait");
                progressBar.show();
                AndroidNetworking.upload("http://api-ppdb.smkrus.com/api/v1/berkas")
                        .addMultipartParameter("id", "1")
                        .addMultipartParameter("type", "AKTE")
                        .addMultipartFile("file", fileselectedImagePathakte)
                        .setPriority(Priority.HIGH)
                        .build()
                        .setUploadProgressListener(new UploadProgressListener() {
                            @Override
                            public void onProgress(long bytesUploaded, long totalBytes) {

                            }
                        })
                        .getAsJSONObject(new JSONObjectRequestListener() {
                            @Override
                            public void onResponse(JSONObject response) {

                                if (progressBar.isShowing()) {
                                    progressBar.dismiss();
                                }

                                Log.d("hasilResponsku", "onResponse: " + response.toString());
                                if (selectedImagePathkuakta.equalsIgnoreCase("")){

                                    Toast.makeText(getApplicationContext(), "Pilih File Gambar", Toast.LENGTH_LONG).show();

                                }else {

                                    AlertDialog.Builder builder = new AlertDialog.Builder(Berkas.this);
                                    builder.setMessage("Data berhasil terkirim!")
                                            .setCancelable(false)
                                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int id) {

                                                    akte.setVisibility(View.GONE);

                                                }
                                            });
                                    AlertDialog alert = builder.create();
                                    alert.show();

                                }

                            }

                            @Override
                            public void onError(ANError anError) {

                                Log.d("hasilResponsku", "onResponseEror: " + anError.toString());
                                Log.d("hasilResponsku", "onResponseEror: " + anError.getErrorDetail());
                                Log.d("hasilResponsku", "onResponseEror: " + anError.getErrorCode());
                                Log.d("hasilResponsku", "onResponseEror: " + anError.getErrorBody());
                                Log.d("hasilResponsku", "onResponseEror: " + fileselectedImagePathfoto);

                            }
                        });

            }
        });

        btnPostKk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progressBar.setMessage("Please wait");
                progressBar.show();
                AndroidNetworking.upload("http://api-ppdb.smkrus.com/api/v1/berkas")
                        .addMultipartParameter("id", "1")
                        .addMultipartParameter("type", "KK")
                        .addMultipartFile("file", fileselectedImagePathkk)
                        .setPriority(Priority.HIGH)
                        .build()
                        .setUploadProgressListener(new UploadProgressListener() {
                            @Override
                            public void onProgress(long bytesUploaded, long totalBytes) {

                            }
                        })
                        .getAsJSONObject(new JSONObjectRequestListener() {
                            @Override
                            public void onResponse(JSONObject response) {

                                if (progressBar.isShowing()) {
                                    progressBar.dismiss();
                                }

                                Log.d("hasilResponsku", "onResponse: " + response.toString());
                                if (selectedImagePathkukk.equalsIgnoreCase("")){

                                    Toast.makeText(getApplicationContext(), "Pilih File Gambar", Toast.LENGTH_LONG).show();

                                }else {

                                    AlertDialog.Builder builder = new AlertDialog.Builder(Berkas.this);
                                    builder.setMessage("Data berhasil terkirim!")
                                            .setCancelable(false)
                                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int id) {

                                                    kk.setVisibility(View.GONE);

                                                }
                                            });
                                    AlertDialog alert = builder.create();
                                    alert.show();

                                }

                            }

                            @Override
                            public void onError(ANError anError) {

                                Log.d("hasilResponsku", "onResponseEror: " + anError.toString());
                                Log.d("hasilResponsku", "onResponseEror: " + anError.getErrorDetail());
                                Log.d("hasilResponsku", "onResponseEror: " + anError.getErrorCode());
                                Log.d("hasilResponsku", "onResponseEror: " + anError.getErrorBody());
                                Log.d("hasilResponsku", "onResponseEror: " + fileselectedImagePathfoto);

                            }
                        });

            }
        });

        btnPostSertifikat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progressBar.setMessage("Please wait");
                progressBar.show();
                AndroidNetworking.upload("http://api-ppdb.smkrus.com/api/v1/berkas")
                        .addMultipartParameter("id", "1")
                        .addMultipartParameter("type", "PRESTASI")
                        .addMultipartFile("file", fileselectedImagePathsertifikat)
                        .setPriority(Priority.HIGH)
                        .build()
                        .setUploadProgressListener(new UploadProgressListener() {
                            @Override
                            public void onProgress(long bytesUploaded, long totalBytes) {

                            }
                        })
                        .getAsJSONObject(new JSONObjectRequestListener() {
                            @Override
                            public void onResponse(JSONObject response) {

                                if (progressBar.isShowing()) {
                                    progressBar.dismiss();
                                }

                                Log.d("hasilResponsku", "onResponse: " + response.toString());
                                if (selectedImagePathkusertifikat.equalsIgnoreCase("")){

                                    Toast.makeText(getApplicationContext(), "Pilih File Gambar", Toast.LENGTH_LONG).show();

                                }else {

                                    AlertDialog.Builder builder = new AlertDialog.Builder(Berkas.this);
                                    builder.setMessage("Data berhasil terkirim!")
                                            .setCancelable(false)
                                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int id) {

                                                    sertifikat.setVisibility(View.GONE);

                                                }
                                            });
                                    AlertDialog alert = builder.create();
                                    alert.show();

                                }

                            }

                            @Override
                            public void onError(ANError anError) {

                                Log.d("hasilResponsku", "onResponseEror: " + anError.toString());
                                Log.d("hasilResponsku", "onResponseEror: " + anError.getErrorDetail());
                                Log.d("hasilResponsku", "onResponseEror: " + anError.getErrorCode());
                                Log.d("hasilResponsku", "onResponseEror: " + anError.getErrorBody());
                                Log.d("hasilResponsku", "onResponseEror: " + fileselectedImagePathfoto);

                            }
                        });

            }
        });

        btnPostKesehatan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progressBar.setMessage("Please wait");
                progressBar.show();
                AndroidNetworking.upload("http://api-ppdb.smkrus.com/api/v1/berkas")
                        .addMultipartParameter("id", "1")
                        .addMultipartParameter("type", "CEK_KESEHATAN")
                        .addMultipartFile("file", fileselectedImagePathkasehtan)
                        .setPriority(Priority.HIGH)
                        .build()
                        .setUploadProgressListener(new UploadProgressListener() {
                            @Override
                            public void onProgress(long bytesUploaded, long totalBytes) {

                            }
                        })
                        .getAsJSONObject(new JSONObjectRequestListener() {
                            @Override
                            public void onResponse(JSONObject response) {

                                if (progressBar.isShowing()) {
                                    progressBar.dismiss();
                                }

                                Log.d("hasilResponsku", "onResponse: " + response.toString());
                                if (selectedImagePathkukesehatan.equalsIgnoreCase("")){

                                    Toast.makeText(getApplicationContext(), "Pilih File Gambar", Toast.LENGTH_LONG).show();

                                }else {

                                    AlertDialog.Builder builder = new AlertDialog.Builder(Berkas.this);
                                    builder.setMessage("Data berhasil terkirim!")
                                            .setCancelable(false)
                                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int id) {

                                                    catksehtan.setVisibility(View.GONE);

                                                }
                                            });
                                    AlertDialog alert = builder.create();
                                    alert.show();

                                }

                            }

                            @Override
                            public void onError(ANError anError) {

                                Log.d("hasilResponsku", "onResponseEror: " + anError.toString());
                                Log.d("hasilResponsku", "onResponseEror: " + anError.getErrorDetail());
                                Log.d("hasilResponsku", "onResponseEror: " + anError.getErrorCode());
                                Log.d("hasilResponsku", "onResponseEror: " + anError.getErrorBody());
                                Log.d("hasilResponsku", "onResponseEror: " + fileselectedImagePathfoto);

                            }
                        });

            }
        });

        btnPostGambar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progressBar.setMessage("Please wait");
                progressBar.show();
                AndroidNetworking.upload("http://api-ppdb.smkrus.com/api/v1/berkas")
                        .addMultipartParameter("id", "1")
                        .addMultipartParameter("type", "GAMBAR_ANIMASI")
                        .addMultipartFile("file", fileselectedImagePathgambar)
                        .setPriority(Priority.HIGH)
                        .build()
                        .setUploadProgressListener(new UploadProgressListener() {
                            @Override
                            public void onProgress(long bytesUploaded, long totalBytes) {

                            }
                        })
                        .getAsJSONObject(new JSONObjectRequestListener() {
                            @Override
                            public void onResponse(JSONObject response) {

                                if (progressBar.isShowing()) {
                                    progressBar.dismiss();
                                }

                                Log.d("hasilResponsku", "onResponse: " + response.toString());
                                if (selectedImagePathkugambar.equalsIgnoreCase("")){

                                    Toast.makeText(getApplicationContext(), "Pilih File Gambar", Toast.LENGTH_LONG).show();

                                }else {

                                    AlertDialog.Builder builder = new AlertDialog.Builder(Berkas.this);
                                    builder.setMessage("Data berhasil terkirim!")
                                            .setCancelable(false)
                                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int id) {

                                                    gambar.setVisibility(View.GONE);

                                                }
                                            });
                                    AlertDialog alert = builder.create();
                                    alert.show();

                                }

                            }

                            @Override
                            public void onError(ANError anError) {

                                Log.d("hasilResponsku", "onResponseEror: " + anError.toString());
                                Log.d("hasilResponsku", "onResponseEror: " + anError.getErrorDetail());
                                Log.d("hasilResponsku", "onResponseEror: " + anError.getErrorCode());
                                Log.d("hasilResponsku", "onResponseEror: " + anError.getErrorBody());
                                Log.d("hasilResponsku", "onResponseEror: " + fileselectedImagePathfoto);

                            }
                        });

            }
        });

        btnPostStruk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progressBar.setMessage("Please wait");
                progressBar.show();
                AndroidNetworking.upload("http://api-ppdb.smkrus.com/api/v1/berkas")
                        .addMultipartParameter("id", "1")
                        .addMultipartParameter("type", "BUKTI_PEMBAYARAN")
                        .addMultipartFile("file", fileselectedImagePathstruck)
                        .setPriority(Priority.HIGH)
                        .build()
                        .setUploadProgressListener(new UploadProgressListener() {
                            @Override
                            public void onProgress(long bytesUploaded, long totalBytes) {

                            }
                        })
                        .getAsJSONObject(new JSONObjectRequestListener() {
                            @Override
                            public void onResponse(JSONObject response) {

                                if (progressBar.isShowing()) {
                                    progressBar.dismiss();
                                }

                                Log.d("hasilResponsku", "onResponse: " + response.toString());
                                if (selectedImagePathkustruk.equalsIgnoreCase("")){

                                    Toast.makeText(getApplicationContext(), "Pilih File Gambar", Toast.LENGTH_LONG).show();

                                }else {

                                    AlertDialog.Builder builder = new AlertDialog.Builder(Berkas.this);
                                    builder.setMessage("Data berhasil terkirim!")
                                            .setCancelable(false)
                                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int id) {

                                                    struk.setVisibility(View.GONE);

                                                }
                                            });
                                    AlertDialog alert = builder.create();
                                    alert.show();

                                }

                            }

                            @Override
                            public void onError(ANError anError) {

                                Log.d("hasilResponsku", "onResponseEror: " + anError.toString());
                                Log.d("hasilResponsku", "onResponseEror: " + anError.getErrorDetail());
                                Log.d("hasilResponsku", "onResponseEror: " + anError.getErrorCode());
                                Log.d("hasilResponsku", "onResponseEror: " + anError.getErrorBody());
                                Log.d("hasilResponsku", "onResponseEror: " + fileselectedImagePathfoto);

                            }
                        });

            }
        });

        btnPostRapot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progressBar.setMessage("Please wait");
                progressBar.show();
                AndroidNetworking.upload("http://api-ppdb.smkrus.com/api/v1/berkas")
                        .addMultipartParameter("id", "1")
                        .addMultipartParameter("type", "FOTO_DIRI")
                        .addMultipartFile("file", fileselectedImagePathfoto)
                        .setPriority(Priority.HIGH)
                        .build()
                        .setUploadProgressListener(new UploadProgressListener() {
                            @Override
                            public void onProgress(long bytesUploaded, long totalBytes) {

                            }
                        })
                        .getAsJSONObject(new JSONObjectRequestListener() {
                            @Override
                            public void onResponse(JSONObject response) {

                                if (progressBar.isShowing()) {
                                    progressBar.dismiss();
                                }

                                Log.d("hasilResponsku", "onResponse: " + response.toString());
                                if (selectedImagePathkuraport.equalsIgnoreCase("")){

                                    Toast.makeText(getApplicationContext(), "Pilih File Gambar", Toast.LENGTH_LONG).show();

                                }else {

                                    AlertDialog.Builder builder = new AlertDialog.Builder(Berkas.this);
                                    builder.setMessage("Data berhasil terkirim!")
                                            .setCancelable(false)
                                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int id) {

                                                    raport.setVisibility(View.GONE);

                                                }
                                            });
                                    AlertDialog alert = builder.create();
                                    alert.show();

                                }

                            }

                            @Override
                            public void onError(ANError anError) {

                                Log.d("hasilResponsku", "onResponseEror: " + anError.toString());
                                Log.d("hasilResponsku", "onResponseEror: " + anError.getErrorDetail());
                                Log.d("hasilResponsku", "onResponseEror: " + anError.getErrorCode());
                                Log.d("hasilResponsku", "onResponseEror: " + anError.getErrorBody());
                                Log.d("hasilResponsku", "onResponseEror: " + fileselectedImagePathfoto);

                            }
                        });

            }
        });

    }

    @Override
    public void onPickResult(PickResult r) {
        if (r.getError() == null & kupassfoto.equalsIgnoreCase("true")) {
            //selectedImagePathkupassfoto = r.getPath();

            try {
                File fileku = new Compressor(this)
                        .setQuality(50)
                        .setCompressFormat(Bitmap.CompressFormat.WEBP)
                        .setDestinationDirectoryPath(Environment.getExternalStoragePublicDirectory(
                                Environment.DIRECTORY_PICTURES).getAbsolutePath())
                        .compressToFile(new File(r.getPath()));

                selectedImagePathkupassfoto = fileku.getAbsolutePath().toString();
                fileselectedImagePathfoto = new File(selectedImagePathkupassfoto.toString());
                Log.d("file", "onCreate: " + fileselectedImagePathfoto);
                Log.d("makananku", "onPickResult: " + selectedImagePathkupassfoto);

                selectedImage = r.getBitmap();
                ivkupassfoto.setImageBitmap(selectedImage);
                kupassfoto = "";

            } catch (IOException e) {
                e.printStackTrace();
            }

        } else if (r.getError() == null & kuakta.equalsIgnoreCase("true")) {
            //selectedImagePathkuakte = r.getPath();

            try {
                File fileku = new Compressor(this)
                        .setQuality(50)
                        .setCompressFormat(Bitmap.CompressFormat.WEBP)
                        .setDestinationDirectoryPath(Environment.getExternalStoragePublicDirectory(
                                Environment.DIRECTORY_PICTURES).getAbsolutePath())
                        .compressToFile(new File(r.getPath()));

                selectedImagePathkuakta = fileku.getAbsolutePath().toString();
                fileselectedImagePathakte = new File(selectedImagePathkuakta.toString());
                Log.d("file", "onCreate: " + fileselectedImagePathakte);
                Log.d("makananku", "onPickResult: " + selectedImagePathkuakta);
                selectedImage = r.getBitmap();
                ivkuakta.setImageBitmap(selectedImage);
                kuakta = "";
            } catch (IOException e) {
                e.printStackTrace();
            }


        } else if (r.getError() == null & kukk.equalsIgnoreCase("true")) {
            //selectedImagePathkukk = r.getPath();

            try {
                File fileku = new Compressor(this)
                        .setQuality(50)
                        .setCompressFormat(Bitmap.CompressFormat.WEBP)
                        .setDestinationDirectoryPath(Environment.getExternalStoragePublicDirectory(
                                Environment.DIRECTORY_PICTURES).getAbsolutePath())
                        .compressToFile(new File(r.getPath()));

                selectedImagePathkukk = fileku.getAbsolutePath().toString();
                fileselectedImagePathkk = new File(selectedImagePathkukk.toString());
                Log.d("file", "onCreate: " + fileselectedImagePathkk);
                Log.d("makananku", "onPickResult: " + selectedImagePathkukk);

                selectedImage = r.getBitmap();
                ivkukk.setImageBitmap(selectedImage);
                kukk = "";

            } catch (IOException e) {
                e.printStackTrace();
            }


        } else if (r.getError() == null & kusertifikat.equalsIgnoreCase("true")) {
            //selectedImagePathkusertifikat = r.getPath();

            try {
                File fileku = new Compressor(this)
                        .setQuality(50)
                        .setCompressFormat(Bitmap.CompressFormat.WEBP)
                        .setDestinationDirectoryPath(Environment.getExternalStoragePublicDirectory(
                                Environment.DIRECTORY_PICTURES).getAbsolutePath())
                        .compressToFile(new File(r.getPath()));

                selectedImagePathkusertifikat = fileku.getAbsolutePath().toString();
                fileselectedImagePathsertifikat = new File(selectedImagePathkusertifikat.toString());
                Log.d("file", "onCreate: " + fileselectedImagePathsertifikat);
                Log.d("makananku", "onPickResult: " + selectedImagePathkusertifikat);

                selectedImage = r.getBitmap();
                ivkusertifikat.setImageBitmap(selectedImage);
                kusertifikat = "";

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (r.getError() == null & kuraport.equalsIgnoreCase("true")) {
            //selectedImagePathkuraport = r.getPath();

            try {
                File fileku = new Compressor(this)
                        .setQuality(50)
                        .setCompressFormat(Bitmap.CompressFormat.WEBP)
                        .setDestinationDirectoryPath(Environment.getExternalStoragePublicDirectory(
                                Environment.DIRECTORY_PICTURES).getAbsolutePath())
                        .compressToFile(new File(r.getPath()));

                selectedImagePathkuraport = fileku.getAbsolutePath().toString();
                fileselectedImagePathraport = new File(selectedImagePathkuraport.toString());
                Log.d("file", "onCreate: " + fileselectedImagePathraport);
                Log.d("makananku", "onPickResult: " + selectedImagePathkuraport);

                selectedImage = r.getBitmap();
                ivkuraport.setImageBitmap(selectedImage);
                kuraport = "";

            } catch (IOException e) {
                e.printStackTrace();
            }

        } else if (r.getError() == null & kukesehatan.equalsIgnoreCase("true")) {
            //selectedImagePathkukesehatan = r.getPath();

            try {
                File fileku = new Compressor(this)
                        .setQuality(50)
                        .setCompressFormat(Bitmap.CompressFormat.WEBP)
                        .setDestinationDirectoryPath(Environment.getExternalStoragePublicDirectory(
                                Environment.DIRECTORY_PICTURES).getAbsolutePath())
                        .compressToFile(new File(r.getPath()));

                selectedImagePathkukesehatan = fileku.getAbsolutePath().toString();
                fileselectedImagePathkasehtan = new File(selectedImagePathkukesehatan.toString());
                Log.d("file", "onCreate: " + fileselectedImagePathkasehtan);
                Log.d("makananku", "onPickResult: " + selectedImagePathkukesehatan);

                selectedImage = r.getBitmap();
                ivkukesehatan.setImageBitmap(selectedImage);
                kukesehatan = "";

            } catch (IOException e) {
                e.printStackTrace();
            }

        } else if (r.getError() == null & kugambar.equalsIgnoreCase("true")) {
            //selectedImagePathkugambar = r.getPath();

            try {
                File fileku = new Compressor(this)
                        .setQuality(50)
                        .setCompressFormat(Bitmap.CompressFormat.WEBP)
                        .setDestinationDirectoryPath(Environment.getExternalStoragePublicDirectory(
                                Environment.DIRECTORY_PICTURES).getAbsolutePath())
                        .compressToFile(new File(r.getPath()));

                selectedImagePathkugambar = fileku.getAbsolutePath().toString();
                fileselectedImagePathgambar = new File(selectedImagePathkugambar.toString());
                Log.d("file", "onCreate: " + fileselectedImagePathgambar);
                Log.d("makananku", "onPickResult: " + selectedImagePathkugambar);

                selectedImage = r.getBitmap();
                ivkugambar.setImageBitmap(selectedImage);
                kugambar = "";

            } catch (IOException e) {
                e.printStackTrace();
            }

        } else if (r.getError() == null & kustruk.equalsIgnoreCase("true")) {
            //selectedImagePathkustruk = r.getPath();

            try {
                File fileku = new Compressor(this)
                        .setQuality(50)
                        .setCompressFormat(Bitmap.CompressFormat.WEBP)
                        .setDestinationDirectoryPath(Environment.getExternalStoragePublicDirectory(
                                Environment.DIRECTORY_PICTURES).getAbsolutePath())
                        .compressToFile(new File(r.getPath()));

                selectedImagePathkustruk = fileku.getAbsolutePath().toString();
                fileselectedImagePathstruck = new File(selectedImagePathkustruk.toString());
                Log.d("file", "onCreate: " + fileselectedImagePathstruck);
                Log.d("makananku", "onPickResult: " + selectedImagePathkustruk);

                selectedImage = r.getBitmap();
                ivkustruk.setImageBitmap(selectedImage);
                kustruk = "";

            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {

        }

    }
}
