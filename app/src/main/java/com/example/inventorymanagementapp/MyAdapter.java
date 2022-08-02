package com.example.inventorymanagementapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MyAdapter extends ArrayAdapter<Modal> {
    public MyAdapter(@NonNull Context context, ArrayList<Modal> modals) {
        super(context, 0,modals);
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Modal modal =getItem(position);
        if (convertView==null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.table_row,parent,false);
        }
        TextView tv1 = convertView.findViewById(R.id.textView2);
        TextView tv2 = convertView.findViewById(R.id.textView3);
        TextView tv3 = convertView.findViewById(R.id.textView4);
        tv1.setText(modal.getName());
        tv2.setText(modal.getName2());
        tv3.setText(modal.getName3());
        return convertView;
    }
}
