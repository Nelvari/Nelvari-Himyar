package com.example.loginonlyonce.Ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.loginonlyonce.Model.ModelClass;
import com.example.loginonlyonce.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class InboxActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    ArrayList<ModelClass> dataList;

    ProgressDialog progressBar;

    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inbox);

        preferences = getSharedPreferences("login", Context.MODE_PRIVATE);

        dataList = new ArrayList<>();

        recyclerView = findViewById(R.id.rvInbox);

        progressBar = new ProgressDialog(InboxActivity.this);

        progressBar.setMessage("Please wait");
        progressBar.show();

        AndroidNetworking.get("http://api-ppdb.smkrus.com/api/v1/inbox?id=" + preferences.getInt("userid", 0))
                .setTag("test")
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {

                            if (progressBar.isShowing()) {
                                progressBar.dismiss();
                            }

                            String status=response.getString("STATUS");
                            if (status.equalsIgnoreCase("SUCCESS")){

                                JSONObject jsonObject = response.getJSONObject("PAYLOAD");
                                ModelClass model = new ModelClass();

                                model.setTxttitle(jsonObject.getString("inb_subjek"));
                                model.setTxtisi(jsonObject.getString("inb_detail"));
                                model.setTxttype(jsonObject.getString("inb_type"));

                            }

                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(InboxActivity.this);

                            recyclerView.setLayoutManager(layoutManager);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onError(ANError anError) {

                        Log.d("tes", "onError: ");

                    }
                });

    }
}
