package com.hwh.www.until;

public class ChineseUtil {
    //判断是否是中文
    public static boolean isChinese(char c) {
        int ascii = (int)c;
        if(ascii >= 0 && ascii <= 255) {
            return false;
        }
        return true;
    }


    //获取字符串的字节长度,中文按2字节算
    public static int length(String chstring) {
        int length = 0;
        if(null == chstring || chstring.equals("") || chstring.length() == 0) {
            return 0;
        }
        for(int i = 0; i < chstring.length(); i++) {
            char c = chstring.charAt(i);
            if(isChinese(c)) {
                length += 2;
            }
            else {
                length += 1;
            }
        }
        return length;
    }

     //字符串截取
    public static String subChString(String chstring, int offset, int length) {
        if(null == chstring || chstring.equals("") || chstring.length() == 0) {
            return chstring;
        }
        int num = 0;
        int index = -1;
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < chstring.length(); i++) {
            char c = chstring.charAt(i);
            int move = 0;
            if(isChinese(c)) {
                move = 2;
            }
            else{
                move = 1;
            }
            index += move;
            if(index >= offset) {
                sb.append(c);
                num += move;
            }
            if(num >= length) {
                break;
            }
        }
        return sb.toString();
    }
}
