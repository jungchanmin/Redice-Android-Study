package com.chanmin.todoapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ToDoMainActivity extends AppCompatActivity {
    ArrayList<String> titleList;
    ArrayList<Boolean> clickList;
    ArrayAdapter<String> titleAdapter;
    ListView list;
    Button addNewListButton;
    Button deleteButton;
    boolean isAddListCalledByUser;
    final int MAIN = 0;
    final int MAIN2 = 1;
    final static String MAINKEY = "titleList";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_main);
        addNewListButton = (Button) findViewById(R.id.addNewList);
        deleteButton = (Button) findViewById(R.id.delete);
        list = (ListView) findViewById(R.id.listView);
        clickList = new ArrayList<>();
        titleList = new ArrayList<>();
        SharedPreferences savedList = getSharedPreferences("toDoListTitle", 0);
        int count = savedList.getInt("count", 0);
        for (int i = 0; i < count; i++) {
            titleList.add(savedList.getString("" + i, "error"));
        }
        titleAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice, titleList);
        list.setAdapter(titleAdapter);
        list.setOnItemClickListener(listViewClickListener);
        list.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        list.clearChoices();
        for (int i = 0; i < titleList.size(); i++) {
            clickList.add(false);
        }
    }

    public void addNewListButtonClicked(View v) {
        isAddListCalledByUser = true;
        Intent intent = new Intent(ToDoMainActivity.this, ToDoSubActivity.class);
        intent.putExtra("isReviseOrView", false);
        startActivityForResult(intent, MAIN);
    }

    public void deleteButtonClicked(View v) {
        System.out.println("list.getCheckedItemPosition() : " + list.getCheckedItemPosition());
        SparseBooleanArray isCheck = list.getCheckedItemPositions();
        int count = titleAdapter.getCount();
        for (int i = count - 1; i >= 0; i--) {
            if (isCheck.get(i)) {
                titleList.remove(i);
            }
        }
        list.clearChoices();
        titleAdapter.notifyDataSetChanged();
    }

    AdapterView.OnItemClickListener listViewClickListener = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView<?> parentView, View clickedView, int position, long id) {
            String clickedTitle = ((TextView) clickedView).getText().toString();
            Intent intent = new Intent(ToDoMainActivity.this, ToDoSubActivity.class);
            intent.putExtra("clickedTitle", clickedTitle);
            intent.putExtra("isReviseOrView", true);
            startActivityForResult(intent, MAIN2);
        }
    };

    public void onPause() {
        super.onPause();
        int count = 0;
        while (true) {
            int listCheck = list.getCheckedItemPosition();
            if (listCheck != ListView.INVALID_POSITION) {
                titleList.remove(listCheck);
                continue;
            }
            break;
        }
        SharedPreferences toDoListTitle = getSharedPreferences("toDoListTitle", 0);
        SharedPreferences.Editor edit = toDoListTitle.edit();
        for (String list : titleList) {
            edit.putString("" + count, list);
            count++;
        }
        edit.putInt("count", count);
        edit.apply();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case MAIN:
                if (resultCode == Activity.RESULT_OK) {
                    if (data.getBooleanExtra("isImportant", false)) {
                        titleList.add(0, data.getStringExtra(MAINKEY));
                        list.clearChoices();
                    } else {
                        titleList.add(data.getStringExtra(MAINKEY));
                    }
                    titleAdapter.notifyDataSetChanged();
                } else {
                    titleList.clear();
                }
                break;
            case MAIN2:
                if (resultCode == Activity.RESULT_OK) {
                    int position = titleList.indexOf(data.getStringExtra("originalTitle"));
                    titleList.remove(data.getStringExtra("originalTitle"));
                    if (data.getBooleanExtra("isImportant", false)) {
                        titleList.add(0, data.getStringExtra(MAINKEY));
                        list.clearChoices();
                    } else {
                        titleList.add(position, data.getStringExtra(MAINKEY));
                    }
                    titleAdapter.notifyDataSetChanged();
                }
                break;
        }
    }

}
