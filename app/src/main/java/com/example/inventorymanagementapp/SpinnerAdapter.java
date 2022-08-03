package com.example.inventorymanagementapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class SpinnerAdapter extends ArrayAdapter<SpinnerModal> {
    public SpinnerAdapter(@NonNull Context context, ArrayList<SpinnerModal> list) {
        super(context, 0,list);
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return customView(position, convertView, parent);}@Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return customView(position, convertView, parent); }
    public View customView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        if(convertView==null){
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.custom_spinner,parent,false);
        }
       SpinnerModal items=getItem(position);
        TextView spinnerName=convertView.findViewById(R.id.textView14);
        TextView spinnerNum=convertView.findViewById(R.id.textView15);
        if(items!=null){
            spinnerName.setText(items.getName());
            spinnerNum.setText(items.getNumber());
        }
        return convertView;
    }
}
