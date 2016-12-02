package com.chanmin.calculate_exmaple;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Calculator extends AppCompatActivity {
    TextView sendingText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculator_layout);
        sendingText =(TextView)findViewById(R.id.textView);

    }
    //TextView sendingText;

    public void ClearClicked(View v)
    {
        sendingText.setText("0");
    }
    public void DeleteClicked(View v)
    {

    }

}
