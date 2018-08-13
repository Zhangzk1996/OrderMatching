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
    <!-- <meta HTTP-EQUIV="Refresh" CONTENT="0;URL=goods/homeGoods"> -->
</head>
<body>
<h1>Hello World!</h1>
<div class="waterfoo stark-components row">
        <div class="item-wrapper normal">
            <c:forEach var="item" items="${allSymbol}">
                <div class="card col">
                    <a href="<%=basePath%>goods/goodsId/${item.symbol}">
                        <div class="card-image">
                            <img src="../upload/${item.share_volume}" />
                        </div>
                        <div class="card-content item-price"><c:out value="${item.symbol}"></c:out></div>
                        <div class="card-content item-name">
                            <p><c:out value="${item.company}"></c:out></p>
                        </div>
                        <div class="card-content item-location">
                            <p>CITI</p>
                            <p><c:out value="${item.change_net}"></c:out></p>
                        </div>
                    </a>
                </div>
            </c:forEach>
        </div>
    </div>
</body>
</html>
