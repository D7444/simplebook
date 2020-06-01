package com.hwh.www.until;

public class EncryptionUtil {
    //密钥
    private static int key = 5;


    //加密
    public static String encryption(String str){
        String newStr = "";
        for(int i=0;i<str.length();i++){
            char c = str.charAt(i);
            c += key;
            newStr += c;
        }
        return newStr;

    }


    //解密
    public static String decrypt(String str){
        String newStr = "";
        for(int i=0;i<str.length();i++){
            char c = str.charAt(i);
            c -= key;
            newStr += c;
        }
        return newStr;
    }

    public static void main(String args[]){
        System.out.println(encryption("123456"));

    }
}
