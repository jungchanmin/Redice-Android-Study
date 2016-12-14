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
    boolean breaker;
    boolean isPointClick;
    int oneLimit;
    boolean [] usedValueArray;
    boolean isCombination;
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
        breaker = false;
        isPointClick = false;
        oneLimit = 1;
        usedValueArray = new boolean[100];
    }

    public void clearClicked(View v) {
        for(int i =0; i<100; i++){
            number[i]="0";
            operation[i] = "";
            usedValueArray[i] = false;
        }
        tempNumber = "0";
        tempOperation = "";
        sendingText.setText("");
        countInt = 0;
        countString = 0;
        intBoll = false;
        stringBoll = false;
        breaker = false;
        isPointClick = false;
    }

    public void deleteClicked(View v) {
        String value = sendingText.getText().toString();
        int valueLength = value.length();
        value = value.substring(0 , valueLength - 1);
        valueLength = value.length();
        String character = value.substring(valueLength - 1 , valueLength);
        sendingText.setText(value);
        //System.out.println("tempNumber: "+tempNumber);
       if (intBoll) {
            if (Float.parseFloat(tempNumber) > 10) {
                int tempNumberLength = tempNumber.length();
                //System.out.println("수정하기 전의 tempNumber의 값: "+tempNumber);
                tempNumber = tempNumber.substring(0, tempNumberLength - 1);
                //System.out.println("수정한 후의 tempNumber의 값: "+tempNumber);
            } else {
                if(!character.equals(".") && oneLimit == 1){
                    tempNumber = "0";
                    number[countInt] = "0";
                    //System.out.println("tempNumber의 값이 10보다 작고, character의 값이 .이 아님");
                }else{
                    int tempLength = tempNumber.length();
                    tempNumber = tempNumber.substring(0 , tempLength-1);
                    oneLimit = oneLimit * -1;
                }
                valueLength = value.length();
                if(valueLength > 1 && !character.equals(".")){
                    String tempValue = value.substring(valueLength - 1, valueLength);
                        switch (tempValue) {
                            case "/":
                                countString--;
                                tempOperation = "/";
                                stringBoll = true;
                                intBoll = false;
                                break;
                            case "*":
                                countString--;
                                tempOperation = "*";
                                stringBoll = true;
                                intBoll = false;
                                break;
                            case "-":
                                countString--;
                                tempOperation = "-";
                                stringBoll = true;
                                intBoll = false;
                                break;
                            case "+":
                                countString--;
                                tempOperation = "+";
                                stringBoll = true;
                                intBoll = false;
                                break;
                            case "=":
                                sendingText.setText(value.substring(0, valueLength - 1));
                                break;
                        }
                    }
                }
            }
            if (stringBoll) {
                operation[countString] = "";
                tempOperation = "";
                countString--;
                stringBoll = false;
                intBoll = true;
            }
        }

    public void equalClicked(View v) {
        if (intBoll) {
            String value = sendingText.getText().toString();
            float result = 0;
            int count = 0;
            int calculationOrder[] = new int[100];
            for (int i = 0; i < 100; i++) {
                calculationOrder[i] = 0;
            }
            int operationCount = 0;
            for (int i = 0; i < countString; i++) {
                System.out.println("operation[i]:"+operation[i]);
                if (operation[i].equals("*") || operation[i].equals("/")) {
                    calculationOrder[operationCount] = i;
                    operationCount++;
                }
            }
            for (int i = 0; i < countString; i++) {
                if (operation[i].equals("-") || operation[i].equals("+")) {
                    calculationOrder[operationCount] = i;
                    operationCount++;
                }
            }
            //System.out.println("현재 operationCount의 값: operationCount: "+operationCount);
            if (intBoll) {
                number[countInt] = tempNumber;
                tempNumber = "0";
                countInt++;
                intBoll = false;
                isPointClick = false;
            }
            float tempStorageVar = 0;
            float tempVar1;
            float tempVar2;
            for (int i = 0; i < operationCount; i++) {
                int priorityOperation = calculationOrder[i];
                System.out.println("priotityOperation: "+priorityOperation);
                System.out.println("operation[priorityOperation]: "+operation[priorityOperation]);
                switch (operation[priorityOperation]) {
                    case "/":
                        tempStorageVar = Float.parseFloat(number[priorityOperation]) / Float.parseFloat(number[priorityOperation+1]);
                        break;
                    case "*":
                        //System.out.println("값을 집어넣기 전의 tempStorageVar값 : "+tempStorageVar);
                        //System.out.println("Float.parseFloat(number[priorityOperation]: "+Float.parseFloat(number[priorityOperation]));
                        //System.out.println("Float.parseFloat(number[priorityOperation+1]: "+Float.parseFloat(number[priorityOperation+1]));
                        //System.out.println("tempVar1: "+tempVar1+" tempVar2: "+tempVar2);
                        tempStorageVar = Float.parseFloat(number[priorityOperation]) * Float.parseFloat(number[priorityOperation+1]);
                        //System.out.println("값을 집어넣은 후의 tempStorageVar값 : "+tempStorageVar);
                        break;
                    case "-":
                        tempVar1 = Float.parseFloat(number[priorityOperation]);
                        tempVar2 = Float.parseFloat(number[priorityOperation+1]);
                        tempStorageVar = tempVar1 - tempVar2;
                        break;
                    case "+":
                        tempVar1 = Float.parseFloat(number[priorityOperation]);
                        tempVar2 = Float.parseFloat(number[priorityOperation+1]);
                        tempStorageVar = tempVar1 + tempVar2;
                        break;
                }
                System.out.println("tempStorageVar: "+tempStorageVar);
                if(usedValueArray[priorityOperation] || usedValueArray[priorityOperation]){
                    usedValueArray[priorityOperation] = true;
                    usedValueArray[priorityOperation + 1] = true;
                    for(int j = 0; j < countInt; j++){
                        if(usedValueArray[j]){
                            number[j] = "" + tempStorageVar;
                        }
                    }
                } else {
                    usedValueArray[priorityOperation] = true;
                    usedValueArray[priorityOperation + 1] = true;
                    number[priorityOperation] = "" + tempStorageVar;
                    number[priorityOperation + 1] = "" + tempStorageVar;
                }
            }
            result = Float.parseFloat(number[0]);
            int resultInt = (int) result;
            float mockery = result - (float) resultInt;
            if (mockery == 0) {
                value = "" + resultInt;
                //System.out.println("result버튼 입력확인. int형 값이 result변수에 삽입됨. result: " + resultInt);
            } else {
                //System.out.println("result버튼 입력확인. float형 값이 result변수에 삽입됨. result: " + result);
                value = "" + result;
            }
            sendingText.setText(value);
            for (int i = 0; i < 100; i++) {
                number[i] = "0";
                operation[i] = "";
                usedValueArray[i] = false;
            }
            if (mockery == 0) {
                number[0] = "" + resultInt;
            } else {
                number[0] = "" + result;
                isPointClick = true;
            }
            System.out.println("현재 배열의 첫번째 값으로 쓰이는 number[0]:" + number[0]);
            tempNumber = "" + result;
            tempOperation = "";
            countInt = 0;
            countString = 0;
            intBoll = true;
            stringBoll = false;
            breaker = false;
            isCombination = false;
        }
    }

    public void pointClicked(View v){
        String value = sendingText.getText().toString();
        if(intBoll) {
            if(!isPointClick) {
                value += ".";
                int tempNumberLength = tempNumber.length();
                if(tempNumberLength > 2) {
                    String floatOrInt = tempNumber.substring(tempNumberLength - 2, tempNumberLength);
                    if(floatOrInt.equals(".0")){
                        tempNumber = tempNumber.substring(0 , tempNumberLength - 1);
                    }else{
                        tempNumber += ".";
                    }
                } else {
                    tempNumber += ".";
                }
                sendingText.setText(value);
                isPointClick = true;
            }
        }
    }
    public void numberPadClicked(View v) {
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
            //System.out.println("tempOperation의 값:"+tempOperation);
            //System.out.println("operation[countString]의 값:"+operation[countString]+"countString의 값:"+countString);
            tempOperation = "";
            countString++;
            stringBoll = false;
        }
        intBoll = true;
        sendingText.setText(value);
    }
    public void arbitraryOperatorsClicked(View v) {
        String value = sendingText.getText().toString();
        String idCode = "";
        //System.out.println("breaker:"+breaker);
        if (!intBoll && !stringBoll || tempOperation.equals("") || !breaker) {
            switch (v.getId()) {
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
            if (stringBoll) {
                int valueLength = value.length();
                value = value.substring(0, valueLength - 1);
                value += "" + idCode;
            } else {
                value += "" + idCode;
            }
            if (intBoll) {
                number[countInt] = tempNumber;
                //System.out.println("tempNumber의 값:"+tempNumber);
                //System.out.println("number[countInt]의 값:"+number[countInt]+"countInt의 값:"+countInt);
                tempNumber = "";
                countInt++;
                intBoll = false;
                //breaker = true;
                isPointClick = false;
            }
            stringBoll = true;
            sendingText.setText(value);
        }
    }
}