package com.chanmin.todoapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class ToDoSubActivity extends AppCompatActivity {
    String titleText;
    String mainText;
    EditText title;
    EditText main;
    Button cancel;
    Button apply;
    int position;
    Boolean important;
    CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_sub);
        title = (EditText) findViewById(R.id.titleText);
        main = (EditText) findViewById(R.id.mainText);
        cancel = (Button) findViewById(R.id.cancel);
        apply = (Button) findViewById(R.id.apply);
        checkBox = (CheckBox) findViewById(R.id.importantCheckbox);
        Intent intent = getIntent();
        titleText = intent.getStringExtra("titleText");
        position = intent.getIntExtra("position", 0);
        title.setText(titleText);
        SharedPreferences pref = getPreferences(0);
        mainText = pref.getString(titleText, "");
        important = pref.getBoolean("important", false);
        main.setText(mainText);
        if(important){
            checkBox.setChecked(true);
        }
    }

    public void cancelButtonClicked(View v) {
        setResult(RESULT_CANCELED);
        finish();
    }

    public void checking(View v) {
        if(important){
            important = false;
        }else{
            important = true;
        }
        String importantCheck = title.getText().toString();
        int importantCheckLength = importantCheck.length();
        if(!importantCheck.equals("")) {
            String symbol = importantCheck.substring(importantCheckLength - 1, importantCheckLength);
            if(!symbol.equals("*")){
                importantCheck += "*";
                title.setText(importantCheck);
            } else {
                importantCheck = importantCheck.substring(0 , importantCheckLength-1);
                title.setText(importantCheck);
            }
        }
    }

    public void applyButtonClicked(View v) {
        String titleT = title.getText().toString();
        String mainT = main.getText().toString();
        SharedPreferences pref = getPreferences(0);
        SharedPreferences.Editor edit = pref.edit();
        edit.putString(titleT, mainT);
        edit.putBoolean("important", important);
        edit.commit();
        Intent intent = new Intent();
        intent.putExtra("titleText", titleT);
        intent.putExtra("position", position);
        intent.putExtra("important",important);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }
}