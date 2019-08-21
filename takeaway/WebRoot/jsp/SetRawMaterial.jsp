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
    
    <title>My JSP 'SetRawMaterial.jsp' starting page</title>
    
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
			max-height: 138px;
			height: auto;
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
		<div class="layui-card">
			<div class="layui-card-header" style="font-size: 20px;padding: 0.6%;">原材料设置</div>
			<div class="layui-card-body">
				<div class="layui-card">
					<div class="layui-card-header">原材料大类</div>
					<div class="layui-card-body" style="padding: 0;">
						<table class="layui-table" id="big"
							lay-data="{
							height: 200, 
							id:'big',
							url:'${ctx}/servlet/RawMaterialServlet?type=tabMaterialType&rawMaterialType=rawMaterialBig',
							page: true,
							limit: 3,//每页默认显示的数量
							limits: [3,10,20,30,50],
							method: 'post', //提交方式 		
							}"
							lay-filter="big">
							<thead>
								<tr>
									<th
										lay-data="{type: 'numbers', width: 80,fixed: 'left', align: 'center', sort: true}">序号</th>
									<th
										lay-data="{field:'rawMaterialBigNum', align: 'center', sort: true}">编号</th>
									<th
										lay-data="{field:'rawMaterialBigName', align: 'center', sort: true}">名称</th>
									<th
										lay-data="{fixed: 'right', width: 165, align:'center', toolbar: '#barBtn'}"></th>
								</tr>
							</thead>
						</table>
						<script type="text/html" id="barBtn">
  							<a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="add">新增</a>
  							<a class="layui-btn layui-btn-xs" lay-event="edit">修改</a>
  							<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
						</script>
					</div>
				</div>
				<div class="layui-card">
					<div class="layui-card-header">原材料小类</div>
					<div class="layui-card-body" style="padding: 0;">
						<table class="layui-table" id="small"
							lay-data="{
							height: 200,
							id: 'small',
							page: true,
							limit: 3,//每页默认显示的数量
							limits: [3,10,20,30,50],
							method: 'post', //提交方式 									
							}"
							lay-filter="small">
							<thead>
								<tr>
									<th
										lay-data="{type: 'numbers', width: 80,fixed: 'left', align: 'center', sort: true}">序号</th>
									<th
										lay-data="{field: 'rawMaterialSmallNum', align: 'center', sort: true}">编号</th>
									<th
										lay-data="{field: 'rawMaterialSmallName', align: 'center', sort: true}">名称</th>
									<th
										lay-data="{fixed: 'right', width: 165, align:'center', toolbar: '#barBtn'}"></th>
								</tr>
							</thead>
						</table>
					</div>
				</div>
				<div class="layui-card">
					<div class="layui-card-header">原材料信息</div>
					<div class="layui-card-body" style="padding: 0;">
						<table class="layui-table" id="rawMaterial"
							lay-data="{
							height: 200, 
							id:'rawMaterial',
							page: true,
							limit: 5,//每页默认显示的数量
							limits: [5,10,20,30,50],
							method: 'post', //提交方式 		
							}"
							lay-filter="rawMaterial">
							<thead>
								<tr>
									<th lay-data="{type: 'checkbox', fixed: 'left'}">选择</th>
									<th
										lay-data="{type: 'numbers', width: 80,fixed: 'left', align: 'center', sort: true}">序号</th>
									<th
										lay-data="{field:'rawMaterialNum', align: 'center', sort: true}">编号</th>
									<th lay-data="{field:'rawMaterialName', align: 'center', sort: true}">名称</th>
									<th lay-data="{field:'unitName', align: 'center', sort: true}">单位</th>
									<th
										lay-data="{field:'rawMaterialPrice', align: 'center', sort: true}">单价</th>
									<th lay-data="{field:'pinyinCode', align: 'center', sort: true}">拼音码</th>							
									<th
										lay-data="{fixed: 'right', width: 165, align:'center', toolbar: '#barBtn'}"></th>
								</tr>
							</thead>
						</table>
					</div>
				</div>
			</div>
		</div>
		<div id="modRawMaterialBig" style="display: none;">
			<div
				class="layui-col-lg9 layui-col-md9 layui-col-sm9 layui-col-md-offset1">
				<form id="formRawMaterialBig" class="layui-form" method="post"
					action="${ctx}/servlet/RawMaterialServlet">
					<input type="text" style="display:none" name="type"
						value="addEidtSave"> <input type="text"
						style="display:none" name="rawMaterialBigId"> <input
						type="text" style="display:none" name="rawMaterialType"
						value="rawMaterialBig">
					<div class="layui-form-item">
						<label class="layui-form-label">编号</label>
						<div class="layui-input-block">
							<input type="text" name=rawMaterialBigNum required
								lay-verify="required" autocomplete="off" class="layui-input"
								readonly="readonly">
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">名称</label>
						<div class="layui-input-block">
							<input type="text" name="rawMaterialBigName" required
								lay-verify="fname" autocomplete="off" class="layui-input">
						</div>
					</div>
					<div class="layui-form-item"
						style="text-align: center;margin-left: 18%;">
						<div class="layui-btn-container">
							<button type="button" class="layui-btn" lay-submit id="btnBig"
								lay-filter="formBig">保存</button>
							<button type="button" class="layui-btn layui-btn-danger"
								onclick="modHide();">取消</button>
						</div>
					</div>
				</form>
			</div>
		</div>
		<div id="modRawMaterialSmall" style="display: none;">
			<div
				class="layui-col-lg9 layui-col-md9 layui-col-sm9 layui-col-md-offset1">
				<form id="formRawMaterialSmall" class="layui-form"
					action="${ctx}/servlet/RawMaterialServlet">
					<input type="text" style="display:none" name="type"
						value="addEidtSave"> <input type="text"
						style="display:none" name="rawMaterialSmallId"> <input
						type="text" style="display:none" name="rawMaterialType"
						value="rawMaterialSmall"> <input type="text"
						style="display:none" name="rawMaterialBigId">
					<div class="layui-form-item">
						<label class="layui-form-label">编号</label>
						<div class="layui-input-block">
							<input type="text" name="rawMaterialSmallNum" required
								lay-verify="required" autocomplete="off" class="layui-input"
								readonly="readonly">
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">名称</label>
						<div class="layui-input-block">
							<input type="text" name="rawMaterialSmallName" required
								lay-verify="fname" autocomplete="off" class="layui-input">
						</div>
					</div>
					<div class="layui-form-item"
						style="text-align: center;margin-left: 18%;">
						<div class="layui-btn-container">
							<button type="button" class="layui-btn" lay-submit id="btnSmall"
								lay-filter="formSmall">保存</button>
							<button type="button" class="layui-btn layui-btn-danger"
								onclick="modHide();">取消</button>
						</div>
					</div>
				</form>
			</div>
		</div>
		<div id="modRawMaterial" style="display: none;">
			<div
				class="layui-col-lg9 layui-col-md9 layui-col-sm9 layui-col-md-offset1">				
				<form id="formRawMaterial" class="layui-form" method="post"
					action="${ctx}/servlet/RawMaterialServlet">
					<input type="text" style="display:none" name="type"
						value="addEidtSave"> <input type="text" 
						style="display:none" name="rawMaterialId"> <input
						type="text" style="display:none" name="rawMaterialSmallId">	<input
						type="text" style="display:none" name="rawMaterialType"
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
							<input type="text" name="rawMaterialName" required lay-verify="fname"
								autocomplete="off" class="layui-input" onblur="pim();">
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
							<input type="text" name="pinyinCode" required lay-verify="required"
								autocomplete="off" class="layui-input" readonly="readonly">
						</div>
					</div>					
					<div class="layui-form-item"
						style="text-align: center;margin-left: 18%;">
						<div class="layui-btn-container">
							<button type="button" class="layui-btn" id="btnRawMaterial"
								lay-submit lay-filter="frawMaterial">保存</button>
							<button type="button" class="layui-btn layui-btn-danger"
								onclick="modHide();">取消</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		layui.use(['table', 'layer', 'form'], function(){
			element = layui.element;
  			table = layui.table;//表格
    		layer = layui.layer;//layer弹出框
    		form = layui.form;//表单
    		
    		//表单条件限制
    		form.verify({
				fname: function (value){
					if (value.length < 2) {
						return "请输入至少2个字的用户名";
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
				fdouble: function (value){
					if(/[^\d.]/g.test(value)){
				      	return "只能输入数字";
				    }
				},
				sunit: function(value) {
					if (value == "0" || value == "") {
						return "请选择一个单位";
					}
				}	
			});
			
			//监听行工具事件
			table.on('tool(big)', function(obj){ //注：tool 是工具条事件名
				var data = obj.data, //获得当前行数据
				layEvent = obj.event; //获得 lay-event 对应的值
			  	if(layEvent === 'add'){
			    	$("#formRawMaterialBig").resetForm();//重置表单
			    	$.getJSON("${ctx}/servlet/RawMaterialServlet?type=getNumber"
			    	+"&rawMaterialType=rawMaterialBig", function(data){
			    		if (data != null) {
							$("#formRawMaterialBig [name='rawMaterialBigNum']")
							.val(data.rawMaterialBigNum);
							$("#btnBig").empty().append("保存");					 	  			 	  			   	  
						    layer.open({
						    	type: 1,//类型
						      	title: '<i class="layui-icon layui-icon-add-1"> 新增原料大类信息</i>',
						      	offset: ['5%','35%'],
						      	area:['500px','238px'],//定义宽和高
						      	shadeClose:false,//点击遮罩层关闭
						      	content: $("#modRawMaterialBig")//打开的内容
						    });
						}
			    	});
			  	} else if(layEvent === 'edit'){
			    	$("#btnBig").empty().append("修改");
		        	$.ajaxSettings.ansyc = false;
		        	$.getJSON("${ctx}/servlet/RawMaterialServlet", { type: "findTypeById", 
		        	rawMaterialBigId: data.rawMaterialBigId, rawMaterialType: "rawMaterialBig" }, 
		        	function(datas){
		        		if (datas != null) {
							loadDatatoForm("formRawMaterialBig", datas);
							layer.open({
							    type: 1,//类型
							    title: '<i class="layui-icon layui-icon-add-1"> 修改原料大类信息</i>',
							    offset: ['5%','35%'],
							    area:['500px','238px'],//定义宽和高
							    shadeClose:false,//点击遮罩层关闭
							    content: $("#modRawMaterialBig")//打开的内容
							 });
						}
		        	});
			  	} else if(layEvent === 'del'){
			    	layer.confirm('真的删除行么', {
		            	icon: 3,
		                btn: ['确定', '取消']
		            }, function (index) {		
		            	layer.close(index); 		
			      		$.ajax({
		                	url: "${ctx}/servlet/RawMaterialServlet?type=delRawMaterialType" + "&rawMaterialBigId=" + 
		                	data.rawMaterialBigId + "&rawMaterialType=rawMaterialBig",		                    
		                	type: "post",//数据传输通道的类型
		                    async: false,
		                    dataType: "json",//传输的数据的类型
		                    success: function (datas) {//直接理解为回调函数
		                 		if (datas.state) {
		                        	obj.del(); //删除对应行（tr）的DOM结构			      					
		                        } else {
		                        	layer.msg(datas.msg, { icon: 2, skin: "layui-layer-molv" });
		                        }
		                    }
		                });
			   		});
			  	}
			});
			//监听提交
            form.on('submit(formBig)', function(data){
                console.log(data.field);
              	formSubmit("formRawMaterialBig", "big"); 
				return false;
            });
            //监听行单击事件（单击事件为：rowDouble）
			var rawMaterialBigId;
			table.on('row(big)', function(obj){
				var data = obj.data;
				rawMaterialBigId = data.rawMaterialBigId;
			  	table.reload("small", {
			  		url: "${ctx}/servlet/RawMaterialServlet?type=tabMaterialType&rawMaterialType=rawMaterialSmall",
			  		where: {
			  			rawMaterialBigId: data.rawMaterialBigId
			  		},
			  	});			  	
			  	//标注选中样式
			  	obj.tr.addClass('layui-table-click').siblings().removeClass('layui-table-click');
			});
			
			//监听行工具事件
			table.on('tool(small)', function(obj){ //注：tool 是工具条事件名
				var data = obj.data, //获得当前行数据
				layEvent = obj.event; //获得 lay-event 对应的值
			  	if(layEvent === 'add'){
			    	$("#formRawMaterialSmall").resetForm();//重置表单
			    	$.getJSON("${ctx}/servlet/RawMaterialServlet?type=getNumber"
			    	+"&rawMaterialType=rawMaterialSmall", function(data){
			    		if (data != null) {
			    			$("#formRawMaterialSmall [name='rawMaterialBigId']")
							.val(rawMaterialBigId);
							$("#formRawMaterialSmall [name='rawMaterialSmallNum']")
							.val(data.rawMaterialSmallNum);
							$("#btnSmall").empty().append("保存");					 	  			 	  			   	  
						    layer.open({
						    	type: 1,//类型
						      	title: '<i class="layui-icon layui-icon-add-1"> 新增原料小类信息</i>',
						      	offset: ['5%','35%'],
						      	area:['500px','238px'],//定义宽和高
						      	shadeClose:false,//点击遮罩层关闭
						      	content: $("#modRawMaterialSmall")//打开的内容
						    });
						}
			    	});
			  	} else if(layEvent === 'edit'){
			    	$("#btnSmall").empty().append("修改");
		        	$.ajaxSettings.ansyc = false;
		        	$.getJSON("${ctx}/servlet/RawMaterialServlet", { type: "findTypeById", 
		        	rawMaterialSmallId: data.rawMaterialSmallId, rawMaterialType: "rawMaterialSmall" }, 
		        	function(datas){
		        		if (datas != null) {
							loadDatatoForm("formRawMaterialSmall", datas);
							layer.open({
							    type: 1,//类型
							    title: '<i class="layui-icon layui-icon-add-1"> 修改原料小类信息</i>',
							    offset: ['5%','35%'],
							    area:['500px','238px'],//定义宽和高
							    shadeClose:false,//点击遮罩层关闭
							    content: $("#modRawMaterialSmall")//打开的内容
							 });
						}
		        	});
			  	} else if(layEvent === 'del'){
			    	layer.confirm('真的删除行么', {
		            	icon: 3,
		                btn: ['确定', '取消']
		            }, function (index) {		
		            	layer.close(index); 		
			      		$.ajax({
		                	url: "${ctx}/servlet/RawMaterialServlet?type=delRawMaterialType" + "&rawMaterialSmallId=" + 
		                	data.rawMaterialSmallId + "&rawMaterialType=rawMaterialSmall",		                    
		                	type: "post",//数据传输通道的类型
		                    async: false,
		                    dataType: "json",//传输的数据的类型
		                    success: function (datas) {//直接理解为回调函数
		                 		if (datas.state) {
		                        	obj.del(); //删除对应行（tr）的DOM结构			      					
		                        } else {
		                        	layer.msg(datas.msg, { icon: 2, skin: "layui-layer-molv" });
		                        }
		                    }
		                });
			   		});
			  	}
			});
			//监听提交
            form.on('submit(formSmall)', function(data){
                console.log(data.field);
              	formSubmit("formRawMaterialSmall", "small"); 
				return false;
            });
            //监听行单击事件（单击事件为：rowDouble）
			var rawMaterialSmallId;
			table.on('row(small)', function(obj){
				var data = obj.data;
				rawMaterialSmallId = data.rawMaterialSmallId;
			  	table.reload("rawMaterial", {
			  		url: "${ctx}/servlet/RawMaterialServlet?type=tabMaterialType&rawMaterialType=rawMaterial",
			  		where: {
			  			rawMaterialSmallId: data.rawMaterialSmallId
			  		},
			  	});			  	
			  	//标注选中样式
			  	obj.tr.addClass('layui-table-click').siblings().removeClass('layui-table-click');
			});
			
			//监听行工具事件
			table.on('tool(rawMaterial)', function(obj){ //注：tool 是工具条事件名
				var data = obj.data, //获得当前行数据
				layEvent = obj.event; //获得 lay-event 对应的值
			  	if(layEvent === 'add'){
			    	$("#formRawMaterial").resetForm();//重置表单
			    	$.getJSON("${ctx}/servlet/RawMaterialServlet?type=getNumber"
			    	+"&rawMaterialType=rawMaterial", function(data){
			    		if (data != null) {
							$("#formRawMaterial [name='rawMaterialSmallId']")
							.val(rawMaterialSmallId);
							$("#formRawMaterial [name='rawMaterialNum']")
							.val(data.rawMaterialNum);
							$("#btnRawMaterial").empty().append("保存");					 	  			 	  			   	  
						    layer.open({
						    	type: 1,//类型
						      	title: '<i class="layui-icon layui-icon-add-1"> 新增原料信息</i>',
						      	offset: ['5%','35%'],
						      	area:['600px','420px'],//定义宽和高
						      	shadeClose:false,//点击遮罩层关闭
						      	content: $("#modRawMaterial")//打开的内容
						    });
						}
			    	});
			  	} else if(layEvent === 'edit'){
			    	$("#btnRawMaterial").empty().append("修改");
		        	$.ajaxSettings.ansyc = false;
		        	$.getJSON("${ctx}/servlet/RawMaterialServlet", { type: "findTypeById", 
		        	rawMaterialId: data.rawMaterialId, rawMaterialType: "rawMaterial" }, 
		        	function(datas){
		        		if (datas != null) {
							loadDatatoForm("formRawMaterial", datas);
							layer.open({
							    type: 1,//类型
							    title: '<i class="layui-icon layui-icon-add-1"> 修改原料信息</i>',
							    offset: ['5%','35%'],
							    area:['600px','420px'],//定义宽和高
							    shadeClose:false,//点击遮罩层关闭
							    content: $("#modRawMaterial")//打开的内容
							 });
						}
		        	});
			  	} else if(layEvent === 'del'){
			    	layer.confirm('真的删除行么', {
		            	icon: 3,
		                btn: ['确定', '取消']
		            }, function (index) {		
		            	layer.close(index); 		
			      		$.ajax({
		                	url: "${ctx}/servlet/RawMaterialServlet?type=delRawMaterialType" + "&rawMaterialId=" + 
		                	data.rawMaterialId + "&rawMaterialType=rawMaterial",		                    
		                	type: "post",//数据传输通道的类型
		                    async: false,
		                    dataType: "json",//传输的数据的类型
		                    success: function (datas) {//直接理解为回调函数
		                 		if (datas.state) {
		                        	obj.del(); //删除对应行（tr）的DOM结构			      					
		                        } else {
		                        	layer.msg(datas.msg, { icon: 2, skin: "layui-layer-molv" });
		                        }
		                    }
		                });
			   		});
			  	}
			});
			//监听提交
            form.on('submit(frawMaterial)', function(data){
                console.log(data.field);
              	formSubmit("formRawMaterial", "rawMaterial"); 
				return false;
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
    	function pim(){
        	var name = $("#formRawMaterial [name='rawMaterialName']").val();
        	var simply = pinyin.getCamelChars(name);
        	$("#formRawMaterial [name='pinyinCode']").val(simply);
        }
	</script>
  </body>
</html>
