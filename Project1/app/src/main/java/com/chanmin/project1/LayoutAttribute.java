package com.chanmin.project1;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import static com.chanmin.project1.R.styleable.View;

public class LayoutAttribute extends AppCompatActivity {
    /*TextView sendingText = (TextView) findViewById(R.id.textView);
    Button clear = (Button) findViewById(R.id.Clear);
    Button delete = (Button) findViewById(R.id.Delete);
    Button seven = (Button) findViewById(R.id.Seven);
    Button eight = (Button) findViewById(R.id.Eight);
    Button nine = (Button) findViewById(R.id.Nine);
    Button division = (Button) findViewById(R.id.Division);
    Button six = (Button) findViewById(R.id.Six);
    Button five = (Button) findViewById(R.id.Five);
    Button four = (Button) findViewById(R.id.Four);
    Button multiply = (Button) findViewById(R.id.Multiply);
    Button three = (Button) findViewById(R.id.Three);
    Button two = (Button) findViewById(R.id.Two);
    Button one = (Button) findViewById(R.id.One);
    Button subtract = (Button) findViewById(R.id.Subtract);
    Button point = (Button) findViewById(R.id.Point);
    Button zero = (Button) findViewById(R.id.Zero);
    Button equal = (Button) findViewById(R.id.Equal);
    Button addition = (Button) findViewById(R.id.Addition);*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_attribute);


        //sendingText.setText("100");

        //Calculate th = new Calculate();
        //th.setDaemon(true);
        //th.start();




    }
   /* public void ClearClicked(android.view.View v)
    {
        sendingText.setText("");

    }*/


    /*class Calculate extends Thread
    {
        public void run()
        {
            while(true)
            {
                try{Thread.sleep(1000);}catch(Exception ex){;}


            }

        }
        Handler hand = new Handler()
        {
            public void handleMessage(Message msg)
            {

                if(msg.what == 0)
                {


                }
            }
        };
    }*/
}
