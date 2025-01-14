package com.example.loginonlyonce.Ui;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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

import com.example.loginonlyonce.R;
import com.vansuita.pickimage.bean.PickResult;
import com.vansuita.pickimage.bundle.PickSetup;
import com.vansuita.pickimage.dialog.PickImageDialog;
import com.vansuita.pickimage.listeners.IPickResult;

import java.io.File;
import java.io.IOException;

import id.zelory.compressor.Compressor;

public class Berkasfile extends AppCompatActivity implements IPickResult {

    private Bitmap selectedImage;
    Button btnfotosisiwa;
    Button btnakte;
    Button btnkk;
    Button btnsertifikat;
    Button btnraport;
    Button btnksehtan;
    Button btngambar;
    Button btnSaf;
    ImageView ivfotodiri;
    ImageView ivakte;
    ImageView ivkk;
    ImageView ivsertifikat;
    ImageView ivraport;
    ImageView ivksehtan;
    ImageView ivgambar;
    private String selectedImagePathfoto = "";
    private String selectedImagePathakte = "";
    private String selectedImagePathkk = "";
    private String selectedImagePathsertifikat = "";
    private String selectedImagePathraport = "";
    private String selectedImagePathkasehtan = "";
    private String selectedImagePathgambar = "";

    String foto="";
    String akte="";
    String kk="";
    String sertifikat="";
    String raport="";
    String ksehtan="";
    String gambar="";

    LinearLayout lineGambar;

    SharedPreferences GambarAnimasi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_berkasfile);

        Toolbar toolbar = findViewById(R.id.toolbarBerkasFile);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Berkas File");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DialogInterface.OnClickListener dialog = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        switch (i){

                            case DialogInterface.BUTTON_POSITIVE :
                                finish();
                                break;

                            case DialogInterface.BUTTON_NEGATIVE :
                                Toast.makeText(Berkasfile.this, "Gagal Kembali", Toast.LENGTH_SHORT).show();
                                break;
                        }

                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setMessage("Jika anda kembali anda akan mengulang kembali?").setPositiveButton("Ya", dialog)
                        .setTitle("Konfirmasi exit")
                        .setNegativeButton("Tidak", dialog).show();


            }
        });

        btnfotosisiwa = (Button) findViewById(R.id.btnfotosisiwa);
        btnakte = (Button) findViewById(R.id.btnakte);
        btnkk = (Button) findViewById(R.id.btnkk);
        btnsertifikat = (Button) findViewById(R.id.btnsertifikat);
        btnraport = (Button) findViewById(R.id.btnraport);
        btnksehtan = (Button) findViewById(R.id.btnksehtan);
        btngambar = (Button) findViewById(R.id.btngambar);
        ivfotodiri= findViewById(R.id.ivfotodiri);
        ivakte= findViewById(R.id.ivakte);
        ivkk= findViewById(R.id.ivkk);
        ivsertifikat= findViewById(R.id.ivsertifikat);
        ivraport= findViewById(R.id.ivraport);
        ivksehtan= findViewById(R.id.ivksehtan);
        ivgambar= findViewById(R.id.ivgambar);
        btnSaf = findViewById(R.id.btnSaf);

        lineGambar = findViewById(R.id.lineGambar);

        GambarAnimasi = getSharedPreferences("login", Context.MODE_PRIVATE);

        Log.d("anim", "onCreate: " + GambarAnimasi.getString("jurusan", ""));

        if (GambarAnimasi.getString("jurusan", "") == "Animasi"){

            lineGambar.setVisibility(View.VISIBLE);

        }else {

            lineGambar.setVisibility(View.GONE);

        }

        btnfotosisiwa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                foto="true";
                PickImageDialog.build(new PickSetup()).show(getSupportFragmentManager());
            }
        });
        btnakte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                akte="true";
                PickImageDialog.build(new PickSetup()).show(getSupportFragmentManager());
            }
        });
        btnkk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                kk="true";
                PickImageDialog.build(new PickSetup()).show(getSupportFragmentManager());
            }
        });
        btnsertifikat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sertifikat="true";
                PickImageDialog.build(new PickSetup()).show(getSupportFragmentManager());
            }
        });
        btnraport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                raport="true";
                PickImageDialog.build(new PickSetup()).show(getSupportFragmentManager());
            }
        });
        btnksehtan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ksehtan="true";
                PickImageDialog.build(new PickSetup()).show(getSupportFragmentManager());
            }
        });
        btngambar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gambar="true";
                PickImageDialog.build(new PickSetup()).show(getSupportFragmentManager());
            }
        });

        btnSaf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DialogInterface.OnClickListener dialog = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        switch (i){

                            case DialogInterface.BUTTON_POSITIVE :
                                Log.d("makanananim", "onClick: "+selectedImagePathgambar);
                                if (selectedImagePathfoto.equalsIgnoreCase("")
                                        || selectedImagePathfoto == ""
                                        ||selectedImagePathfoto == null){
                                    Toast.makeText(Berkasfile.this, "foto kosong", Toast.LENGTH_SHORT).show();
                                }else if (selectedImagePathakte.equalsIgnoreCase("")
                                        || selectedImagePathakte == ""
                                        ||selectedImagePathakte == null){
                                    Toast.makeText(Berkasfile.this, "akte kosong", Toast.LENGTH_SHORT).show();
                                }else if (selectedImagePathkk.equalsIgnoreCase("")
                                        || selectedImagePathkk == ""
                                        ||selectedImagePathkk == null){
                                    Toast.makeText(Berkasfile.this, "kk kosong", Toast.LENGTH_SHORT).show();
                                }else if (selectedImagePathkasehtan.equalsIgnoreCase("")
                                        || selectedImagePathkasehtan == ""
                                        ||selectedImagePathkasehtan == null){
                                    Toast.makeText(Berkasfile.this, "catatan kesehatan kosong", Toast.LENGTH_SHORT).show();
                                }else {
                                    Intent intent = new Intent(Berkasfile.this, DataSiswa.class);
                                    intent.putExtra("selectedImagePathfoto", selectedImagePathfoto);
                                    intent.putExtra("selectedImagePathakte", selectedImagePathakte);
                                    intent.putExtra("selectedImagePathkk", selectedImagePathkk);
                                    intent.putExtra("selectedImagePathsertifikat", selectedImagePathsertifikat);
                                    intent.putExtra("selectedImagePathraport", selectedImagePathraport);
                                    intent.putExtra("selectedImagePathkasehtan", selectedImagePathkasehtan);
                                    intent.putExtra("selectedImagePathgambar", selectedImagePathgambar);
                                    startActivity(intent);

                                    finish();
                                }
                                break;

                            case  DialogInterface.BUTTON_NEGATIVE :

                                Toast.makeText(getApplicationContext(), "Data gagal di simpan", Toast.LENGTH_LONG).show();

                                break;
                        }

                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setMessage("Apakah anda yakin ingin simpan data?").setPositiveButton("Ya", dialog)
                        .setNegativeButton("Tidak", dialog).show();

            }
        });

    }

    @Override
    public void onPickResult(PickResult r) {
        if (r.getError() == null & foto.equalsIgnoreCase("true")) {
            //selectedImagePathfoto = r.getPath();
            //try {
//                File fileku = new Compressor(this)
//                        .setQuality(100)
//                        .setCompressFormat(Bitmap.CompressFormat.WEBP)
//                        .setDestinationDirectoryPath(Environment.getExternalStoragePublicDirectory(
//                                Environment.DIRECTORY_PICTURES).getAbsolutePath())
//                        .compressToFile(new File(r.getPath()));

                selectedImagePathfoto=r.getPath().toString();
                Log.d("makananku", "onPickResult: "+selectedImagePathfoto);

                selectedImage = r.getBitmap();
                ivfotodiri.setImageBitmap(selectedImage);
                foto="";

//            } catch (IOException e) {
//                e.printStackTrace();
//            }



        }else if(r.getError() == null & akte.equalsIgnoreCase("true")){
            //selectedImagePathakte = r.getPath();

            try {
                File fileku = new Compressor(this)
                        .setQuality(50)
                        .setCompressFormat(Bitmap.CompressFormat.WEBP)
                        .setDestinationDirectoryPath(Environment.getExternalStoragePublicDirectory(
                                Environment.DIRECTORY_PICTURES).getAbsolutePath())
                        .compressToFile(new File(r.getPath()));

                selectedImagePathakte=fileku.getAbsolutePath().toString();
                Log.d("makananku", "onPickResult: "+selectedImagePathakte);
                selectedImage = r.getBitmap();
                ivakte.setImageBitmap(selectedImage);
                akte="";
            } catch (IOException e) {
                e.printStackTrace();
            }


        }else if(r.getError() == null & kk.equalsIgnoreCase("true")) {
            //selectedImagePathkk = r.getPath();

            try {
                File fileku = new Compressor(this)
                        .setQuality(50)
                        .setCompressFormat(Bitmap.CompressFormat.WEBP)
                        .setDestinationDirectoryPath(Environment.getExternalStoragePublicDirectory(
                                Environment.DIRECTORY_PICTURES).getAbsolutePath())
                        .compressToFile(new File(r.getPath()));

                selectedImagePathkk=fileku.getAbsolutePath().toString();
                Log.d("makananku", "onPickResult: "+selectedImagePathkk);

                selectedImage = r.getBitmap();
                ivkk.setImageBitmap(selectedImage);
                kk = "";

            } catch (IOException e) {
                e.printStackTrace();
            }


        }else if(r.getError() == null & sertifikat.equalsIgnoreCase("true")) {
            //selectedImagePathsertifikat = r.getPath();

            try {
                File fileku = new Compressor(this)
                        .setQuality(50)
                        .setCompressFormat(Bitmap.CompressFormat.WEBP)
                        .setDestinationDirectoryPath(Environment.getExternalStoragePublicDirectory(
                                Environment.DIRECTORY_PICTURES).getAbsolutePath())
                        .compressToFile(new File(r.getPath()));

                selectedImagePathsertifikat=fileku.getAbsolutePath().toString();
                Log.d("makananku", "onPickResult: "+selectedImagePathsertifikat);

                selectedImage = r.getBitmap();
                ivsertifikat.setImageBitmap(selectedImage);
                sertifikat = "";

            } catch (IOException e) {
                e.printStackTrace();
            }

        }else if(r.getError() == null & raport.equalsIgnoreCase("true")) {
            //selectedImagePathraport = r.getPath();

            try {
                File fileku = new Compressor(this)
                        .setQuality(50)
                        .setCompressFormat(Bitmap.CompressFormat.WEBP)
                        .setDestinationDirectoryPath(Environment.getExternalStoragePublicDirectory(
                                Environment.DIRECTORY_PICTURES).getAbsolutePath())
                        .compressToFile(new File(r.getPath()));

                selectedImagePathraport=fileku.getAbsolutePath().toString();
                Log.d("makananku", "onPickResult: "+selectedImagePathraport);

                selectedImage = r.getBitmap();
                ivraport.setImageBitmap(selectedImage);
                raport = "";

            } catch (IOException e) {
                e.printStackTrace();
            }

        }else if(r.getError() == null & ksehtan.equalsIgnoreCase("true")) {
            //selectedImagePathkasehtan = r.getPath();

            try {
                File fileku = new Compressor(this)
                        .setQuality(50)
                        .setCompressFormat(Bitmap.CompressFormat.WEBP)
                        .setDestinationDirectoryPath(Environment.getExternalStoragePublicDirectory(
                                Environment.DIRECTORY_PICTURES).getAbsolutePath())
                        .compressToFile(new File(r.getPath()));

                selectedImagePathkasehtan=fileku.getAbsolutePath().toString();
                Log.d("makananku", "onPickResult: "+selectedImagePathkasehtan);

                selectedImage = r.getBitmap();
                ivksehtan.setImageBitmap(selectedImage);
                ksehtan = "";

            } catch (IOException e) {
                e.printStackTrace();
            }

        }else if(r.getError() == null & gambar.equalsIgnoreCase("true")) {
            //selectedImagePathgambar = r.getPath();

            try {
                File fileku = new Compressor(this)
                        .setQuality(50)
                        .setCompressFormat(Bitmap.CompressFormat.WEBP)
                        .setDestinationDirectoryPath(Environment.getExternalStoragePublicDirectory(
                                Environment.DIRECTORY_PICTURES).getAbsolutePath())
                        .compressToFile(new File(r.getPath()));

                selectedImagePathgambar=fileku.getAbsolutePath().toString();
                Log.d("makananku", "onPickResult: "+selectedImagePathgambar);

                selectedImage = r.getBitmap();
                ivgambar.setImageBitmap(selectedImage);
                gambar = "";

            } catch (IOException e) {
                e.printStackTrace();
            }

        }else {
            Toast.makeText(this, r.getError().getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
