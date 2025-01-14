package com.example.loginonlyonce.Ui;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.loginonlyonce.R;

import java.util.Calendar;

public class DataSiswa extends AppCompatActivity {

    ImageView date;
    TextView show;
    Button btnSave;

    EditText noUjian;
    EditText nisn;
    EditText namaSiswa;
    Spinner jenisKelamin;
    EditText tempat;
    Spinner agama;
    EditText editxtalamatSiswaProvinsi;
    EditText editxtalamatSiswaKota;
    EditText editxtalamatSiswaKecamatan;
    EditText editxtalamatSiswaDesa;
    EditText tinggi;
    EditText berat;


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
        setContentView(R.layout.activity_data_siswa);

        Toolbar toolbar = findViewById(R.id.toolbarSiswa);
        noUjian = findViewById(R.id.editxtnoUjian);
        nisn = findViewById(R.id.editxtnisn);
        namaSiswa = findViewById(R.id.editxtnamaSiswa);
        jenisKelamin = findViewById(R.id.spinnerJenisKelamin);
        tempat = findViewById(R.id.editxttempatlahir);
        agama = findViewById(R.id.spinnerAgama);
        tinggi = findViewById(R.id.editxttinggi);
        editxtalamatSiswaProvinsi = findViewById(R.id.editxtalamatSiswaProvinsi);
        editxtalamatSiswaKota = findViewById(R.id.editxtalamatSiswaKota);
        editxtalamatSiswaKecamatan = findViewById(R.id.editxtalamatSiswaKecamatan);
        editxtalamatSiswaDesa = findViewById(R.id.editxtalamatSiswaDesa);
        berat = findViewById(R.id.editxtberat);


        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Formulir Siswa");
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
                                Toast.makeText(DataSiswa.this, "Gagal Kembali", Toast.LENGTH_SHORT).show();
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

        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {

            selectedImagePathfoto = bundle.getString("selectedImagePathfoto");
            selectedImagePathakte = bundle.getString("selectedImagePathakte");
            selectedImagePathkk = bundle.getString("selectedImagePathkk");
            selectedImagePathsertifikat = bundle.getString("selectedImagePathsertifikat");
            selectedImagePathraport = bundle.getString("selectedImagePathraport");
            selectedImagePathkasehtan = bundle.getString("selectedImagePathkasehtan");
            selectedImagePathgambar = bundle.getString("selectedImagePathgambar");

            Log.d("databerkas", "onCreate: " + selectedImagePathfoto);
            Log.d("databerkas", "onCreate: " + selectedImagePathakte);
            Log.d("databerkas", "onCreate: " + selectedImagePathkk);
            Log.d("databerkas", "onCreate: " + selectedImagePathsertifikat);
            Log.d("databerkas", "onCreate: " + selectedImagePathraport);
            Log.d("databerkas", "onCreate: " + selectedImagePathkasehtan);
            Log.d("databerkas", "onCreate: " + selectedImagePathgambar);

        }

        date = findViewById(R.id.date);
        show = findViewById(R.id.show);

        Calendar cal = Calendar.getInstance();
        final int year = cal.get(Calendar.YEAR);
        final int month = cal.get(Calendar.MONTH);
        final int day = cal.get(Calendar.DAY_OF_MONTH);

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        DataSiswa.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                        month = month + 1;
                        String datef = day + "/" + month + "/" + year;
                        show.setText(datef);

                    }
                }, year, month, day);
                datePickerDialog.show();

            }
        });

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        DataSiswa.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                        month = month + 1;
                        String datef = day + "/" + month + "/" + year;
                        show.setText(datef);

                    }
                }, year, month, day);
                datePickerDialog.show();

            }
        });

        btnSave = findViewById(R.id.btnSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DialogInterface.OnClickListener dialog = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        switch (i) {
                            case DialogInterface.BUTTON_POSITIVE:

                                if (nisn.length()==0)
                                {
                                    nisn.requestFocus();
                                    nisn.setError("FIELD CANNOT BE EMPTY");
                                }

                                else if (namaSiswa.length()==0)
                                {
                                    namaSiswa.requestFocus();
                                    namaSiswa.setError("FIELD CANNOT BE EMPTY");
                                }
                                else if (tempat.length()==0)
                                {
                                    tempat.requestFocus();
                                    tempat.setError("FIELD CANNOT BE EMPTY");
                                }

                                else if (editxtalamatSiswaProvinsi.length()==0)
                                {
                                    editxtalamatSiswaProvinsi.requestFocus();
                                    editxtalamatSiswaProvinsi.setError("FIELD CANNOT BE EMPTY");
                                }

                                else if (editxtalamatSiswaKota.length()==0)
                                {
                                    editxtalamatSiswaKota.requestFocus();
                                    editxtalamatSiswaKota.setError("FIELD CANNOT BE EMPTY");
                                }

                                else if (editxtalamatSiswaKecamatan.length()==0)
                                {
                                    editxtalamatSiswaKecamatan.requestFocus();
                                    editxtalamatSiswaKecamatan.setError("FIELD CANNOT BE EMPTY");
                                }

                                else if (editxtalamatSiswaDesa.length()==0)
                                {
                                    editxtalamatSiswaDesa.requestFocus();
                                    editxtalamatSiswaDesa.setError("FIELD CANNOT BE EMPTY");
                                }

                                else if (tinggi.length()==0)
                                {
                                    tinggi.requestFocus();
                                    tinggi.setError("FIELD CANNOT BE EMPTY");
                                }

                                else if (berat.length()==0)
                                {
                                    berat.requestFocus();
                                    berat.setError("FIELD CANNOT BE EMPTY");
                                }
                                else {
                                    Intent in = new Intent(getApplicationContext(), DataOrangTua.class);
                                    in.putExtra("namasiswa", namaSiswa.getText().toString());
                                    in.putExtra("jeniskelamin", jenisKelamin.getSelectedItem().toString());
                                    in.putExtra("tempatlahir", tempat.getText().toString());
                                    in.putExtra("tanggallahir", show.getText().toString());
                                    in.putExtra("agama", agama.getSelectedItem().toString());
                                    in.putExtra("alamatsiswaprovinsi", editxtalamatSiswaProvinsi.getText().toString());
                                    in.putExtra("alamatsiswakota", editxtalamatSiswaKota.getText().toString());
                                    in.putExtra("alamatsiswakecamatan", editxtalamatSiswaKecamatan.getText().toString());
                                    in.putExtra("alamatsiswadesa", editxtalamatSiswaDesa.getText().toString());
                                    in.putExtra("tinggibadan", tinggi.getText().toString());
                                    in.putExtra("beratbadan", berat.getText().toString());
                                    in.putExtra("nisn", nisn.getText().toString());
                                    in.putExtra("noujian", noUjian.getText().toString());
                                    //put extra here

                                    in.putExtra("selectedImagePathfoto", selectedImagePathfoto);
                                    in.putExtra("selectedImagePathakte", selectedImagePathakte);
                                    in.putExtra("selectedImagePathkk", selectedImagePathkk);
                                    in.putExtra("selectedImagePathsertifikat", selectedImagePathsertifikat);
                                    in.putExtra("selectedImagePathraport", selectedImagePathraport);
                                    in.putExtra("selectedImagePathkasehtan", selectedImagePathkasehtan);
                                    in.putExtra("selectedImagePathgambar", selectedImagePathgambar);

                                    startActivity(in);

                                    finish();
                                }
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:

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
