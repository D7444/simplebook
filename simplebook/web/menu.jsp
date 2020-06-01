<%--
  Created by IntelliJ IDEA.
  User: hx25
  Date: 2020/4/19
  Time: 11:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="css/menu.css">
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
</head>
<body>
<div class="container">
<div class="col-md-1"></div>
<div class="col-md-10">
    <nav class="navbar navbar-default" role="navigation">
        <div class="navbar-header">
            <a class="navbar-brand" href="index.jsp" style="font-size: 23px;color: orangered;font-weight: bold">简 书</a>
        </div>
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <c:set var="power" value="${ownUser.power}"/>
            <c:if test="${power==null}">
            <ul class="nav navbar-nav" style="margin-left: 100px;">
                <li>
                    <a href="index.jsp"><span class="glyphicon glyphicon-home"></span>首页</a>
                </li>
            </ul>
            </c:if>
            <c:if test="${power!=null}">
            <ul class="nav navbar-nav">
                <li style="margin-left: 100px;">
                    <a href="index.jsp"><span class="glyphicon glyphicon-home"></span>发现</a>
                </li>
                <li class="dropdown">
                    <a href="MessageServlet?id=${ownUser.id}" class="dropdown-toggle" data-toggle="dropdown"><span class="glyphicon glyphicon-envelope"></span>消息<strong class="caret"></strong></a>
                    <ul class="dropdown-menu">
                        <li>
                            <a href="MessageServlet?id=${ownUser.id}">消息</a>
                        </li>
                        <li>
                            <a href="MessageServlet?id=${ownUser.id}">评论</a>
                        </li>
                        <li>
                            <a href="MessageServlet?id=${ownUser.id}">简信</a>
                        </li>
                        <li>
                            <a href="MessageServlet?id=${ownUser.id}">好友</a>
                        </li>
                    </ul>
                </li>
            </ul>
            </c:if>
            <form action="SearchServlet" method="post" class="navbar-form navbar-left" role="search">
                <div class="form-group">
                    <input name="search" type="text" class="form-control" />
                </div>
                <button type="submit" class="btn btn-default">搜索</button>
            </form>
            <button class="navbar-right btn-danger btn1" onclick="window.location.href='addStartServlet?id=${ownUser.id}'" style="margin-right: 5px;"><span class="glyphicon glyphicon-pencil" style="margin-right: 5px;"></span>写文章</button>
            <c:if test="${power!=null}">
            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                    <img src="ImageServlet?id=${ownUser.id}" class="img-circle dropdown-toggle" data-toggle="dropdown" style="height:40px;width: 40px;margin-top: 5px;cursor: pointer"><strong class="caret" style="margin-right: 20px;"></strong>
                    <ul class="dropdown-menu">
                        <li>
                            <a href="UserServlet?id=${ownUser.id}">我的主页</a>
                        </li>
                        <li>
                            <a href="GetCollectServlet?id=${ownUser.id}">收藏的文章</a>
                        </li>
                        <li>
                            <a href="OptionServlet?id=${ownUser.id}">设置</a>
                        </li>

                        <c:if test="${power == '管理员'}">
                            <li>
                                <a href="ReportServlet">举报处理</a>
                            </li>
                        </c:if>

                        <li>
                            <a href="LogOutServlet">注销</a>
                        </li>
                    </ul>
                </li>
            </ul>
            </c:if>
            <c:if test="${power==null}">
            <button class="navbar-right btn2" style="margin-right: 10px;" onclick="window.location.href='register.jsp'">注册</button>
            <ul class="nav navbar-nav navbar-right">
                <li>
                    <a href="login.jsp">登录</a>
                </li>
            </ul>
            </c:if>
        </div>
    </nav>
</div>
<div class="col-md-1"></div>
</div>
</body>
</html>
