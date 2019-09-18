package com.example.loginonlyonce.Ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.androidnetworking.interfaces.UploadProgressListener;
import com.example.loginonlyonce.R;

import org.json.JSONObject;

import java.io.File;

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
    String nisn;
    String noUjian;

    String selectedImagePathfoto;
    String selectedImagePathakte;
    String selectedImagePathkk;
    String selectedImagePathsertifikat;
    String selectedImagePathraport;
    String selectedImagePathkasehtan;
    String selectedImagePathgambar;

    File fileselectedImagePathfoto;
    File fileselectedImagePathakte;
    File fileselectedImagePathkk;
    File fileselectedImagePathsertifikat;
    File fileselectedImagePathraport;
    File fileselectedImagePathkasehtan;
    File fileselectedImagePathgambar;

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

    private SharedPreferences mInfoRPL;

    private ProgressDialog dialog;

    Bitmap bitmapcontoh;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_asal_sekolah);

        mInfoRPL = getSharedPreferences("login", Context.MODE_PRIVATE);

        dialog = new ProgressDialog(DataAsalSekolah.this);
        Toolbar toolbar = findViewById(R.id.toolbar);

        txtNamaSekolah = findViewById(R.id.txtNamaSekolah);
        txtAlamatSekolah = findViewById(R.id.txtAlamatSekolah);
        txtNilaiSTTb = findViewById(R.id.txtNilaiSTTb);
        txtNoSTTb = findViewById(R.id.txtNoSTTB);
        txtTahunSTTb = findViewById(R.id.txtTahunSTTB);

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

            nisn = bundle.getString("nisn");
            noUjian = bundle.getString("noujian");

            selectedImagePathfoto = bundle.getString("selectedImagePathfoto");
            selectedImagePathakte = bundle.getString("selectedImagePathakte");
            selectedImagePathkk = bundle.getString("selectedImagePathkk");
            selectedImagePathsertifikat = bundle.getString("selectedImagePathsertifikat");
            selectedImagePathraport = bundle.getString("selectedImagePathraport");
            selectedImagePathkasehtan = bundle.getString("selectedImagePathkasehtan");
            selectedImagePathgambar = bundle.getString("selectedImagePathgambar");

            fileselectedImagePathfoto=new File(selectedImagePathfoto);
            fileselectedImagePathakte=new File(selectedImagePathakte);
            fileselectedImagePathkk=new File(selectedImagePathkk);
            fileselectedImagePathsertifikat=new File(selectedImagePathsertifikat);
            fileselectedImagePathraport=new File(selectedImagePathraport);
            fileselectedImagePathkasehtan=new File(selectedImagePathkasehtan);
            fileselectedImagePathgambar=new File(selectedImagePathgambar);

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

        btn = findViewById(R.id.btnSave);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DialogInterface.OnClickListener dialog = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        switch (i){
                            case DialogInterface.BUTTON_POSITIVE:


                                //siswa
                                Log.d("ceksiswa", namaSiswa);
                                Log.d("ceksiswa", jenisKelamin);
                                Log.d("ceksiswa", tempatLahir);
                                Log.d("ceksiswa", tanggalLahir);
                                Log.d("ceksiswa", agama);
                                Log.d("ceksiswa", alamatSiswa);
                                Log.d("ceksiswa", tinggiBadan);
                                Log.d("ceksiswa", beratBadan);

                                Log.d("ceksiswa", nisn);
                                Log.d("ceksiswa", noUjian);

                                //fotoBerkas
                                Log.d("cekberkas", selectedImagePathfoto);
                                Log.d("cekberkas", selectedImagePathakte);
                                Log.d("cekberkas", selectedImagePathkk);
                                Log.d("cekberkas", selectedImagePathsertifikat);
                                Log.d("cekberkas", selectedImagePathraport);
                                Log.d("cekberkas", selectedImagePathkasehtan);
                                Log.d("cekberkas", selectedImagePathgambar);

                                //orangtua atau wali
                                Log.d("cekorangtua", namaayah);
                                Log.d("cekorangtua", namaibu);
                                Log.d("cekorangtua", alamatorangtua);
                                Log.d("cekorangtua", pekerjaanayah);
                                Log.d("cekorangtua", pekerjaanibu);
                                Log.d("cekorangtua", penghasilanayah);
                                Log.d("cekorangtua", penghasilanibu);
                                Log.d("cekorangtua", noayah);
                                Log.d("cekorangtua", noibu);
                                Log.d("cekorangtua", namawali);
                                Log.d("cekorangtua", alamatwali);
                                Log.d("cekorangtua", nowali);
                                Log.d("cekorangtua", pekerjaanwali);

                                //asalsekolah
                                Log.d("ceksekolah", txtNamaSekolah.getText().toString());
                                Log.d("ceksekolah", txtAlamatSekolah.getText().toString());
                                Log.d("ceksekolah", txtNilaiSTTb.getText().toString());
                                Log.d("ceksekolah", txtNoSTTb.getText().toString());
                                Log.d("ceksekolah", txtTahunSTTb.getText().toString());


                                Log.d("ceksekolah",mInfoRPL.getString("username", ""));
                                Log.d("ceksekolah",mInfoRPL.getString("jurusan", ""));

                                senData();

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

    private void senData(){

        dialog.setMessage("Doing something, please wait.");
        dialog.show();
        AndroidNetworking.upload("http://api-ppdb.smkrus.com/api/v1/daftar")
                //berkas, testing pakai text dulu
                .addMultipartFile("lmp_foto", fileselectedImagePathfoto)
                .addMultipartFile("lmp_akte", fileselectedImagePathakte)
                .addMultipartFile("lmp_gambar_anm", fileselectedImagePathgambar)
                .addMultipartFile("lmp_kesehatan", fileselectedImagePathkasehtan)
                .addMultipartFile("lmp_raport", fileselectedImagePathraport)
                .addMultipartFile("lmp_prestasi", fileselectedImagePathsertifikat)
                .addMultipartFile("lmp_kk", fileselectedImagePathkk)


                //berkas, kalau sudah siap pakai file, komen aja 7 dibawah ini, trus unkomen 7 diatas
//                .addMultipartParameter("lmp_foto", selectedImagePathfoto)
//                .addMultipartParameter("lmp_akte", content)
//                .addMultipartParameter("lmp_gambar_anm", content)
//                .addMultipartParameter("lmp_kesehatan", content)
//                .addMultipartParameter("gantiparaminiyaNel", content)
//                .addMultipartParameter("lmp_skhun", content)
//                .addMultipartParameter("lmp_kk", content)


                .addMultipartParameter("username", mInfoRPL.getString("username", ""))
                .addMultipartParameter("sw_jurusan", mInfoRPL.getString("jurusan", ""))



                //Siswa
                .addMultipartParameter("sw_nama_lengkap", namaSiswa)
                .addMultipartParameter("sw_nisn", nisn)
                .addMultipartParameter("sw_ttl", tempatLahir + ", " + tanggalLahir)
                .addMultipartParameter("sw_alamat", alamatSiswa)
                .addMultipartParameter("sw_gender", jenisKelamin)
                .addMultipartParameter("sw_agama", agama)
                .addMultipartParameter("sw_berat_badan", beratBadan)
                .addMultipartParameter("sw_tinggi_badan", tinggiBadan)
                .addMultipartParameter("sw_no_ujian", noUjian)

                //Orang Tua atau Wali
                .addMultipartParameter("ayah_nama", namaayah)
                .addMultipartParameter("ayah_pekerjaan", pekerjaanayah)
                .addMultipartParameter("ayah_no_hp", noayah)
                .addMultipartParameter("ibu_nama", namaibu)
                .addMultipartParameter("ibu_pekerjaan", pekerjaanibu)
                .addMultipartParameter("ibu_no_hp", noibu)
                .addMultipartParameter("wali_nama", namawali)
                .addMultipartParameter("wali_alamat", alamatwali)
                .addMultipartParameter("wali_pekerjaan", pekerjaanwali)
                .addMultipartParameter("wali_no_hp", nowali)

                //Sekolah Asal
                .addMultipartParameter("sw_sekolah_asal", txtNamaSekolah.getText().toString())
                .addMultipartParameter("sw_sekolah_asal_alamat", txtAlamatSekolah.getText().toString())

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

                        if (dialog.isShowing()) {
                            Log.d("hasilResponsku", "onResponse: " + response.toString());
                            dialog.dismiss();
                            SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPref.edit();
                            editor.putString("username", mInfoRPL.getString("username", ""));
                            editor.commit();

                            Intent intent = new Intent(DataAsalSekolah.this, Mainmenu.class);
                            startActivity(intent);

                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        if (dialog.isShowing()) {
                            dialog.dismiss();
                            Log.d("erorResponsku", "onError: " + anError.getErrorDetail());
                            Log.d("erorResponsku", "onError: " + anError.getErrorBody());
                            Log.d("erorResponsku", "onError: " + anError.getErrorCode());
                        }

                    }
                });

    }
}