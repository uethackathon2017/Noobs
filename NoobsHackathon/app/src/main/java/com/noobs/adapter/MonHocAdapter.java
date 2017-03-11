package com.noobs.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.noobs.model.MonHoc;
import com.noobs.noobshackathon.SuaMonHocActivity;
import com.noobs.noobshackathon.R;

import java.util.List;

/**
 * Created by SUNFLOWER on 3/10/2017.
 */

public class MonHocAdapter extends ArrayAdapter<MonHoc> {
    Activity context;
    int resource;
    List<MonHoc> objects;
    public MonHocAdapter(@NonNull Activity context, @LayoutRes int resource, @NonNull List<MonHoc> objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
        this.objects=objects;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        View row = inflater.inflate(this.resource, null);
        TextView txtSttMonHoc = (TextView) row.findViewById(R.id.txtSttMonHoc);
        TextView txtTenMonHoc = (TextView) row.findViewById(R.id.txtTenMonHoc);
        TextView txtMoTaMonHoc = (TextView) row.findViewById(R.id.txtMoTaMonHoc);

        ImageButton btnEditMonHoc = (ImageButton) row.findViewById(R.id.btnEditMonHoc);
        ImageButton btnDelMonHoc = (ImageButton) row.findViewById(R.id.btnDelMonHoc);
        btnEditMonHoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xulyEditMonHoc(position);
            }
        });
        btnDelMonHoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xulyDelMonHoc(position);
            }
        });
        MonHoc tmp = this.objects.get(position);
        txtTenMonHoc.setText(tmp.getTenMonHoc());
        txtSttMonHoc.setText(Integer.toString(position + 1));
        if (tmp.getDsDapAn() != null) {
            txtMoTaMonHoc.setText(Integer.toString(tmp.getDsDapAn().size())+" đề");
        }
        else{
            txtMoTaMonHoc.setText("Chưa có dữ liệu!");
        }
        return row;
    }

    private void xulyDelMonHoc(final int position) {
        AlertDialog.Builder mBuilder=new AlertDialog.Builder(context);
        View mView= context.getLayoutInflater().inflate(R.layout.dialog_warning,null);
        TextView txtDialogTitle= (TextView) mView.findViewById(R.id.txtDialogTitle);
        TextView txtDialogMess= (TextView) mView.findViewById(R.id.txtDialogMess);

        Button btnDialogOk= (Button) mView.findViewById(R.id.btnDialogOk);
        Button btnDialogCancel= (Button) mView.findViewById(R.id.btnDialogCancel);
        txtDialogTitle.setText("Cảnh báo");
        txtDialogMess.setText("Bạn có chắc muốn xóa môn "+this.objects.get(position).getTenMonHoc());
        btnDialogOk.setText("Xóa");
        btnDialogCancel.setText("Hủy");
        mBuilder.setView(mView);
        final AlertDialog dialog=mBuilder.create();
        dialog.show();
        btnDialogOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xulyOkClick(position);
                dialog.dismiss();
            }
        });
        btnDialogCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }

    private void xulyOkClick(int position) {
        this.objects.remove(position);
        this.notifyDataSetChanged();
    }

    private void xulyEditMonHoc(int position) {
        Intent intent=new Intent(context,SuaMonHocActivity.class);
        intent.putExtra("MONHOCSENT",objects.get(position));
        context.startActivityForResult(intent,111);
    }
}
