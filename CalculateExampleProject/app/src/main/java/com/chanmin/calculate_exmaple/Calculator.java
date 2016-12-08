package com.chanmin.calculate_exmaple;

import android.os.Debug;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static android.R.attr.button;
import static android.R.attr.id;
import static android.R.id.button2;

public class Calculator extends AppCompatActivity {
    TextView sendingText;
    String [] number;
    String tempNumber;
    String [] operation;
    String tempOperation;
    int countInt;
    int countString;
    boolean stringBoll;
    boolean intBoll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculator_layout);
        sendingText =(TextView)findViewById(R.id.textView);
        tempNumber = "";
        tempOperation = "";
        number = new String[100];
        operation = new String[100];
        stringBoll = false;
        intBoll = false;
        countInt = 0;
        countString = 0;
    }

    public void clearClicked(View v)
    {
        for(int i =0; i<100; i++){
            number[i]="0";
            operation[i] = "";
        }
        tempNumber = "0";
        tempOperation = "";
        sendingText.setText("");
        countInt = 0;
        countString = 0;
        intBoll = false;
        stringBoll = false;
    }

    public void deleteClicked(View v) {}

    public void equalClicked(View v){
        String value = sendingText.getText().toString();
        float result = 0;
        int count=0;
        if(intBoll) {
            number[countInt] = tempNumber;
            tempNumber = "0";
            countInt++;
            intBoll = false;
        }
        for(int i=0; i<100; i+=2) {

            if (number[i + 1] != null) {
                System.out.println("operation[count]:" + operation[count] + " count:" + count);
                switch (operation[count]) {
                    case "/":
                        result += Float.parseFloat(number[i]) / Float.parseFloat(number[i + 1]);
                        break;
                    case "*":
                        result += Float.parseFloat(number[i]) * Float.parseFloat(number[i + 1]);
                        break;
                    case "-":
                        result += Float.parseFloat(number[i]) - Float.parseFloat(number[i + 1]);
                        break;
                    case "+":
                        System.out.println("number[i]:" + number[i] + " number[i+1]:" + number[i + 1]);
                        result += Float.parseFloat(number[i]) + Float.parseFloat(number[i + 1]);
                        break;
                }
                count++;
            } else if (number[i] != null) {
                switch (operation[count]) {
                    case "/":
                        result /= Float.parseFloat(number[i]);
                        break;
                    case "*":
                        result *= Float.parseFloat(number[i]);
                        break;
                    case "-":
                        result -= Float.parseFloat(number[i]);
                        break;
                    case "+":
                        result += Float.parseFloat(number[i]);
                        break;
                }
            } else {
                break;
            }
        }
        value += "="+result;
        sendingText.setText(value);
    }

    public void pointClicked(View v){}
    public void numberPadClicked(View v)
    {
        String value = sendingText.getText().toString();
        int idCode = 0;
        switch(v.getId()){
            case R.id.zero:
                idCode = 0;
                break;
            case R.id.one:
                idCode = 1;
                break;
            case R.id.two:
                idCode = 2;
                break;
            case R.id.three:
                idCode = 3;
                break;
            case R.id.four:
                idCode = 4;
                break;
            case R.id.five:
                idCode = 5;
                break;
            case R.id.six:
                idCode = 6;
                break;
            case R.id.seven:
                idCode = 7;
                break;
            case R.id.eight:
                idCode = 8;
                break;
            case R.id.nine:
                idCode = 9;
                break;
        }
        value += ""+idCode;
        tempNumber += ""+idCode;
        if(stringBoll){
            operation[countString] = tempOperation;
            System.out.println("tempOperation의 값:"+tempOperation);
            System.out.println("operation[countString]의 값:"+operation[countString]+"countString의 값:"+countString);
            tempOperation = "";
            countString++;
            stringBoll = false;
        }
        intBoll = true;
        sendingText.setText(value);
    }
    public void arbitraryOperatorsClicked(View v)
    {
        String value = sendingText.getText().toString();
        String idCode="";
        switch(v.getId())
        {
            case R.id.division:
                idCode = "/";
                break;
            case R.id.multiply:
                idCode = "*";
                break;
            case R.id.subtract:
                idCode = "-";
                break;
            case R.id.addition:
                idCode = "+";
                break;
            default:
                break;
        }
        tempOperation = idCode;
        if(stringBoll){
            int valueLength = value.length();
            value = value.substring(0,valueLength-1);
            value += ""+idCode;
        }else{
            value += ""+idCode;
        }
        if(intBoll) {
            number[countInt] = tempNumber;
            System.out.println("tempNumber의 값:"+tempNumber);
            System.out.println("number[countInt]의 값:"+number[countInt]+"countInt의 값:"+countInt);
            tempNumber = "";
            countInt++;
            intBoll = false;
        }
        stringBoll =  true;
        sendingText.setText(value);
    }

}