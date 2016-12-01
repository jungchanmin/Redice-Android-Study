package com.chanmin.project2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Activity1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_attribute);


    }
    int x;


    public void ClearClicked(View v)
    {
        TextView sendingText = (TextView)findViewById(R.id.textView);
        sendingText.setText("0");
    }
    public void DeleteClicked(View v)
    {
        TextView sendingText = (TextView)findViewById(R.id.textView);
    }

}
