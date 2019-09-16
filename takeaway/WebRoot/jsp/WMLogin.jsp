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
  	<link rel="stylesheet" href="${ctx}/Content/layui/css/layui.css" type="text/css"></link>
  	<style type="text/css">
  		*{
  			padding: 0;
  			margin: 0;
  		}
  		body{
  			background: url("${ctx}/Content/images/Login.jpg") center no-repeat;
  			background-size: 100% 100%;  			
  		}
  		.layui-container{
  			width: 100%;  		  			
  		}
		.layui-form{
			background: white;
			padding: 8% 8% 8% 0;
		}
		.layui-form-checkbox{
			display: inline-table;
		}
		.layui-row h1{
			margin-top: 8%;
			color: white;
			font-size: 120px;
			font-family: FZShuTi;
		}
		.layui-row h1 span{
			color: red;			
		}
		.layui-row p{
			font-size: 18px;
			font-family: cursive;
			color: #999!important;
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
				if ("userNames".equals(cookie.getName())) {
					userName = URLDecoder
							.decode(cookie.getValue(), "utf-8");
				}
				if ("userPasswords".equals(cookie.getName())) {
					userPassword = URLDecoder
							.decode(cookie.getValue(), "utf-8");
				}
			}
		}
	%>
    <div class="layui-container">
    	<div class="layui-row" style="cursor: pointer;">
    		<div class="layui-col-lg6 layui-col-md6 layui-col-sm6 layui-col-md-offset1">
    			<h1>麻 辣 <span>小 龙 虾</span></h1>
    			<p>麻辣小龙虾又名口味虾、长沙口味虾、香辣小龙虾，是湖南著名的地方小吃。
				麻辣小龙虾以小龙虾为主材，配以辣椒、花椒和其他香辛料制成。成菜后，色泽红亮，口味辣并鲜香。
				小龙虾学名克氏原螯虾，原产自北美洲，1918年由美国引入日本，1929年再由日本引入中国，生长在中国南方的河湖池沼中。
				改革开放以来，随着湖南人遍布全国推广湘菜，特别是湖南卫视的传播，麻辣小龙虾（口味虾）风靡一时，众多演艺明星来湖南做节目时必然忘不了吃麻辣小龙虾。
				20世纪末，麻辣小龙虾开始传遍全国，成为人们夏夜街边啤酒摊的经典小吃。</p>
    		</div>    		
    	</div>
    	<div class="layui-row" style="margin-top: 3%;">
    		<div class="layui-col-lg3 layui-col-md3 layui-col-sm3 layui-col-md-offset6">
    			<form id="fmLogin" class="layui-form" action="${ctx}/servlet/MainServlet">
    				<input type="text" style="display:none" name="type" value="wMLogin">
    				<div class="layui-form-item">
    					<div class="layui-col-lg3 layui-col-md3 layui-col-sm3 layui-col-md-offset1">
    						<h2 style="font-weight: 600;color: #999!important;">欢迎登录</h2>
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
	    			<div class="layui-form-item" pane>
	    				<div class="layui-input-block">
							<input type="checkbox" name="rememberMe" title="自动登录" lay-skin="primary">
						</div>
	    			</div>
	    			<div class="layui-form-item">
	    				<div class="layui-input-block">
	    					<button type="button" class="layui-btn" id="btnLogin" lay-submit style="width: 100%;">登 录</button>
	    				</div>						
					</div>
					<div class="layui-form-item">
						<div class="layui-input-block">
							<div class="layui-col-lg5 layui-col-md5 layui-col-sm5">
								<div style="margin: 0;float: left;cursor: pointer;">
									<a href="" style="color: #999!important;">忘记登录密码</a>
								</div>
							</div>
							<div
								class="layui-col-lg3 layui-col-md3 layui-col-sm3 layui-col-md-offset4">
								<div style="margin: 0;float: right;cursor: pointer;">
									<a href="${ctx}/jsp/Register.jsp" style="color: orange;">免费注册</a>
								</div>
							</div>
						</div>
					</div>
	    		</form>
    		</div>    		
    	</div>
    </div>    
    <script type="text/javascript" src="${ctx}/Content/js/jquery-2.0.3.min.js"></script>
    <script type="text/javascript" src="${ctx}/Content/js/jquery.form.js"></script>
    <script type="text/javascript" src="${ctx}/Content/layui/layui.js"></script>
    <script type="text/javascript">
    	layui.use('form', function(){
		  var form = layui.form; //只有执行了这一步，部分表单元素才会自动修饰成功
		});
		$("#btnLogin").click(function() {
			$("#fmLogin").ajaxSubmit(function(data) {
				data = JSON.parse(data);
				if (data.state == true) {
					window.location.href = "${ctx}/servlet/MainServlet?type=onWMMain&";					
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
