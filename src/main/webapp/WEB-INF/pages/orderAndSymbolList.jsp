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
	<title>Order Matching System</title>
	<link rel="stylesheet" href="../../css/bootstrap.min.css" />
	<link rel="stylesheet" href="../../css/jquery.bootgrid.min.css" />
	<link rel="stylesheet" href="../../css/bootstrap-datetimepicker.min.css" type="text/css" />
	<script src="../../js/jquery-3.1.1.min.js"></script>
	<script src="../../js/bootstrap.min.js"></script>
	<script src="../../js/jquery.bootgrid.min.js"></script>
	<script src="../../js/bootstrap-datetimepicker.min.js"></script>

	<script>
		function showSymbol() {
            if($("#orderList").css("display")=='block'){
                $("#orderList").css("display","none");
            }
            if($("#sybmolList").css("display")=='none'){
                $("#sybmolList").css("display","block");
            }else{
                $("#orderList").css("display","none");
            }
        }
        function showOrder() {
            if($("#sybmolList").css("display")=='block'){
                $("#sybmolList").css("display","none");
            }
            if($("#orderList").css("display")=='none'){
                $("#orderList").css("display","block");
            }else{
                $("#sybmolList").css("display","none");
            }
        }
	</script>
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
						<a class="navbar-brand" href="<%=basePath%>main/mainPage">Order Matching</a>
					</div>
					<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1" style="margin-left:800px">
						<div class="collapse navbar-collapse"
						id="bs-example-navbar-collapse-1"
						style="right: 60px;">
						<ul class="nav navbar-nav">
							<li><a>${cur_trader.traderName}</a></li>
							<li><a href="<%=basePath%>main/logout">logout</a></li>
						</ul>
					</div>
					</div>
				</div>
			</nav>
		</div>
	</div>
	<div class="row">
		<div class="col-md-12">
			<h2>Order Matching System</h2>
			<!-- <%=basePath %>order/orderList -->
			<!-- <%=basePath %>symbol/symbolList -->
			<a class="btn btn-primary" href="#" onclick="showOrder()">OrderList</a>
			<a class="btn btn-primary" href="#" onclick="showSymbol()">symbolList</a>
			<div class="btn-group"></div>
			<div class="orderList" id="orderList">
				<table id="grid-data" class="table table-condensed table-hover table-striped">
					<thead>
					<tr>
						<th data-column-id="orderId"  data-identifier="true" data-type="numeric">ID</th>
						<th data-column-id="symbol">symbol</th>
						<th data-column-id="traderName">traderName</th>
						<th data-column-id="side">side</th>
						<th data-column-id="qty">Qty</th>
						<th data-column-id="price">price</th>
						<th data-column-id="fok">FOK</th>
						<th data-column-id="cond">condition</th>
						<th data-column-id="sta">status</th>
						<th data-column-id="commands" data-formatter="commands" data-sortable="false">Commands</th>
					</tr>
					</thead>
				</table>
			</div>
			<div class="symbolList" id="sybmolList" style="display: none;">
				<table id="grid-data2" class="table table-condensed table-hover table-striped">
					<thead>
					<tr>
						<th data-column-id="symbol"  data-identifier="true" data-type="numeric">ID</th>
						<th data-column-id="company">company</th>
						<th data-column-id="last_sale">lastSale</th>
						<th data-column-id="change_net">changeNet</th>
						<th data-column-id="share_volume">shareVolume</th>
						<th data-column-id="commands" data-formatter="commands" data-sortable="false">Commands</th>
					</tr>
					</thead>
				</table>
			</div>
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
            url:"<%=basePath %>main/allOrders",
            formatters: {
                "commands": function(column, row)
                {
                    return "<button type=\"button\" class=\"btn btn-xs btn-default command-delete\" data-row-id=\"" + row.orderId + "\">Delete<span class=\"fa fa-trash-o\"></span></button>";
                }
            }
        }).on("loaded.rs.jquery.bootgrid", function()
		{
            grid.find(".command-delete").on("click", function(e)
            {
              		alert("You pressed delete on row: " + $(this).data("row-id"));
             		$.post("<%=basePath %>order/deleteOrder",{orderId:$(this).data("row-id")},function(){
                    	alert("Delete Done");
                    	$("#grid-data").bootgrid("reload");
                }); 
            });
        });
    });

	$(document).ready(function(){
        var grid = $("#grid-data2").bootgrid({
            ajax:true,
            post: function ()
            {
                return {
                    id: "b0df282a-0d67-40e5-8558-c9e93b7befed"
                };
            },
            url:"<%=basePath %>main/allSymbols",
            formatters: {
                "commands": function(column, row)
                {
                    return "<button type=\"button\" class=\"btn btn-xs btn-default command-delete\" data-row-id=\"" + row.symbol + "\">OK<span class=\"fa fa-trash-o\"></span></button>";
                }
            }
        }).on("loaded.rs.jquery.bootgrid", function()
		{
            grid.find(".command-delete").on("click", function(e)
            {
             		$.post("<%=basePath %>main/mainPage1",{symbol:$(this).data("row-id")},function(){
                    	alert("Go to submit an order?");
                    	window.location.href = "<%=basePath %>main/mainPage";
                }); 
            });
        });
    }); 
</script>
</body>
</html>