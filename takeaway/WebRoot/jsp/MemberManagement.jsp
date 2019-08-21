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
    
    <title>My JSP 'MemberManagement.jsp' starting page</title>
    
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
			<div class="layui-card-header">会员管理：</div>
			<div class="layui-card-body">
				<form class="layui-form" style="margin: 1% 0 2% 0;">
					<div class="layui-form-item">
						<div class="layui-col-lg3 layui-col-md3 layui-col-sm3">
							<label class="layui-form-label">卡 号:</label>
							<div class="layui-input-block">
								<input type="text" id="MarkClub" autocomplete="off"
									class="layui-input">
							</div>
						</div>
						<div class="layui-col-lg3 layui-col-md3 layui-col-sm3">
							<label class="layui-form-label">姓 名:</label>
							<div class="layui-input-block">
								<input type="text" id="MemberMC" autocomplete="off"
									class="layui-input">
							</div>
						</div>
						<div class="layui-col-lg6 layui-col-md6 layui-col-sm6" style="padding-left: 15px;">
							<div class="layui-btn-container">
								<button type="button" class="layui-btn" 
									onclick="search();">
									<i class="layui-icon layui-icon-search"> <span> 搜 索</span>
									</i>
								</button>
								<button type="button" class="layui-btn layui-btn-normal"
									id="btnReportTheLossOf">
									<i class="layui-icon layui-icon-survey"> <span>
											挂 失</span> </i>
								</button>
								<button type="button" class="layui-btn layui-btn-warm" id="btnBack">
									<i class="layui-icon layui-icon-return"> <span> 返回主页面</span>
									</i>
								</button>
							</div>
						</div>
					</div>
				</form>
				<table class="layui-table"
					lay-data="{
								height: 568,
								id: 'findMemberInfo',		
								url: '${ctx}/servlet/MemberServlet?type=findMemberInfo',
								page: true,									
								limit: 10,//每页默认显示的数量
								method: 'post', //提交方式								
								}"
					lay-filter="findMemberInfo">
					<thead>
						<tr>
							<th lay-data="{type: 'radio', hide: 'true'}"></th>
							<th
								lay-data="{field: 'memberTypeMC', align: 'center', sort: true}">会员类型</th>
							<th lay-data="{field: 'markClub', align: 'center', sort: true}">卡号</th>
							<th lay-data="{field: 'memberMC', align: 'center', sort: true}">姓名</th>
							<th
								lay-data="{field: 'papersTypeMC', align: 'center', sort: true}">证件类型</th>
							<th
								lay-data="{field: 'papersMarkMemb', align: 'center', sort: true}">证件号码</th>
							<th
								lay-data="{field: 'birthdayMember', align: 'center', sort: true}">会员生日</th>
							<th
								lay-data="{field: 'balanceClub', align: 'center', sort: true}">卡上余额</th>
							<th
								lay-data="{field: 'integralClub', align: 'center', sort: true}">卡上积分</th>
							<th
								lay-data="{field: 'phoneMember', align: 'center', sort: true}">联系电话</th>
							<th
								lay-data="{field: 'unitsMember', align: 'center', sort: true}">单位</th>
							<th lay-data="{field: 'siteMember', align: 'center', sort: true}">地址</th>
							<th
								lay-data="{field: 'delivertimeClubs', align: 'center', sort: true}">发卡时间</th>
						</tr>
					</thead>
				</table>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		var table;
		var layer;
		layui.use(["element", "form", "table", "layer", "laydate"], function() {
			element = layui.element,
			form = layui.form,//表单
			table = layui.table,//表格
			layer = layui.layer;			
			
			$("#btnReportTheLossOf").click(function() {
				var checkStatus=table.checkStatus('findMemberInfo'),
	  			data = checkStatus.data[0];
	  			if (checkStatus.data.length > 0) {
	  				layer.confirm("请确认是否挂失?", {
	  					icon : 3,
	  					btn: ['确认', '取消']
	  				}, function(index) {
	  					layer.close(index);
	  					$.getJSON("${ctx}/servlet/MemberServlet", { type: "updateState", 
	                    clubCardID: data.clubCardID }, function (data) {
	                        if (data.state) {
	                        	layer.alert(data.msg, {
									icon : 1,
									title : '提示'
								},function(layerIndex){
									layer.close(layerIndex);
									search();
								});                             
	                        } else {
	                        	layer.alert(data.msg, {
									icon : 0,
									title : '提示'
								});
	                        }
	                    });
	  				});
	  			} else {
	  				layer.msg("请点击一行数据");
	  			}
			});
		});
		/*--------------点击表格行勾选复选框或单选框----------------*/
		$(document).on("click", ".layui-table-body table.layui-table tbody tr td", function () { 
		 	if ($(this).attr("data-field") === "0") return;
		 	$(this).siblings().eq(0).find('i').click(); 
		});
		/*--------------------查询按钮-------------------*/
		function search(){
			var markClub = $("#MarkClub").val();//卡号
			var memberMC = $("#MemberMC").val();//姓名
			if (markClub == undefined) {
	            markClub = "";
	        }
	        if (memberMC == undefined) {
	            memberMC = "";
	        }
	        table.reload('findMemberInfo', {
	        	page: {
					curr: 1 //重新从第 1 页开始
				},
				where: {
					markClub: markClub,
					memberMC: memberMC
				}
	        });
		}
		//返回前台主页面
        $("#btnBack").click(function () {
            window.location.href = "${ctx}/jsp/FrontDesk.jsp";
        });
	</script>
</body>
</html>
