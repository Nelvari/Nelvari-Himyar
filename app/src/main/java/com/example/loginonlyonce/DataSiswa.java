package com.example.loginonlyonce;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

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
    EditText alamatSiswa;
    EditText tinggi;
    EditText berat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_siswa);

        Toolbar toolbar = findViewById(R.id.toolbarSiswa);
        noUjian = findViewById(R.id.editxtnoUjian);
        nisn = findViewById(R.id.editxtnisn);
        namaSiswa = findViewById(R.id.editxtnamaSiswa);
        jenisKelamin = findViewById(R.id.jenisKelamin);
        tempat = findViewById(R.id.editxttempatlahir);
        agama = findViewById(R.id.agama);
        tinggi = findViewById(R.id.editxttinggi);
        alamatSiswa = findViewById(R.id.editxtalamatSiswa);
        berat = findViewById(R.id.editxtberat);


        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Formulir Siswa");
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

                                Intent in = new Intent(getApplicationContext(), DataOrangTua.class);
                                in.putExtra("namasiswa", namaSiswa.getText().toString() );
                                in.putExtra("jeniskelamin", jenisKelamin.getSelectedItem().toString() );
                                in.putExtra("tempatlahir", tempat.getText().toString() );
                                in.putExtra("tanggallahir", show.getText().toString() );
                                in.putExtra("agama", agama.getSelectedItem().toString() );
                                in.putExtra("alamatsiswa", alamatSiswa.getText().toString() );
                                in.putExtra("tinggibadan", tinggi.getText().toString() );
                                in.putExtra("beratbadan", berat.getText().toString() );
                                in.putExtra("nisn", nisn.getText().toString() );
                                in.putExtra("noujian", noUjian.getText().toString() );
                                //put extra here
                                startActivity(in);

                                break;

                            case DialogInterface.BUTTON_NEGATIVE:

                                break;
                        }

                    }
                };

//                if(namaSiswa.length()==0)
//
//                {
//                    namaSiswa.requestFocus();
//                    namaSiswa.setError("FIELD CANNOT BE EMPTY");
//                }
//
//                else if(!Name.matches("[a-zA-Z ]+"))
//                {
//                    NameEditText.requestFocus();
//                    NameEditText.setError("ENTER ONLY ALPHABETICAL CHARACTER");
//                }
//
//                else if(word.length()==0)
//                {
//                    wordEditText.requestFocus();
//                    wordEditText.setError("FIELD CANNOT BE EMPTY");
//                }
//                else
//                {
//                    Toast.makeText(DataSiswa.this,"Validation Successful",Toast.LENGTH_LONG).show();
//                }




                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setMessage("Apakah anda yakin ingin simpan data?").setPositiveButton("Ya", dialog)
                        .setNegativeButton("Tidak", dialog).show();

            }
        });

    }
}
