<%--
  Created by IntelliJ IDEA.
  User: hx25
  Date: 2020/5/14
  Time: 17:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="menu.jsp"%>
<html>
<head>
    <title>简书 - 举报</title>
</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="page-header">
                <h1>
                    举报系统 <small>请管理员及时处理未处理的举报邮件</small>
                </h1>
            </div>
            <div class="tabbable" id="tabs-8055">
                <ul class="nav nav-tabs">
                    <li class="active">
                        <a href="#first" data-toggle="tab">未处理的举报</a>
                    </li>
                    <li>
                        <a href="#second" data-toggle="tab">已处理的举报</a>
                    </li>
                </ul>
                <div class="tab-content">
                    <div class="tab-pane active" id="first">
                        <table class="table table-bordered">
                            <thead>
                            <tr>
                                <th>
                                    编号
                                </th>
                                <th>
                                    被举报人
                                </th>
                                <th>
                                    类型
                                </th>
                                <th>
                                    描述
                                </th>
                                <th>
                                    时间
                                </th>
                                <th>
                                    管理员处理
                                </th>
                            </tr>
                            </thead>
                            <tbody>

                            <c:forEach items="${reportlist}" var="report">
                                <c:if test="${report.back == null}">
                                    <tr id="report${report.reportId}">
                                        <td>
                                                ${report.reportId}
                                        </td>
                                        <td>
                                                ${report.uname}
                                        </td>
                                        <td>
                                                ${report.type}
                                        </td>
                                        <td>
                                                ${report.content}
                                        </td>
                                        <td>
                                                ${report.time}
                                        </td>
                                        <td>
                                            <label style="padding-left: 10px"><input class="checkbox" id="checkbox${report.reportId}" style="display: inline" type="checkbox" value="已读" onclick="readReport(${report.reportId})">已查阅</label>
                                        </td>
                                    </tr>
                                </c:if>
                            </c:forEach>

                            </tbody>
                        </table>
                    </div>

                    <div class="tab-pane fade" id="second">
                        <table class="table table-bordered">
                            <thead>
                            <tr>
                                <th>
                                    编号
                                </th>
                                <th>
                                    被举报人
                                </th>
                                <th>
                                    类型
                                </th>
                                <th>
                                    描述
                                </th>
                                <th>
                                    时间
                                </th>
                                <th>
                                    管理员处理
                                </th>
                            </tr>
                            </thead>
                            <tbody id="dealreport">

                            <c:forEach items="${reportlist}" var="report">
                                <c:if test="${report.back != null}">
                                    <tr>
                                        <td>
                                                ${report.reportId}
                                        </td>
                                        <td>
                                                ${report.uname}
                                        </td>
                                        <td>
                                                ${report.type}
                                        </td>
                                        <td>
                                                ${report.content}
                                        </td>
                                        <td>
                                                ${report.time}
                                        </td>
                                        <td>
                                                ${report.back}
                                        </td>
                                    </tr>
                                </c:if>
                            </c:forEach>

                            </tbody>
                        </table>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script>
    function readReport(reportId) {
        $.ajax({
            url:"DealReportServlet",
            method:"post",
            anscy:true,
            data:{"reportId":reportId},
            success:function () {
                //移动举报信息
                document.getElementById("dealreport").append(document.getElementById("report"+reportId));
                //禁用复选框
                document.getElementById("checkbox"+reportId).disabled = "disabled";
            }

        })
    }
</script>
</html>
