<%--
  Created by IntelliJ IDEA.
  User: hx25
  Date: 2020/4/21
  Time: 14:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="menu.jsp"%>
<html>
<head>
    <script type="text/javascript" charset="utf-8" src="ueditor/ueditor.config.js"></script> <!--ueditor的配置文件-->
    <script type="text/javascript" charset="utf-8" src="ueditor/ueditor.all.min.js"></script> <!--ueditor核心文件-->
    <script type="text/javascript" charset="utf-8" src="ueditor/lang/zh-cn/zh-cn.js"></script> <!--ueditor语言文件-->
    <title>简书 - 写文章</title>
</head>
<body>
<div class="row clearfix">
    <div class="col-md-1 column">
    </div>
    <div class="col-md-3 column">
        <ul class="nav nav-pills nav-stacked">
            <c:forEach items="${userWz}" var="bean">
                <c:set var="id" value="${bean.id}"/>
                <li><a href="#" data-toggle="tab" style="font-size: 20px;border:1px solid green;margin-top: 10px;border-radius: 20px " onclick="select(${bean.wzid})">${bean.title}</a></li>
            </c:forEach>
            <li><a class="active" href="#" data-toggle="tab" style="font-size: 20px;border:1px solid green;margin-top: 10px;border-radius: 20px " onclick="create()">+新文章</a></li>
        </ul>
    </div>
    <div class="col-md-7 column">
        <form action="UploadWzServlet" method="post" name="upform">
            <div style="width: 100%; height: 100%;">
                标题:
                <input type="text" id="title" name="title" style="width: 50%;margin-bottom: 20px;border-radius: 10px;height: 30px;font-size: 20px" value="${wzchange.title}">
                <input type="hidden" id="wzid" name="wzid">
                <input type="hidden" id="breif" name="breif">
                <input type="hidden" id="content" name="content">
                <input type="hidden" name="id" value="${ownUser.id}">
                类型：
                <span id="typeshow"></span>
                <select class="btn-primary" id="type" name="type" style="width: 60px">
                    <option value="社会">社会</option>
                    <option value="人文">人文</option>
                    <option value="科学">科学</option>
                    <option value="杂谈">杂谈</option>
                    <option value="文学">文学</option>
                    <option value="学习">学习</option>
                    <option value="技巧">技巧</option>
                </select>
                <button class="btn-primary" id="add" type="button" onclick="ac()" style="width: 100px;height: 50px;margin-left: 50px"><span class="glyphicon glyphicon-share-alt" style="margin-right: 5px"></span>发表文章</button>
                <button class="btn-danger" id="update" type="button" onclick="updateWz()" style="width: 100px;height: 50px;margin-left: 50px;display: none"><span class="glyphicon glyphicon-refresh" style="margin-right: 5px"></span>更新文章</button>
                <script id="editor" type="text/plain" style="width: 100%;height:100%;"></script>
            </div>
        </form>

    </div>
</div>
</body>
<script type="text/javascript">
    var ue = UE.getEditor('editor',{
        toolbars:[
            ['fullscreen', 'source', 'bold','blockquote','|','fontfamily', 'fontsize','|','insertimage', 'undo', 'redo','|','customstyle']
        ],
    });

//上传文章（增加新文章）
    function ac(){
        if(document.getElementById("title").value==""){
            alert("请给你的文章取个标题喽");
            return ;
        }
        document.getElementById("breif").value = ue.getContentTxt();
        document.getElementById("content").value = ue.getContent();
        if(document.getElementById("content").value==""){
            alert("是不是忘记输入内容了?");
            return ;
        }
        document.upform.submit();
    }


//更新文章
    function updateWz() {
        document.getElementById("breif").value = ue.getContentTxt();
        document.getElementById("content").value = ue.getContent();
        //改变提交到更新Servlet
        document.upform.action="UpdateWzServlet";
        document.upform.submit();
    }

//选择，动态更换文章
    function select(wzid) {
        document.getElementById("add").style.display = "none";
        document.getElementById("update").style.display = "inline";
        document.getElementById("wzid").value = wzid;
        $.ajax({
            url: "ChangWzServlet",
            type: "post",
            async: true,
            data:{"wzid":wzid},
            success:function (msg) {
                var str = eval("("+msg+")");
                document.getElementById("title").value = str["title"];
                document.getElementById("type").style.display="none";
                document.getElementById("typeshow").innerText = str["type"];
                ue.setContent(str["content"]);
            }
        });
    }

//创建新的文章
    function create() {
        document.getElementById("type").style.display="inline";
        document.getElementById("typeshow").innerText = "";
        document.getElementById("title").value = "";
        document.getElementById("add").style.display = "inline";
        document.getElementById("update").style.display = "none";
        ue.setContent("");
    }

</script>
</html>

