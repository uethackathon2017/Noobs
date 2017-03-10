package com.noobs.adapter;

import android.app.Activity;
import android.content.Context;
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
import com.noobs.noobshackathon.R;

import java.util.ArrayList;
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
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=this.context.getLayoutInflater();
        View row=inflater.inflate(this.resource,null);
        TextView txtTenMonHoc= (TextView) row.findViewById(R.id.txtTenMonHoc);
        Button btnEditMonHoc= (Button) row.findViewById(R.id.btnEditMonHoc);
        Button btnDelMonHoc= (Button) row.findViewById(R.id.btnDelMonHoc);

        MonHoc tmp=this.objects.get(position);
        txtTenMonHoc.setText(tmp.getTenMonHoc());
        return row;
    }
}
