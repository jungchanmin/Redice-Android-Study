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

    public ListAdapter(Context context, int layout, ArrayList<Item> list) {
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.list = list;
        this.layout = layout;
    }
    public int getCount() {
        return list.size();
    }
    public String getItem(int position) {
        return list.get(position).getTitle();
    }
    public Boolean getItem2(int position){
        return list.get(position).getCheck();
    }
    public long getItemId(int position) {
        return position;
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(layout, parent, false);
        }
        TextView text = (TextView) convertView.findViewById(R.id.listTitle);
        text.setTag(position);
        text.setText(getItem(position));
        CheckBox checkBox = (CheckBox) convertView.findViewById(R.id.checkBox);
        checkBox.setTag(position);
        checkBox.setChecked(getItem2(position));
        return convertView;
    }
}
