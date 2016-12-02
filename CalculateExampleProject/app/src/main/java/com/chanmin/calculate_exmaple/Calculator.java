package com.chanmin.calculate_exmaple;

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
    //TextView sendingText;

    public void ClearClicked(View v)
    {
        result = 0;
        sendingText.setText("0");
    }
    public void DeleteClicked(View v)
    {
        String value = sendingText.toString();
        //텍스트뷰에서 가져온 값가공
        float value_float = Float.parseFloat(value);
        //실수형으로 형변환
        int value_int = Integer.parseInt(value);
        //정수형으로 형변환
        if(value_float != 0.0f)//0일때 제외
        {
            if(value_int == value_float)//소수점 이하자리의 값 없음
            {
                value_int = value_int/10;
                value = ""+value_int;//스트링값으로 형변환
                sendingText.setText(value);
            }//
            else//소수점 이하자리의 값이 존재함
            {
                float mockery = value_float-value_int;
                if(mockery<10)
                {
                    value = ""+value_int;
                    sendingText.setText(value);
                }//mockery가 10 이하일때(한자릿수 소수점)
                else
                {//소수점 두자릿수 이상의 실수. 연산으로 뽑아내기 매우 귀찮음
                    int value_length = value.length();//가져온 값의 길이계산
                    value = value.substring(0,value_length-1);//맨 끝자리를 제외한 문자열 추출
                    sendingText.setText(value);
                }//mockery가 10 이상일때
            }//소수점 자릿수가 존재할때-else구문
        }
        else
        {
            sendingText.setText("0");
        }
    }//DeleteClicked의 종료
    public void ZeroClicked(View v)
    {
        String value = sendingText.toString();
        int value_int = Integer.parseInt(value);
        float value_float = Integer.parseInt(value);
        if(value_int != value_float)
        {
            sendingText.setText(value);
        }else
        {
            value = value+"0";
            sendingText.setText(value);
        }
    }
    public void NineClicked(View v)
    {
        String value = sendingText.toString();
        value = value+"9";
        sendingText.setText(value);
    }
    public void EightClicked(View v)
    {
        String value = sendingText.toString();
        value = value+"8";
        sendingText.setText(value);
    }
    public void SevenClicked(View v)
    {
        String value = sendingText.toString();
        value = value+"7";
        sendingText.setText(value);
    }
    public void SixClicked(View v)
    {
        String value = sendingText.toString();
        value = value+"6";
        sendingText.setText(value);
    }
    public void FiveClicked(View v)
    {
        String value = sendingText.toString();
        value = value+"5";
        sendingText.setText(value);
    }
    public void FourClicked(View v)
    {
        String value = sendingText.toString();
        value = value+"4";
        sendingText.setText(value);
    }
    public void ThreeClicked(View v)
    {
        String value = sendingText.toString();
        value = value+"3";
        sendingText.setText(value);
    }
    public void TwoClicked(View v)
    {
        String value = sendingText.toString();
        value = value+"2";
        sendingText.setText(value);
    }
    public void OneClicked(View v)
    {
        String value = sendingText.toString();
        value = value+"1";
        sendingText.setText(value);
    }
}
