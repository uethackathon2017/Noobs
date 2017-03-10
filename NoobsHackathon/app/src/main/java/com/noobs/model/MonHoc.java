package com.noobs.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by SUNFLOWER on 3/10/2017.
 */

public class MonHoc implements Serializable{
    private String tenMonHoc;
    private ArrayList<DapAn> dsDapAn;

    public MonHoc() {
    }

    public String getTenMonHoc() {

        return tenMonHoc;
    }

    public void setTenMonHoc(String tenMonHoc) {
        this.tenMonHoc = tenMonHoc;
    }

    public ArrayList<DapAn> getDsDapAn() {
        return dsDapAn;
    }

    public void setDsDapAn(ArrayList<DapAn> dsDapAn) {
        this.dsDapAn = dsDapAn;
    }

    public MonHoc(String tenMonHoc, ArrayList<DapAn> dsDapAn) {

        this.tenMonHoc = tenMonHoc;
        this.dsDapAn = dsDapAn;
    }
}
