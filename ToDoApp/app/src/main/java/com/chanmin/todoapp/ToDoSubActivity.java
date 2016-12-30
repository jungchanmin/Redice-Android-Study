package com.chanmin.todoapp;

import android.app.Activity;
import android.content.Intent;
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

public class ToDoSubActivity extends AppCompatActivity {
Button apply;
Button cancel;
EditText title;
EditText main;
TextView fixedTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_sub);
        fixedTitle = (TextView)findViewById(R.id.fixedTitle);
        String value = "Add List";
        fixedTitle.setText(value);
        apply = (Button)findViewById(R.id.apply);
        cancel = (Button)findViewById(R.id.cancel);
        title = (EditText)findViewById(R.id.titleText);
        main = (EditText)findViewById(R.id.mainText);
    }
    public void cancelButtonClicked(View v){
        setResult(RESULT_CANCELED);
        finish();
    }
    public void applyButtonClicked(View v){
        String value = title.getText().toString();
        Intent intent = new Intent();
        intent.putExtra("titleList",value);
        setResult(Activity.RESULT_OK,intent);
        finish();
    }
}
