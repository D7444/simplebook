package com.hwh.www.po;

public class WenZhang {
    /*与数据库对应的数据*/
    //文章id
    private int wzid;
    //类型
    private String type;
    //标题
    private String title;
    //简介
    private String breif;
    //内容
    private String content;
    //用户id
    private int id;
    //点赞
    private int dianzan;
    //收藏
    private int shoucang;
    //时间
    private String time;
    //热门标记
    private String hot;

    /*数据库外的内容*/
    //昵称
    private String uname;
    //文章第一张图片
    private String photo;

    /*方法*/
    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }

    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }

    public int getDianzan() {
        return dianzan;
    }
    public void setDianzan(int dianzan) {
        this.dianzan = dianzan;
    }

    public int getShoucang() {
        return shoucang;
    }
    public void setShoucang(int shoucang) {
        this.shoucang = shoucang;
    }

    public int getWzid() {
        return wzid;
    }
    public void setWzid(int wzid) {
        this.wzid = wzid;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getBreif() {
        return breif;
    }

    public void setBreif(String breif) {
        this.breif = breif;
    }

    public String getUname() {
        return uname;
    }
    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getHot() {
        return hot;
    }

    public void setHot(String hot) {
        this.hot = hot;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPhoto() {
        return photo;
    }

    @Override
    public String toString(){
        return "WenZhang [wzid="+wzid+"type="+type+"title="+title+"breif="+breif+"content="+content+"id="+id+"dianzan="+dianzan+"shoucang="+shoucang+"time="+time+"]";
    }
}
