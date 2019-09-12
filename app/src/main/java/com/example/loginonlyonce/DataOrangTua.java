package com.example.loginonlyonce;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class DataOrangTua extends AppCompatActivity {

    Button btnSimpanOrangTua;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.formorangtua);

        btnSimpanOrangTua=(Button)findViewById(R.id.btnSimpanOrangTua);
        btnSimpanOrangTua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //dialog
                Intent in =new Intent(getApplicationContext(),DataAsalSekolah.class);
                //put extra here
                startActivity(in);
            }
        });
//        Toolbar toolbar = findViewById(R.id.toolbar);
//
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setTitle("Formulir Pendaftaran");
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//
//        ViewPager viewPager = findViewById(R.id.viewPager);
//        setupViewPager(viewPager);
//
//        // setting tabLayout
//        TabLayout tabLayout = findViewById(R.id.tabLayout);
//        tabLayout.setupWithViewPager(viewPager);

    }
//
//    private void setupViewPager(ViewPager viewPager) {
//
//        MainFragmentPagerAdapter mainFragmentPagerAdapter = new MainFragmentPagerAdapter(getSupportFragmentManager());
//        mainFragmentPagerAdapter.addFragment(new OrangTuaFragment(), getString(R.string.orangtua));
//        mainFragmentPagerAdapter.addFragment(new WaliFragment(), getString(R.string.wali));
//        viewPager.setAdapter(mainFragmentPagerAdapter);
//
//    }
}
