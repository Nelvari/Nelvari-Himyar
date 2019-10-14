package com.example.loginonlyonce.Ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.loginonlyonce.R;

public class RPL extends AppCompatActivity {

    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rpl);

        Toolbar toolbar = findViewById(R.id.toolbar);
        btnLogin = findViewById(R.id.btnlogin);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("RPL");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(RPL.this, Berkasfile.class);
                startActivity(intent);
                finish();

            }
        });

    }
}
