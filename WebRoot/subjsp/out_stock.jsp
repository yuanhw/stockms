<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
  <head>
    <title>出库操作</title>
    <meta name="content-type" content="text/html; charset=UTF-8">
    
    <!-- easyui样式和脚本 -->
    <link rel="stylesheet" type="text/css" href="../jeasyui1.5/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="../jeasyui1.5/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="../jeasyui1.5/demo/demo.css">
	<link rel="stylesheet" type="text/css" href="../css/base.css"> 
	
	<script type="text/javascript" src="../jeasyui1.5/jquery.min.js"></script>
	<script type="text/javascript" src="../jeasyui1.5/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="../jeasyui1.5/locale/easyui-lang-zh_CN.js"></script>
	
	<!-- 自定义函数  -->
	<script type="text/javascript" src="../js/in_out_stock.js"></script>
   
  </head>
  
  <body>
   <div style="padding:5px;border-bottom:1px solid #abc;">
   <a id="btn1" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'">增加产品</a>
   <a id="btn3" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove'">删除产品</a>
   	<a id="btn4" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">退出</a>
   	<a id="btn5" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'">保存</a>
   </div>
   <div id="instock" style="border:1px solid #abcdef;">
   		<div style="text-align:center;border:1px solid #abcdef;padding:3px;">
   			<span style="font-size:32px;">出库单</span>
   			<span style="margin-left:300px;">编号：
   			<input id="outid" class="easyui-textbox" style="width:100px;color:red;" data-options="disabled:true"/></span>
   		</div>
   		<div style="border:1px solid #abcdef;padding:3px;">
   			<span>出货仓库：<input id="ck" class="easyui-combobox"/></span>
   			<span style="margin-left:247px;">日期：<input id="date" class="easyui-datebox" data-options="width:120"/></span>
   		</div>
   		<div style="margin-top:3px;">
   			<table id="dg" class="easyui-datagrid" style="height:269px;"></table>
   		</div>
   		<div style="border:1px solid #abcdef;padding:3px;"><span>操作人：<input id="operator" class="easyui-textbox" data-options="disabled:true" value="${user.uname}"/></span></div>
 		<div hidden="hidden" id="uid" >${user.uid}</div>
   </div>
   <script>
   $(document).ready(function(){
	   //定义仓库
	    def_combobox("ck","wid","wname");
	   //加载仓库数据
	    var rs=getComboData("whdata");
	    $("#ck").combobox({data:rs});
	   //日期框默认当前日期
	    var date=new Date();
	    $("#date").datebox("setValue",date.toString());
	    //入库单编号
	    var outid=getId();
	    $("#outid").textbox('setValue',outid);
	    //加载产品信息
	    var productdata=getComboData("prodata");
	    //定义网格并且加载数据
	    var column=getDefColumns(productdata);
	    var id="dg";
	    fn_def_datagrid(id,column);
		/*按钮点击事件*/
		//增加产品按钮
		$("#btn1").bind('click',function(){
			fn_Add(id);
		});
		//删除按钮事件
		$("#btn3").bind('click',function(){
			fn_Remove(id);
		});
		//退出按钮事件
		$("#btn4").bind('click',function(){
			window.location.href='../html/hello.html';
		});
		//保存按钮事件
		$("#btn5").bind('click',function(){
		   var rows=$("#dg").datagrid('getChecked');
		   for(var i=0;i<rows.length;i++){
				$('#dg').datagrid('endEdit',i);
		   }
		   $("#dg").datagrid('checkAll');
		   var outid=$("#outid").textbox('getValue');
		   var whid=$("#ck").combobox('getValue');
		   var date=$("#date").datebox('getValue');
		   var operator=$("#uid").text();
		   var data={'outid':outid,'whid':whid,'date':date,'operator':operator,'rows':rows};
		   var url="../servlet/OutStockOPData";
		   if(whid!=null&&whid!=''){
			   fn_Save(id,data,url);
		   }else{
			   $.messager.alert('警告','仓库不能为空！');
		   }
		});
	});  
   function getDefColumns(productdata){
	   return columnArray1=[[
	              			{field:'id',title:'序号',width:20,checkbox:true,sortable:true,align:'center'},
	           				{field:'pid',title:'产品名称',width:100,align:'center',
	           					editor:{type:'combobox',options:{
	           						valueField:'pid',
	           						textField:'pname',
	           						panelHeight:'auto',
	           						data:productdata,
	           						required:true}
	           					}
	         				},
	           				{field:'unitprice',title:'单价',width:100,align:'center',
	         					editor:{
	              					type:'numberbox',
	              					options:{
	              						min:0,
	              					    precision:2,
	         	  						required:true
	              					}
	           					}
	           				},
	           				{field:'numbers',title:'数量',width:100,align:'center',
	           					editor:{
	              					type:'numberbox',
	              					options:{
	              						min:0,
	         	  						required:true
	              					}
	           					}
	           				}
	           		    ]];
   }
   </script>
  </body>
  
</html>
