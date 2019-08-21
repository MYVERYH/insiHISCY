<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page language="java" import="java.net.URLDecoder"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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

<title>My JSP 'EmployeeData.jsp' starting page</title>

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
* {
	padding: 0;
	margin: 0;
}

.layui-container {
	width: 100%;
}

.layui-card-header {
	font-size: 26px;
	padding: 1%;
}

.layui-table {
	width: 100%;
}

#modStaff .layui-form {
	padding-top: 6%
}

.layui-layer-title .layui-icon {
	font-size: 18px;
}

.layui-form .layui-anim {
	max-height: 138px;
	height: auto;
}
</style>
<script type="text/javascript"
	src="${ctx}/Content/js/jquery-2.0.3.min.js"></script>
<script type="text/javascript" src="${ctx}/Content/js/jquery.form.js"></script>
<script type="text/javascript" src="${ctx}/Content/js/echoform.js"></script>
<script type="text/javascript" src="${ctx}/Content/layui/layui.js"></script>
</head>

<body>
	<div class="layui-container">
		<div class="layui-card">
			<div class="layui-card-header">员工资料</div>
			<div class="layui-card-body">
				<table class="layui-table" id="test"
					lay-data="{
					height: 500, 
					id:'test',
					url:'${ctx}/servlet/StaffServlet?type=tabStaff', 
					page: true,
					toolbar: 'default', //开启工具栏，此处显示默认图标，可以自定义模板，详见文档
					limit: 10,//每页默认显示的数量
					method: 'post', //提交方式 		
					}"
					lay-filter="test">
					<thead>
						<tr>
							<th lay-data="{type: 'checkbox', fixed: 'left'}"></th>
							<th lay-data="{fixed: 'left', type: 'numbers'}">序号</th>
							<th lay-data="{field:'staffNum', align: 'center', sort: true}">编号</th>
							<th lay-data="{field:'staffName', align: 'center', sort: true}">姓名</th>
							<th lay-data="{field:'staffPhone', align: 'center', sort: true}">手机</th>
							<th lay-data="{field:'departmentTell', align: 'center', sort: true}">电话</th>							
							<th
								lay-data="{field:'departmentName', align: 'center', sort: true}">部门</th>
							<th lay-data="{field:'positionName', align: 'center', sort: true}">职位</th>
							<th lay-data="{field:'staffAddress', align: 'center', sort: true}">地址</th>
							<th lay-data="{field:'staffRemark', align: 'center', sort: true}">备注</th>
						</tr>
					</thead>
				</table>
			</div>
		</div>
		<div id="modStaff" style="display: none;">
			<div
				class="layui-col-lg9 layui-col-md9 layui-col-sm9 layui-col-md-offset1">
				<form id="formStaff" class="layui-form"
					action="${ctx}/servlet/StaffServlet">
					<input type="text" style="display:none" name="type"
						value="addEidtSave"> <input type="text"
						style="display:none" name="staffId">
					<div class="layui-form-item">
						<label class="layui-form-label">编号</label>
						<div class="layui-input-block">
							<input type="text" name="staffNum" required lay-verify="required"
								autocomplete="off" class="layui-input" readonly="readonly">
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">姓名</label>
						<div class="layui-input-block">
							<input type="text" name="staffName" required lay-verify="fname"
								autocomplete="off" class="layui-input">
						</div>
					</div>					
					<div class="layui-form-item">
						<label class="layui-form-label">手机</label>
						<div class="layui-input-block">
							<input type="text" name="staffPhone" required lay-verify="phone"
								autocomplete="off" class="layui-input">
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">部门</label>
						<div class="layui-input-block">
							<select name="departmentId" lay-verify="sdepartment" lay-search>
								<option value="0">---请选择---</option>
								<c:forEach items="${departments}" var="department">
									<option value="${department.departmentId}">${department.departmentName}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">职位</label>
						<div class="layui-input-block">
							<select name="positionId" lay-verify="sposition" lay-search>
								<option value="0">---请选择---</option>
								<c:forEach items="${positions}" var="position">
									<option value="${position.positionId}">${position.positionName}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">地址</label>
						<div class="layui-input-block">
							<input type="text" name="staffAddress" lay-verify="fstaffAddress"
								autocomplete="off" class="layui-input">
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">备注</label>
						<div class="layui-input-block">
							<input type="text" name="staffRemark" autocomplete="off"
								class="layui-input">
						</div>
					</div>
					<div class="layui-form-item"
						style="text-align: center;margin-left: 18%;">
						<div class="layui-btn-container">
							<button type="button" class="layui-btn" lay-submit id="btn"
								lay-filter="*">保存</button>
							<button type="button" class="layui-btn layui-btn-danger"
								onclick="modHide();">取消</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		layui
				.use(
						[ 'table', 'layer', 'form' ],
						function() {
							var table = layui.table;//表格
							var layer = layui.layer;//layer弹出框
							var form = layui.form;//表单
							//监听头工具栏事件
							table
									.on(
											'toolbar(test)',
											function(obj) {
												var checkStatus = table
														.checkStatus(obj.config.id), data = checkStatus.data; //获取选中的数据
												switch (obj.event) {
												case 'add':
													$("#formStaff").resetForm();//重置表单				  	  
													var rowIndex = table.cache.test.length;
													//rowIndex = Number(rowIndex - 1);
													if (rowIndex > -1) {
														//var num = table.cache.test[rowIndex].staffNum;
														var len = 4;
														num = Number(rowIndex)
																+ Number(1);
														num = num.toString();
														while (num.length < len) {
															num = "0" + num;
														}
														$(
																"#formStaff [name='staffNum']")
																.val(num);
														$(
																"#formStaff [name='departmentId']")
																.val("0");
														$(
																"#formStaff [name='positionId']")
																.val("0");
														$("#btn").empty()
																.append("保存");
														layer
																.open({
																	type : 1,//类型
																	title : '<i class="layui-icon layui-icon-add-1"> 新增员工信息</i>',
																	offset : [
																			'5%',
																			'30%' ],
																	area : [
																			'660px',
																			'560px' ],//定义宽和高
																	shadeClose : false,//点击遮罩层关闭
																	content : $("#modStaff")
																//打开的内容
																});
													}
													break;
												case 'update':
													if (data.length === 0) {
														layer.msg('请选择一行');
													} else if (data.length > 1) {
														layer.msg('只能同时编辑一个');
													} else {
														$("#btn").empty()
																.append("修改");
														$.ajaxSettings.ansyc = false;
														$
																.getJSON(
																		"${ctx}/servlet/StaffServlet",
																		{
																			type : "findById",
																			staffID : checkStatus.data[0].staffId
																		},
																		function(
																				datas) {
																			if (datas != null) {
																				loadDatatoForm(
																						"formStaff",
																						datas);
																				form
																						.render("select");//重新渲染
																				$(
																						"#formStaff [name='departmentId']")
																						.val(
																								datas.departmentId);
																				layer
																						.open({
																							type : 1,//类型
																							title : '<i class="layui-icon layui-icon-add-1"> 修改员工信息</i>',
																							offset : [
																									'5%',
																									'30%' ],
																							area : [
																									'660px',
																									'560px' ],//定义宽和高
																							shadeClose : false,//点击遮罩层关闭
																							content : $("#modStaff")
																						//打开的内容
																						});
																			}
																		});
													}
													break;
												case 'delete':
													if (data.length === 0) {
														layer.msg("请选择一行数据!");
													} else {
														layer
																.confirm(
																		"请确认是否删除员工信息?",
																		{
																			icon : 3,
																			btn : [
																					'确定',
																					'取消' ]
																		},
																		function(
																				layerIndex) {
																			layer
																					.close(layerIndex);//关闭提示框
																			var ReturnLength = 0;
																			for ( var i = 0; i < data.length; i++) {
																				$
																						.ajax({
																							url : "${ctx}/servlet/StaffServlet?type=delStaff&staffId="
																									+ checkStatus.data[i].staffId, //所需要的列表接口地址（控制器的方法）
																							type : "get",//数据传输通道的类型
																							async : false,
																							dataType : "json",//传输的数据的类型
																							success : function(
																									datas) {//直接理解为回调函数
																								if (datas.state) {
																									ReturnLength++;
																								} else {
																									layer
																											.msg(
																													datas.msg,
																													{
																														icon : 2,
																														skin : "layui-layer-molv"
																													});
																								}
																							}
																						});
																			}
																			if (ReturnLength == data.length) {
																				table
																						.reload("test");
																			}
																		});
													}
													break;
												}
												;
											});
							form
									.verify({
										fname : function(value) {
											if (value.length < 2) {
												return "请输入至少2个字的用户名";
											}
											if (!new RegExp(
													"^[a-zA-Z0-9_\u4e00-\u9fa5\\s·]+$")
													.test(value)) {
												return "用户名不能有特殊字符";
											}
											if (/(^\_)|(\__)|(\_+$)/
													.test(value)) {
												return "用户名首尾不能出现下划线\'_\'";
											}
											if (/^\d+\d+\d$/.test(value)) {
												return "用户名不能全为数字";
											}
										},										
										phone : [ /^1[3|4|5|7|8]\d{9}$/,
												"请输入有效的手机号码" ],
										sdepartment : function(value) {
											if (value == "0" || value == "") {
												return "请选择一个部门";
											}
										},
										sposition : function(value) {
											if (value == "0" || value == "") {
												return "请选择一个职位";
											}
										},
										fstaffAddress : function(value) {
											if (value == ""
													|| value == undefined) {
												return "地址不能为空";
											}
										}
									});
							//监听提交
							form.on('submit(*)', function(data) {
								//ajax
								//console.log(data.elem) //被执行事件的元素DOM对象，一般为button对象
								//console.log(data.form) //被执行提交的form对象，一般在存在form标签时才会返回
								console.log(data.field); //当前容器的全部表单字段，名值对形式：{name: value};获取单个值data.field["title"]
								formSubmit();
								return false;
							});
							function formSubmit() {
								$("#formStaff").ajaxSubmit(
										function(jsonObject) {
											data = JSON.parse(jsonObject);
											if (data.state) {
												modHide();
												layer.alert(data.msg, {
													icon : 1,
													title : '提示'
												}, function(layerIndex) {
													layer.close(layerIndex);
													table.reload("test");
												});
											} else {
												layer.alert(data.msg, {
													icon : 0,
													title : '提示'
												});
											}
										});
							}
						});
		function modHide() {
			$(".layui-layer-close").trigger("click");
		}
	</script>
</body>
</html>
