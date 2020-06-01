<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: hx25
  Date: 2020/4/18
  Time: 22:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="menu.jsp"%>
<html>
<head>
  <title>简书</title>
    <style>
        .imgshow img{
            max-width: 100%;
            max-height: 170px;
            border-radius: 10px;
        }
    </style>
</head>
<body onload="run()">
<div class="container">
  <div class="row clearfix">
    <div class="col-md-12 column">
      <div class="row clearfix">
        <div class="col-md-1 column">
            <input id="flag" type="hidden" value=${param.a}>
        </div>
        <div class="col-md-7 column">
          <div class="jumbotron" style="border: 1px solid greenyellow">
              <a style="border: 2px solid red;border-radius: 5px;font-size: 18px;color: green;padding: 3px;margin-right: 5px"><span class="glyphicon glyphicon-fire" style="color: red"></span>热门文章</a>
              <h3><a href="FindWenZhangServlet?wzid=${wzHot.wzid}" style="color: black;font-weight: bold">${wzHot.title}</a></h3>
              <h5 style="color: gray;width: 100%">${wzHot.breif}</h5>
              <div>
                  <a style="border: 1px solid orangered;border-radius: 5px;font-size: 15px;color: orangered;padding: 3px;margin-right: 5px">${wzHot.type}</a>
                  <a href="UserServlet?id=${wzHot.id}" style="color: green">${wzHot.uname}</a>
                  <span class="glyphicon glyphicon-heart" style="margin-left: 10px;color: red;font-size: 12px">${wzHot.dianzan}</span>
              </div>
          </div>

            <div style="padding: 10px">
                <c:forEach items="${wenZhang}" var="bean">
                        <c:if test="${bean.photo != null}">
                            <div class="row clearfix">
                            <div class="col-md-8">
                        </c:if>
                        <h4 style="font-size: 22px"><a href="FindWenZhangServlet?wzid=${bean.wzid}" style="color: black;font-weight: bold">${bean.title}</a></h4>
                        <h5 style="color: gray;width: 100%;padding: 5px">${bean.breif}</h5>
                        <div class="bottom">
                            <a style="border: 1px solid orangered;border-radius: 5px;font-size: 15px;color: orangered;padding: 3px;margin-right: 5px">${bean.type}</a>
                            <a href="UserServlet?id=${bean.id}" style="color: green">${bean.uname}</a>
                            <span class="glyphicon glyphicon-heart" style="margin-left: 10px;color: red;font-size: 12px">${bean.dianzan}</span>
                        </div>

                        <c:if test="${bean.photo != null}">
                            </div>
                            <div class="col-md-4 imgshow">
                                    ${bean.photo}
                            </div>
                            </div>
                        </c:if>

                    <hr class="simple" style="margin: 25px">
                </c:forEach>
            </div>

            <ul class="pagination">
                <li>
                    <a href="WenZhangServlet?start=${pageTotal.prePage}">上一页</a>
                </li>

                <c:forEach begin="1" end="${pageTotal.totalPage}" varStatus="status">
                    <li>
                         <a href="WenZhangServlet?start=${status.count}">${status.count}</a>
                    </li>
                </c:forEach>

                <li>
                    <a href="WenZhangServlet?start=${pageTotal.nextPage}">下一页</a>
                </li>
            </ul>

        </div>
        <div class="col-md-3 column">
            <img src="images/banner1.png" style="height: 50px;width: 250px;margin-bottom: 10px;cursor: pointer" onclick="alert('未开发')">
            <img src="images/banner2.png" style="height: 50px;width: 250px;margin-bottom: 10px;cursor: pointer" onclick="alert('未开发')">
            <img src="images/banner3.png" style="height: 50px;width: 250px;margin-bottom: 10px;cursor: pointer" onclick="alert('未开发')">
            <img src="images/banner4.png" style="height: 50px;width: 250px;margin-bottom: 25px;cursor: pointer" onclick="alert('未开发')">
                <div>
                <a style="text-decoration: none;color: gray">推荐作者</a>
                <a class="navbar-right" href="#" style="margin-right: 15px;color: gray"><span class="glyphicon glyphicon-refresh"></span>换一批</a>
                </div>
                <c:forEach items="${recUser}" var="user">
                    <div class="row clearfix">
                        <div class="col-md-2">
                            <img src="ImageServlet?id=${user.id}" class="img-circle" style="height: 50px;width: 50px;margin-top: 10px">
                        </div>
                        <div class="col-md-8">
                            <a href="UserServlet?id=${user.id}" style="color: black;font-size: 18px;margin-left: 10px;display: block;padding-top: 10px">${user.uname}</a>
                            <a style="color: black;font-size: 15px;margin-left: 10px;display: block;color: gray;text-decoration: none">收获了${user.love}个喜欢</a>
                        </div>
                    </div>
                </c:forEach>

        </div>
        <div class="col-md-1 column">
        </div>
      </div>
    </div>
  </div>
</div>
</body>

<script>

    //初始化页面
    if(document.getElementById("flag").value != "true"){
        window.location.href="WenZhangServlet?start=1";
    }

</script>
</html>
