package com.chanmin.calculate_exmaple;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


public class Calculator extends AppCompatActivity {
    TextView sendingText;
    //****************************************
    //--backOperator--
    List<String> backOperator;
    Stack<String> operationStack;
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
        operationStack = new Stack<>();
        alignedOperator = new ArrayList<>();
        //*********************************************
    }

    public void clearClicked(View v) {
        isNumberPadClick = false;
        backOperator.clear();
        operationStack.clear();
        alignedOperator.clear();
        String value;
        value = "";
        sendingText.setText(value);
    }

    public void deleteClicked(View v) {
    }

    public void equalClicked(View v) {
        if (isNumberPadClick) {
            String tempNumber = "";
            for (String operator : backOperator) {
                if (operator.equals("/") || operator.equals("*")) {
                    operationStack.push(operator);
                    alignedOperator.add(tempNumber);
                    tempNumber = "";
                } else if (operator.equals("+") || operator.equals("-")) {
                    //System.out.println("+ or - operator detected");
                    alignedOperator.add(tempNumber);
                    tempNumber = "";
                    String multiplyCheck;
                    while (!operationStack.empty()) {
                        multiplyCheck = operationStack.pop();
                        //System.out.println("multiplyCheck:"+multiplyCheck);
                        if (multiplyCheck.equals("*") || multiplyCheck.equals("/")) {
                            alignedOperator.add(multiplyCheck);
                        } else {
                            operationStack.push(multiplyCheck);
                            break;
                        }
                    }
                    operationStack.push(operator);
                } else {
                    tempNumber += operator;
                }
            }
            alignedOperator.add(tempNumber);
            //int count = 0;
            String stack;
            for (; ; ) {
                //System.out.println("연산자 스택에서 뺴내기. count : " + count);
                //count++;
                if (operationStack.empty()) {
                    break;
                }
                stack = operationStack.pop();
                alignedOperator.add(stack);
            }
            /*String value  = "";
            for(String result : alignedOperator){
                value += result;
            }
            sendingText.setText(value);*/
            String tempValue;
            String stringNumber1 = "";
            String stringNumber2 = "";
            int firstNumberPoint = 0;
            float tempResult = 0;
            int alignedOperatorSize = alignedOperator.size();
            for (int i = 0; i < alignedOperatorSize; i++) {
                System.out.println("i:" + i);
                tempValue = alignedOperator.get(i);
                if (tempValue.equals("*") || tempValue.equals("/") || tempValue.equals("-") || tempValue.equals("+")) {
                    System.out.println("stringNumber1: " + stringNumber1 + " stringNumber2: " + stringNumber2);
                    switch (tempValue) {
                        case "*":
                            tempResult = Float.parseFloat(stringNumber1) * Float.parseFloat(stringNumber2);
                            break;
                        case "/":
                            tempResult = Float.parseFloat(stringNumber1) / Float.parseFloat(stringNumber2);
                            break;
                        case "-":
                            tempResult = Float.parseFloat(stringNumber1) - Float.parseFloat(stringNumber2);
                            break;
                        case "+":
                            tempResult = Float.parseFloat(stringNumber1) + Float.parseFloat(stringNumber2);
                            break;
                    }
                    System.out.println(stringNumber1 + alignedOperator.get(i) + stringNumber2);
                    alignedOperator.remove(i);
                    alignedOperator.remove(stringNumber1);
                    stringNumber1 = "";
                    alignedOperator.remove(stringNumber2);
                    stringNumber2 = "";
                    alignedOperator.add(firstNumberPoint, "" + tempResult);
                    firstNumberPoint = 0;
                    System.out.println("=" + tempResult);
                    if (alignedOperatorSize > 2) {
                        alignedOperatorSize -= 2;
                        i = -1;
                    } else {
                        break;
                    }
                } else {
                    if (stringNumber1.equals("")) {
                        stringNumber1 = alignedOperator.get(i);
                        firstNumberPoint = i;
                    } else if (stringNumber2.equals("")) {
                        stringNumber2 = alignedOperator.get(i);
                    }
                }
            }
                String result = alignedOperator.get(0);
                int resultLength = result.length();
                if (resultLength > 2) {
                    if (result.substring(resultLength - 2, resultLength).equals(".0")) {
                        result = result.substring(0, resultLength - 2);
                    }
                }
                String value;
                value = result;
                sendingText.setText(value);
                isNumberPadClick = true;
                backOperator.clear();
                operationStack.clear();
                alignedOperator.clear();
                backOperator.add(value);
            }
        }

    public void pointClicked(View v){
        if(isNumberPadClick && !isPointClick){
            backOperator.add(".");
            isPointClick = true;
            isNumberPadClick = false;
            String value = sendingText.getText().toString();
            value += ".";
            sendingText.setText(value);
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
            isPointClick = false;
            isNumberPadClick = false;
            String value = sendingText.getText().toString();
            int backOperatorSize = backOperator.size()-1;
            value += backOperator.get(backOperatorSize);
            sendingText.setText(value);
        }
    }
}
