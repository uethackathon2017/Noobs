package com.noobs.adapter;

import android.app.Activity;
import android.content.Context;
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

import com.noobs.model.DapAn;
import com.noobs.noobshackathon.R;

import java.util.List;

/**
 * Created by SUNFLOWER on 3/10/2017.
 */

public class DapAnAdapter extends ArrayAdapter<DapAn> {
    Activity context;
    int resource;
    List<DapAn> objects;

    public DapAnAdapter(@NonNull Activity context, @LayoutRes int resource, @NonNull List<DapAn> objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
        this.objects=objects;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View row=inflater.inflate(this.resource,null);
        TextView txtTenDapAn= (TextView) row.findViewById(R.id.txtTenDapAn);
        Button btnDelDapAn= (Button) row.findViewById(R.id.btnDelDapAn);
        DapAn da=this.objects.get(position);
        txtTenDapAn.setText(Integer.toString(da.getMaDe()));
        btnDelDapAn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xulyDelDapAn(position);
            }
        });
        return row;
    }

    private void xulyDelDapAn(int position) {
        this.objects.remove(position);
        this.notifyDataSetChanged();
    }
}
