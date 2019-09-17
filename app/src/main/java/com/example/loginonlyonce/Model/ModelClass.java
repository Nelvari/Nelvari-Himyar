package com.example.loginonlyonce.Model;

public class ModelClass {

    private String txttitle;
    private String txtisi;
    private String txtwaktu;


    public ModelClass(String txttitle, String txtisi, String txtwaktu) {
        this.setTxttitle(txttitle);
        this.setTxtisi(txtisi);
        this.setTxtwaktu(txtwaktu);

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
        return txtwaktu;
    }

    public void setTxtwaktu(String txtwaktu) {
        this.txtwaktu = txtwaktu;
    }
}