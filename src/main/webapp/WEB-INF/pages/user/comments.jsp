<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    System.out.print("basepath:"+basePath);
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>我的评论</title>
    <link rel="stylesheet" href="../css/font-awesome.min.css" />
    <link rel="stylesheet" href="../css/emoji.css" />
    <link rel="stylesheet" href="../css/userhome.css" />
    <link rel="stylesheet" href="../css/user.css" />
    <link rel="stylesheet" href="../css/bootstrap.min.css">  
	<script src="../js/jquery-3.1.1.min.js"></script>
	<script src="../js/bootstrap.min.js"></script>

</head>
<body>
<div class="pre-2" id="big_img">
    <img src="http://findfun.oss-cn-shanghai.aliyuncs.com/images/head_loading.gif" class="jcrop-preview jcrop_preview_s">
</div>
<div id="cover" style="min-height: 639px;">
    <div id="user_area">
        <div id="home_header">
            <a href="<%=basePath%>goods/homeGoods">
                <h1 class="logo"></h1>
            </a>
            <a href="<%=basePath%>user/home">
                <div class="home"></div>
            </a>
        </div>
        <!--
            描述：左侧个人中心栏
        -->
        <div id="user_nav">
            <div class="user_info">
                <div class="head_img">
                    <img src="<%=basePath%>img/photo.png">
                </div>
                <div class="big_headimg">
                    <img src="<%=basePath%>img/findfun.png">
                </div>
                <span class="name">${cur_user.username}</span>
                <span class="school">四川大学</span>
                <span class="name">闲置数量：${cur_user.goodsNum}</span>
            </div>
            <div class="home_nav">
                <ul>
                    <a href="../user/myComments">
                        <li class="notice">
                            <div></div>
                            <span>我的评论</span>
                            <strong></strong>
                        </li>
                    </a>
                    <a href="<%=basePath%>user/myNotice">
                        <li class="fri">
                            <div></div>
                            <span>我的记录</span>
                            <strong></strong>
                        </li>
                    </a>
                    <a href="<%=basePath%>user/basic">
                        <li class="set">
                            <div></div>
                            <span>个人设置</span>
                            <strong></strong>
                        </li>
                    </a>
                    <a href="<%=basePath %>goods/publishGoods">
                        <li class="store">
                            <div></div>
                            <span>发布物品</span>
                            <strong></strong>
                        </li>
                    </a>
                    <a href="<%=basePath %>user/allGoods">
                        <li class="second">
                            <div></div>
                            <span>我的闲置</span>
                            <strong></strong>
                        </li>
                    </a>
                </ul>
            </div>
        </div>
        <!--
            描述：右侧内容区域
        -->
        <!-- 我的评论显示 -->
        <div id="user_content">
            <div class="basic">
                    <h1>我的全部评论</h1><hr />
                    <c:forEach var = "item" items = "${myComments}" >
	                    <div class="changeinfo">
	                        <em class="content" style="font-size:large; color:#CD919E">正文：</em>
	                        &nbsp;&nbsp;
	                        <em class="content_1" id ="content" style="font-style:normal;font-size: 15px;">${item.content}</em>
	                        <br/>
	                        <br/>
	                        <div class="commentTime" id="time" style="color:#b4b4b4; margin-left:400px">${item.createAt}</div>
	                        <div class="goodName" id="goodsName" style="color:#CD919E;margin-left:400px;font-size:normal"><a href="<%=basePath%>goods/goodsId/${item.goodsId}" style="color:#CD919E;font-size:normal">${item.goodsName}(戳我)</a></div>
	                    </div>
	                    <hr class="hr2"/>
	                </c:forEach>
            </div>
            <!--
                描述：最右侧，可能认识的人
            -->
<!--             <div class="recommend"> -->
<!--                 <div class="title"> -->
<!--                     <span class="text">可能认识的人</span> -->
<!--                     <span class="change">换一组</span> -->
<!--                     <span class="underline"></span> -->
<!--                 </div> -->
<!--                 <ul> -->
<!--                     <li> -->
<!--                         <a href="" class="head_img"> -->
<%--                             <img src="<%=basePath%>img/photo1.png" /> --%>
<!--                         </a> -->
<!--                         <span>Brudce</span> -->
<!--                         <div class="fa fa-plus-square"></div> -->
<!--                     </li> -->
<!--                     <li> -->
<!--                         <a href="" class="head_img"> -->
<%--                             <img src="<%=basePath%>img/photo2.png" /> --%>
<!--                         </a> -->
<!--                         <span>Graham</span> -->
<!--                         <div class="fa fa-plus-square"></div> -->
<!--                     </li> -->
<!--                     <li> -->
<!--                         <a href="" class="head_img"> -->
<%--                             <img src="<%=basePath%>img/photo3.png" /> --%>
<!--                         </a> -->
<!--                         <span>策马奔腾hly</span> -->
<!--                         <div class="fa fa-plus-square"></div> -->
<!--                     </li> -->
<!--                     <li> -->
<!--                         <a href="" class="head_img"> -->
<%--                             <img src="<%=basePath%>img/photo4.png" /> --%>
<!--                         </a> -->
<!--                         <span>Danger-XFH</span> -->
<!--                         <div class="fa fa-plus-square"></div> -->
<!--                     </li> -->
<!--                     <li> -->
<!--                         <a href="" class="head_img"> -->
<%--                             <img src="<%=basePath%>img/photo5.png" /> --%>
<!--                         </a> -->
<!--                         <span>Keithw</span> -->
<!--                         <div class="fa fa-plus-square"></div> -->
<!--                     </li> -->
<!--                 </ul> -->
<!--             </div> -->
        </div>
    </div>
</div>
</body>
</html>