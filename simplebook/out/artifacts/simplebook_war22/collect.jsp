<%--
  Created by IntelliJ IDEA.
  User: hx25
  Date: 2020/5/2
  Time: 13:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="menu.jsp"%>
<html>
<head>
    <title>收藏的文章</title>
</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-2 column">
        </div>
        <div class="col-md-8 column">
            <img src="images/collect.png" style="width: 100%">
            <hr style="margin: 25px;color: white">
            <c:forEach items="${userCl}" var="bean">
                <h4><a href="FindWenZhangServlet?wzid=${bean.wzid}" style="color: black">${bean.title}</a></h4>
                <p style="color: gray">${bean.breif}</p>
                <div>
                    <a href="UserServlet?id=${bean.id}" style="color: green">${bean.uname}</a>
                    <span class="glyphicon glyphicon-heart" style="margin-left: 10px;color: red">${bean.dianzan}</span>
                </div>
                <hr class="simple" style="margin: 25px;color: gray"/>
            </c:forEach>
        </div>
        <div class="col-md-2 column">
        </div>
    </div>
</div>
</body>
</html>
