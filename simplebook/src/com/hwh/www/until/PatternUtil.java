package com.hwh.www.until;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternUtil {

    /*正则表达式（根据规则regex拆分字段text）*/
    /*分离数字*/
    public static Matcher divideNumber(String text, String regex){
        //设置规则
        Pattern p = Pattern.compile(regex);
        //匹配字段
        Matcher m = p.matcher(text);
        return m;
    }

    /*切割字符串*/
    public static String[] divideText(String text,String regex){
        String[] arr = text.split(regex);
        return arr;
    }

    public static void main(String args[]){
        Matcher matcher = divideNumber("<p>测试2333<img alt=\"1589030783397084670.png\" src=\"/simplebook/ueditor/jsp/upload/image/20200509/1589030783397084670.png\"/></p>","<img.*?/>");
        while (matcher.find()) {
            System.out.println(matcher.group(0));
        }

    }



}
