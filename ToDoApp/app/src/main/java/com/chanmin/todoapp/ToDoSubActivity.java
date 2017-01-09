package com.chanmin.todoapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.prefs.Preferences;


public class ToDoSubActivity extends AppCompatActivity {
    String titleText;
    String mainText;
    EditText title;
    EditText main;
    Button cancel;
    Button apply;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_sub);
        title = (EditText) findViewById(R.id.titleText);
        main = (EditText) findViewById(R.id.mainText);
        cancel = (Button) findViewById(R.id.cancel);
        apply = (Button) findViewById(R.id.apply);
        Intent intent = getIntent();
        titleText = intent.getStringExtra("titleText");
        position = intent.getIntExtra("position", 0);
        title.setText(titleText);
        SharedPreferences pref = getPreferences(0);
        mainText = pref.getString(titleText, "");
        main.setText(mainText);
    }

    public void cancelButtonClicked(View v) {
        setResult(RESULT_CANCELED);
        finish();
    }

    public void checking(View v) {
    }

    public void applyButtonClicked(View v) {
        String titleT = title.getText().toString();
        String mainT = main.getText().toString();
        SharedPreferences pref = getPreferences(0);
        SharedPreferences.Editor edit = pref.edit();
        edit.putString(titleT, mainT);
        edit.commit();
        Intent intent = new Intent();
        intent.putExtra("titleText", titleT);
        intent.putExtra("position", position);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }
}