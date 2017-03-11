package com.noobs.noobshackathon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.noobs.adapter.MonHocAdapter;
import com.noobs.model.DapAn;
import com.noobs.model.MonHoc;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<MonHoc> dsMonHoc;
    MonHocAdapter monHocAdapter;
    ListView lvDsMonHoc;
    ImageButton btnAddMonHoc;
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
        lvDsMonHoc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this,"Vào màn camera "+Integer.toString(position), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void xuLyAddMonHoc() {
        Intent intent=new Intent(MainActivity.this,CameraActivity.class);
        intent.putExtra("MONHOCSENT",new MonHoc());
        startActivityForResult(intent,111);

       /* final AlertDialog dialog;
        AlertDialog.Builder mBuilder=new AlertDialog.Builder(MainActivity.this);
        View mView= getLayoutInflater().inflate(R.layout.dialog_nhapten,null);
        TextView txtTitle= (TextView) mView.findViewById(R.id.txtTitle);
        txtTitle.setText("Nhâp tên môn học!");
        final TextView txtTenMonHoc= (TextView) mView.findViewById(R.id.txtContentDialog);
        Button btnOk= (Button) mView.findViewById(R.id.btnXacNhan);
        mBuilder.setView(mView);
        dialog=mBuilder.create();
        dialog.show();
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!txtTenMonHoc.getText().toString().isEmpty()) {*/


    }

    private void addControl() {
        lvDsMonHoc= (ListView) findViewById(R.id.lvDsMonHoc);
        btnAddMonHoc= (ImageButton) findViewById(R.id.btnAddMonHoc);
        dsMonHoc=new ArrayList<>();
        monHocAdapter=new MonHocAdapter(MainActivity.this,R.layout.item_monhoc,dsMonHoc);
        lvDsMonHoc.setAdapter(monHocAdapter);
        gialapdulieu();
    }

    private void gialapdulieu() {
        String arr1="Abcdefgh";
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
        dsMonHoc.add(boDa2);
        dsMonHoc.add(boDa2);
        dsMonHoc.add(boDa2);
        monHocAdapter.notifyDataSetChanged();
    }
}
