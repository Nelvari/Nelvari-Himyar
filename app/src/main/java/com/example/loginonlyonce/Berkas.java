package com.example.loginonlyonce;

import android.app.ProgressDialog;
import android.content.Context;
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

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.vansuita.pickimage.bean.PickResult;
import com.vansuita.pickimage.bundle.PickSetup;
import com.vansuita.pickimage.dialog.PickImageDialog;
import com.vansuita.pickimage.listeners.IPickResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

import id.zelory.compressor.Compressor;

public class Berkas extends AppCompatActivity implements IPickResult {

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


    private String selectedImagePathkupassfoto = "";
    private String selectedImagePathkuakta = "";
    private String selectedImagePathkukk = "";
    private String selectedImagePathkusertifikat = "";
    private String selectedImagePathkuraport = "";
    private String selectedImagePathkukesehatan = "";
    private String selectedImagePathkugambar = "";
    private String selectedImagePathkustruk = "";

    String kupassfoto = "";
    String kuakta = "";
    String kukk = "";
    String kusertifikat = "";
    String kuraport = "";
    String kukesehatan = "";
    String kugambar = "";
    String kustruk = "";


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
        lnberkas = (LinearLayout) findViewById(R.id.lnberkas);

        ivkupassfoto=findViewById(R.id.ivkupassfoto);
        ivkuakta=findViewById(R.id.ivkuakta);
        ivkukk=findViewById(R.id.ivkukk);
        ivkusertifikat=findViewById(R.id.ivkusertifikat);
        ivkuraport=findViewById(R.id.ivkuraport);
        ivkukesehatan=findViewById(R.id.ivkukesehatan);
        ivkugambar=findViewById(R.id.ivkugambar);
        ivkustruk=findViewById(R.id.ivkustruk);


        btnkufoto = (Button) findViewById(R.id.btnkufoto);
        btnkuakta = (Button) findViewById(R.id.btnkuAkte);
        btnkukk = (Button) findViewById(R.id.btnkukk);
        btnkusertifikat = (Button) findViewById(R.id.btnkusertifikat);
        btnkuraport = (Button) findViewById(R.id.btnkuraport);
        btnkukesehatan = (Button) findViewById(R.id.btnkuksehatan);
        btnkugambar = (Button) findViewById(R.id.btnkugambar);
        btnkustruk = (Button) findViewById(R.id.btnkustruk);

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
        AndroidNetworking.get("http://api-ppdb.smkrus.com/api/v1/berkas?id" + mInfoBerkas.getInt("userid", 0))
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

                            String status = response.getString("STATUS");
                            if (status.equalsIgnoreCase("SUCCESS")) {
                                JSONObject jsonObjectPayload = response.getJSONObject("PAYLOAD");
                                if (jsonObjectPayload.getString("lmp_raport").equalsIgnoreCase("-")) {
                                    raport.setVisibility(View.VISIBLE);
                                } else if (jsonObjectPayload.getString("lmp_raport").equalsIgnoreCase("N")) {
                                    raport.setVisibility(View.VISIBLE);
                                } else if (jsonObjectPayload.getString("lmp_raport").equalsIgnoreCase("Y")) {
                                    raport.setVisibility(View.INVISIBLE);
                                }

                                if (jsonObjectPayload.getString("lmp_akte").equalsIgnoreCase("-")) {
                                    akte.setVisibility(View.VISIBLE);
                                } else if (jsonObjectPayload.getString("lmp_akte").equalsIgnoreCase("N")) {
                                    akte.setVisibility(View.VISIBLE);
                                } else if (jsonObjectPayload.getString("lmp_akte").equalsIgnoreCase("Y")) {
                                    akte.setVisibility(View.INVISIBLE);
                                }

                                if (jsonObjectPayload.getString("lmp_kk").equalsIgnoreCase("-")) {
                                    kk.setVisibility(View.VISIBLE);
                                } else if (jsonObjectPayload.getString("lmp_kk").equalsIgnoreCase("N")) {
                                    kk.setVisibility(View.VISIBLE);
                                } else if (jsonObjectPayload.getString("lmp_kk").equalsIgnoreCase("Y")) {
                                    kk.setVisibility(View.INVISIBLE);
                                }

                                if (jsonObjectPayload.getString("lmp_foto").equalsIgnoreCase("-")) {
                                    foto.setVisibility(View.VISIBLE);
                                } else if (jsonObjectPayload.getString("lmp_foto").equalsIgnoreCase("N")) {
                                    foto.setVisibility(View.VISIBLE);
                                } else if (jsonObjectPayload.getString("lmp_foto").equalsIgnoreCase("Y")) {
                                    foto.setVisibility(View.INVISIBLE);
                                }

                                if (jsonObjectPayload.getString("lmp_kesehatan").equalsIgnoreCase("-")) {
                                    catksehtan.setVisibility(View.VISIBLE);
                                } else if (jsonObjectPayload.getString("lmp_kesehatan").equalsIgnoreCase("N")) {
                                    catksehtan.setVisibility(View.VISIBLE);
                                } else if (jsonObjectPayload.getString("lmp_kesehatan").equalsIgnoreCase("Y")) {
                                    catksehtan.setVisibility(View.INVISIBLE);
                                }

                                if (jsonObjectPayload.getString("lmp_prestasi").equalsIgnoreCase("-")) {
                                    sertifikat.setVisibility(View.VISIBLE);
                                } else if (jsonObjectPayload.getString("lmp_prestasi").equalsIgnoreCase("N")) {
                                    sertifikat.setVisibility(View.VISIBLE);
                                } else if (jsonObjectPayload.getString("lmp_prestasi").equalsIgnoreCase("Y")) {
                                    sertifikat.setVisibility(View.INVISIBLE);
                                }

                                if (jsonObjectPayload.getString("lmp_gambar_anm").equalsIgnoreCase("-")) {
                                    gambar.setVisibility(View.VISIBLE);
                                } else if (jsonObjectPayload.getString("lmp_gambar_anm").equalsIgnoreCase("N")) {
                                    gambar.setVisibility(View.VISIBLE);
                                } else if (jsonObjectPayload.getString("lmp_gambar_anm").equalsIgnoreCase("Y")) {
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

    @Override
    public void onPickResult(PickResult r) {
        if (r.getError() == null & kupassfoto.equalsIgnoreCase("true")) {
            try {
                File fileku = new Compressor(this)
                        .setQuality(50)
                        .setCompressFormat(Bitmap.CompressFormat.WEBP)
                        .setDestinationDirectoryPath(Environment.getExternalStoragePublicDirectory(
                                Environment.DIRECTORY_PICTURES).getAbsolutePath())
                        .compressToFile(new File(r.getPath()));

                selectedImagePathkupassfoto = fileku.getAbsolutePath().toString();
                Log.d("makananku", "onPickResult: " + selectedImagePathkupassfoto);

                selectedImage = r.getBitmap();
                ivkupassfoto.setImageBitmap(selectedImage);
                kupassfoto = "";

            } catch (IOException e) {
                e.printStackTrace();
            }

        }else if(r.getError() == null & kuakta.equalsIgnoreCase("true")){
            //selectedImagePathakte = r.getPath();

            try {
                File fileku = new Compressor(this)
                        .setQuality(50)
                        .setCompressFormat(Bitmap.CompressFormat.WEBP)
                        .setDestinationDirectoryPath(Environment.getExternalStoragePublicDirectory(
                                Environment.DIRECTORY_PICTURES).getAbsolutePath())
                        .compressToFile(new File(r.getPath()));

                selectedImagePathkuakta=fileku.getAbsolutePath().toString();
                Log.d("makananku", "onPickResult: "+selectedImagePathkuakta);
                selectedImage = r.getBitmap();
                ivkuakta.setImageBitmap(selectedImage);
                kuakta="";
            } catch (IOException e) {
                e.printStackTrace();
            }


        }else if(r.getError() == null & kukk.equalsIgnoreCase("true")) {
            //selectedImagePathkk = r.getPath();

            try {
                File fileku = new Compressor(this)
                        .setQuality(50)
                        .setCompressFormat(Bitmap.CompressFormat.WEBP)
                        .setDestinationDirectoryPath(Environment.getExternalStoragePublicDirectory(
                                Environment.DIRECTORY_PICTURES).getAbsolutePath())
                        .compressToFile(new File(r.getPath()));

                selectedImagePathkukk=fileku.getAbsolutePath().toString();
                Log.d("makananku", "onPickResult: "+selectedImagePathkukk);

                selectedImage = r.getBitmap();
                ivkukk.setImageBitmap(selectedImage);
                kukk = "";

            } catch (IOException e) {
                e.printStackTrace();
            }


        }else if(r.getError() == null & kusertifikat.equalsIgnoreCase("true")) {
            //selectedImagePathgambar = r.getPath();

            try {
                File fileku = new Compressor(this)
                        .setQuality(50)
                        .setCompressFormat(Bitmap.CompressFormat.WEBP)
                        .setDestinationDirectoryPath(Environment.getExternalStoragePublicDirectory(
                                Environment.DIRECTORY_PICTURES).getAbsolutePath())
                        .compressToFile(new File(r.getPath()));

                selectedImagePathkusertifikat=fileku.getAbsolutePath().toString();
                Log.d("makananku", "onPickResult: "+selectedImagePathkusertifikat);

                selectedImage = r.getBitmap();
                ivkusertifikat.setImageBitmap(selectedImage);
                kusertifikat = "";

            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if(r.getError() == null & kuraport.equalsIgnoreCase("true")) {
            //selectedImagePathraport = r.getPath();

            try {
                File fileku = new Compressor(this)
                        .setQuality(50)
                        .setCompressFormat(Bitmap.CompressFormat.WEBP)
                        .setDestinationDirectoryPath(Environment.getExternalStoragePublicDirectory(
                                Environment.DIRECTORY_PICTURES).getAbsolutePath())
                        .compressToFile(new File(r.getPath()));

                selectedImagePathkuraport=fileku.getAbsolutePath().toString();
                Log.d("makananku", "onPickResult: "+selectedImagePathkuraport);

                selectedImage = r.getBitmap();
                ivkuraport.setImageBitmap(selectedImage);
                kuraport = "";

            } catch (IOException e) {
                e.printStackTrace();
            }

        }else if(r.getError() == null & kukesehatan.equalsIgnoreCase("true")) {
            //selectedImagePathkasehtan = r.getPath();

            try {
                File fileku = new Compressor(this)
                        .setQuality(50)
                        .setCompressFormat(Bitmap.CompressFormat.WEBP)
                        .setDestinationDirectoryPath(Environment.getExternalStoragePublicDirectory(
                                Environment.DIRECTORY_PICTURES).getAbsolutePath())
                        .compressToFile(new File(r.getPath()));

                selectedImagePathkukesehatan=fileku.getAbsolutePath().toString();
                Log.d("makananku", "onPickResult: "+selectedImagePathkukesehatan);

                selectedImage = r.getBitmap();
                ivkukesehatan.setImageBitmap(selectedImage);
                kukesehatan = "";

            } catch (IOException e) {
                e.printStackTrace();
            }

        }else if(r.getError() == null & kugambar.equalsIgnoreCase("true")) {
            //selectedImagePathgambar = r.getPath();

            try {
                File fileku = new Compressor(this)
                        .setQuality(50)
                        .setCompressFormat(Bitmap.CompressFormat.WEBP)
                        .setDestinationDirectoryPath(Environment.getExternalStoragePublicDirectory(
                                Environment.DIRECTORY_PICTURES).getAbsolutePath())
                        .compressToFile(new File(r.getPath()));

                selectedImagePathkugambar=fileku.getAbsolutePath().toString();
                Log.d("makananku", "onPickResult: "+selectedImagePathkugambar);

                selectedImage = r.getBitmap();
                ivkugambar.setImageBitmap(selectedImage);
                kugambar = "";

            } catch (IOException e) {
                e.printStackTrace();
            }

        }else if(r.getError() == null & kustruk.equalsIgnoreCase("true")) {
            //selectedImagePathgambar = r.getPath();

            try {
                File fileku = new Compressor(this)
                        .setQuality(50)
                        .setCompressFormat(Bitmap.CompressFormat.WEBP)
                        .setDestinationDirectoryPath(Environment.getExternalStoragePublicDirectory(
                                Environment.DIRECTORY_PICTURES).getAbsolutePath())
                        .compressToFile(new File(r.getPath()));

                selectedImagePathkustruk=fileku.getAbsolutePath().toString();
                Log.d("makananku", "onPickResult: "+selectedImagePathkustruk);

                selectedImage = r.getBitmap();
                ivkustruk.setImageBitmap(selectedImage);
                kustruk = "";

            } catch (IOException e) {
                e.printStackTrace();
            }

        }else {

        }

        }


    }



