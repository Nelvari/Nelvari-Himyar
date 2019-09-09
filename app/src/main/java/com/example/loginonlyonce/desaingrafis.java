package com.example.loginonlyonce;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.loginonlyonce.Model.ControlClass;

public class desaingrafis extends AppCompatActivity {

    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desaingrafis);

        Toolbar toolbar = findViewById(R.id.toolbar);
        btnLogin = findViewById(R.id.btnlogin);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("DKV");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(desaingrafis.this, ControlClass.class);
                startActivity(intent);
                finish();

            }
        });

    }
}
