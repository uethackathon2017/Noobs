package com.noobs.noobshackathon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.noobs.adapter.MonHocAdapter;
import com.noobs.model.DapAn;
import com.noobs.model.MonHoc;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<MonHoc> dsMonHoc;
    MonHocAdapter monHocAdapter;
    ListView lvDsMonHoc;
    Button btnAddMonHoc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControl();
        addEvent();
    }
    private void addEvent() {
        btnAddMonHoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyAddMonHoc();
            }
        });
    }

    private void xuLyAddMonHoc() {
        

    }

    private void addControl() {
        lvDsMonHoc= (ListView) findViewById(R.id.lvDsMonHoc);
        btnAddMonHoc= (Button) findViewById(R.id.btnAddMonHoc);
        dsMonHoc=new ArrayList<>();
        monHocAdapter=new MonHocAdapter(MainActivity.this,R.layout.item_monhoc,dsMonHoc);
        lvDsMonHoc.setAdapter(monHocAdapter);
        gialapdulieu();
    }

    private void gialapdulieu() {
        String arr1="Abcd";
        DapAn da1=new DapAn(123,arr1);
        DapAn da2=new DapAn(124,arr1);
        ArrayList<DapAn> dsDa=new ArrayList<>();
        dsDa.add(da1);
        dsDa.add(da2);
        MonHoc boDa=new MonHoc("VatLy",null);
        MonHoc boDa1=new MonHoc("Toan",dsDa);
        MonHoc boDa2=new MonHoc("Hoa",dsDa);
        dsMonHoc.add(boDa);
        dsMonHoc.add(boDa1);
        dsMonHoc.add(boDa2);
        monHocAdapter.notifyDataSetChanged();
    }
}
