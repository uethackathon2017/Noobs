package com.noobs.noobshackathon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.noobs.adapter.CauTraLoiAdapter;
import com.noobs.model.DapAn;

import java.util.ArrayList;
import java.util.Collections;

public class SuaDapAnActivity extends AppCompatActivity {
    TextView txtMaDe;
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
        txtMaDe= (TextView) findViewById(R.id.txtMaDe);
        intent=getIntent();
        DapAn da= (DapAn) intent.getSerializableExtra("DAPANSENT");

        if(da.getMaDe()!=0) {
            txtMaDe.setText(Integer.toString(da.getMaDe()));
        }
        lvCauTraLoi= (ListView) findViewById(R.id.lvCauTraLoi);
        String tmp=da.getCauTraLoi();
        dsCauTraLoi=new ArrayList<>();
        for (int i=0;i<tmp.length();i++){
            dsCauTraLoi.add(Character.valueOf(tmp.charAt(i)));
        }
        if(dsCauTraLoi!=null){
            cauTraLoiAdapter=new CauTraLoiAdapter(SuaDapAnActivity.this,R.layout.item_cautraloi,dsCauTraLoi);
            lvCauTraLoi.setAdapter(cauTraLoiAdapter);
        }
    }
}
