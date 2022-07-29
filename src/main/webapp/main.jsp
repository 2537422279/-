<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
    <title>新闻发布管理系统</title>
</head>

<frameset rows="88,*" cols="*" frameborder="no" border="0" framespacing="0">
    <frame src="${pageContext.request.contextPath}/top.jsp" name="topFrame" scrolling="no" noresize="noresize" id="topFrame" title="topFrame" />
    <frameset cols="187,*" frameborder="no" border="0" framespacing="0">
        <frame src="${pageContext.request.contextPath}/left.jsp" name="leftFrame" scrolling="no" noresize="noresize" id="leftFrame" title="leftFrame" />
        <frame src="${pageContext.request.contextPath}/right.jsp" name="rightFrame" id="rightFrame" title="rightFrame" />
    </frameset>
</frameset>
<noframes><body>
</body></noframes>
</html>
