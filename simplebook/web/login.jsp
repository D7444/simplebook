<%--
  Created by IntelliJ IDEA.
  User: hx25
  Date: 2020/4/18
  Time: 22:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录 - 简书</title>
    <link rel="stylesheet" href="css/login1.css">
</head>
<body>
<div class="container">
    <img src="images/loginLogo.png" onclick="window.location.href='index.jsp'" style="margin:50px;cursor: pointer">
    <div class="login-warpper">
        <div class="header">
            <a href="#" style="color: orange;">登录</a>
            ·
            <a href="register.jsp">注册</a>
        </div>
        <div class="form-warpper">
            <input type="text" id="email" placeholder="邮箱/手机号" class="input-item">
            <input type="password" id="password" placeholder="密码" class="input-item">
            <img id="pic" style="height: 45px;width: 140px" src="CheckServlet">
            <button type="button" onclick="Checktest()" class="btn1">看不清，换一张</button>
            <input type="text" id="code" placeholder="验证码" class="input-item">
            <button class="btn" onclick="login()">登 录</button>
            <div class="register" >
                忘记了密码？<a href="findPassword.jsp">找回密码</a>
            </div>
        </div>

    </div>
</div>
</body>

<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script>

    function Checktest(){
        var time = new Date();
        document.getElementById("pic").src="CheckServlet?t="+time;
    }

    //登录
    function login() {
        if (document.getElementById("email").value == "") {
            window.alert("账号不能为空！");
            return;
        }
        if (document.getElementById("password").value == "") {
            window.alert("密码不能为空！");
            return;
        }
        if(document.getElementById("code").value == "") {
            window.alert("请输入验证码");
            return;
        }
        $.ajax({
            url:"LoginServlet",
            type:"post",
            async:true,
            data:{"email":document.getElementById("email").value,"password":document.getElementById("password").value,"code":document.getElementById("code").value},
            success:function (msg) {
                if(msg == "success"){
                    window.location.href="index.jsp";
                }
                else{
                    window.alert(msg);
                }
            }
        });
    }
</script>
</html>
