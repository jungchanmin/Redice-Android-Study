package com.chanmin.calculate_exmaple;

import android.os.Debug;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Calculator extends AppCompatActivity {
    TextView sendingText;
    float result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculator_layout);
        sendingText =(TextView)findViewById(R.id.textView);
    }

    public void clearClicked(View v)
    {
        result = 0;
        sendingText.setText("0");
    }

    public void deleteClicked(View v)
    {
        String value = sendingText.getText().toString();
        //텍스트뷰에서 가져온 값가공
        float valueFloat = Float.parseFloat(value);
        //실수형으로 형변환
        int valueInt = Integer.parseInt(value);
        //정수형으로 형변환
        if(valueFloat != 0.0f)//0일때 제외
        {
            if(valueInt == valueFloat)//소수점 이하자리의 값 없음
            {
                valueInt = valueInt/10;
                value = ""+valueInt;//스트링값으로 형변환
                sendingText.setText(value);
            }//
            else//소수점 이하자리의 값이 존재함
            {
                float mockery = valueFloat-(float)valueInt;
                if(mockery<10)
                {
                    value = ""+valueInt;
                    sendingText.setText(value);
                }//mockery가 10 이하일때(한자릿수 소수점)
                else
                {//소수점 두자릿수 이상의 실수. 연산으로 뽑아내기 매우 귀찮음
                    int valueLength = value.length();//가져온 값의 길이계산
                    value = value.substring(0,valueLength-1);//맨 끝자리를 제외한 문자열 추출
                    sendingText.setText(value);
                }//mockery가 10 이상일때
            }//소수점 자릿수가 존재할때-else구문
        }
        else
        {
            sendingText.setText("0");
        }
    }//DeleteClicked의 종료

    public void numberPadClicked(View v)
    {
        String value = sendingText.getText().toString();//현재 스크린 상에 표시되고 있는 값 가져옴(스트링값)
        float valueFloat = Float.parseFloat(value);//실수형으로 형변환
        int valueInt = Integer.parseInt(value);
        float mockery = valueFloat - (float)valueInt;
        switch(v.getId())
        {
            case R.id.zero :
                if(valueFloat == 0.0f || mockery > 0.0f);
                //아무것도 실행하지 않음- 값이 0일때, 소수점이하값이 없을때
                else
                    value += "0";
                break;
            case R.id.one :
                if(valueFloat == 0.0f)
                    value = "1";
                else
                    value += "1";
                break;
            case R.id.two :
                if(valueFloat == 0.0f)
                    value = "2";
                else
                    value += "2";
                break;
            case R.id.three :
                if(valueFloat == 0.0f)
                    value = "3";
                else
                    value += "3";
                break;
            case R.id.four :
                if(valueFloat == 0.0f)
                    value = "4";
                else
                    value += "4";
                break;
            case R.id.five :
                if(valueFloat == 0.0f)
                    value = "5";
                else
                    value += "5";
                break;
            case R.id.six:
                if(valueFloat == 0.0f)
                    value = "6";
                else
                    value += "6";
                break;
            case R.id.seven:
                if(valueFloat == 0.0f)
                    value = "7";
                else
                    value += "7";
                break;
            case R.id.eight:
                if(valueFloat == 0.0f)
                    value = "8";
                else
                    value += "8";
                break;
            case R.id.nine:
                if(valueFloat == 0.0f)
                    value = "9";
                else
                    value += "9";
                break;
        }
      sendingText.setText(value);
    }

}
