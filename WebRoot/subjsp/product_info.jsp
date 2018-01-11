<%@ page language="java"  pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
  <head>
    <title>产品信息</title>
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
	<script type="text/javascript" src="../js/fn_info_dg.js"></script>
   
  </head>
  
  <body>
   <div style="padding:5px;border-bottom:1px solid #abc;">
   	<a id="btn1" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'">增加</a>
   	<a id="btn2" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">修改</a>
   	<a id="btn3" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove'">删除</a>
   	<a id="btn4" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">取消</a>
   	<a id="btn5" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'">保存</a>
   	<a id="btn6" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-reload'">刷新</a>
   </div>
   <table id="dg" class="easyui-datagrid"></table>
   <script>
   $(document).ready(function(){
	    /*得到列数组数据*/
	    var columnArray0=getDefColumnArray(true);
	    var columnArray1=getDefColumnArray(false);
	    var id="dg";
	    var title="&nbsp;产品信息";
	    var url="../servlet/ProductOPGridData";
	    /*定义带有分页的网格*/
	   	fnDefDataGridWithPagination(id,title,columnArray0,url);
		/*初始化表格数据*/
		fnLoadGridData(id,1,10,url);
		/*按钮点击事件*/
		//增加按钮事件
		$("#btn1").bind('click',function(){
			fn_Add(id,columnArray1);
		});
		//修改按钮事件
		$("#btn2").bind('click',function(){
			fn_Update(id);
		});
		//删除按钮事件
		$("#btn3").bind('click',function(){
			fn_Delete(id,title,columnArray0,url);
		});
		//取消按钮事件
		$("#btn4").bind('click',function(){
			fn_Cancel(id,title,columnArray0,url);
		});
		//保存按钮事件
		$("#btn5").bind('click',function(){
			fn_Save(id,title,columnArray0,url);
		});
		//刷新按钮事件
		$("#btn6").bind('click',function(){
			fn_refresh(id,title,columnArray0,url);
		});
	});
   function getDefColumnArray(temp){
	   return columnArray1=[[
     			{field:'id',title:'序号',width:20,checkbox:true,sortable:true,align:'center'},
  				{field:'pid',title:'产品编号',width:100,align:'center',
     				editor:{
     					type:'textbox',
     					options:{
     						disabled:temp,
	  						required:true
     					}
  					}
  				},
  				{field:'pname',title:'产品名称',width:100,align:'center',
  					editor:{
     					type:'textbox',
     					options:{
	  						required:true
     					}
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
  				{field:'units',title:'计量单位',width:100,align:'center',
  					editor:{
     					type:'textbox',
     					options:{
	  						required:true
     					}
  					}
  				},
  				{field:'ptype',title:'类型',width:100,align:'center',
  					editor:{type:'combobox',options:{
  						valueField:'text',
  						textField:'text',
  						method:'get',
  						panelHeight:'auto',
  						url:'../json/product_type.json',
  						required:true}
  					}
  				}
  		    ]];
   }
   </script>
  </body>
  
</html>
