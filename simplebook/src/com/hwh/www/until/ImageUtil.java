package com.hwh.www.until;

import java.io.*;
import java.sql.Blob;

public class ImageUtil {

    //读取本地照片(用于上传)
    public static FileInputStream readImage(String path){
        FileInputStream fileInputStream = null;
        try {
            fileInputStream=new FileInputStream(new File(path));
        }catch (IOException e){
            e.printStackTrace();
        }
        return fileInputStream;
    }

    //读取数据库照片
    public static OutputStream getImage(Blob blob, String name){
        OutputStream output=null;
        try {
            //数据库中不存在图片，取消读取
            if(blob==null){
                return null;
            }
            //转化成流
            InputStream input = blob.getBinaryStream();
            File file = new File("images/");
            if(!file.exists()){
                file.mkdirs();
            }

            //添加照片或覆盖照片
            output = new FileOutputStream(new File("images/" +name+".jpg"));
            int len = 0;
            byte[] buff = new byte[1024];
            while ((len = input.read(buff)) != -1) {
                output.write(buff, 0, len);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return output;
    }
}
