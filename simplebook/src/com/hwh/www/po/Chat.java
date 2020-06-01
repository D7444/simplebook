package com.hwh.www.po;

public class Chat {
    //聊天记录主键
    private int chatId;
    //发起id
    private int id;
    //接收id
    private int toid;
    //内容
    private String content;
    //时间
    private String time;



    /*方法*/

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setToid(int toid) {
        this.toid = toid;
    }

    public int getToid() {
        return toid;
    }

    public void setChatId(int chatId) {
        this.chatId = chatId;
    }

    public int getChatId() {
        return chatId;
    }
}
