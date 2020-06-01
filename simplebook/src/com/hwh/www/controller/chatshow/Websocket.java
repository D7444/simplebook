package com.hwh.www.controller.chatshow;

import com.alibaba.fastjson.JSONObject;
import com.hwh.www.service.ChatService;
import com.hwh.www.service.ChatServiceImpl;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/server/{sendUser}")
public class Websocket {
    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount=0;

    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。若要实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key可以为用户标识
    private static ConcurrentHashMap<Integer,Websocket> webSocketMap = new ConcurrentHashMap<Integer,Websocket>();

    //聊天信息
    private Integer sendUser;
    private Integer toUser;
    private String message;

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    @OnOpen
    public void onOpen(@PathParam("sendUser") Integer user, Session session){
        sendUser = user;
        this.session = session;
        webSocketMap.put(user,this);
        System.out.println("有人连接！" + sendUser);
    }

    @OnClose
    public void onClose(){
        System.out.println(sendUser+"已断开");
        webSocketMap.remove(sendUser);
    }

    @OnMessage
    public void onMessage(String showMsg,Session session){
        //获取消息，解包消息
        JSONObject object = JSONObject.parseObject(showMsg);
        sendUser = Integer.parseInt(object.getString("sendUser"));
        message = object.getString("message");
        toUser = Integer.parseInt(object.getString("toUser"));

        //储存消息到数据库
        ChatService chatService = new ChatServiceImpl();
        chatService.addChat(sendUser,toUser,message);

        System.out.println("来自"+sendUser+"的信息:"+message+toUser);

        //发送消息
        try{
            Websocket websocket = webSocketMap.get(toUser);
            //如果对方不在线则取消发送
            if(websocket!=null) {
                websocket.sendMessage(message);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    //发消息
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }



    //获得当前在线人数
    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    //新用户
    public static synchronized void addOnlineCount() {
        onlineCount++;
    }

    //移除用户
    public static synchronized void subOnlineCount() {
        onlineCount--;
    }


}
