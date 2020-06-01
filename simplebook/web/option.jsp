<%--
  Created by IntelliJ IDEA.
  User: hx25
  Date: 2020/4/20
  Time: 19:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="menu.jsp" %>
<html>
<head>
    <title>设置 - 简书</title>
</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-1 column">
        </div>
        <div class="col-md-3 column">
            <ul class="nav nav-pills nav-stacked">
                <li class="active"><a href="#01" data-toggle="tab">基础设置</a></li>
                <li><a href="#02" data-toggle="tab">黑名单</a></li>
            </ul>
        </div>
        <div class="col-md-7 column">
            <div class="tab-content">
                <div class="tab-pane active" id="01">
                    <form action="UpdateUserServlet" name="changeForm" method="post">
                        <img src="ImageServlet?id=${ownUser.id}" id="image" class="img-circle" style="height: 150px;width: 150px">
                        <input type="hidden" name="id" value="${ownUser.id}">
                        <input type="file" name="file" id="file" style="display: none"/>
                        <button type="button" class="btn btn-success" style="margin-left: 50px;background-color: white;color: forestgreen" onclick="changImage()">更换头像</button>
                        <hr class="simple" style="margin: 25px" color="#6f5499" />
                        <h4>
                            <span class="label label-info" style="margin-right: 50px">昵称</span>
                            <input type="text" name="uname" class="input-item" style="border-radius: 5px;background-color: lightgray" value="${ownUser.uname}">
                            <hr class="simple" style="margin: 25px" color="#6f5499" />
                            <span class="label label-info" style="margin-right: 25px">电子邮箱</span>
                            <input type="text" id="email" name="email" class="input-item" readonly="readonly" style="border-radius: 5px;background-color: lightgray" value="${ownUser.email}">
                            <button type="button" class="btn btn-success" style="margin-left: 20px;background-color: white;color: forestgreen" onclick="changeEmail()">更换绑定邮箱</button>
                            <hr class="simple" style="margin: 25px" color="#6f5499" />
                            <span class="label label-info" style="margin-right: 50px">权限</span>
                            ${ownUser.power}
                            <hr class="simple" style="margin: 25px" color="#6f5499" />
                        </h4>
                        <button class="btn btn-success" style="width: 100px;height: 40px">保存</button>
                        <span style="color: red">${userUpdateMsg}</span>
                    </form>
                </div>
                <div class="tab-pane fade" id="02">
                    <p style="margin-bottom:50px">
                        你可以在用户主页将用户加入你的黑名单。在你黑名单中的用户无法在你文章下评论，
                        无法在其它评论中提到你，无法给你发送简信，自动从你的粉丝列表移除且无法再关注你
                    </p>
                    <hr class="simple" style="margin: 20px" />
                    <c:forEach items="${blacklist}" var="black">
                        <h4 id="black${black.blackId}" style="color: black">
                            <a href="UserServlet?id=${black.beid}" style="color: gray">${black.uname}</a>
                            <a class="navbar-right" style="margin-right: 30px;cursor: pointer" onclick="deleteBlack(${black.blackId})">从黑名单中移除</a>
                            <hr class="simple" style="margin: 20px" />
                        </h4>
                    </c:forEach>

                </div>
             </div>
        </div>
    </div>
</div>
</body>


<script>
    function changeEmail() {
        alert("已切换可更改状态");
        //解除禁用
        document.getElementById("email").readOnly="";
    }

    function changImage() {
        //模拟点击
        document.getElementById("file").click();
        //判断非空
        if(document.getElementById("file").value != ""){
            //提交表单
            document.changeForm.submit();
        }
    }

    //移出黑名单
    function deleteBlack(blackId) {
        $.ajax({
            url:"DeleteBlackServlet",
            method:"post",
            async:true,
            data:{"blackId":blackId},
            success:function () {
                document.getElementById("black"+blackId).style.display="none";
            }
        })
    }
</script>
</html>
