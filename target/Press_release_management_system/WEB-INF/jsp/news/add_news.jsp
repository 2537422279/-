<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="place">
    <span>位置：</span>
    <ul class="placeul">
        <li><a href="#">首页</a></li>
        <li><a href="${pageContext.request.contextPath}/findNewsByPage">新闻列表</a></li>
    </ul>
</div>
<div class="formbody">
    <div class="formtitle"><span>发布新闻</span></div>
    <form action="${pageContext.request.contextPath}/addNews" name="ff" method="post" onsubmit="return checkValue()">
        <ul class="forminfo">
            <li><label style="width:150px;">新闻标题（<font style="color:Red;font-weight:bolder;">*</font>）：</label><input id="title" name="title" value="${news.title}" type="text" class="dfinput" /><i></i></li>
            <li><label style="width:150px;">新闻类别（<font style="color:Red;font-weight:bolder;">*</font>）：</label>
                <select name="categoryId" id="categoryId"  class="dfinput">
                    <option value="">--请选择--</option>
                    <c:forEach items="${categoryList}" var="c">
                        <option value="${c.categoryId}" <c:if test="${c.categoryId eq news.categoryId }">selected="selected"</c:if>>${c.categoryName}</option>
                    </c:forEach>
                </select>
                <i></i></li>
            <li><label style="width:150px;">新闻内容页标题（<font style="color:Red;font-weight:bolder;">*</font>）：</label><input id="contentTitle" name="contentTitle" value="${news.contentTitle}" type="text" class="dfinput" /><i></i></li>
            <li><label style="width:150px;">内容摘要：</label><textarea id="contentAbstract" name="contentAbstract" cols="100" rows="2" style="width:800px;height:50px;"  class="dfinput">${news.contentAbstract}</textarea><i></i></li>
            <li><label style="width:150px;">内容：（<font style="color:Red;font-weight:bolder;">*</font>）</label><textarea id="content" name="content" cols="100" rows="4" style="width:800px;height:100px;"  class="dfinput">${news.content}</textarea><i></i></li>
            <li><label style="width:150px;">关键词：</label><input id="keywords" name="keywords"  value="${news.keywords}" type="text" class="dfinput" /><i></i></li>
            <li><label style="width:150px;">作者/来源：</label><input id="author" name="author"  value="${news.author}" type="text" class="dfinput" /><i></i></li>

            <li>
                <label>&nbsp;</label><input name="" type="submit" value="确认发布"/>
                &nbsp;&nbsp;<input name="" type="button" onclick="goback();" value="返回列表"/>
            </li>
        </ul>
    </form>
</div>
<script type="text/javascript">
    function goback(){
        window.location.href="${pageContext.request.contextPath}/findNewsByPage";
    }
    function checkValue() {
        let str = document.getElementById("title").value;
        if (str.length < 1) {
            alert("新闻标题");
            document.getElementById("title").focus();
            return false;
        }
        str = document.getElementById("categoryId").value;
        if (str === '') {
            alert("请选择新闻类别");
            return false;
        }
        str = document.getElementById("contentTitle").value;
        if (str.length < 1) {
            alert("新闻内容页标题");
            document.getElementById("contentTitle").focus();
            return false;
        }
        str = document.getElementById("content").value;
        if (str.length < 1) {
            alert("请输入新闻内容");
            document.getElementById("content").focus();
            return false;
        }
        return true;
    }
</script>
</body>
</html>