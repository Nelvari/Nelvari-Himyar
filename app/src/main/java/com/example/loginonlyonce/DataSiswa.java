package com.example.loginonlyonce;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
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

import java.util.Calendar;

public class DataSiswa extends AppCompatActivity {

    ImageView date;
    TextView show;
    Button btnSave;
    EditText noPendaftaran;
    EditText noUjian;
    EditText nisn;
    EditText namaSiswa;
    Spinner jenisKelamin;
    EditText tempat;
    Spinner agama;
    EditText alamatSiswa;
    EditText tinggi;
    EditText berat;
    EditText prestasi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_siswa);

        Toolbar toolbar = findViewById(R.id.toolbar);
        noPendaftaran = findViewById(R.id.noPendaftaran);
        noUjian = findViewById(R.id.noUjian);
        nisn = findViewById(R.id.nisn);
        namaSiswa = findViewById(R.id.namaSiswa);
        jenisKelamin = findViewById(R.id.jenisKelamin);
        tempat = findViewById(R.id.tempat);
        agama = findViewById(R.id.agama);
        tinggi = findViewById(R.id.tinggi);
        alamatSiswa = findViewById(R.id.alamatSiswa);
        berat = findViewById(R.id.berat);
        prestasi = findViewById(R.id.prestasi);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(" ");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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
                },year,month,day);
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
                },year,month,day);
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
