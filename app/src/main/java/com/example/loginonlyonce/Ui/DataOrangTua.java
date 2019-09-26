package com.example.loginonlyonce.Ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.loginonlyonce.R;

public class DataOrangTua extends AppCompatActivity {

    Button btnSimpanOrangTua;
    EditText txtNamaAyah;
    EditText txtNamaIbu;
    EditText txtAlamatOrangTua;
    EditText txtPekerjaanAyah;
    EditText txtPekerjaanIbu;
    EditText txtPenghasilanAyah;
    EditText txtPenghasilanIbu;
    EditText txtnoAyah;
    EditText txtnoIbu;
    EditText txtNamaWali;
    EditText txtAlamatWali;
    EditText txtnoWali;
    EditText txtPekerjaanWali;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_orang_tua);

        Toolbar toolbar = findViewById(R.id.toolbarOrangTua);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Formulir Orang Tua/Wali");
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

            nisn = bundle.getString("nisn");
            noUjian = bundle.getString("noujian");

            selectedImagePathfoto = bundle.getString("selectedImagePathfoto");
            selectedImagePathakte = bundle.getString("selectedImagePathakte");
            selectedImagePathkk = bundle.getString("selectedImagePathkk");
            selectedImagePathsertifikat = bundle.getString("selectedImagePathsertifikat");
            selectedImagePathraport = bundle.getString("selectedImagePathraport");
            selectedImagePathkasehtan = bundle.getString("selectedImagePathkasehtan");
            selectedImagePathgambar = bundle.getString("selectedImagePathgambar");

        }

        txtNamaAyah = findViewById(R.id.namaAyah);
        txtNamaIbu = findViewById(R.id.namaIbu);
        txtAlamatOrangTua = findViewById(R.id.alamatOrangTua);
        txtPekerjaanAyah = findViewById(R.id.pekerjaanAyah);
        txtPekerjaanIbu = findViewById(R.id.pekerjaanIbu);
        txtPenghasilanAyah = findViewById(R.id.penghasilanAyah);
        txtPenghasilanIbu = findViewById(R.id.penghasilanIbu);
        txtnoAyah = findViewById(R.id.noAyah);
        txtnoIbu = findViewById(R.id.noIbu);
        txtNamaWali = findViewById(R.id.namaWali);
        txtAlamatWali = findViewById(R.id.alamatWali);
        txtnoWali = findViewById(R.id.noWali);
        txtPekerjaanWali = findViewById(R.id.pekerjaanWali);


        btnSimpanOrangTua=(Button)findViewById(R.id.btnSimpanOrangTua);

        btnSimpanOrangTua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DialogInterface.OnClickListener dialog = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        switch (i){

                            case DialogInterface.BUTTON_POSITIVE :

                                //dialog
                                Intent in =new Intent(getApplicationContext(),DataAsalSekolah.class);
                                //put extra here
                                in.putExtra("namaayah", txtNamaAyah.getText().toString());
                                in.putExtra("namaibu", txtNamaIbu.getText().toString());
                                in.putExtra("alamatorangtua", txtAlamatOrangTua.getText().toString());
                                in.putExtra("pekerjaanayah", txtPekerjaanAyah.getText().toString());
                                in.putExtra("pekerjaanibu", txtPekerjaanIbu.getText().toString());
                                in.putExtra("penghasilanayah", txtPenghasilanAyah.getText().toString());
                                in.putExtra("penghasilanibu", txtPenghasilanIbu.getText().toString());
                                in.putExtra("noayah", txtnoAyah.getText().toString());
                                in.putExtra("noibu", txtnoIbu.getText().toString());
                                in.putExtra("namawali", txtNamaWali.getText().toString());
                                in.putExtra("alamatwali", txtAlamatWali.getText().toString());
                                in.putExtra("nowali", txtnoWali.getText().toString());
                                in.putExtra("pekerjaanwali", txtPekerjaanWali.getText().toString());

                                //siswa
                                in.putExtra("namasiswa", namaSiswa);
                                in.putExtra("jeniskelamin", jenisKelamin);
                                in.putExtra("tempatlahir", tempatLahir);
                                in.putExtra("tanggallahir", tanggalLahir);
                                in.putExtra("agama", agama);
                                in.putExtra("alamatsiswa", alamatSiswa);
                                in.putExtra("tinggibadan", tinggiBadan);
                                in.putExtra("beratbadan", beratBadan);

                                in.putExtra("nisn", nisn);
                                in.putExtra("noujian", noUjian);

                                in.putExtra("selectedImagePathfoto", selectedImagePathfoto);
                                in.putExtra("selectedImagePathakte", selectedImagePathakte);
                                in.putExtra("selectedImagePathkk", selectedImagePathkk);
                                in.putExtra("selectedImagePathsertifikat", selectedImagePathsertifikat);
                                in.putExtra("selectedImagePathraport", selectedImagePathraport);
                                in.putExtra("selectedImagePathkasehtan", selectedImagePathkasehtan);
                                in.putExtra("selectedImagePathgambar", selectedImagePathgambar);

                                startActivity(in);

                                break;

                            case DialogInterface.BUTTON_NEGATIVE :

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
}
