package com.chanmin.todoapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;


import java.util.ArrayList;

public class ToDoMainActivity extends AppCompatActivity {
    ArrayList<Item> listItem;
    Boolean []deleteItem;
    ListAdapter adapter;
    ListView list;
    final int ADDLIST = 0;
    final int CHANGELIST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_main);
        listItem = new ArrayList<>();
        SharedPreferences pref = getSharedPreferences("titleList", 0);
        int count = pref.getInt("listItemSize", 0);
        for (int i = 0; i < count; i++) {
            listItem.add(new Item(pref.getString("" + i, ""), false));
        }
        adapter = new ListAdapter(this, R.layout.activity_list_view_item, listItem);
        list = (ListView) findViewById(R.id.listView);
        list.setAdapter(adapter);
        if(listItem.size() < 1){
            deleteItem = new Boolean[1];
        }else {
            deleteItem = new Boolean[listItem.size()];
        }
        for (int i = 0; i < deleteItem.length; i++) {
            deleteItem[i] = false;
        }
    }

    public void addNewListButtonClicked(View v) {
        Intent intent = new Intent(ToDoMainActivity.this, ToDoSubActivity.class);
        intent.putExtra("titleText", "");
        startActivityForResult(intent, ADDLIST);
    }

    public void deleteButtonClicked(View v) {
        for (int i = adapter.getCount() - 1; i >= 0; i--) {
            if (deleteItem[i]) {
                listItem.remove(i);
            }
        }
        adapter.notifyDataSetChanged();
        list.clearChoices();
    }

    public void checkBoxClicked(View checkView) {
        boolean isCheck = ((CheckBox) checkView).isChecked();
        CheckBox check = (CheckBox) checkView;
        int position = Integer.parseInt(check.getTag().toString());
        if (isCheck) {
            check.setChecked(true);
            deleteItem[position] = true;
        } else {
            check.setChecked(false);
            deleteItem[position] = false;
        }
    }

    public void listTitleClicked(View clickedView) {
        int position = Integer.parseInt(clickedView.getTag().toString());
        String clickedTitleText = ((TextView) clickedView).getText().toString();
        Intent intent = new Intent(ToDoMainActivity.this, ToDoSubActivity.class);
        intent.putExtra("titleText", clickedTitleText);
        intent.putExtra("position", position);
        startActivityForResult(intent, CHANGELIST);
    }

    public void onPause() {
        super.onPause();
        SharedPreferences pref = getSharedPreferences("titleList", 0);
        SharedPreferences.Editor edit = pref.edit();
        for (int i = 0; i < listItem.size(); i++) {
            edit.putString("" + i, listItem.get(i).getTitle());
        }
        edit.putInt("listItemSize", listItem.size());
        edit.apply();
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case ADDLIST:
                if (resultCode == RESULT_OK) {
                    String titleText = data.getStringExtra("titleText");
                    if(data.getBooleanExtra("important",false)){
                        listItem.add(0, new Item(titleText, false));
                    }else{
                        listItem.add(new Item(titleText, false));
                    }
                    deleteItem = new Boolean[listItem.size()];
                    for(int i = 0; i<deleteItem.length; i++){
                        deleteItem[i] = false;
                    }
                }
                break;
            case CHANGELIST:
                if (resultCode == RESULT_OK) {
                    String titleText = data.getStringExtra("titleText");
                    int position = data.getIntExtra("position", 0);
                    listItem.remove(position);
                    if(data.getBooleanExtra("important",false)){
                        listItem.add(0, new Item(titleText, false));
                    }else {
                        listItem.add(position, new Item(titleText, false));
                    }
                }
                break;
        }
        adapter.notifyDataSetChanged();
        list.clearChoices();
    }
}

