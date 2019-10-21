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

public class KartuPeserta extends AppCompatActivity {

    WebView wvKartuPeserta;

    private SharedPreferences mPeserta;

    private ProgressDialog progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kartu_peserta);

        mPeserta = getSharedPreferences("login", Context.MODE_PRIVATE);

        progressBar = new ProgressDialog(KartuPeserta.this);

        progressBar.setMessage("Please wait");
        progressBar.show();

        wvKartuPeserta = (WebView) findViewById(R.id.wvKartuPeserta);
        wvKartuPeserta.getSettings().setLoadsImagesAutomatically(true);
        wvKartuPeserta.getSettings().setJavaScriptEnabled(true);
        wvKartuPeserta.getSettings().setDomStorageEnabled(true);

        // Tiga baris di bawah ini agar laman yang dimuat dapat
        // melakukan zoom.
        wvKartuPeserta.getSettings().setSupportZoom(true);
        wvKartuPeserta.getSettings().setBuiltInZoomControls(true);
        wvKartuPeserta.getSettings().setDisplayZoomControls(false);
        // Baris di bawah untuk menambahkan scrollbar di dalam WebView-nya
        wvKartuPeserta.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        wvKartuPeserta.setWebViewClient(new WebViewClient());
        wvKartuPeserta.loadUrl("http://api-ppdb.smkrus.com/preview/"+ mPeserta.getInt("userid", 0) +"?type=PENDAFTARAN");

        String url = "http://api-ppdb.smkrus.com/preview/"+ mPeserta.getInt("userid", 0) +"?type=PENDAFTARAN";

        wvKartuPeserta.setDownloadListener(new DownloadListener() {
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
