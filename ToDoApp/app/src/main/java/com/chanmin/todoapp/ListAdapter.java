package com.chanmin.todoapp;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
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
    Boolean[] deleteItem;

    public ListAdapter(Context context, int layout, ArrayList<Item> list, Boolean[] deleteItem) {
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.list = list;
        this.layout = layout;
        this.deleteItem = deleteItem;
    }

    public int getCount() {
        return list.size();
    }

    public Item getItem(int position) {
        return list.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(layout, parent, false);
        }
        TextView title = (TextView)convertView.findViewById(R.id.listTitle);
        CheckBox check = (CheckBox)convertView.findViewById(R.id.checkBox);
        title.setTag(position);
        check.setTag(position);
        title.setText(getItem(position).getTitle());
        title.setTextColor(Color.parseColor(getItem(position).getColor()));
        check.setChecked(false);
        if(deleteItem[position]){
            check.setChecked(true);
        }
        return convertView;
    }
}
