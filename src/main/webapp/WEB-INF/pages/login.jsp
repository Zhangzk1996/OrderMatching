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
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"></meta>
    <link href="../css/bootstrap.css" rel="stylesheet"/>
    <link href="../css/style.css" rel="stylesheet"/>
    <title>login</title>
    <script type="text/javascript" src="../js/jquery-3.3.1.js" ></script>
    <script type="text/javascript" src="../js/common.js"></script>
    <script type="text/javascript" src="../js/bootstrap.js"></script>
</head>
<body>
<div class="container">
    	<div class="row">
                <div class="col-md-12">
                    <nav class="navbar navbar-inverse">
                        <div class="container-fluid">
                            <div class="navbar-header">
                                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                                    <span class="sr-only">Toggle navigation</span>
                                    <span class="icon-bar">*****</span>
                                </button>
                                <a class="navbar-brand" href="#" style="font-size: x-large;">Order Matching System</a>
                            </div>
                            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                                <!-- <ul class="nav navbar-nav">
                                    <li><a href="<%=basePath %>goods/homeGoods">logout</a></li>
                                    <li><a href="<%=basePath %>goods/homeGoods">logout</a></li>
                                    <li><a href="<%=basePath %>goods/homeGoods">logout</a></li>
                                    <li><a href="<%=basePath %>goods/homeGoods">logout</a></li>
                                </ul> -->
                            </div>
                        </div>
                    </nav>
                </div>
            </div>
    <div class="container" style="width: 360px; border:1px solid black; margin-top: 100px; border-radius: 5px; border-color: #00008B">
    <!-- <div class="col-md-4 col-md-offset-4" id="form-container"> -->
        <!-- ../trader/login -->
        <h1 class="text-center">Login</h1>
        <form:form id="loginForm" class="form-inline" method="post" action="../trader/login"  commandName="trader" role="form" style="margin-top:60px;">
            <div class="form-group" style="padding-bottom: 20px;">
                <!-- <label class="col-md-4" for="telephone">phone</label> -->
                <div class="col-md-6" style="width: 30px;">Email</div>
                <div class="col-md-6" style="margin-left: 76px;">
                    <input class="form-control" id="email" name="email" onblur="checkEmail(this.value)" required="true" type="text"/>
                </div>
                <div class="col-md-12">
                <p id="emailError" style="margin-bottom: -2px;color:red;margin-left: 110px;"></p>
                </div>
            </div>
            <div class="form-group" style="padding-bottom: 8px;">
                <div class="col-md-6" style="width:30px;">PassW</div>
                <div class="col-md-6" style="margin-left: 76px;">
                    <input class="form-control" id="password" name="password" onblur="checkPassword()" required="true" type="password"/>
                </div>
                <div class="col-md-12">
                <p id="passwordError" style="margin-bottom: -2px;color: red;margin-left: 110px;">
                 </p>
                </div>
            </div>
            <div class="form-group" style="margin-bottom: 15px; margin-top: 40px; margin-left: 60px;">
                <div class="col-md-3 col-md-offset-3">
                    <button class="btn btn-primary btn-md" type="submit">
                        Login
                    </button>
                </div>
                <div class="col-md-3 col-md-offset-3">
                    <a class="btn btn-primary btn-md" href="<%=basePath %>main/register">
                       Register
                    </a>
                </div>
            </div>
             <c:if test="${!empty passError}">
                <div class="col-md-12 btn btn-warning btn-md" style="left: 0px; margin-bottom: 10px; margin-top:20px;">
                    ${passError}
               </div>
            	</c:if>
        </form:form>
    <!-- </div> -->
</div>
</div>
</body>
</html>