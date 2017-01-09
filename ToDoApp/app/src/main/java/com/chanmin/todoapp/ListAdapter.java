package com.chanmin.todoapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017-01-09.
 */
class ListAdapter extends BaseAdapter {
    Context context;
    LayoutInflater inflater;
    ArrayList<Item> list;
    int layout;

    public ListAdapter(Context cont, int lay, ArrayList<Item> li) {
        context = cont;
        inflater = (LayoutInflater) cont.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        list = li;
        layout = lay;
    }

    public int getCount() {
        return list.size();
    }

    public String getItem(int position) {
        return list.get(position).title;
    }

    public long getItemId(int position) {
        return position;
    }
    public Boolean getCheck(int position){
        return list.get(position).check;
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        final int POS = position;
        if (convertView == null) {
            convertView = inflater.inflate(layout, parent, false);
        }
        TextView text = (TextView) convertView.findViewById(R.id.listTitle);
        text.setTag(position);
        text.setText(list.get(position).title);
        CheckBox checkBox = (CheckBox) convertView.findViewById(R.id.checkBox);
        checkBox.setTag(position);
        checkBox.setChecked(list.get(position).check);
        return convertView;
    }
}
