<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'FrontDesk.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" href="${ctx}/Content/layui/css/layui.css"
		type="text/css" media="all"></link>
	<link rel="stylesheet" type="text/css" href="${ctx}/Content/css/ckeditor/style.css" />
	<style>
        body {
            background-image: url("${ctx}/Content/images/maindesk.png");
            background-attachment: fixed !important;
            background-size: 100% 100% !important;
            font-size: 22px;
            padding-left: 20%;
        }

        .layui-container {
        	width: 100%;
            padding-right: 15px;
            padding-left: 15px;
            margin-right: auto;
            margin-left: auto;
        }

        .header-grids {
            margin-top: 12em;
            padding-left: 10%;
        }

        .header-grid-img img {
            width: 111%;
            border-radius: 36%;
        }
        .layui-layer-btn a{
        	font-size: 14px;
        }
        .loginOut{
        	margin-left: 98.5%;
    		margin-top: 5px;
        }
        .header-grid-info{
        	width: 20%;
    		margin-right: 2%;
        }
    </style>
  </head>
  
  <body>
    <div class="layui-container">
        <div class="loginOut">
            <a href="javascript:;" onclick="logOut();">
                <img src="${ctx}/Content/images/LoginOut.png" width="33" height="34" />
            </a>
        </div>
		<div class="header-grids">
			<div class="layui-col-md3 header-grid-info">
				<a href="javascript:;">
					<div class="header-grid gray">
						<div class="header-grid-img gray-grid">
							<img src="${ctx}/Content/images/13.png" />
							<h3>前台</h3>
						</div>
					</div> </a>
			</div>
			<div class="layui-col-md3 header-grid-info">
				<a href="${ctx}/servlet/MemberServlet?type=showMemberManagement">
					<div class="header-grid">
						<div class="header-grid-img">
							<img src="${ctx}/Content/images/11.png" />
							<h3>会员管理</h3>
						</div>
					</div> </a>
			</div>
			<div class="layui-col-md3 header-grid-info">
				<a href="${ctx}/servlet/OrderListServlet?type=showOrderList">
					<div class="header-grid blue">
						<div class="header-grid-img blue-grid">
							<img src="${ctx}/Content/images/14.png" />
							<h3>订单管理</h3>
						</div>
					</div> </a>
			</div>
		</div>
	</div>
    <script type="text/javascript"
		src="${ctx}/Content/js/jquery-2.0.3.min.js"></script>
    <script type="text/javascript" src="${ctx}/Content/layui/layui.js"></script>
	<script type="text/javascript">
		var layer;
		layui.use([ 'layer' ], function() {
			layer = layui.layer;
		});
		function logOut() {
			layer
					.confirm(
							"确定要退出吗?",
							{
								icon : 3,
								btn : [ '确定', '取消' ]
							},
							function(layerIndex) {
								layer.close(layerIndex);//关闭提示框
								window.location.href = "${ctx}/servlet/MainServlet?type=logOut&sort=next";
							});
		}
	</script>
</body>
</html>
