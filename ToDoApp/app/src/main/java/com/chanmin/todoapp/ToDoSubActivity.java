package com.chanmin.todoapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class ToDoSubActivity extends AppCompatActivity {
    String titleText;
    String mainText;
    String importantColor;
    String defaultColor;
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
        important = intent.getBooleanExtra("important", false);
        title.setText(titleText);
        SharedPreferences pref = getPreferences(0);
        mainText = pref.getString(titleText, "");
        main.setText(mainText);
        importantColor = "#FF4500";
        defaultColor = "#000000";
        if (important) {
            title.setTextColor(Color.parseColor(importantColor));
            checkBox.setChecked(true);
        } else {
            title.setTextColor(Color.parseColor(defaultColor));
        }
    }

    public void cancelButtonClicked(View v) {
        setResult(RESULT_CANCELED);
        finish();
    }

    public void checking(View v) {
        important = !important;
        if (important) {
            title.setTextColor(Color.parseColor(importantColor));
        } else {
            title.setTextColor(Color.parseColor(defaultColor));
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
        intent.putExtra("important", important);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }
}