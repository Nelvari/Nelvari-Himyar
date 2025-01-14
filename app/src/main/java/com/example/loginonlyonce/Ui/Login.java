package com.example.loginonlyonce.Ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.loginonlyonce.R;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

import static android.provider.ContactsContract.Intents.Insert.EMAIL;

public class Login extends AppCompatActivity {

    EditText txtusername;
    EditText txtpassword;
    TextView tvdaftar;
    GoogleSignInClient mGoogleSignInClient;
    CallbackManager callbackManager;
    LoginButton loginButton;
    public String fbId;
    public String realName;
    public String email;
    public String avatar;
    String token="";
    String nohp="";

    int id;

    private ProgressDialog dialog;

    SharedPreferences mLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mLogin = getSharedPreferences("login", Context.MODE_PRIVATE);
        txtusername = (EditText) findViewById(R.id.txtusername);
        txtpassword = (EditText) findViewById(R.id.txtpassword);
        Button btnlogin = (Button) findViewById(R.id.btnlogin);


        tvdaftar = (TextView) findViewById(R.id.tvdaftar);
        tvdaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(Login.this, Registrasi.class);
                startActivity(in);
            }
        });

        dialog = new ProgressDialog(Login.this);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (txtusername.getText().toString().isEmpty() || txtpassword.getText().toString().isEmpty()) {
                    Toast.makeText(Login.this, "Silahkan isi username dan password terlebih dahulu", Toast.LENGTH_SHORT).show();
                }
                else {
                    dialog.setTitle("Login process");
                    dialog.setMessage("Please wait...");
                    dialog.show();
                    dialog.setCancelable(false);

                    AndroidNetworking.post(BaseURL.url+"/login")
                            .addBodyParameter("username", txtusername.getText().toString())
                            .addBodyParameter("password", txtpassword.getText().toString())
                            .addBodyParameter("role", "user")
                            .setTag("test")
                            .setPriority(Priority.MEDIUM)
                            .build()
                            .getAsJSONObject(new JSONObjectRequestListener() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    // do anything with response
                                    //stop
                                    try {
                                        Log.d("loginku ", "onResponse: "+response.toString());
                                        String status=response.getString("STATUS");
                                        if(status.equalsIgnoreCase("SUCCESS")){
                                            JSONObject getdata=response.getJSONObject("PAYLOAD");
                                            token=getdata.getString("login_token");
                                            nohp=getdata.getString("u_no_hp");
                                            email=getdata.getString("u_username");
                                            id = getdata.getInt("u_id");

                                            SharedPreferences.Editor editor = mLogin.edit();
                                            editor.putString("username", txtusername.getText().toString());
                                            editor.putString("nohp", nohp);
                                            editor.putString("email", email);
                                            editor.putInt("userid", id);
                                            editor.putString("data4", "");
                                            editor.apply();

                                            if (dialog.isShowing()) {
                                                dialog.dismiss();
                                            }

                                            String MESSAGE=response.getString("MESSAGE");
                                            AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
                                            builder.setMessage(MESSAGE)
                                                    .setTitle("Information")
                                                    .setCancelable(false)
                                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                        public void onClick(DialogInterface dialog, int id) {

                                                            Intent intent = new Intent(Login.this, Mainmenu.class);
                                                            startActivity(intent);
                                                            finish();

                                                        }
                                                    });
                                            AlertDialog alert = builder.create();
                                            alert.show();
                                        }else{
                                            if (dialog.isShowing()) {
                                                dialog.dismiss();
                                                Log.d("loginku", "onResponse: masuk else");
                                                String MESSAGE=response.getString("MESSAGE");
                                                AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
                                                builder.setMessage(MESSAGE)
                                                        .setTitle("Information")
                                                        .setCancelable(false)
                                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                            public void onClick(DialogInterface dialog, int id) {

                                                            }
                                                        });
                                                AlertDialog alert = builder.create();
                                                alert.show();
                                            }
                                        }
                                    } catch (JSONException e) {
                                        if (dialog.isShowing()) {
                                            dialog.dismiss();
                                            Toast.makeText(Login.this, "Login gagal", Toast.LENGTH_SHORT).show();
                                        }
                                        Log.d("errorku", "onResponse: "+e.toString());
                                        e.printStackTrace();
                                    }
                                }
                                @Override
                                public void onError(ANError error) {
                                    if (dialog.isShowing()) {
                                        dialog.dismiss();
                                        Toast.makeText(Login.this, "user tidak valid!", Toast.LENGTH_SHORT).show();
                                    }
                                    Toast.makeText(getApplicationContext(), "Eror", Toast.LENGTH_SHORT).show();
                                    Log.d("gagal login", "onResponse: "+error.toString());
                                }
                            });

                }

            }
        });

        SignInButton googleSignButton = findViewById(R.id.sign_in_button);

        googleSignButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                switch (view.getId()) {
                    case R.id.sign_in_button:
                        signIn();
                        break;

                }

            }
        });
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        callbackManager = CallbackManager.Factory.create();
        loginButton = findViewById(R.id.login_button);
        loginButton.setReadPermissions(Arrays.asList(EMAIL));
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                GraphRequest graphRequest = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        String userDetil = response.getRawResponse();
                        try {
                            JSONObject jsonObject = new JSONObject(userDetil);
                            Log.d("gambar", "onCompleted: " + jsonObject.toString());
                            fbId = jsonObject.getString("id");
                            realName = jsonObject.optString("name", "");
                            if (jsonObject.has("email")){
                                email = jsonObject.optString("email", "");
                            }else{
                                Log.d("gambar", "onCompleted: masuk else");
                                email = jsonObject.optString("name", "");
                            }

                            avatar = "https://graph.facebook.com/" + fbId + "/picture?type=large";



                            Log.d("gambar", "onCompleted: "+avatar);
                            Log.d("gambar", "oncompleted" + realName);
                            Log.d("gambar", "oncompleted" + email);
                            Log.d("gambar", "oncompleted" + fbId);

                            if (!dialog.isShowing()){
                                dialog.show();
                                dialog.setMessage("login process..");
                                dialog.setTitle("info");
                                dialog.setCancelable(false);

                            }

                            AndroidNetworking.post(BaseURL.url+"/register")
                                    .addBodyParameter("nama", email)
                                    .addBodyParameter("username", realName)
                                    .addBodyParameter("password", "facebook")
                                    .addBodyParameter("no_hp", "-")
                                    .addBodyParameter("role", "1")
                                    .setTag("test")
                                    .setPriority(Priority.MEDIUM)
                                    .build()
                                    .getAsJSONObject(new JSONObjectRequestListener() {
                                        @Override
                                        public void onResponse(JSONObject response) {
                                            Log.d("facebookdataku", "onResponse: " + response.toString());
                                            // do anything with response
                                            try {
                                                String status = response.getString("STATUS");
                                                if (status.equalsIgnoreCase("SUCCESS")) {

                                                    JSONObject getdata=response.getJSONObject("PAYLOAD");

                                                    //nohp=getdata.getString("u_no_hp");

                                                    id = getdata.getInt("u_id");

                                                    SharedPreferences.Editor editor = mLogin.edit();
                                                    editor.putString("username", realName);
                                                    editor.putString("nohp", "-");
                                                    editor.putString("password", "facebook");
                                                    editor.putInt("userid", id);
                                                    editor.putString("data2", email);
                                                    editor.putString("data3", fbId);
                                                    editor.putString("data4", avatar);
                                                    editor.apply();



                                                    Intent intent = new Intent(Login.this, Mainmenu.class);
                                                    startActivity(intent);
                                                    finish();

                                                    if (dialog.isShowing()) {
                                                        dialog.dismiss();
                                                    }

                                                }else if (status.equalsIgnoreCase("ERROR")){

                                                    if (response.getString("MESSAGE").equalsIgnoreCase("Username sudah terpakai!") ){
                                                        JSONObject getdata=response.getJSONObject("PAYLOAD");
                                                        id = getdata.getInt("u_id");
                                                        SharedPreferences.Editor editor = mLogin.edit();
                                                        editor.putString("username", realName);
                                                        editor.putString("nohp", "-");
                                                        editor.putString("password", "facebook");
                                                        editor.putInt("userid", id);
                                                        editor.putString("data2", email);
                                                        editor.putString("data3", fbId);
                                                        editor.putString("data4", avatar);
                                                        editor.apply();
                                                        Intent intent = new Intent(Login.this, Mainmenu.class);
                                                        startActivity(intent);
                                                        finish();
                                                    }

                                                }else {
                                                    if (dialog.isShowing()) {
                                                        dialog.dismiss();
                                                        Toast.makeText(Login.this, "Registrasi gagal, coba ulang lagi", Toast.LENGTH_SHORT).show();
                                                    }
                                                }
                                            } catch (JSONException e) {
                                                if (dialog.isShowing()) {
                                                    dialog.dismiss();
                                                    Toast.makeText(Login.this, "Registrasi gagal, coba ulang lagi", Toast.LENGTH_SHORT).show();
                                                }
                                                e.printStackTrace();
                                            }
                                        }

                                        @Override
                                        public void onError(ANError error) {
                                            if (dialog.isShowing()) {
                                                dialog.dismiss();
                                                Toast.makeText(Login.this, "Registrasi gagal, coba ulang lagi", Toast.LENGTH_SHORT).show();
                                            }
                                            Log.d("gagal login", "onResponse: " + error.toString());
                                            Log.d("gagal login", "onResponse: " + error.getErrorBody());
                                            Log.d("gagal login", "onResponse: " + error.getErrorCode());
                                            Log.d("gagal login", "onResponse: " + error.getErrorDetail());
                                        }
                                    });

                        } catch (JSONException ignored) {
                            Log.d("facebookgagal1", "onCompleted: " + ignored.getMessage());
                        }

                    }
                });

                Bundle parameters = new Bundle();
                parameters.putString("fields", "name,email");
                graphRequest.setParameters(parameters);
                graphRequest.executeAsync();

            }

            @Override
            public void onCancel() {
                Log.d("facebookgagal2", "onCompleted: " );
            }

            @Override
            public void onError(FacebookException error) {
                Log.d("facebookgagal3", "onCompleted: " + error.getMessage());
            }
        });

    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, 997);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 997) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            final String realName = account.getDisplayName();
            final String email = account.getEmail();
            final String fbId = account.getId();
            final String avatar;

            if (account.getPhotoUrl() != null) {
                avatar = account.getPhotoUrl().toString();
            } else {
                avatar = "avatar";
            }
            Log.d("avatarku", "handleSignInResult: "+avatar);

            Log.d("datagooglesaya", "handleSignInResult: " + realName + "," + email + "," + fbId + "," + avatar);

            //here
            if (!dialog.isShowing()){
                dialog.show();
                dialog.setMessage("login process..");
                dialog.setTitle("info");
                dialog.setCancelable(false);

            }
            AndroidNetworking.post(BaseURL.url+"/register")
                    .addBodyParameter("nama", email)
                    .addBodyParameter("username", realName)
                    .addBodyParameter("password", "google")
                    .addBodyParameter("no_hp", "-")
                    .addBodyParameter("role", "1")
                    .setTag("test")
                    .setPriority(Priority.MEDIUM)
                    .build()
                    .getAsJSONObject(new JSONObjectRequestListener() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Log.d("googledataku", "onResponse: " + response.toString());
                            // do anything with response
                            try {
                                String status = response.getString("STATUS");
                                if (status.equalsIgnoreCase("SUCCESS")) {

                                    JSONObject getdata=response.getJSONObject("PAYLOAD");

                                    //nohp=getdata.getString("u_no_hp");

                                    id = getdata.getInt("u_id");

                                    SharedPreferences.Editor editor = mLogin.edit();
                                    editor.putString("username", realName);
                                    editor.putString("nohp", "-");
                                    editor.putString("password", "google");
                                    editor.putInt("userid", id);
                                    editor.putString("data2", email);
                                    editor.putString("data3", fbId);
                                    editor.putString("data4", avatar);
                                    editor.apply();



                                    Intent intent = new Intent(Login.this, Mainmenu.class);
                                    startActivity(intent);
                                    finish();

                                    if (dialog.isShowing()) {
                                        dialog.dismiss();
                                    }

                                }else if (status.equalsIgnoreCase("ERROR")){

                                    if (response.getString("MESSAGE").equalsIgnoreCase("Username sudah terpakai!") ){
                                        JSONObject getdata=response.getJSONObject("PAYLOAD");
                                        id = getdata.getInt("u_id");
                                        SharedPreferences.Editor editor = mLogin.edit();
                                        editor.putString("username", realName);
                                        editor.putString("nohp", "-");
                                        editor.putString("password", "google");
                                        editor.putInt("userid", id);
                                        editor.putString("data2", email);
                                        editor.putString("data3", fbId);
                                        editor.putString("data4", avatar);
                                        editor.apply();
                                        Intent intent = new Intent(Login.this, Mainmenu.class);
                                        startActivity(intent);
                                        finish();
                                    }

                                }else {
                                    if (dialog.isShowing()) {
                                        dialog.dismiss();
                                        Toast.makeText(Login.this, "Registrasi gagal, coba ulang lagi", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            } catch (JSONException e) {
                                if (dialog.isShowing()) {
                                    dialog.dismiss();
                                    Toast.makeText(Login.this, "Registrasi gagal, coba ulang lagi", Toast.LENGTH_SHORT).show();
                                }
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onError(ANError error) {
                            if (dialog.isShowing()) {
                                dialog.dismiss();
                                Toast.makeText(Login.this, "Registrasi gagal, coba ulang lagi", Toast.LENGTH_SHORT).show();
                            }
                            Log.d("gagal login", "onResponse: " + error.toString());
                            Log.d("gagal login", "onResponse: " + error.getErrorBody());
                            Log.d("gagal login", "onResponse: " + error.getErrorCode());
                            Log.d("gagal login", "onResponse: " + error.getErrorDetail());
                        }
                    });

        } catch (ApiException e) {
            e.printStackTrace();
        }

    }

}
