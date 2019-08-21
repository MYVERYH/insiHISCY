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
    
    <title>My JSP 'OrderList.jsp' starting page</title>
    
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
		
		body{
			background-image: url("${ctx}/Content/images/maindesk.png");
            background-attachment: fixed !important;
            background-size: 100% 100% !important;
		}
		
		.layui-container{
			width: 76%;
			padding-top: 5%;			
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
		
		.text-left {
			text-align: left;
		}
		
		.text-right {
			text-align: right;
		}
		.form1 .layui-form-item{
            border-bottom:1px dashed #e0d3d3;
            padding-bottom:10px;
        }

        .form1 .layui-form-item .layui-col-lg2{
            padding-left:0;
        }

        .form2{
            border-top:1px solid #e0d3d3;
            padding-top:13px;
            margin-top:33px;
        }

        .form2 .layui-col-lg2,.form2 .layui-col-lg8{
            padding-left:14px;
            font-size:10px;
            font-weight:200;
        }
        
        .layui-rate{
        	padding: 0;
        }
        
        #modOrderEvaluate .form2 .layui-form-item{
        	margin-bottom: 0;
        }
        
        .layui-slider{
        	height: 12px;
        }
        
        .layui-slider-wrap-btn{
        	margin-top: 8px;
        }
        
        .laytable-cell-1-wineGreId{  /*最后的pic为字段的field*/
	       height: 100%;
	       max-width: 100%;
	   	} 
	   	
	   	#TotalSum,#Count,#PJCount{
            color:blue;
        }

        #TotalPrices,#PJTotalPrices{
            color:red;
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
			<div class="layui-card-header">订单列表</div>
			<div class="layui-card-body">
				<form class="layui-form" style="margin: 1% 0 2% 0;">
					<div class="layui-form-item">
						<div class="layui-col-lg2 layui-col-md2 layui-col-sm2">
							<input type="text" id="Parameter" autocomplete="off"
									class="layui-input">
						</div>
						<div class="layui-col-lg6 layui-col-md6 layui-col-sm6"
							style="padding-left: 20px;">
							<div class="layui-btn-container">
								<button type="button" class="layui-btn " id="btnSearch"
									onclick="searchIndent();">
									<i class="layui-icon layui-icon-search"> <span> 订单查询</span>
									</i>
								</button>
								<button type="button" class="layui-btn layui-btn-warm" id="btnBack">
									<i class="layui-icon layui-icon-return"> <span> 返回主页面</span>
									</i>
								</button>
								<span style="color:red;font-size:12px;margin-left: 15px;">*可查询订单编号、时间、状态</span>
							</div>	
						</div>
					</div>
				</form>
				<table class="layui-table"
					lay-data="{
								height: 568,
								id: 'findIndent',		
								url: '${ctx}/servlet/OrderListServlet?type=findIndent',
								page: true,									
								limit: 10,//每页默认显示的数量
								method: 'post', //提交方式								
								}"
					lay-filter="findIndent">
					<thead>
						<tr>
							<th lay-data="{field: 'indentNum', align: 'center', sort: true}">订单编号</th>
							<th lay-data="{field: 'wineGreName', align: 'center', sort: true, templet: '#wineGreInfo'}">菜品名称</th>
							<th lay-data="{field: 'totalPice', align: 'center', sort: true}">菜品价格</th>
							<th
								lay-data="{field: 'indentTimes', align: 'center', sort: true}">下单时间</th>
							<th
								lay-data="{field: 'orderStatus', align: 'center', sort: true}">状态</th>
							<th
								lay-data="{field: 'indentRemark', align: 'center', sort: true}">备注</th>
							<th lay-data="{field: '', align: 'center', sort: true, templet: '#operation'}">操作</th>
						</tr>
					</thead>
				</table>
				<script type="text/html" id="wineGreInfo">
  					{{#  if(d.wineGreName != ''){ }}
    					{{d.wineGreName}} <a href="javascript:;" style="color: #FF5722;" onclick="showDetail({{d.indentId}})">...</a>					
  					{{#  } }}
				</script>
				<script type="text/html" id="operation">
  					{{#  if(d.orderStatus === '未处理'){ }}
    					<a href="javascript:;" style="color: #FF8000;" onclick="showIndentDetail({{ d.indentId }})">处理</a><span> | </span><a href="javascript:;" style="color: #FE0000;" onclick="delIndent({{ d.indentId }})">删除</a>
  					{{#  } else if(d.orderStatus === '已确认'){ }}
    					<a href="javascript:;" style="color: #5FB878;" onclick="modInd({{ d.indentId }})">处理</a><span> | </span><a href="javascript:;" style="color: #FE0000;" onclick="modlas()">删除</a>
  					{{#  } else if(d.orderStatus === '待派送'){ }}
						<a href="javascript:;" style="color: #FF8000;" onclick="showPS({{ d.indentId }})">处理</a><span> | </span><a href="javascript:;" style="color: #FE0000;" onclick="modlay()">删除</a>
					{{#  } else if(d.orderStatus === '已完成'){ }}
						<a href="javascript:;" style="color: #1E9FFF;" onclick="modYMC()">查看</a><span> | </span><a href="javascript:;" style="color: #FE0000;">删除</a>
					{{#  } else if(d.orderStatus === '已评论'){ }}
						<a href="javascript:;" style="color: #1E9FFF;" onclick="ShowPJ({{ d.indentId }})">查看</a><span> | </span><a href="javascript:;" style="color: #FE0000;" onclick="delIndent({{ d.indentId }})">删除</a>
					{{#  } else { }}
						<a href="javascript:;" style="color: #1E9FFF;" onclick="modlays()">查看</a><span> | </span><a href="javascript:;" style="color: #FE0000;" onclick="modlas()">删除</a>
					{{#  } }}
				</script>
			</div>
		</div>
		<!-- 菜品信息模态窗体 -->
		<div id="modWineGre" style="display: none;padding: 6px 6px 0 6px;">
			<table class="layui-table"
				lay-data="{
							height: 582,
							id: 'findWineGre',							
							page: true,									
							limit: 5,//每页默认显示的数量
							limits: [5,10,20,30,50,60], 
							method: 'post', //提交方式
							done: function(res,curr,count){
								$('.layui-table-cell').css({'height':'auto'});
								},							
							}"
				lay-filter="findWineGre">
				<thead>
					<tr>
						<th
							lay-data="{field: 'wineGreId', align: 'center', sort: true, templet: '#Picture', style:'height:100px;'}">菜品图片</th>
						<th lay-data="{field: 'wineGreName', align: 'center', sort: true}">菜品名称</th>
						<th lay-data="{field: 'wineGrePrice', align: 'center', sort: true}">菜品价格</th>
						<th lay-data="{field: 'indentQuantity', align: 'center', sort: true}">菜品份数</th>
					</tr>
				</thead>
			</table>
			<script type="text/html" id="Picture">
  				{{#  if(d.wineGreId > 0){ }}
    				<img src='${ctx}/servlet/OrderListServlet?type=findPicture&wineGreId={{ d.wineGreId }}'/>					
  				{{#  } }}
			</script>
		</div>
		<!-- 订单详情模态窗体 -->
        <div id="modIndent" style="display: none;padding: 15px;">
        	<!--startprint-->
        	<form class="layui-form form1" id="formIndent"></form>
        	<form class="layui-form form1">
				<div class="layui-form-item">
					<div class="layui-col-lg10 layui-col-md10 layui-col-sm10">
						<span>外卖费</span>
					</div>
					<div class="layui-col-lg2 layui-col-md2 layui-col-sm2 text-right">
						<span>￥</span>0
					</div>
				</div>
				<div class="layui-form-item">
					<div class="layui-col-lg10 layui-col-md10 layui-col-sm10">
						<span>餐盒费</span>
					</div>
					<div class="layui-col-lg2 layui-col-md2 layui-col-sm2 text-right">
						<span>￥</span>0
					</div>
				</div>
				<div class="layui-form-item">
					<div class="layui-col-lg9 layui-col-md9 layui-col-sm9">
						<span>数量 : <span id="Count"></span>
						</span>
					</div>
					<div class="layui-col-lg3 layui-col-md-3 layui-col-sm3 text-right">
						<span>总价 : <span id="TotalPrices"></span>
						</span>
					</div>
				</div>
			</form>
			<form class="layui-form form2">
				<div class="layui-form-item">
					<div class="layui-col-lg2 layui-col-md2 layui-col-sm2">
						<span>送餐地址 ：</span>
					</div>
					<div class="layui-col-lg8 layui-col-md8 layui-col-sm8">
						<span id="SCDZ"></span>
					</div>
				</div>
				<div class="layui-form-item">
					<div class="layui-col-lg2 layui-col-md2 layui-col-sm2">
						<span>手机号码 ：</span>
					</div>
					<div class="layui-col-lg8 layui-col-md8 layui-col-sm8">
						<span id="SJHM"></span>
					</div>
				</div>
				<div class="layui-form-item">
					<div class="layui-col-lg2 layui-col-md2 layui-col-sm2">
						<span>支付方式 ：</span>
					</div>
					<div class="layui-col-lg8 layui-col-md8 layui-col-sm8">
						<span id="ZFFS"></span>
					</div>
				</div>
				<div class="layui-form-item">
					<div class="layui-col-lg2 layui-col-md2 layui-col-sm2">
						<span>订单编号 ：</span>
					</div>
					<div class="layui-col-lg8 layui-col-md8 layui-col-sm8">
						<span id="DDBM"></span>
					</div>
				</div>
				<div class="layui-form-item">
					<div class="layui-col-lg2 layui-col-md2 layui-col-sm2">
						<span>下单时间 ：</span>
					</div>
					<div class="layui-col-lg8 layui-col-md8 layui-col-sm8">
						<span id="XDSJ"></span>
					</div>
				</div>
			</form>
			<!--endprint-->
			<div class="row" style="text-align: center;">
				<div class="layui-btn-container">
					<button type="button" class="layui-btn layui-btn-primary"
						id="btnPrint">
						<i class="layui-icon layui-icon-template-1"> <span> 打 印</span> </i>
					</button>
					<button type="button" class="layui-btn layui-btn-danger" onclick="modHide();">
						<i class="layui-icon layui-icon-close"> <span> 取 消</span> </i>
					</button>
				</div>
			</div>
		</div>
		<!-- 配送信息模态窗体 -->
        <div id="modDeliveryStaff" style="display: none;padding: 15px 10px 0 10px;">
			<form class="layui-form">
				<div class="layui-form-item">
					<div class="layui-col-lg5 layui-col-md5 layui-col-sm5">
						<input type="hidden" id="Indenting" />
						<input type="hidden" id="State" />
						<label class="layui-form-label">状 态</label>
						<div class="layui-input-block">
							<select id="States">
								<option value="false">待分配</option>
								<option value="true">已分配</option>
							</select>
						</div>
					</div>
					<div class="layui-col-lg6 layui-col-md6 layui-col-sm6" style="padding-left: 5%;">
						<button type="button" class="layui-btn layui-btn-primary"
							onclick="searchStaff();">
							<i class="layui-icon layui-icon-search"> <span> 查 询</span> </i>
						</button>
					</div>
				</div>
			</form>
			<table class="layui-table"
				lay-data="{
							height: 468,
							id: 'findDeliveryStaff',								
							page: true,									
							limit: 6,//每页默认显示的数量
							limits: [6,10,20,30,50,60],
							method: 'post', //提交方式								
							}"
				lay-filter="findDeliveryStaff">
				<thead>
					<tr>
						<th
							lay-data="{field: 'deliveryStaffNum', align: 'center', sort: true}">编号</th>
						<th
							lay-data="{field: 'deliveryStaffName', align: 'center', sort: true}">名称</th>
						<th
							lay-data="{field: 'deliveryState', align: 'center', sort: true, templet: '#state'}">状态</th>
						<th
							lay-data="{field: 'deliveryState', align: 'center', sort: true, templet: '#btn'}">操作</th>
					</tr>
				</thead>
			</table>
			<script type="text/html" id="state">
  				{{#  if(d.deliveryState === true){ }}
    				<span style='color:#0aa3ad;'>已分配</span>
				{{#  } else { }}
					<span style='color:#0aa3ad;'>待分配</span>
  				{{#  } }}
			</script>
			<script type="text/html" id="btn">
  				{{#  if(d.deliveryState === false){ }}
    				<a href="javascript:;" style="color: #FF8000;" onclick="pS({{d.deliveryStaffID}})">处理</a>	
				{{#  } else { }}
					<a href="javascript:;" style="color:black;">已处理</a>			
  				{{#  } }}
			</script>
        </div>
        <!-- 订单评论模态窗体 -->
        <div id="modOrderEvaluate" style="display: none;padding: 15px;">
        	<form class="layui-form form1" id="formOrderEvaluate"></form>
        	<form class="layui-form form1">
				<div class="layui-form-item">
					<div class="layui-col-lg10 layui-col-md10 layui-col-sm10">
						<span>外卖费</span>
					</div>
					<div class="layui-col-lg2 layui-col-md2 layui-col-sm2 text-right">
						<span>￥</span>0
					</div>
				</div>
				<div class="layui-form-item">
					<div class="layui-col-lg10 layui-col-md10 layui-col-sm10">
						<span>餐盒费</span>
					</div>
					<div class="layui-col-lg2 layui-col-md2 layui-col-sm2 text-right">
						<span>￥</span>0
					</div>
				</div>
				<div class="layui-form-item">
					<div class="layui-col-lg9 layui-col-md9 layui-col-sm9">
						<span>数量 : <span id="PJCount"></span>
						</span>
					</div>
					<div class="layui-col-lg3 layui-col-md-3 layui-col-sm3 text-right">
						<span>总价 : <span id="PJTotalPrices"></span>
						</span>
					</div>
				</div>
			</form>
			<form class="layui-form form2">
				<div class="layui-form-item">
					<div class="layui-col-lg2 layui-col-md2 layui-col-sm2 text-right" style="padding-top: 5px;">
						<label>*总体评价</label>
					</div>
					<div class="layui-col-lg8 layui-col-md8 layui-col-sm8">
						<div id="Evaluate"></div>
					</div>
				</div>
				<div class="layui-form-item" style="margin-bottom:20px;">
					<div class="layui-col-lg2 layui-col-md2 layui-col-sm2 text-right"
						style="padding-top:3px;">
						<label>*送餐速度</label>
					</div>
					<div class="layui-col-lg7 layui-col-md7 layui-col-sm7" style="padding-left: 15px;padding-top: 5px;">
						<div id="slideEvaluate" class="demo-slider"></div>
						<span class="text-left"
							style="display:inline-block;font-size:10px;position:relative;right:5px;">0分钟</span>
						<span class="text-right"
							style="display:inline-block;font-size:10px;position:relative;left:246px;">120分钟</span>
					</div>
					<div class="layui-col-lg2 layui-col-md2 layui-col-sm2"
						style="padding-top:5px;">
						<div class="text" style="font-size:13px;" id="minute">0分钟</div>
					</div>
				</div>
				<div class="layui-form-item">
					<div class="layui-col-lg2 layui-col-md2 layui-col-sm2 text-right">
						<label>总体感觉</label>
					</div>
					<div class="layui-col-lg10 layui-col-md10 layui-col-sm10" style="padding-left: 15px;padding-right: 0px;">
						<textarea id="ZTGJ" class="layui-textarea" readonly="readonly"></textarea>
					</div>
				</div>
			</form>
		</div>
	</div>
	<script type="text/javascript">
		var table;
		var layer;
		var rate;
		var slider;
		layui.use(["element", "form", "table", "layer", "laydate", "rate", "slider"], function() {
			element = layui.element,
			form = layui.form,//表单
			table = layui.table,//表格
			layer = layui.layer,
			rate = layui.rate,
			slider = layui.slider;
			
			
		});
		//条件查询订单
		function searchIndent() {
            var parameter = $("#Parameter").val();
            if (parameter == undefined || parameter == "") {
                parameter = "";
            }
            table.reload("findIndent", {
	        	page: {
					curr: 1 //重新从第 1 页开始
				},
				where: {
					parameter: parameter
				}
	        });
        }
		//弹出菜品详情模态框
		function showDetail(indentId){
			showTable("findWineGre", "${ctx}/servlet/OrderListServlet?type=findWineGre&findType=selectWineGre&indentId=" + indentId,
				"layui-icon-list", "酒菜信息", "modWineGre");
		}
		//查看订单详情
       	var indentID;
       	function showIndentDetail(indentId) {
            $("#formIndent").empty();    
            $.ajaxSettings.ansyc = false;  
            appendWine(indentId, "formIndent", "Count", "TotalPrices", "订单详情", "modIndent");             
            $.getJSON("${ctx}/servlet/OrderListServlet", { type: "findIndentById", indentId: indentId }, function (data) {
                if (data != null) {
                    $("#SCDZ").empty().append(data.userAddress);
                    $("#SJHM").empty().append(data.contactNumber);
                    $("#ZFFS").empty().append(data.paymentMethod);
                    $("#DDBM").empty().append(data.indentNum);
                    $("#XDSJ").empty().append(data.indentTimes);
                }
            });
            indentID = indentId;
        }       
        //拼接菜品信息
       function appendWine(indentId, id, countId, sumId, title, modId){
       		var height = 0;
       		var count = 0;
            var sum = 0.00;
       		$.ajaxSettings.ansyc = false;
        	$.post("${ctx}/servlet/OrderListServlet", { type: "findWineGre", findType: "findWineGre", indentId: indentId }, function (data) {
               	$("#" + id).empty();
               	if (data != null) {
					$.each(data, function (i) {
	                    $("#" + id).append('<div class="layui-form-item">'
	                                + '<div class="layui-col-lg8 layui-col-md8 layui-col-sm8">'
	                                    + '<span>' + data[i].wineGreName + '</span>'
	                                + '</div>'
	                                + '<div class="layui-col-lg2 layui-col-md2 layui-col-sm2 text-right">'
	                                    + '<span>' + data[i].indentQuantity + '</span>份'
	                                + '</div>'
	                                + '<div class="layui-col-lg2 layui-col-md2 layui-col-sm2 text-right">'
	                                    + '￥<span>' + data[i].wineGrePrice + '</span>'
	                                + '</div>'
	                            + '</div>');
	                	count += Number(data[i].indentQuantity);
	            		sum += Number(data[i].indentQuantity * data[i].wineGrePrice);
	                });
	                height = data.length;
	                $("#" + countId).empty().append(count);
            		$("#" + sumId).empty().append(sum);
            		height = Number(height+2) * 45 + Number(250 + 60 + 43) + 'px';      
		            layer.open({
				    	type: 1,//类型
				      	title: '<i class="layui-icon layui-icon-list"> ' + title + '</i>',
				      	offset: ['5%','35%'],
				      	area:['560px', height],//定义宽和高
				      	shadeClose: true,//点击遮罩层关闭		      	
				      	content: $("#" + modId)//打开的内容		      	
					});
				}                
            }, "json");          
        }
        //打印订单
        $("#btnPrint").click(function () {
            bdhtml = window.document.body.innerHTML;
            sprnstr = "<!--startprint-->";
            eprnstr = "<!--endprint-->";
            prnhtml = bdhtml.substr(bdhtml.indexOf(sprnstr) + 17);
            prnhtml = prnhtml.substring(0, prnhtml.indexOf(eprnstr));
            window.document.body.innerHTML = prnhtml;
            window.print();
            var indentId = indentID;
            $.getJSON("${ctx}/servlet/OrderListServlet", { type: "updateIndent", indentId: indentId, 
            orderStatusId: 1 }, function (data) {
                if (data.state) {
                    location.reload();
                }
            });
        });
        //修改确认订单
       function modInd(indentID) {
            layer.confirm("请确认该订单菜品是否已制作完成?", {
                icon: 3,
                btn: ['确定', '取消']
            }, function (layerIndex) {
                layer.close(layerIndex);//关闭提示框
                $.ajaxSettings.async = false;
                $.getJSON("${ctx}/servlet/OrderListServlet", { type: "updateIndent", indentId: indentID, 
                orderStatusId: 3 }, function (data) {
                    if (data.state) {
                        layer.alert("等待配送员接单!", {
							icon : 1,
							title : '提示'
						},function(layerIndex){
							layer.close(layerIndex);
							location.reload();
						});
                    } else {
                        layer.msg(data.msg, { icon: 2, skin: "layui-layer-molv" });
                    }
                });
            });
        }
        //显示配送表格信息
       function showPS(indentId) {
            $("#Indenting").val(indentId);
            searchStaff();
            layer.open({
		    	type: 1,//类型
		      	title: '<i class="layui-icon layui-icon-list"> 配送员信息</i>',
		      	offset: ['5%','33%'],
		      	area:['680px','auto'],//定义宽和高
		      	shadeClose: true,//点击遮罩层关闭
		      	content: $("#modDeliveryStaff")//打开的内容
			});			
        }
        //条件查询
       function searchStaff() {
            var state = $("#States").val();
            if (state == undefined || state == "") {
                state = false;
            }
           	table.reload("findDeliveryStaff", {
           		url: "${ctx}/servlet/OrderListServlet?type=findDeliveryStaff",
	        	page: {
					curr: 1 //重新从第 1 页开始
				},
				where: {
					state: state
				}
	        });
        }
        //分配配送员信息
       function pS(deliveryStaffID) {
            var indentId = $("#Indenting").val();
            layer.confirm("请确认是否分配该配送员信息", {
                icon: 3,
                btn: ['确定', '取消']
            }, function (layerIndex) {
                layer.close(layerIndex);//关闭提示框
                $.ajaxSettings.async = false;
                $.getJSON("${ctx}/servlet/OrderListServlet", { type: "insertDelivery", 
                deliveryStaffID: deliveryStaffID, indentId: indentId, deliveryState: true,orderStatusId: 4 }, function (data) {
                    if (data.state) {
                    	modHide();
                        layer.alert("分配成功!", {
							icon : 1,
							title : '提示'
						},function(layerIndex){
							layer.close(layerIndex);
							location.reload();
						});                        
                    } else {
                        layer.alert(data.msg, { icon: 2, title : '提示' });
                    }
                });
            });
        }
        //提示该订单状态
       function modlay() {
            layer.msg("该订单待派送不能删除！", { icon: 0, skin: "layui-layer-molv" });
        }
        function modlas() {
            layer.msg("该订单已确认或派送中不能删除！", { icon: 0, skin: "layui-layer-molv" });
        }
        function modlays(){
        	layer.msg("订单派送中,等待用户确认！", { icon: 1, skin: "layui-layer-molv" });
        }
        function modYMC(){
        	layer.msg("订单已完成,等待用户评价！", { icon: 1, skin: "layui-layer-molv" });
        }
        //显示评价信息
       function ShowPJ(indentID) {
            $("#formOrderEvaluate").empty();
            $("#ZTPJ").empty();
            $("#ZTGJ").empty();
            appendWine(indentID, "formOrderEvaluate", "PJCount", "PJTotalPrices", "订单评价信息", "modOrderEvaluate");            
            $.getJSON("${ctx}/servlet/OrderListServlet", { type: "findRatingFormByID", indentId: indentID }, function (data) {
                if (data != null) {
                	//评价星星
					rate.render({
						elem: '#Evaluate',
						text: true,
						readonly: true,
						value: data.totalRating,
						text: true,
						setText: function(value){ //自定义文本的回调
					      	var arrs = {
					      		'0.5': '', 
					        	'1': '1分极差!',
					        	'1.5': '1分较差!',
					        	'2': '2分差!',
					        	'2.5': '2分差!',
					        	'3': '3分一般!',
					        	'3.5': '3分一般!',
					        	'4': '4分一般!',
					        	'4.5': '4.5分很好!',
					        	'5': '5分好极了!'
					    	};
					    	this.span.text(arrs[value] || ( value + "星"));
					    },
						half: true //开启半星
					});
					
					//送餐速度
					slider.render({
						elem: '#slideEvaluate',
						theme: '#1E9FFF',
						tips: false, //关闭默认提示层
						min: 0,
		    			max: 120,
					    change: function(value){
					      $('#minute').empty().html(value + '分钟');
					    },
						value: data.songCanSuDu //初始值
					});
                    var bf = Number(data.totalRating * 0.2) * 100 + "%";
                    $('#minute').empty().html(parseInt(data.songCanSuDu) + '分钟');
                    $("#ZTGJ").val(data.zongFeel);//label label-default
                }
            });
        }
        //删除订单
       function delIndent(indentId) {
            layer.confirm("请确认是否删除该订单信息?", {
                icon: 3,
                btn: ['确定', '取消']
            }, function (layerIndex) {
                layer.close(layerIndex);//关闭提示框
                $.ajaxSettings.async = false;
                $.getJSON("${ctx}/servlet/OrderListServlet", { type: "delIndent", 
                indentId: indentId }, function (data) {
                    if (data.state) {
                        searchIndent();
                    } else {
                        layer.msg(data.msg, { icon: 2, skin: "layui-layer-molv" });
                    }
                });
            });
        }
        //返回前台主页面
        $("#btnBack").click(function () {
            window.location.href = "${ctx}/jsp/FrontDesk.jsp";
        });
		/*--------------弹出模态框并加载表格---------------*/
		function showTable(tableId, Url, Class, title, modId){
			table.reload(tableId, {
				url: Url
			});
			layer.open({
		    	type: 1,//类型
		      	title: '<i class="layui-icon ' + Class + '"> ' + title + '</i>',
		      	offset: ['5%','33%'],
		      	area:['680px','auto'],//定义宽和高
		      	shadeClose: true,//点击遮罩层关闭
		      	content: $("#" + modId)//打开的内容
		    });
		}		
		//关闭模态框
		function modHide() {
    		$(".layui-layer-close").trigger("click");
    	}
	</script>
  </body>
</html>
