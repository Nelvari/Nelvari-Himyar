package com.example.loginonlyonce;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

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
    GoogleSignInClient mGoogleSignInClient;
    CallbackManager callbackManager;
    LoginButton loginButton;
    public String fbId;
    public String realName;
    public String email;
    public String avatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtusername = (EditText) findViewById(R.id.txtusername);
        txtpassword = (EditText) findViewById(R.id.txtpassword);
        Button btnlogin = (Button) findViewById(R.id.btnlogin);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences mLogin = getSharedPreferences("login", Context.MODE_PRIVATE);

                final String username = txtusername.getText().toString().trim();
                final String password = txtpassword.getText().toString().trim();

                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(Login.this, "please fill my heart first to send a request :(", Toast.LENGTH_SHORT).show();
                }
                else {

                    SharedPreferences.Editor editor = mLogin.edit();
                    editor.putString("username", username);
                    editor.putInt("userid", getTaskId());
                    editor.apply();
                    Intent intent = new Intent(Login.this, MainActivity.class);
                    startActivity(intent);
                    finish();

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
                            editor.apply();

                            Intent intent = new Intent(Login.this, MainActivity.class);
                            intent.putExtra("data1", realName);
                            intent.putExtra("data2", email);
                            intent.putExtra("data3", fbId);
                            intent.putExtra("data4", avatar);
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
        startActivityForResult(signInIntent, 0);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 0) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {

        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            String realName = account.getDisplayName();
            String username = account.getGivenName() + account.getId();
            String email = account.getEmail();
            String userId = account.getId();
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
            editor.apply();
            Intent intent = new Intent(Login.this, MainActivity.class);
            startActivity(intent);
            finish();

        } catch (ApiException e) {
            e.printStackTrace();
        }

    }



}
