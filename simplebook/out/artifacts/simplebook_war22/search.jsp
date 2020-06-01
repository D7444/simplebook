<%--
  Created by IntelliJ IDEA.
  User: hx25
  Date: 2020/5/2
  Time: 22:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="menu.jsp"%>
<html>
<head>
    <title>搜索</title>
</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-1">
        </div>
        <div class="col-md-3 column">
            <ul class="nav nav-pills nav-stacked">
                <li class="active"><a href="#wz" data-toggle="tab">文章</a></li>
                <li><a href="#user" data-toggle="tab">用户</a></li>
            </ul>
        </div>
        <div class="col-md-7 column">
            <div class="tab-content">

                <div class="tab-pane active" id="wz">
                    <span style="font-size: 20px;margin-top: 20px;color: green">搜索的文章</span>
                    <hr style="margin: 25px;color: white">
                    <c:forEach items="${wzSearch}" var="bean">
                        <h4><a href="FindWenZhangServlet?wzid=${bean.wzid}" style="color: black">${bean.title}</a></h4>
                        <p style="color: gray">${bean.breif}</p>
                        <div>
                            <a href="UserServlet?id=${bean.id}" style="color: green">${bean.uname}</a>
                            <span class="glyphicon glyphicon-heart" style="margin-left: 10px;color: red">${bean.dianzan}</span>
                        </div>
                        <hr class="simple" style="margin: 25px;color: gray"/>
                    </c:forEach>
                </div>

                <div class="tab-pane" id="user">
                    <span style="font-size: 20px;margin-top: 20px;color: green">搜索的用户</span>
                    <hr style="margin: 25px;color: white">
                    <c:forEach items="${userSearch}" var="sb">
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
        <div class="col-md-1 column">
        </div>
    </div>
</div>
</body>
</html>
