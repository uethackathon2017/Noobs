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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;

import com.noobs.noobshackathon.R;

import java.util.List;

/**
 * Created by SUNFLOWER on 3/10/2017.
 */

public class CauTraLoiAdapter extends ArrayAdapter<Character> {
    Activity context;
    int resource;
    List<Character> objects;
    public CauTraLoiAdapter(@NonNull Activity context, @LayoutRes int resource, @NonNull List<Character> objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
        this.objects=objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View row=inflater.inflate(this.resource,null);
        TextView txtSttCau= (TextView) row.findViewById(R.id.txtSttCau);
        txtSttCau.setText("Câu sô "+Integer.toString(position+1)+":");
        RadioButton radA= (RadioButton) row.findViewById(R.id.radA);
        RadioButton radB= (RadioButton) row.findViewById(R.id.radB);
        RadioButton radC= (RadioButton) row.findViewById(R.id.radC);
        RadioButton radD= (RadioButton) row.findViewById(R.id.radD);
        RadioGroup radioGroup= (RadioGroup) row.findViewById(R.id.radGroup);
        Character ch=this.objects.get(position);
        switch (Character.toLowerCase(ch.charValue())) {
            case 'a': {
                radioGroup.check(R.id.radA);
            }
            break;
            case 'b': {
                radioGroup.check(R.id.radB);
            }
            break;
            case 'c': {
                radioGroup.check(R.id.radC);
            }
            break;
            case 'd': {
                radioGroup.check(R.id.radD);
            }
            break;
            default: radioGroup.clearCheck();
        }
        return row;
    }
}
