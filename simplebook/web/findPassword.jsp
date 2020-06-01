<%--
  Created by IntelliJ IDEA.
  User: hx25
  Date: 2020/4/19
  Time: 17:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用邮箱重置密码</title>
    <link rel="stylesheet" href="css/login1.css">
</head>
<body>
<div class="container">
    <img src="images/loginLogo.png" onclick="window.location.href='index.jsp'" style="margin:50px;cursor: pointer">
    <div class="login-warpper">
        <div class="header">
            用邮箱重置密码
        </div>
        <div class="form-warpper">
            <form id="findForm" action="FindPasswordServlet" method="post">
                <input type="text" id="email" name="email" oninput="checkEmail(this.value)" placeholder="请输入注册或绑定的邮箱" class="input-item">
                <input type="text" id="code" name="code" placeholder="邮箱验证码" class="input-item" style="width: 50%;display: inline">
                <input type="button" id="sendEmail" disabled="disabled" class="btn1" onclick="send()" style="width: 40%;height: 40px;background-color: gray;cursor: default" value="发送邮件" >
                <input type="password" id="password1" name="password" placeholder="请输入新密码" class="input-item">
                <input type="password" id="password2" placeholder="请再输入一遍新密码" class="input-item">
                <input type="button" class="btn" onclick="checkCode()" value="重置密码">
                <div class="abtn"><a href="login.jsp">⬅返回登陆注册</a></div>
            </form>
        </div>
    </div>
</div>
</body>
<!--jquery需要引入的文件-->
<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script>
    //判断邮箱格式是否正确
    function checkEmail(str){
        var
            re = /^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/
        if(re.test(str)){
            //恢复鼠标指示
            document.getElementById("sendEmail").style.cursor="pointer";
            //恢复颜色
            document.getElementById("sendEmail").style.backgroundColor="#6f86d6";
            //解除按钮禁用
            document.getElementById("sendEmail").disabled="";
        }
    }

    //发送邮件
    var backCode;
    function send() {
        //调用servlet发送邮件
        $.ajax({
            url:"SendFindServlet",
            type:"POST",
            async:true,
            dataType:"text",
            data:{
                email:document.getElementById("email").value
            },
            success:function (msg) {
                //获取返回的验证码
                backCode = msg;
            }

        });
        //禁用按钮
        document.getElementById("sendEmail").disabled="disabled";
        //变换颜色
        document.getElementById("sendEmail").style.backgroundColor="gray";
    }

    function checkCode() {
        if(document.getElementById("code").value.toUpperCase() != backCode){
            alert("验证码错误");
            return ;
        }
        if(document.getElementById("password1").value == ""){
            alert("请输入要修改的密码");
            return ;
        }
        if(document.getElementById("password1").value != document.getElementById("password2").value){
            alert("两次密码不相同");
            return ;
        }
        findForm.submit();
    }
</script>
</html>
