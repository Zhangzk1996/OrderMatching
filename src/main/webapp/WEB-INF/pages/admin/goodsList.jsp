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
			<h2>Secondhand商品信息</h2>
			<a class="btn btn-primary" href="<%=basePath %>admin/userList">用户信息</a>

			  <div class="nav-wrapper search-bar" style="display:none">
                <form <%-- ng-submit="search()" --%>method="post" class="ng-pristine ng-invalid ng-invalid-required" action="<%=basePath %>admin/goodsList">
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
					<th data-column-id="catelogId">商品类别</th>
					<th data-column-id="userId">发布者</th>
					<th data-column-id="name">名称</th>
					<th data-column-id="polishTime">上架时间</th>
					<th data-column-id="endTime">下架时间</th>
					<th data-column-id="price">价格</th>
					<th data-column-id="describle">简介</th>
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
            url:"<%=basePath %>admin/allGoods",
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
                $.post("<%=basePath %>admin/getGoodInfo",{goodId:$(this).data("row-id")},function(str){
                    $("#goodId2").val(str.id);
                    $("#goodPolishTime").val(str.polishTime);
                    $("#goodEndTime").val(str.endTime);
                });
            }).end().find(".command-delete").on("click", function(e)
            {
                alert("You pressed delete on row: " + $(this).data("row-id"));
              	 $.post("<%=basePath %>admin/deleteGood",{goodId:$(this).data("row-id")},function(){
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
						<label for="goodId2">goodId</label>
						<input type="text" name="goodId" class="form-control" id="goodId2" readonly="true">
					</div>
					<div class="form-group">
						<label for="goodPolishTime">polishTime</label>
						<input type="text" name="polishTime" class="form-control" id="goodPolishTime" readonly="true">
					</div>
					<div class="form-group">
						<label for="goodEndTime">endTime</label>
						<input type="text" name="endTime" class="form-control" id="goodEndTime" readonly="true">
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
			<form action="" method="post">
				<div class="modal-body">
					<div class="form-group">
						<label for="userName1">userName</label>
						<input type="text" name="userName" class="form-control" id="userName1">
					</div>
					<div class="form-group">
						<label for="userAge1">userAge</label>
						<input type="text" name="userAge" class="form-control" id="userAge1">
					</div>
					<div class="form-group">
						<label for="userMajor1">userMajor</label>
						<input type="text" name="userMajor" class="form-control" id="userMajor1">
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