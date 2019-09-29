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
    
    <title>My JSP 'EntryOrder.jsp' starting page</title>
    
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
		.layui-card{
			box-shadow: none;
		}
		.layui-card .layui-card-header{
			font-size: 26px;
			padding: 26px 0 10px 26px;
		}
		.layui-card .layui-card-body{
			border: none;
		}
		.layui-card .laui-row{
			margin: 0 16px;
			border-bottom: 1px solid rgb(246, 246, 246);
		}
		.layui-row>.layui-btn-container>.layui-btn{
			width: 118px;
		}
		.layui-btn i>span{
			position: relative;
			bottom: 1.5px;
			font-size: 14px;
		}
		.layui-form .layui-anim {
			max-height: 156px;
			height: auto;
		}
		#modCheckMaterial .layui-card .layui-card-header{
			font-size: 20px;
			padding: 8px 0 0 26px;
		}
	</style>
	<script type="text/javascript"
		src="${ctx}/Content/js/jquery-2.0.3.min.js"></script>
	<script type="text/javascript" src="${ctx}/Content/js/jquery.form.js"></script>
	<script type="text/javascript" src="${ctx}/Content/js/echoform.js"></script>
	<script type="text/javascript" src="${ctx}/Content/js/Convert_Pinyin.js"></script>
	<script type="text/javascript" src="${ctx}/Content/layui/layui.js"></script>
  </head>
  
  <body>
    <div class="layui-container">
		<div class="layui-tab layui-tab-card">
			<ul class="layui-tab-title">
				<li id="Reload1" class="layui-this">采购订货单</li>
				<li id="Reload2">配送单</li>
				<li id="Reload3">入库单</li>
				<li id="Reload4">退货单</li>
				<li id="Reload5">领料单</li>
				<li id="Reload6">领料退货单</li>
				<li id="Reload7">仓库调拨单</li>
				<li id="Reload8">库存盘点单</li>
			</ul>
			<div class="layui-tab-content" style="padding: 0;">
				<div class="layui-tab-item layui-show">
					<div class="layui-card">
						<div class="layui-card-header">订货单信息：</div>
						<div class="layui-card-body" style="padding: 0;">
							<div class="laui-row">
								<form id="formPurchaseOrders" class="layui-form" method="post" 
								action="${ctx}/servlet/EntryOrderServlet" style="margin-top: 26px;">
									<input type="text" style="display:none" name="type"
										value="addBills"><input type="text" style="display:none"
										name="billsType" value="purchaseOrders">
									<div class="layui-form-item">
										<div class="layui-col-lg3 layui-col-md3 layui-col-sm3">
											<label class="layui-form-label">录单日期:</label>
											<div class="layui-input-block">
												<input type="text" id="billsEnteryTime1" name="billsEntryTime" required
													lay-verify="date" autocomplete="off" class="layui-input"
													readonly="readonly">
											</div>
										</div>
										<div class="layui-col-lg3 layui-col-md3 layui-col-sm3 layui-col-md-offset1">
											<label class="layui-form-label">订货单号:</label>
											<div class="layui-input-block">
												<input type="text" id="billsNum1" name="billsNum" required
													lay-verify="required" autocomplete="off" class="layui-input"
													readonly="readonly">
											</div>
										</div>
									</div>
									<div class="layui-form-item">
										<div class="layui-col-lg3 layui-col-md3 layui-col-sm3">
											<label class="layui-form-label">供应商:</label>
											<div class="layui-input-block">
												<select name="supplierId" lay-filter="ssupplier" lay-verify="fsupplier" lay-search>
												<option value="0">----请选择----</option>
												<c:forEach items="${suppliers}" var="supplier">
													<option value="${supplier.supplierId}">${supplier.supplierName}</option>
												</c:forEach>
											</select>
											</div>
										</div>
										<div class="layui-col-lg3 layui-col-md3 layui-col-sm3 layui-col-md-offset1">
											<label class="layui-form-label">经办人:</label>
											<div class="layui-input-block">
												<select name="staffID" lay-verify="fstaff" lay-search>
													<option value="0">----请选择----</option>
													<c:forEach items="${staffs}" var="staff">
														<option value="${staff.staffId}">${staff.staffName}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
									<div class="layui-form-item">
										<div class="layui-col-lg3 layui-col-md3 layui-col-sm3">
											<label class="layui-form-label">仓库:</label>
											<div class="layui-input-block">
												<select name="warehouseId" lay-verify="fwarehouse" 
												lay-search>
													<option value="0">----请选择----</option>
													<c:forEach items="${warehouses}" var="warehouse">
														<option value="${warehouse.warehouseId}">${warehouse.warehouseName}</option>
													</c:forEach>
												</select>
											</div>
										</div>
										<div class="layui-col-lg3 layui-col-md3 layui-col-sm3 layui-col-md-offset1">
											<label class="layui-form-label">批准人:</label>
											<div class="layui-input-block">
												<select name="staffId" lay-verify="sstaff" lay-search>
													<option value="0">----请选择----</option>
													<c:forEach items="${staffs}" var="staff">
														<option value="${staff.staffId}">${staff.staffName}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
									<div class="layui-form-item">
										<div class="layui-col-lg7 layui-col-md7 layui-col-sm7">
											<label class="layui-form-label">备注:</label>
											<div class="layui-input-block">
												<textarea placeholder="请输入内容" class="layui-textarea" name="billsRemark"></textarea>
												<input type="text" style="display:none" id="billsMoney" name="billsMoney">											
											</div>
										</div>					
									</div>
									<div class="layui-form-item" style="border-top: 1px solid rgb(246, 246, 246);">
										<table class="layui-table" id="selectMaterial1"
											lay-data="{
												height: 280,
												id: 'selectMaterial1',
												method: 'post', //提交方式 									
											}"
											lay-filter="selectMaterial1">
											<thead>
												<tr>										
													<th
														lay-data="{field: 'rawMaterialNum', align: 'center', sort: true}">编号</th>
													<th
														lay-data="{field: 'rawMaterialName', align: 'center', sort: true}">原料名称</th>
													<th
														lay-data="{field: 'unitName', align: 'center', sort: true}">进货单位</th>
													<th
														lay-data="{field: 'rawMaterialAmount', edit: 'text', align: 'center', sort: true}">补货数量</th>
													<th
														lay-data="{field: 'rawMaterialPrice', align: 'center', sort: true}">原料货物单价</th>
													<th
														lay-data="{field: 'remark', edit: 'text', align: 'center', sort: true}">备注</th>
													<th
														lay-data="{fixed: 'right', width: 165, align:'center', toolbar: '#barBtn'}">移除</th>
												</tr>
											</thead>
										</table>
										<script type="text/html" id="barBtn">
  											<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
										</script>			
									</div>
									<div class="layui-form-item">
										<div class="layui-btn-container" style="text-align: center;padding: 10px 0;">
											<button type="button" class="layui-btn layui-btn-normal"
												id="btnMaterial1">
												<i class="layui-icon layui-icon-search"> <span>
														浏览仓库</span> </i>
											</button>
											<button type="button" class="layui-btn" lay-submit
												lay-filter="formPurchaseOrders">
												<i class="layui-icon layui-icon-add-1"> <span> 落
														单</span> </i>
											</button>
											<button type="button" class="layui-btn layui-btn-warm" id="btnReload1"
												onclick="reload('formPurchaseOrders', 'selectMaterial1');">
												<i class="layui-icon layui-icon-refresh-1"> <span>
														新 单</span> </i>
											</button>
										</div>
									</div>
								</form>
							</div>				
						</div>
						<div  id="modRawMaterial1" style="display: none;">
							<div class="layui-btn-group btnRawMaterial" style="padding: 10px 0 0 82%;">
								<button class="layui-btn layui-btn-normal layui-btn-sm" data-type="getShow">新增</button>&nbsp;
								<button class="layui-btn layui-btn-warm layui-btn-sm" data-type="getCheckData">修改</button>
							</div>
							<table class="layui-table" id="findMaterial1"
								lay-data="{
									height: 396,
									id: 'findMaterial1',
									page: true,
									limit: 8,//每页默认显示的数量
									limits: [8,20,30,50,60],
									method: 'post', //提交方式 									
								}"
								lay-filter="findMaterial1">
								<thead>
									<tr>
										<th lay-data="{type: 'checkbox', fixed: 'left'}"></th>
										<th
											lay-data="{field: 'rawMaterialNum', width: 125, align: 'center', sort: true}">编号</th>
										<th
											lay-data="{field: 'rawMaterialName', width: 125, align: 'center', sort: true}">原料名称</th>
										<th
											lay-data="{field: 'rawMaterialBigName', width: 125, align: 'center', sort: true}">原料类别</th>
										<th
											lay-data="{field: 'unitName', width: 125, align: 'center', sort: true}">原料单位</th>
										<th
											lay-data="{field: 'rawMaterialPrice', width: 125, align: 'center', sort: true}">原料单价</th>
									</tr>
								</thead>
							</table>
							<div class="layui-btn-container" style="margin-top: 3%;text-align: center;">
								<button type="button" class="layui-btn"
									onclick="selectMaterial('findMaterial1', 'selectMaterial1');">确
									认</button>
								<button type="button" class="layui-btn layui-btn-danger"
									onclick="modHide();">取 消</button>
							</div>
						</div>
						<div id="modAddEidtMaterial" style="display: none;">
							<div
								class="layui-col-lg9 layui-col-md9 layui-col-sm9 layui-col-md-offset1">
								<form id="formRawMaterial" class="layui-form" method="post"
									action="${ctx}/servlet/RawMaterialServlet" style="margin-top: 26px;">
									<input type="text" style="display:none" name="type"
										value="addEidtSave"> <input type="text" style="display:none"
										name="rawMaterialId"> <input type="text"
										style="display:none" name="rawMaterialType"
										value="rawMaterial">
									<div class="layui-form-item">
										<label class="layui-form-label">编号</label>
										<div class="layui-input-block">
											<input type="text" name="rawMaterialNum" required
												lay-verify="required" autocomplete="off" class="layui-input"
												readonly="readonly">
										</div>
									</div>
									<div class="layui-form-item">
										<label class="layui-form-label">名称</label>
										<div class="layui-input-block">
											<input type="text" name="rawMaterialName" required
												lay-verify="fname" autocomplete="off" class="layui-input"
												onblur="pim();">
										</div>
									</div>		
									<div class="layui-form-item">
										<label class="layui-form-label">原料类别</label>
										<div class="layui-input-block">
											<select class="rawMaterialSmallId" name="rawMaterialSmallId" lay-verify="srawMaterialSmall" lay-search>
												
											</select>
										</div>
									</div>
									<div class="layui-form-item">
										<label class="layui-form-label">单位</label>
										<div class="layui-input-block">
											<select name="unitId" lay-verify="sunit" lay-search>
												<option value="0">---请选择---</option>
												<c:forEach items="${units}" var="unit">
													<option value="${unit.unitId}">${unit.unitName}</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<div class="layui-form-item">
										<label class="layui-form-label">单价</label>
										<div class="layui-input-block">
											<input type="text" name="rawMaterialPrice" required
												lay-verify="fdouble" autocomplete="off" class="layui-input">
										</div>
									</div>
									<div class="layui-form-item">
										<label class="layui-form-label">拼音码</label>
										<div class="layui-input-block">
											<input type="text" name="pinyinCode" required
												lay-verify="required" autocomplete="off" class="layui-input"
												readonly="readonly">
										</div>
									</div>
									<div class="layui-form-item"
										style="text-align: center;margin-left: 18%;">
										<div class="layui-btn-container">
											<button type="button" class="layui-btn" id="btnRawMaterial"
												lay-submit lay-filter="frawMaterial">保存</button>
											<button type="button" class="layui-btn layui-btn-danger Cancel">取消</button>
										</div>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
				<div class="layui-tab-item">
					<div class="layui-card">
						<div class="layui-card-header">配送单信息：</div>
						<div class="layui-card-body" style="padding: 0;">
							<div class="laui-row">
								<form id="formDispatchingOrders" class="layui-form" method="post" 
								action="${ctx}/servlet/EntryOrderServlet" style="margin-top: 26px;">
									<input type="text" style="display:none" name="type"
										value="addBills"><input type="text" style="display:none"
										name="billsType" value="dispatchingOrders">
									<div class="layui-form-item">
										<div class="layui-col-lg3 layui-col-md3 layui-col-sm3">
											<label class="layui-form-label">录单日期:</label>
											<div class="layui-input-block">
												<input type="text" id="billsEnteryTime2" name="billsEntryTime" required
													lay-verify="required" autocomplete="off" class="layui-input"
													readonly="readonly">
											</div>
										</div>
										<div class="layui-col-lg3 layui-col-md3 layui-col-sm3 layui-col-md-offset1">
											<label class="layui-form-label">配送单号:</label>
											<div class="layui-input-block">
												<input type="text" id="billsNum2" name="billsNum" required
													lay-verify="required" autocomplete="off" class="layui-input"
													readonly="readonly">
											</div>
										</div>
										<div class="layui-col-lg3 layui-col-md3 layui-col-sm3">
											<div class="layui-input-block">
												<button type="button" class="layui-btn" id="btnPurchase"
													lay-filter="btnPurchase">
													<i class="layui-icon layui-icon-add-1"> <span> 从采购单录入</span>
													</i>
												</button>
											</div>											
										</div>
									</div>
									<div class="layui-form-item">
										<div class="layui-col-lg3 layui-col-md3 layui-col-sm3">
											<label class="layui-form-label">供应商:</label>
											<div class="layui-input-block">
												<select name="supplierId" lay-verify="ssupplier" lay-search>
												<option value="0">----请选择----</option>
												<c:forEach items="${suppliers}" var="supplier">
													<option value="${supplier.supplierId}">${supplier.supplierName}</option>
												</c:forEach>
											</select>
											</div>
										</div>
										<div class="layui-col-lg3 layui-col-md3 layui-col-sm3 layui-col-md-offset1">
											<label class="layui-form-label">经办人:</label>
											<div class="layui-input-block">
												<select name="staffID" lay-verify="fstaff" lay-search>
													<option value="0">----请选择----</option>
													<c:forEach items="${staffs}" var="staff">
														<option value="${staff.staffId}">${staff.staffName}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
									<div class="layui-form-item">
										<div class="layui-col-lg3 layui-col-md3 layui-col-sm3">
											<label class="layui-form-label">仓库:</label>
											<div class="layui-input-block">
												<select name="warehouseId" lay-verify="sunit"
												lay-search>
													<option value="0">----请选择----</option>
													<c:forEach items="${warehouses}" var="warehouse">
														<option value="${warehouse.warehouseId}">${warehouse.warehouseName}</option>
													</c:forEach>
												</select>
											</div>
										</div>
										<div class="layui-col-lg3 layui-col-md3 layui-col-sm3 layui-col-md-offset1">
											<label class="layui-form-label">批准人:</label>
											<div class="layui-input-block">
												<select name="staffId" lay-verify="sunit" lay-search>
													<option value="0">----请选择----</option>
													<c:forEach items="${staffs}" var="staff">
														<option value="${staff.staffId}">${staff.staffName}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
									<div class="layui-form-item">
										<div class="layui-col-lg7 layui-col-md7 layui-col-sm7">
											<label class="layui-form-label">备注:</label>
											<div class="layui-input-block">
												<textarea placeholder="请输入内容" class="layui-textarea" name="billsRemark"></textarea>
												<input type="text" style="display:none" id="billsMoney2" name="billsMoney">
											</div>
										</div>									
									</div>
									<div class="layui-form-item" style="border-top: 1px solid rgb(246, 246, 246);">
										<table class="layui-table" id="selectMaterial2"
											lay-data="{
												height: 277,
												id: 'selectMaterial2',
												method: 'post', //提交方式 									
											}"
											lay-filter="selectMaterial2">
											<thead>
												<tr>
													<th
														lay-data="{field: 'rawMaterialNum', align: 'center', sort: true}">原料编号</th>
													<th
														lay-data="{field: 'rawMaterialName', align: 'center', sort: true}">原料名称</th>
													<th
														lay-data="{field: 'unitName', align: 'center', sort: true}">单位</th>
													<th
														lay-data="{field: 'rawMaterialAmount', edit: 'text', align: 'center', sort: true}">数量</th>
													<th
														lay-data="{field: 'rawMaterialPrice', edit: 'text', align: 'center', sort: true}">进货单价</th>
													<th
														lay-data="{field: 'remark', edit: 'text', align: 'center', sort: true}">备注</th>
													<th
														lay-data="{fixed: 'right', width: 165, align:'center', toolbar: '#barBtn'}">移除</th>
												</tr>
											</thead>
										</table>
										<script type="text/html" id="barBtn">
  											<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
										</script>
									</div>
									<div class="layui-form-item">
										<div class="layui-btn-container" style="text-align: center;padding: 10px 0;">
											<button type="button" class="layui-btn layui-btn-normal"
												id="btnMaterial2">
												<i class="layui-icon layui-icon-search"> <span>
														浏览原料</span> </i>
											</button>
											<button type="button" class="layui-btn" lay-submit
												lay-filter="formDispatchingOrders">
												<i class="layui-icon layui-icon-add-1"> <span> 落
														单</span> </i>
											</button>
											<button type="button" class="layui-btn layui-btn-warm" id="btnReload2"
												onclick="reload('formDispatchingOrders', 'selectMaterial2');">
												<i class="layui-icon layui-icon-refresh-1"> <span>
														新 单</span> </i>
											</button>
										</div>
									</div>
								</form>
							</div>	
						</div>
						<div  id="modPurchaseOrder" style="display: none;">
							<table class="layui-table" id="findPurchase"
								lay-data="{
										height: 516,
										id: 'findPurchase',
										page: true,
										limit: 10,//每页默认显示的数量
										method: 'post', //提交方式 									
										}"
								lay-filter="findPurchase">
								<thead>
									<tr>
										<th
											lay-data="{field: 'billsEntryTimes', align: 'center', sort: true}">录单日期</th>
										<th
											lay-data="{field: 'billsNum', align: 'center', sort: true}">采购单号</th>
										<th
											lay-data="{field: 'supplierName', align: 'center', sort: true}">供应商</th>
										<th
											lay-data="{field: 'warehouseName', align: 'center', sort: true}">仓库</th>
									</tr>
								</thead>
							</table>
						</div>
						<div  id="modRawMaterial2" style="display: none;">
							<table class="layui-table" id="findMaterial2"
								lay-data="{
										height: 438,
										id: 'findMaterial2',
										page: true,
										limit: 8,//每页默认显示的数量
										limits: [8,20,30,50,60],
										method: 'post', //提交方式 									
										}"
								lay-filter="findMaterial2">
								<thead>
									<tr>
										<th lay-data="{type: 'checkbox', fixed: 'left'}"></th>
										<th
											lay-data="{field: 'rawMaterialNum', align: 'center', sort: true}">编号</th>
										<th
											lay-data="{field: 'rawMaterialName', align: 'center', sort: true}">原料名称</th>
										<th
											lay-data="{field: 'rawMaterialBigName', align: 'center', sort: true}">原料类别</th>
										<th
											lay-data="{field: 'unitName', align: 'center', sort: true}">原料单位</th>
										<th
											lay-data="{field: 'rawMaterialPrice', align: 'center', sort: true}">原料单价</th>
										<th
											lay-data="{field: 'rawMaterialAmount', align: 'center', sort: true}">数量</th>
									</tr>
								</thead>
							</table>
							<div class="layui-btn-container" style="margin-top: 3%;text-align: center;">
								<button type="button" class="layui-btn" 
								onclick="selectMaterial('findMaterial2', 'selectMaterial2');">确 认</button>
								<button type="button" class="layui-btn layui-btn-danger"
									onclick="modHide();">取 消</button>
							</div>
						</div>
					</div>
				</div>
				<div class="layui-tab-item">
					<div class="layui-card">
						<div class="layui-card-header">入库单信息：</div>
						<div class="layui-card-body" style="padding: 0;">
							<div class="laui-row">
								<form id="formGodownOrders" class="layui-form" method="post" 
								action="${ctx}/servlet/EntryOrderServlet" style="margin-top: 26px;">
									<input type="text" style="display:none" name="type"
										value="addBills"><input type="text" style="display:none"
										name="billsType" value="godownOrders">
									<div class="layui-form-item">
										<div class="layui-col-lg3 layui-col-md3 layui-col-sm3">
											<label class="layui-form-label">录单日期:</label>
											<div class="layui-input-block">
												<input type="text" id="billsEnteryTime3" name="billsEntryTime" required
													lay-verify="required" autocomplete="off" class="layui-input"
													readonly="readonly">
											</div>
										</div>
										<div class="layui-col-lg3 layui-col-md3 layui-col-sm3 layui-col-md-offset1">
											<label class="layui-form-label">入库单号:</label>
											<div class="layui-input-block">
												<input type="text" id="billsNum3" name="billsNum" required
													lay-verify="required" autocomplete="off" class="layui-input"
													readonly="readonly">
											</div>
										</div>
										<div class="layui-col-lg3 layui-col-md3 layui-col-sm3">
											<div class="layui-input-block">
												<button type="button" class="layui-btn" id="btnDispatching">
													<i class="layui-icon layui-icon-add-1"> <span> 从配送单录入</span>
													</i>
												</button>
											</div>											
										</div>
									</div>
									<div class="layui-form-item">
										<div class="layui-col-lg3 layui-col-md3 layui-col-sm3">
											<label class="layui-form-label">供应商:</label>
											<div class="layui-input-block">
												<select name="supplierId" lay-verify="sunit" lay-search>
												<option value="0">----请选择----</option>
												<c:forEach items="${suppliers}" var="supplier">
													<option value="${supplier.supplierId}">${supplier.supplierName}</option>
												</c:forEach>
											</select>
											</div>
										</div>
										<div class="layui-col-lg3 layui-col-md3 layui-col-sm3 layui-col-md-offset1">
											<label class="layui-form-label">经办人:</label>
											<div class="layui-input-block">
												<select name="staffID" lay-verify="sunit" lay-search>
													<option value="0">----请选择----</option>
													<c:forEach items="${staffs}" var="staff">
														<option value="${staff.staffId}">${staff.staffName}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
									<div class="layui-form-item">
										<div class="layui-col-lg3 layui-col-md3 layui-col-sm3">
											<label class="layui-form-label">仓库:</label>
											<div class="layui-input-block">
												<select name="warehouseId" lay-verify="sunit"
												lay-search>
													<option value="0">----请选择----</option>
													<c:forEach items="${warehouses}" var="warehouse">
														<option value="${warehouse.warehouseId}">${warehouse.warehouseName}</option>
													</c:forEach>
												</select>
											</div>
										</div>
										<div class="layui-col-lg3 layui-col-md3 layui-col-sm3 layui-col-md-offset1">
											<label class="layui-form-label">批准人:</label>
											<div class="layui-input-block">
												<select name="staffId" lay-verify="sunit" lay-search>
													<option value="0">----请选择----</option>
													<c:forEach items="${staffs}" var="staff">
														<option value="${staff.staffId}">${staff.staffName}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
									<div class="layui-form-item">
										<div class="layui-col-lg7 layui-col-md7 layui-col-sm7">
											<label class="layui-form-label">备注:</label>
											<div class="layui-input-block">
												<textarea placeholder="请输入内容" class="layui-textarea" name="billsRemark"></textarea>
												<input type="text" style="display:none" id="billsMoney3" name="billsMoney">
											</div>
										</div>									
									</div>
									<div class="layui-form-item" style="border-top: 1px solid rgb(246, 246, 246);">
										<table class="layui-table" id="selectMaterial3"
											lay-data="{
												height: 317,
												id: 'selectMaterial3',
												totalRow: true,
												method: 'post', //提交方式 									
											}"
											lay-filter="selectMaterial3">
											<thead>
												<tr>
													<th
														lay-data="{field: 'rawMaterialNum', align: 'center', sort: true, totalRowText: '合计'}">原料编号</th>
													<th
														lay-data="{field: 'rawMaterialName', align: 'center', sort: true}">原料名称</th>
													<th
														lay-data="{field: 'unitName', align: 'center', sort: true}">进货单位</th>
													<th
														lay-data="{field: 'rawMaterialAmount', edit: 'text', align: 'center', sort: true, totalRow: true}">进货数量</th>
													<th
														lay-data="{field: 'rawMaterialPrice', align: 'center', sort: true}">进货单价</th>
													<th
														lay-data="{field: 'totalPrice', align: 'center', sort: true, totalRow: true}">金额</th>
													<th
														lay-data="{field: 'remark', edit: 'text', align: 'center', sort: true}">备注</th>
													<th
														lay-data="{field: 'reportAmount', align: 'center', sort: true, event: 'setQuantity'}">库存数量</th>
													<th
														lay-data="{field: 'rawMaterialPrice', align: 'center', sort: true}">库存价格</th>
												</tr>
											</thead>
										</table>
									</div>
									<div class="layui-form-item">
										<div class="layui-btn-container" style="text-align: center;padding: 10px 0;">
											<button type="button" class="layui-btn" lay-submit
												lay-filter="formGodownOrders">
												<i class="layui-icon layui-icon-add-1"> <span> 落
														单</span> </i>
											</button>
											<button type="button" class="layui-btn layui-btn-warm" id="btnReload3"
												onclick="reload('formGodownOrders', 'selectMaterial3');">
												<i class="layui-icon layui-icon-refresh-1"> <span>
														新 单</span> </i>
											</button>
										</div>
									</div>
								</form>
							</div>
						</div>
						<div  id="modDispatchingOrders" style="display: none;">
							<table class="layui-table" id="findDispatching"
								lay-data="{
										height: 516,
										id: 'findDispatching',
										page: true,
										limit: 10,//每页默认显示的数量
										method: 'post', //提交方式 									
										}"
								lay-filter="findDispatching">
								<thead>
									<tr>
										<th
											lay-data="{field: 'billsEntryTimes', align: 'center', sort: true}">录单日期</th>
										<th
											lay-data="{field: 'billsNum', align: 'center', sort: true}">配送单号</th>
										<th
											lay-data="{field: 'supplierName', align: 'center', sort: true}">供应商</th>
										<th
											lay-data="{field: 'warehouseName', align: 'center', sort: true}">仓库</th>
									</tr>
								</thead>
							</table>
						</div>
					</div>
				</div>
				<div class="layui-tab-item">
					<div class="layui-card">
						<div class="layui-card-header">退货单信息：</div>
						<div class="layui-card-body" style="padding: 0;">
							<div class="laui-row">
								<form id="formCreditOrders" class="layui-form" method="post" 
								action="${ctx}/servlet/EntryOrderServlet" style="margin-top: 26px;">
									<input type="text" style="display:none" name="type"
										value="addBills"><input type="text" style="display:none"
										name="billsType" value="creditOrders">
									<div class="layui-form-item">
										<div class="layui-col-lg3 layui-col-md3 layui-col-sm3">
											<label class="layui-form-label">录单日期:</label>
											<div class="layui-input-block">
												<input type="text" id="billsEnteryTime4" name="billsEntryTime" required
													lay-verify="required" autocomplete="off" class="layui-input"
													readonly="readonly">
											</div>
										</div>
										<div class="layui-col-lg3 layui-col-md3 layui-col-sm3 layui-col-md-offset1">
											<label class="layui-form-label">退货单号:</label>
											<div class="layui-input-block">
												<input type="text" id="billsNum4" name="billsNum" required
													lay-verify="required" autocomplete="off" class="layui-input"
													readonly="readonly">
											</div>
										</div>
										<div class="layui-col-lg3 layui-col-md3 layui-col-sm3">
											<div class="layui-input-block">
												<button type="button" class="layui-btn" id="btnGodownOrders">
													<i class="layui-icon layui-icon-add-1"> <span> 从入库单录入</span>
													</i>
												</button>
											</div>											
										</div>
									</div>
									<div class="layui-form-item">
										<div class="layui-col-lg3 layui-col-md3 layui-col-sm3">
											<label class="layui-form-label">供应商:</label>
											<div class="layui-input-block">
												<select name="supplierId" lay-verify="sunit" lay-search>
												<option value="0">----请选择----</option>
												<c:forEach items="${suppliers}" var="supplier">
													<option value="${supplier.supplierId}">${supplier.supplierName}</option>
												</c:forEach>
											</select>
											</div>
										</div>
										<div class="layui-col-lg3 layui-col-md3 layui-col-sm3 layui-col-md-offset1">
											<label class="layui-form-label">经办人:</label>
											<div class="layui-input-block">
												<select name="staffID" lay-verify="sunit" lay-search>
													<option value="0">----请选择----</option>
													<c:forEach items="${staffs}" var="staff">
														<option value="${staff.staffId}">${staff.staffName}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
									<div class="layui-form-item">
										<div class="layui-col-lg3 layui-col-md3 layui-col-sm3">
											<label class="layui-form-label">仓库:</label>
											<div class="layui-input-block">
												<select name="warehouseId" lay-verify="sunit"
												lay-search>
													<option value="0">----请选择----</option>
													<c:forEach items="${warehouses}" var="warehouse">
														<option value="${warehouse.warehouseId}">${warehouse.warehouseName}</option>
													</c:forEach>
												</select>
											</div>
										</div>
										<div class="layui-col-lg3 layui-col-md3 layui-col-sm3 layui-col-md-offset1">
											<label class="layui-form-label">批准人:</label>
											<div class="layui-input-block">
												<select name="staffId" lay-verify="sunit" lay-search>
													<option value="0">----请选择----</option>
													<c:forEach items="${staffs}" var="staff">
														<option value="${staff.staffId}">${staff.staffName}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
									<div class="layui-form-item">
										<div class="layui-col-lg7 layui-col-md7 layui-col-sm7">
											<label class="layui-form-label">备注:</label>
											<div class="layui-input-block">
												<textarea placeholder="请输入内容" class="layui-textarea" name="billsRemark"></textarea>
												<input type="text" style="display:none" id="billsMoney4" name="billsMoney">
											</div>
										</div>									
									</div>
									<div class="layui-form-item" style="border-top: 1px solid rgb(246, 246, 246);">
										<table class="layui-table" id="selectMaterial4"
											lay-data="{
												height: 317,
												id: 'selectMaterial4',
												totalRow: true,
												method: 'post', //提交方式									
											}"
											lay-filter="selectMaterial4">
											<thead>
												<tr>
													<th
														lay-data="{field: 'rawMaterialNum', align: 'center', sort: true, totalRowText: '合计'}">原料编号</th>
													<th
														lay-data="{field: 'rawMaterialName', align: 'center', sort: true}">原料名称</th>
													<th
														lay-data="{field: 'unitName', align: 'center', sort: true}">单位</th>
													<th
														lay-data="{field: 'rawMaterialAmount', edit: 'text', align: 'center', sort: true, totalRow: true}">数量</th>
													<th
														lay-data="{field: 'rawMaterialPrice', align: 'center', sort: true}">单价</th>
													<th
														lay-data="{field: 'totalPrice', align: 'center', sort: true, totalRow: true}">金额</th>
													<th
														lay-data="{field: 'remark', edit: 'text', align: 'center', sort: true}">备注</th>
												</tr>
											</thead>
										</table>
									</div>
									<div class="layui-form-item">
										<div class="layui-btn-container" style="text-align: center;padding: 10px 0;">
											<button type="button" class="layui-btn" lay-submit 
												lay-filter="formCreditOrders">
												<i class="layui-icon layui-icon-add-1"> <span> 落
														单</span> </i>
											</button>
											<button type="button" class="layui-btn layui-btn-warm" id="btnReload4"
												onclick="reload('formCreditOrders', 'selectMaterial4');">
												<i class="layui-icon layui-icon-refresh-1"> <span>
														新 单</span> </i>
											</button>
										</div>
									</div>
								</form>
							</div>
						</div>
						<div id="modGodownOrders" style="display: none;">
							<table class="layui-table" id="findGodownOrders"
								lay-data="{
										height: 516,
										id: 'findGodownOrders',
										page: true,
										limit: 10,//每页默认显示的数量
										method: 'post', //提交方式 									
										}"
								lay-filter="findGodownOrders">
								<thead>
									<tr>
										<th
											lay-data="{field: 'billsEntryTimes', align: 'center', sort: true}">录单日期</th>
										<th
											lay-data="{field: 'billsNum', align: 'center', sort: true}">采购单号</th>
										<th
											lay-data="{field: 'supplierName', align: 'center', sort: true}">供应商</th>
										<th
											lay-data="{field: 'warehouseName', align: 'center', sort: true}">仓库</th>
									</tr>
								</thead>
							</table>
						</div>
					</div>
				</div>
				<div class="layui-tab-item">
					<div class="layui-card">
						<div class="layui-card-header">领料单信息：</div>
						<div class="layui-card-body" style="padding: 0;">
							<div class="laui-row">
								<form id="formStocksRequisition" class="layui-form" method="post" 
								action="${ctx}/servlet/EntryOrderServlet" style="margin-top: 26px;">
									<input type="text" style="display:none" name="type"
										value="addBills"><input type="text" style="display:none"
										name="billsType" value="stocksRequisition">
									<div class="layui-form-item">
										<div class="layui-col-lg3 layui-col-md3 layui-col-sm3">
											<label class="layui-form-label">录单日期:</label>
											<div class="layui-input-block">
												<input type="text" id="billsEnteryTime5" name="billsEntryTime" required
													lay-verify="required" autocomplete="off" class="layui-input"
													readonly="readonly">
											</div>
										</div>
										<div class="layui-col-lg3 layui-col-md3 layui-col-sm3 layui-col-md-offset1">
											<label class="layui-form-label">领料单号:</label>
											<div class="layui-input-block">
												<input type="text" id="billsNum5" name="billsNum" required
													lay-verify="required" autocomplete="off" class="layui-input"
													readonly="readonly">
											</div>
										</div>
									</div>
									<div class="layui-form-item">
										<div class="layui-col-lg3 layui-col-md3 layui-col-sm3">
											<label class="layui-form-label">部门:</label>
											<div class="layui-input-block">
												<select name="departmentId" lay-verify="sdepartment" lay-search>
													<option value="0">----请选择----</option>
													<c:forEach items="${departments}" var="department">
														<option value="${department.departmentId}">${department.departmentName}</option>
													</c:forEach>
												</select>
											</div>
										</div>
										<div class="layui-col-lg3 layui-col-md3 layui-col-sm3 layui-col-md-offset1">
											<label class="layui-form-label">经办人:</label>
											<div class="layui-input-block">
												<select name="staffID" lay-verify="fstaff" lay-search>
													<option value="0">----请选择----</option>
													<c:forEach items="${staffs}" var="staff">
														<option value="${staff.staffId}">${staff.staffName}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
									<div class="layui-form-item">
										<div class="layui-col-lg3 layui-col-md3 layui-col-sm3">
											<label class="layui-form-label">仓库:</label>
											<div class="layui-input-block">
												<select name="warehouseId" lay-verify="fwarehouse" 
												lay-search>
													<option value="0">----请选择----</option>
													<c:forEach items="${warehouses}" var="warehouse">
														<option value="${warehouse.warehouseId}">${warehouse.warehouseName}</option>
													</c:forEach>
												</select>
											</div>
										</div>
										<div class="layui-col-lg3 layui-col-md3 layui-col-sm3 layui-col-md-offset1">
											<label class="layui-form-label">批准人:</label>
											<div class="layui-input-block">
												<select name="staffId" lay-verify="sstaff" lay-search>
													<option value="0">----请选择----</option>
													<c:forEach items="${staffs}" var="staff">
														<option value="${staff.staffId}">${staff.staffName}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
									<div class="layui-form-item">
										<div class="layui-col-lg7 layui-col-md7 layui-col-sm7">
											<label class="layui-form-label">备注:</label>
											<div class="layui-input-block">
												<textarea placeholder="请输入内容" class="layui-textarea" name="billsRemark"></textarea>
												<input type="text" style="display:none" id="billsMoney5" name="billsMoney">
											</div>
										</div>									
									</div>
									<div class="layui-form-item" style="border-top: 1px solid rgb(246, 246, 246);">
										<table class="layui-table" id="selectMaterial5"
											lay-data="{
												height: 317,
												id: 'selectMaterial5',
												method: 'post', //提交方式 									
											}"
											lay-filter="selectMaterial5">
											<thead>
												<tr>
													<th
														lay-data="{field: 'rawMaterialNum', align: 'center', sort: true}">原料编号</th>
													<th
														lay-data="{field: 'rawMaterialName', align: 'center', sort: true}">原料名称</th>
													<th
														lay-data="{field: 'unitName', align: 'center', sort: true}">单位</th>
													<th
														lay-data="{field: 'rawMaterialAmount', edit: 'text', align: 'center', sort: true}">数量</th>
													<th
														lay-data="{field: 'rawMaterialPrice', align: 'center', sort: true}">价格</th>
													<th
														lay-data="{field: 'remark', edit: 'text', align: 'center', sort: true}">备注</th>
												</tr>
											</thead>
										</table>
									</div>
									<div class="layui-form-item">
										<div class="layui-btn-container" style="text-align: center;padding: 10px 0;">
											<button type="button" class="layui-btn layui-btn-normal"
												id="btnRequirement">
												<i class="layui-icon layui-icon-search"> <span>
														浏览需求</span> </i>
											</button>
											<button type="button" class="layui-btn" lay-submit
												lay-filter="formStocksRequisition">
												<i class="layui-icon layui-icon-add-1"> <span> 落
														单</span> </i>
											</button>
											<button type="button" class="layui-btn layui-btn-warm" id="btnReload5"
												onclick="reload('formStocksRequisition', 'selectMaterial5');">
												<i class="layui-icon layui-icon-refresh-1"> <span>
														新 单</span> </i>
											</button>
										</div>
									</div>
								</form>
							</div>
						</div>
						<div  id="modMaterialRequirement" style="display: none;">
							<div class="layui-btn-group btnRawMaterial" style="padding: 10px 0 0 82%;">
								<button class="layui-btn layui-btn-normal layui-btn-sm" data-type="getRequirementShow">新增</button>&nbsp;
								<button class="layui-btn layui-btn-warm layui-btn-sm" data-type="getRequirementCheckData">修改</button>
							</div>
							<table class="layui-table" id="findMaterialRequirement"
								lay-data="{
										height: 396,
										id: 'findMaterialRequirement',
										page: true,
										limit: 8,//每页默认显示的数量
										limits: [8,20,30,50,60],
										method: 'post', //提交方式 									
										}"
								lay-filter="findMaterialRequirement">
								<thead>
									<tr>
										<th lay-data="{type: 'checkbox', fixed: 'left'}"></th>
										<th
											lay-data="{field: 'rawMaterialNum', align: 'center', sort: true}">编号</th>
										<th
											lay-data="{field: 'rawMaterialName', align: 'center', sort: true}">原料名称</th>
										<th
											lay-data="{field: 'unitName', align: 'center', sort: true}">原料单位</th>
										<th
											lay-data="{field: 'reportAmount', align: 'center', sort: true}">库存数量</th>
										<th
											lay-data="{field: 'quantityRequired', width: 125, align: 'center', sort: true}">需求数量</th>
									</tr>
								</thead>
							</table>
							<div class="layui-btn-container" style="margin-top: 3%;text-align: center;">
								<button type="button" class="layui-btn"
									onclick="selectMaterial('findMaterialRequirement', 'selectMaterial5');">确
									认</button>
								<button type="button" class="layui-btn layui-btn-danger"
									onclick="modHide();">取 消</button>
							</div>
						</div>
						<div id="modAddEidtRequirement" style="display: none;">
							<div
								class="layui-col-lg9 layui-col-md9 layui-col-sm9 layui-col-md-offset1">
								<form id="formMaterialRequirement" class="layui-form" method="post"
									action="${ctx}/servlet/EntryOrderServlet" style="margin-top: 26px;">
									<input type="text" style="display:none" name="type"
										value="addEidtSave"> <input type="text" style="display:none"
										name="materialRequirementId">
									<div class="layui-form-item">
										<label class="layui-form-label">部门</label>
										<div class="layui-input-block">
											<select name="departmentId" lay-verify="sdepartment" lay-search>
												<option value="0">----请选择----</option>
												<c:forEach items="${departments}" var="department">
													<option value="${department.departmentId}">${department.departmentName}</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<div class="layui-form-item">
										<label class="layui-form-label">原料大类</label>
										<div class="layui-input-block">
											<select name="rawMaterialBigId" lay-search lay-filter="srawMaterialBig" lay-verify="srawMaterialBig">
												<option value="0">----请选择----</option>
												<c:forEach items="${bigs}" var="big">
													<option value="${big.rawMaterialBigId}">${big.rawMaterialBigName}</option>
												</c:forEach>
											</select>
										</div>
									</div>		
									<div class="layui-form-item">
										<label class="layui-form-label">原料小类</label>
										<div class="layui-input-block">
											<select class="srawMaterialSmall" name="rawMaterialSmallId" lay-filter="srawMaterialSmall"  lay-verify="srawMaterialSmall" lay-search>
												
											</select>
										</div>
									</div>
									<div class="layui-form-item">
										<label class="layui-form-label">原料</label>
										<div class="layui-input-block">
											<select class="srawMaterial" name="rawMaterialId" lay-verify="srawMaterial" lay-search>
												
											</select>
										</div>
									</div>
									<div class="layui-form-item">
										<label class="layui-form-label">需求数量</label>
										<div class="layui-input-block">
											<input type="text" name="quantityRequired" required
												lay-verify="fdouble" autocomplete="off" class="layui-input">
										</div>
									</div>
									<div class="layui-form-item"
										style="text-align: center;margin-left: 18%;">
										<div class="layui-btn-container">
											<button type="button" class="layui-btn"  lay-submit id="btnXQ" 
											lay-filter="fMaterialRequirement">保存</button>
											<button type="button" class="layui-btn layui-btn-danger Cancel" >取消</button>
										</div>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
				<div class="layui-tab-item">
					<div class="layui-card">
						<div class="layui-card-header">领料退货单信息：</div>
						<div class="layui-card-body" style="padding: 0;">
							<div class="laui-row">
								<form id="formPickingCreditOrders" class="layui-form" method="post" 
								action="${ctx}/servlet/EntryOrderServlet" style="margin-top: 26px;">
									<input type="text" style="display:none" name="type"
										value="addBills"><input type="text" style="display:none"
										name="billsType" value="pickingCreditOrders">
									<div class="layui-form-item">
										<div class="layui-col-lg3 layui-col-md3 layui-col-sm3">
											<label class="layui-form-label">录单日期:</label>
											<div class="layui-input-block">
												<input type="text" id="billsEnteryTime6" name="billsEntryTime" required
													lay-verify="required" autocomplete="off" class="layui-input"
													readonly="readonly">
											</div>
										</div>
										<div class="layui-col-lg3 layui-col-md3 layui-col-sm3 layui-col-md-offset1">
											<label class="layui-form-label">领退单号:</label>
											<div class="layui-input-block">
												<input type="text" id="billsNum6" name="billsNum" required
													lay-verify="required" autocomplete="off" class="layui-input"
													readonly="readonly">
											</div>
										</div>
										<div class="layui-col-lg3 layui-col-md3 layui-col-sm3">
											<div class="layui-input-block">
												<button type="button" class="layui-btn" id="btnStocksRequisition">
													<i class="layui-icon layui-icon-add-1"> <span> 从领料单录入</span>
													</i>
												</button>
											</div>											
										</div>
									</div>
									<div class="layui-form-item">
										<div class="layui-col-lg3 layui-col-md3 layui-col-sm3">
											<label class="layui-form-label">部门:</label>
											<div class="layui-input-block">
												<select name="departmentId" lay-verify="sdepartment" lay-search>
													<option value="0">----请选择----</option>
													<c:forEach items="${departments}" var="department">
														<option value="${department.departmentId}">${department.departmentName}</option>
													</c:forEach>
												</select>
											</div>
										</div>
										<div class="layui-col-lg3 layui-col-md3 layui-col-sm3 layui-col-md-offset1">
											<label class="layui-form-label">经办人:</label>
											<div class="layui-input-block">
												<select name="staffID" lay-verify="fstaff" lay-search>
													<option value="0">----请选择----</option>
													<c:forEach items="${staffs}" var="staff">
														<option value="${staff.staffId}">${staff.staffName}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
									<div class="layui-form-item">
										<div class="layui-col-lg3 layui-col-md3 layui-col-sm3">
											<label class="layui-form-label">仓库:</label>
											<div class="layui-input-block">
												<select name="warehouseId" lay-verify="fwarehouse"
												lay-search>
													<option value="0">----请选择----</option>
													<c:forEach items="${warehouses}" var="warehouse">
														<option value="${warehouse.warehouseId}">${warehouse.warehouseName}</option>
													</c:forEach>
												</select>
											</div>
										</div>
										<div class="layui-col-lg3 layui-col-md3 layui-col-sm3 layui-col-md-offset1">
											<label class="layui-form-label">批准人:</label>
											<div class="layui-input-block">
												<select name="staffId" lay-verify="sstaff" lay-search>
													<option value="0">----请选择----</option>
													<c:forEach items="${staffs}" var="staff">
														<option value="${staff.staffId}">${staff.staffName}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
									<div class="layui-form-item">
										<div class="layui-col-lg7 layui-col-md7 layui-col-sm7">
											<label class="layui-form-label">备注:</label>
											<div class="layui-input-block">
												<textarea placeholder="请输入内容" class="layui-textarea" name="billsRemark"></textarea>
												<input type="text" style="display:none" id="billsMoney6" name="billsMoney">
											</div>
										</div>									
									</div>
									<div class="layui-form-item" style="border-top: 1px solid rgb(246, 246, 246);">
										<table class="layui-table" id="selectMaterial6"
											lay-data="{
												height: 317,
												id: 'selectMaterial6',
												totalRow: true,
												method: 'post', //提交方式 									
											}"
											lay-filter="selectMaterial6">
											<thead>
												<tr>
													<th
														lay-data="{field: 'rawMaterialNum', align: 'center', sort: true, totalRowText: '合计'}">原料编号</th>
													<th
														lay-data="{field: 'rawMaterialName', align: 'center', sort: true}">原料名称</th>
													<th
														lay-data="{field: 'unitName', align: 'center', sort: true}">单位</th>
													<th
														lay-data="{field: 'rawMaterialAmount', edit: 'text', align: 'center', sort: true, totalRow: true}">数量</th>
													<th
														lay-data="{field: 'rawMaterialPrice', align: 'center', sort: true}">价格</th>
													<th
														lay-data="{field: 'totalPrice', align: 'center', sort: true, totalRow: true}">金额</th>
													<th
														lay-data="{field: 'remark', edit: 'text', align: 'center', sort: true}">备注</th>
												</tr>
											</thead>
										</table>
									</div>
									<div class="layui-form-item">
										<div class="layui-btn-container" style="text-align: center;padding: 10px 0;">
											<button type="button" class="layui-btn" lay-submit
												lay-filter="formPickingCreditOrders">
												<i class="layui-icon layui-icon-add-1"> <span> 落
														单</span> </i>
											</button>
											<button type="button" class="layui-btn layui-btn-warm" id="btnReload6"
												onclick="reload('formPickingCreditOrders', 'selectMaterial6');">
												<i class="layui-icon layui-icon-refresh-1"> <span>
														新 单</span> </i>
											</button>
										</div>
									</div>
								</form>
							</div>
						</div>
						<div id="modStocksRequisition" style="display: none;">
							<table class="layui-table" id="findStocksRequisition"
								lay-data="{
										height: 516,
										id: 'findStocksRequisition',
										page: true,
										limit: 10,//每页默认显示的数量
										method: 'post', //提交方式 									
										}"
								lay-filter="findStocksRequisition">
								<thead>
									<tr>
										<th
											lay-data="{field: 'billsEntryTimes', align: 'center', sort: true}">录单日期</th>
										<th
											lay-data="{field: 'billsNum', align: 'center', sort: true}">采购单号</th>
										<th
											lay-data="{field: 'departmentName', align: 'center', sort: true}">供应商</th>
										<th
											lay-data="{field: 'warehouseName', align: 'center', sort: true}">仓库</th>
									</tr>
								</thead>
							</table>
						</div>
					</div>					
				</div>
				<div class="layui-tab-item">
					<div class="layui-card">
						<div class="layui-card-header">仓库调拨单信息：</div>
						<div class="layui-card-body" style="padding: 0;">
							<div class="laui-row">
								<form id="formWarehouseTransferOrder" class="layui-form" method="post" 
								action="${ctx}/servlet/EntryOrderServlet" style="margin-top: 26px;">
									<input type="text" style="display:none" name="type"
										value="addBills"><input type="text" style="display:none"
										name="billsType" value="warehouseTransferOrder">
									<div class="layui-form-item">
										<div class="layui-col-lg3 layui-col-md3 layui-col-sm3">
											<label class="layui-form-label">录单日期:</label>
											<div class="layui-input-block">
												<input type="text" id="billsEnteryTime7" name="billsEntryTime" required
													lay-verify="required" autocomplete="off" class="layui-input"
													readonly="readonly">
											</div>
										</div>
										<div class="layui-col-lg3 layui-col-md3 layui-col-sm3 layui-col-md-offset1">
											<label class="layui-form-label">调拨单号:</label>
											<div class="layui-input-block">
												<input type="text" id="billsNum7" name="billsNum" required
													lay-verify="required" autocomplete="off" class="layui-input"
													readonly="readonly">
											</div>
										</div>
									</div>
									<div class="layui-form-item">
										<div class="layui-col-lg3 layui-col-md3 layui-col-sm3">
											<label class="layui-form-label">调出仓库:</label>
											<div class="layui-input-block">
												<select name="warehouseID" lay-verify="fwarehouse" lay-search>
													<option value="0">----请选择----</option>
														<c:forEach items="${warehouses}" var="warehouse">
															<option value="${warehouse.warehouseId}">${warehouse.warehouseName}</option>
														</c:forEach>
												</select>
											</div>
										</div>
										<div class="layui-col-lg3 layui-col-md3 layui-col-sm3 layui-col-md-offset1">
											<label class="layui-form-label">经办人:</label>
											<div class="layui-input-block">
												<select name="staffID" lay-verify="fstaff" lay-search>
													<option value="0">----请选择----</option>
													<c:forEach items="${staffs}" var="staff">
														<option value="${staff.staffId}">${staff.staffName}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
									<div class="layui-form-item">
										<div class="layui-col-lg3 layui-col-md3 layui-col-sm3">
											<label class="layui-form-label">调入仓库:</label>
											<div class="layui-input-block">
												<select name="warehouseId" lay-verify="fwarehouse"
												lay-search>
													<option value="0">----请选择----</option>
													<c:forEach items="${warehouses}" var="warehouse">
														<option value="${warehouse.warehouseId}">${warehouse.warehouseName}</option>
													</c:forEach>
												</select>
											</div>
										</div>
										<div class="layui-col-lg3 layui-col-md3 layui-col-sm3 layui-col-md-offset1">
											<label class="layui-form-label">批准人:</label>
											<div class="layui-input-block">
												<select name="staffId" lay-verify="sstaff" lay-search>
													<option value="0">----请选择----</option>
													<c:forEach items="${staffs}" var="staff">
														<option value="${staff.staffId}">${staff.staffName}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
									<div class="layui-form-item">
										<div class="layui-col-lg7 layui-col-md7 layui-col-sm7">
											<label class="layui-form-label">备注:</label>
											<div class="layui-input-block">
												<textarea placeholder="请输入内容" class="layui-textarea" name="billsRemark"></textarea>
												<input type="text" style="display:none" id="billsMoney7" name="billsMoney">
											</div>
										</div>									
									</div>
									<div class="layui-form-item" style="border-top: 1px solid rgb(246, 246, 246);">
										<table class="layui-table" id="selectMaterial7"
											lay-data="{
												height: 317,
												id: 'selectMaterial7',
												method: 'post', //提交方式							
											}"
											lay-filter="selectMaterial7">
											<thead>
												<tr>
													<th
														lay-data="{field: 'rawMaterialNum', align: 'center', sort: true}">原料编号</th>
													<th
														lay-data="{field: 'rawMaterialName', align: 'center', sort: true}">原料名称</th>
													<th
														lay-data="{field: 'unitName', align: 'center', sort: true}">单位</th>
													<th
														lay-data="{field: 'rawMaterialAmount', edit: 'text', align: 'center', sort: true}">数量</th>
													<th
														lay-data="{field: 'rawMaterialPrice', align: 'center', sort: true}">单价</th>
													<th
														lay-data="{field: 'remark', edit: 'text', align: 'center', sort: true}">备注</th>
												</tr>
											</thead>
										</table>
									</div>
									<div class="layui-form-item">
										<div class="layui-btn-container" style="text-align: center;padding: 10px 0;">
											<button type="button" class="layui-btn layui-btn-normal"
												id="btnWarehouse">
												<i class="layui-icon layui-icon-search"> <span>
														浏览仓库</span> </i>
											</button>
											<button type="button" class="layui-btn" lay-submit
												lay-filter="formWarehouseTransferOrder">
												<i class="layui-icon layui-icon-add-1"> <span> 落
														单</span> </i>
											</button>
											<button type="button" class="layui-btn layui-btn-warm" id="btnReload7"
												onclick="reload('formWarehouseTransferOrder', 'selectMaterial7');">
												<i class="layui-icon layui-icon-refresh-1"> <span>
														新 单</span> </i>
											</button>
										</div>
									</div>
								</form>
							</div>
						</div>
						<div  id="modRawMaterial3" style="display: none;">
							<table class="layui-table" id="findMaterial3"
								lay-data="{
										height: 438,
										id: 'findMaterial3',
										page: true,
										limit: 8,//每页默认显示的数量
										limits: [8,20,30,50,60],
										method: 'post', //提交方式 									
										}"
								lay-filter="findMaterial3">
								<thead>
									<tr>
										<th lay-data="{type: 'checkbox', fixed: 'left'}"></th>
										<th
											lay-data="{field: 'rawMaterialNum', align: 'center', sort: true}">编号</th>
										<th
											lay-data="{field: 'rawMaterialName', align: 'center', sort: true}">原料名称</th>
										<th
											lay-data="{field: 'rawMaterialBigName', align: 'center', sort: true}">原料类别</th>
										<th
											lay-data="{field: 'unitName', align: 'center', sort: true}">原料单位</th>
										<th
											lay-data="{field: 'rawMaterialPrice', align: 'center', sort: true}">原料单价</th>
										<th
											lay-data="{field: 'reportAmount', align: 'center', sort: true}">库存数量</th>
									</tr>
								</thead>
							</table>
							<div class="layui-btn-container" style="margin-top: 3%;text-align: center;">
								<button type="button" class="layui-btn"
									onclick="selectMaterial('findMaterial3', 'selectMaterial7');">确 认</button>
								<button type="button" class="layui-btn layui-btn-danger"
									onclick="modHide();">取 消</button>
							</div>
						</div>
					</div>
				</div>
				<div class="layui-tab-item">
					<div class="layui-card">
						<div class="layui-card-header">库存盘点单信息：</div>
						<div class="layui-card-body" style="padding: 0;">
							<div class="laui-row">
								<form id="formRepertoryCheck" class="layui-form" method="post" 
								action="${ctx}/servlet/EntryOrderServlet" style="margin-top: 26px;">
									<input type="text" style="display:none" name="type"
										value="addBills"><input type="text" style="display:none"
										name="billsType" value="repertoryCheck">
									<div class="layui-form-item">
										<div class="layui-col-lg3 layui-col-md3 layui-col-sm3">
											<label class="layui-form-label">录单日期:</label>
											<div class="layui-input-block">
												<input type="text" id="billsEnteryTime8" name="billsEntryTime" required
													lay-verify="required" autocomplete="off" class="layui-input"
													readonly="readonly">
											</div>
										</div>
										<div class="layui-col-lg3 layui-col-md3 layui-col-sm3 layui-col-md-offset1">
											<label class="layui-form-label">盘点单号:</label>
											<div class="layui-input-block">
												<input type="text" id="billsNum8" name="billsNum" required
													lay-verify="required" autocomplete="off" class="layui-input"
													readonly="readonly">
											</div>
										</div>
										<div class="layui-col-lg5 layui-col-md5 layui-col-sm5">
											<div class="layui-input-block">
												<button type="button" class="layui-btn" id="btnInCheck">
													<i class="layui-icon layui-icon-search"> <span> 进入盘点</span>
													</i>
												</button>
												<button type="button" class="layui-btn" lay-submit 
													lay-filter="formRepertoryCheck">
													<i class="layui-icon layui-icon-add-1"> <span> 盘点落单</span>
													</i>
												</button>
												<button type="button" class="layui-btn layui-btn-warm" id="btnReload8"
													onclick="reload('formRepertoryCheck', 'findCheckMaterial');">
													<i class="layui-icon layui-icon-refresh-1"> <span>
															盘点新单</span> </i>
												</button>
											</div>											
										</div>
									</div>
									<div class="layui-form-item">
										<div class="layui-col-lg3 layui-col-md3 layui-col-sm3">
											<label class="layui-form-label">盘点人:</label>
											<div class="layui-input-block">
												<select name="staffId" lay-verify="dstaff" lay-search>
													<option value="0">----请选择----</option>
													<c:forEach items="${staffs}" var="staff">
														<option value="${staff.staffId}">${staff.staffName}</option>
													</c:forEach>
												</select>
											</div>
										</div>
										<div class="layui-col-lg3 layui-col-md3 layui-col-sm3 layui-col-md-offset1">
											<label class="layui-form-label">仓库名称:</label>
											<div class="layui-input-block">
												<select name="warehouseId" lay-verify="fwarehouse" 
												lay-search>
													<option value="0">----请选择----</option>
													<c:forEach items="${warehouses}" var="warehouse">
														<option value="${warehouse.warehouseId}">${warehouse.warehouseName}</option>
													</c:forEach>
												</select>
											</div>
										</div>										
									</div>									
									<div class="layui-form-item">
										<div class="layui-col-lg7 layui-col-md7 layui-col-sm7">
											<label class="layui-form-label">备注:</label>
											<div class="layui-input-block">
												<textarea placeholder="请输入内容" class="layui-textarea" name="billsRemark"></textarea>
												<input type="text" style="display:none" id="billsMoney8" name="billsMoney">
											</div>
										</div>									
									</div>
									<div class="layui-form-item" style="border-top: 1px solid rgb(246, 246, 246);">
										<table class="layui-table" id="findCheckMaterial"
											lay-data="{
												height: 317,
												id: 'findCheckMaterial',
												totalRow: true,
												method: 'post', //提交方式								
											}"
											lay-filter="findCheckMaterial">
											<thead>
												<tr>
													<th
														lay-data="{field: 'rawMaterialNum', align: 'center', sort: true, totalRowText: '合计'}">编号</th>
													<th
														lay-data="{field: 'rawMaterialName', align: 'center', sort: true}">名称</th>
													<th
														lay-data="{field: 'unitName', align: 'center', sort: true}">单位</th>
													<th
														lay-data="{field: 'rawMaterialPrice', align: 'center', sort: true}">单价</th>
													<th
														lay-data="{field: 'reportAmount', align: 'center', sort: true, totalRow: true}">原来库存</th>
													<th
														lay-data="{field: 'stockAmount', align: 'center', sort: true, totalRow: true}">库存金额</th>
													<th
														lay-data="{field: 'inventoryCount', align: 'center', sort: true, totalRow: true}">盘点数</th>
													<th
														lay-data="{field: 'checkAmount', align: 'center', sort: true, totalRow: true}">盘点金额</th>
													<th
														lay-data="{field: 'numberOfProfitAndLoss', align: 'center', sort: true, totalRow: true}">盈亏数</th>
													<th
														lay-data="{field: 'profitAndLossAmount', edit: 'text', align: 'center', sort: true, totalRow: true}">盈亏金额</th>
												</tr>
											</thead>
										</table>
									</div>
								</form>
							</div>							
						</div>
						<div  id="modCheckMaterial" style="display: none;">
							<div class="layui-card" style="margin-bottom: 0;">
								<div class="layui-card-header">原材料列表信息:</div>
								<div class="layui-card-body" style="padding: 0;">
									<form class="layui-form" action="">
										<div class="layui-form-item" style="padding-top: 16px;margin-bottom: 0;">
											<div class="layui-col-lg4 layui-col-md4 layui-col-sm4">
												<label class="layui-form-label">编号:</label>
												<div class="layui-input-block">
													<input type="number" name="num" id="rawMaterialNum"
														autocomplete="off" class="layui-input">
												</div>
											</div>
											<div class="layui-col-lg4 layui-col-md4 layui-col-sm4">
												<label class="layui-form-label">名称:</label>
												<div class="layui-input-block">
													<input type="text" name="name" id="rawMaterialName"
														autocomplete="off" class="layui-input"
														onKeyUp="chkUper(this)" onafterpaste="chkUper(this)">
												</div>
											</div>
											<div
												class="layui-col-lg1 layui-col-md1 layui-col-sm1 layui-col-md-offset1">
												<div class="layui-btn-container btnRawMaterial">
													<button type="button" class="layui-btn" data-type="reload">
														<i class="layui-icon layui-icon-search"> <span>
																查 询</span> </i>
													</button>													
												</div>
											</div>
										</div>
									</form>
									<table class="layui-table"
										lay-data="{
											height: 199,
											id: 'findMaterial4',
											page: true,
											limit: 3,//每页默认显示的数量
											limits: [3,6,10,20,30,50],
											method: 'post', //提交方式 									
										}"
										lay-filter="findMaterial4">
										<thead>
											<tr>
												<th
													lay-data="{field: 'rawMaterialNum', align: 'center', sort: true}">编号</th>
												<th
													lay-data="{field: 'rawMaterialName', align: 'center', sort: true}">原料名称</th>
											</tr>
										</thead>
									</table>
								</div>								
							</div>
							<form class="layui-form" id="formCheck"
								action="${ctx}/servlet/EntryOrderServlet" method="post" 
								style="border-bottom: 1px solid rgb(246, 246, 246);border-top: 1px solid rgb(246, 246, 246);">
								<input type="text" style="display:none" name="type"
									value="addEidtSave"><input type="text" style="display:none"
									name="addType" value="addCheck"><input type="text"
									style="display:none" name="rawMaterialId"><input type="text"
									style="display:none" name="warehouseId">
								<div class="layui-form-item" style="padding-top: 16px;">
									<div class="layui-col-lg4 layui-col-md4 layui-col-sm4">
										<label class="layui-form-label">编号:</label>
										<div class="layui-input-block">
											<input type="number" name="rawMaterialNum" autocomplete="off"
												class="layui-input" readonly="readonly">
										</div>
									</div>
									<div class="layui-col-lg4 layui-col-md4 layui-col-sm4">
										<label class="layui-form-label">名称:</label>
										<div class="layui-input-block">
											<input type="text" name="rawMaterialName"  autocomplete="off" 
											class="layui-input" readonly="readonly">
										</div>
									</div>
									<div class="layui-col-lg3 layui-col-md3 layui-col-sm3">
										<label class="layui-form-label">单位:</label>
										<div class="layui-input-block">
											<input type="text" name="unitName" autocomplete="off"
												class="layui-input" readonly="readonly">
										</div>
									</div>
								</div>
								<div class="layui-form-item">
									<div class="layui-col-lg4 layui-col-md4 layui-col-sm4">
										<label class="layui-form-label">库存数:</label>
										<div class="layui-input-block">
											<input type="number" name="reportAmount" autocomplete="off"
												class="layui-input">
										</div>
									</div>
									<div class="layui-col-lg4 layui-col-md4 layui-col-sm4">
										<label class="layui-form-label">盘点数:</label>
										<div class="layui-input-block">
											<input type="number" name="inventoryCount"
												autocomplete="off" class="layui-input">
										</div>
									</div>
									<div class="layui-col-lg4 layui-col-md4 layui-col-sm4">
										<div class="layui-btn-container btnRawMaterial" style="padding-left: 20%;">
											<button type="button" class="layui-btn" lay-submit 
												lay-filter="formCheck">
												<i class="layui-icon layui-icon-add-1"> <span> 添加</span>
												</i>
											</button>
											<button type="button" class="layui-btn" data-type="delCheck">
												<i class="layui-icon layui-icon-delete"> <span>
														删除</span> </i>
											</button>
										</div>
									</div>
								</div>
							</form>
							<div class="layui-card">
								<div class="layui-card-header">	盘点信息:</div>
								<div class="layui-card-body" style="padding: 0;">									
									<table class="layui-table"
										lay-data="{
											height: 199,
											id: 'findCheck',
											page: true,
											limit: 3,//每页默认显示的数量
											limits: [3,6,10,20,30,50],
											method: 'post', //提交方式 									
										}"
										lay-filter="findCheck">
										<thead>
											<tr>
												<th lay-data="{type: 'checkbox', fixed: 'left'}"></th>
												<th
													lay-data="{field: 'rawMaterialNum', align: 'center', sort: true}">编号</th>
												<th
													lay-data="{field: 'rawMaterialName', align: 'center', sort: true}">原料名称</th>
												<th
													lay-data="{field: 'unitName', align: 'center', sort: true}">原料单位</th>
												<th
													lay-data="{field: 'rawMaterialPrice', align: 'center', sort: true}">原料单价</th>
												<th
													lay-data="{field: 'reportAmount', align: 'center', sort: true}">原来库存</th>
												<th
													lay-data="{field: 'inventoryCount', align: 'center', sort: true}">盘点数</th>
												<th
													lay-data="{field: 'numberOfProfitAndLoss', align: 'center', sort: true}">盈亏数</th>
											</tr>
										</thead>
									</table>
								</div>
							</div>
							<div class="layui-btn-container" style="margin-top: 3%;text-align: center;">
								<button type="button" class="layui-btn"  id="btnConfirm">
									<i class="layui-icon layui-icon-ok"> <span> 确 认</span> </i>
								</button>
								<button type="button" class="layui-btn layui-btn-danger" onclick="modHide();">
									<i class="layui-icon layui-icon-close"> <span> 取消</span> </i>
								</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script>
		var layer;
		var table;
		var form;
		$(function(){
			getbillsNum();
		});
		function getbillsNum(){
			nowBills("billsEnteryTime1", "billsNum1", "DH");
			nowBills("billsEnteryTime2", "billsNum2", "PS");
			nowBills("billsEnteryTime3", "billsNum3", "RK");
			nowBills("billsEnteryTime4", "billsNum4", "TH");
			nowBills("billsEnteryTime5", "billsNum5", "LL");
			nowBills("billsEnteryTime6", "billsNum6", "LT");
			nowBills("billsEnteryTime7", "billsNum7", "DB");
			nowBills("billsEnteryTime8", "billsNum8", "PD");
		}
		//获取当前时间
       function nowBills(Id, id, str) {
            var t = new Date();
            var y = t.getFullYear();
            var m = t.getMonth() + 1;
            if (m < 10) {
				m = "0" + m;
			}
            var d = t.getDate();
            var nowDate = y + "-" + m + "-" + d;
            $("#" + id).val(str + new Date().toLocaleString().match(/\d+/g).join(""));
            $("#" + Id).val(nowDate);
        }
		//注意：选项卡 依赖 element 模块，否则无法进行功能性操作
		layui.use(["element", "form", "table", "layer"], function() {
			element = layui.element,
			form = layui.form,//表单
			table = layui.table,
			layer = layui.layer;
			
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
				fsupplier: function(value) {
					if (value == "0" || value == "") {
						return "请选择一个供应商";
					}
				},
				fwarehouse: function(value) {
					if (value == "0" || value == "") {
						return "请选择一个仓库";
					}
				},
				fstaff: function(value) {
					if (value == "0" || value == "") {
						return "请选择一个经办人";
					}
				},
				sstaff: function(value) {
					if (value == "0" || value == "") {
						return "请选择一个批准人";
					}
				},
				dstaff: function(value) {
					if (value == "0" || value == "") {
						return "请选择一个盘点人";
					}
				},
				sunit: function(value) {
					if (value == "0" || value == "") {
						return "请选择一个单位";
					}
				},
				srawMaterialBig: function(value) {
					if (value == "0" || value == "") {
						return "请选择一个原料大类";
					}
				},
				srawMaterialSmall: function(value) {
					if (value == "0" || value == "") {
						return "请选择一个原料小类";
					}
				},
				srawMaterial: function(value) {
					if (value == "0" || value == "") {
						return "请选择一个原料";
					}
				},
				fdouble: function (value){
					if(/[^\d.]/g.test(value)){
				      	return "只能输入数字";
				    }
				},
				sdepartment: function (value){
					if (value == "0" || value == "") {
						return "请选择一个部门";
					}
				}
			});			
			
			var $ = layui.$, active = {
				getShow: function () {
					$("#formRawMaterial").resetForm();//重置表单
					$("#btnRawMaterial").empty().append("保存");	
					$.post("${ctx}/servlet/RawMaterialServlet?type=getNumber&rawMaterialType=rawMaterial",
					function(data){
						if (data != null) {
							$("#formRawMaterial [name='rawMaterialNum']")
							.val(data.rawMaterialNum);
							layer.open({
						    	type: 1,//类型
						      	title: '<i class="layui-icon layui-icon-add-1"> 新增原料信息</i>',
						      	offset: ['5%','33%'],
						      	area:['600px','460px'],//定义宽和高
						      	shadeClose: true,//点击遮罩层关闭
						      	content: $("#modAddEidtMaterial")//打开的内容
							});
						}
					}, "json");
				},
				getCheckData: function(){ //获取选中数据
			    	var checkStatus = table.checkStatus('findMaterial1')
			      	,data = checkStatus.data;			      	
			      	$("#formRawMaterial").resetForm();//重置表单
			      	$("#btnRawMaterial").empty().append("修改");
			      	if (data.length === 0) {
						layer.msg('请选择一行');
					} else if (data.length > 1) {
						layer.msg('只能同时编辑一个');
					} else {
						data = data[0];
						loadDatatoForm("formRawMaterial", data);
				      	layer.open({
					    	type: 1,//类型
					      	title: '<i class="layui-icon layui-icon-add-1"> 修改原料信息</i>',
					      	offset: ['5%','33%'],
					      	area:['600px','460px'],//定义宽和高
					      	shadeClose: true,//点击遮罩层关闭
					      	content: $("#modAddEidtMaterial")//打开的内容
						});
					}			      	
			    },
			    getRequirementShow: function() {
			    	$("#formMaterialRequirement").resetForm();//重置表单
					$("#btnXQ").empty().append("保存");	
					$("#formMaterialRequirement [name='departmentId']").
					val($("#formStocksRequisition [name='departmentId']").val());
					form.render('select');
					layer.open({
				    	type: 1,//类型
				      	title: '<i class="layui-icon layui-icon-add-1"> 新增部门原料需求信息</i>',
				      	offset: ['5%','33%'],
				      	area:['600px','420px'],//定义宽和高
				      	shadeClose: true,//点击遮罩层关闭
				      	content: $("#modAddEidtRequirement")//打开的内容
					});
			    },
			    getRequirementCheckData: function() {
			    	var checkStatus = table.checkStatus('findMaterialRequirement')
			      	,data = checkStatus.data;			      	
			      	$("#formMaterialRequirement").resetForm();//重置表单
			      	$("#btnXQ").empty().append("修改");
			      	if (data.length === 0) {
						layer.msg('请选择一行');
					} else if (data.length > 1) {
						layer.msg('只能同时编辑一个');
					} else {
						data = data[0];						
						$.ajaxSettings.ansyc = false;
						loadDatatoForm("formMaterialRequirement", data);
						$.post("${ctx}/servlet/EntryOrderServlet?type=smallOption&optionType=selectSmall&rawMaterialBigId=" + data.rawMaterialBigId,function(datas){
			        		if (datas != null) {	
			        			$(".srawMaterialSmall").empty().append("<option value='0'>----请选择----</option>");
			        			for ( var i = 0; i < datas.length; i++) {
									$(".srawMaterialSmall").append("<option value=" + datas[i].rawMaterialSmallId + ">" + datas[i].rawMaterialSmallName + "</option>");
								}			
								$(".srawMaterialSmall").val(data.rawMaterialSmallId);
								form.render('select');
								$.post("${ctx}/servlet/EntryOrderServlet?type=smallOption&optionType=selectRawMaterial&rawMaterialSmallId=" + data.rawMaterialSmallId,function(datas){
					        		if (datas != null) {	
					        			$(".srawMaterial").empty().append("<option value='0'>----请选择----</option>");
					        			for ( var i = 0; i < datas.length; i++) {
											$(".srawMaterial").append("<option value=" + datas[i].rawMaterialId + ">" + datas[i].rawMaterialName + "</option>");
										}		
										$(".srawMaterial").val(data.rawMaterialId);		
										form.render('select');		
									}
					        	}, "json");	
							}
			        	}, "json"); 		
				      	layer.open({
					    	type: 1,//类型
					      	title: '<i class="layui-icon layui-icon-add-1"> 修改部门原料需求信息</i>',
					      	offset: ['5%','33%'],
					      	area:['600px','420px'],//定义宽和高
					      	shadeClose: true,//点击遮罩层关闭
					      	content: $("#modAddEidtRequirement")//打开的内容
						});
					}	
			    },
			    reload: function() {
			    	var num = $("#rawMaterialNum").val();
			    	var name = $("#rawMaterialName").val();
			    	table.reload('findMaterial4', {
			    		page: {
			    			curr: 1//重新从第一页开始
			    		},
			    		where: {
			    			rawMaterialNum: num,
			    			rawMaterialName: name
			    		}
			    	});
			    },
			    delCheck: function () {
					var checkStatus = table.checkStatus('findCheck')
			      	,data = checkStatus.data;
			      	var warehouseId = $("#formCheck [name='warehouseId']").val();
			      	if (data.length === 0) {
						layer.msg("请选择一行数据!");
					} else {						
						layer.confirm("请确认是否删除盘点信息?",{
							icon : 3,
							btn : ['确定', '取消' ]
						}, function (layerIndex) {
							layer.close(layerIndex);	
							var returnLength = 0;						
							for ( var i = 0; i < data.length; i++)	{								
								$.ajax({
									url : "${ctx}/servlet/EntryOrderServlet?type=addEidtSave&addType=delCheck&checkId="+ checkStatus.data[i].checkId, //所需要的列表接口地址（控制器的方法）
									type : "get",//数据传输通道的类型
									async : false,
									dataType : "json",//传输的数据的类型
									success : function(datas) {//直接理解为回调函数
										if (datas.state) {
											returnLength++;
										} else {
											layer.msg(datas.msg,
											{
												icon : 2,
												skin : "layui-layer-molv"
											});												
										}
									}
								});		
							}	
							if (returnLength == data.length) {
								reloadCheck(warehouseId);
							}				
						});											
					}					
				}
			};
			$('.btnRawMaterial .layui-btn').on('click', function(){
				var type = $(this).data('type');
				active[type] ? active[type].call(this) : '';
			});
			
			/*------------------------采购单---------------------------*/
			$("#btnMaterial1").click(function(){
				var supplierId = $("#formPurchaseOrders [name='supplierId']").val();
				if (supplierId > 0) {
					showTable("findMaterial1", 
						"${ctx}/servlet/EntryOrderServlet?type=findMaterial&findType=findMaterial&supplierId=" + supplierId, 
						"layui-icon-add-1", "原料信息","modRawMaterial1");
				} else {
					layer.msg("请选择一个供应商");
				}				
			});
			
			form.on("select(ssupplier)", function(data) {
				if (data.value != 0) {
					$.post("${ctx}/servlet/EntryOrderServlet?type=smallOption&optionType=finSmall&supplierId=" + data.value,function(datas){
		        		if (datas != null) {	
		        			$(".rawMaterialSmallId").empty().append("<option value='0'>----请选择----</option>");
		        			for ( var i = 0; i < datas.length; i++) {
								$(".rawMaterialSmallId").append("<option value=" + datas[i].rawMaterialSmallId + ">" + datas[i].rawMaterialSmallName + "</option>");
							}	
							form.render('select');					
						}
		        	}, "json");		        	
				}
			});
			//监听提交原料模态框
            form.on('submit(frawMaterial)', function(data){
                console.log(data.field);
              	$("#formRawMaterial").ajaxSubmit(function(jsonObject){
	    			if (jsonObject != "") {
						data = JSON.parse(jsonObject);
	        			if (data.state) {
	        				modHide();
							layer.alert(data.msg, {
								icon : 1,
								title : '提示'
							},function(layerIndex){
								layer.close(layerIndex);
								table.reload("findMaterial1");
								layer.open({
							    	type: 1,//类型
							      	title: '<i class="layui-icon layui-icon-add-1"> 原料信息</i>',
							      	offset: ['5%','30%'],
							      	area:['680px','600px'],//定义宽和高
							      	shadeClose: true,//点击遮罩层关闭
							      	content: $("#modRawMaterial1")//打开的内容
							    });
							});	
						} else {
							layer.alert(data.msg, {
								icon : 0,
								title : '提示'
							});
						}
					}	        		
	        	});
				return false;
            });            
            
            //监听行工具事件
            table.on('tool(selectMaterial1)', function(obj){ //注：tool 是工具条事件名
				var data = obj.data, //获得当前行数据
				layEvent = obj.event; //获得 lay-event 对应的值
				if(layEvent === 'del'){
					delRawMaterial(data.rawMaterialId, "selectMaterial1");
				}
			});
			
			//监听采购单表单提交
            form.on('submit(formPurchaseOrders)', function(data){   
            	var tableInfo = table.cache.selectMaterial1;
            	var totalMoney = 0;
            	if (tableInfo != undefined && tableInfo != null && tableInfo.length > 0) {
            		for ( var i = 0; i < tableInfo.length; i++) {
						totalMoney += Number(tableInfo[i].rawMaterialPrice) * Number(tableInfo[i].rawMaterialAmount);
					}
					if (totalMoney > 0) {
						$("#billsMoney").val(totalMoney);		
						tableInfo = JSON.stringify(tableInfo);
	            		formSubmit("formPurchaseOrders", 
	            		"${ctx}/servlet/EntryOrderServlet?tableInfo=" + tableInfo, "selectMaterial1");
					}					
				} else {
					layer.alert('请选择需要采购的原料信息', {
						icon : 0,
						title : '提示'
					});
				} 	
            	return false;
            });			
            /*------------------------配送单---------------------------*/
			$("#btnPurchase").click(function(){
				showTable("findPurchase", "${ctx}/servlet/EntryOrderServlet?type=findOrderInfo&orderType=findPurchase", 
					"layui-icon-menu-fill", "采购单信息","modPurchaseOrder");
			});
			var purchaseOrdersId = 0;
			table.on('row(findPurchase)', function(obj) {
				var data = obj.data;
				$("#formDispatchingOrders [name='supplierId']").val(data.supplierId);
				$("#formDispatchingOrders [name='warehouseId']").val(data.warehouseId);
				purchaseOrdersId = data.purchaseOrdersId;
				form.render('select');
				modHide();
			});
			$("#btnMaterial2").click(function() {
				if (purchaseOrdersId > 0) {
					showTable("findMaterial2", 
						"${ctx}/servlet/EntryOrderServlet?type=findMaterial&findType=findMaterial2&purchaseOrdersId=" + purchaseOrdersId, 
						"layui-icon-menu-fill", "采购单原料信息","modRawMaterial2");
				} else {
					layer.msg('请选择一条采购单数据');
				}
				
			});
			//监听行工具事件
            table.on('tool(selectMaterial2)', function(obj){ //注：tool 是工具条事件名
				var data = obj.data, //获得当前行数据
				layEvent = obj.event; //获得 lay-event 对应的值
				if(layEvent === 'del'){
					delRawMaterial(data.rawMaterialId, "selectMaterial2");
				}
			});
			
			//监听配送单表单提交
            form.on('submit(formDispatchingOrders)', function(data){   
            	var tableInfo = table.cache.selectMaterial2;
            	var totalMoney = 0;
            	if (tableInfo != undefined && tableInfo != null && tableInfo.length > 0) {
            		for ( var i = 0; i < tableInfo.length; i++) {
						totalMoney += Number(tableInfo[i].rawMaterialPrice) * Number(tableInfo[i].rawMaterialAmount);
					}
					if (totalMoney > 0) {
						$("#billsMoney2").val(totalMoney);
						console.log(data.field);
						tableInfo = JSON.stringify(tableInfo);
	            		formSubmit("formDispatchingOrders", 
	            		"${ctx}/servlet/EntryOrderServlet?tableInfo=" + tableInfo, "selectMaterial2");
					}					
				} else {
					layer.alert('请选择需要配送的原料信息', {
						icon : 0,
						title : '提示'
					});
				} 	
            	return false;
            });
            /*------------------------入库单---------------------------*/
            $("#btnDispatching").click(function() {
            	showTable("findDispatching", 
            		"${ctx}/servlet/EntryOrderServlet?type=findOrderInfo&orderType=findDispatching", 
					"layui-icon-menu-fill", "配送单信息", "modDispatchingOrders");
            });
            //监听点击表格行事件
            table.on('row(findDispatching)', function(obj) {
				var data = obj.data;
				$("#formGodownOrders [name='supplierId']").val(data.supplierId);
				$("#formGodownOrders [name='warehouseId']").val(data.warehouseId);
				form.render('select');
				table.reload("selectMaterial3", {
					url: "${ctx}/servlet/EntryOrderServlet?type=findMaterial&findType=findPSMaterial&dispatchingOrdersId=" + 
					data.dispatchingOrdersId + "&warehouseId=" + data.warehouseId
				});
				modHide();
			});
			//监听配送单表单提交
            form.on('submit(formGodownOrders)', function(data){   
            	var tableInfo = table.cache.selectMaterial3;
            	var totalMoney = 0;
            	if (tableInfo != undefined && tableInfo != null && tableInfo.length > 0) {
            		for ( var i = 0; i < tableInfo.length; i++) {
						totalMoney += Number(tableInfo[i].rawMaterialPrice) * Number(tableInfo[i].rawMaterialAmount);
					}
					if (totalMoney > 0) {
						$("#billsMoney3").val(totalMoney);
						console.log(data.field);
						tableInfo = JSON.stringify(tableInfo);
	            		formSubmit("formGodownOrders", 
	            		"${ctx}/servlet/EntryOrderServlet?tableInfo=" + tableInfo, "selectMaterial3");
					}					
				} else {
					layer.alert('请选择需要入库的原料信息', {
						icon : 0,
						title : '提示'
					});
				} 	
            	return false;
            });
            /*------------------------退货单---------------------------*/
            $("#btnGodownOrders").click(function() {
            	showTable("findGodownOrders", 
            		"${ctx}/servlet/EntryOrderServlet?type=findOrderInfo&orderType=findGodownOrders", 
					"layui-icon-menu-fill", "入库单信息", "modGodownOrders");
            });
            //监听点击表格行事件
            table.on('row(findGodownOrders)', function(obj) {
				var data = obj.data;
				$("#formCreditOrders [name='supplierId']").val(data.supplierId);
				$("#formCreditOrders [name='warehouseId']").val(data.warehouseId);
				form.render('select');
				table.reload("selectMaterial4", {
					url: "${ctx}/servlet/EntryOrderServlet?type=findMaterial&findType=findRKMaterial&godownOrdersId=" + 
					data.godownOrdersId + "&warehouseId=" + data.warehouseId
				});
				modHide();
			});
			//监听配送单表单提交
            form.on('submit(formCreditOrders)', function(data){   
            	var tableInfo = table.cache.selectMaterial4;
            	var totalMoney = 0;
            	if (tableInfo != undefined && tableInfo != null && tableInfo.length > 0) {
            		for ( var i = 0; i < tableInfo.length; i++) {
						totalMoney += Number(tableInfo[i].rawMaterialPrice) * Number(tableInfo[i].rawMaterialAmount);
					}
					if (totalMoney > 0) {
						$("#billsMoney4").val(totalMoney);
						console.log(data.field);
						tableInfo = JSON.stringify(tableInfo);
	            		formSubmit("formCreditOrders", 
	            		"${ctx}/servlet/EntryOrderServlet?tableInfo=" + tableInfo, "selectMaterial4");
					}					
				} else {
					layer.alert('请选择需要一条入库单信息', {
						icon : 0,
						title : '提示'
					});
				} 	
            	return false;
            });
            /*------------------------领料单---------------------------*/
           var departmentId = 0;
            var warehouseId = 0;
            $("#btnRequirement").click(function() {
            	departmentId = $("#formStocksRequisition [name='departmentId']").val();
            	warehouseId = $("#formStocksRequisition [name='warehouseId']").val();
            	if (departmentId > 0 && warehouseId > 0) {
					showTable("findMaterialRequirement", 
            			"${ctx}/servlet/EntryOrderServlet?type=findMaterial&findType=findMaterialRequirement&departmentId=" 
            			+ departmentId + "&warehouseId=" + warehouseId, 
						"layui-icon-menu-fill", "部门原料需求信息", "modMaterialRequirement");
				} else {
					layer.msg("请选择一个部门和仓库");
				}            	
            });
            //监听下拉框值改变
            form.on("select(srawMaterialBig)", function(data) {
				if (data.value > 0) {
					$.post("${ctx}/servlet/EntryOrderServlet?type=smallOption&optionType=selectSmall&rawMaterialBigId=" + data.value,function(datas){
		        		if (datas != null) {	
		        			$(".srawMaterialSmall").empty().append("<option value='0'>----请选择----</option>");
		        			for ( var i = 0; i < datas.length; i++) {
								$(".srawMaterialSmall").append("<option value=" + datas[i].rawMaterialSmallId + ">" + datas[i].rawMaterialSmallName + "</option>");
							}	
							form.render('select');					
						}
		        	}, "json");		        	
				}
			});
			form.on("select(srawMaterialSmall)", function(data) {
				if (data.value > 0) {
					$.post("${ctx}/servlet/EntryOrderServlet?type=smallOption&optionType=selectRawMaterial&rawMaterialSmallId=" + data.value,function(datas){
		        		if (datas != null) {	
		        			$(".srawMaterial").empty().append("<option value='0'>----请选择----</option>");
		        			for ( var i = 0; i < datas.length; i++) {
								$(".srawMaterial").append("<option value=" + datas[i].rawMaterialId + ">" + datas[i].rawMaterialName + "</option>");
							}	
							form.render('select');					
						}
		        	}, "json");		        	
				}
			});
			//监听部门原料需求form表单
            form.on('submit(fMaterialRequirement)', function(data){
                console.log(data.field);
              	$("#formMaterialRequirement").ajaxSubmit(function(jsonObject){
	    			if (jsonObject != "") {
						data = JSON.parse(jsonObject);
	        			if (data.state) {
	        				modHide();
							layer.alert(data.msg, {
								icon : 1,
								title : '提示'
							},function(layerIndex){
								layer.close(layerIndex);
								showTable("findMaterialRequirement", 
		            			"${ctx}/servlet/EntryOrderServlet?type=findMaterial&findType=findMaterialRequirement&departmentId=" 
		            			+ departmentId + "&warehouseId=" + warehouseId, 
								"layui-icon-menu-fill", "部门原料需求信息", "modMaterialRequirement");
									});	
						} else {
							layer.alert(data.msg, {
								icon : 0,
								title : '提示'
							});
						}
					}	        		
	        	});
				return false;
            });    
            //监听表格单元格编辑
            table.on('edit(selectMaterial5)', function(obj) {
            	var value = obj.value //得到修改后的值
    			,data = obj.data; //得到所在行所有键值
    			if (data.reportAmount < value) {
					layer.msg('领料数量不能大于库存数量!');
					obj.update({
						rawMaterialAmount: Math.ceil(obj.value * 0)
					});
				}
            });
            //监听领料单表单提交
            form.on('submit(formStocksRequisition)', function(data){   
            	var tableInfo = table.cache.selectMaterial5;
            	var totalMoney = 0;
            	if (tableInfo != undefined && tableInfo != null && tableInfo.length > 0) {
            		for ( var i = 0; i < tableInfo.length; i++) {
						totalMoney += Number(tableInfo[i].rawMaterialPrice) * Number(tableInfo[i].rawMaterialAmount);
					}
					if (totalMoney > 0) {
						$("#billsMoney5").val(totalMoney);
						console.log(data.field);
						tableInfo = JSON.stringify(tableInfo);
	            		formSubmit("formStocksRequisition", 
	            		"${ctx}/servlet/EntryOrderServlet?tableInfo=" + tableInfo, "selectMaterial5");
					}					
				} else {
					layer.alert('请选择需要领取原料信息', {
						icon : 0,
						title : '提示'
					});
				} 	
            	return false;
            });
            /*------------------------领料退货单---------------------------*/
            $("#btnStocksRequisition").click(function() {
            	showTable("findStocksRequisition", 
            		"${ctx}/servlet/EntryOrderServlet?type=findOrderInfo&orderType=findStocksRequisition", 
					"layui-icon-menu-fill", "领料单信息", "modStocksRequisition");
            });
            //监听点击表格行事件
            table.on('row(findStocksRequisition)', function(obj) {
				var data = obj.data;
				$("#formPickingCreditOrders [name='departmentId']").val(data.departmentId);
				$("#formPickingCreditOrders [name='warehouseId']").val(data.warehouseId);
				form.render('select');
				table.reload("selectMaterial6", {
					url: "${ctx}/servlet/EntryOrderServlet?type=findMaterial&findType=findLLMaterial&stocksRequisitionId=" + 
					data.stocksRequisitionId
				});
				modHide();
			});
			//监听领料退货单表单提交
            form.on('submit(formPickingCreditOrders)', function(data){   
            	var tableInfo = table.cache.selectMaterial6;
            	var totalMoney = 0;
            	if (tableInfo != undefined && tableInfo != null && tableInfo.length > 0) {
            		for ( var i = 0; i < tableInfo.length; i++) {
						totalMoney += Number(tableInfo[i].rawMaterialPrice) * Number(tableInfo[i].rawMaterialAmount);
					}
					if (totalMoney > 0) {
						$("#billsMoney6").val(totalMoney);
						console.log(data.field);
						tableInfo = JSON.stringify(tableInfo);
	            		formSubmit("formPickingCreditOrders", 
	            		"${ctx}/servlet/EntryOrderServlet?tableInfo=" + tableInfo, "selectMaterial6");
					}					
				} else {
					layer.alert('请选择需要领取原料信息', {
						icon : 0,
						title : '提示'
					});
				} 	
            	return false;
            });
            /*------------------------仓库调拨单---------------------------*/
            $("#btnWarehouse").click(function() {
            	var warehouseId = $("#formWarehouseTransferOrder [name='warehouseID']").val();
            	if (warehouseId > 0) {
					showTable("findMaterial3", 
            		"${ctx}/servlet/EntryOrderServlet?type=findMaterial&findType=findWarehouse&warehouseId=" + warehouseId, 
					"layui-icon-menu-fill", "仓库原料信息", "modRawMaterial3");
				} else {
					layer.msg('请选择一个调出仓库!');
				}
            });
            //监听仓库调拨单表单提交
            form.on('submit(formWarehouseTransferOrder)', function(data){   
            	var warehouseId = $("#formWarehouseTransferOrder [name='warehouseId']").val();
            	var warehouseID = $("#formWarehouseTransferOrder [name='warehouseID']").val();
            	if (warehouseId != warehouseID) {
					var tableInfo = table.cache.selectMaterial7;
	            	var totalMoney = 0;
	            	if (tableInfo != undefined && tableInfo != null && tableInfo.length > 0) {
	            		for ( var i = 0; i < tableInfo.length; i++) {
							totalMoney += Number(tableInfo[i].rawMaterialPrice) * Number(tableInfo[i].rawMaterialAmount);
						}
						if (totalMoney > 0) {
							$("#billsMoney7").val(totalMoney);
							console.log(data.field);
							tableInfo = JSON.stringify(tableInfo);
		            		formSubmit("formWarehouseTransferOrder", 
		            		"${ctx}/servlet/EntryOrderServlet?tableInfo=" + tableInfo, "selectMaterial7");
						}					
					} else {
						layer.alert('请选择需要调拨原料信息', {
							icon : 0,
							title : '提示'
						});
					} 	
				} else {
					layer.msg('调出仓库和调入仓库不能相同');
				}            	
            	return false;
            });
            /*------------------------库存盘点单---------------------------*/
            $("#btnInCheck").click(function() {
            	var staffId = $("#formRepertoryCheck [name='staffId']").val();
            	var warehouseId = $("#formRepertoryCheck [name='warehouseId']").val();
            	if (staffId > 0 && warehouseId > 0) {
            		$("#formCheck").resetForm();//重置表单
            		$("#formCheck [name='warehouseId']").val(warehouseId);
					table.reload("findMaterial4", {
						url: "${ctx}/servlet/EntryOrderServlet?type=findMaterial&findType=findMaterial4"
					});
					reloadCheck(warehouseId);
					layer.open({
				    	type: 1,//类型
				      	title: '<i class="layui-icon layui-icon-menu-fill"> 盘点货物信息</i>',
				      	offset: ['1%','18%'],
				      	area:['1100px','880px'],//定义宽和高
				      	shadeClose: true,//点击遮罩层关闭
				      	content: $("#modCheckMaterial")//打开的内容
				    });
				} else {
					layer.msg('请选择盘点人和盘点仓库');
				}
            });
            //监听点击表格行事件
            table.on('row(findMaterial4)', function(obj) {
				var data = obj.data;
				var warehouseId = $("#formCheck [name='warehouseId']").val();
				$("#formCheck [name='rawMaterialId']").val(data.rawMaterialId);
				$("#formCheck [name='rawMaterialNum']").val(data.rawMaterialNum);
				$("#formCheck [name='rawMaterialName']").val(data.rawMaterialName);
				$("#formCheck [name='unitName']").val(data.unitName);
				$.post("${ctx}/servlet/EntryOrderServlet?type=getReportAmount&rawMaterialId=" 
				+ data.rawMaterialId + "&warehouseId=" + warehouseId, function(datas){
					$("#formCheck [name='reportAmount']").empty().val(datas.sum);
				}, 'json');
			});
			function reloadCheck(warehouseId) {
				table.reload("findCheck", {
						page: {
							curr: 1 //重新从第 1 页开始
						},
						url: "${ctx}/servlet/EntryOrderServlet?type=findMaterial&findType=findCheck&warehouseId=" + warehouseId
					});
			}
			//监听盘点信息表单提交
            form.on('submit(formCheck)', function(data){
            	var rawMaterialId = $("#formCheck [name='rawMaterialId']").val();
            	var warehouseId = $("#formCheck [name='warehouseId']").val();
            	var count = $("#formCheck [name='inventoryCount']").val();
            	if (rawMaterialId > 0 && warehouseId > 0 && count > 0) {
		           	$("#formCheck").ajaxSubmit({
		    			url: "${ctx}/servlet/EntryOrderServlet",
		    			success: function(jsonObject){
			    			if (jsonObject != "") {
								data = JSON.parse(jsonObject);
			        			if (data.state) {
									layer.msg(data.msg);
									reloadCheck(warehouseId);
								} else {
									layer.alert(data.msg, {
										icon : 0,
										title : '提示'
									});
								}
							}
						}	        		
		        	}); 	
				} else {
					layer.msg('请点击一条原料信息并填写盘点数量');
				}           	
            	return false;
            });
            //点击确定按钮
            $("#btnConfirm").click(function() {
            	var data = table.cache.findCheck;
            	table.reload('findCheckMaterial', {
            		data: data
            	});
            	modHide();
            });
            //监听仓库调拨单表单提交
            form.on('submit(formRepertoryCheck)', function(data){   
            	var tableInfo = table.cache.findCheckMaterial;
            	var totalMoney = 0;
            	if (tableInfo != undefined && tableInfo != null && tableInfo.length > 0) {
            		for ( var i = 0; i < tableInfo.length; i++) {
						totalMoney += Number(tableInfo[i].rawMaterialPrice) * Number(tableInfo[i].rawMaterialAmount);
					}
					if (totalMoney > 0) {
						$("#billsMoney8").val(totalMoney);
						console.log(data.field);
						tableInfo = JSON.stringify(tableInfo);
	            		formSubmit("formRepertoryCheck", 
	            		"${ctx}/servlet/EntryOrderServlet?tableInfo=" + tableInfo, "findCheckMaterial");
					}					
				} else {
					layer.alert('请选择需要调拨原料信息', {
						icon : 0,
						title : '提示'
					});
				} 	          	
            	return false;
            });
		});		
		function modHide(){
    		$(".layui-layer-close").trigger("click");
    	}
    	$(".Cancel").click(function(){ 
    		$("#modAddEidtMaterial").parents().next('span').find('a.layui-layer-close').trigger("click");
    		$("#modAddEidtRequirement").parents().next('span').find('a.layui-layer-close').trigger("click");
    	});
    	function pim(){
        	var name = $("#formRawMaterial [name='rawMaterialName']").val();
        	var simply = pinyin.getCamelChars(name);
        	$("#formRawMaterial [name='pinyinCode']").val(simply);
        }
        //构造函数 重新构造数据
       function constructionMethod(object){
       		var arr = new Array();
       		for (i in object) {
       			arr.push(i);				
			}
        	var obj = new Object();
        	for ( var j = 0; j < arr.length; j++) {
        		var str = arr[j];
				obj[str] = object[str];
			}
			console.log(obj);
        	return obj;
        }
        
        function showTable(tableId, Url, Class, title, modId){
			table.reload(tableId, {
				url: Url
			});
			layer.open({
		    	type: 1,//类型
		      	title: '<i class="layui-icon ' + Class + '"> ' + title + '</i>',
		      	offset: ['5%','30%'],
		      	area:['680px','580px'],//定义宽和高
		      	shadeClose: true,//点击遮罩层关闭
		      	content: $("#" + modId)//打开的内容
		    });
		}
        //点击选项卡重置表单和清空数组和表格
        $(".layui-tab-title li").click(function() {
        	arrayRawMaterial.length = 0;
        	var btnId = "btn" + $(this)[0].id;
  			$("#" + btnId).trigger("click");
        });
        //首先声明一个数组，用来存选中的表格数据
       	var arrayRawMaterial = new Array();
        function selectMaterial(modTableId, tableId){
        	var total = 0;            	
        	//获取表格已勾选的数据
        	var checkStatus = table.checkStatus(modTableId)
		   	,data = checkStatus.data;
		   	if (arrayRawMaterial.length == 0 && data.length != 0) {
			   	for ( var i = 0; i < data.length; i++) {
					//data[l] = constructionMethod(data[l]);
					if (data[i].quantityRequired > 0) {
						if (data[i].reportAmount > data[i].quantityRequired){//判断库存数量是否大于需求数量
							data[i].rawMaterialAmount = data[i].quantityRequired;//部门原料需求数量
						} else {
							data[i].rawMaterialAmount = data[i].reportAmount;//库存原料数量
							layer.msg('库存原料不足，只能领取库存小于等于库存数量');
						}						
					} else if (data[i].rawMaterialAmount === 0) {
						data[i].rawMaterialAmount = 10;//添加一列数据，并给予默认值
						if (data[i].reportAmount > 0) {
							var count = data[i].reportAmount;
							for ( var j = 10; j > 0; j--) {
								if (count < data[i].rawMaterialAmount) {
									data[i].rawMaterialAmount = j;
								}
							}
						}
					}					 				
				}
				arrayRawMaterial = data;
			} else {
				for ( var i = 0; i < arrayRawMaterial.length; i++) {					 					
					for ( var j = 0; j < data.length; j++) {
						if (arrayRawMaterial[i].rawMaterialId == data[j].rawMaterialId) {
							total++;
							data.splice(j, 1);
							break;
						}
					}
				}
				for ( var k = 0; k < data.length; k++) {
					arrayRawMaterial.push(data[k]);
				}					
				for ( var l = 0; l < arrayRawMaterial.length; l++) {
					if (arrayRawMaterial[l].quantityRequired > 0) {
						arrayRawMaterial[l].rawMaterialAmount = arrayRawMaterial[l].quantityRequired;//部门原料需求数量
					} else {
						arrayRawMaterial[l].rawMaterialAmount = 10;//添加一列数据，并给予默认值
						if (arrayRawMaterial[l].reportAmount > 0) {
							var count = arrayRawMaterial[l].reportAmount;
							for ( var m = 10; m > 0; m--) {
								if (count < arrayRawMaterial[l].rawMaterialAmount) {
									arrayRawMaterial[l].rawMaterialAmount = m;
								}
							}
						}
					}
					
				}
				if (total > 0) {
					layer.msg('已去掉' + total + '重复数据!');
				}
			}				
			table.reload(tableId, {
				data: arrayRawMaterial
			});
			modHide();
        }
		//移除已选中原料信息
		function delRawMaterial(rawMaterialId, tableId){
			for ( var i = 0; i < arrayRawMaterial.length; i++) {
				if (arrayRawMaterial[i].rawMaterialId == rawMaterialId) {
					arrayRawMaterial.splice(i, 1);
				}
			}
			table.reload(tableId, {
		    	data: arrayRawMaterial
		    });
		}
		//表单提交方法
		function formSubmit(formId, Url, tableId){
    		$("#" + formId).ajaxSubmit({
    			type: 'post',
    			url: Url,
    			success: function(jsonObject){
	    			if (jsonObject != "") {
						data = JSON.parse(jsonObject);
	        			if (data.state) {
							layer.alert(data.msg, {
								icon : 1,
								title : '提示'
							},function(layerIndex){
								layer.close(layerIndex);
								reload(formId, tableId);
							});
						} else {
							layer.alert(data.msg, {
								icon : 0,
								title : '提示'
							});
						}
					}
				}	        		
        	}); 	
    	}		
    	//重置表单和清空表格
    	function reload(formId, tableId){
    		$("#" + formId).resetForm();//重置表单
			getbillsNum();
			arrayRawMaterial.length = 0;
			table.reload(tableId, {
				url: "",
		    	data: arrayRawMaterial
		    });
    	}
    	//限制输入
    	function chkUper(obj){
	        if(/[^\u4E00-\u9FA5]/g.test(obj.value)){
	            obj.value = obj.value.replace(/[^\u4E00-\u9FA5]/g,'');
	        }
	    }	
	</script>
</body>
</html>
