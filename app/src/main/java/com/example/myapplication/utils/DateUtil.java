package com.example.myapplication.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil{
    public static String getNowDate(){
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        return sdf.format(new Date());
    }
}
