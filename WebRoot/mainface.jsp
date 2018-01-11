<%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
  <head>
    <title>首页</title>
    <meta name="content-type" content="text/html; charset=utf-8">
    
    <link rel="stylesheet" type="text/css" href="jeasyui1.5/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="jeasyui1.5/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="jeasyui1.5/demo/demo.css">
	<link rel="stylesheet" type="text/css" href="css/base.css">
	
	<!-- 次页面css -->
	<link rel="stylesheet" type="text/css" href="css/mainface.css">
	
	<script type="text/javascript" src="jeasyui1.5/jquery.min.js"></script>
	<script type="text/javascript" src="jeasyui1.5/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="jeasyui1.5/locale/easyui-lang-zh_CN.js"></script>
	
	<!-- 垂直下拉菜单特效 -->
	<script type="text/javascript" src="js/jquery.js"></script>
	
  </head>
  
  <body class="easyui-layout">
    <div id="north" data-options="region:'north',title:'',split:true" style="height:86px;">
   		<div id="title">库存管理系统</div>
    	<div id="btdiv">
	    	<a href="subjsp/person_info.jsp" target="iframe_a">
	    		<input id="bt1" type="button" value="个人信息"/>
	    	</a>&nbsp;
	    	<a href="servlet/LogOut">
	    		<input id="bt2" type="button" value="退出系统"/>
	    	</a>
    	</div>
    </div>
    <div id="south" data-options="region:'south',title:'',split:true" style="height:37px;">
    	<p>
    		操作员：<label>${user.getUname()}</label>&nbsp;&nbsp;
    		角色：<label>${user.getUtype()}</label>&nbsp;&nbsp;
    	              系统时间：<label id="clock"></label>
    	</p>
    </div>
    <div id="west" data-options="region:'west',title:'',split:true" style="width:276px;">
    	<div id="firstpane" class="menu_list">
			<h3 class="menu_head">基本设置</h3>
			<div style="display:none" class="menu_body">
				<a href="subjsp/staff_info.jsp" target="iframe_a">人员信息</a>
				<a href="subjsp/product_info.jsp" target="iframe_a">产品信息</a>
				<a href="subjsp/warehouse_info.jsp" target="iframe_a">仓库信息</a>
			</div>
			<h3 class="menu_head">业务操作</h3>
			<div style="display:none" class="menu_body">
				<a href="subjsp/in_stock.jsp" target="iframe_a">填写入库单</a>
				<a href="subjsp/out_stock.jsp" target="iframe_a">填写出库单</a>
				<div id="secondpane" class="menu_list" >
					<h3 class="menu_head" id="uq">审核单据</h3>
					<div style="display:none;" class="menu_body" id="sediv">
						<a href="subjsp/instock_review.jsp" target="iframe_a">入库单审核</a>
						<a href="subjsp/outstock_review.jsp" target="iframe_a">出库单审核</a>
					</div>
				</div>
				<a href="html/nopage.html" target="iframe_a">库存盘点</a>
			</div>
			<h3 class="menu_head">库存查询</h3>
			<div style="display:none" class="menu_body">
				<a href="subjsp/inventory_info.jsp" target="iframe_a">库存信息</a>
			</div>
		</div>
    </div>
    <div data-options="region:'center',title:''" style="background-color:#eee;">
    	<iframe name="iframe_a" src="<%=request.getContextPath()%>/html/hello.html" style="width:100%;height:100%;" frameborder="0"></iframe>
    </div>
    <script>
	$(document).ready(function(){
		/*下拉菜单事件*/
		$("#firstpane h3.menu_head").click(function(){
			$(this).addClass("current").next("div.menu_body").slideToggle(300).siblings("div.menu_body").slideUp("slow");
			$(this).siblings().removeClass("current");
		});
		/*下拉子菜单事件*/
		$("#secondpane h3.menu_head").mouseover(function(){
			$(this).addClass("current").next("div.menu_body").slideDown(500).siblings("div.menu_body").slideUp("slow");
			$(this).siblings().removeClass("current");
		});
		/*时间事件*/
		window.onload=function(){ 
			window.setInterval("realSysTime(clock)",1000);  
		} 
	});
	function realSysTime(clock){ 
		var now=new Date();  
		var year=now.getFullYear(); 
		var month=now.getMonth();  
		var date=now.getDate(); 
		var day=now.getDay();  
		var hour=now.getHours();  
		var minu=now.getMinutes();  
		var sec=now.getSeconds(); 
		month=month+1; 
		var arr_week=new Array("星期日","星期一","星期二","星期三","星期四","星期五","星期六"); 
		var week=arr_week[day];  
		var time=year+"年"+month+"月"+date+"日 "+week+" "+hour+":"+minu+":"+sec;  
		clock.innerHTML=time;  
	} 
	</script>
  </body>
</html>
