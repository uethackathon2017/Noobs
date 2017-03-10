package com.noobs.model;

import java.util.ArrayList;

/**
 * Created by SUNFLOWER on 3/10/2017.
 */

public class MonHoc {
    private String tenMonHoc;
    private ArrayList<DapAn> dapAnArrayList;
    public MonHoc() {
    }

    public String getTenMonHoc() {

        return tenMonHoc;
    }

    public void setTenMonHoc(String tenMonHoc) {
        this.tenMonHoc = tenMonHoc;
    }

    public ArrayList<DapAn> getDapAnArrayList() {
        return dapAnArrayList;
    }

    public void setDapAnArrayList(ArrayList<DapAn> dapAnArrayList) {
        this.dapAnArrayList = dapAnArrayList;
    }

    public MonHoc(String tenMonHoc, ArrayList<DapAn> dapAnArrayList) {

        this.tenMonHoc = tenMonHoc;
        this.dapAnArrayList = dapAnArrayList;
    }


}
