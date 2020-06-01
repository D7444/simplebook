<%--
  Created by IntelliJ IDEA.
  User: hx25
  Date: 2020/4/21
  Time: 10:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="menu.jsp"%>
<html>
<head>
    <title>消息 - 简书</title>
</head>

<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-1 column">
        </div>
        <div class="col-md-3 column">
            <ul class="nav nav-pills nav-stacked">
                <li class="active"><a href="#notice" data-toggle="tab">邀请通知</a></li>
                <li><a href="#pl" data-toggle="tab">收到的评论</a></li>
                <li><a href="#chat" data-toggle="tab">简信</a></li>
                <li><a href="#friend" data-toggle="tab">好友</a></li>
            </ul>
        </div>

        <div class="col-md-7 column">
            <div class="tab-content">
                <div class="tab-pane active" id="notice">
                    <h4>邀请通知</h4>
                    <hr class="simple" style="margin: 25px" color="#6f5499" />

                    <div class="panel-group" id="panelParent1">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <a class="panel-title collapsed" data-toggle="collapse" data-parent="#panelParent1" href="#message01">收到的消息</a>
                            </div>
                            <div id="message01" class="panel-collapse collapse">
                                <c:forEach items="${benotice}" var="notice">
                                    <div class="row clearfix" style="margin-left: 3px;border: 2px solid orangered;border-radius: 10px;width: 99%">
                                        <div class="col-md-1">
                                            <img src="ImageServlet?id=${notice.id}" class="img-circle" style="height: 40px;width: 40px;margin-top: 10px">
                                        </div>
                                        <div class="col-md-6">
                                            <a href="UserServlet?id=${notice.id}" style="font-size: 20px;color: green">${notice.uname}</a>
                                            <pre>${notice.content}</pre>
                                                ${notice.time}
                                        </div>

                                        <c:if test="${notice.choice==null}">
                                            <div id="noticebtn${notice.noticeId}" class="col-md-5">
                                                <button style="color:orangered;font-weight:bold;background: white;border-radius: 10px;border: 1px solid green;width: 70px;height: 50px;margin: 20px" onclick="updateNotice('接受',${notice.noticeId})">接受</button>
                                                <button style="color:orangered;font-weight:bold;background: white;border-radius: 10px;border: 1px solid green;width: 70px;height: 50px" onclick="updateNotice('拒绝',${notice.noticeId})">拒绝</button>
                                                <br/>
                                                <span id="noticetc${notice.noticeId}" style="font-size: 20px;margin-left: 80px"></span>
                                            </div>

                                        </c:if>

                                        <c:if test="${notice.choice!=null}">
                                           <div class="col-md-5">
                                               <br/><br/>
                                               <span style="font-size: 20px;margin-left: 80px">${notice.choice}</span>
                                           </div>
                                        </c:if>

                                    </div>
                                </c:forEach>

                            </div>
                        </div>
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <a class="panel-title" data-toggle="collapse" data-parent="#panelParent1" href="#message02">发送的消息</a>
                            </div>
                            <div id="message02" class="panel-collapse collapse">

                                <c:forEach items="${notice}" var="notice">
                                    <div id="notice${notice.noticeId}" class="row clearfix" style="margin-left: 3px;border: 2px solid orangered;border-radius: 10px;width: 99%">
                                        <div class="col-md-1">
                                            <img src="ImageServlet?id=${notice.invite}" class="img-circle" style="height: 40px;width: 40px;margin-top: 10px">
                                        </div>
                                        <div class="col-md-6">
                                            <a href="UserServlet?id=${notice.invite}" style="font-size: 20px;color: green">${notice.uname}</a>
                                            <pre>${notice.content}</pre>
                                            ${notice.time}
                                        </div>
                                        <div class="col-md-5">

                                            <c:if test="${notice.choice==null}">
                                                <button style="color:orangered;font-weight:bold;background: white;border-radius: 10px;border: 1px solid green;width: 100px;height: 50px;margin-left: 50px;margin-top: 30px" onclick="deleteNotice(${notice.noticeId})">取消请求</button>
                                            </c:if>

                                            <c:if test="${notice.choice!=null}">
                                                <br/><br/>
                                                <span style="font-size: 20px;margin-left: 80px">对方已${notice.choice}</span>
                                            </c:if>
                                        </div>
                                    </div>
                                </c:forEach>

                            </div>
                        </div>
                    </div>

                </div>
                <div class="tab-pane fade" id="pl">
                    <h4>收到的评论</h4>
                    <hr class="simple" style="margin: 25px" color="#6f5499" />
                    <button class="btn btn-success" style="width: 100px;height: 40px">保存</button>
                </div>

                <div class="tab-pane fade" id="chat">
                    <div class="tab-content">
                        <div class="tab-pane active" id="chatlist">
                            <h4>全部简信</h4>
                            <div>
                                <c:forEach items="${messagelist}" var="list">

                                    <div class="row clearfix" style="margin-top: 10px" id="messageSession${list.messageId}">
                                        <hr class="simple" style="margin: 25px" color="#6f5499" />
                                        <div class="col-md-1" style="cursor: pointer;" data-toggle="tab" href="#chatWindow${list.messageId}">
                                            <img src="ImageServlet?id=${list.toid}" class="img-circle" style="height: 50px;width: 50px">
                                        </div>
                                        <div class="col-md-7" style="cursor: pointer" data-toggle="tab" href="#chatWindow${list.messageId}">
                                                <a href="#chatWindow" data-toggle="tab" style="color: green;font-size: 18px">${list.uname}</a>
                                                <div style="color: gray">${list.content}</div>
                                        </div>

                                        <div class="col-md-3" style="cursor: pointer" data-toggle="tab" href="#chatWindow${list.messageId}">
                                            <a style="text-decoration: none">${list.time}</a>
                                        </div>
                                        <div class="col-md-1" style="cursor: pointer">
                                            <span class="glyphicon glyphicon-chevron-down" data-toggle="dropdown" style="cursor: pointer;margin-right: 100px"></span>
                                            <ul class="dropdown-menu">
                                                <li><a onclick="deleteMessageSession(${list.messageId})">移除对话</a></li>
                                                <c:set var="i" value="a"/>
                                                <c:forEach items="${blacklist}" var="black">
                                                    <c:if test="${black.beid == list.toid}">
                                                        <li><a id="DeleteBlack${list.toid}" onclick="deleteBlack(${list.toid})"><span class="glyphicon glyphicon-check"></span>移出黑名单</a></li>
                                                        <li><a id="AddBlack${list.toid}" onclick="addBlack(${list.toid})" style="display: none;cursor: pointer"><span class="glyphicon glyphicon-ban-circle"></span>加入黑名单</a></li>
                                                        <c:set var="i" value="b"/>
                                                    </c:if>
                                                </c:forEach>
                                                <c:if test="${i=='a'}">
                                                    <li><a id="DeleteBlack${list.toid}" onclick="deleteBlack(${list.toid})" style="display: none"><span class="glyphicon glyphicon-check"></span>移出黑名单</a></li>
                                                    <li><a id="AddBlack${list.toid}" onclick="addBlack(${list.toid})"><span class="glyphicon glyphicon-ban-circle"></span>加入黑名单</a></li>
                                                </c:if>
                                            </ul>
                                        </div>

                                    </div>
                                </c:forEach>

                            </div>
                        </div>

                        <c:forEach items="${messagelist}" var="list">

                            <div class="tab-pane fade" id="chatWindow${list.messageId}">

                                <div style="height: 5%">
                                    <a href="#chatlist" data-toggle="tab" style="color: gray;font-size: 16px;margin-top: 10px;text-decoration: none"><span class="glyphicon glyphicon-chevron-left"></span>返回简信列表</a>
                                    <a id="chatUname" style="position: center;font-size: 17px;font-weight: bold;margin-left: 120px;color: black;text-decoration: none">与${list.uname}的对话</a>
                                    <span class="glyphicon glyphicon-chevron-down" style="margin-left: 250px;cursor: pointer"></span>
                                    <hr class="simple" style="margin: 15px" color="#6f5499" />
                                </div>

                                <div style="height: 70%;overflow:auto;" id="chatContent${list.messageId}">
                                    <c:forEach items="${message}" var="bean">
                                        <c:if test="${list.messageId == bean.messageId}">
                                            <div style="margin-bottom: 20px">
                                                <div class="row clearfix">
                                                    <div class="col-md-1">
                                                        <img src="ImageServlet?id=${bean.id}" class="img-circle" style="height: 50px;width: 50px">
                                                    </div>
                                                    <div class="col-md-11">
                                                        <c:if test="${list.id == bean.id}">
                                                        <textarea disabled="disabled" style="border: 1px solid gray;background: rgba(231,241,250,1);border-radius: 5px;width: 85%;height: 60px">${bean.content}</textarea>
                                                        </c:if>
                                                        <c:if test="${list.toid == bean.id}">
                                                        <textarea style="border: 1px solid gray;background: whitesmoke;border-radius: 5px;width: 85%;height: 60px">${bean.content}</textarea>
                                                        </c:if>
                                                        <br/>
                                                        <span>${bean.time}</span>
                                                    </div>
                                                </div>
                                            </div>
                                        </c:if>
                                    </c:forEach>
                                </div>

                                <div style="height: 10%">
                                        <textarea style="width: 100%;height: 60px;background: rgba(245,245,245,1);border: 1px solid gray;border-radius: 5px;padding: 5px" id="chatcontent"></textarea>
                                        <button onclick="sendMessage(${list.messageId},${list.toid})" class="navbar-right" style="font-size:20px;background: orangered;color: white;margin: 5px;border: none;border-radius: 20px;height: 40px;width: 80px">发送</button>
                                </div>
                            </div>

                        </c:forEach>

                    </div>
                </div>

                <div class="tab-pane fade" id="friend">
                    <h4 style="text-align: center">好友列表</h4>
                    <hr class="simple" style="margin: 10px" color="green" />

                    <div class="panel-group" id="panelParent">

                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <a class="panel-title collapsed" data-toggle="collapse" data-parent="#panelParent" href="#panel01">默认分组</a>
                            </div>
                            <div id="panel01" class="panel-collapse collapse">
                                <div id="默认列表" class="panel-body">
                                    <c:forEach items="${friendList}" var="friend">
                                        <c:if test="${friend.group eq '默认列表'}">
                                            <div id="friend${friend.freid}" class="dropdown" style="margin-bottom: 10px">
                                                <img src="ImageServlet?id=${friend.freid}" data-toggle="dropdown" class="img-circle" style="cursor: pointer;height: 40px;width:40px;margin-right: 5px"><a href="UserServlet?id=${friend.freid}" style="font-size: 18px;text-decoration: none">${friend.uname}</a>
                                                <ul class="dropdown-menu">
                                                    <li><a onclick="chatNow('${friend.freid}','${friend.uname}')" style="cursor: pointer">发送消息</a></li>
                                                    <li>
                                                        <a>移到分组</a>
                                                        <ul>
                                                            <li><a href="#" onclick="move(${friend.freid},this.innerHTML)" data-toggle="dropdown">默认列表</a></li>
                                                            <c:forEach items="${friendListName}" var="listname">
                                                                <li><a href="#" onclick="move(${friend.freid},this.innerHTML)" data-toggle="dropdown">${listname.group}</a></li>
                                                            </c:forEach>
                                                        </ul>
                                                    </li>
                                                    <c:set var="i" value="a"/>
                                                    <c:forEach items="${blacklist}" var="black">
                                                        <c:if test="${black.beid == friend.freid}">
                                                            <li><a id="fDeleteBlack${friend.freid}" onclick="deleteBlack(${friend.freid})" style="cursor: pointer"><span class="glyphicon glyphicon-check"></span>移出黑名单</a></li>
                                                            <li><a id="fAddBlack${friend.freid}" onclick="addBlack(${friend.freid})" style="display: none;cursor: pointer"><span class="glyphicon glyphicon-ban-circle"></span>加入黑名单</a></li>
                                                            <c:set var="i" value="b"/>
                                                        </c:if>
                                                    </c:forEach>
                                                    <c:if test="${i=='a'}">
                                                        <li><a id="fDeleteBlack${friend.freid}" onclick="deleteBlack(${friend.freid})" style="display: none;cursor: pointer"><span class="glyphicon glyphicon-check"></span>移出黑名单</a></li>
                                                        <li><a id="fAddBlack${friend.freid}" onclick="addBlack(${friend.freid})" style="cursor: pointer"><span class="glyphicon glyphicon-ban-circle"></span>加入黑名单</a></li>
                                                    </c:if>
                                                    <li><a href="#" onclick="deleteFriend(${friend.freid})">删除好友</a></li>
                                                    <li><a data-toggle="modal" href="#report-friend${friend.freid}">举报好友</a></li>
                                                </ul>

                                                <div class="modal fade" id="report-friend${friend.freid}" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                                    <div class="modal-dialog">
                                                        <div class="modal-content">
                                                            <div class="modal-header">
                                                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                                                                <h1 class="modal-title">
                                                                    举报
                                                                </h1>
                                                            </div>
                                                            <div class="modal-body" style="padding-left: 60px">
                                                                <div style="margin-bottom: 20px">
                                                                    <span>类型:</span>
                                                                    <select id="select${friend.freid}">
                                                                        <option value="涉及诈骗">涉及诈骗</option>
                                                                        <option value="涉及侵权">涉及侵权</option>
                                                                        <option value="虚假内容">虚假内容</option>
                                                                        <option value="垃圾信息">垃圾信息</option>
                                                                        <option value="色情信息">色情信息</option>
                                                                    </select>
                                                                </div>
                                                                <div>
                                                                    <span style="vertical-align: top">描述:</span>
                                                                    <textarea id="describeFriend${friend.freid}" placeholder="详细填写举报理由，有利于审核，不得少于8个字" style="width: 400px;height: 200px"></textarea>
                                                                </div>
                                                            </div>
                                                            <div class="modal-footer">
                                                                <button type="button" id="buttonClose${friend.freid}" class="btn btn-default" data-dismiss="modal">关闭</button>
                                                                <button type="button" class="btn btn-primary" onclick="report(${friend.freid})">提交</button>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>

                                            </div>
                                        </c:if>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                        <c:forEach items="${friendListName}" var="listname">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <a id="group${listname.groupId}" class="panel-title" data-toggle="collapse" data-parent="#panelParent" href="#group${listname.group}">${listname.group}</a>
                                    <input id="groupinput${listname.groupId}" type="hidden" style="background: whitesmoke;border-radius: 5px;border: 1px solid green">
                                    <button id="agroupbutton${listname.groupId}" class="btn" style="display:inline;margin-left:200px;border: 1px solid green;border-radius: 5px;background: whitesmoke;color: green" onclick="enableGroupName(${listname.groupId})">更改组名</button>
                                    <button id="bgroupbutton${listname.groupId}" class="btn" style="display:none;margin-left:150px;border: 1px solid green;border-radius: 5px;background: whitesmoke;color: green" onclick="updateGroupName(${listname.groupId})">提交</button>
                                    <button id="cgroupbutton${listname.groupId}" class="btn" style="display:none;margin-left:20px;border: 1px solid green;border-radius: 5px;background: whitesmoke;color: green" onclick="disableGroupName(${listname.groupId})">取消</button>

                                    <form action="DeleteGroupServlet" method="post" style="display:inline;">
                                        <input type="hidden" name="groupId" value="${listname.groupId}">
                                        <input type="hidden" name="id" value="${ownUser.id}">
                                        <input type="hidden" name="group" value="${listname.group}">
                                        <button class="btn" style="margin-left:50px;border: 1px solid green;border-radius: 5px;background: whitesmoke;color: green">删除分组</button>
                                    </form>
                                </div>
                                <div id="group${listname.group}" class="panel-collapse collapse">
                                    <div id="${listname.group}" class="panel-body">
                                        <c:forEach items="${friendList}" var="friend">
                                            <c:if test="${friend.group eq listname.group}">
                                                <div id="friend${friend.freid}" class="dropdown" style="margin-bottom: 10px">
                                                    <img src="ImageServlet?id=${friend.freid}" data-toggle="dropdown" class="img-circle" style="cursor: pointer;height: 40px;width:40px;margin-right: 5px"><a href="UserServlet?id=${friend.freid}" style="font-size: 18px;text-decoration: none">${friend.uname}</a>
                                                    <ul class="dropdown-menu">
                                                        <li><a onclick="chatNow('${friend.freid}','${friend.uname}')" style="cursor: pointer">发送消息</a></li>
                                                        <li>
                                                            <a>移到分组</a>
                                                            <ul>
                                                                <li><a href="#" onclick="move(${friend.freid},this.innerHTML)" data-toggle="dropdown">默认列表</a></li>
                                                                <c:forEach items="${friendListName}" var="listname">
                                                                    <li><a href="#" onclick="move(${friend.freid},this.innerHTML)" data-toggle="dropdown">${listname.group}</a></li>
                                                                </c:forEach>
                                                            </ul>
                                                        </li>
                                                        <li><a onclick="deleteFriend(${friend.freid})">删除好友</a></li>
                                                        <li><a data-toggle="modal" href="#report-friend${friend.freid}">举报好友</a></li>
                                                    </ul>

                                                    <div class="modal fade" id="report-friend${friend.freid}" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                                        <div class="modal-dialog">
                                                            <div class="modal-content">
                                                                <div class="modal-header">
                                                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                                                                    <h1 class="modal-title">
                                                                        举报
                                                                    </h1>
                                                                </div>
                                                                <div class="modal-body" style="padding-left: 60px">
                                                                    <div style="margin-bottom: 20px">
                                                                        <span>类型:</span>
                                                                        <select id="select${friend.freid}">
                                                                            <option value="涉及诈骗">涉及诈骗</option>
                                                                            <option value="涉及侵权">涉及侵权</option>
                                                                            <option value="虚假内容">虚假内容</option>
                                                                            <option value="垃圾信息">垃圾信息</option>
                                                                            <option value="色情信息">色情信息</option>
                                                                        </select>
                                                                    </div>
                                                                    <div>
                                                                        <span style="vertical-align: top">描述:</span>
                                                                        <textarea id="describeFriend${friend.freid}" placeholder="详细填写举报理由，有利于审核，不得少于8个字" style="width: 400px;height: 200px"></textarea>
                                                                    </div>
                                                                </div>
                                                                <div class="modal-footer">
                                                                    <button type="button" id="buttonClose${friend.freid}" class="btn btn-default" data-dismiss="modal">关闭</button>
                                                                    <button type="button" class="btn btn-primary" onclick="report(${friend.freid})">提交</button>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>

                                                </div>
                                            </c:if>
                                        </c:forEach>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>

                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <form action="AddNewGroupServlet" method="post">
                                    <input type="hidden" name="id" value="${ownUser.id}">
                                    <input type="text" name="group" placeholder="新的分组" style="border-radius: 5px;border: 1px solid green;background: whitesmoke;height: 35px">
                                    <button class="btn btn-primary" type="submit" style="margin-left: 20px">增加</button>
                                </form>
                            </div>
                        </div>

                </div>

                </div>
            </div>
        </div>
        <div class="col-md-1">
        </div>
    </div>
</div>
</body>

<script>
    //删除通知
    function deleteNotice(noticeId) {
        $.ajax({
            url:"DeleteNoticeServlet",
            method:"post",
            async:true,
            data:{"noticeId":noticeId},
            success:function () {
                alert("成功取消");
                document.getElementById("notice"+noticeId).style.display="none";
            }
        })
    }

    //更新通知
    function updateNotice(choice,noticeId) {
        $.ajax({
            url:"UpdateNoticeServlet",
            method:"post",
            async:true,
            data:{"noticeId":noticeId,"choice":choice},
            success:function () {
                //按钮显示取消
                document.getElementById("noticebtn"+noticeId).style.display="none";
                //修改通知
                document.getElementById("noticetc"+noticeId).innerHTML=choice;
            }
        })
    }

    //移动分组
    function move(uid,groupname) {
        $.ajax({
            url:"ChangeFriendGroupServlet",
            method:"post",
            async:true,
            data:{"id":${ownUser.id},"freid":uid,"group":groupname},
            success:function () {
                //移动头像
                document.getElementById(groupname).append(document.getElementById("friend"+uid));
            }
        })
    }

    //解除更改组名
    function enableGroupName(groupId) {
        //禁用超链接
        document.getElementById("group"+groupId).style.display="none";
        //启用输入框
        document.getElementById("groupinput"+groupId).type="text";
        document.getElementById("groupinput"+groupId).value=document.getElementById("group"+groupId).innerHTML;
        //禁用按钮
        document.getElementById("agroupbutton"+groupId).style.display="none";
        //启用修改相关按钮
        document.getElementById("bgroupbutton"+groupId).style.display="inline";
        document.getElementById("cgroupbutton"+groupId).style.display="inline";
    }

    //取消更改组名
    function disableGroupName(groupId) {
        //启用超链接
        document.getElementById("group"+groupId).style.display="inline";
        //禁用输入框
        document.getElementById("groupinput"+groupId).type="hidden";
        //启用按钮
        document.getElementById("agroupbutton"+groupId).style.display="inline";
        //禁用修改相关按钮
        document.getElementById("bgroupbutton"+groupId).style.display="none";
        document.getElementById("cgroupbutton"+groupId).style.display="none";
    }

    //更改组名
    function updateGroupName(groupId) {
        $.ajax({
            url:"UpdateGroupServlet",
            method:"post",
            async:true,
            data:{"groupId":groupId,"newgroup":document.getElementById("groupinput"+groupId).value},
            success:function () {
                //取消更改状态
                disableGroupName(groupId);
                //修改组名
                document.getElementById("group"+groupId).innerHTML=document.getElementById("groupinput"+groupId).value;
            }
        })
    }
    
    //删除好友
    function deleteFriend(freid) {
        $.ajax({
            url:"DeleteFriendServlet",
            method:"post",
            async:true,
            data:{"id":${ownUser.id},"freid":freid},
            success:function () {
                //删除好友显示
                document.getElementById("friend"+freid).style.display="none";
            }
        })
    }

    //发送简信
    function sendMessage(messageId,toid) {
        $.ajax({
            url:"SendMessageServlet",
            method:"post",
            async:true,
            data:{"messageId":messageId,"id":${ownUser.id},"toid":toid,"content":document.getElementById("chatcontent").value},
            success:function (msg) {
                if(msg=='success') {
                    str="            <div style=\"margin-bottom: 20px\">\n" +
                        "                <div class=\"row clearfix\">\n" +
                        "                    <div class=\"col-md-1\">\n" +
                        "                        <img src=\"ImageServlet?id=" + ${ownUser.id} + "\" class=\"img-circle\" style=\"height: 50px;width: 50px\">\n" +
                        "                    </div>\n" +
                        "                    <div class=\"col-md-11\">\n" +
                        "                        <textarea style=\"border: 1px solid gray;background: rgba(231,241,250,1);border-radius: 5px;width: 85%;height: 60px\">" + document.getElementById("chatcontent").value + "</textarea>\n" +
                        "                        <br/>\n" +
                        "                    </div>\n" +
                        "                </div>\n" +
                        "            </div>";
                    document.getElementById("chatContent" + messageId).innerHTML += str;
                    document.getElementById("chatcontent").value == "";
                    document.getElementById("chatcontent" + messageId).scrollIntoView();
                }else {
                    alert(msg);
                }
            }
        })
    }

    //加入黑名单
    function addBlack(beid) {
        $.ajax({
            url:"AddBlackServlet",
            method:"post",
            async:true,
            data:{"id":${ownUser.id},"beid":beid},
            success:function () {
                document.getElementById("AddBlack"+beid).style.display="none";
                document.getElementById("DeleteBlack"+beid).style.display="inline";
                document.getElementById("fAddBlack"+beid).style.display="none";
                document.getElementById("fDeleteBlack"+beid).style.display="inline";
            }
        })
    }

    //移出黑名单
    function deleteBlack(beid) {
        $.ajax({
            url:"DeleteBlackServlet",
            method:"post",
            async:true,
            data:{"id":${ownUser.id},"beid":beid},
            success:function () {
                document.getElementById("AddBlack"+beid).style.display="inline";
                document.getElementById("DeleteBlack"+beid).style.display="none";
                document.getElementById("fAddBlack"+beid).style.display="inline";
                document.getElementById("fDeleteBlack"+beid).style.display="none";
            }
        })
    }

    //移除对话
    function deleteMessageSession(messageId) {
        $.ajax({
            url:"DeleteMessageSEServlet",
            method:"post",
            async:true,
            data:{"messageId":messageId},
            success:function () {
                document.getElementById("messageSession"+messageId).style.display="none";
            }
        })
    }

    //举报
    function report(beid) {
        $.ajax({
            url:"AddReportServlet",
            method:"post",
            async:true,
            data:{"id":${ownUser.id},"beid":beid,"content":document.getElementById("describeFriend"+beid).value,"type":document.getElementById("select"+beid).value},
            success:function () {
                alert("你的举报已成功提交，客服会在24小时内进行处理");
                //关闭窗口
                document.getElementById("buttonClose"+beid).click();
            }
        })
    }

    //发起实时聊天
    function chatNow(toid,uname) {
        $.ajax({
            url:"JudgeTogetherServlet",
            method:"post",
            async:true,
            data:{"id":${ownUser.id},"toid":toid},
            success:function (msg) {
                if(msg == "true"){
                    //跳转聊天窗口
                    window.location.href="ChatServlet?id=${ownUser.id}&toid="+toid+"&chatName="+uname;
                }else {
                    alert("实时聊天功能需要双方互相关注");
                }
            }
        })
    }




</script>


</html>
