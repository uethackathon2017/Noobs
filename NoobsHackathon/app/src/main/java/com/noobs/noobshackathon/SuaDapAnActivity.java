package com.noobs.noobshackathon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.noobs.adapter.CauTraLoiAdapter;
import com.noobs.model.DapAn;

import java.util.ArrayList;
import java.util.Collections;

public class SuaDapAnActivity extends AppCompatActivity {
    EditText txtMaDe;
    ArrayList<Character> dsCauTraLoi;
    CauTraLoiAdapter cauTraLoiAdapter;

    ListView lvCauTraLoi;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sua_dap_an);
        addControl();
        addEvent();
    }

    private void addEvent() {

    }
    private void addControl() {
        txtMaDe= (EditText) findViewById(R.id.txtMaDe);
        intent=getIntent();
        DapAn da= (DapAn) intent.getSerializableExtra("DAPANSENT");
        if(da.getMaDe()!=0) {
            txtMaDe.setText(Integer.toString(da.getMaDe()));
        }
      /*  lvCauTraLoi= (ListView) findViewById(R.id.lvCauTraLoi);
        String tmp=da.getCauTraLoi();
        for(int i=0;i<tmp.length();i++){

            dsCauTraLoi.add(Character.valueOf(tmp[i]));
        }*/
    }
}
