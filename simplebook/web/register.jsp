<%--
  Created by IntelliJ IDEA.
  User: hx25
  Date: 2020/4/19
  Time: 10:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored = "false" %>
<html>
<head>
    <title>注册 - 简书</title>
    <link rel="stylesheet" href="css/login.css">
</head>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=`, initial-scale=1.0">
    <link rel="stylesheet" href="css/login1.css">
    <title>用户登录</title>
</head>
<body>
<div class="container">
    <img src="images/loginLogo.png" onclick="window.location.href='index.jsp'" style="margin:50px;cursor: pointer">
    <div class="login-warpper">
        <div class="header">
            <a href="login.jsp">登录</a>
            ·
            <a href="#" style="color: orange;">注册</a>
        </div>
        <div class="error">${sessionScope.registerError}</div>
        <div class="form-warpper">
                <input type="text" id="uname" placeholder="昵称" class="input-item">
                <input type="text" id="email" placeholder="邮箱" class="input-item">
                <input type="password" id="password" placeholder="密码" class="input-item">
                <button type="button" class="btn" onclick="regis()">注 册</button>
        </div>
        <div>点击 “注册” 即表示您同意并愿意遵守简书
            用户协议 和 隐私政策 。</div>
    </div>
</div>
</body>

<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script>
    function regis(){
        if (document.getElementById("uname").value == "") {
            window.alert("昵称不能为空！");
            return ;
        }

        if (document.getElementById("email").value == "") {
            window.alert("邮箱不能为空！");
            return ;
        }

        if(document.getElementById("password").value == "") {
            window.alert("密码不能为空！");
            return ;
        }

        $.ajax({
            url:"RegisterServlet",
            type:"post",
            async:true,
            data:{"uname":document.getElementById("uname").value,"email":document.getElementById("email").value,"password":document.getElementById("password").value},
            success:function (msg) {
                if(msg == "success"){
                    alert("注册成功");
                    window.location.href="login.jsp";
                }
                else{
                    alert(msg);
                }
            }
        });
    }
</script>
</html>
