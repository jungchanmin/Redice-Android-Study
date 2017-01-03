package com.chanmin.todoapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.prefs.Preferences;

import static com.chanmin.todoapp.ToDoMainActivity.MAINKEY;

public class ToDoSubActivity extends AppCompatActivity {
    Button apply;
    Button cancel;
    EditText title;
    EditText main;
    TextView fixedTitle;
    String value;
    Boolean isReviseOrView;
    String originalTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_sub);
        fixedTitle = (TextView) findViewById(R.id.fixedTitle);
        apply = (Button) findViewById(R.id.apply);
        cancel = (Button) findViewById(R.id.cancel);
        title = (EditText) findViewById(R.id.titleText);
        main = (EditText) findViewById(R.id.mainText);
        Intent intent = getIntent();
        isReviseOrView = intent.getBooleanExtra("isReviseOrView", false);
        originalTitle = intent.getStringExtra("clickedTitle");
        System.out.println("originalTitle:" + originalTitle);
        if (isReviseOrView) {
            value = "View List";
            title.setText(intent.getStringExtra("clickedTitle"));
            apply.setText("complete");
            SharedPreferences mainText = getPreferences(0);
            main.setText(mainText.getString(intent.getStringExtra("clickedTitle"), "error"));
        } else {
            value = "Add List";
        }
        fixedTitle.setText(value);
    }

    public void cancelButtonClicked(View v) {
        setResult(RESULT_CANCELED);
        finish();
    }

    public void applyButtonClicked(View v) {
        String value = title.getText().toString();
        String mainTextValue = main.getText().toString();
        SharedPreferences mainText = getPreferences(0);
        SharedPreferences.Editor edit = mainText.edit();
        edit.putString(value, mainTextValue);
        edit.commit();
        Intent intent = new Intent();
        intent.putExtra("" + MAINKEY, value);
        intent.putExtra("originalTitle", originalTitle);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }
}