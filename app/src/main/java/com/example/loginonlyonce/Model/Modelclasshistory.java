package com.example.loginonlyonce.Model;

import android.net.Uri;

public class Modelclasshistory {

    private String tvtitle;
    private String ivfile;

    public  Modelclasshistory(String tvtitle, String ivfile) {
        this.setTvtitle(tvtitle);
        this.setIvfile(ivfile);

    }

    public String getTvtitle() {
        return tvtitle;
    }

    public void setTvtitle(String tvtitle) {
        this.tvtitle = tvtitle;
    }

    public Uri getIvfile() {
        return ivfile;
    }

    public void setIvfile(String ivfile) {
        this.ivfile = ivfile;
    }
}
