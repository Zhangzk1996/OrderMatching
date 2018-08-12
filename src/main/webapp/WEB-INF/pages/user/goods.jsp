<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>我的闲置</title>
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
            <a href="<%=basePath %>goods/homeGoods">
                <h1 class="logo"></h1>
            </a>
            <a href="<%=basePath %>user/home">
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
                    <img src="">
                </div>
                <span class="name">${cur_user.username}</span>
                <span class="school">四川大学</span>
                <span class="name">闲置数量：${cur_user.goodsNum}</span>
<!--                 <span class="fa fa-share-alt">"快去分享一下"</span> -->
            </div>
            <div class="home_nav">
                <ul>
                    <a href="<%=basePath%>user/myComments">
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
                    <a href="<%=basePath %>user/basic">
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
        <div id="user_content">
            <div class="share">
                <!--
                    作者：hlk_1135@outlook.com
                    时间：2017-05-11
                    描述：闲置商品展示
                -->
                <div class="share_content">
                    <c:if test="${empty goodsAndImage}">
                        <div class="no_share">
                            <span>没有任何内容，去逛逛其它的吧！</span>
                        </div>
                    </c:if>
                    <c:if test="${!empty goodsAndImage}">
                        <c:forEach var="items" items="${goodsAndImage}">
                            <div class="story">
                                <a href="<%=basePath%>goods/goodsId/${items.goods.id}" class="head_img">
                                    <img src="../upload/${items.images[0].imgUrl}">
                                </a>
                                <span class="name">${items.goods.name}</span>
                                <span class="text" style="overflow: hidden; outline: none;">${items.goods.describle}</span>
                                <div class="box">
                                    <div class="box_content">
                                        <div class="left_shadow"></div>
                                        <div class="left" index="1" style="display: none;"><</div>
                                        <div class="right_shadow"></div>
                                        <div class="left" index="3" style="display: none;">></div>
                                        <img src="../upload/${items.images[0].imgUrl}" index="2">
                                        <span class="com" style="display: none;left: 396.733px;"></span>
                                    </div>
                                    <div class="interact">
                                       <a href="<%=basePath%>goods/editGoods/${items.goods.id}"> <span class="fa fa-heart">编辑</span></a>
                                        <a href="<%=basePath%>goods/goodsId/${items.goods.id}"><span class="fa fa-commenting">${items.goods.commetNum}</span></a>
                                        <span class="time">${items.goods.startTime}</span>
                                        <a href="<%=basePath%>goods/deleteGoods/${items.goods.id}"><span class="fa fa-trash" style="color:#939cac;">删除</span></a>
                                    </div>
                                    <div class="like_detail">
                                        <div class="like_content">
                                            <span>下架时间：${items.goods.endTime}</span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </c:if>
                </div>
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
<%--                             <img src="<%=basePath%>img/photo1.png"> --%>
<!--                         </a> -->
<!--                         <span>Brudce</span> -->
<!--                         <div class="fa fa-plus-square"></div> -->
<!--                     </li> -->
<!--                     <li> -->
<!--                         <a href="" class="head_img"> -->
<%--                             <img src="<%=basePath%>img/photo2.png"> --%>
<!--                         </a> -->
<!--                         <span>Graham</span> -->
<!--                         <div class="fa fa-plus-square"></div> -->
<!--                     </li> -->
<!--                     <li> -->
<!--                         <a href="" class="head_img"> -->
<%--                             <img src="<%=basePath%>img/photo3.png"> --%>
<!--                         </a> -->
<!--                         <span>策马奔腾hly</span> -->
<!--                         <div class="fa fa-plus-square"></div> -->
<!--                     </li> -->
<!--                     <li> -->
<!--                         <a href="" class="head_img"> -->
<%--                             <img src="<%=basePath%>img/photo4.png"> --%>
<!--                         </a> -->
<!--                         <span>Danger-XFH</span> -->
<!--                         <div class="fa fa-plus-square"></div> -->
<!--                     </li> -->
<!--                     <li> -->
<!--                         <a href="" class="head_img"> -->
<%--                             <img src="<%=basePath%>img/photo5.png"> --%>
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