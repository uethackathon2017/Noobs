package com.noobs.noobshackathon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.noobs.adapter.DapAnAdapter;
import com.noobs.model.DapAn;
import com.noobs.model.MonHoc;

import java.util.ArrayList;

public class SuaMonHocActivity extends AppCompatActivity {
    ArrayList<DapAn> dsDapAn;
    DapAnAdapter dapAnAdapter;
    ListView lvDsDapAn;
    Button btnAddDapAn;
    EditText txtTenMonHoc;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sua_mon_hoc);
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
        lvDsDapAn.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                xulyDapAn(position);
            }
        });
        if(dsDapAn!=null){
            dapAnAdapter = new DapAnAdapter(SuaMonHocActivity.this, R.layout.item_dapan, dsDapAn);
            lvDsDapAn.setAdapter(dapAnAdapter);
        }else{

        }
        btnAddDapAn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xulyDapAn(-1);
            }
        });

    }

    private void xulyDapAn(int position) {
        Intent intent=new Intent(SuaMonHocActivity.this,SuaDapAnActivity.class);
        if(position==-1){
        intent.putExtra("DAPANSENT",new DapAn());
        }else{
            intent.putExtra("DAPANSENT",dsDapAn.get(position));
        }
        startActivityForResult(intent,333);
    }

    private void addEvent() {

    }


}
