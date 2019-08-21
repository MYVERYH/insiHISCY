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
	
	<title>My JSP 'SetTables.jsp' starting page</title>
	
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
</head>

<body>
	<div class="layui-container">
		<div class="layui-card">
			<div class="layui-card-header" style="font-size: 20px;padding: 0.6%;">酒菜设置</div>
			<div class="layui-card-body">
				<div class="layui-card">
					<div class="layui-card-header">酒菜大类</div>
					<div class="layui-card-body" style="padding: 0;">
						<table class="layui-table" id="big"
							lay-data="{
							height: 199, 
							id:'big',
							url:'${ctx}/servlet/SetTablesServlet?type=tabWineGres&wineGreType=wineGreBig',
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
										lay-data="{field:'wineGreBigNum', align: 'center', sort: true}">编号</th>
									<th
										lay-data="{field:'wineGreBigName', align: 'center', sort: true}">酒菜大类名称</th>
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
					<div class="layui-card-header">酒菜小类</div>
					<div class="layui-card-body" style="padding: 0;">
						<table class="layui-table" id="small"
							lay-data="{
							height: 199,
							id:'small',
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
										lay-data="{field: 'wineGreSmallNum', align: 'center', sort: true}">编号</th>
									<th
										lay-data="{field: 'wineGreSmallName', align: 'center', sort: true}">酒菜小类名称</th>
									<th
										lay-data="{fixed: 'right', width: 165, align:'center', toolbar: '#barBtn'}"></th>
								</tr>
							</thead>
						</table>
					</div>
				</div>
				<div class="layui-card">
					<div class="layui-card-header">酒菜项目信息</div>
					<div class="layui-card-body" style="padding: 0;">
						<table class="layui-table" id="wineGre"
							lay-data="{
							height: 199, 
							id:'wineGre',
							page: true,
							limit: 5,//每页默认显示的数量
							limits: [5,10,20,30,50],
							method: 'post', //提交方式 		
							}"
							lay-filter="wineGre">
							<thead>
								<tr>
									<th lay-data="{type: 'checkbox', fixed: 'left'}">选择</th>
									<th
										lay-data="{type: 'numbers', width: 80,fixed: 'left', align: 'center', sort: true}">序号</th>
									<th
										lay-data="{field:'wineGreNum', align: 'center', sort: true}">编号</th>
									<th lay-data="{field:'wineGreName', align: 'center', sort: true}">酒菜名称</th>
									<th
										lay-data="{field:'wineGrePrice', align: 'center', sort: true}">酒菜价格</th>
									<th lay-data="{field:'bigPrice', align: 'center', sort: true}">大分价格</th>
									<th
										lay-data="{field:'smallPrice', align: 'center', sort: true}">小分价格</th>
									<th
										lay-data="{field:'memberPrice', align: 'center', sort: true}">会员价格</th>
									<th lay-data="{field:'isDiscount', align: 'center', templet: '#switch', unresize: true, sort: true}">可打折</th>
									<th
										lay-data="{fixed: 'right', width: 165, align:'center', toolbar: '#barBtn'}"></th>
								</tr>
							</thead>
						</table>
						<script type="text/html" id="switch">
							<input type="checkbox" name="isDiscount" value="{{d.isDiscount}}"
								lay-skin="switch" lay-text="是|否" lay-filter="IsDiscount" {{ d.isDiscount ? 'checked' : '' }}>
						</script>
					</div>
				</div>
			</div>
		</div>
		<div id="modWineGreBig" style="display: none;">
			<div
				class="layui-col-lg9 layui-col-md9 layui-col-sm9 layui-col-md-offset1">
				<form id="formWineGreBig" class="layui-form"
					action="${ctx}/servlet/SetTablesServlet">
					<input type="text" style="display:none" name="type"
						value="addEidtSave"> <input type="text"
						style="display:none" name="wineGreBigId"> <input
						type="text" style="display:none" name="wineGreType"
						value="wineGreBig">
					<div class="layui-form-item">
						<label class="layui-form-label">编号</label>
						<div class="layui-input-block">
							<input type="text" name="wineGreBigNum" required
								lay-verify="required" autocomplete="off" class="layui-input"
								readonly="readonly">
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">大类名称</label>
						<div class="layui-input-block">
							<input type="text" name="wineGreBigName" required
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
		<div id="modWineGreSmall" style="display: none;">
			<div
				class="layui-col-lg9 layui-col-md9 layui-col-sm9 layui-col-md-offset1">
				<form id="formWineGreSmall" class="layui-form"
					action="${ctx}/servlet/SetTablesServlet">
					<input type="text" style="display:none" name="type"
						value="addEidtSave"> <input type="text"
						style="display:none" name="wineGreSmallId"> <input
						type="text" style="display:none" name="wineGreType"
						value="wineGreSmall"> <input type="text"
						style="display:none" name="wineGreBigId">
					<div class="layui-form-item">
						<label class="layui-form-label">编号</label>
						<div class="layui-input-block">
							<input type="text" name="wineGreSmallNum" required
								lay-verify="required" autocomplete="off" class="layui-input"
								readonly="readonly">
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">小类名称</label>
						<div class="layui-input-block">
							<input type="text" name="wineGreSmallName" required
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
		<div id="modWineGre" style="display: none;">
			<div
				class="layui-col-lg9 layui-col-md9 layui-col-sm9 layui-col-md-offset1">
				<!-- 表单enctype属性指定数据返回服务器时的编码类型， multipart/form-data表示不对字符编码，上传文件时使用-->
				<form id="formWineGre" class="layui-form" method="post"
					action="${ctx}/servlet/SetTablesServlet"
					enctype="multipart/form-data">
					<input type="text" style="display:none" name="wineGreId"> <input
						type="text" style="display:none" name="wineGreSmallId">
					<div class="layui-form-item" style="text-align: center;">
						<div style="display: none">
							<input type="file" name="picture"
								class="form-control form-control-radius" id="Picture" />
						</div>
						<div class="layui-input-block">
							<img src="" alt="" width="220" height="130" id="ImgPicture" />
							<button type="button" class="layui-btn layui-btn-sm"
								id="pictureID" style="margin-top:100px;">&nbsp;图片</button>
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">编号</label>
						<div class="layui-input-block">
							<input type="text" name="wineGreNum" required
								lay-verify="required" autocomplete="off" class="layui-input"
								readonly="readonly">
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">酒菜名称</label>
						<div class="layui-input-block">
							<input type="text" name="wineGreName" required lay-verify="fname"
								autocomplete="off" class="layui-input">
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">酒菜价格</label>
						<div class="layui-input-block">
							<input type="text" name="wineGrePrice" required
								lay-verify="fdouble" autocomplete="off" class="layui-input">
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">大份价格</label>
						<div class="layui-input-block">
							<input type="text" name="bigPrice" required lay-verify="fdouble"
								autocomplete="off" class="layui-input">
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">小份价格</label>
						<div class="layui-input-block">
							<input type="text" name="smallPrice" required
								lay-verify="fdouble" autocomplete="off" class="layui-input">
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">会员价格</label>
						<div class="layui-input-block">
							<input type="text" name="memberPrice" required
								lay-verify="fdouble" autocomplete="off" class="layui-input">
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">是否可打折</label>
						<div class="layui-input-block">
							<input type="radio" name="isDiscount" value="1" title="是">
							<input type="radio" name="isDiscount" value="0" title="否" checked>
						</div>
					</div>
					<div class="layui-form-item"
						style="text-align: center;margin-left: 18%;">
						<div class="layui-btn-container">
							<button type="button" class="layui-btn" id="btnWineGre"
								lay-submit lay-filter="fWineGre">保存</button>
							<button type="button" class="layui-btn layui-btn-danger"
								onclick="modHide();">取消</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	<script type="text/javascript"
		src="${ctx}/Content/js/jquery-2.0.3.min.js"></script>
	<script type="text/javascript" src="${ctx}/Content/js/jquery.form.js"></script>	
	<script type="text/javascript" src="${ctx}/Content/js/echoform.js"></script>
	<script type="text/javascript" src="${ctx}/Content/layui/layui.js"></script>
	<script type="text/javascript">
  		var table;
  		var layer;
  		var form;
  		layui.use(['table', 'layer', 'form'], function(){
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
				}			
			});
    		
    		//监听行工具事件
			table.on('tool(big)', function(obj){ //注：tool 是工具条事件名
				var data = obj.data, //获得当前行数据
				layEvent = obj.event; //获得 lay-event 对应的值
			  	if(layEvent === 'add'){
			    	$("#formWineGreBig").resetForm();//重置表单
			    	$.getJSON("${ctx}/servlet/SetTablesServlet?type=getNumber&wineGreType=wineGreBig",
			    	function(data){
			    		if (data != null) {
							$("#formWineGreBig [name='wineGreBigNum']").val(data.wineGreBigNum);
							$("#btnBig").empty().append("保存");					 	  			 	  			   	  
						    layer.open({
						    	type: 1,//类型
						      	title: '<i class="layui-icon layui-icon-add-1"> 新增酒菜大类信息</i>',
						      	offset: ['5%','35%'],
						      	area:['500px','238px'],//定义宽和高
						      	shadeClose:false,//点击遮罩层关闭
						      	content: $("#modWineGreBig")//打开的内容
						    });
						}
			    	});
			  	} else if(layEvent === 'edit'){
			    	$("#btnBig").empty().append("修改");
		        	$.ajaxSettings.ansyc = false;
		        	$.getJSON("${ctx}/servlet/SetTablesServlet", { type: "findTypeById", 
		        	wineGreBigId: data.wineGreBigId, wineGreType: "wineGreBig" }, function(datas){
		        		if (datas != null) {
							loadDatatoForm("formWineGreBig", datas);
							layer.open({
							    type: 1,//类型
							    title: '<i class="layui-icon layui-icon-add-1"> 修改酒菜大类信息</i>',
							    offset: ['5%','35%'],
							    area:['500px','238px'],//定义宽和高
							    shadeClose:false,//点击遮罩层关闭
							    content: $("#modWineGreBig")//打开的内容
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
		                	url: "${ctx}/servlet/SetTablesServlet?type=delWineGreType&wineGreBigId=" + data.wineGreBigId + "&wineGreType=wineGreBig",		                    type: "get",//数据传输通道的类型
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
                console.log(data.field); //当前容器的全部表单字段,获取单个值data.field["title"]
              	formSubmit("formWineGreBig", "big");         
				return false;
            });
			//监听行单击事件（单击事件为：rowDouble）
			var wineGreBigId;
			table.on('row(big)', function(obj){
				var datas = obj.data;
				wineGreBigId = datas.wineGreBigId;
			  	table.reload("small", {
			  		url: "${ctx}/servlet/SetTablesServlet?type=tabWineGres&wineGreType=wineGreSmall",
			  		where: {
			  			wineGreBigId: datas.wineGreBigId
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
			    	$("#formWineGreSmall").resetForm();//重置表单
			    	$.getJSON("${ctx}/servlet/SetTablesServlet?type=getNumber&wineGreType=wineGreSmall",
			    	function(data){
			    		if (data != null) {
							$("#formWineGreSmall [name='wineGreSmallNum']").val(data.wineGreSmallNum);
							$("#formWineGreSmall [name='wineGreBigId']").val(wineGreBigId);							
							$("#btnSmall").empty().append("保存");				 	  			 	  			   	  
						    layer.open({
						    	type: 1,//类型
						      	title: '<i class="layui-icon layui-icon-add-1"> 新增酒菜小类信息</i>',
						      	offset: ['5%','35%'],
						      	area:['500px','238px'],//定义宽和高
						      	shadeClose:false,//点击遮罩层关闭
						      	content: $("#modWineGreSmall")//打开的内容
						    });
						}			    	
			    	});			    	
			  	} else if(layEvent === 'edit'){
			    	$("#btnSmall").empty().append("修改");
		        	$.ajaxSettings.ansyc = false;
		        	$.getJSON("${ctx}/servlet/SetTablesServlet", { type: "findTypeById", 
		        	wineGreSmallId: data.wineGreSmallId, wineGreType: "wineGreSmall" }, function(datas){
		        		if (datas != null) {
							loadDatatoForm("formWineGreSmall", datas);
							layer.open({
							    type: 1,//类型
							    title: '<i class="layui-icon layui-icon-add-1"> 修改酒菜小类信息</i>',
							    offset: ['5%','35%'],
							    area:['500px','238px'],//定义宽和高
							    shadeClose:false,//点击遮罩层关闭
							    content: $("#modWineGreSmall")//打开的内容
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
		                	url: "${ctx}/servlet/SetTablesServlet?type=delWineGreType&wineGreSmallId=" + data.wineGreSmallId + "&wineGreType=wineGreSmall", 
		                    type: "get",//数据传输通道的类型
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
                console.log(data.field); //当前容器的全部表单字段,获取单个值data.field["title"]
              	formSubmit("formWineGreSmall", "small");         
				return false;
            });  		
            var wineGreSmallId;
			table.on('row(small)', function(obj){
				var datas = obj.data;
				wineGreSmallId = datas.wineGreSmallId;
			  	table.reload("wineGre", {
			  		url: "${ctx}/servlet/SetTablesServlet?type=tabWineGres&wineGreType=wineGre",			  		
			  		where: {
			  			wineGreSmallId: datas.wineGreSmallId
			  		},
			  	});			  	
			  	//标注选中样式
			  	obj.tr.addClass('layui-table-click').siblings().removeClass('layui-table-click');
			});
			
			//监听可打折操作
  			form.on('switch(switch)', function(obj){
    			layer.tips(this.value + ' ' + this.name + '：'+ obj.elem.checked, obj.othis);
  			});
			
			//加载图片
       		$("#pictureID").click(function () {
            	$("#modWineGre input[name='picture']").click();
        	});
        	var imgReaderI = new FileReader();
        	regexImageFilter = /^(?:image\/bmp|image\/png|image\/jpeg|image\/jpg)$/i;
        	imgReaderI.onload = function (evt) {
            	$("#ImgPicture").attr('src', evt.target.result);
        	};
        	
        	$("#Picture").change(function () {
            	var imgfFile = $("#Picture").prop('files')[0];
            	var imgFileSize = Math.round(imgfFile.size/1024*100)/100;//获取文件大小
            	if (imgFileSize != 0 && imgFileSize > 2000) {
					layer.msg("请选择小于2M的图片!", { icon: 0, skin: "layui-layer-molv" });
				} else {
					//加载image标签中
	            	if (!regexImageFilter.test(imgfFile.type)) {
	                	//alert("选择的不是一个有效的图片文件");
	                	layer.msg('选择的不是一个有效的图片文件', { icon: 0 });
	            	}
	            	imgReaderI.readAsDataURL(imgfFile);
				}            		
        	});
        	//酒菜表格监听行工具事件
			table.on('tool(wineGre)', function(obj){ //注：tool 是工具条事件名
				var data = obj.data, //获得当前行数据
				layEvent = obj.event; //获得 lay-event 对应的值
			  	if(layEvent === 'add'){
			    	$("#formWineGre").resetForm();//重置表单
			    	$("#ImgPicture").attr("src", "");
			    	$.getJSON("${ctx}/servlet/SetTablesServlet?type=getNumber&wineGreType=wineGre",
			    	function(data){
			    		if (data != null) {
							$("#formWineGre [name='wineGreNum']").val(data.wineGreNum);
							$("#formWineGre [name='wineGreSmallId']").val(wineGreSmallId);							
							$("#btnWineGre").empty().append("保存");				 	  			 	  			   	  
						    layer.open({
						    	type: 1,//类型
						      	title: '<i class="layui-icon layui-icon-add-1"> 新增酒菜信息</i>',
						      	offset: ['5%','33%'],
						      	area:['600px','668px'],//定义宽和高
						      	shadeClose:false,//点击遮罩层关闭
						      	content: $("#modWineGre")//打开的内容
						    });
						}			    	
			    	});			    	
			  	} else if(layEvent === 'edit'){
			  		var wineGreId = data.wineGreId;
			  		$("#formWineGre").resetForm();//重置表单
			    	$("#btnWineGre").empty().append("修改");
			    	$("#ImgPicture").attr("src", "");
		        	$.ajaxSettings.ansyc = false;
		        	$.getJSON("${ctx}/servlet/SetTablesServlet", { type: "findTypeById", 
		        	wineGreId: wineGreId, wineGreType: "wineGre" }, function(datas){
		        		if (datas != null) {
		        			console.log(datas);		        			
		        			$("#ImgPicture").attr("src", "${ctx}/servlet/SetTablesServlet?type=rtPicture&wineGreId=" + wineGreId);	
		        			valDatatoForm("formWineGre", datas);				
							layer.open({
							   	type: 1,//类型
							   	title: '<i class="layui-icon layui-icon-add-1"> 修改酒菜信息</i>',
							   	offset: ['5%','33%'],
							   	area:['600px','668px'],//定义宽和高
							   	shadeClose:false,//点击遮罩层关闭
							   	content: $("#modWineGre")//打开的内容
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
		                	url: "${ctx}/servlet/SetTablesServlet?type=delWineGreType&wineGreId=" + data.wineGreId + "&wineGreType=wineGre", 
		                    type: "get",//数据传输通道的类型
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
            form.on('submit(fWineGre)', function(data){
                console.log(data.field); //当前容器的全部表单字段,获取单个值data.field["title"]
              	formSubmit("formWineGre", "wineGre");    
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
  	</script>
</body>
</html>
