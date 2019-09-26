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
    
    <title>My JSP 'BaseInfo.jsp' starting page</title>
    
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
		
		.layui-card-body .layui-card {
			margin-bottom: 5px;
		}
		
		.layui-col-lg9 .layui-form {
			padding-top: 6%
		}
		
		.layui-layer-title .layui-icon {
			font-size: 18px;
		}		
		
		.layui-form .layui-anim {
			max-height: 118px;
			height: auto;
		}
		
		.layui-card .layui-card-header{
			font-size: 26px;
			padding: 26px 0 10px 26px;
		}
		.layui-btn i>span{
			position: relative;
			bottom: 1.5px;
			font-size: 14px;
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
    	<div class="layui-tab layui-tab-brief">
			<ul class="layui-tab-title">
				<li class="layui-this">供应商资料</li>
				<li>仓库资料</li>
				<li>部门资料</li>
			</ul>
			<div class="layui-tab-content" style="padding: 0; height: 88%;">
				<div class="layui-tab-item layui-show">
					<div class="layui-card">
						<div class="layui-card-header">供应商资料信息：</div>
						<div class="layui-card-body" style="padding: 0;">
							<form class="layui-form" action="">
								<div class="layui-form-item" style="padding-top: 16px;">
									<div class="layui-col-lg3 layui-col-md3 layui-col-sm3">
										<label class="layui-form-label">编号:</label>
										<div class="layui-input-block">
											<input type="number" name="num" id="supplierNum"
											autocomplete="off" class="layui-input">
										</div>
									</div>
									<div class="layui-col-lg3 layui-col-md3 layui-col-sm3">
										<label class="layui-form-label">名称:</label>
										<div class="layui-input-block">
											<input type="text" name="name" id="supplierName" 
											 autocomplete="off" class="layui-input" 
											 onKeyUp="chkUper(this)" onafterpaste="chkUper(this)">
										</div>
									</div>
									<div class="layui-col-lg5 layui-col-md5 layui-col-sm5 layui-col-md-offset1">
										<div class="layui-btn-container tableBtn">
											<button type="button" class="layui-btn" data-type="reload">
												<i class="layui-icon layui-icon-search"> <span> 查
														询</span> </i>
											</button>
											<button type="button" class="layui-btn layui-btn-primary"
												data-type="clear">
												<i class="layui-icon layui-icon-delete"> <span>
														清空查询</span> </i>
											</button>
											<button type="button" class="layui-btn layui-btn-normal"
												data-type="getShowSupplier">
												<i class="layui-icon layui-icon-add-1"> <span> 新
														增</span> </i>
											</button>
											<button type="button" class="layui-btn layui-btn-warm"
												data-type="getCheckSupplier">
												<i class="layui-icon layui-icon-edit"> <span> 修
														改</span> </i>
											</button>
											<button type="button" class="layui-btn layui-btn-danger"
												data-type="delCheckSupplier">
												<i class="layui-icon layui-icon-delete"> <span> 删
														除</span> </i>
											</button>
										</div>
									</div>									
								</div>
							</form>													
							<table class="layui-table" id="findSupplier"
								lay-data="{
										height: 471,
										id: 'findSupplier',
										url: '${ctx}/servlet/BaseInfoServlet?type=findBaseInfo&baseType=findSupplier',
										page: true,
										limit: 10,//每页默认显示的数量
										method: 'post', //提交方式 									
										}"
								lay-filter="findSupplier">
								<thead>
									<tr>
										<th lay-data="{type: 'checkbox', fixed: 'left'}"></th>
										<th
											lay-data="{field: 'supplierNum', align: 'center', sort: true}">编号</th>
										<th
											lay-data="{field: 'supplierName', align: 'center', sort: true}">名称</th>
										<th
											lay-data="{field: 'rawMaterialBigName', align: 'center', sort: true}">类别</th>
										<th
											lay-data="{field: 'supplierPrincipal', align: 'center', sort: true}">负责人</th>
										<th
											lay-data="{field: 'supplierLinkman', align: 'center', sort: true}">联系人</th>
										<th
											lay-data="{field: 'supplierPhone', align: 'center', sort: true}">电话</th>
										<th
											lay-data="{field: 'supplierTell', align: 'center', sort: true}">传真</th>
										<th
											lay-data="{field: 'supplierSite', align: 'center', sort: true}">地址</th>
										<th
											lay-data="{field: 'supplierMail', align: 'center', sort: true}">邮编</th>
										<th
											lay-data="{field: 'supplierRemark', align: 'center', sort: true}">备注</th>
									</tr>
								</thead>
							</table>
						</div>
						<div id="modSupplier" style="display: none;">
							<div
								class="layui-col-lg9 layui-col-md9 layui-col-sm9 layui-col-md-offset1">
								<form id="formSupplier" class="layui-form" method="post"
									action="${ctx}/servlet/BaseInfoServlet">
									<input type="text" style="display:none" name="type"
										value="addEidtSave"> 
									<input type="text" style="display:none"
										name="supplierId"> 
									<input type="text" style="display:none"
										name="supplierDetailId">
									<input type="text" style="display:none" name="baseType"
											value="supplier">
									<div class="layui-form-item">
										<label class="layui-form-label">编号</label>
										<div class="layui-input-block">
											<input type="text" name="supplierNum" required
												lay-verify="required" autocomplete="off" class="layui-input"
												readonly="readonly">
										</div>
									</div>
									<div class="layui-form-item">
										<label class="layui-form-label">名称</label>
										<div class="layui-input-block">
											<input type="text" name="supplierName" required
												lay-verify="fname" autocomplete="off" class="layui-input">
										</div>
									</div>
									<div class="layui-form-item">
										<label class="layui-form-label">类型</label>
										<div class="layui-input-block">
											<select name="rawMaterialBigId" lay-verify="srawMaterialBig" lay-search>
												<option value="0">---请选择---</option>
												<c:forEach items="${bigs}" var="big">
													<option value="${big.rawMaterialBigId}">${big.rawMaterialBigName}</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<div class="layui-form-item">
										<label class="layui-form-label">负责人</label>
										<div class="layui-input-block">
											<input type="text" name="supplierPrincipal"  required
												lay-verify="fname" autocomplete="off" class="layui-input">
										</div>
									</div>
									<div class="layui-form-item">
										<label class="layui-form-label">联系人</label>
										<div class="layui-input-block">
											<input type="text" name="supplierLinkman" required
												lay-verify="fname" autocomplete="off" class="layui-input">
										</div>
									</div>
									<div class="layui-form-item">
										<label class="layui-form-label">手机</label>
										<div class="layui-input-block">
											<input type="text" name="supplierPhone"  required
												lay-verify="required|phone" autocomplete="off" class="layui-input">
										</div>
									</div>
									<div class="layui-form-item">
										<label class="layui-form-label">电话</label>
										<div class="layui-input-block">
											<input type="text" name="supplierTell" required
												lay-verify="ftell" autocomplete="off" class="layui-input">
										</div>
									</div>
									<div class="layui-form-item">
										<label class="layui-form-label">地址</label>
										<div class="layui-input-block">
											<input type="text" name="supplierSite" required 
											lay-verify="fsite" autocomplete="off" class="layui-input">
										</div>
									</div>
									<div class="layui-form-item">
										<label class="layui-form-label">邮编</label>
										<div class="layui-input-block">
											<input type="text" name="supplierMail" required
												lay-verify="femail" autocomplete="off" class="layui-input">
										</div>
									</div>
									<div class="layui-form-item">
										<label class="layui-form-label">备注</label>
										<div class="layui-input-block">
											<input type="text" name="supplierRemark" 
											autocomplete="off" class="layui-input">
										</div>
									</div>
									<div class="layui-form-item"
										style="text-align: center;margin-left: 18%;">
										<div class="layui-btn-container">
											<button type="button" class="layui-btn" id="btnSupplier"
												lay-submit lay-filter="fSupplier">保存</button>
											<button type="button" class="layui-btn layui-btn-danger" onclick="modHide();">取消</button>
										</div>
									</div>
								</form>
							</div>
						</div>
					</div>				
				</div>
				<div class="layui-tab-item">
					<div class="layui-card">
						<div class="layui-card-header">仓库资料信息：</div>
						<div class="layui-card-body" style="padding: 0;">
							<div class="layui-btn-group tableBtn" style="padding: 10px 0 0 82%;">
								<button class="layui-btn layui-btn-normal layui-btn-sm" data-type="getShow">新增</button>&nbsp;
								<button class="layui-btn layui-btn-warm layui-btn-sm" data-type="getCheckData">修改</button>
								<button class="layui-btn layui-btn-danger layui-btn-sm" data-type="delCheckData">删除</button>
							</div>
							<table class="layui-table" id="findWarehouse"
								lay-data="{
										height: 471,
										id: 'findWarehouse',
										url: '${ctx}/servlet/BaseInfoServlet?type=findBaseInfo&baseType=findWarehouse',
										page: true,
										limit: 10,//每页默认显示的数量	
										method: 'post', //提交方式 									
										}"
								lay-filter="findWarehouse">
								<thead>
									<tr>
										<th lay-data="{type: 'checkbox', fixed: 'left'}"></th>
										<th
											lay-data="{field: 'warehouseNum', align: 'center', sort: true}">编号</th>
										<th
											lay-data="{field: 'warehouseName', align: 'center', sort: true}">名称</th>
										<th
											lay-data="{field: 'staffName', align: 'center', sort: true}">负责人</th>
										<th
											lay-data="{field: 'staffPhone', align: 'center', sort: true}">电话</th>
										<th
											lay-data="{field: 'warehouseRemark', align: 'center', sort: true}">备注</th>																			
									</tr>
								</thead>
							</table>
						</div>
						<div id="modWarehouse" style="display: none;">
							<div
								class="layui-col-lg9 layui-col-md9 layui-col-sm9 layui-col-md-offset1">
								<form id="formWarehouse" class="layui-form" method="post"
									action="${ctx}/servlet/BaseInfoServlet" style="margin-top: 26px;">
									<input type="text" style="display:none" name="type"
										value="addEidtSave"> 
									<input type="text" style="display:none"
										name="warehouseId"> 
									<input type="text" style="display:none" name="baseType"
											value="warehouse">
									<div class="layui-form-item">
										<label class="layui-form-label">编号</label>
										<div class="layui-input-block">
											<input type="text" name="warehouseNum" required
												lay-verify="required" autocomplete="off" class="layui-input"
												readonly="readonly">
										</div>
									</div>
									<div class="layui-form-item">
										<label class="layui-form-label">名称</label>
										<div class="layui-input-block">
											<input type="text" name="warehouseName" required
												lay-verify="fname" autocomplete="off" class="layui-input">
										</div>
									</div>
									<div class="layui-form-item">
										<label class="layui-form-label">负责人</label>
										<div class="layui-input-block">
											<select name="staffId" lay-verify="sstaff" lay-search>
												<option value="0">---请选择---</option>
												<c:forEach items="${staffs}" var="staff">
													<option value="${staff.staffId}">${staff.staffName}</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<div class="layui-form-item">
										<label class="layui-form-label">备注</label>
										<div class="layui-input-block">
											<input type="text" name="warehouseRemark" 
											autocomplete="off" class="layui-input">
										</div>
									</div>
									<div class="layui-form-item"
										style="text-align: center;margin-left: 18%;">
										<div class="layui-btn-container">
											<button type="button" class="layui-btn" id="btnWarehouse"
												lay-submit lay-filter="fWarehouse">保存</button>
											<button type="button" class="layui-btn layui-btn-danger" onclick="modHide();">取消</button>
										</div>
									</div>
								</form>
							</div>
						</div>
					</div>	
				</div>
				<div class="layui-tab-item">
					<div class="layui-card">
						<div class="layui-card-header">部门资料信息：</div>
						<div class="layui-card-body" style="padding: 0;">
							<div class="layui-btn-group tableBtn" style="padding: 10px 0 0 82%;">
								<button class="layui-btn layui-btn-normal layui-btn-sm" data-type="getShowDepartment">新增</button>&nbsp;
								<button class="layui-btn layui-btn-warm layui-btn-sm" data-type="getCheckDepartment">修改</button>
								<button class="layui-btn layui-btn-danger layui-btn-sm" data-type="delCheckDepartment">删除</button>
							</div>
							<table class="layui-table" id="findDepartment"
								lay-data="{
										height: 471,
										id: 'findDepartment',
										url: '${ctx}/servlet/BaseInfoServlet?type=findBaseInfo&baseType=findDepartment',
										page: true,
										limit: 10,//每页默认显示的数量	
										method: 'post', //提交方式 									
										}"
								lay-filter="findDepartment">
								<thead>
									<tr>
										<th lay-data="{type: 'checkbox', fixed: 'left'}"></th>
										<th
											lay-data="{field: 'departmentName', align: 'center', sort: true}">部门名称</th>
										<th
											lay-data="{field: 'staffName', align: 'center', sort: true}">部门负责人</th>
										<th
											lay-data="{field: 'departmentTell', align: 'center', sort: true}">部门电话</th>
										<th
											lay-data="{field: 'remark', align: 'center', sort: true}">备注</th>																			
									</tr>
								</thead>
							</table>
						</div>
						<div id="modDepartment" style="display: none;">
							<div
								class="layui-col-lg9 layui-col-md9 layui-col-sm9 layui-col-md-offset1">
								<form id="formDepartment" class="layui-form" method="post"
									action="${ctx}/servlet/BaseInfoServlet" style="margin-top: 26px;">
									<input type="text" style="display:none" name="type"
										value="addEidtSave"> 
									<input type="text" style="display:none"
										name="departmentId"> 
									<input type="text" style="display:none" name="baseType"
											value="department">
									<div class="layui-form-item">
										<label class="layui-form-label">名称</label>
										<div class="layui-input-block">
											<input type="text" name="departmentName" required
												lay-verify="fname" autocomplete="off" class="layui-input">
										</div>
									</div>
									<div class="layui-form-item">
										<label class="layui-form-label">负责人</label>
										<div class="layui-input-block">
											<select name="staffId" lay-verify="sstaff" lay-search>
												<option value="0">---请选择---</option>
												<c:forEach items="${staffs}" var="staff">
													<option value="${staff.staffId}">${staff.staffName}</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<div class="layui-form-item">
										<label class="layui-form-label">电话</label>
										<div class="layui-input-block">
											<input type="text" name="departmentTell" required lay-verify="ftell"
												autocomplete="off" class="layui-input"
												onkeyup="value=value.replace(/[^\d\-\d]/g,'')">
										</div>
									</div>
									<div class="layui-form-item">
										<label class="layui-form-label">备注</label>
										<div class="layui-input-block">
											<input type="text" name="remark" 
											autocomplete="off" class="layui-input">
										</div>
									</div>
									<div class="layui-form-item"
										style="text-align: center;margin-left: 18%;">
										<div class="layui-btn-container">
											<button type="button" class="layui-btn" id="btnDepartment"
												lay-submit lay-filter="fDepartment">保存</button>
											<button type="button" class="layui-btn layui-btn-danger" onclick="modHide();">取消</button>
										</div>
									</div>
								</form>
							</div>
						</div>
					</div>	
				</div>
			</div>
		</div>				
    </div>
    <script type="text/javascript">
    	layui.use(['element', 'table', 'layer', 'form'], function(){
    		element = layui.element;
    		table = layui.table;//表格
    		layer = layui.layer;//layer弹出框
    		form = layui.form;//表单  		
    		 	
    		//表单条件限制
    		form.verify({
				fname: function (value){
					if (value.length < 2) {
						return "请输入至少2个字的名称";
					}
					if(!new RegExp("^[a-zA-Z0-9_\u4e00-\u9fa5\\s·]+$").test(value)){
				      	return "名称不能有特殊字符";
				    }
				    if(/(^\_)|(\__)|(\_+$)/.test(value)){
				      	return "名称首尾不能出现下划线\'_\'";
				    }
				    if(/^\d+\d+\d$/.test(value)){
				      	return "名称不能全为数字";
				    }
				},
				sstaff: function(value) {
					if (value == "0" || value == "") {
						return "请选择一个负责人";
					}
				},
				srawMaterialBig: function(value) {
					if (value == "0" || value == "") {
						return "请选择一个供应商类型";
					}
				},
				fsite: function(value) {
					if (value.length == 0 || value == "") {
						return "地址不能为空";
					}
				},
				femail: [/^[1-9]\d{5}$/g, "请输入合法的邮编号码"],
				ftell: [ /0\d{2,3}-\d{7,8}/, "电话格式为000-00000000" ],
			});
			
			var $ = layui.$, active = {
				reload: function() {
					var supplierNum = $("#supplierNum").val();
					var supplierName = $("#supplierName").val();
					if (supplierNum == undefined) {
                		supplierNum = "";
            		}
            		if (supplierName == undefined) {
                		supplierName = "";
            		}
					//执行重载
					table.reload('findSupplier', {
						page: {
							curr: 1 //重新从第 1 页开始
						},
						where: {
							supplierNum: supplierNum,
						  	supplierName: supplierName
						}
					});
				},
				clear: function() {
					$("#supplierNum").val("");
					$("#supplierName").val("");
					var supplierNum = $("#supplierNum").val();
					var supplierName = $("#supplierName").val();
					if (supplierNum == undefined) {
                		supplierNum = "";
            		}
            		if (supplierName == undefined) {
                		supplierName = "";
            		}
					//执行重载
					table.reload('findSupplier', {
						page: {
							curr: 1 //重新从第 1 页开始
						},
						where: {
							supplierNum: supplierNum,
						  	supplierName: supplierName
						}
					});
				},
				getShowSupplier: function() {
					$("#formSupplier").resetForm();//重置表单
					$("#btnSupplier").empty().append("保存");	
					$.post("${ctx}/servlet/BaseInfoServlet?type=getNumber&baseType=supplier",function(data){
						if (data != null) {
							$("#formSupplier [name='supplierNum']")
							.val(data.supplierNum);
							layer.open({
						    	type: 1,//类型
						      	title: '<i class="layui-icon layui-icon-add-1"> 新增供应商信息</i>',
						      	offset: ['5%','35%'],
						      	area:['600px','680px'],//定义宽和高
						      	shadeClose: true,//点击遮罩层关闭
						      	content: $("#modSupplier")//打开的内容
							});
						}
					}, "json");
				},
				getCheckSupplier: function() { //获取选中数据
			    	var checkStatus = table.checkStatus('findSupplier')
			      	,data = checkStatus.data;			      	
			      	$("#formSupplier").resetForm();//重置表单
			      	$("#btnSupplier").empty().append("修改");
			      	if (data.length === 0) {
						layer.msg('请选择一行');
					} else if (data.length > 1) {
						layer.msg('只能同时编辑一个');
					} else {
				      	loadDatatoForm("formSupplier", data[0]);
				      	layer.open({
					    	type: 1,//类型
					      	title: '<i class="layui-icon layui-icon-add-1"> 修改供应商信息</i>',
					      	offset: ['5%','35%'],
					      	area:['600px','680px'],//定义宽和高
					      	shadeClose: true,//点击遮罩层关闭
					      	content: $("#modSupplier")//打开的内容
						});
					}
			    },
			    delCheckSupplier: function() {
					var checkStatus = table.checkStatus('findSupplier')
			      	,data = checkStatus.data;
			      	if (data.length === 0) {
						layer.msg("请选择一行数据!");
					} else {
						layer.confirm('真的删除行么', {
			            	icon: 3,
			                btn: ['确定', '取消']
			            }, function (index) {		
			            	layer.close(index);
			            	var returnLength = 0;
			            	for ( var i = 0; i < data.length; i++) {			            		
			            		$.ajax({
				                	url: "${ctx}/servlet/BaseInfoServlet?type=delBaseInfo&supplierId=" + 
				                	data[i].supplierId + "&baseType=supplier",		                    
				                	type: "post",//数据传输通道的类型
				                    async: false,
				                    dataType: "json",//传输的数据的类型
				                    success: function (datas) {//直接理解为回调函数
				                 		if (datas.state) {
				                        	returnLength++;	      					
				                        } else {
				                        	layer.msg(datas.msg, { icon: 2, skin: "layui-layer-molv" });
				                        }
				                    }
				               });
			            	}
			            	if (returnLength == data.length) {
								table.reload("findSupplier");
							}				      		
					   	});
					}
				},
				getShow: function () {
					$("#formWarehouse").resetForm();//重置表单
					$("#btnWarehouse").empty().append("保存");	
					$.post("${ctx}/servlet/BaseInfoServlet?type=getNumber&baseType=warehouse",function(data){
						if (data != null) {
							$("#formWarehouse [name='warehouseNum']")
							.val(data.warehouseNum);
							layer.open({
						    	type: 1,//类型
						      	title: '<i class="layui-icon layui-icon-add-1"> 新增仓库信息</i>',
						      	offset: ['5%','35%'],
						      	area:['600px','400px'],//定义宽和高
						      	shadeClose: true,//点击遮罩层关闭
						      	content: $("#modWarehouse")//打开的内容
							});
						}
					}, "json");
				},
				getCheckData: function() { //获取选中数据
			    	var checkStatus = table.checkStatus('findWarehouse')
			      	,data = checkStatus.data;
			      	$("#formWarehouse").resetForm();//重置表单
			      	$("#btnRawMaterial").empty().append("修改");
			      	if (data.length === 0) {
						layer.msg('请选择一行');
					} else if (data.length > 1) {
						layer.msg('只能同时编辑一个');
					} else {
				      	loadDatatoForm("formWarehouse", data[0]);
				      	layer.open({
					    	type: 1,//类型
					      	title: '<i class="layui-icon layui-icon-add-1"> 修改仓库信息</i>',
					      	offset: ['5%','35%'],
					      	area:['600px','380px'],//定义宽和高
					      	shadeClose: true,//点击遮罩层关闭
					      	content: $("#modWarehouse")//打开的内容
						});
					}
			    },
			    delCheckData: function() {
			    	var checkStatus = table.checkStatus('findWarehouse')
			      	,data = checkStatus.data;
			      	if (data.length === 0) {
						layer.msg("请选择一行数据!");
					} else {
						layer.confirm('真的删除行么', {
			            	icon: 3,
			                btn: ['确定', '取消']
			            }, function (index) {		
			            	layer.close(index);
			            	var returnLength = 0;
			            	for ( var i = 0; i < data.length; i++) {
			            		$.ajax({
				                	url: "${ctx}/servlet/BaseInfoServlet?type=delBaseInfo" + "&warehouseId=" + 
				                	data[i].warehouseId + "&baseType=warehouse",		                    
				                	type: "post",//数据传输通道的类型
				                    async: false,
				                    dataType: "json",//传输的数据的类型
				                    success: function (datas) {//直接理解为回调函数
				                 		if (datas.state) {
				                        	returnLength++;	      					
				                        } else {
				                        	layer.msg(datas.msg, { icon: 2, skin: "layui-layer-molv" });
				                        }
				                    }
				               });
			            	}
			            	if (returnLength == data.length) {
								table.reload("findWarehouse");
							}				      		
					   	});
					}
			    },
			    getShowDepartment: function() {
			    	$("#formDepartment").resetForm();//重置表单
					$("#btnDepartment").empty().append("保存");	
					layer.open({
				    	type: 1,//类型
				      	title: '<i class="layui-icon layui-icon-add-1"> 新增部门信息</i>',
				      	offset: ['5%','35%'],
				      	area:['600px','380px'],//定义宽和高
				      	shadeClose: true,//点击遮罩层关闭
				      	content: $("#modDepartment")//打开的内容
					});
			    },
			    getCheckDepartment: function() { //获取选中数据
			    	var checkStatus = table.checkStatus('findDepartment')
			      	,data = checkStatus.data;
			      	if (data.length === 0) {
						layer.msg('请选择一行');
					} else if (data.length > 1) {
						layer.msg('只能同时编辑一个');
					} else {
				      	$("#formDepartment").resetForm();//重置表单
				      	$("#btnDepartment").empty().append("修改");
				      	loadDatatoForm("formDepartment", data[0]);
				      	layer.open({
					    	type: 1,//类型
					      	title: '<i class="layui-icon layui-icon-add-1"> 修改部门信息</i>',
					      	offset: ['5%','35%'],
					      	area:['600px','380px'],//定义宽和高
					      	shadeClose: true,//点击遮罩层关闭
					      	content: $("#modDepartment")//打开的内容
						});
					}
			    },
			    delCheckDepartment: function() {
			    	var checkStatus = table.checkStatus('findDepartment')
			      	,data = checkStatus.data;
			      	if (data.length === 0) {
						layer.msg("请选择一行数据!");
					} else {
						layer.confirm('真的删除行么', {
			            	icon: 3,
			                btn: ['确定', '取消']
			            }, function (index) {		
			            	layer.close(index);
			            	var returnLength = 0;
			            	for ( var i = 0; i < data.length; i++) {
			            		$.ajax({
				                	url: "${ctx}/servlet/BaseInfoServlet?type=delBaseInfo" + "&departmentId=" + 
				                	data[i].departmentId + "&baseType=department",		                    
				                	type: "post",//数据传输通道的类型
				                    async: false,
				                    dataType: "json",//传输的数据的类型
				                    success: function (datas) {//直接理解为回调函数
				                 		if (datas.state) {
				                        	returnLength++;	      					
				                        } else {
				                        	layer.msg(datas.msg, { icon: 2, skin: "layui-layer-molv" });
				                        }
				                    }
				               });
			            	}
			            	if (returnLength == data.length) {
								table.reload("findDepartment");
							}				      		
					   	});
					}
			    }
			};
			//监听提交
            form.on('submit(fSupplier)', function(data){
                console.log(data.field);
              	formSubmit("formSupplier", "findSupplier");
				return false;
            });
            //监听提交
            form.on('submit(fWarehouse)', function(data){
                console.log(data.field);
              	formSubmit("formWarehouse", "findWarehouse");
				return false;
            });
            //监听提交
            form.on('submit(fDepartment)', function(data){
                console.log(data.field);
              	formSubmit("formDepartment", "findDepartment");
				return false;
            });
			
			$('.tableBtn .layui-btn').on('click', function(){
			  var type = $(this).data('type');
			  active[type] ? active[type].call(this) : '';
			});
    		
    		function formSubmit(forName,tabName){
	    		$("#" + forName).ajaxSubmit(function(jsonObject){
	    			if (jsonObject != "") {
						data = JSON.parse(jsonObject);
	        			if (data.state) {
	        				modHide();
							layer.alert(data.msg, {
								icon : 1,
								title : '提示'
							},function(layerIndex){
								layer.close(layerIndex);
								table.reload(tabName);
							});	
						} else {
							layer.alert(data.msg, {
								icon : 0,
								title : '提示'
							});
						}
					}	        		
	        	}); 	
	    	}    		
    	});    	
    	function modHide(){
    		$(".layui-layer-close").trigger("click");
    	}
    	function chkUper(obj){
	        if(/[^\u4E00-\u9FA5]/g.test(obj.value)){
	            obj.value = obj.value.replace(/[^\u4E00-\u9FA5]/g,'');
	        }
	    }
    </script>
  </body>
</html>
