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
    
    <title>My JSP 'FindOrder.jsp' starting page</title>
    
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
			padding: 1% 0 1% 0.5%;
		}
		
		.layui-card .layui-card-body{
			border: none;
		}
		
		.times{
			width: 43.5%;
			display: inline-block;
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
	<script type="text/javascript" src="${ctx}/Content/js/Convert_Pinyin.js"></script>
	<script type="text/javascript" src="${ctx}/Content/layui/layui.js"></script>
  </head>
  
  <body>
    <div class="layui-container">
		<div class="layui-tab">
			<ul class="layui-tab-title">
				<li id="CheckOrder" class="layui-this">盘点单据查询</li>
				<li id="Material1">盘点损益报表</li>
				<li id="GodownOrders">入库单查询</li>
				<li id="CreditOrders">入库退货单查询</li>
				<li id="StocksRequisition">领料单查询</li>
				<li id="PickingCreditOrders">领料退货单查询</li>
				<li id="WarehouseTransferOrder">仓库调拨单查询</li>
				<li id="Material2">仓库调拨单汇总</li>
				<li id="Material3">部门领料单汇总</li>	
			</ul>
			<div class="layui-tab-content">
				<div class="layui-tab-item layui-show">
					<div class="layui-card">
						<div class="layui-card-header">查询条件如下：</div>
						<div class="layui-card-body" style="padding: 0;">
							<form class="layui-form" style="margin: 1% 0 2% 0;">
								<div class="layui-form-item">
									<div class="layui-col-lg3 layui-col-md3 layui-col-sm3">
										<label class="layui-form-label">盘点单号:</label>
										<div class="layui-input-block">
											<input type="text" id="billsNum1" autocomplete="off"
												class="layui-input">
										</div>
									</div>
									<div
										class="layui-col-lg3 layui-col-md3 layui-col-sm3 layui-col-md-offset1">
										<label class="layui-form-label">仓库:</label>
										<div class="layui-input-block">
											<select id="warehouseId1" lay-search>
												<option value="0">----请选择----</option>
												<c:forEach items="${warehouses}" var="warehouse">
													<option value="${warehouse.warehouseId}">${warehouse.warehouseName}</option>
												</c:forEach>
											</select>
										</div>
									</div>
								</div>
								<div class="layui-form-item">
									<div class="layui-col-lg3 layui-col-md3 layui-col-sm3">
										<label class="layui-form-label">盘点日期:</label>
										<div class="layui-input-block">
											<input type="text" id="startTime1" autocomplete="off"
												class="layui-input times" placeholder="yyyy-MM-dd">
											&nbsp;&nbsp;至&nbsp;&nbsp;
											<input type="text" id="endTime1" autocomplete="off"
													class="layui-input times" placeholder="yyyy-MM-dd">
										</div>																
									</div>
									<div
										class="layui-col-lg3 layui-col-md3 layui-col-sm3 layui-col-md-offset1">
										<label class="layui-form-label">盘点单人:</label>
										<div class="layui-input-block">
											<select id="staffId1" lay-search>
												<option value="0">----请选择----</option>
												<c:forEach items="${staffs}" var="staff">
													<option value="${staff.staffId}">${staff.staffName}</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<div class="layui-col-lg5 layui-col-md5 layui-col-sm5">
										<div class="layui-input-block">
											<button type="button" class="layui-btn" onclick="refesh('btnCheckOrder')">
												<i class="layui-icon layui-icon-refresh"> <span>
														刷 新</span> </i>
											</button>
											<button type="button" class="layui-btn" id="btnCheckOrder"
											onclick="searchCheck();">
												<i class="layui-icon layui-icon-search"> <span>
														搜 索</span> </i>
											</button>
											<button type="button" class="layui-btn layui-btn-warm"
												onclick="showDetail('findCheckOrder');">
												<i class="layui-icon layui-icon-search"> <span>
														明 细</span> </i>
											</button>
										</div>
									</div>
								</div>								
							</form>
							<table class="layui-table"
								lay-data="{
											height: 471,
											id: 'findCheckOrder',
											url: '${ctx}/servlet/FindOrderServlet?type=findOrder&findType=findCheckOrder',											
											page: true,
											limit: 10,//每页默认显示的数量
											method: 'post', //提交方式								
											}"
								lay-filter="findCheckOrder">
								<thead>
									<tr>
										<th lay-data="{type: 'radio', hide: 'true'}"></th>
										<th
											lay-data="{field: 'billsNum', align: 'center', sort: true}">单号</th>
										<th
											lay-data="{field: 'warehouseName', align: 'center', sort: true}">仓库</th>
										<th
											lay-data="{field: 'staffName', align: 'center', sort: true}">盘点人</th>
										<th
											lay-data="{field: 'billsEntryTimes', align: 'center', sort: true}">时间</th>
										<th
											lay-data="{field: 'billRemark', align: 'center', sort: true}">备注</th>
									</tr>
								</thead>
							</table>
						</div>
					</div>					
				</div>
				<div class="layui-tab-item">
					<div class="layui-card">
						<div class="layui-card-header">查询条件如下：</div>
						<div class="layui-card-body" style="padding: 0;">
							<form class="layui-form" style="margin: 1% 0 2% 0;">								
								<div class="layui-form-item">
									<div class="layui-col-lg3 layui-col-md3 layui-col-sm3">
										<label class="layui-form-label">盘点时间:</label>
										<div class="layui-input-block">
											<input type="text" id="startTime2" autocomplete="off"
												class="layui-input times" placeholder="yyyy-MM-dd">
											&nbsp;&nbsp;至&nbsp;&nbsp;
											<input type="text" id="endTime2" autocomplete="off"
													class="layui-input times" placeholder="yyyy-MM-dd">
										</div>																
									</div>
									<div
										class="layui-col-lg3 layui-col-md3 layui-col-sm3 layui-col-md-offset1">
										<label class="layui-form-label">仓库名称:</label>
										<div class="layui-input-block">
											<select id="warehouseId2" lay-search>
												<option value="0">----请选择----</option>
												<c:forEach items="${warehouses}" var="warehouse">
													<option value="${warehouse.warehouseId}">${warehouse.warehouseName}</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<div class="layui-col-lg5 layui-col-md5 layui-col-sm5">
										<div class="layui-input-block">
											<button type="button" class="layui-btn" id="btnMaterial1"
											onclick="searchMaterial(2, 2, 0, 0, 0, 'findMaterial1');">
												<i class="layui-icon layui-icon-search"> <span>
														搜 索</span> </i>
											</button>											
										</div>
									</div>
								</div>								
							</form>
							<table class="layui-table"
								lay-data="{
											height: 511,
											id: 'findMaterial1',
											page: true,
											totalRow: true,
											limit: 10,//每页默认显示的数量
											method: 'post', //提交方式								
											}"
								lay-filter="findMaterial1">
								<thead>
									<tr>
										<th
											lay-data="{field: 'rawMaterialNum', align: 'center', sort: true, totalRowText: '合计'}">编号</th>
										<th
											lay-data="{field: 'rawMaterialName', align: 'center', sort: true}">名称</th>
										<th
											lay-data="{field: 'warehouseName', align: 'center', sort: true}">仓库名称</th>
										<th
											lay-data="{field: 'unitName', align: 'center', sort: true}">单位</th>
										<th
											lay-data="{field: 'rawMaterialPrice', align: 'center', sort: true}">平均单价</th>
										<th
											lay-data="{field: 'numberOfProfitAndLoss', align: 'center', sort: true， totalRow: true}">损盈数量</th>
										<th
											lay-data="{field: 'profitAndLossAmount', align: 'center', sort: true, totalRow: true}">损盈金额</th>
									</tr>
								</thead>
							</table>
						</div>
					</div>
				</div>
				<div class="layui-tab-item">
					<div class="layui-card">
						<div class="layui-card-header">查询条件如下：</div>
						<div class="layui-card-body" style="padding: 0;">
							<form class="layui-form" style="margin: 1% 0 2% 0;">
								<div class="layui-form-item">
									<div class="layui-col-lg3 layui-col-md3 layui-col-sm3">
										<label class="layui-form-label">单号:</label>
										<div class="layui-input-block">
											<input type="text" id="billsNum2" autocomplete="off"
												class="layui-input">
										</div>
									</div>
									<div
										class="layui-col-lg3 layui-col-md3 layui-col-sm3 layui-col-md-offset1">
										<label class="layui-form-label">仓库:</label>
										<div class="layui-input-block">
											<select id="warehouseId3" lay-search>
												<option value="0">----请选择----</option>
												<c:forEach items="${warehouses}" var="warehouse">
													<option value="${warehouse.warehouseId}">${warehouse.warehouseName}</option>
												</c:forEach>
											</select>
										</div>
									</div>
								</div>
								<div class="layui-form-item">
									<div class="layui-col-lg3 layui-col-md3 layui-col-sm3">
										<label class="layui-form-label">日期:</label>
										<div class="layui-input-block">
											<input type="text" id="startTime3" autocomplete="off"
												class="layui-input times" placeholder="yyyy-MM-dd">
											&nbsp;&nbsp;至&nbsp;&nbsp; <input type="text" id="endTime3"
												autocomplete="off" class="layui-input times"
												placeholder="yyyy-MM-dd">
										</div>
									</div>
									<div
										class="layui-col-lg3 layui-col-md3 layui-col-sm3 layui-col-md-offset1">
										<label class="layui-form-label">供应商:</label>
										<div class="layui-input-block">
											<select id="supplierId1" lay-search>
												<option value="0">----请选择----</option>
												<c:forEach items="${suppliers}" var="supplier">
													<option value="${supplier.supplierId}">${supplier.supplierName}</option>
												</c:forEach>
											</select>
										</div>
									</div>
								</div>
								<div class="layui-form-item">
									<div class="layui-col-lg3 layui-col-md3 layui-col-sm3">
										<label class="layui-form-label">批准人:</label>
										<div class="layui-input-block">
											<select id="staffID1" lay-search>
												<option value="0">----请选择----</option>
												<c:forEach items="${staffs}" var="staff">
													<option value="${staff.staffId}">${staff.staffName}</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<div
										class="layui-col-lg3 layui-col-md3 layui-col-sm3 layui-col-md-offset1">
										<label class="layui-form-label">经办人:</label>
										<div class="layui-input-block">
											<select id="staffId2" lay-search>
												<option value="0">----请选择----</option>
												<c:forEach items="${staffs}" var="staff">
													<option value="${staff.staffId}">${staff.staffName}</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<div class="layui-col-lg5 layui-col-md5 layui-col-sm5">
										<div class="layui-input-block">
											<button type="button" class="layui-btn" onclick="refesh('btnGodownOrders')">
												<i class="layui-icon layui-icon-refresh"> <span>
														刷 新</span> </i>
											</button>
											<button type="button" class="layui-btn" id="btnGodownOrders"
											onclick="searchOrder('2', '3', '3', '1', '2', '1', 'findGodownOrders')">
												<i class="layui-icon layui-icon-search"> <span> 搜
														索</span> </i>
											</button>
											<button type="button" class="layui-btn layui-btn-warm" 
											onclick="showDetail('findGodownOrders');">
												<i class="layui-icon layui-icon-search"> <span> 明
														细</span> </i>
											</button>
										</div>
									</div>
								</div>
							</form>
							<table class="layui-table"
								lay-data="{
											height: 471,
											id: 'findGodownOrders',											
											page: true,
											limit: 10,//每页默认显示的数量
											method: 'post', //提交方式								
											}"
								lay-filter="findGodownOrders">
								<thead>
									<tr>
										<th lay-data="{type: 'radio', hide: 'true'}"></th>
										<th lay-data="{field: 'billsNum', align: 'center', sort: true}">单号</th>
										<th
											lay-data="{field: 'supplierName', align: 'center', sort: true}">供应商</th>
										<th
											lay-data="{field: 'warehouseName', align: 'center', sort: true}">仓库</th>
										<th
											lay-data="{field: 'staffName', align: 'center', sort: true}">经办人</th>
										<th
											lay-data="{field: 'staffNames', align: 'center', sort: true}">批准人</th>
										<th
											lay-data="{field: 'billsEntryTimes', align: 'center', sort: true}">时间</th>
										<th
											lay-data="{field: 'billsRemark', align: 'center', sort: true}">备注</th>
										<th
											lay-data="{field: 'billsMoney', align: 'center', sort: true}">金额</th>
									</tr>
								</thead>
							</table>
						</div>						
					</div>					
				</div>
				<div class="layui-tab-item">
					<div class="layui-card">
						<div class="layui-card-header">查询条件如下：</div>
						<div class="layui-card-body" style="padding: 0;">
							<form class="layui-form" style="margin: 1% 0 2% 0;">
								<div class="layui-form-item">
									<div class="layui-col-lg3 layui-col-md3 layui-col-sm3">
										<label class="layui-form-label">单号:</label>
										<div class="layui-input-block">
											<input type="text" id="billsNum3" autocomplete="off"
												class="layui-input">
										</div>
									</div>
									<div
										class="layui-col-lg3 layui-col-md3 layui-col-sm3 layui-col-md-offset1">
										<label class="layui-form-label">仓库:</label>
										<div class="layui-input-block">
											<select id="warehouseId4" lay-search>
												<option value="0">----请选择----</option>
												<c:forEach items="${warehouses}" var="warehouse">
													<option value="${warehouse.warehouseId}">${warehouse.warehouseName}</option>
												</c:forEach>
											</select>
										</div>
									</div>
								</div>
								<div class="layui-form-item">
									<div class="layui-col-lg3 layui-col-md3 layui-col-sm3">
										<label class="layui-form-label">日期:</label>
										<div class="layui-input-block">
											<input type="text" id="startTime4" autocomplete="off"
												class="layui-input times" placeholder="yyyy-MM-dd">
											&nbsp;&nbsp;至&nbsp;&nbsp; <input type="text" id="endTime4"
												autocomplete="off" class="layui-input times"
												placeholder="yyyy-MM-dd">
										</div>
									</div>
									<div
										class="layui-col-lg3 layui-col-md3 layui-col-sm3 layui-col-md-offset1">
										<label class="layui-form-label">供应商:</label>
										<div class="layui-input-block">
											<select id="supplierId2" lay-search>
												<option value="0">----请选择----</option>
												<c:forEach items="${suppliers}" var="supplier">
													<option value="${supplier.supplierId}">${supplier.supplierName}</option>
												</c:forEach>
											</select>
										</div>
									</div>
								</div>
								<div class="layui-form-item">
									<div class="layui-col-lg3 layui-col-md3 layui-col-sm3">
										<label class="layui-form-label">批准人:</label>
										<div class="layui-input-block">
											<select id="staffID2" lay-search>
												<option value="0">----请选择----</option>
												<c:forEach items="${staffs}" var="staff">
													<option value="${staff.staffId}">${staff.staffName}</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<div
										class="layui-col-lg3 layui-col-md3 layui-col-sm3 layui-col-md-offset1">
										<label class="layui-form-label">经办人:</label>
										<div class="layui-input-block">
											<select id="staffId3" lay-search>
												<option value="0">----请选择----</option>
												<c:forEach items="${staffs}" var="staff">
													<option value="${staff.staffId}">${staff.staffName}</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<div class="layui-col-lg5 layui-col-md5 layui-col-sm5">
										<div class="layui-input-block">
											<button type="button" class="layui-btn" onclick="refesh('btnCreditOrders')">
												<i class="layui-icon layui-icon-refresh"> <span>
														刷 新</span> </i>
											</button>
											<button type="button" class="layui-btn" id="btnCreditOrders"
											onclick="searchOrder('3', '4', '4', '2', '3', '2', 'findCreditOrders')">
												<i class="layui-icon layui-icon-search"> <span> 搜
														索</span> </i>
											</button>
											<button type="button" class="layui-btn layui-btn-warm" 
											onclick="showDetail('findCreditOrders');">
												<i class="layui-icon layui-icon-search"> <span> 明
														细</span> </i>
											</button>
										</div>
									</div>
								</div>
							</form>
							<table class="layui-table"
								lay-data="{
											height: 471,
											id: 'findCreditOrders',											
											page: true,
											limit: 10,//每页默认显示的数量
											method: 'post', //提交方式								
											}"
								lay-filter="findCreditOrders">
								<thead>
									<tr>
										<th lay-data="{type: 'radio', hide: 'true'}"></th>
										<th lay-data="{field: 'billsNum', align: 'center', sort: true}">单号</th>
										<th
											lay-data="{field: 'supplierName', align: 'center', sort: true}">供应商</th>
										<th
											lay-data="{field: 'warehouseName', align: 'center', sort: true}">仓库</th>
										<th
											lay-data="{field: 'staffName', align: 'center', sort: true}">经办人</th>
										<th
											lay-data="{field: 'staffNames', align: 'center', sort: true}">批准人</th>
										<th
											lay-data="{field: 'billsEntryTimes', align: 'center', sort: true}">时间</th>
										<th
											lay-data="{field: 'billsRemark', align: 'center', sort: true}">备注</th>
										<th
											lay-data="{field: 'billsMoney', align: 'center', sort: true}">金额</th>
									</tr>
								</thead>
							</table>
						</div>
					</div>
				</div>
				<div class="layui-tab-item">
					<div class="layui-card">
						<div class="layui-card-header">查询条件如下：</div>
						<div class="layui-card-body" style="padding: 0;">
							<form class="layui-form" style="margin: 1% 0 2% 0;">
								<div class="layui-form-item">
									<div class="layui-col-lg3 layui-col-md3 layui-col-sm3">
										<label class="layui-form-label">单号:</label>
										<div class="layui-input-block">
											<input type="text" id="billsNum4" autocomplete="off"
												class="layui-input">
										</div>
									</div>
									<div
										class="layui-col-lg3 layui-col-md3 layui-col-sm3 layui-col-md-offset1">
										<label class="layui-form-label">部门:</label>
										<div class="layui-input-block">
											<select id="departmentId1" lay-search>
												<option value="0">----请选择----</option>
												<c:forEach items="${departments}" var="department">
													<option value="${department.departmentId}">${department.departmentName}</option>
												</c:forEach>
											</select>
										</div>
									</div>
								</div>
								<div class="layui-form-item">
									<div class="layui-col-lg3 layui-col-md3 layui-col-sm3">
										<label class="layui-form-label">日期:</label>
										<div class="layui-input-block">
											<input type="text" id="startTime5" autocomplete="off"
												class="layui-input times" placeholder="yyyy-MM-dd">
											&nbsp;&nbsp;至&nbsp;&nbsp; <input type="text" id="endTime5"
												autocomplete="off" class="layui-input times"
												placeholder="yyyy-MM-dd">
										</div>
									</div>
									<div
										class="layui-col-lg3 layui-col-md3 layui-col-sm3 layui-col-md-offset1">
										<label class="layui-form-label">批准人:</label>
										<div class="layui-input-block">
											<select id="staffID3" lay-search>
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
											<select id="warehouseId5" lay-search>
												<option value="0">----请选择----</option>
												<c:forEach items="${warehouses}" var="warehouse">
													<option value="${warehouse.warehouseId}">${warehouse.warehouseName}</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<div
										class="layui-col-lg3 layui-col-md3 layui-col-sm3 layui-col-md-offset1">
										<label class="layui-form-label">经办人:</label>
										<div class="layui-input-block">
											<select id="staffId4" lay-search>
												<option value="0">----请选择----</option>
												<c:forEach items="${staffs}" var="staff">
													<option value="${staff.staffId}">${staff.staffName}</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<div class="layui-col-lg5 layui-col-md5 layui-col-sm5">
										<div class="layui-input-block">
											<button type="button" class="layui-btn" onclick="refesh('btnStocksRequisition')">
												<i class="layui-icon layui-icon-refresh"> <span>
														刷 新</span> </i>
											</button>
											<button type="button" class="layui-btn" id="btnStocksRequisition"
											onclick="searchOrder('4', '5', '5', '1', '4', '3', 'findStocksRequisition')">
												<i class="layui-icon layui-icon-search"> <span> 搜
														索</span> </i>
											</button>
											<button type="button" class="layui-btn layui-btn-warm" 
											onclick="showDetail('findStocksRequisition');">
												<i class="layui-icon layui-icon-search"> <span> 明
														细</span> </i>
											</button>
										</div>
									</div>
								</div>
							</form>
							<table class="layui-table"
								lay-data="{
											height: 471,
											id: 'findStocksRequisition',											
											page: true,
											limit: 10,//每页默认显示的数量
											method: 'post', //提交方式								
											}"
								lay-filter="findStocksRequisition">
								<thead>
									<tr>
										<th lay-data="{type: 'radio', hide: 'true'}"></th>
										<th lay-data="{field: 'billsNum', align: 'center', sort: true}">单号</th>
										<th
											lay-data="{field: 'departmentName', align: 'center', sort: true}">部门名称</th>
										<th
											lay-data="{field: 'warehouseName', align: 'center', sort: true}">仓库</th>
										<th
											lay-data="{field: 'staffName', align: 'center', sort: true}">经办人</th>
										<th
											lay-data="{field: 'staffNames', align: 'center', sort: true}">批准人</th>
										<th
											lay-data="{field: 'billsEntryTimes', align: 'center', sort: true}">时间</th>
										<th
											lay-data="{field: 'billsRemark', align: 'center', sort: true}">备注</th>
										<th
											lay-data="{field: 'billsMoney', align: 'center', sort: true}">金额</th>
									</tr>
								</thead>
							</table>
						</div>
					</div>
				</div>
				<div class="layui-tab-item">
					<div class="layui-card">
						<div class="layui-card-header">查询条件如下：</div>
						<div class="layui-card-body" style="padding: 0;">
							<form class="layui-form" style="margin: 1% 0 2% 0;">
								<div class="layui-form-item">
									<div class="layui-col-lg3 layui-col-md3 layui-col-sm3">
										<label class="layui-form-label">单号:</label>
										<div class="layui-input-block">
											<input type="text" id="billsNum5" autocomplete="off"
												class="layui-input">
										</div>
									</div>
									<div
										class="layui-col-lg3 layui-col-md3 layui-col-sm3 layui-col-md-offset1">
										<label class="layui-form-label">部门:</label>
										<div class="layui-input-block">
											<select id="departmentId2" lay-search>
												<option value="0">----请选择----</option>
												<c:forEach items="${departments}" var="department">
													<option value="${department.departmentId}">${department.departmentName}</option>
												</c:forEach>
											</select>
										</div>
									</div>
								</div>
								<div class="layui-form-item">
									<div class="layui-col-lg3 layui-col-md3 layui-col-sm3">
										<label class="layui-form-label">日期:</label>
										<div class="layui-input-block">
											<input type="text" id="startTime6" autocomplete="off"
												class="layui-input times" placeholder="yyyy-MM-dd">
											&nbsp;&nbsp;至&nbsp;&nbsp; <input type="text" id="endTime6"
												autocomplete="off" class="layui-input times"
												placeholder="yyyy-MM-dd">
										</div>
									</div>
									<div
										class="layui-col-lg3 layui-col-md3 layui-col-sm3 layui-col-md-offset1">
										<label class="layui-form-label">批准人:</label>
										<div class="layui-input-block">
											<select id="staffID4" lay-search>
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
											<select id="warehouseId6" lay-search>
												<option value="0">----请选择----</option>
												<c:forEach items="${warehouses}" var="warehouse">
													<option value="${warehouse.warehouseId}">${warehouse.warehouseName}</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<div
										class="layui-col-lg3 layui-col-md3 layui-col-sm3 layui-col-md-offset1">
										<label class="layui-form-label">经办人:</label>
										<div class="layui-input-block">
											<select id="staffId5" lay-search>
												<option value="0">----请选择----</option>
												<c:forEach items="${staffs}" var="staff">
													<option value="${staff.staffId}">${staff.staffName}</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<div class="layui-col-lg5 layui-col-md5 layui-col-sm5">
										<div class="layui-input-block">
											<button type="button" class="layui-btn" onclick="refesh('btnPickingCreditOrders')">
												<i class="layui-icon layui-icon-refresh"> <span>
														刷 新</span> </i>
											</button>
											<button type="button" class="layui-btn" id="btnPickingCreditOrders"
											onclick="searchOrder('5', '6', '6', '2', '5', '4', 'findPickingCreditOrders')">
												<i class="layui-icon layui-icon-search"> <span> 搜
														索</span> </i>
											</button>
											<button type="button" class="layui-btn layui-btn-warm" 
											onclick="showDetail('findPickingCreditOrders');">
												<i class="layui-icon layui-icon-search"> <span> 明
														细</span> </i>
											</button>
										</div>
									</div>
								</div>
							</form>
							<table class="layui-table"
								lay-data="{
											height: 471,
											id: 'findPickingCreditOrders',											
											page: true,
											limit: 10,//每页默认显示的数量
											method: 'post', //提交方式								
											}"
								lay-filter="findPickingCreditOrders">
								<thead>
									<tr>
										<th lay-data="{type: 'radio', hide: 'true'}"></th>
										<th lay-data="{field: 'billsNum', align: 'center', sort: true}">单号</th>
										<th
											lay-data="{field: 'departmentName', align: 'center', sort: true}">部门名称</th>
										<th
											lay-data="{field: 'warehouseName', align: 'center', sort: true}">仓库</th>
										<th
											lay-data="{field: 'staffName', align: 'center', sort: true}">经办人</th>
										<th
											lay-data="{field: 'staffNames', align: 'center', sort: true}">批准人</th>
										<th
											lay-data="{field: 'billsEntryTimes', align: 'center', sort: true}">时间</th>
										<th
											lay-data="{field: 'billsRemark', align: 'center', sort: true}">备注</th>
										<th
											lay-data="{field: 'billsMoney', align: 'center', sort: true}">金额</th>
									</tr>
								</thead>
							</table>
						</div>
					</div>
				</div>
				<div class="layui-tab-item">
					<div class="layui-card">
						<div class="layui-card-header">查询条件如下：</div>
						<div class="layui-card-body" style="padding: 0;">
							<form class="layui-form" style="margin: 1% 0 2% 0;">
								<div class="layui-form-item">
									<div class="layui-col-lg3 layui-col-md3 layui-col-sm3">
										<label class="layui-form-label">单号:</label>
										<div class="layui-input-block">
											<input type="text" id="billsNum6" autocomplete="off"
												class="layui-input">
										</div>
									</div>
									<div
										class="layui-col-lg3 layui-col-md3 layui-col-sm3 layui-col-md-offset1">
										<label class="layui-form-label">调出仓库:</label>
										<div class="layui-input-block">
											<select id="warehouseID1" lay-search>
												<option value="0">----请选择----</option>
												<c:forEach items="${warehouses}" var="warehouse">
													<option value="${warehouse.warehouseId}">${warehouse.warehouseName}</option>
												</c:forEach>
											</select>
										</div>
									</div>
								</div>
								<div class="layui-form-item">
									<div class="layui-col-lg3 layui-col-md3 layui-col-sm3">
										<label class="layui-form-label">日期:</label>
										<div class="layui-input-block">
											<input type="text" id="startTimes" autocomplete="off"
												class="layui-input times" placeholder="yyyy-MM-dd">
											&nbsp;&nbsp;至&nbsp;&nbsp; <input type="text" id="endTimes"
												autocomplete="off" class="layui-input times"
												placeholder="yyyy-MM-dd">
										</div>
									</div>
									<div
										class="layui-col-lg3 layui-col-md3 layui-col-sm3 layui-col-md-offset1">
										<label class="layui-form-label">调入仓库:</label>
										<div class="layui-input-block">
											<select id="warehouseId7" lay-search>
												<option value="0">----请选择----</option>
												<c:forEach items="${warehouses}" var="warehouse">
													<option value="${warehouse.warehouseId}">${warehouse.warehouseName}</option>
												</c:forEach>
											</select>
										</div>
									</div>
								</div>
								<div class="layui-form-item">
									<div class="layui-col-lg3 layui-col-md3 layui-col-sm3">										
										<label class="layui-form-label">批准人:</label>
										<div class="layui-input-block">
											<select id="staffID5" lay-search>
												<option value="0">----请选择----</option>
												<c:forEach items="${staffs}" var="staff">
													<option value="${staff.staffId}">${staff.staffName}</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<div
										class="layui-col-lg3 layui-col-md3 layui-col-sm3 layui-col-md-offset1">
										<label class="layui-form-label">经办人:</label>
										<div class="layui-input-block">
											<select id="staffId6" lay-search>
												<option value="0">----请选择----</option>
												<c:forEach items="${staffs}" var="staff">
													<option value="${staff.staffId}">${staff.staffName}</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<div class="layui-col-lg5 layui-col-md5 layui-col-sm5">
										<div class="layui-input-block">
											<button type="button" class="layui-btn" onclick="refesh('btnWarehouseTransferOrder')">
												<i class="layui-icon layui-icon-refresh"> <span>
														刷 新</span> </i>
											</button>
											<button type="button" class="layui-btn" id="btnWarehouseTransferOrder"
											onclick="searchOrder('6', '7', 's', '1', '6', '5', 'findWarehouseTransferOrder')">
												<i class="layui-icon layui-icon-search"> <span> 搜
														索</span> </i>
											</button>
											<button type="button" class="layui-btn layui-btn-warm" 
											onclick="showDetail('findWarehouseTransferOrder');">
												<i class="layui-icon layui-icon-search"> <span> 明
														细</span> </i>
											</button>
										</div>
									</div>
								</div>
							</form>
							<table class="layui-table"
								lay-data="{
											height: 471,
											id: 'findWarehouseTransferOrder',											
											page: true,
											limit: 10,//每页默认显示的数量
											method: 'post', //提交方式								
											}"
								lay-filter="findWarehouseTransferOrder">
								<thead>
									<tr>
										<th lay-data="{type: 'radio', hide: 'true'}"></th>
										<th lay-data="{field: 'billsNum', align: 'center', sort: true}">单号</th>
										<th
											lay-data="{field: 'warehouseNames', align: 'center', sort: true}">调出仓库</th>
										<th
											lay-data="{field: 'warehouseName', align: 'center', sort: true}">调入仓库</th>
										<th
											lay-data="{field: 'staffName', align: 'center', sort: true}">经办人</th>
										<th
											lay-data="{field: 'staffNames', align: 'center', sort: true}">批准人</th>
										<th
											lay-data="{field: 'billsEntryTimes', align: 'center', sort: true}">时间</th>
										<th
											lay-data="{field: 'billsRemark', align: 'center', sort: true}">备注</th>
										<th
											lay-data="{field: 'billsMoney', align: 'center', sort: true}">金额</th>
									</tr>
								</thead>
							</table>
						</div>
					</div>
				</div>
				<div class="layui-tab-item">
					<div class="layui-card">
						<div class="layui-card-header">查询条件如下：</div>
						<div class="layui-card-body" style="padding: 0;">
							<form class="layui-form" style="margin: 1% 0 2% 0;">		
								<div class="layui-form-item">
									<div class="layui-col-lg3 layui-col-md3 layui-col-sm3">
										<label class="layui-form-label">调出仓库:</label>
										<div class="layui-input-block">
											<select id="warehouseID2" lay-search>
												<option value="0">----请选择----</option>
												<c:forEach items="${warehouses}" var="warehouse">
													<option value="${warehouse.warehouseId}">${warehouse.warehouseName}</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<div class="layui-col-lg3 layui-col-md3 layui-col-sm3 layui-col-md-offset1">
										<label class="layui-form-label">物品编号:</label>
										<div class="layui-input-block">
											<input type="text" id="rawMaterialNum1" autocomplete="off"
												class="layui-input">
										</div>
									</div>
									<div class="layui-col-lg3 layui-col-md3 layui-col-sm3 layui-col-md-offset1">
										<label class="layui-form-label">日期:</label>
										<div class="layui-input-block">
											<input type="text" id="startTime7" autocomplete="off"
												class="layui-input times" placeholder="yyyy-MM-dd">
											&nbsp;&nbsp;至&nbsp;&nbsp; <input type="text" id="endTime7"
												autocomplete="off" class="layui-input times"
												placeholder="yyyy-MM-dd">
										</div>
									</div>									
								</div>						
								<div class="layui-form-item">
									<div class="layui-col-lg3 layui-col-md3 layui-col-sm3">
										<label class="layui-form-label">调入仓库:</label>
										<div class="layui-input-block">
											<select id="warehouseId8" lay-search>
												<option value="0">----请选择----</option>
												<c:forEach items="${warehouses}" var="warehouse">
													<option value="${warehouse.warehouseId}">${warehouse.warehouseName}</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<div class="layui-col-lg3 layui-col-md3 layui-col-sm3 layui-col-md-offset1">
										<label class="layui-form-label">物品名称:</label>
										<div class="layui-input-block">
											<input type="text" id="rawMaterialName1" autocomplete="off"
												class="layui-input">
										</div>
									</div>
									<div class="layui-col-lg4 layui-col-md4 layui-col-sm4 layui-col-md-offset1">
										<button type="button" class="layui-btn" style="margin-left: 7%;" id="btnMaterial2"
										onclick="searchMaterial(7, 8, 2, 0, 1, 'findMaterial2');">
											<i class="layui-icon layui-icon-search"> <span> 搜
													索</span> </i>
										</button>
									</div>
								</div>								
							</form>
							<table class="layui-table"
								lay-data="{
											height: 471,
											id: 'findMaterial2',
											page: true,
											totalRow: true,
											limit: 10,//每页默认显示的数量
											method: 'post', //提交方式								
											}"
								lay-filter="findMaterial2">
								<thead>
									<tr>
										<th
											lay-data="{field: 'rawMaterialNum', align: 'center', sort: true}">物品编号</th>
										<th
											lay-data="{field: 'rawMaterialName', align: 'center', sort: true}">物品名称</th>
										<th
											lay-data="{field: 'rawMaterialAmount', align: 'center', sort: true}">数量</th>
										<th
											lay-data="{field: 'rawMaterialPrice', align: 'center', sort: true}">单价</th>
										<th
											lay-data="{field: 'totalPrice', align: 'center', sort: true}">金额</th>
									</tr>
								</thead>
							</table>
						</div>
					</div>
				</div>
				<div class="layui-tab-item">
					<div class="layui-card">
						<div class="layui-card-header">查询条件如下：</div>
						<div class="layui-card-body" style="padding: 0;">
							<form class="layui-form" style="margin: 1% 0 2% 0;">		
								<div class="layui-form-item">
									<div class="layui-col-lg3 layui-col-md3 layui-col-sm3">
										<label class="layui-form-label">日期:</label>
										<div class="layui-input-block">
											<input type="text" id="startTime8" autocomplete="off"
												class="layui-input times" placeholder="yyyy-MM-dd">
											&nbsp;&nbsp;至&nbsp;&nbsp; <input type="text" id="endTime8"
												autocomplete="off" class="layui-input times"
												placeholder="yyyy-MM-dd">
										</div>
									</div>
									<div class="layui-col-lg3 layui-col-md3 layui-col-sm3 layui-col-md-offset1">
										<label class="layui-form-label">物品编号:</label>
										<div class="layui-input-block">
											<input type="text" id="rawMaterialNum2" autocomplete="off"
												class="layui-input">
										</div>
									</div>																		
								</div>						
								<div class="layui-form-item">
									<div class="layui-col-lg3 layui-col-md3 layui-col-sm3">
										<label class="layui-form-label">部门:</label>
										<div class="layui-input-block">
											<select id="departmentId3" lay-search>
												<option value="0">----请选择----</option>
												<c:forEach items="${departments}" var="department">
													<option value="${department.departmentId}">${department.departmentName}</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<div class="layui-col-lg3 layui-col-md3 layui-col-sm3 layui-col-md-offset1">
										<label class="layui-form-label">物品名称:</label>
										<div class="layui-input-block">
											<input type="text" id="rawMaterialName2" autocomplete="off"
												class="layui-input">
										</div>
									</div>
									<div class="layui-col-lg5 layui-col-md5 layui-col-sm5">
										<div class="layui-input-block">
											<button type="button" class="layui-btn" id="btnMaterial3"
											onclick="searchMaterial(8, 0, 0, 3, 2, 'findMaterial3');">
												<i class="layui-icon layui-icon-search"> <span>
														搜 索</span> </i>
											</button>											
										</div>
									</div>
								</div>								
							</form>
							<table class="layui-table"
								lay-data="{
											height: 471,
											id: 'findMaterial3',
											page: true,
											limit: 10,//每页默认显示的数量
											method: 'post', //提交方式								
											}"
								lay-filter="findMaterial3">
								<thead>
									<tr>
										<th
											lay-data="{field: 'rawMaterialNum', align: 'center', sort: true}">物品编号</th>
										<th
											lay-data="{field: 'rawMaterialName', align: 'center', sort: true}">物品名称</th>
										<th
											lay-data="{field: 'rawMaterialAmount', align: 'center', sort: true}">数量</th>
										<th
											lay-data="{field: 'rawMaterialPrice', align: 'center', sort: true}">单价</th>
										<th
											lay-data="{field: 'totalPrice', align: 'center', sort: true}">金额</th>
									</tr>
								</thead>
							</table>
						</div>
					</div>
				</div>				
			</div>
		</div>
		<div id="modRawMaterial" style="display: none;">
			<table class="layui-table"
				lay-data="{
						height: 471,
						id: 'findMaterials',
						page: true,
						limit: 10,//每页默认显示的数量
						method: 'post', //提交方式 							
					}"
				lay-filter="findMaterials">
				<thead>
					<tr>									
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
							lay-data="{field: 'rawMaterialAmount', align: 'center', sort: true}">原料数量</th>
						<th
							lay-data="{field: 'remark', align: 'center', sort: true}">备注</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
  </body>
  <script type="text/javascript">
  	var table;
  	var layer;
  	//注意：选项卡 依赖 element 模块，否则无法进行功能性操作
	layui.use(["element", "form", "table", "layer", "laydate"], function() {
		element = layui.element,
		form = layui.form,//表单
		table = layui.table,
		layer = layui.layer,
		laydate = layui.laydate;
		
		//日期
  		laydate.render({
    		elem: '#startTime1'
  		});
  		laydate.render({
    		elem: '#endTime1'
  		});
  		laydate.render({
    		elem: '#startTime2'
  		});
  		laydate.render({
    		elem: '#endTime2'
  		});
  		laydate.render({
    		elem: '#startTime3'
  		});
  		laydate.render({
    		elem: '#endTime3'
  		});
  		laydate.render({
    		elem: '#startTime4'
  		});
  		laydate.render({
    		elem: '#endTime4'
  		});
  		laydate.render({
    		elem: '#startTime5'
  		});
  		laydate.render({
    		elem: '#endTime5'
  		});
  		laydate.render({
    		elem: '#startTime6'
  		});
  		laydate.render({
    		elem: '#endTime6'
  		});
  		laydate.render({
    		elem: '#startTimes'
  		});
  		laydate.render({
    		elem: '#endTimes'
  		});
  		laydate.render({
    		elem: '#startTime7'
  		});
  		laydate.render({
    		elem: '#endTime7'
  		});
  		laydate.render({
    		elem: '#startTime8'
  		});
  		laydate.render({
    		elem: '#endTime8'
  		});
  		
  		$(".layui-tab-title li").click(function() {		
			var btnId = "btn" + $(this)[0].id;
  			$(".layui-form").resetForm();//重置表单
  			$("#" + btnId).trigger("click");
  		});	
	});
	/*--------------点击表格行勾选复选框或单选框----------------*/
	$(document).on("click", ".layui-table-body table.layui-table tbody tr td", function () { 
	 	if ($(this).attr("data-field") === "0") return;
	 	$(this).siblings().eq(0).find('i').click(); 
	});
	/*--------------盘点单查询----------------*/
	function searchCheck(){
		var billsNum = $("#billsNum1").val();
		var warehouseId = $("#warehouseId1").val();
        var startTime = $("#startTime1").val();
        var endTime = $("#endTime1").val();
        var staffId = $("#staffId1").val();
        if (billsNum == undefined) {
            billsNum = "";
        }
        if (warehouseId == "" || warehouseId == undefined) {
            warehouseId = 0;
        }            
        if (startTime == undefined) {
            startTime = "";
        }
        if (endTime == undefined) {
            endTime = "";
        }
        if (staffId == "" || staffId == undefined) {
            staffId = 0;
        }            
		table.reload('findCheckOrder', {
			page: {
				curr: 1 //重新从第 1 页开始
			},
			where: {
				billsNum: billsNum,
				warehouseId: warehouseId,
				startTime: startTime,
				endTime: endTime,
				staffId: staffId
			}
		});
	}
	/*--------------单据汇总查询----------------*/
	function searchMaterial(t, w1, w2, d, r, tableId){	
        var startTime = $("#startTime" + t).val();
		var endTime = $("#endTime" + t).val();
		var warehouseId = $("#warehouseId" + w1).val();
		var warehouseID = $("#warehouseID" + w2).val();
		var departmentId = $("#departmentId" + d).val();
		var rawMaterialNum = $("#rawMaterialNum" + r).val();
		var rawMaterialName = $("#rawMaterialName" + r).val();
		if (startTime == undefined) {
            startTime = "";
        }
        if (endTime == undefined) {
            endTime = "";
        }
        if (warehouseId == "" || warehouseId == undefined) {
            warehouseId = 0;
        }
        if (warehouseID == "" || warehouseID == undefined) {
            warehouseID = 0;
        }
        if (departmentId == "" || departmentId == undefined) {
            departmentId = 0;
        } 
        if (rawMaterialNum == undefined) {
            rawMaterialNum = "";
        }
        if (rawMaterialName == undefined) {
            rawMaterialName = "";
        }
        if (tableId == "findMaterial1") {
			table.reload(tableId, {
				url: "${ctx}/servlet/FindOrderServlet?type=findMaterial&findType=" + tableId,
				page: {
					curr: 1 //重新从第 1 页开始
				},
				where: {
					startTime: startTime,
					endTime: endTime,
					warehouseId: warehouseId
				}
			});
		} else if (tableId == "findMaterial2") {
			table.reload(tableId, {
				url: "${ctx}/servlet/FindOrderServlet?type=findMaterial&findType=" + tableId,
				page: {
					curr: 1 //重新从第 1 页开始
				},
				where: {
					warehouseId: warehouseId,
					rawMaterialNum: rawMaterialNum,
					startTime: startTime,
					endTime: endTime,
					warehouseID: warehouseID,
					rawMaterialName: rawMaterialName
				}
			});
		} else if (tableId == "findMaterial3") {
			table.reload(tableId, {
				url: "${ctx}/servlet/FindOrderServlet?type=findMaterial&findType=" + tableId,
				page: {
					curr: 1 //重新从第 1 页开始
				},
				where: {					
					startTime: startTime,
					endTime: endTime,
					departmentId: departmentId,
					rawMaterialNum: rawMaterialNum,
					rawMaterialName: rawMaterialName
				}
			});
		}		
	}
	/*--------------自定义方法----------------*/
	function refesh(btnId){
		$(".layui-form").resetForm();//重置表单
		$("#" + btnId).trigger("click");		
	}
	function showTable(tableId, Url, Class, title, modId){
		table.reload(tableId, {
			url: Url
		});
		layer.open({
	    	type: 1,//类型
	      	title: '<i class="layui-icon ' + Class + '"> ' + title + '</i>',
	      	offset: ['8%','28%'],
	      	area:['800px','536px'],//定义宽和高
	      	shadeClose: true,//点击遮罩层关闭
	      	content: $("#" + modId)//打开的内容
	    });
	}
	function searchOrder(b, w, t, s, s1, s2, tableId){
		var supplierId = "";
		var departmentId = "";
		var warehouseID = "";
		var billsNum = $("#billsNum" + b).val();
		var warehouseId = $("#warehouseId" + w).val();
        var startTime = $("#startTime" + t).val();
        var endTime = $("#endTime" + t).val();
        if (tableId == "findGodownOrders" || tableId == "findCreditOrders") {
			supplierId = $("#supplierId" + s).val();
			if (supplierId == "" || supplierId == undefined) {
            	supplierId = 0;
       		} 
		}        
		if (tableId == "findStocksRequisition" || tableId == "findPickingCreditOrders") {
			departmentId = $("#departmentId" + s).val();
			if (departmentId == "" || departmentId == undefined) {
            	departmentId = 0;
       		} 
		}
		if (tableId == "findWarehouseTransferOrder") {
			warehouseID = $("#warehouseID" + s).val();
			if (warehouseID == "" || warehouseID == undefined) {
            	warehouseID = 0;
       		}
		}
        var staffId = $("#staffId" + s1).val();
        var staffID = $("#staffID" + s2).val();
        if (billsNum == undefined) {
            billsNum = "";
        }
        if (warehouseId == "" || warehouseId == undefined) {
            warehouseId = 0;
        }            
        if (startTime == undefined) {
            startTime = "";
        }
        if (endTime == undefined) {
            endTime = "";
        }
        
        if (staffId == "" || staffId == undefined) {
            staffId = 0;
        }
        if (staffID == "" || staffID == undefined) {
            staffID = 0;
        }           
        if (supplierId != "") {
			table.reload(tableId, {
				url: '${ctx}/servlet/FindOrderServlet?type=findOrder&findType=' + tableId,
				page: {
					curr: 1 //重新从第 1 页开始
				},
				where: {
					billsNum: billsNum,
					warehouseId: warehouseId,
					startTime: startTime,
					endTime: endTime,
					supplierId: supplierId,
					staffId: staffId,
					staffID: staffID
				}
			});
		} else if (departmentId != "") {
			table.reload(tableId, {
				url: '${ctx}/servlet/FindOrderServlet?type=findOrder&findType=' + tableId,
				page: {
					curr: 1 //重新从第 1 页开始
				},
				where: {
					billsNum: billsNum,
					warehouseId: warehouseId,
					startTime: startTime,
					endTime: endTime,
					departmentId: departmentId,
					staffId: staffId,
					staffID: staffID
				}
			});
		} else if (warehouseID != "") {
			table.reload(tableId, {
				url: '${ctx}/servlet/FindOrderServlet?type=findOrder&findType=' + tableId,
				page: {
					curr: 1 //重新从第 1 页开始
				},
				where: {
					billsNum: billsNum,
					warehouseId: warehouseId,
					startTime: startTime,
					endTime: endTime,
					warehouseID: warehouseID,
					staffId: staffId,
					staffID: staffID
				}
			});
		}		
	}
	function showDetail(tableId){
		var checkStatus = table.checkStatus(tableId),
  			data = checkStatus.data;
  			if (data[0] != null || data[0] != undefined) {
				showTable("findMaterials", 
						"${ctx}/servlet/FindOrderServlet?type=findMaterial&findType=findMaterials&billsId=" + data[0].billsId, 
						"layui-icon-menu-fill", "单据明细","modRawMaterial");
			}
	}
  </script>
</html>
