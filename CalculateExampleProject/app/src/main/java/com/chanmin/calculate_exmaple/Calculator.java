package com.chanmin.calculate_exmaple;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class Calculator extends AppCompatActivity {
    TextView sendingText;
    //****************************************
    //--backOperator--
    List<String> backOperator;
    List<String> operationStack;
    List<String> alignedOperator;
    boolean isNumberPadClick;
    //************************
    boolean isPointClick;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculator_layout);
        sendingText =(TextView)findViewById(R.id.textView);
        //********************************************
        backOperator = new ArrayList<>();
        isNumberPadClick = false;
        operationStack = new ArrayList<>();
        alignedOperator = new ArrayList<>();
        //*********************************************
    }

    public void clearClicked(View v) {
        isNumberPadClick = false;
        backOperator.clear();
        operationStack.clear();
        alignedOperator.clear();
        String value = sendingText.getText().toString();
        value = "";
        sendingText.setText(value);
    }

    public void deleteClicked(View v) {
    }

    public void equalClicked(View v) {
        String value = sendingText.getText().toString();
        if(isNumberPadClick){
            String tempNumber = "";
            for(int i =0; i<backOperator.size(); i++){
                if(backOperator.get(i).equals("/") || backOperator.get(i).equals("*")){
                    operationStack.add(backOperator.get(i));
                    alignedOperator.add(tempNumber);
                    tempNumber = "";
                } else if(backOperator.get(i).equals("+") || backOperator.get(i).equals("-")){
                    operationStack.add(backOperator.get(i));
                    alignedOperator.add(tempNumber);
                    tempNumber = "";
                    for(int j = operationStack.size()-1; j >= 0; j--){
                        if(operationStack.get(j).equals("*") || operationStack.get(j).equals("/")){
                            alignedOperator.add(operationStack.get(j));
                            operationStack.remove(j);
                        }
                    }
                } else {
                    tempNumber += backOperator.get(i);
                }
            }
            alignedOperator.add(tempNumber);
            for(int i = operationStack.size() - 1; i>=0; i--){
                alignedOperator.add(operationStack.get(i));
            }
            System.out.println("");
            value = "";
            for(int i = 0; i<alignedOperator.size(); i++){
                value += alignedOperator.get(i)+" ";
            }
            sendingText.setText(value);
        }
    }
    public void pointClicked(View v){
        if(isNumberPadClick && !isPointClick){
            backOperator.add(".");
            isPointClick = true;
            isNumberPadClick = false;
        }
    }
    public void numberPadClicked(View v) {
        switch(v.getId()){
            case R.id.zero:
                backOperator.add("0");
                break;
            case R.id.one:
                backOperator.add("1");
                break;
            case R.id.two:
                backOperator.add("2");
                break;
            case R.id.three:
                backOperator.add("3");
                break;
            case R.id.four:
                backOperator.add("4");
                break;
            case R.id.five:
                backOperator.add("5");
                break;
            case R.id.six:
                backOperator.add("6");
                break;
            case R.id.seven:
                backOperator.add("7");
                break;
            case R.id.eight:
                backOperator.add("8");
                break;
            case R.id.nine:
                backOperator.add("9");
                break;
        }
        isNumberPadClick = true;
        String value = sendingText.getText().toString();
        int backOperatorSize = backOperator.size()-1;
        value += backOperator.get(backOperatorSize);
        sendingText.setText(value);
    }
    public void arbitraryOperatorsClicked(View v) {
        if(isNumberPadClick) {
            switch (v.getId()) {
                case R.id.division:
                    backOperator.add("/");
                    break;
                case R.id.multiply:
                    backOperator.add("*");
                    break;
                case R.id.subtract:
                    backOperator.add("-");
                    break;
                case R.id.addition:
                    backOperator.add("+");
                    break;
            }
            isPointClick = true;
            isNumberPadClick = false;
            String value = sendingText.getText().toString();
            int backOperatorSize = backOperator.size()-1;
            value += backOperator.get(backOperatorSize);
            sendingText.setText(value);
        }
    }
}
