<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'Main.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" href="${ctx}/Content/layui/css/layui.css" type="text/css" media="all"></link>
	<style type="text/css">
		.layui-header .layui-logo{
			font-size: 30px;
			font-family: FZShuTi;
		}
		.layui-nav-item .layui-icon {
		    position: relative;
		    top: 5%;
		    margin-top: -19px;
		    font-size: 18px;
		}
	</style>
  </head>
  
  <body class="layui-layout-body">
    <div class="layui-layout layui-layout-admin">
    	<div class="layui-header">
    		<div class="layui-logo">后 台 管 理</div>
			<ul class="layui-nav layui-layout-right">
				<li class="layui-nav-item"><a href="javascript:;">${user.staffName}</a>
					<dl class="layui-nav-child">
						<dd>
							<a href="javascript:;" id="BaseData">基本资料</a>
						</dd>
						<dd>
							<a href="javascript:;" id="UpdatePassword">修改密码</a>
						</dd>
					</dl></li>
				<li class="layui-nav-item">
					<a href="javascript:;" onclick="logOut();">退了</a>
				</li>
			</ul>
		</div>
		<div class="layui-side layui-bg-black">
			<div class="layui-side-scroll">
				<!-- 左侧导航区域（可配合layui已有的垂直导航） -->
				<ul class="layui-nav layui-nav-tree" lay-filter="test">
					<li class="layui-nav-item">
						<a class="" href="javascript:;" lay-tips="主页" >
							<i class="layui-icon layui-icon-list"></i>
							<cite>基础数据</cite>
							<span class="layui-nav-more"></span>
						</a>
						<dl class="layui-nav-child">
							<dd>
								<a href="javascript:;" id="SetTables">酒菜设置</a>
							</dd>
							<!-- <dd>
								<a href="javascript:;" id="SpecialManagement">酒菜特价管理</a>
							</dd>
							<dd>
								<a href="javascript:;" id="DiscountProgram">酒菜折扣方案</a>
							</dd>
							<dd>
								<a href="javascript:;" id="SetCombo">套餐设置</a>
							</dd> -->
							<dd> 
								<a href="javascript:;" id="SetMaterial">原材料设置</a>
							</dd>
							<!-- <dd>
								<a href="javascript:;" id="CuisineSetMaterial">菜品原材料配置</a>
							</dd>	
							<dd>
								<a href="javascript:;" id="SetRoomTable ">房台设置</a>
							</dd>
							<dd>
								<a href="javascript:;" id="SetBilling">计费设置</a>
							</dd> -->
							<dd> 
								<a href="javascript:;" id="EmployeeData">员工资料</a>
							</dd>
							<!-- <dd>
								<a href="javascript:;" id="SetDishes">菜品做法设置</a>
							</dd> -->						
						</dl>
					</li>
					<!-- <li class="layui-nav-item">
						<a href="javascript:;">
							<i class="layui-icon layui-icon-set"></i>
							<cite>销售中心</cite>
							<span class="layui-nav-more"></span>
						</a>
						<dl class="layui-nav-child">
							<dd>
								<a href="javascript:;" id="DealUnit">协议单位</a>
							</dd>
							<dd>
								<a href="javascript:;" id="SettleAccounts">挂账结算</a>
							</dd>
							<dd>
								<a href="javascript:;" id="MemberType">会员类型</a>
							</dd>
							<dd>
								<a href="javascript:;" id="MemberMag">会员管理</a>
							</dd>
							<dd>
								<a href="javascript:;" id="ClubCardPasUp">会员卡密码修改</a>
							</dd>
							<dd>
								<a href="javascript:;" id="ClubCardPasRes">会员卡密码重置</a>
							</dd>
							<dd>
								<a href="javascript:;" id="MemberRecScheme">会员充值方案</a>
							</dd>
							<dd>
								<a href="javascript:;" id="MemberReDotScheme">会员返点方案</a>
							</dd>
							<dd>
								<a href="javascript:;" id="MemberTranAcc">会员转账</a>
							</dd>
							<dd>
								<a href="javascript:;" id="SelectMemberTranAcc">会员转账查询</a>
							</dd>
							<dd>
								<a href="javascript:;" id="SelectMemberRecharge">会员充值查询</a>
							</dd>
							<dd>
								<a href="javascript:;" id="SelectMemberIntegral">会员积分查询</a>
							</dd>
						</dl>
					</li> -->
					<li class="layui-nav-item layui-nav-itemed">
						<a href="javascript:;">
							<i class="layui-icon layui-icon-menu-fill"></i>
							<cite>库存管理</cite>
							<span class="layui-nav-more"></span>
						</a>
						<dl class="layui-nav-child">
							<dd class="layui-this">
								<a href="javascript:;" id="ManOrder">单据录入</a>
							</dd>
							<dd>
								<a href="javascript:;" id="FindOrder">单据查询</a>
							</dd>
							<dd>
								<a href="javascript:;" id="FindRepertory">库存查询</a>
							</dd>
							<dd>
								<a href="javascript:;" id="BaseInfo">基础资料</a>
							</dd>
						</dl>
					</li>
					<!-- <li class="layui-nav-item">
						<a href="javascript:;">
							<i class="layui-icon layui-icon-table"></i>
							<cite>报表中心</cite>
							<span class="layui-nav-more"></span>
						</a>
						<dl class="layui-nav-child">
							<dd>
								<a href="javascript:;" id="CPZSQKB">菜品赠送情况表</a>
							</dd>
							<dd>
								<a href="javascript:;" id="CPTDQKB">菜品退单情况表</a>
							</dd>
							<dd>
								<a href="javascript:;" id="CPZDTJB">菜品赠单统计表</a>
							</dd>
							<dd>
								<a href="javascript:;" id="CPTDTJB">菜品退单统计表</a>
							</dd>
							<dd>
								<a href="javascript:;" id="JCXSMXB">酒菜销售明细表</a>
							</dd>
							<dd>
								<a href="javascript:;" id="JCXSHZB">酒菜销售汇总表</a>
							</dd>
						</dl>
					</li>	 -->						
				</ul>
			</div>
		</div>

		<div class="layui-body" style="height: 100%;">
			<!-- 内容主体区域 -->
			<iframe id="content" style="width: 100%;height: 100%;border: none;"></iframe>
		</div>
	</div>
	<script type="text/javascript" src="${ctx}/Content/js/jquery-2.0.3.min.js"></script>
    <script type="text/javascript" src="${ctx}/Content/layui/layui.js"></script>
    <script type="text/javascript">    	
    	layui.use(['element', 'layer'], function(){
		  var element = layui.element;//加载左边导航栏	 
		  var layer = layui.layer;//加载layer弹出框 
		}); 
		$(function(){
			$(".layui-nav .layui-nav-child a").attr("onclick","going(this);");
			$("#ManOrder").trigger("click");
		});
		function going(str){
        	$("#content").attr("src", "${ctx}/servlet/MainServlet?type=iFrame&content=" + str.id);
        }
        function logOut(){
        	layer.confirm("确定要退出吗?", {
               icon: 3,
               btn: ['确定', '取消']
            }, function (layerIndex) {
            	layer.close(layerIndex);//关闭提示框
            	window.location.href = "${ctx}/servlet/MainServlet?type=logOut&sort=next";                
            });
        }
    </script>
  </body>
</html>
