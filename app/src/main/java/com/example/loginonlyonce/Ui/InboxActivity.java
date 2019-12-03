
package com.example.loginonlyonce.Ui;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.loginonlyonce.Adapter.AdapterInbox;
import com.example.loginonlyonce.Model.ModelClass;
import com.example.loginonlyonce.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class InboxActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AdapterInbox adapter;

    ArrayList<ModelClass> datalist;

    ProgressDialog progressBar;

    SharedPreferences preferences;

    CardView cvInbox;


    TextView txttitle, txtisi,txttype,txtinfo;

    Button btnkembali;

    View vInbox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inbox);

        txttype = findViewById(R.id.txttype);
        txttitle = findViewById(R.id.txttitle);
        txtisi = findViewById(R.id.txtisi);
        txtinfo = findViewById(R.id.txtinfo);
        btnkembali = findViewById(R.id.btnkembali);
        btnkembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Toolbar toolbar = findViewById(R.id.toolbarInbox);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Inbox");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        datalist = new ArrayList<>();

        preferences = getSharedPreferences("login", Context.MODE_PRIVATE);

        progressBar = new ProgressDialog(InboxActivity.this);

        cvInbox = findViewById(R.id.cvInbox);

        vInbox = findViewById(R.id.vInbox);

        recyclerView = findViewById(R.id.listInbox);

        progressBar.setMessage("Please wait");
        progressBar.show();

        AndroidNetworking.get(BaseURL.url+"/inbox?id=" + preferences.getInt("userid", 0) )
                .setTag("test")
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @SuppressLint("ResourceAsColor")
                    @Override
                    public void onResponse(JSONObject response) {

                        if (progressBar.isShowing()) {
                            progressBar.dismiss();
                        }

                        try {
                            JSONArray data = response.getJSONArray("PAYLOAD");

                            for (int i = 0; i < data.length(); i++) {

                                ModelClass model = new ModelClass();
                                JSONObject object = data.getJSONObject(i);
                                model.setTxttitle(object.getString("inb_subjek"));
                                model.setTxtisi(object.getString("inb_detail"));
                                model.setTxttype(object.getString("inb_type"));
                                model.setTxtdate(object.getString("inb_created_at"));
                                datalist.add(model);

                            }

                            adapter = new AdapterInbox(datalist);

                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(InboxActivity.this);

                            recyclerView.setLayoutManager(layoutManager);

                            recyclerView.setAdapter(adapter);

                            Log.d("pay1", "onResponse: " + response.getJSONArray("PAYLOAD"));

                            if (response.getJSONArray("PAYLOAD").length() == 0){

                                recyclerView.setVisibility(View.GONE);
                                txtinfo.setVisibility(View.VISIBLE);
                                btnkembali.setVisibility(View.VISIBLE);

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });

    }
}