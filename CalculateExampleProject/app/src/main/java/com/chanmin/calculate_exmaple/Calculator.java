package com.chanmin.calculate_exmaple;

import android.os.Debug;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static android.R.attr.button;
import static android.R.id.button2;

public class Calculator extends AppCompatActivity {
    TextView sendingText;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button button5;
    Button button6;
    Button button7;
    Button button8;
    Button button9;
    float result;
    float tempValue;
    char operationSymbol;
    Boolean isOperationSymbolExist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculator_layout);
        sendingText =(TextView)findViewById(R.id.textView);
        button1 = (Button)findViewById(R.id.one);
        button2 = (Button)findViewById(R.id.two);
        button3 = (Button)findViewById(R.id.three);
        button4 = (Button)findViewById(R.id.four);
        button5 = (Button)findViewById(R.id.five);
        button6 = (Button)findViewById(R.id.six);
        button7 = (Button)findViewById(R.id.seven);
        button8 = (Button)findViewById(R.id.eight);
        button9 = (Button)findViewById(R.id.nine);
        isOperationSymbolExist = false;
        operationSymbol ='N';
        result = 0;
        tempValue = 0;
    }

    public void clearClicked(View v)
    {
        result = 0;
        sendingText.setText("0");
    }

    public void deleteClicked(View v) {
        String value = sendingText.getText().toString();
        float valueFloat = Float.parseFloat(value);
        int strimLength;
        if(isOperationSymbolExist) {
            strimLength = 3;
            isOperationSymbolExist = false;
            operationSymbol = 'N';
        }else {
            strimLength = 1;
        }
        if (valueFloat != 0.0f) {
            int valueLength = value.length();//가져온 값의 길이계산
            value = value.substring(0, valueLength - strimLength);
            sendingText.setText(value);
        } else {
            sendingText.setText("0");
        }
    }//DeleteClicked의 종료

    public void numberPadClicked(View v)
    {
        String value = sendingText.getText().toString();//현재 스크린 상에 표시되고 있는 값 가져옴(스트링값)
        float valueFloat = Float.parseFloat(value);//실수형으로 형변환
        int valueInt = Integer.parseInt(value);
        float mockery = valueFloat - (float)valueInt;
        String buttonText = "";
        switch(v.getId()) {
            case R.id.zero:
                if (valueFloat == 0.0f || mockery > 0.0f) ;
                    //아무것도 실행하지 않음- 값이 0일때, 소수점이하값이 없을때
                else
                    value += "0";
                break;
            case R.id.one:
                buttonText = button1.getText().toString();
                break;
            case R.id.two:
                buttonText = button2.getText().toString();
                break;
            case R.id.three:
                buttonText = button3.getText().toString();
                break;
            case R.id.four:
                buttonText = button4.getText().toString();
                break;
            case R.id.five:
                buttonText = button5.getText().toString();
                break;
            case R.id.six:
                buttonText = button6.getText().toString();
                break;
            case R.id.seven:
                buttonText = button7.getText().toString();
                break;
            case R.id.eight:
                buttonText = button8.getText().toString();
                break;
            case R.id.nine:
                buttonText = button9.getText().toString();
                break;
        }
        if(isOperationSymbolExist) {
            switch(operationSymbol){
                case '/':
                    result = tempValue / valueFloat;
                    break;
                case '*':
                    result = tempValue * valueFloat;
                    break;
                case '+':
                    result = tempValue + valueFloat;
                    break;
                case '-':
                    result = tempValue - valueFloat;
                    break;
            }
            isOperationSymbolExist = false;
            operationSymbol ='N';
            tempValue = result;
        }
        if(valueFloat == 0.0f)
            value = buttonText;
        else
            value +=buttonText;
      sendingText.setText(value);
    }

    public void divisionClicked(View v){
        String value = sendingText.toString();
        float valueFloat = Float.parseFloat(value);
        if(isOperationSymbolExist){
            int valueLength = value.length();
            value = value.substring(0,valueLength-3);
            value += " / ";
        }else{
            isOperationSymbolExist = true;
            value += " / ";
        }
        tempValue = valueFloat;
        operationSymbol = '/';
        sendingText.setText(value);
    }
    public void multiplyClicked(View v){}
    public void subtractClicked(View v){}
    public void additionClicked(View v){}
    public void equalClicked(View v){}
    public void pointClicked(View v){}
}
