package com.chanmin.todoapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;


import com.google.gson.Gson;

import java.util.ArrayList;

public class ToDoMainActivity extends AppCompatActivity {
    ArrayList<Item> listItem;
    Boolean[] deleteItem;
    ListAdapter adapter;
    ListView list;
    String colorDefault;
    String importantColor;
    final int ADDLIST = 0;
    final int CHANGELIST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_main);
        colorDefault = "#000000";
        importantColor = "#FF4500";
        listItem = new ArrayList<>();
        SharedPreferences pref = getSharedPreferences("titleList", 0);
        Gson gson = new Gson();
        String json = pref.getString("SaveData", "");
        SaveData saveData = gson.fromJson(json, SaveData.class);
        int count = pref.getInt("listItemSize", 0);
        if (count < 1) {
            deleteItem = new Boolean[0];
        }else {
            deleteItem = saveData.deleteItem;
            listItem = saveData.listItem;
        }
        adapter = new ListAdapter(this, R.layout.activity_list_view_item, listItem, deleteItem);
        list = (ListView) findViewById(R.id.listView);
        list.setAdapter(adapter);
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
        deleteItem = new Boolean[listItem.size()];
        for (int i = 0; i < deleteItem.length; i++) {
            deleteItem[i] = false;
        }
        adapter = new ListAdapter(this, R.layout.activity_list_view_item, listItem, deleteItem);
        list.setAdapter(adapter);
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
        Boolean important = (listItem.get(position).getColor()).equals(importantColor);
        Intent intent = new Intent(ToDoMainActivity.this, ToDoSubActivity.class);
        intent.putExtra("titleText", clickedTitleText);
        intent.putExtra("position", position);
        intent.putExtra("important", important);
        startActivityForResult(intent, CHANGELIST);
    }

    public void onPause() {
        super.onPause();
        SharedPreferences pref = getSharedPreferences("titleList", 0);
        SharedPreferences.Editor edit = pref.edit();
        edit.putInt("listItemSize", listItem.size());
        SaveData saveData = new SaveData(deleteItem, listItem);
        Gson gson = new Gson();
        String json = gson.toJson(saveData);
        edit.putString("SaveData", json);
        edit.apply();
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        String titleText;
        Boolean isImportant;
        Boolean isChangeItemClick = false;
        int position = 0;
        if(resultCode != RESULT_CANCELED){
            position = data.getIntExtra("position", 0);
        }
        ArrayList<Boolean> delete = new ArrayList<Boolean>();
        for(Boolean deletingItem : deleteItem){
            delete.add(deletingItem);
        }
        if(deleteItem.length > 1){
            isChangeItemClick = deleteItem[position];
        }
        if (resultCode == RESULT_OK) {
            titleText = data.getStringExtra("titleText");
            isImportant = data.getBooleanExtra("important", false);
            switch (requestCode) {
                case ADDLIST:
                    if (isImportant) {
                        listItem.add(0, new Item(titleText, importantColor));
                    } else {
                        listItem.add(new Item(titleText, colorDefault));
                    }
                    deleteItem = new Boolean[listItem.size()];
                    for (int i = 0; i < deleteItem.length; i++) {
                        deleteItem[i] = false;
                    }
                    for(int i = 0; i< delete.size(); i++){
                        deleteItem[i] = delete.get(i);
                    }
                    break;
                case CHANGELIST:
                    deleteItem = new Boolean[listItem.size()];
                    for(int i = 0; i < deleteItem.length; i++){
                        deleteItem[i] = false;
                    }
                    listItem.remove(position);
                    if (isImportant) {
                        listItem.add(0, new Item(titleText, importantColor));
                        delete.remove(position);
                        delete.add(0, isChangeItemClick);
                        for(int i = 0; i < delete.size(); i++){
                            deleteItem[i] = delete.get(i);
                        }
                    } else {
                        if(data.getBooleanExtra("importantSetting", false)){
                            listItem.add(new Item(titleText, colorDefault));
                            delete.remove(position);
                            delete.add(isChangeItemClick);
                        }else {
                            listItem.add(position, new Item(titleText, colorDefault));
                        }
                        for(int i = 0; i < delete.size(); i++){
                            deleteItem[i] = delete.get(i);
                        }
                    }
                    break;
            }
            adapter = new ListAdapter(this, R.layout.activity_list_view_item, listItem, deleteItem);
            list.setAdapter(adapter);
        }
    }
}

