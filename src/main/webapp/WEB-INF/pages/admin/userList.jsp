<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>Secondhand后台管理系统</title>
	<script src="../js/jquery-3.1.1.min.js"></script>
	<link rel="stylesheet" href="../css/bootstrap.min.css">
	<link rel="stylesheet" href="../css/jquery.bootgrid.min.css">
	<script src="../js/bootstrap.min.js"></script>
	<script src="../js/jquery.bootgrid.min.js"></script>
	<script src="../js/bootstrap-datetimepicker.min.js"></script>
	<link rel="stylesheet" href="../css/bootstrap-datetimepicker.min.css" type="text/css"></link>
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
						<a class="navbar-brand" href="#">Secondhand后台管理</a>
					</div>
					<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
						<ul class="nav navbar-nav">
							<li><a href="<%=basePath %>goods/homeGoods">logout</a></li>
						</ul>
					</div>
				</div>
			</nav>
		</div>
	</div>
	<div class="row">
		<div class="col-md-12">
			<h2>Secondhand用户信息</h2>
			<a class="btn btn-primary" href="<%=basePath %>admin/goodsList">商品信息</a>
			<a class="btn btn-primary" href="#" id="add">添加用户</a>
			<div class="btn-group">
				<button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
					查看接口数据 <span class="caret"></span>
				</button>
				<ul class="dropdown-menu">
					<li><a href="/admin/users?current=1&rowCount=10&sort[sender]=asc&searchPhrase=&id=b0df282a-0d67-40e5-8558-c9e93b7befed" target="_blank">JSON</a></li>
				</ul>
			</div>
			  <div class="nav-wrapper search-bar" style="display:none">
                <form <%-- ng-submit="search()" --%>method="post" class="ng-pristine ng-invalid ng-invalid-required" action="<%=basePath %>admin/allGoods">
                    <div class="input-field">
                        <input id="search" name="str" placeholder="搜点什么吧233..." style="height: 40px;"
                               class="ng-pristine ng-untouched ng-empty ng-invalid ng-invalid-required" <%-- value="<c:out value="${search}"></c:out>" --%>/>
                        <label for="search" class="active">
                            <i ng-click="search()" class="iconfont"></i>
                        </label>
                    </div>
                </form>
            </div>
			<table id="grid-data" class="table table-condensed table-hover table-striped">
				<thead>
				<tr>
					<th data-column-id="id"  data-identifier="true" data-type="numeric">序号</th>
					<th data-column-id="phone">手机号</th>
					<th data-column-id="username">姓名</th>
					<th data-column-id="qq">QQ</th>
					<th data-column-id="createAt">开通时间</th>
					<th data-column-id="goodsNum">商品数量</th>
					<th data-column-id="power">用户权限</th>
					<th data-column-id="commands" data-formatter="commands" data-sortable="false">Commands</th>
				</tr>
				</thead>
			</table>
		</div>
	</div>
</div>
<script>
    $(document).ready(function(){
        var grid = $("#grid-data").bootgrid({
            ajax:true,
            post: function ()
            {
                return {
                    id: "b0df282a-0d67-40e5-8558-c9e93b7befed"
                };
            },
            url:"<%=basePath %>admin/users",
            formatters: {
                "commands": function(column, row)
                {
                    return "<button type=\"button\" class=\"btn btn-xs btn-default command-edit\" data-row-id=\"" + row.id + "\">编辑<span class=\"fa fa-pencil\"></span></button> " +
                        "<button type=\"button\" class=\"btn btn-xs btn-default command-delete\" data-row-id=\"" + row.id + "\">删除<span class=\"fa fa-trash-o\"></span></button>";
                }
            }
        }).on("loaded.rs.jquery.bootgrid", function()
		{
            grid.find(".command-edit").on("click", function(e)
            {
                $(".stumodal").modal();
                $.post("<%=basePath %>admin/getUserInfo",{userId:$(this).data("row-id")},function(str){
                    $("#userId2").val(str.id);
                    $("#userName2").val(str.username);
                    $("#userPower2").val(str.power);
                });
            }).end().find(".command-delete").on("click", function(e)
            {
              		alert("You pressed delete on row: " + $(this).data("row-id"));
             		$.post("<%=basePath %>admin/deleteUser",{userId:$(this).data("row-id")},function(){
                    	alert("删除成功");
                    	$("#grid-data").bootgrid("reload");
                }); 
            });
        });
    });
    $(document).ready(function(){
        $("#add").click(function(){
            $(".addmodal").modal();
        });
    });
</script>

<div class="modal fade stumodal" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
	<div class="modal-dialog modal-sm">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h4 class="modal-title">信息修改</h4>
			</div>
			<form action="<%=basePath %>admin/updateUser" method="post">
				<div class="modal-body">
					<div class="form-group">
						<label for="userId2">userId</label>
						<input type="text" name="userId" class="form-control" id="userId2" readonly="true">
					</div>
					<div class="form-group">
						<label for="userName2">userName</label>
						<input type="text" name="userName" class="form-control" id="userName2" readonly="true">
					</div>
					<div class="form-group">
						<label for="userPower2">userPower</label>
						<input type="text" name="userPower" class="form-control" id="userPower2">
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<button type="submit" class="btn btn-primary">Save</button>
				</div>
			</form>
		</div>
	</div>
</div>

<div class="modal fade addmodal" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
	<div class="modal-dialog modal-sm">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h4 class="modal-title">添加用户</h4>
			</div>
			<form action="<%=basePath %>admin/addUser" method="post">
				<div class="modal-body">
					<div class="form-group">
						<label for="userName1">userName</label>
						<input type="text" name="userName" class="form-control" id="userName1">
					</div>
					<div class="form-group">
						<label for="userPhone1">userPhone</label>
						<input type="text" name="userPhone" class="form-control" id="userPhone1">
					</div>
					<div class="form-group">
						<label for="userPassword1">userPassword</label>
						<input type="password" name="userPassword" class="form-control" id="userPassword1">
					</div>
					<div class="form-group">
						<input type="hidden" name="Id" class="form-control" id="Id">
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<button type="submit" class="btn btn-primary">Add User</button>
				</div>
			</form>
		</div>
	</div>
</div>
</body>
</html>