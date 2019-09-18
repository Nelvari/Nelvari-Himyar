package com.example.loginonlyonce.Model;

public class ModelClass {

    private String txttitle;
    private String txtisi;
    private String txttype;


    public ModelClass (String tvtitle, String tvisi, String tvtype) {
        this.txttitle = tvtitle;
        this.txtisi = tvisi;
        this.txttype = tvtype;

    }

    public String getTxttitle() {
        return txttitle;
    }

    public void setTxttitle(String txttitle) {
        this.txttitle = txttitle;
    }

    public String getTxtisi() {
        return txtisi;
    }

    public void setTxtisi(String txtisi) {
        this.txtisi = txtisi;
    }

    public String getTxtwaktu() {
        return txttype;
    }

    public void setTxtwaktu(String txtwaktu) {
        this.txttype = txtwaktu;
    }
}