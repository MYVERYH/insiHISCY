<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'EditUser.jsp' starting page</title>

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
<style type="text/css">
body {
	background-color: #ddd;
	padding-top: 6%;
}

.layui-form {
	background: white;
	padding: 8% 8% 8% 0;
}
</style>
</head>

<body>
	<div class="layui-container">
		<div class="layui-card">
			<div class="layui-card-header" style="font-size: 20px;padding: 0.6%;">修改密码</div>
			<div class="layui-card-body" style="padding-left: 20%;padding-right: 20%;">
				<form id="formUserInfo" class="layui-form" method="post"
						action="${ctx}/servlet/MainServlet">
						<input style="display: none" type="text" name="type" id="type"
							value="editUser">
						<div class="layui-form-item">
							<label class="layui-form-label">用户名</label>
							<div class="layui-input-block">
								<input type="text" name="userName" value="${user.userName}"
									required lay-verify="required" autocomplete="off"
									class="layui-input" readonly="readonly">
							</div>
						</div>
						<div class="layui-form-item">
							<label class="layui-form-label">原密码</label>
							<div class="layui-input-block">
								<input type="password" name="oldPassword" required
									autocomplete="off" class="layui-input">
							</div>
						</div>
						<div class="layui-form-item">
							<label class="layui-form-label">新密码</label>
							<div class="layui-input-block">
								<input type="password" name="newPassword" required
									autocomplete="off" class="layui-input">
							</div>
						</div>
						<div class="layui-form-item">
							<label class="layui-form-label">确认密码</label>
							<div class="layui-input-block">
								<input type="password" name="userPassword" required
									autocomplete="off" class="layui-input">
							</div>
						</div>
						<div class="layui-form-item"
							style="text-align: center;margin-left: 16%;">
							<div class="layui-btn-container">
								<button type="button" class="layui-btn" lay-submit id="confirm">确认</button>
								<button type="button" class="layui-btn layui-btn-danger"
									onclick="modHide();">取消</button>
							</div>
						</div>
					</form>
				<div
					class="layui-col-lg6 layui-col-md6 layui-col-sm6 layui-col-md-offset3">
					
				</div>
			</div>
		</div>		
	</div>
	<script type="text/javascript"
		src="${ctx}/Content/js/jquery-2.0.3.min.js"></script>
	<script type="text/javascript" src="${ctx}/Content/js/jquery.form.js"></script>
	<script type="text/javascript" src="${ctx}/Content/js/echoform.js"></script>
	<script type="text/javascript" src="${ctx}/Content/layui/layui.js"></script>
	<script type="text/javascript">
		layui.use('layer', function(){
			layer = layui.layer;//layer弹出框
			$("#confirm")
				.click(
						function() {
							var userName = $("#formUserInfo [name='userName']")
									.val();
							var oldPassword = $(
									"#formUserInfo [name='oldPassword']").val();
							var newPassword = $(
									"#formUserInfo [name='newPassword']").val();
							var userPassword = $(
									"#formUserInfo [name='userPassword']")
									.val();
							if (userName != "" && oldPassword != ""
									&& newPassword != "" && userPassword != "") {
								if (newPassword == userPassword) {
									$("#formUserInfo")
											.ajaxSubmit(
													function(data) {
														if (data != null
																&& data != "") {
															data = JSON
																	.parse(data);
															if (data.state == true) {
																layer
																		.alert(
																				data.msg,
																				{
																					icon : 1,
																					title : '提示'
																				},
																				function(
																						layerIndex) {
																					layer
																							.close(layerIndex);
																					$(
																							"#formUserInfo [name='oldPassword']")
																							.val(
																									"");
																					$(
																							"#formUserInfo [name='newPassword']")
																							.val(
																									"");
																					$(
																							"#formUserInfo [name='userPassword']")
																							.val(
																									"");
																				});
															} else {
																layer
																		.alert(
																				data.msg,
																				{
																					icon : 0,
																					title : '提示'
																				});
															}
														}
													});
								} else {
									layer.alert("两次密码输入不一致！", {
										icon : 0,
										title : '提示'
									});
								}
							}
						});
		});
		
	</script>
</body>
</html>
