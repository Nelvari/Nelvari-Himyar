package com.example.loginonlyonce.Ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

import com.example.loginonlyonce.R;

public class KartuUjian extends AppCompatActivity {

    WebView wvKartuUjian;

    private SharedPreferences mUjian;

    private ProgressDialog progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kartu_ujian);

        mUjian = getSharedPreferences("login", Context.MODE_PRIVATE);

        progressBar = new ProgressDialog(KartuUjian.this);

        progressBar.setMessage("Please wait");
        progressBar.show();

        wvKartuUjian = (WebView) findViewById(R.id.wvKartuUjian);
        wvKartuUjian.getSettings().setLoadsImagesAutomatically(true);
        wvKartuUjian.getSettings().setJavaScriptEnabled(true);
        wvKartuUjian.getSettings().setDomStorageEnabled(true);

        // Tiga baris di bawah ini agar laman yang dimuat dapat
        // melakukan zoom.
        wvKartuUjian.getSettings().setSupportZoom(true);
        wvKartuUjian.getSettings().setBuiltInZoomControls(true);
        wvKartuUjian.getSettings().setDisplayZoomControls(false);
        // Baris di bawah untuk menambahkan scrollbar di dalam WebView-nya
        wvKartuUjian.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        wvKartuUjian.setWebViewClient(new WebViewClient());
        wvKartuUjian.loadUrl("http://api-ppdb.smkrus.com/preview/"+ mUjian.getInt("userid", 0) +"?type=TEST");

        String url = "http://api-ppdb.smkrus.com/preview/"+ mUjian.getInt("userid", 0) +"?type=TEST";

        wvKartuUjian.setDownloadListener(new DownloadListener() {
            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        if (progressBar.isShowing()){
            progressBar.dismiss();
        }

    }
}
