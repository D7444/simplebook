package com.hwh.www.po;

import java.sql.Blob;

public class Photo {
    //文章id
    private int wzid;
    //图片
    private Blob photo;

    /*方法*/

    public int getWzid() {
        return wzid;
    }

    public void setWzid(int wzid) {
        this.wzid = wzid;
    }

    public void setPhoto(Blob photo) {
        this.photo = photo;
    }

    public Blob getPhoto() {
        return photo;
    }
}
