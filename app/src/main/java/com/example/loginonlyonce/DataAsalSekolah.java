package com.example.loginonlyonce;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
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

import org.json.JSONException;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_asal_sekolah);

        mInfoRPL = getSharedPreferences("login", Context.MODE_PRIVATE);


        Toolbar toolbar = findViewById(R.id.toolbar);

        txtNamaSekolah = findViewById(R.id.namaSekolah);
        txtAlamatSekolah = findViewById(R.id.alamatSekolah);
        txtNilaiSTTb = findViewById(R.id.nilaiSTTb);
        txtNoSTTb = findViewById(R.id.noSTTB);
        txtTahunSTTb = findViewById(R.id.tahunSTTB);

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

        btn = findViewById(R.id.btnSav);

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
                                Log.d("ceksiswa", prestasi);
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

        AndroidNetworking.upload("http://api-ppdb.smkrus.com/api/v1/daftar")
                //berkas, testing pakai text dulu
//                .addMultipartFile("gantiparaminiyaNel", fileselectedImagePathfoto)
//                .addMultipartFile("lmp_akte", fileselectedImagePathakte)
//                .addMultipartFile("gantiparaminiyaNel", fileselectedImagePathgambar)
//                .addMultipartFile("gantiparaminiyaNel", fileselectedImagePathkasehtan)
//                .addMultipartFile("gantiparaminiyaNel", fileselectedImagePathraport)
//                .addMultipartFile("lmp_skhun", fileselectedImagePathsertifikat)
//                .addMultipartFile("lmp_kk", fileselectedImagePathkk)


                //berkas, kalau sudah siap pakai file, komen aja 7 dibawah ini, trus unkomen 7 diatas
//                .addMultipartParameter("gantiparaminiyaNel", selectedImagePathfoto)
                .addMultipartParameter("lmp_akte", selectedImagePathakte)
//                .addMultipartParameter("gantiparaminiyaNel", selectedImagePathgambar)
//                .addMultipartParameter("gantiparaminiyaNel", selectedImagePathkasehtan)
//                .addMultipartParameter("gantiparaminiyaNel", selectedImagePathraport)
                .addMultipartParameter("lmp_skhun", selectedImagePathsertifikat)
                .addMultipartParameter("lmp_kk", selectedImagePathkk)


                .addMultipartParameter("username", mInfoRPL.getString("username", ""))
                .addMultipartParameter("sw_jurusan", mInfoRPL.getString("jurusan", ""))

                //berkas, kalau sudah siap pakai file, komen aja 7 dibawah ini, trus unkomen 7 diatas
                .addMultipartParameter("gantiparaminiyaNel", selectedImagePathfoto)
                .addMultipartParameter("gantiparaminiyaNel", selectedImagePathakte)
                .addMultipartParameter("gantiparaminiyaNel", selectedImagePathgambar)
                .addMultipartParameter("gantiparaminiyaNel", selectedImagePathkasehtan)
                .addMultipartParameter("gantiparaminiyaNel", selectedImagePathraport)
                .addMultipartParameter("gantiparaminiyaNel", selectedImagePathsertifikat)
                .addMultipartParameter("gantiparaminiyaNel", selectedImagePathkk)


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
                        Log.d("hasilResponsku", "onResponse: " + response.toString());
                        try {
                            String status = response.getString("STATUS");
                            if (status.equalsIgnoreCase("SUCCESS")){
                                finish();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.d("erorResponsku", "onError: " + anError.getErrorDetail());
                        Log.d("erorResponsku", "onError: " + anError.getErrorBody());
                        Log.d("erorResponsku", "onError: " + anError.getErrorCode());
                    }
                });

    }
}