package com.noobs.noobshackathon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.noobs.adapter.DapAnAdapter;
import com.noobs.adapter.MonHocAdapter;
import com.noobs.model.DapAn;
import com.noobs.model.MonHoc;

import java.util.ArrayList;

public class ChonDapAnActivity extends AppCompatActivity {
    ArrayList<DapAn> dsDapAn;
    DapAnAdapter dapAnAdapter;
    ListView lvDsDapAn;
    Button btnAddDapAn;
    EditText txtTenMonHoc;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chon_dap_an);
        addControl();
        addEvent();
    }

    private void addControl() {

        intent = getIntent();
        MonHoc tmp = (MonHoc) intent.getSerializableExtra("MONHOCSENT");

        txtTenMonHoc= (EditText) findViewById(R.id.txtTenMonHoc);
        txtTenMonHoc.setText(tmp.getTenMonHoc());
        btnAddDapAn = (Button) findViewById(R.id.btnAddDapAn);
        lvDsDapAn = (ListView) findViewById(R.id.lvDsDapAn);
        dsDapAn = tmp.getDsDapAn();
        if(dsDapAn!=null){
            dapAnAdapter = new DapAnAdapter(ChonDapAnActivity.this, R.layout.item_dapan, dsDapAn);
            lvDsDapAn.setAdapter(dapAnAdapter);
        }else{

        }

    }

    private void addEvent() {

    }


}
