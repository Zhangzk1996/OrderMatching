<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();

	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	request.getSession().getAttribute("ifhave");
	request.getSession().getAttribute("cur_trader");
	System.out.println(basePath);
	System.out.print(request.getSession().getAttribute("cur_trader"));
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8"></meta>
<link href="../css/bootstrap.css" rel="stylesheet" />
<link href="../css/style.css" rel="stylesheet" />
<link rel="stylesheet" href="../css/style.css">
<link rel="stylesheet" href="../css/bootstrap.min.css" />
<link rel="stylesheet" href="../css/jquery.bootgrid.min.css" />
<link rel="stylesheet" type="text/css"
	href="http://fonts.googleapis.com/css?family=Montserrat:400,700%7CLato%7CRoboto">
<link rel="stylesheet" href="../css/bootstrap-datetimepicker.min.css"
	type="text/css" />
<title>login</title>
<style>
.levelLink:hover {
	text-decoration: none;
}

.levelLink::after {
	text-decoration: none;
}
</style>
<script type="text/javascript" src="../js/jquery-3.3.1.js"></script>
<script src="../js/jquery-3.1.1.min.js"></script>

<script type="text/javascript" src="../js/common.js"></script>
<script type="text/javascript" src="../js/bootstrap.js"></script>
<script src="../js/bootstrap.min.js"></script>
<script src="../js/jquery.bootgrid.min.js"></script>
<script src="../js/bootstrap-datetimepicker.min.js"></script>


<script type="text/javascript" src="../js/core.min.js"></script>
<script type="text/javascript" src="../js/script.js"></script>
<script src="../js/Chart.bundle.js"></script>
<script src="../js/utils.js"></script>
<script>
        function showLevel1(){
            if($("#matchOrderListLevel2").css("display")=='block'){
                $("#matchOrderListLevel2").css("display","none");
            }
            if($("#matchOrderListLevel1").css("display")=='none'){
                $("#matchOrderListLevel1").css("display","block");
            }else{
                $("#matchOrderListLevel2").css("display","none");
            }
        }
        function showLevel2(){
            if($("#matchOrderListLevel1").css("display")=='block'){
                $("#matchOrderListLevel1").css("display","none");
            }
            if($("#matchOrderListLevel2").css("display")=='none'){
                $("#matchOrderListLevel2").css("display","block");
            }else{
                $("#matchOrderListLevel1").css("display","none");
            }
        
        }
    </script>
<script>
            var mydata = [12,23,34,35,56,23,67];
            var label = ["Red", "Blue", "Yellow", "Green", "Purple", "Orange"];
            
            const config = {
                type: 'bar',
                data: {
                    labels: label,
                    datasets: [{
                        
                        label: 'NASDAQ',
                        fill: false,
                        fillColor: "rgba(220,220,220,0.2)",
                        strokeColor: "rgba(220,220,220,1)",
                        pointColor: "rgba(220,220,220,1)",
                        pointStrokeColor: "#fff",
                        pointHighlightFill: "#fff",
                        pointHighlightStroke: "rgba(220,220,220,1)",
                        data: mydata
                    },
                    {
                        label:"test",
                        fill:false,
                        backgroundColor:window.chartColors.blue,
                        borderColor:window.chartColors.blue,
                        data:mydata,
                        type:"line",
                    },
                ]
                },
                options: {
                    responsive: true,
                    title: {
                        display: true,
                        text: 'Features stock market price'
                    },
                    tooltips: {
                        mode: 'index',
                        intersect: false,
                    },
                    hover: {
                        mode: 'nearest',
                        intersect: true
                    },
                    scales: {
                        xAxes: [{
                            display: true,
                            scaleLabel: {
                                display: true,
                                labelString: 'Month'
                            }
                        }],
                        yAxes: [{
                            display: true,
                            scaleLabel: {
                                display: true,
                                labelString: 'Price'
                            }
                        }]
                    }
                }
            };
        
            window.onload = function() {
                const ctx = document.getElementById('canvas').getContext('2d');
                window.myLine = new Chart(ctx, config);
            };
        </script>
</head>
<body>
	<div class="container">
		<nav class="navbar navbar-inverse">
			<div class="container-fluid">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed"
						data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
						aria-expanded="false">
						<span class="sr-only">Toggle navigation</span> <span
							class="icon-bar">*****</span>
					</button>
					<a class="navbar-brand" href="#" style="font-size: x-large;">Order
						Matching System </a>
				</div>
				<div class="collapse navbar-collapse"
					id="bs-example-navbar-collapse-1" style="margin-left: 800px;">
					<div class="collapse navbar-collapse"
						id="bs-example-navbar-collapse-1" style="right: 60px;">
						<ul class="nav navbar-nav">
							<li><a
								href="<%=basePath%>order/traderOrders/${cur_trader.traderName}">My
									Order</a></li>
							<li><a>${cur_trader.traderName}</a></li>
							<li><a href="<%=basePath%>main/logout">logout</a></li>
						</ul>
					</div>
				</div>
		</nav>

		<div class="shell" style="float: left; width: 30%">
			<h1 class="txt-white">Order</h1>
			<div class="range" style="width: 80%; height: 40%;">
				<div class="cell-lg-8 cell-lg-preffix-2">
					<!-- RD Mailform-->
					<form:form class="rd-mailform text-left form-search" method="post"
						action="../order/addOrder" commandName="order" role="form">
						<div class="range offset-top-22">
							<div class="cell-sm-6 offset-top-45 offset-sm-top-0">
								<div class="form-group">
									<label for="traderName" class="form-label">traderName</label> <input
										id="traderName" type="text" name="traderName"
										data-constraints="@Required" placeholder="traderName"
										class="form-control"
										value=${cur_trader.traderName
										} readOnly=true>
								</div>
							</div>
							<div class="cell-sm-6">
								<div class="form-group">
									<label for="symbol" class="form-label">symbol</label> <select
										id="symbol" name="symbol" data-placeholder="symbol"
										data-minimum-results-for-search="Infinity"
										class="form-control select-filter">
										<option>-</option>
										<option>AABA</option>
										<option>AAPL</option>
										<option>AMD</option>
										<option>AMZN</option>
										<option>ARKR</option>
									</select>
								</div>
							</div>
							<div class="cell-sm-6 offset-top-45">
								<div class="form-group date">
									<label for="side" class="form-label">side</label> <select
										id="side" name="side" data-placeholder="-"
										data-minimum-results-for-search="Infinity"
										class="form-control select-filter">
										<option>-</option>
										<option>-</option>
										<option>sell</option>
										<option>buy</option>
									</select>
								</div>
							</div>
							<div class="cell-sm-6 offset-top-45">
								<div class="form-group date">
									<label for="qty" class="form-label">Qty</label> <input id="qty"
										name="qty" type="text" data-time-picker="date"
										placeholder="qty" data-constraints="@Required"
										class="form-control"><span
										class="material-icons-event icon icon-md icon-primary"></span>
								</div>
							</div>
							<div class="cell-sm-6 offset-top-45">
								<div class="form-group date">
									<label for="price" class="form-label">price</label> <input
										id="price" name="price" type="text" data-time-picker="date"
										placeholder="price" data-constraints="@Required"
										class="form-control"><span
										class="material-icons-event icon icon-md icon-primary"></span>
								</div>
							</div>
							<div class="cell-xs-6 cell-md-3 offset-top-45">
								<div class="form-group">
									<!--Select 2-->
									<label for="fok" class="form-label">FOK</label> <select
										id="fok" name="fok" data-placeholder="-"
										data-minimum-results-for-search="Infinity"
										class="form-control select-filter">
										<option>-</option>
										<option>FILL</option>
										<option>KILL</option>
									</select>
								</div>
							</div>
							<div class="cell-xs-6 cell-md-3 offset-top-45">
								<div class="form-group">
									<!--Select 2-->
									<label for="cond" class="form-label">condition</label> <select
										id="cond" name="cond" data-placeholder="-"
										data-minimum-results-for-search="Infinity"
										class="form-control select-filter">
										<option>-</option>
										<option>Limit Order</option>
										<option>Market Order</option>
									</select>
								</div>
							</div>
							<div class="cell-xs-12 offset-top-37">
								<button type="submit" data-text="search"
									class="btn btn-sm btn-primary">
									<span>Add</span>
								</button>
							</div>
						</div>
					</form:form>
				</div>
			</div>
		</div>
		<div id="mydiv2" class="mydiv2"
			style="width: 1px; height: 1000px; border: 1px solid black; float: left; margin-left: 6px;"></div>
		<div
			style="width: 66%; float: left; margin-top: 20px; margin-left: 20px;"
			id="canvasChart">
			<canvas id="canvas" width="100" height="50px"></canvas>
			<div style="width: 100%; border: 1px gray solid; float: left"></div>
			<div
				style="border: 1px solid gray; width: 100%; text-align: center; margin-top: 20px; border-radius: 5px; font-size: x-large; color: dodgerblue; background-color: rgba(220, 220, 220, 0.2)">
				<a href="#" class="levelLink" id="FirstLevel" onclick="showLevel1()">Level
					1</a> <span style="size: 2px; height: 100%">|</span> <a href="#"
					class="levelLink" id="SecondLevel" onclick="showLevel2()">Level
					2</a>
			</div>
			<div class="matchOrderList" id="matchOrderListLevel1"
				style="margin-top: 20px;">
				<table id="grid-data"
					class="table table-condensed table-hover table-striped">
					<thead>
						<tr>
							<th data-column-id="matchOrderId" data-identifier="true"
								data-type="numeric">ID</th>
							<th data-column-id="symbol">Symbol 1</th>
							<th data-column-id="bid_price">Bid</th>
							<th data-column-id="bid_size">BidSize</th>
							<th data-column-id="ask_price">Ask</th>
							<th data-column-id="ask_size">AskSize</th>
							<th data-column-id="commands" data-formatter="commands"
								data-sortable="false">Commands</th>
						</tr>
					</thead>
				</table>
			</div>
			<div class="matchOrderList" id="matchOrderListLevel2"
				style="margin-top: 20px; display: none">
				<table id="grid-data2"
					class="table table-condensed table-hover table-striped">
					<thead>
						<tr>
							<th data-column-id="matchOrderId" data-identifier="true"
								data-type="numeric">ID</th>
							<th data-column-id="symbol">Symbol 2</th>
							<th data-column-id="bid_price">Bid</th>
							<th data-column-id="bid_size">BidSize</th>
							<th data-column-id="ask_price">Ask</th>
							<th data-column-id="ask_size">AskSize</th>
							<th data-column-id="commands" data-formatter="commands"
								data-sortable="false">Commands</th>
						</tr>
					</thead>
				</table>
			</div>
		</div>
		<div style="width: 100%; border: 1px gray solid; float: clear"></div>

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
            url:"#",
			// <%=basePath%>order/orderList
            formatters: {
                "commands": function(column, row)
                {
                    return "<button type=\"button\" class=\"btn btn-xs btn-default command-delete\" data-row-id=\"" + row.id + "\">Delete<span class=\"fa fa-trash-o\"></span></button>";
                }
            }
        }).on("loaded.rs.jquery.bootgrid", function()
		{
            grid.find(".command-delete").on("click", function(e)
            {
              		alert("You pressed delete on row: " + $(this).data("row-id"));
             		$.post("<%=basePath%>matchOrder/deleteMatchOrder",{userId:$(this).data("row-id")},function(){
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
            url:"#",
			// <%=basePath%>order/orderList
            formatters: {
                "commands": function(column, row)
                {
                    return "<button type=\"button\" class=\"btn btn-xs btn-default command-delete\" data-row-id=\"" + row.id + "\">Delete<span class=\"fa fa-trash-o\"></span></button>";
                }
            }
        }).on("loaded.rs.jquery.bootgrid", function()
		{
            grid.find(".command-delete").on("click", function(e)
            {
              		alert("You pressed delete on row: " + $(this).data("row-id"));
             		$.post("<%=basePath%>
		matchOrder/deleteMatchOrder",
																					{
																						userId : $(
																								this)
																								.data(
																										"row-id")
																					},
																					function() {
																						alert("Delete Done");
																						$(
																								"#grid-data")
																								.bootgrid(
																										"reload");
																					});
																});
											});
						});
	</script>
</body>
</html>