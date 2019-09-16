<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page language="java" import="java.net.URLDecoder"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'Login.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" href="${ctx}/Content/layui/css/layui.css" type="text/css" media="all"></link>
	<style type="text/css">
		*{
  			padding: 0;
  			margin: 0;
  		}
  		body{
  			background: url("${ctx}/Content/images/12.jpg") no-repeat center;
  			background-size: 100% 100%; 
  		}
  		.layui-container{
  			width: 100%;  			
  		}
  		.layui-row{
  			text-align: center;
  		}
  		.layui-row h1{
  			color: white;
  			margin-top: 3%;
  			font-size: 100px;
  			font-family: FZShuTi;  			
  		}
  		.layui-form{
			background: white;
			padding: 8% 8% 8% 0;
		}
		.layui-form-checkbox{
			display: inline-table;
			position: relative;
			right: 35% !important;
		}
		.layui-form-item img{
			display: inline-block;
		    position: relative;
		    bottom: 10px;
		}
		.layui-form-radio{
			margin-right: 10%;
		}
		.layui-form-radio div{
			font-size: 20px;
		}
	</style>
  </head>
  
  <body>
  	<%
		String userName = "";
		String userPassword = "";
		Cookie[] cookies = request.getCookies();
		Cookie cookie = null;
		if (cookies == null) {
			System.out.println("欢迎初次登录！");
		} else {
			for (int i = 0; i < cookies.length; i++) {
				cookie = cookies[i];
				if ("userName".equals(cookie.getName())) {
					userName = URLDecoder
							.decode(cookie.getValue(), "utf-8");
				}
				if ("userPassword".equals(cookie.getName())) {
					userPassword = URLDecoder
							.decode(cookie.getValue(), "utf-8");
				}
			}
		}
	%>
    <div class="layui-container">
    	<div class="layui-row">
    		<h1>餐 饮 管 理 系 统</h1>
    	</div>
    	<div class="layui-row">
    		<div class="layui-col-lg3 layui-col-md3 layui-col-sm3" style="margin-top: 5%;margin-left: 38%">
    			<form id="fmLogin" class="layui-form" action="${ctx}/servlet/MainServlet" onkeydown="onreturn();">
    				<input type="text" style="display:none" name="type" value="login">
    				<div class="layui-form-item">
    					<div class="layui-col-lg4 layui-col-md4 layui-col-sm4 layui-col-md-offset2">
    						<input type="radio" name="Reception" value="0" title="前台管理">
    					</div>
    					<div class="layui-col-lg6 layui-col-md6 layui-col-sm6">
    						<input type="radio" name="Reception" value="1" title="后台管理" checked>
    					</div>      					
    				</div>
	    			<div class="layui-form-item">
	    				<label class="layui-form-label">
	    					<i class="layui-icon layui-icon-username" style="font-size: 32px;"></i>
	    				</label>
	    				<div class="layui-input-block">
	    					<input type="text" name="userName" value="<%=userName%>" required lay-verify="required" 
	    					placeholder="请输入账户" autocomplete="off" class="layui-input">
	    				</div>
	    			</div>
	    			<div class="layui-form-item">
	    				<label class="layui-form-label">
	    					<i class="layui-icon layui-icon-password" style="font-size: 32px;"></i>
	    				</label>
	    				<div class="layui-input-block">
	    					<input type="password" name="userPassword" value="<%=userPassword%>" required lay-verify="required" 
	    					placeholder="请输入密码" autocomplete="off" class="layui-input">
	    				</div>
	    			</div>
	    			<div class="layui-form-item">
	    				<label class="layui-form-label">
	    					<i class="layui-icon layui-icon-vercode" style="font-size: 32px;"></i>
	    				</label>
	    				<div class="layui-input-inline">
	    					<input type="text" name="validateCode"
	    					placeholder="请输入验证码" autocomplete="off" class="layui-input">
	    				</div>
	    				<div class="layui-form-mid layui-word-aux"><img src="${ctx}/servlet/MainServlet?type=validateCode" id="ValidateCode"/></div>
	    			</div>
	    			<div class="layui-form-item">
	    				<div class="layui-input-block">
							<input type="checkbox" name="rememberMe" title="记住我" lay-skin="primary" checked>
						</div>
	    			</div>
	    			<div class="layui-form-item" style="padding-left: 13%;">
	    				<div class="layui-btn-container">
	    					<button type="button" class="layui-btn" id="btnLogin" lay-submit style="width: 100%;">登 录</button>	    					
	    				</div>	    									
					</div>					
	    		</form>
	    	</div>
    	</div>
    </div>
    <script type="text/javascript" src="${ctx}/Content/js/jquery-2.0.3.min.js"></script>
    <script type="text/javascript" src="${ctx}/Content/layui/layui.js"></script>
    <script type="text/javascript" src="${ctx}/Content/js/jquery.form.js"></script>
    <script type="text/javascript">
    	layui.use('form', function(){
		  var form = layui.form; //只有执行了这一步，部分表单元素才会自动修饰成功
		});
		$("#ValidateCode")
				.click(
						function() {
							$("#ValidateCode").attr("src","${ctx}/servlet/MainServlet?type=validateCode&time=" + new Date());
						});
		$("#btnLogin").click(function() {
			$("#fmLogin").ajaxSubmit(function(data) {
				data = JSON.parse(data);
				console.log(data);
				if (data.state == true) {
					setTimeout(function() {
						var reception = $('#fmLogin [name="Reception"]:checked').val();
						console.log(reception);
						window.location.href = "${ctx}/servlet/MainServlet?type=onMain&Reception=" + reception;
					}, 1000);					
				} else {
					layer.alert(data.msg, {
						icon : 0,
						title : '提示'
					});
				}
			});
		});
		//点击回车键登录
		function onreturn() {
		    if (window.event.keyCode == 13) {
		    	$("#btnLogin").click();
		        //if (document.all('btnSubmit').click());
		    }
		}
    </script>
  </body>
</html>
