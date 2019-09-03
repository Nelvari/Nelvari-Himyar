package com.example.loginonlyonce;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class MainActivity extends AppCompatActivity {

    Button log;
    GoogleSignInClient mGoogleSignInClient;
    ImageView imageView;
    TextView email;
    TextView id;
    TextView nama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);

        log = findViewById(R.id.logOut);
        imageView = findViewById(R.id.icon);
        nama = findViewById(R.id.nama);
        email = findViewById(R.id.email);
        id = findViewById(R.id.id);

        if (getIntent().getExtras() != null){

            nama.setText(getIntent().getStringExtra("data1"));
            email.setText(getIntent().getStringExtra("data2"));
            id.setText(getIntent().getStringExtra("data3"));

            Glide.with(this).load("https://graph.facebook.com/487152671831258/picture?type=large").into(imageView);

        }

        if (acct != null) {
            String personName = acct.getDisplayName();
            String personEmail = acct.getEmail();
            String personId = acct.getId();
            Uri personPhoto = acct.getPhotoUrl();

            nama.setText(personName);
            email.setText(personEmail);
            id.setText(personId);
            Glide.with(this).load(personPhoto).into(imageView);

        }

        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences mLogin = getSharedPreferences("login", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = mLogin.edit();
                editor.clear();
                editor.apply();

                //LoginManager.getInstance().logOut();

                Intent intent = new Intent(MainActivity.this, SplashScreen.class);
                startActivity(intent);
                finish();

//                switch (view.getId()) {
//                    // ...
//                    case R.id.logOut:
//                        signOut();
//                        break;
//                    // ...
//                }

            }
        });



    }

    private void signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Intent intent = new Intent(MainActivity.this, SplashScreen.class);
                        startActivity(intent);
                        finish();
                        Toast.makeText(MainActivity.this, "Sign Out successfully", Toast.LENGTH_LONG).show();
                    }
                });
    }

}
