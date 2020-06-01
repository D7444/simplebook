<%--
  Created by IntelliJ IDEA.
  User: hx25
  Date: 2020/4/20
  Time: 20:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="menu.jsp" %>
<html>
<head>
    <title>${user.uname} - 简书</title>
    <style>
        .imgshow img{
            max-width: 100%;
            max-height: 170px;
            border-radius: 10px;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-1 column">
        </div>
        <div class="col-md-7 column">
            <div class="col-md-2">
            <img src="ImageServlet?id=${user.id}" class="img-circle" style="height: 90px;width: 90px">
            </div>
            <div class="col-md-10">
                <h4 style="font-weight: bold">${user.uname}
                    <c:if test="${ownUser.power == '管理员'}">
                        <button id="banbutton" href="#ban" data-toggle="modal" style="margin-top: 5px;background: white;border-radius: 15px;border: 1px solid red;height: 30px;width: 80px;color: red">封禁</button>
                    </c:if>
                </h4>

                <div class="modal fade" id="ban" role="dialog" aria-labelledby="banModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">x</button>
                                <h4 class="modal-title" id="banModalLabel">
                                    封锁【${user.uname}】用户
                                </h4>
                            </div>
                            <div class="modal-body">
                                解封日期：<a id="deadline" style="text-decoration: none">${user.ban}</a>
                                <br/>
                                选择封禁的期限:
                                <select id="banChoice">
                                    <option value="1">1天</option>
                                    <option value="3">3天</option>
                                    <option value="7">7天</option>
                                    <option value="14">14天</option>
                                </select>
                            </div>
                            <div class="modal-footer">
                                <button type="button" id="banClose" class="btn btn-default" data-dismiss="modal">关闭</button>
                                <button type="button" class="btn btn-primary" onclick="banUser()">确定</button>
                            </div>
                        </div>
                    </div>
                </div>
                <table class="table">
                    <td>${user.sub}<br/><a href="#second" data-toggle="tab" style="color: gray;font-size: 13px">关注</a></td>
                    <td>${user.fan}<br/><a href="#second" data-toggle="tab" style="color: gray;font-size: 13px">粉丝</a></td>
                    <td>${user.artical}<br/><a href="#first" data-toggle="tab" style="color: gray;font-size: 13px">文章</a></td>
                    <td>${user.love}<br/><a data-toggle="tab" style="color: gray;font-size: 13px;border-bottom: none;text-decoration: none">收获喜欢</a></td>
                    <td>
                        <form action="AddMessageServlet" method="post">
                            <input type="hidden" name="id" value="${ownUser.id}">
                            <input type="hidden" name="toid" value="${user.id}">
                            <button id="send" style="margin-top: 5px;background: white;border-radius: 20px;border: 1px solid green;height: 40px;width: 80px;color: green">发简信</button>
                        </form>
                    </td>

                    <td><input id="subButton" style="margin-top: 5px;background: white;border-radius: 20px;border: 1px solid green;height: 40px;width: 80px;color: green" type="button" value="关注" onclick="followSub()"></td>
                    <td><button id="addfriend" href="#modal-container-263574" data-toggle="modal" style="margin-top: 5px;background: white;border-radius: 20px;border: 1px solid green;height: 40px;width: 80px;color: green">添加好友</button></td>
                    <td><button id="sendfriend" style="display:none;margin-top: 5px;background: white;border-radius: 20px;border: 1px solid green;height: 40px;width: 80px;color: green" onclick="chatNow('${user.id}','${user.uname}')">实时聊天</button></td>

                    <div class="modal fade" id="modal-container-263574" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                                    <h4 class="modal-title" id="myModalLabel">
                                        添加好友
                                    </h4>
                                </div>
                                <div class="modal-body">
                                    个人介绍:<textarea id="mycontent" type="text" style="width: 100%;height: 60px;border-radius: 10px;border: 1px solid green" placeholder="加个好友交流一下吧"></textarea>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" id="buttonClose" class="btn btn-default" data-dismiss="modal">关闭</button>
                                    <button type="button" class="btn btn-primary" onclick="addfriend()">发送</button>
                                </div>
                            </div>
                        </div>
                    </div>


                </table>
            </div>
            <div class="tab-content">
                <div class="tab-pane active" id="first">
                    <div class="tabbable" id="tabs-107151" style="margin-left: 50px">
                        <ul class="nav nav-pills nav-tabs">
                            <li class="active">
                                <a href="#01" data-toggle="tab">文章</a>
                            </li>
                            <li>
                                <a href="#02" data-toggle="tab">动态</a>
                            </li>
                            <li>
                                <a href="#03" data-toggle="tab">最新评论</a>
                            </li>
                        </ul>
                        <div class="tab-content">
                            <div class="tab-pane active" id="01">
                                <hr style="margin: 25px;color: white">
                                <c:forEach items="${userWz}" var="bean">
                                        <h4><a href="FindWenZhangServlet?wzid=${bean.wzid}" style="color: black">${bean.title}</a></h4>
                                        <p style="color: gray">${bean.breif}</p>
                                        <div>
                                            <a href="UserServlet?id=${bean.id}" style="color: green">${bean.uname}</a>
                                            <span class="glyphicon glyphicon-heart" style="margin-left: 10px;color: red">${bean.dianzan}</span>
                                        </div>
                                        <hr class="simple" style="margin: 25px;color: gray"/>
                                </c:forEach>

                            </div>
                            <div class="tab-pane fade" id="02">

                                <c:forEach items="${dynamic}" var="d">
                                    <c:if test="${d.followUser != null}">
                                        <%--关注--%>
                                        <div style="margin-top: 20px">
                                                <%--个人信息--%>
                                            <div style="margin-bottom: 15px">
                                                <img src="ImageServlet?id=${d.id}" class="img-circle" style="height: 30px;width: 30px;margin-right: 10px">
                                                <a href="UserServlet?id=${d.id}" style="font-size: 15px;color: black;margin-right: 10px">${d.uname}</a>
                                                <a style="color: gray;text-decoration: none">关注了作者 &nbsp; ${d.time}</a>
                                            </div>
                                                <%--用户--%>
                                            <div style="margin-bottom: 10px;background: ghostwhite">
                                                <div class="row clearfix">
                                                    <div class="col-md-1" style="margin: 10px">
                                                        <img src="ImageServlet?id=${d.followUser.id}" class="img-circle" style="height: 50px;width: 50px;margin-top: 10px">
                                                    </div>
                                                    <div class="col-md-8">
                                                        <div style="margin-top: 20px">
                                                            <a href="#" style="font-size:20px;color: green">${d.followUser.uname}</a>
                                                            <div>写了${d.followUser.artical}篇文章,被${d.followUser.fan}人关注,获得了${d.followUser.love}个喜欢</div>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-3">
                                                    </div>
                                                </div>
                                                <hr class="simple">
                                            </div>
                                            <hr class="simple">
                                        </div>
                                    </c:if>
                                    <c:if test="${d.dianZan != null && d.wenZhang != null}">
                                        <%--点赞（喜欢）文章--%>
                                        <div style="margin-top: 20px">
                                                <%--个人信息--%>
                                            <div style="margin-bottom: 15px">
                                                <img src="ImageServlet?id=${d.id}" class="img-circle" style="height: 30px;width: 30px;margin-right: 10px">
                                                <a href="UserServlet?id=${d.id}" style="font-size: 15px;color: black;margin-right: 10px">${d.uname}</a>
                                                <a style="color: gray;text-decoration: none">喜欢了文章 ${d.time}</a>
                                            </div>
                                                <%--文章--%>
                                            <div style="margin-bottom: 10px">
                                                <c:if test="${d.wenZhang.photo != null}">
                                                <div class="row clearfix">
                                                    <div class="col-md-8">
                                                        </c:if>
                                                        <h4 style="font-size: 22px"><a href="#" style="color: gray;font-weight: bold">${d.wenZhang.title}</a></h4>
                                                        <h5 style="color: gray;width: 100%;padding: 5px">${d.wenZhang.breif}</h5>
                                                        <div class="bottom">
                                                            <a style="border: 1px solid orangered;border-radius: 5px;font-size: 15px;color: orangered;padding: 3px;margin-right: 5px">${d.wenZhang.type}</a>
                                                            <a href="UserServlet?id=${bean.id}" style="color: green">${d.wenZhang.uname}</a>
                                                            <span class="glyphicon glyphicon-heart" style="margin-left: 10px;color: red;font-size: 12px">${d.wenZhang.dianzan}</span>
                                                        </div>
                                                        <c:if test="${d.wenZhang.photo != null}">
                                                    </div>
                                                    <div class="col-md-4 imgshow">
                                                            ${d.wenZhang.photo}
                                                    </div>
                                                </div>
                                                </c:if>
                                            </div>
                                            <hr class="simple">
                                        </div>
                                    </c:if>
                                    <c:if test="${d.wenZhang != null}">
                                        <%--发表文章--%>
                                        <div style="margin-top: 20px">
                                                <%--个人信息--%>
                                            <div style="margin-bottom: 15px">
                                                <img src="ImageServlet?id=${d.id}" class="img-circle" style="height: 30px;width: 30px;margin-right: 10px">
                                                <a href="UserServlet?id=${d.id}" style="font-size: 15px;color: black;margin-right: 10px">${d.uname}</a>
                                                <a style="color: gray;text-decoration: none">发表了文章 ${d.time}</a>
                                            </div>
                                                <%--文章--%>
                                            <div style="margin-bottom: 10px">
                                                <h4 style="font-size: 22px"><a href="#" style="color: gray;font-weight: bold">${d.wenZhang.title}/a></h4>
                                                <h5 style="color: gray;width: 100%;padding: 5px">${d.wenZhang.breif}</h5>
                                                <div class="bottom">
                                                    <a style="border: 1px solid orangered;border-radius: 5px;font-size: 15px;color: orangered;padding: 3px;margin-right: 5px">${d.wenZhang.type}</a>
                                                    <span class="glyphicon glyphicon-heart" style="margin-left: 10px;color: red;font-size: 12px">${d.wenZhang.dianzan}</span>
                                                </div>
                                            </div>
                                            <hr class="simple">
                                        </div>
                                    </c:if>
                                    <c:if test="${d.pingLun != null && d.wenZhang != null}">
                                        <%--评论--%>
                                        <div style="margin-top: 20px">
                                                <%--个人信息--%>
                                            <div style="margin-bottom: 15px">
                                                <img src="ImageServlet?id=${d.id}" class="img-circle" style="height: 30px;width: 30px;margin-right: 10px">
                                                <a href="UserServlet?id=${d.id}" style="font-size: 15px;color: black;margin-right: 10px">${d.uname}</a>
                                                <a style="color: gray;text-decoration: none">发表了评论 ${d.time}</a>
                                            </div>
                                                <%--内容--%>
                                            <div style="margin-bottom: 10px">${d.pingLun.content}</div>
                                                <%--文章--%>
                                            <div style="border-left: 3px solid gray;padding: 15px;margin-bottom: 10px">
                                                <h4 style="font-size: 18px"><a href="#" style="color: gray;font-weight: bold">${d.wenZhang.title}</a></h4>
                                                <h5 style="color: gray;width: 100%">${d.wenZhang.breif}</h5>
                                                <div class="bottom">
                                                    <a style="border: 1px solid orangered;border-radius: 5px;font-size: 15px;color: orangered;padding: 3px;margin-right: 5px">${d.wenZhang.type}</a>
                                                    <a href="UserServlet?id=${bean.id}" style="color: green">${d.wenZhang.uname}</a>
                                                    <span class="glyphicon glyphicon-heart" style="margin-left: 10px;color: red;font-size: 12px">${d.wenZhang.dianzan}</span>
                                                </div>
                                            </div>
                                            <hr class="simple">
                                        </div>

                                    </c:if>
                                </c:forEach>

                            </div>
                            <div class="tab-pane" id="03">
                                <hr style="margin: 25px;color: white">
                                <c:forEach items="${userPlWz}" var="bean">
                                    <h4><a href="FindWenZhangServlet?wzid=${bean.wzid}" style="color: black">${bean.title}</a></h4>
                                    <p style="color: gray">${bean.breif}</p>
                                    <div>
                                        <a href="UserServlet?id=${bean.id}" style="color: green">${bean.uname}</a>
                                        <span class="glyphicon glyphicon-heart" style="margin-left: 10px;color: red">${bean.dianzan}</span>
                                    </div>
                                    <hr class="simple" style="margin: 25px;color: gray">
                                </c:forEach>
                            </div>

                        </div>
                    </div>
                </div>
                <div class="tab-pane" id="second">
                    <div class="tabbable" id="tabs-12350" style="margin-left: 50px">
                        <ul class="nav nav-pills nav-tabs">
                            <li class="active">
                                <a href="#sub" data-toggle="tab">关注</a>
                            </li>
                            <li>
                                <a href="#fans" data-toggle="tab">粉丝</a>
                            </li>
                        </ul>
                        <div class="tab-content">

                            <div class="tab-pane active" id="sub">
                                <c:set var="id1" value="${ownUser.id}"/>
                                <c:set var="id2" value="${user.id}"/>
                                <c:forEach items="${sub}" var="sb">
                                    <div class="row clearfix" id="${sb.id}">
                                        <div class="col-md-1">
                                            <img src="ImageServlet?id=${sb.id}" class="img-circle" style="height: 50px;width: 50px;margin-top: 10px">
                                        </div>
                                        <div class="col-md-8">
                                            <div style="margin-top: 10px">
                                                <a href="UserServlet?id=${sb.id}" style="color: green">${sb.uname}</a>
                                                <div>关注：${sb.sub}&nbsp;|&nbsp;粉丝：${sb.fan}&nbsp;|&nbsp;文章：${sb.artical}</div>
                                                <div>获得喜欢:${sb.love}</div>
                                            </div>
                                        </div>
                                        <div class="col-md-3">
                                            <c:if test="${id1==id2}">
                                            <button class="btn btn-success" style="margin-top: 20px;height: 40px;background-color: white;color: forestgreen" onclick="cancelSub(${sb.id})">取消关注</button>
                                            </c:if>
                                        </div>
                                    </div>
                                    <hr class="simple" style="margin: 10px" color="#6f5499" />
                                </c:forEach>
                            </div>

                            <div class="tab-pane" id="fans">
                                <c:forEach items="${fan}" var="sb">
                                    <div class="row clearfix">
                                        <div class="col-md-1">
                                            <img src="ImageServlet?id=${sb.id}" class="img-circle" style="height: 50px;width: 50px;margin-top: 10px">
                                        </div>
                                        <div class="col-md-8">
                                            <div style="margin-top: 10px">
                                                <a href="UserServlet?id=${sb.id}" style="color: green">${sb.uname}</a>
                                                <div>关注：${sb.sub}&nbsp;|&nbsp;粉丝：${sb.fan}&nbsp;|&nbsp;文章：${sb.artical}</div>
                                                <div>获得喜欢:${sb.love}</div>
                                            </div>
                                        </div>
                                        <div class="col-md-3">
                                        </div>
                                    </div>
                                    <hr class="simple" style="margin: 10px" color="#6f5499" />
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-3 column">
            1
        </div>
        <div class="col-md-1 column">
        </div>
    </div>
</div>
</body>

<script>

    //判断是否关注了用户
    $.ajax({
        url:"FollowServlet",
        type:"post",
        async:true,
        data:{"sub":${ownUser.id},"besub":${user.id}},
        success:function (msg) {
            if(msg==""){
                //如果是自己则取消显示
                document.getElementById("send").style.display="none";
                document.getElementById("subButton").style.display="none";
                document.getElementById("addfriend").style.display="none";
                return ;

            }
            if(msg=="true"){
                document.getElementById("subButton").value="取消关注";
            }else{
                document.getElementById("subButton").value="关注";
            }
        }
    });

    //判断是否是好友
    $.ajax({
        url:"JudgeFriendServlet",
        type:"post",
        async:true,
        data:{"id":${ownUser.id},"freid":${user.id}},
        success:function (msg) {
            if(msg=="true"){
                document.getElementById("sendfriend").style.display="inline";
                document.getElementById("addfriend").style.display="none";
            }
        }
    });


    //关注按钮
    function followSub() {
        if(document.getElementById("subButton").value=="关注"){
            $.ajax({
                url:"FollowControlServlet",
                type:"post",
                async:false,
                data:{"sub":${ownUser.id},"besub":${user.id},"flag":"add"},
                success:function (msg) {
                    if(msg != "success"){
                        alert(msg);
                    }
                    else{
                    document.getElementById("subButton").value="取消关注";
                    }
                }
            });
        }
        else {
            $.ajax({
                url:"FollowControlServlet",
                type:"post",
                async:false,
                data:{"sub":${ownUser.id},"besub":${user.id},"flag":"cancel"},
                success:function () {
                    document.getElementById("subButton").value="关注";
                }
            });

        }
        setTimeout(window.location.href="UserServlet?id=${user.id}","2000");

    }

    //封禁用户
    function banUser() {
        $.ajax({
            url:"BanUserServlet",
            type:"post",
            async:true,
            data:{"id":${user.id},"banChoice":document.getElementById("banChoice").value},
            success:function (msg) {
                alert("成功封禁");
                //关闭窗口
                document.getElementById("banClose").click();
                //修改期限
                document.getElementById("deadline").innerHTML=msg;
            }
        });
    }

    //下层唯一取消关注按钮
    function cancelSub(besub) {
        $.ajax({
            url:"FollowControlServlet",
            type:"post",
            async:true,
            data:{"sub":${ownUser.id},"besub":besub,"flag":"cancel"}
        });
        document.getElementById(besub).style.display="none";
    }
    
    //好友操作
    function addfriend() {
        $.ajax({
           url:"AddNoticeServlet",
           type:"post" ,
            async:true,
            data:{"id":${ownUser.id},"invite":${user.id},"content":document.getElementById("mycontent").value,"flag":1},
            success:function () {
                alert("请求已经发送成功");
                //关闭窗口
                document.getElementById("buttonClose").click();
            }
        });
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
