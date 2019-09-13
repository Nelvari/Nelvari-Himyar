package com.example.loginonlyonce;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class DataAsalSekolah extends AppCompatActivity {

    Button btn;
    EditText txtNamaSekolah;
    EditText txtAlamatSekolah;
    EditText txtTahunSTTb;
    EditText txtNoSTTb;
    EditText txtNilaiSTTb;

    String namaSiswa;
    String jenisKelamin;
    String tempatLahir;
    String tanggalLahir;
    String agama;
    String alamatSiswa;
    String tinggiBadan;
    String beratBadan;
    String prestasi;
    String nisn;
    String noUjian;

    String selectedImagePathfoto;
    String selectedImagePathakte;
    String selectedImagePathkk;
    String selectedImagePathsertifikat;
    String selectedImagePathraport;
    String selectedImagePathkasehtan;
    String selectedImagePathgambar;

    String namaayah;
    String namaibu;
    String alamatorangtua;
    String pekerjaanayah;
    String pekerjaanibu;
    String penghasilanayah;
    String penghasilanibu;
    String noayah;
    String noibu;
    String namawali;
    String alamatwali;
    String nowali;
    String pekerjaanwali;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_asal_sekolah);

        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Formulir Asal Sekolah");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null){

            namaSiswa = bundle.getString("namasiswa");
            jenisKelamin = bundle.getString("jeniskelamin");
            tempatLahir = bundle.getString("tempatlahir");
            tanggalLahir = bundle.getString("tanggallahir");
            agama = bundle.getString("agama");
            alamatSiswa = bundle.getString("alamatsiswa");
            tinggiBadan = bundle.getString("tinggibadan");
            beratBadan = bundle.getString("beratbadan");
            prestasi = bundle.getString("prestasi");
            nisn = bundle.getString("nisn");
            noUjian = bundle.getString("noujian");

            selectedImagePathfoto = bundle.getString("selectedImagePathfoto");
            selectedImagePathakte = bundle.getString("selectedImagePathakte");
            selectedImagePathkk = bundle.getString("selectedImagePathkk");
            selectedImagePathsertifikat = bundle.getString("selectedImagePathsertifikat");
            selectedImagePathraport = bundle.getString("selectedImagePathraport");
            selectedImagePathkasehtan = bundle.getString("selectedImagePathkasehtan");
            selectedImagePathgambar = bundle.getString("selectedImagePathgambar");

            namaayah = bundle.getString("namaayah");
            namaibu = bundle.getString("namaibu");
            alamatorangtua = bundle.getString("alamatorangtua");
            pekerjaanayah = bundle.getString("pekerjaanayah");
            pekerjaanibu = bundle.getString("pekerjaanibu");
            penghasilanayah = bundle.getString("penghasilanayah");
            penghasilanibu = bundle.getString("penghasilanibu");
            noayah = bundle.getString("noayah");
            noibu = bundle.getString("noibu");
            namawali = bundle.getString("namawali");
            alamatwali = bundle.getString("alamatwali");
            nowali = bundle.getString("nowali");
            pekerjaanwali = bundle.getString("pekerjaanwali");

        }

        btn = findViewById(R.id.btnSav);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DialogInterface.OnClickListener dialog = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        switch (i){
                            case DialogInterface.BUTTON_POSITIVE:
                                Toast.makeText(getApplicationContext(), "Sukses", Toast.LENGTH_LONG).show();
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                Toast.makeText(getApplicationContext(), "Gagal", Toast.LENGTH_LONG).show();
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
}
