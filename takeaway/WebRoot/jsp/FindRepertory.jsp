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
    
    <title>My JSP 'FindRepertory.jsp' starting page</title>
    
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
		
		.layui-btn i>span{
			position: relative;
			bottom: 1.5px;
			font-size: 14px;
		}
		
		.times{
			width: 43.5%;
			display: inline-block;
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
				<li id="NowRepertory" class="layui-this">当前库存</li>
				<li id="InOrOutRepertory">月出入库查询</li>
				<li id="RepertoryDetail">库存进销报表</li>
				<li id="RepertoryAlarm">库存报警</li>				
				<li id="SupplierYFK">供应商应付款查询</li>
				<li id="SupplierInfo">供应商供货查询</li>				
			</ul>
			<div class="layui-tab-content">
				<div class="layui-tab-item layui-show">
					<div class="layui-card">
						<div class="layui-card-header">当前库存查询条件如下：</div>
						<div class="layui-card-body" style="padding: 0;">
							<form class="layui-form" style="margin: 1% 0 2% 0;">
								<div class="layui-form-item">
									<div class="layui-col-lg3 layui-col-md3 layui-col-sm3">
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
									<div
										class="layui-col-lg3 layui-col-md3 layui-col-sm3 layui-col-md-offset1">
										<label class="layui-form-label">商品编号:</label>
										<div class="layui-input-block">
											<input type="text" id="rawMaterialNum1" autocomplete="off"
												class="layui-input">
										</div>
									</div>
								</div>
								<div class="layui-form-item">
									<div class="layui-col-lg3 layui-col-md3 layui-col-sm3">
										<label class="layui-form-label">商品类型</label>
										<div class="layui-input-block">
											<select id="rawMaterialBigId1" lay-search>
												<option value="0">---请选择---</option>
												<c:forEach items="${bigs}" var="big">
													<option value="${big.rawMaterialBigId}">${big.rawMaterialBigName}</option>
												</c:forEach>
											</select>
										</div>												
									</div>
									<div
										class="layui-col-lg3 layui-col-md3 layui-col-sm3 layui-col-md-offset1">
										<label class="layui-form-label">商品名称:</label>
										<div class="layui-input-block">
											<input type="text" id="rawMaterialName1" autocomplete="off"
												class="layui-input">
										</div>
									</div>
									<div class="layui-col-lg5 layui-col-md5 layui-col-sm5">
										<div class="layui-input-block">											
											<button type="button" class="layui-btn" id="btnNowRepertory"
											onclick="search(1,1,1,0,'findNowRepertory');">
												<i class="layui-icon layui-icon-search"> <span>
														搜 索</span> </i>
											</button>											
										</div>
									</div>
								</div>								
							</form>
							<table class="layui-table"
								lay-data="{
											height: 618,
											id: 'findNowRepertory',
											url: '${ctx}/servlet/FindRepertoryServlet?type=findRepertory&findType=findNowRepertory',
											page: true,
											limit: 10,//每页默认显示的数量
											method: 'post', //提交方式								
											}"
								lay-filter="findNowRepertory">
								<thead>
									<tr>
										<th
											lay-data="{field: 'warehouseName', align: 'center', sort: true}">仓库名称</th>
										<th
											lay-data="{field: 'rawMaterialNum', align: 'center', sort: true}">物品编号</th>
										<th
											lay-data="{field: 'rawMaterialName', align: 'center', sort: true}">物品名称</th>
										<th
											lay-data="{field: 'unitName', align: 'center', sort: true}">单位</th>
										<th
											lay-data="{field: 'rawMaterialBigName', align: 'center', sort: true}">类别</th>
										<th
											lay-data="{field: 'rawMaterialQuantity', align: 'center', sort: true}">数量</th>
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
										<label class="layui-form-label">营业期间:</label>
										<div class="layui-input-block">
											<input type="month" id="month" autocomplete="off"
												class="layui-input">											
										</div>
									</div>
									<div
										class="layui-col-lg3 layui-col-md3 layui-col-sm3 layui-col-md-offset1">
										<label class="layui-form-label">商品编号:</label>
										<div class="layui-input-block">
											<input type="text" id="rawMaterialNum2" autocomplete="off"
												class="layui-input">
										</div>
									</div>
								</div>
								<div class="layui-form-item">
									<div class="layui-col-lg3 layui-col-md3 layui-col-sm3">
										<label class="layui-form-label">仓库:</label>
										<div class="layui-input-block">
											<select id="warehouseId2" lay-search>
												<option value="0">----请选择----</option>
												<c:forEach items="${warehouses}" var="warehouse">
													<option value="${warehouse.warehouseId}">${warehouse.warehouseName}</option>
												</c:forEach>
											</select>
										</div>												
									</div>
									<div
										class="layui-col-lg3 layui-col-md3 layui-col-sm3 layui-col-md-offset1">
										<label class="layui-form-label">商品名称:</label>
										<div class="layui-input-block">
											<input type="text" id="rawMaterialName2" autocomplete="off"
												class="layui-input">
										</div>
									</div>
									<div class="layui-col-lg5 layui-col-md5 layui-col-sm5">
										<div class="layui-input-block">											
											<button type="button" class="layui-btn" id="btnInOrOutRepertory"
											onclick="search(2,2,2,0,'findInOrOutRepertory');">
												<i class="layui-icon layui-icon-search"> <span>
														搜 索</span> </i>
											</button>											
										</div>
									</div>
								</div>								
							</form>
							<table class="layui-table"
								lay-data="{
											height: 618,
											id: 'findInOrOutRepertory',											
											page: true,
											totalRow: true,
											limit: 10,//每页默认显示的数量
											method: 'post', //提交方式								
											}"
								lay-filter="findInOrOutRepertory">
								<thead>
									<tr>
										<th
											lay-data="{type: 'numbers', width: 60, fixed: 'left', align: 'center', totalRowText: '合计：'}">序号</th>
										<th
											lay-data="{field: 'rawMaterialNum', align: 'center', sort: true}">编号</th>
										<th
											lay-data="{field: 'rawMaterialName', align: 'center', sort: true}">名称</th>
										<th
											lay-data="{field: 'unitName', align: 'center', sort: true}">单位</th>
										<th
											lay-data="{field: 'godownQuantity', align: 'center', sort: true, totalRow: true}">本月入库数</th>
										<th
											lay-data="{field: 'goOutQuantity', align: 'center', sort: true, totalRow: true}">本月出库数</th>
										<th
											lay-data="{field: 'adjustQuantity', align: 'center', sort: true, totalRow: true}">库存调整数</th>
										<th
											lay-data="{field: 'rawMaterialQuantity', align: 'center', sort: true, totalRow: true}">当前库存数</th>
										<th
											lay-data="{field: 'rawMaterialPrice', align: 'center', sort: true}">平均成本单价</th>
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
										<label class="layui-form-label">营业时间:</label>
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
										<label class="layui-form-label">编号:</label>
										<div class="layui-input-block">
											<input type="text" id="rawMaterialNum3" autocomplete="off"
												class="layui-input">
										</div>
									</div>
								</div>
								<div class="layui-form-item">
									<div class="layui-col-lg3 layui-col-md3 layui-col-sm3">
										<label class="layui-form-label">仓库名称:</label>
										<div class="layui-input-block">
											<select id="warehouseId3" lay-search>
												<option value="0">----请选择----</option>
												<c:forEach items="${warehouses}" var="warehouse">
													<option value="${warehouse.warehouseId}">${warehouse.warehouseName}</option>
												</c:forEach>
											</select>
										</div>												
									</div>
									<div
										class="layui-col-lg3 layui-col-md3 layui-col-sm3 layui-col-md-offset1">
										<label class="layui-form-label">名称:</label>
										<div class="layui-input-block">
											<input type="text" id="rawMaterialName3" autocomplete="off"
												class="layui-input">
										</div>
									</div>
									<div class="layui-col-lg5 layui-col-md5 layui-col-sm5">
										<div class="layui-input-block">											
											<button type="button" class="layui-btn" id="btnRepertoryDetail"
											onclick="search(3,0,3,1,'findRepertoryDetail');">
												<i class="layui-icon layui-icon-search"> <span>
														搜 索</span> </i>
											</button>											
										</div>
									</div>
								</div>								
							</form>
							<table class="layui-table"
								lay-data="{
											height: 618,
											id: 'findRepertoryDetail',											
											page: true,
											limit: 10,//每页默认显示的数量
											method: 'post', //提交方式								
											}"
								lay-filter="findRepertoryDetail">
								<thead>
									<tr>
										<th
											lay-data="{type: 'numbers', width: 60, fixed: 'left', align: 'center'}">序号</th>
										<th
											lay-data="{field: 'rawMaterialNum', align: 'center', sort: true}">编号</th>
										<th
											lay-data="{field: 'rawMaterialName', align: 'center', sort: true}">名称</th>
										<th
											lay-data="{field: 'unitName', align: 'center', sort: true}">单位</th>
										<th
											lay-data="{field: 'godownQuantity', align: 'center', sort: true}">进货</th>
										<th
											lay-data="{field: 'goOutQuantity', align: 'center', sort: true}">退货</th>
										<th
											lay-data="{field: 'llQuantity', align: 'center', sort: true}">领料</th>
										<th
											lay-data="{field: 'ltQuantity', align: 'center', sort: true}">领退</th>
										<th
											lay-data="{field: 'dcQuantity', align: 'center', sort: true}">调出</th>
										<th
											lay-data="{field: 'drQuantity', align: 'center', sort: true}">调入</th>
										<th
											lay-data="{field: 'rawMaterialQuantity', align: 'center', sort: true}">库存</th>
										<th
											lay-data="{field: 'rawMaterialPrice', align: 'center', sort: true}">进价</th>
										<!-- <th
											lay-data="{field: 'profit', align: 'center', sort: true}">利润</th> -->
									</tr>
								</thead>
							</table>						
						</div>
					</div>
				</div>
				<div class="layui-tab-item">
					<div class="layui-card">
						<div class="layui-card-header" style="font-size: 22px;padding: 5px 0 5px 3px;">当前库存查询条件：</div>
						<div class="layui-card-body" style="padding: 0;">
							<form class="layui-form" style="margin: 1% 0 2% 0;">
								<div class="layui-form-item">
									<div class="layui-col-lg3 layui-col-md3 layui-col-sm3">
										<label class="layui-form-label">仓库名称:</label>
										<div class="layui-input-block">
											<select id="warehouseId4" lay-search>
												<option value="0">----请选择----</option>
												<c:forEach items="${warehouses}" var="warehouse">
													<option value="${warehouse.warehouseId}">${warehouse.warehouseName}</option>
												</c:forEach>
											</select>
										</div>	
									</div>
									<div class="layui-col-lg3 layui-col-md3 layui-col-sm3">
										<label class="layui-form-label">编号:</label>
										<div class="layui-input-block">
											<input type="text" id="rawMaterialNum5" autocomplete="off"
												class="layui-input">
										</div>
									</div>
									<div class="layui-col-lg3 layui-col-md3 layui-col-sm3">
										<label class="layui-form-label">名称:</label>
										<div class="layui-input-block">
											<input type="text" id="rawMaterialName5" autocomplete="off"
												class="layui-input">
										</div>
									</div>
									<div
										class="layui-col-lg3 layui-col-md3 layui-col-sm3">
										<div class="layui-input-block">											
											<button type="button" class="layui-btn" id="btnSearch3"
											onclick="search(4,0,5,0,'findRepertory');">
												<i class="layui-icon layui-icon-search"> <span>
														搜 索</span> </i>
											</button>	
											<button type="button" class="layui-btn layui-btn-normal" id="btnMinimumStock">
												<i class="layui-icon layui-icon-set-fill"> <span>
														设置最低库存</span> </i>
											</button>																									
										</div>
									</div>
								</div>
							</form>
							<table class="layui-table"
								lay-data="{
											height: 618,
											id: 'findRepertory',		
											url: '${ctx}/servlet/FindRepertoryServlet?type=findRepertory&findType=findRepertoryAlarm',
											page: true,									
											limit: 10,//每页默认显示的数量
											method: 'post', //提交方式								
											}"
								lay-filter="findRepertory">
								<thead>
									<tr>
										<th lay-data="{type: 'radio', hide: 'true'}"></th>
										<th
											lay-data="{field: 'warehouseName', align: 'center', sort: true}">仓库名称</th>
										<th
											lay-data="{field: 'rawMaterialNum', align: 'center', sort: true}">商品编号</th>
										<th
											lay-data="{field: 'rawMaterialName', align: 'center', sort: true}">商品名称</th>
										<th
											lay-data="{field: 'unitName', align: 'center', sort: true}">单位</th>
										<th
											lay-data="{field: 'rawMaterialBigName', align: 'center', sort: true}">商品类别</th>
										<th
											lay-data="{field: 'rawMaterialQuantity', align: 'center', sort: true}">当前库存</th>
										<th
											lay-data="{field: 'minimumQuantity', align: 'center', sort: true, templet: '#title'}">最低库存</th>	
										<th
											lay-data="{field: 'titleInfo', align: 'center', sort: true, templet: '#titleInfo'}">报警库存</th>						
										
									</tr>									
								</thead>
							</table>
							<script type="text/html" id="title">
								{{#  if(d.minimumQuantity === 0){ }}
    								<span style="color: #F581B1;">{{ '未设置最低库存' }}</span> 
								{{#  } else { }}
    								{{ d.minimumQuantity }} 								
  								{{#  } }}
							</script>
							<script type="text/html" id="titleInfo">
								{{#  if(d.titleInfo != ''){ }}
    								<span style="color: red;">{{ d.titleInfo }}</span>  								
  								{{#  } }}
							</script>
						</div>			
					</div>
					<div id="modMinimumStock" style="display: none;">
						<div
							class="layui-col-lg9 layui-col-md9 layui-col-sm9 layui-col-md-offset1">
							<form id="formMinimumStock" class="layui-form" method="post"
								action="${ctx}/servlet/FindRepertoryServlet"
								style="margin-top: 26px;">
								<input type="text" style="display:none" name="type"
									value="addMinimumStock"> <input type="text" style="display:none"
										name="repertoryId">
								<div class="layui-form-item">
									<label class="layui-form-label">仓库名称:</label>
										<div class="layui-input-block">
											<select name="warehouseId" lay-search disabled="disabled">
												<option value="0">----请选择----</option>
												<c:forEach items="${warehouses}" var="warehouse">
													<option value="${warehouse.warehouseId}">${warehouse.warehouseName}</option>
												</c:forEach>
											</select>
										</div>
								</div>
								<div class="layui-form-item">
									<label class="layui-form-label">编号</label>
									<div class="layui-input-block">
										<input type="text" name="rawMaterialNum" lay-verify="required" 
											autocomplete="off" class="layui-input"
											readonly="readonly">
									</div>
								</div>
								<div class="layui-form-item">
									<label class="layui-form-label">名称</label>
									<div class="layui-input-block">
										<input type="text" name="rawMaterialName" autocomplete="off" class="layui-input" readonly="readonly">
									</div>
								</div>	
								<div class="layui-form-item">
									<label class="layui-form-label">最低库存数</label>
									<div class="layui-input-block">
										<input type="text" name="minimumQuantity" required lay-verify="required|fdouble"
										autocomplete="off" class="layui-input">
									</div>
								</div>								
								<div class="layui-form-item"
									style="text-align: center;margin-left: 18%;">
									<div class="layui-btn-container">
										<button type="button" class="layui-btn" id="btnMinimum"
											lay-submit lay-filter="fMinimumStock">保存</button>
										<button type="button" class="layui-btn layui-btn-danger" onclick="modHide();">取消</button>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
				<div class="layui-tab-item">
					<div class="layui-card">
						<div class="layui-card-header" style="font-size: 22px;padding: 5px 0 5px 3px;">查询供应商应付款：</div>
						<div class="layui-card-body" style="padding: 0;">
							<form class="layui-form" style="margin: 1% 0 2% 0;">
								<div class="layui-form-item">
									<div class="layui-col-lg3 layui-col-md3 layui-col-sm3">
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
									<div class="layui-col-lg3 layui-col-md3 layui-col-sm3 layui-col-md-offset1">
										<label class="layui-form-label">供货时间:</label>
										<div class="layui-input-block">
											<input type="text" id="startTime3" autocomplete="off"
												class="layui-input times" placeholder="yyyy-MM-dd">
											&nbsp;&nbsp;至&nbsp;&nbsp;
											<input type="text" id="endTime3" autocomplete="off"
													class="layui-input times" placeholder="yyyy-MM-dd">											
										</div>
									</div>
									<div
										class="layui-col-lg3 layui-col-md3 layui-col-sm3 layui-col-md-offset1">
										<div class="layui-input-block">											
											<button type="button" class="layui-btn layui-btn-normal" id="btnSearch1"
											onclick="search(2,0,0,3,'findSupplierPayment');">
												<i class="layui-icon layui-icon-search"> <span>
														搜 索</span> </i>
											</button>	
											<button type="button" class="layui-btn" id="btnPayment">
												<i class="layui-icon layui-icon-login-wechat"> <span>
														付 款</span> </i>
											</button>										
										</div>
									</div>
								</div>
							</form>
							<table class="layui-table"
								lay-data="{
											height: 199,
											id: 'findSupplierPayment',		
											url: '${ctx}/servlet/FindRepertoryServlet?type=findSupplier&findType=findSupplierPayment',
											page: true,
											limit: 3,//每页默认显示的数量
											limits: [3,6,10,20,30,50],
											method: 'post', //提交方式								
											}"
								lay-filter="findSupplierPayment">
								<thead>
									<tr>
										<th lay-data="{type: 'radio', hide: 'true'}"></th>
										<th
											lay-data="{field: 'rawMaterialBigName', align: 'center', sort: true}">类别</th>
										<th
											lay-data="{field: 'supplierName', align: 'center', sort: true}">供应商名称</th>
										<th
											lay-data="{field: 'totalMoney', align: 'center', sort: true}">应付款金额</th>
									</tr>
								</thead>
							</table>
						</div>
					</div>
					<div class="layui-card">
						<div class="layui-card-header" style="font-size: 22px;padding: 5px 0 5px 3px;">查询供应商付款记录：</div>
						<div class="layui-card-body" style="padding: 0;">
							<form class="layui-form" style="margin: 1% 0 2% 0;">
								<div class="layui-form-item">
									<div class="layui-col-lg3 layui-col-md3 layui-col-sm3 layui-col-md-offset4">
										<label class="layui-form-label">供应商:</label>
										<div class="layui-input-block">
											<select id="supplierId3" lay-search>
												<option value="0">----请选择----</option>
												<c:forEach items="${suppliers}" var="supplier">
													<option value="${supplier.supplierId}">${supplier.supplierName}</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<div
										class="layui-col-lg3 layui-col-md3 layui-col-sm3 layui-col-md-offset1">
										<div class="layui-input-block">											
											<button type="button" class="layui-btn layui-btn-normal" id="btnSearch2"
											onclick="search(3,0,0,0,'findSupplierPaymentInfo');">
												<i class="layui-icon layui-icon-search"> <span>
														搜 索</span> </i>
											</button>										
										</div>
									</div>
								</div>
							</form>							
							<table class="layui-table"
								lay-data="{
											height: 316,
											id: 'findSupplierPaymentInfo',	
											url: '${ctx}/servlet/FindRepertoryServlet?type=findSupplier&findType=findSupplierPaymentInfo',	
											limit: 6,//每页默认显示的数量
											limits: [6,10,20,30,50],
											method: 'post', //提交方式								
											}"
								lay-filter="findSupplierPaymentInfo">
								<thead>
									<tr>
										<th
											lay-data="{field: 'supplierName', align: 'center', sort: true}">供应商名称</th>
										<th
											lay-data="{field: 'totalMoney', align: 'center', sort: true}">付款金额</th>
										<th
											lay-data="{field: 'paymentDates', align: 'center', sort: true}">付款日期</th>
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
									<div
										class="layui-col-lg3 layui-col-md3 layui-col-sm3 layui-col-md-offset1">
										<label class="layui-form-label">原料类别:</label>
										<div class="layui-input-block">
											<select id="rawMaterialBigId2" lay-search>
												<option value="0">----请选择----</option>
												<c:forEach items="${bigs}" var="big">
													<option value="${big.rawMaterialBigId}">${big.rawMaterialBigName}</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<div
										class="layui-col-lg3 layui-col-md3 layui-col-sm3 layui-col-md-offset1">
										<label class="layui-form-label">经办人:</label>
										<div class="layui-input-block">
											<select id="staffId" lay-search>
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
										<label class="layui-form-label">日期:</label>
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
										<label class="layui-form-label">名称:</label>
										<div class="layui-input-block">
											<input type="text" id="rawMaterialName4" autocomplete="off"
												class="layui-input">
										</div>
									</div>
									<div class="layui-col-lg5 layui-col-md5 layui-col-sm5">
										<div class="layui-input-block">											
											<button type="button" class="layui-btn" id="btnSupplierInfo"
											onclick="search(1,2,4,2,'findSupplierInfo');">
												<i class="layui-icon layui-icon-search"> <span>
														搜 索</span> </i>
											</button>											
										</div>
									</div>
								</div>								
							</form>
							<table class="layui-table"
								lay-data="{
											height: 618,
											id: 'findSupplierInfo',											
											page: true,
											limit: 10,//每页默认显示的数量
											method: 'post', //提交方式								
											}"
								lay-filter="findSupplierInfo">
								<thead>
									<tr>
										<th
											lay-data="{type: 'numbers', width: 60, fixed: 'left', align: 'center'}">序号</th>										
										<th
											lay-data="{field: 'supplierName', align: 'center', sort: true}">供应商名称</th>
										<th
											lay-data="{field: 'billsEntryTimes', align: 'center', sort: true}">日期</th>
										<th
											lay-data="{field: 'rawMaterialName', align: 'center', sort: true}">原料名称</th>
										<th
											lay-data="{field: 'rawMaterialBigName', align: 'center', sort: true}">原料类型</th>
										<th
											lay-data="{field: 'unitName', align: 'center', sort: true}">单位</th>
										<th
											lay-data="{field: 'rawMaterialAmount', align: 'center', sort: true}">数量</th>
										<th
											lay-data="{field: 'rawMaterialPrice', align: 'center', sort: true}">价格</th>
										<th
											lay-data="{field: 'totalPrice', align: 'center', sort: true}">应付金额</th>
										<th
											lay-data="{field: 'staffName', align: 'center', sort: true}">经办人</th>
										<th
											lay-data="{field: 'staffNames', align: 'center', sort: true}">批准人</th>
									</tr>
								</thead>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		var table;
	  	var layer;
	  	//注意：选项卡 依赖 element 模块，否则无法进行功能性操作
		layui.use(["element", "form", "table", "layer", "laydate"], function() {
			element = layui.element,
			form = layui.form,//表单
			table = layui.table,//表格
			layer = layui.layer,
			laydate = layui.laydate;//时间
			
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
 
	  		
	  		//表单条件限制
    		form.verify({
    			fdouble: function (value){
					if(/[^\d.]/g.test(value)){
				      	return "只能输入数字";
				    }
				}
    		});
	  		
	  		//点击选项卡清空form表单同时加载对应卡片内表格数据
	  		$(".layui-tab-title li").click(function() {
  				var btnId = "btn" + $(this)[0].id;
  				$(".layui-form").resetForm();//重置表单
  				$("#" + btnId).trigger("click");
  				$("#btnSearch1").trigger('click');
				$("#btnSearch2").trigger('click');
				$("#btnSearch3").trigger('click');
	  		});
	  		
	  		/*----------------------保存供应商付款记录----------------------*/
	  		$("#btnPayment").click(function() {
	  			var checkStatus=table.checkStatus('findSupplierPayment'),
	  			data = checkStatus.data[0];
	  			if (checkStatus.data.length > 0) {
	  				layer.confirm('<div style="padding: 10px 35px 10px 35px; line-height: 22px; font-weight: 500;">供 应 商：<span style="color: red;">' 
	  				+ data.supplierName + '</span><br>付款金额：<span style="color: red;">' + data.totalMoney + '</span></div>', {
	  					btn: ['确定付款', '取消付款'],
	  					btn1: function(index) {
							layer.close(index);
							$.getJSON("${ctx}/servlet/FindRepertoryServlet",{ type: "addPayment", 
							supplierId: data.supplierId, totalMoney: data.totalMoney}, function(data) {
								if (data != null || data != undefined) {
									if (data.state) {
										layer.alert(data.msg, {
											icon : 1,
											title : '提示'
										}, function(index) {
											layer.close(index);
											$(".layui-form").resetForm();//重置表单
											$("#btnSearch1").trigger('click');
											$("#btnSearch2").trigger('click');
										});										
									} else {
										layer.alert(data.msg, {
											icon : 2,
											title : '提示'
										});
									}
								}								
							});
						}
	  				});	
				} else {
					layer.msg("请点击一行数据");
				}
	  		});
	  		
	  		/*-----------------------保存最低仓库原料最低库存设置-------------------------*/
	  		$("#btnMinimumStock").click(function() {
	  			var checkStatus=table.checkStatus('findRepertory'),
	  			data = checkStatus.data[0];
	  			if (checkStatus.data.length > 0) {
	  				loadDatatoForm("formMinimumStock", data);
	  				form.render('select');
	  				if (data.minimumQuantity > 0) {
						$("#btnMinimum").empty().append("修改");
					} else {	
						$("#btnMinimum").empty().append("保存");
					}	
					layer.open({
					    	type: 1,//类型
					      	title: '<i class="layui-icon layui-icon-add-1"> 设置最低库存信息</i>',
					      	offset: ['5%','33%'],
					      	area:['500px','360px'],//定义宽和高
					      	shadeClose: true,//点击遮罩层关闭
					      	content: $("#modMinimumStock")//打开的内容
						});  				
	  			}else {
					layer.msg("请点击一行数据");
				}
	  		});
	  		//监听提交原料模态框
            form.on('submit(fMinimumStock)', function(data){
                console.log(data.field);
              	$("#formMinimumStock").ajaxSubmit(function(jsonObject){
	    			if (jsonObject != "") {
						data = JSON.parse(jsonObject);
	        			if (data.state) {
	        				modHide();
							layer.alert(data.msg, {
								icon : 1,
								title : '提示'
							},function(layerIndex){
								layer.close(layerIndex);
								$("#btnSearch3").trigger('click');
								$("#btnRepertoryAlarm").trigger('click');
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
		});
		/*--------------点击表格行勾选复选框或单选框----------------*/
		$(document).on("click", ".layui-table-body table.layui-table tbody tr td", function () { 
		 	if ($(this).attr("data-field") === "0") return;
		 	$(this).siblings().eq(0).find('i').click(); 
		});
		/*--------------点击搜索按钮查询数据----------------*/
		function search(w, b, n, d,tableId){
			var warehouseId = $("#warehouseId" + w).val();//仓库
			var rawMaterialBigId = $("#rawMaterialBigId" + b).val();//类型(原料大类)
			var supplierId = $("#supplierId" + w).val();//供应商
			var staffId = $("#staffId").val();//经办人
			var rawMaterialNum = $("#rawMaterialNum" + n).val();//原料编号
			var rawMaterialName = $("#rawMaterialName" + n).val();//原料名称
			var month = $("#month").val();//月份
			var startTime = $("#startTime" + d).val();//开始时间
        	var endTime = $("#endTime" + d).val();//结束时间
			if (warehouseId == "" || warehouseId == undefined) {
	            warehouseId = 0;
	        }
	        if (supplierId == "" || supplierId == undefined) {
	            supplierId = 0;
	        }
	        if (rawMaterialBigId == "" || rawMaterialBigId == undefined) {
	            rawMaterialBigId = 0;
	        }
	        if (supplierId == "" || supplierId == undefined) {
	            supplierId = 0;
	        }
	        if (rawMaterialNum == undefined) {
	            rawMaterialNum = "";
	        }
	        if (rawMaterialName == undefined) {
	            rawMaterialName = "";
	        }
	        if (month == undefined) {
	            month = "";
	        }
	        if (startTime == undefined) {
	            startTime = "";
	        }
	        if (endTime == undefined) {
	            endTime = "";
	        }
	        if (tableId == "findNowRepertory") {//当前库存表格
				table.reload(tableId, {
		        	page: {
						curr: 1 //重新从第 1 页开始
					},
					where: {
						warehouseId: warehouseId,
						rawMaterialBigId: rawMaterialBigId,
						rawMaterialNum: rawMaterialNum,
						rawMaterialName: rawMaterialName
					}
		        });
			} else if (tableId == "findInOrOutRepertory") {//月出入库表格
				table.reload(tableId, {
					url: "${ctx}/servlet/FindRepertoryServlet?type=findRepertory&findType=" + tableId,
		        	page: {
						curr: 1 //重新从第 1 页开始
					},
					where: {
						month: month,
						rawMaterialNum: rawMaterialNum,
						warehouseId: warehouseId,						
						rawMaterialName: rawMaterialName
					}
		        });
			} else if (tableId == "findRepertoryDetail") {//库存进销表格
				table.reload(tableId, {
					url: "${ctx}/servlet/FindRepertoryServlet?type=findRepertory&findType=" + tableId,
		        	page: {
						curr: 1 //重新从第 1 页开始
					},
					where: {
						startTime: startTime,
						endTime: endTime,
						rawMaterialNum: rawMaterialNum,
						warehouseId: warehouseId,			
						rawMaterialName: rawMaterialName
					}
		        });
			} else if (tableId == "findSupplierInfo") {//供应商供货表格
				table.reload(tableId, {
					url: "${ctx}/servlet/FindRepertoryServlet?type=findSupplier&findType=" + tableId,
		        	page: {
						curr: 1 //重新从第 1 页开始
					},
					where: {
						supplierId: supplierId,
						rawMaterialBigId: rawMaterialBigId,
						staffId: staffId,
						startTime: startTime,
						endTime: endTime,	
						rawMaterialName: rawMaterialName
					}
		        });
			} else if (tableId == "findSupplierPayment") {//供应商应付款表格
				table.reload(tableId, {
					url: "${ctx}/servlet/FindRepertoryServlet?type=findSupplier&findType=" + tableId,
		        	page: {
						curr: 1 //重新从第 1 页开始
					},
					where: {
						supplierId: supplierId,						
						startTime: startTime,
						endTime: endTime,	
					}
		        });
			} else if (tableId == "findSupplierPaymentInfo") {//供应商付款记录表格
				table.reload(tableId, {
					url: "${ctx}/servlet/FindRepertoryServlet?type=findSupplier&findType=" + tableId,
		        	page: {
						curr: 1 //重新从第 1 页开始
					},
					where: {
						supplierId: supplierId
					}
		        });
			} else if (tableId == "findRepertory") {//库存表格
				table.reload(tableId, {
		        	page: {
						curr: 1 //重新从第 1 页开始
					},
					where: {
						warehouseId: warehouseId,
						rawMaterialNum: rawMaterialNum,
						rawMaterialName: rawMaterialName
					}
		        });
			}else if (tableId == "findRepertoryAlarm") {
				table.reload(tableId, {
					url: "${ctx}/servlet/FindRepertoryServlet?type=findRepertory&findType=" + tableId,
		        	page: {
						curr: 1 //重新从第 1 页开始
					},
					where: {
						warehouseId: warehouseId,
						rawMaterialNum: rawMaterialNum,
						rawMaterialName: rawMaterialName
					}
		        });
			}
		}
		//关闭模态框
		function modHide() {
    		$(".layui-layer-close").trigger("click");
    	}
	</script>
  </body>
</html>
