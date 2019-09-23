package com.example.loginonlyonce.Ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.RelativeLayout;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.loginonlyonce.Adapter.InboxAdapter;
import com.example.loginonlyonce.Model.ModelClass;
import com.example.loginonlyonce.R;

import org.json.JSONObject;

import java.util.ArrayList;

public class InboxActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private InboxAdapter adapter;

    private RelativeLayout relativeLayout;

    ArrayList<ModelClass> dataList;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inbox);
        recyclerView = (RecyclerView) findViewById(R.id.ListDATA);
        swipeRefreshLayout=(SwipeRefreshLayout)  findViewById(R.id.Swiperefresh);
        relativeLayout =(RelativeLayout)  findViewById(R.id.rlloading);
        dataList = new ArrayList<>();


        swipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                // Stop animation (This will be after 3 seconds)
                                swipeRefreshLayout.setRefreshing(false);
                            }
                        }, 1500);

                    }
                }
        );
    }
}
