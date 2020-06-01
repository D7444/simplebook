<%--
  Created by IntelliJ IDEA.
  User: hx25
  Date: 2020/5/8
  Time: 13:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="menu.jsp" %>
<html>
<body>
<div class="container">
    <div class="col-md-1"></div>
    <div class="col-md-2" style="border: 1px solid green;border-radius: 10px">
        <ul class="nav nav-stacked">
            <li><a style="font-size: 20px"><span class="glyphicon glyphicon-user"></span>好友列表</a></li>
            <c:forEach items="${friendList}" var="friend">
                <li><a href="ChatServlet?id=${ownUser.id}&toid=${friend.freid}&chatName=${friend.uname}" style="font-size: 20px">${friend.uname}</a></li>
            </c:forEach>
        </ul>
    </div>
    <div class="col-md-8">
        <div style="height: 5%">
            <a href="MessageServlet?id=${ownUser.id}" data-toggle="tab" style="color: gray;font-size: 16px;margin-top: 10px;text-decoration: none"><span class="glyphicon glyphicon-chevron-left"></span>返回好友列表</a>
            <a href="UserServlet?id=${sessionScope.chatId}" style="position: center;font-size: 17px;font-weight: bold;margin-left: 180px;color: black;text-decoration: none">与${sessionScope.chatName}的聊天</a>
            <hr class="simple" style="margin: 15px" color="#6f5499" />
        </div>

        <div id="chat" style="height: 70%;overflow:auto">
        <c:forEach items="${chatList}" var="bean">
            <div style="margin-bottom: 20px">
                <div class="row clearfix">
                    <div class="col-md-1">
                        <img src="ImageServlet?id=${bean.id}" class="img-circle" style="height: 50px;width: 50px">
                    </div>
                    <div class="col-md-11">
                        <c:if test="${ownUser.id == bean.id}">
                            <textarea disabled="disabled" style="border: 1px solid gray;background: rgba(231,241,250,1);border-radius: 5px;width: 85%;height: 60px">${bean.content}</textarea>
                        </c:if>
                        <c:if test="${ownUser.id != bean.id}">
                            <textarea style="border: 1px solid gray;background: whitesmoke;border-radius: 5px;width: 85%;height: 60px">${bean.content}</textarea>
                        </c:if>
                        <br/>
                    </div>
                </div>
            </div>
        </c:forEach>
        </div>

        <div style="height: 10%;margin-top: 10px">
                <textarea style="width: 100%;height: 60px;background: rgba(245,245,245,1);border: 1px solid gray;border-radius: 5px;padding: 5px" id="content"></textarea>
                <button class="navbar-right" onclick="send()" style="font-size:20px;background: orangered;color: white;margin: 5px;border: none;border-radius: 20px;height: 40px;width: 80px">发送</button>
        </div>


    </div>
</div>

</body>

<script type="text/javascript">

    var websocket=null;
    if('WebSocket' in window){
        websocket=new WebSocket("ws://localhost:8888/simplebook_war22/server/" + ${ownUser.id});
    }
    else{
        alert('当前浏览器不支持websocket');
    }

    //连接失败事件
    websocket.onerror=function(){
    };

    //连接成功事件
    websocket.onopen=function(){
    };


    //有消息从服务器端发送过来
    websocket.onmessage=function (event){
        creatMsg(${chatId},event.data);
    };

    //关闭事件
    websocket.onclose=function(){
    };


    window.onbeforeunload=function(){//关闭窗口时，关闭连接
        websocket.close();
    };

    function send(){
        var message=document.getElementById('content').value;
        var jsonMsg = {"sendUser":${ownUser.id},"message":message,"toUser":${chatId}};
        //发送信息
        websocket.send(JSON.stringify(jsonMsg));
        //创建气泡框
        creatMsg(${ownUser.id},message);
        //清空输入框
        document.getElementById("content").value = "";
    }

    //创建新div
    function creatMsg(id,message) {
        var str = "";
        if(id == ${ownUser.id}){
            str="            <div style=\"margin-bottom: 20px\">\n" +
                "                <div class=\"row clearfix\">\n" +
                "                    <div class=\"col-md-1\">\n" +
                "                        <img src=\"ImageServlet?id=" + id + "\" class=\"img-circle\" style=\"height: 50px;width: 50px\">\n" +
                "                    </div>\n" +
                "                    <div class=\"col-md-11\">\n" +
                "                        <textarea style=\"border: 1px solid gray;background: rgba(231,241,250,1);border-radius: 5px;width: 85%;height: 60px\">" + message + "</textarea>\n" +
                "                        <br/>\n" +
                "                    </div>\n" +
                "                </div>\n" +
                "            </div>";
        }
        else {
            str="            <div style=\"margin-bottom: 20px\">\n" +
                "                <div class=\"row clearfix\">\n" +
                "                    <div class=\"col-md-1\">\n" +
                "                        <img src=\"ImageServlet?id=" + id + "\" class=\"img-circle\" style=\"height: 50px;width: 50px\">\n" +
                "                    </div>\n" +
                "                    <div class=\"col-md-11\">\n" +
                "                        <textarea style=\"border: 1px solid gray;background: whitesmoke;border-radius: 5px;width: 85%;height: 60px\">" + message + "</textarea>\n" +
                "                        <br/>\n" +
                "                    </div>\n" +
                "                </div>\n" +
                "            </div>";
        }
        document.getElementById("chat").innerHTML += str;
    }
</script>
</html>
