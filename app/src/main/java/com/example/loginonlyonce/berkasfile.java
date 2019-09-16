package com.example.loginonlyonce;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.vansuita.pickimage.bean.PickResult;
import com.vansuita.pickimage.bundle.PickSetup;
import com.vansuita.pickimage.dialog.PickImageDialog;
import com.vansuita.pickimage.listeners.IPickResult;

public class berkasfile extends AppCompatActivity implements IPickResult {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_berkasfile);
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

                Intent intent = new Intent(berkasfile.this, DataSiswa.class);
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
        });

    }

    @Override
    public void onPickResult(PickResult r) {
        if (r.getError() == null & foto.equalsIgnoreCase("true")) {
            selectedImagePathfoto = r.getPath();
            selectedImage = r.getBitmap();
            ivfotodiri.setImageBitmap(selectedImage);
            foto="";
        }else if(r.getError() == null & akte.equalsIgnoreCase("true")){
            selectedImagePathakte = r.getPath();
            selectedImage = r.getBitmap();
            ivakte.setImageBitmap(selectedImage);
            akte="";
        }else if(r.getError() == null & kk.equalsIgnoreCase("true")) {
            selectedImagePathkk = r.getPath();
            selectedImage = r.getBitmap();
            ivkk.setImageBitmap(selectedImage);
            kk = "";
        }else if(r.getError() == null & sertifikat.equalsIgnoreCase("true")) {
            selectedImagePathsertifikat = r.getPath();
            selectedImage = r.getBitmap();
            ivsertifikat.setImageBitmap(selectedImage);
            sertifikat = "";
        }else if(r.getError() == null & raport.equalsIgnoreCase("true")) {
            selectedImagePathraport = r.getPath();
            selectedImage = r.getBitmap();
            ivraport.setImageBitmap(selectedImage);
            raport = "";
        }else if(r.getError() == null & ksehtan.equalsIgnoreCase("true")) {
            selectedImagePathkasehtan = r.getPath();
            selectedImage = r.getBitmap();
            ivksehtan.setImageBitmap(selectedImage);
            ksehtan = "";
        }else if(r.getError() == null & gambar.equalsIgnoreCase("true")) {
            selectedImagePathgambar = r.getPath();
            selectedImage = r.getBitmap();
            ivgambar.setImageBitmap(selectedImage);
            gambar = "";
        }else {
            Toast.makeText(this, r.getError().getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
