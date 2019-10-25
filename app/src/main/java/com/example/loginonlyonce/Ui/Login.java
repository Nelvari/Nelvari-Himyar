package com.example.loginonlyonce.Ui;

import android.app.ProgressDialog;
import android.content.Context;
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
                mLogin = getSharedPreferences("login", Context.MODE_PRIVATE);
                if (txtusername.getText().toString().isEmpty() || txtpassword.getText().toString().isEmpty()) {
                    Toast.makeText(Login.this, "Silahkan isi username dan password terlebih dahulu", Toast.LENGTH_SHORT).show();
                }
                else {
                    dialog.setTitle("Login process");
                    dialog.setMessage("Please wait...");
                    dialog.show();
                    dialog.setCancelable(false);

                    AndroidNetworking.post("http://api-ppdb.smkrus.com/api/v1/login")
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
                                        if(status.equalsIgnoreCase("SUCCES")){
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

                                            Intent intent = new Intent(Login.this, Mainmenu.class);
                                            startActivity(intent);
                                            finish();


                                        }else{
                                            if (dialog.isShowing()) {
                                                dialog.dismiss();
                                                Toast.makeText(Login.this, "Login gagal", Toast.LENGTH_SHORT).show();
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
                                        Toast.makeText(Login.this, "Login gagal", Toast.LENGTH_SHORT).show();
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
                            fbId = jsonObject.getString("id");
                            realName = jsonObject.optString("name", "");
                            email = jsonObject.optString("email", "");
                            avatar = "https://graph.facebook.com/" + fbId + "/picture?type=large";

                            Log.d("gambar", "onCompleted: "+avatar);
                            Log.d("gambar", "oncompleted" + realName);
                            Log.d("gambar", "oncompleted" + email);
                            Log.d("gambar", "oncompleted" + fbId);

                            SharedPreferences mLogin = getSharedPreferences("login", Context.MODE_PRIVATE);

                            SharedPreferences.Editor editor = mLogin.edit();
                            editor.putInt("userid", getTaskId());
                            editor.putString("username", realName);
                            editor.putString("data2", email);
                            editor.putString("data3", fbId);
                            editor.putString("data4", avatar);
                            editor.apply();

                            Intent intent = new Intent(Login.this, Mainmenu.class);
                            startActivity(intent);
                            finish();

                        } catch (JSONException ignored) {
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

            }

            @Override
            public void onError(FacebookException error) {

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
            String realName = account.getDisplayName();
            String email = account.getEmail();
            String fbId = account.getId();
            String avatar;
            if (account.getPhotoUrl() != null) {
                avatar = account.getPhotoUrl().toString();
            } else {
                avatar = "avatar";
            }
            Log.d("avatarku", "handleSignInResult: "+avatar);
            SharedPreferences mLogin = getSharedPreferences("login", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = mLogin.edit();
            editor.putInt("userid", getTaskId());
            editor.putString("username", realName);
            editor.putString("data2", email);
            editor.putString("data3", fbId);
            editor.putString("data4", avatar);
            editor.apply();
            Intent intent = new Intent(Login.this, Mainmenu.class);
            startActivity(intent);
            finish();
        } catch (ApiException e) {
            e.printStackTrace();
        }

    }

}
