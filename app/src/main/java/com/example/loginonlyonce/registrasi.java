package com.example.loginonlyonce;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class registrasi extends AppCompatActivity {

    Button btnBuat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrasi);

        btnBuat = findViewById(R.id.btnbuat);

        btnBuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(registrasi.this, Login.class);
                startActivity(intent);

            }
        });

    }
}
