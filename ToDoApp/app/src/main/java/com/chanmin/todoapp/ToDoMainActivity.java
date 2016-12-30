package com.chanmin.todoapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ToDoMainActivity extends AppCompatActivity {
ArrayList<String> titleList;
ArrayAdapter<String> titleAdapter;
ListView list;
Button addNewListButton;
int count;
TextView fixedTitle;
boolean isAddListCalledByUser;
final static int MAIN = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_main);
        fixedTitle = (TextView)(findViewById(R.id.fixedTitle));
        String value = "To Do List";
        fixedTitle.setText(value);
        list = (ListView)findViewById(R.id.listView);
        addNewListButton = (Button)findViewById(R.id.addNewList);
        count = 0;
        titleList = new ArrayList<>();
        titleAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice, titleList);
        list.setAdapter(titleAdapter);

    }
    public void addNewListButtonClicked(View v){
        isAddListCalledByUser = true;
        Intent intent = new Intent(ToDoMainActivity.this, ToDoSubActivity.class);
        startActivityForResult(intent,MAIN);
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        switch(requestCode){
            case MAIN :
                if(resultCode == Activity.RESULT_OK){
                    titleList.add(data.getStringExtra("titleList"));
                    titleAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice, titleList);
                    list.setAdapter(titleAdapter);
                }
        }
    }

}
