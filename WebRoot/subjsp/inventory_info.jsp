<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
  <head>
    <title>库存查询</title>
    <meta name="content-type" content="text/html; charset=UTF-8">
    
    <!-- easyui样式和脚本 -->
    <link rel="stylesheet" type="text/css" href="../jeasyui1.5/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="../jeasyui1.5/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="../jeasyui1.5/demo/demo.css">
	<link rel="stylesheet" type="text/css" href="../css/base.css"> 
	
	<script type="text/javascript" src="../jeasyui1.5/jquery.min.js"></script>
	<script type="text/javascript" src="../jeasyui1.5/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="../jeasyui1.5/locale/easyui-lang-zh_CN.js"></script>
   
  </head>
  
  <body>
   <div id="main">
   	<div id="tools" style="padding:5px;border:1px solid #abcdef;">
   		<label>选择仓库：</label><input id="wh" class="easyui-combobox">
   		<label style="margin-left:23px;">选择产品：</label><input id="pro" class="easyui-combobox">
   		<a id="btn1" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="margin-left:37px;width:80px;">查询</a>
   		<a id="btn2" class="easyui-linkbutton" data-options="iconCls:'icon-print'" style="margin-left:10px;width:80px;">打印</a>
   	</div>
   	<div>
   		<div id="datalist" style="padding:5px;">
   			<table id="dg" class="easyui-datagrid" style="height:339px;"></table>
   		</div>
   	</div>
   </div>
   <script>
   $(document).ready(function(){
	   $('#wh').combobox({
			width:110,
			panelHeight:'auto',
		    valueField:'wid',
		    textField:'wname',
		    data:fn_load_wh_info()
		});
	   $('#pro').combobox({
			width:110,
			panelHeight:'auto',
		    valueField:'pid',
		    textField:'pname',
		    data:fn_load_pro_info()
		});
	   var dgcolumn=getDefColumnArray();
	   fn_def_datagrid(dgcolumn);
	   $("#btn1").bind('click',function(){
			var wid=$('#wh').combobox('getValue');
			var pid=$('#pro').combobox('getValue');
			fn_search_datalist(wid,pid);
		});
	   $("#btn2").bind('click',function(){
		   var data=$("#dg").datagrid("getData"); 
		   var str=JSON.stringify(data.rows);
		   post("../servlet/PrintExcelFile",{"data":str});
		});
   });
   //虚拟表单传递网格数据
   function post(URL, PARAMS) {
	   var temp = document.createElement("form");
	   temp.action = URL;
	   temp.method = "post";
	   temp.style.display = "none";
	   for (var x in PARAMS) {
	     var opt = document.createElement("textarea");
	     opt.name = x;
	     opt.value = PARAMS[x];
	     temp.appendChild(opt);
	   }
	   document.body.appendChild(temp);
	   temp.submit();
	   return temp;
	 }
   function fn_load_wh_info(){
	   var rs=null;
	   $.ajax({       
			url: "../servlet/LoadData",
			data:{"optype":"whdata"},
			async: false, method: 'post',    
			success: function(data) {
				rs=eval(data);
				rs.push({'wid':'000','wname':'全部','selected':true});
			} 
		});
	   return rs;
   }
   function fn_load_pro_info(){
	   var rs=null;
	   $.ajax({       
			url: "../servlet/LoadData",
			data:{"optype":"prodata"},
			async: false, method: 'post',    
			success: function(data) {
				rs=eval(data);
				rs.push({'pid':'000','pname':'全部','selected':true});
			} 
		});
	   return rs;
   }
   function fn_search_datalist(wid,pid){
	   $.ajax({       
			url: "../servlet/InventoryOPData",
			data:{"optype":"search","wid":wid,"pid":pid},
			async: false, method: 'post',    
			success: function(data) { 
				var source=eval("("+data+")");
				if(source.length==0){
					alert("no data!");
				}else{
					$("#dg").datagrid("reload"); 
					$("#dg").datagrid("loadData",source); 
					$("#dg").datagrid('resize'); 
				}
			} 
		});
   }
   function fn_def_datagrid(columnArray){
	  	 $('#dg').datagrid({
	  		 	title:"库存信息",
			    rownumbers: true,
		 		striped: true,
			    columns:columnArray
		 });
	}
  function getDefColumnArray(){
	   return columnArray=[[
				{field:'wname',title:'仓库名称',width:100,align:'center'},
 				{field:'pname',title:'产品名称',width:100,align:'center'},
 				{field:'units',title:'计量单位',width:100,align:'center'},
 				{field:'unitprice',title:'单价',width:100,align:'center'},
 				{field:'numbers',title:'数量',width:100,align:'center'}
 		    ]];
  }
   </script>
  </body>
  
</html>
