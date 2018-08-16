<%@ page language="java"  contentType="text/html; charset=UTF-8"  import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();

    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    request.getSession().getAttribute("ifhave");
    System.out.println(basePath);
    System.out.print( request.getSession().getAttribute("ifhave"));
%>
<html>
<head>
    <title>Welcome</title>
    <meta HTTP-EQUIV="Refresh" CONTENT="0;URL=main/login"> 
</head>
<body>
</body>
</html>
