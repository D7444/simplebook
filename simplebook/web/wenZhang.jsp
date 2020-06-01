<%--
  Created by IntelliJ IDEA.
  User: hx25
  Date: 2020/4/21
  Time: 15:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="menu.jsp" %>
<html>
<head>
    <title>${wznow.title} - 简书</title>
    <style>
        .test img{
            max-width: 95%;
            margin: 30px;
        }
    </style>
    <script type="text/javascript">
        //复制链接
        function copyUrl(){
            var Url2=document.getElementById("shareUrl").value;
            var oInput = document.createElement('input');
            oInput.value = Url2;
            document.body.appendChild(oInput);
            oInput.select(); // 选择对象
            document.execCommand("Copy"); // 执行浏览器复制命令
            oInput.className = 'oInput';
            oInput.style.display='none';
        }
    </script>
</head>
<body style="background: rgba(249,249,249,1)">
<div class="container">
    <div class="row clearfix">
        <div class="col-md-1 column">
        </div>
        <div class="col-md-7 column">
            <div style="background: white;border-radius: 10px;padding: 20px">
                <h2>${wznow.title}</h2>
                <div class="row clearfix" style="margin-bottom: 20px">
                    <div class="col-md-1">
                        <img src="ImageServlet?id=${wznow.id}" class="img-circle" style="height: 50px;width: 50px;margin-top: 10px">
                    </div>
                    <div class="col-md-8">
                        <div style="margin-top: 10px">
                            <a href="UserServlet?id=${wznow.id}" style="color: green">${wznow.uname}</a>
                            <input type="button" id="subButton" style="border-radius: 20px;color: orangered;background: white;border: 1px solid orangered" value="关注" onclick="followSub()">
                            <div>发布时间：${wznow.time}&nbsp;</div>
                        </div>
                    </div>
                    <c:set var="jownid" value="${ownUser.id}"/>
                    <c:set var="jwzid" value="${wznow.id}"/>
                    <c:if test="${jownid==jwzid}">
                        <div class="navbar-right">
                            <a href="addStartServlet?id=${ownUser.id}" style="margin-right: 30px;color: gray">编辑文章</a>
                        </div>
                    </c:if>
                </div>

                <div class="test">
                    ${wznow.content}
                </div>

                <button id="dzBefore" class="img-circle" style="height: 40px;width: 40px;border:1px solid gray;background: white;font-size: 25px" onclick="dianZan(0,${wznow.id})">
                    <span class="glyphicon glyphicon-thumbs-up img-circle"></span>
                </button>
                <c:if test="${ownUser != null}">
                    <button id="dzAfter" class="img-circle" style="height: 40px;width: 40px;border:1px solid orangered;background: orangered;font-size: 25px;color: white" onclick="dianZan(0,${wznow.id})">
                        <span class="glyphicon glyphicon-thumbs-up img-circle"></span>
                    </button>
                </c:if>
                <a id="dzNum" style="font-size: 30px">${wznow.dianzan}</a>

                <button id="clBefore" class="img-circle" style="height: 40px;width: 40px;border:1px solid gray;background: white;font-size: 25px" onclick="collect()">
                    <span class="glyphicon glyphicon-star img-circle"></span>
                </button>
                <c:if test="${ownUser != null}">
                    <button id="clAfter"  class="img-circle" style="height: 40px;width: 40px;border:1px solid orangered;background: orangered;font-size: 25px;color: white" onclick="collect()">
                        <span class="glyphicon glyphicon-star img-circle"></span>
                    </button>
                </c:if>
                <a id="clNum" style="font-size: 30px">${wznow.shoucang}</a>

                <button class="img-circle" style="height: 40px;width: 40px;border:1px solid gray;background: white;font-size: 25px" href="#share" data-toggle="modal" >
                    <span class="glyphicon glyphicon-share img-circle"></span>
                </button>

                <div class="modal fade" id="share" role="dialog" aria-labelledby="shareLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                                <h4 class="modal-title" id="shareLabel">
                                    分享链接给朋友
                                </h4>
                            </div>
                            <div class="modal-body">
                                <input type="text" id="shareUrl" value="${share}" style="width: 470px">
                                <button type="button" class="btn btn-primary" onclick="copyUrl()">复制</button>
                            </div>
                        </div>
                    </div>
                </div>

                    <div class="dropup navbar-right" style="margin-right: 10px">
                        <button href="#" data-toggle="dropdown" style="height: 40px;border: none;width: 40px;background: white;font-size: 25px">
                            <span class="glyphicon glyphicon-tasks img-circle"></span>
                        </button>
                        <ul class="dropdown-menu">
                            <c:if test="${jownid==jwzid}">
                                <li><a href="DeleteWzServlet?wzid=${wznow.wzid}">删除文章</a></li>
                            </c:if>
                            <c:if test="${ownUser.power=='管理员'}">
                                <li><a href="DeleteWzServlet?wzid=${wznow.wzid}">删除文章</a></li>
                                <li><a onclick="setHot()">置顶为热门文章</a></li>
                            </c:if>
                        </ul>
                    </div>


            </div>

            <div style="background: white;border-radius: 10px;margin-top: 20px;padding: 20px">
                <div class="row clearfix">
                        <div class="col-md-1">
                        <img src="ImageServlet?id=${ownUser.id}" class="img-circle" style="height: 50px;width: 50px">
                    </div>
                    <div class="col-md-11">
                        <form action="UploadPlServlet" method="post">
                            <input type="hidden" name="wzid" value="${wznow.wzid}">
                            <input type="hidden" name="id" value="${ownUser.id}">
                            <input type="hidden" name="fatherid" value="0">
                            <textarea style="width: 100%;height: 80px;background: rgba(245,245,245,1);border: 1px solid gray;border-radius: 5px;padding: 5px" name="content"></textarea>
                            <button class="navbar-right" style="background: orangered;color: white;margin: 5px;border: none;border-radius: 10px;height: 30px;width: 70px">发布</button>
                        </form>
                    </div>
                </div>
                <h4>全部评论</h4>
                <c:forEach items="${plnow1}" var="pl1" varStatus="statu">
                    <c:set var="jplId" value="${pl1.id}"/>
                    <div class="row clearfix">
                        <div class="col-md-1">
                            <img src="ImageServlet?id=${pl1.id}" class="img-circle" style="height: 50px;width: 50px;padding: 5px">
                        </div>
                        <div class="col-md-11">
                            <a href="UserServlet?id=${pl1.id}" style="color: green;font-size: 18px">${pl1.uname}</a>
                            <div style="font-size: 12px;color: gray">${statu.count}楼&nbsp;${pl1.time}</div>
                            <p style="margin-top: 10px">${pl1.content}</p>
                            <div style="font-size: 17px;color: gray">

                                <span id="${pl1.plid}" class="glyphicon glyphicon-thumbs-up" style="margin-right: 20px;cursor: pointer" onclick="dianZan(${pl1.plid},${pl1.id})">${pl1.dianzan}</span>
                                <span class="glyphicon glyphicon-comment" style="cursor: pointer" data-toggle="collapse" href="#first${statu.count}">回复</span>
                                <c:if test="${jownid==jplId}">
                                <div class="navbar-right">
                                    <a href="DeletePlServlet?wzid=${wznow.wzid}&plid=${pl1.plid}" style="margin-right: 30px;color: gray">删除</a>
                                </div>
                                </c:if>
                            </div>

                                <%--二级评论--%>
                        <div id="first${statu.count}" class="accordion-body collapse">
                            <c:forEach items="${plnow2}" var="pl2">
                                <c:set var="jplId2" value="${pl2.id}"/>
                                <c:set var="father" value="${pl1.plid}"/>
                                <c:set var="son" value="${pl2.fatherid}"/>
                                <c:if test="${father==son}">
                                    <hr class="simple" style="margin: 20px">
                                    <div class="row clearfix">
                                        <div class="col-md-1">
                                            <img src="ImageServlet?id=${pl2.id}" class="img-circle" style="height: 50px;width: 50px;padding: 5px">
                                        </div>
                                        <div class="col-md-11">
                                            <a href="UserServlet?id=${pl2.id}" style="color: green;font-size: 18px">${pl2.uname}</a>
                                            <div style="font-size: 12px;color: gray">${pl2.time}</div>
                                            <p style="margin-top: 10px">${pl2.content}</p>
                                            <div style="font-size: 17px;color: gray">
                                                <span class="glyphicon glyphicon-comment" style="cursor: pointer">回复</span>
                                            </div>
                                            <c:if test="${jownid==jplId2}">
                                                <div class="navbar-right">
                                                    <a href="DeletePlServlet?wzid=${wznow.wzid}&plid=${pl2.plid}" style="margin-right: 30px;color: gray">删除</a>
                                                </div>
                                            </c:if>
                                            <hr class="simple" style="margin: 20px">
                                        </div>
                                    </div>
                                </c:if>
                            </c:forEach>

                            <div class="row clearfix" >
                                <form action="UploadPlServlet" method="post">
                                    <input type="hidden" name="wzid" value="${wznow.wzid}">
                                    <input type="hidden" name="id" value="${ownUser.id}">
                                    <input type="hidden" name="fatherid" value="${pl1.plid}">
                                    <textarea name="content" style="width: 100%;height: 80px;background: rgba(245,245,245,1);border: 1px solid gray;border-radius: 5px;padding: 5px"></textarea>
                                    <button class="navbar-right" style="background: orangered;color: white;margin: 5px;border: none;border-radius: 10px;height: 30px;width: 70px">发布</button>
                                </form>
                            </div>

                        </div>


                        </div>
                    </div>
                    <hr class="simple" style="margin: 20px" color="#6f5499" />

                </c:forEach>

            </div>
        </div>
        <div class="col-md-3 column">
            <div style="background: white">
                <div style="border-left: 3px solid red ">
                    <a style="text-decoration: none;color: black;font-weight:bold;font-size:20px;margin-left: 10px">推荐阅读</a>
                </div>
                <c:forEach items="${wztype}" var="wztp">
                    <c:if test="${wztp.wzid != wznow.wzid}">
                        <div style="margin-top: 20px">
                            <h4><a href="FindWenZhangServlet?wzid=${wztp.wzid}" style="color: black">${wztp.title}</a></h4>
                            <a style="color: gray;text-decoration: none">收获点赞${wztp.dianzan}</a>
                        </div>
                    </c:if>
                </c:forEach>
            </div>
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
        data:{"sub":${ownUser.id},"besub":${wznow.id}},
        success:function (msg) {
            if(msg==""){
                //如果是自己则取消显示
                document.getElementById("subButton").style.display="none";
                return ;
            }
            if(msg=="true"){
                document.getElementById("subButton").value="取消关注";
            }else{
                document.getElementById("subButton").value="关注";
            }
        }
    });
    //判断文章点赞,收藏
    $.ajax({
        url:"CollectDZServlet",
        type:"post",
        async:true,
        data:{"id":${ownUser.id},"wzid":${wznow.wzid},"plid":"0"},
        success:function (msg) {
            var str = eval("("+msg+")");
            if(str["dianzan"]==true){
                document.getElementById("dzBefore").style.display="none";
            }else {
                document.getElementById("dzAfter").style.display="none";
            }

            if(str["collect"]==true){
                document.getElementById("clBefore").style.display="none";
            }else {
                document.getElementById("clAfter").style.display="none";
            }
        }
    });
    //点赞按钮
    function dianZan(plid,beid) {
        $.ajax({
            url:"DianZanServlet",
            type:"post",
            async:true,
            data:{"id":${ownUser.id},"wzid":${wznow.wzid},"plid":plid,"beid":beid},
            success:function (msg) {
                if(plid=="0") {
                    var i = parseInt(document.getElementById("dzNum").innerText);
                    if (msg == 1) {
                        document.getElementById("dzBefore").style.display="none";
                        document.getElementById("dzAfter").style.display="inline";
                        document.getElementById("dzNum").innerText=i+1;
                    } else {
                        document.getElementById("dzBefore").style.display="inline";
                        document.getElementById("dzAfter").style.display="none";
                        document.getElementById("dzNum").innerText=i-1;
                    }
                }else{
                    var i = parseInt(document.getElementById(plid).innerText);
                    if (msg == 1) {
                        document.getElementById(plid).innerText=i+1;
                    } else {
                        document.getElementById(plid).innerText=i-1;
                    }
                }
            }
        });
    }

    //收藏按钮
    function collect() {
        $.ajax({
            url:"CollectServlet",
            type:"post",
            async:true,
            data:{"id":${ownUser.id},"wzid":${wznow.wzid}},
            success:function (msg) {
                var i = parseInt(document.getElementById("clNum").innerText);
                if (msg == 1) {
                    document.getElementById("clBefore").style.display="none";
                    document.getElementById("clAfter").style.display="inline";
                    document.getElementById("clNum").innerText=i+1;
                } else {
                    document.getElementById("clBefore").style.display="inline";
                    document.getElementById("clAfter").style.display="none";
                    document.getElementById("clNum").innerText=i-1;
                }
            }
        });
    }


    //关注按钮
    function followSub() {
        if(document.getElementById("subButton").value=="关注"){
            $.ajax({
                url:"FollowControlServlet",
                type:"post",
                async:true,
                data:{"sub":${ownUser.id},"besub":${wznow.id},"flag":"add"},
                success:function (msg) {
                    if(msg!="success"){
                        alert(msg);
                    }
                    else {
                        document.getElementById("subButton").value = "取消关注";
                    }
                }
            });

        }
        else {
            $.ajax({
                url:"FollowControlServlet",
                type:"post",
                async:true,
                data:{"sub":${ownUser.id},"besub":${wznow.id},"flag":"cancel"},
                success:function () {
                    document.getElementById("subButton").value="关注";
                }
            });

        }
    }

    //设置为热门文章
    function setHot() {
        $.ajax({
            url:"SetHotServlet",
            type:"post",
            async:true,
            data:{"wzid":${wznow.wzid}},
            success:function () {
                alert("已设置为热门文章");
            }
        });
    }


</script>
</html>
