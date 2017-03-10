package com.noobs.model;

/**
 * Created by SUNFLOWER on 3/10/2017.
 */

public class DapAn {
    private int maDe;
    private String cauTraLoi;

    public DapAn() {
    }

    public int getMaDe() {

        return maDe;
    }

    public void setMaDe(int maDe) {
        this.maDe = maDe;
    }

    public String getCauTraLoi() {
        return cauTraLoi;
    }

    public void setCauTraLoi(String cauTraLoi) {
        this.cauTraLoi = cauTraLoi;
    }

    public DapAn(int maDe, String cauTraLoi) {

        this.maDe = maDe;
        this.cauTraLoi = cauTraLoi;
    }
}
