package com.hwh.www.until;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;

public class TimeUntil {
    /*获取当前时间*/
    public static String getNowTime() {
        //设定格式转换
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //返回当前时间
        return dateFormat.format(new Date());
    }

    /*比较时间(目标时间大于现在时间true，小于false)*/
    public static boolean compareNowTime(String date) {
        //设定格式转换
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH");
        Date today = new Date();
        try {
            Date dateD = dateFormat.parse(date);
            return dateD.getTime() >= today.getTime();

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    /*比较时间(date1大于等于data2返回true)*/
    public static boolean compareTime(String date1,String date2) {
        //设定格式转换
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH");
        try {
            Date dateD1 = dateFormat.parse(date1);
            Date dateD2 = dateFormat.parse(date2);
            return dateD1.getTime() >= dateD2.getTime();

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    /*添加时间*/
    public static String addTime(String time,int i) {
        //增加i天
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date newtime = new Date(dateFormat.parse(time).getTime() + 24 * 3600 * 1000 * i);
            return dateFormat.format(newtime);
        } catch (Exception e) {
            return "error";
        }
    }


    public static void main(String args[]) {
//        System.out.println(addTime(getNowTime("yyyy-MM-dd HH:mm:ss")));
//        System.out.println(compareTime("2020-04-12|10"));

    }
}
