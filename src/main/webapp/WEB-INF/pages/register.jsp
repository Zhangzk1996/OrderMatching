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
    <link rel="stylesheet" type="text/css" href="../css/xcConfirm.css"/>
    <title>
        Register
    </title>
    <script src="../js/jquery-3.3.1.js" type="text/javascript"></script>
    <script src="../js/common.js" type="text/javascript"></script>
    <script src="../js/bootstrap.js" type="text/javascript"></script>
    <script src="../js/jquery-1.9.1.js" type="text/javascript" charset="utf-8"></script>
    <script src="../js/xcConfirm.js" type="text/javascript" charset="utf-8"></script>


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
                        </div>
                    </nav>
                </div>
            </div>
    <div class="container" style="width: 360px; border:1px solid black; margin-top: 100px; border-radius: 5px; border-color: #00008B">
        <h1 class="text-center">
            Register
        </h1>
        <!-- action="../trader/addTrader" -->
        <form:form  class="form-inline" method="post" onsubmit="return checkRegister()" action="../trader/addTrader" commandName="trader" role="form" style="margin-top: 60px;" id="myForm">
            <div class="form-group" style="padding-bottom: 20px;">
                <div class="col-md-6" style="width: 30px;">Name:</div>                       
                <div class="col-md-6" style="margin-left: 76px;">
                    <input class="form-control" id="traderName" name="traderName" onblur="checkName()" required="true" type="text"/>
                </div>
                <div class="col-md-12">
                <p id="nameError" style="margin-bottom: -2px;color: red;margin-left: 110px;">
                    </p>
                </div>
            </div>
            <div class="form-group" style="padding-bottom: 20px;">
                    <div class="col-md-6" style="width: 30px;">Email:</div>
                    <div class="col-md-6" style="margin-left: 76px;">
                        <input class="form-control" id="email" name="email" onblur="checkEmail(this.value)" required="true" type="text"/>
                    </div>
                    <div class="col-md-12">
                    <p id="emailError" style="margin-bottom: -2px;color: red;margin-left: 110px;">
                        </p>
                    </div>
                </div>
            <div class="form-group" style="padding-bottom: 20px;">
                <div class="col-md-6" style="width: 30px;">Passw:</div>
                <div class="col-md-6" style="margin-left: 76px;">
                    <input class="form-control" id="password" name="password" onblur="checkPassword()" required="true" type="password"/>
                </div>
                <div class="col-md-12">
                <p id="passwordError" style="margin-bottom: -2px;color: red;margin-left: 110px;">
                    </p>
                </div>
            </div>
            <div class="form-group" style="padding-bottom: 20px;">
                <div class="col-md-6" style="width: 30px;">Repas:</div>
                <div class="col-md-6" style="margin-left: 76px;">
                    <input class="form-control" id="rePassword" name="rePassword" onblur="checkRepassword()" required="true" type="password"/>
                </div>
                <div class="col-md-12">
                <p id="rePasswordError" style="margin-bottom: -2px;color: red;margin-left: 110px;">
                    </p>
                </div>
            </div>
            <div class="col-md-6" style="margin-bottom: 20px">
                <input class="btn btn-primary btn-block btn-md" type="submit" value="Register" />
            </div>
            <div class="col-md-6" style="margin-bottom: 18px">
                <a class="btn btn-primary btn-block btn-md" href="../main/login">
                    Have a count
                </a>
            </div>
        </form:form>
    </div>
</div>
<script>
        function showInfomation() {
            var formvalue = $("#myForm").val;
            console.log(formvalue);
            alert(formvalue);
            console.log("hhhhhhhhhhhhh");
            
        }
    </script>
</body>
</html>