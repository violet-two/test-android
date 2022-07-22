package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class CalculatorActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_result;
    //第一个操作数
    private String firstNum = "";
    //运算符
    private String operator = "";
    //第二个操作数
    private String secondNum = "";
    //当前计算结果
    private String result = "";
    //显示的文本内容
    private String showText = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        //从布局文件中获取名叫tv_result的文本视图
        tv_result = findViewById(R.id.tv_result);
        findViewById(R.id.cancel).setOnClickListener(this);
        findViewById(R.id.divide).setOnClickListener(this);
        findViewById(R.id.multiply).setOnClickListener(this);
        findViewById(R.id.clear).setOnClickListener(this);
        findViewById(R.id.seven).setOnClickListener(this);
        findViewById(R.id.eight).setOnClickListener(this);
        findViewById(R.id.nine).setOnClickListener(this);
        findViewById(R.id.plus).setOnClickListener(this);
        findViewById(R.id.four).setOnClickListener(this);
        findViewById(R.id.five).setOnClickListener(this);
        findViewById(R.id.six).setOnClickListener(this);
        findViewById(R.id.minus).setOnClickListener(this);
        findViewById(R.id.one).setOnClickListener(this);
        findViewById(R.id.two).setOnClickListener(this);
        findViewById(R.id.three).setOnClickListener(this);
        findViewById(R.id.sqrt).setOnClickListener(this);
        findViewById(R.id.reciprocal).setOnClickListener(this);
        findViewById(R.id.zero).setOnClickListener(this);
        findViewById(R.id.dot).setOnClickListener(this);
        findViewById(R.id.equal).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String inputText;
        //如果是开根号按钮
        if ((view.getId() == R.id.sqrt)) {
            inputText = "√";
        } else {
            //出开根号外的其他按钮
            inputText = ((TextView) view).getText().toString();
        }
        switch (view.getId()) {
            //点击的清除按钮
            case R.id.clear:
                clear();
                break;
            //点击了取消按钮
            case R.id.cancel:
                break;
            //点击了加减乘除按钮
            case R.id.plus:
            case R.id.minus:
            case R.id.multiply:
            case R.id.divide:
                operator = inputText;
                refreshText(showText + operator);
                break;
            //点击了等号按钮
            case R.id.equal:
                double calculate_result = calculateFour();
                refreshOperate(String.valueOf(calculate_result));
                result = subZeroAndDot(result);
                refreshText(showText + "=" + result);
                break;
            //点击了开根号按钮
            case R.id.sqrt:
                double sqrt_result = Math.sqrt(Double.parseDouble(firstNum));
                refreshOperate(String.valueOf(sqrt_result));
                refreshText((showText + "√=" + result));
                break;
            //点击了求倒数按钮
            case R.id.reciprocal:
                double reciprocal_result = 1.0 / Double.parseDouble(firstNum);
                refreshOperate(String.valueOf(reciprocal_result));
                refreshText((showText + "/=" + result));
                break;
            //点击了其他按钮，包括数字和小数点
            default:
                if (result.length() > 0 && operator.equals("")) {
                    clear();
                }
                //无运算符，则继续拼接第一个操作数
                if (operator.equals("")) {
                    firstNum = firstNum + inputText;
                } else {
                    secondNum = secondNum + inputText;
                }

                //整数前面不需要0
                if (showText.equals("0") && !inputText.equals(".")) {
                    refreshText(inputText);
                } else {
                    refreshText(showText + inputText);
                }
                break;
        }
    }

    private static String subZeroAndDot(String s) {
        if (s.indexOf(".") > 0) {
            s = s.replaceAll("0+?$", "");//去掉多余的0
            s = s.replaceAll("[.]$", "");//如最后一位是.则去掉
        }
        return s;
    }

    private double calculateFour() {
        switch (operator) {
            case "+":
                return Double.parseDouble(firstNum) + Double.parseDouble(secondNum);
            case "-":
                return Double.parseDouble(firstNum) - Double.parseDouble(secondNum);
            case "*":
                return Double.parseDouble(firstNum) * Double.parseDouble(secondNum);
            case "÷":
                return Double.parseDouble(firstNum) / Double.parseDouble(secondNum);
        }
        return 0;
    }

    //清空并初始化
    private void clear() {
        refreshOperate("");
        refreshText("");
    }

    private void refreshOperate(String new_result) {
        result = new_result;
        firstNum = result;
        secondNum = "";
        operator = "";
    }

    //刷新文本显示
    private void refreshText(String text) {
        showText = text;
        tv_result.setText(showText);
    }
}