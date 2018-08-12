<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    System.out.print("path:"+path+";"+"basePath:"+basePath); 
//     System.out.print(cur_user);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SCU-简易论坛</title>

<link rel="stylesheet" href="<%=basePath%>css/bootstrap.css" type="text/css" />
<link rel="stylesheet" href="<%=basePath%>css/font-awesome.min.css" type="text/css" />
<link rel="stylesheet" href="<%=basePath%>css/app.css" type="text/css" />
<link rel="stylesheet" href="<%=basePath%>css/font.css" type="text/css" />

<script src="<%=basePath%>js/jquery-1.11.1.js"></script>
<script src="<%=basePath%>js/bootstrap.js"></script>
<script>
	function replyPost(id) {
		$("#postId").val(id);
		var postId = $("#postId").val();
		if($("#replyModal").css("display")=='block'){
			$("#replyModal").css("display","none");
		}
		if($("#replyModal").css("display")=='none'){
			$("#replyModal").css("display","block");
		}
		else{
			$("#replyModal").css("display","none");			
		}
	}
</script>

</head>
<body>
	<div style="float:left; margin-left:10px">
		<a href="<%=basePath%>goods/homeGoods"><img src="<%=basePath %>img/backhome.png" style=""/></a>
<!-- 		<div style=" border-left:1px gray solid; height:1000px; width:1px;"></div> -->
	</div>
	
	<div class="container" style="margin-top: 20px;">
		<!-- .comment-list -->
		<div class="m-b b-l m-l-md streamline" ng-repeat="item in postList" on-finish-render="ngRepeatFinished">
			<c:if test="${!empty cur_user}">
				<c:forEach var = "item" items="${postLists }">
					<div class="post">
						<a class="pull-left m-l-n-md">
							<div>${item.authorName}</div>
						</a>
						<div class="m-l-lg panel b-a">
							<div class="panel-heading pos-rlt b-b b-light">
								<span class="arrow left"></span> <span>${item.content}</span> 
								<c:if test="${item.userId == cur_user.id }">
									<a class="label bg-info m-l-xs" href="${pageContext.request.contextPath }/wbPost/delete?itid=${item.id}">Delete</a>
								</c:if>
								<span class="text-muted m-l-sm pull-right"> 
									<i class="fa fa-clock-o"></i> ${item.createTime}
								</span>
							</div>
							<div class="panel-body">
								<div class="">
									${item.likes} 
									<a class="btn btn-default btn-xs" href="${pageContext.request.contextPath }/wbPost/likes?itid=${item.id}&flag=true"> 
										<i class="fa fa-star text-danger" ng-if="item.liked"></i> Like
									</a> 
									<a class="btn btn-default btn-xs" data-toggle="modal"data-target="#replyModal" onclick="replyPost(${item.id})">
										<i class="fa fa-mail-reply text-muted" ></i>
										Reply
									</a>
								</div>
							</div>
						</div>
		
					</div>
				
					<!-- .comment-reply -->
					<div class="m-l-lg" ng-repeat="cItem in item.commentList">
						<c:forEach var="comnentItem" items="${item.commentList }">
							<a class="pull-left m-l-n-sd">
								<div>${comnentItem.cAuthorName}</div>
							</a>
							<div class="m-l-xxl panel b-a">
								<div class="panel-heading pos-rlt">
									<span class="arrow left pull-up"></span> ${comnentItem.cContent} <span
										class="text-muted m-l-sm pull-right"> <i
										class="fa fa-clock-o"></i> ${comnentItem.cCreateTime}
									</span>
				
								</div>
							</div>
						</c:forEach>
					</div>
				</c:forEach>
			</c:if>
		</div>
		
		<div class="clearfix m-b-lg">
	
			<div class="m-l-xxl">
				<form class="m-b-none" action="<%=basePath %>wbPost/newWbPost" method="post">
					<div class="input-group">
						<input type="text" class="form-control input-lg" ng-model="post.content" placeholder="说点什么吧。。。。" name="postContent">
						<span class="input-group-btn">
							<button class="btn btn-info btn-lg" type="submit"
								ng-click="addPost()">POST</button>
						</span>
					</div>
				</form>
			</div>
		</div>
	</div>

			
	<div class="modal fade" id="replyModal" tabindex="-1" role="dialog" aria-hidden="true">
		<form action="<%=basePath %>wbcomments/addWbComments" method="post" style="width:60%; margin-left:280px;">
			<br/>
			<div style="float:left; width:700px;">
				<input type="text" style="display:none" value="" id="postId" class="postId" name="postId"/>
				<textarea rows=5 name = "commentsContent" class="form-control" ng-model="comment.cContent" placeholder="回复点啥吧。。。。"></textarea>
			</div>
			<div style="margin-top:40px;"><input type="submit" value="提交" class="btn btn-info"/></div>
		</form>
	</div>
</body>
</html>