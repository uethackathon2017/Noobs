package com.noobs.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
        LayoutInflater inflater=this.context.getLayoutInflater();
        View row=inflater.inflate(this.resource,null);
        TextView txtTenMonHoc= (TextView) row.findViewById(R.id.txtTenMonHoc);
        Button btnEditMonHoc= (Button) row.findViewById(R.id.btnEditMonHoc);
        Button btnDelMonHoc= (Button) row.findViewById(R.id.btnDelMonHoc);
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
        MonHoc tmp=this.objects.get(position);
        txtTenMonHoc.setText(tmp.getTenMonHoc());
        return row;
    }

    private void xulyDelMonHoc(int position) {
        this.objects.remove(position);
        this.notifyDataSetChanged();
    }

    private void xulyEditMonHoc(int position) {
        Intent intent=new Intent(context,SuaMonHocActivity.class);
        intent.putExtra("MONHOCSENT",objects.get(position));
        context.startActivityForResult(intent,111);
    }
}
