<%@ page language="java"  pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
  <head>
    <title>入库单审核操作</title>
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
	<script type="text/javascript" src="../js/review.js"></script>
   
  </head>
  
  <body>
   <div id="main">
   	<div id="tools" style="padding:5px;border:1px solid #abcdef;">
   		<label>选择入库单状态：</label><input id="type" class="easyui-combobox">
   		<a id="btn1" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'" style="margin-left:69px;">审核单据</a>
   		<a id="btn2" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove'">删除单据</a>
   	</div>
   	<div>
   		<div id="datalist" style="padding:5px;">
   			<table id="dg" class="easyui-datagrid" style="height:229px;"></table>
   		</div>
   		<div id="dataitem" style="padding:5px;">
   			<table id="dg2" class="easyui-datagrid" style="height:219px;"></table>
   		</div>
   	</div>
   </div>
   <script>
   $(document).ready(function(){
	   index=-1;//记录所选行索引
	   $('#type').combobox({
			width:173,
			panelHeight:'auto',
		    data:[{'id':'001','text':'待审核列表','selected':true},{'id':'002','text':'已审核列表'}],
		    valueField:'id',
		    textField:'text',
		    onSelect:function(record){
		    	switch(record.id){
			    	case '001': fn_op("未审核");break;
			    	case '002': fn_op("已审核");break;
		    	}
		    }
		});
	   var id="dg";
	   var columns1=getDefColumns1();
	   var title="待审核入库单列表";
	   def_datagrid(id,title,columns1);
	   $('#dg').datagrid({
		   onCheck:function(rowIndex,rowData){
			index=rowIndex;
	    	var source=rowData.items;
	    	$("#dg2").datagrid("reload"); 
			$("#dg2").datagrid("loadData",source); 
			$("#dg2").datagrid('resize'); 
		   }
	   });
	   var columns2=getDefColumns2();
	   def_datagrid("dg2","入库产品信息",columns2);
	   load_datalist(id,"未审核","../servlet/InStockOPData");
	   //审核单据
	    $("#btn1").bind('click',function(){
	    	var type=$("#type").combobox('getValue');
	    	if(type=='001'){
	    		fn_sh();
	    	}else{
	    		alert("已经审核过了！");
	    	}
		});
		//删除
		$("#btn2").bind('click',function(){
			 fn_del();
		});
   });
   function fn_op(state){
	   load_datalist("dg",state,"../servlet/InStockOPData");
	   $("#dg2").datagrid("loadData",[]);
   }
   function fn_sh(){
	   var rs=$("#dg").datagrid('getSelected');
	   $.ajax({       
			url: "../servlet/InStockReview",
			data:{"optype":"sh","inid":rs.inid},
			async: false, method: 'post',    
			success: function(data) {
				alert(data);
				if(data=='success'){
					$("#dg").datagrid('deleteRow',index);
					$("#dg2").datagrid("loadData",[]);
				}
			} 
		});
   }
   function fn_del(){
	   var rs=$("#dg").datagrid('getSelected');
	   $.ajax({       
			url: "../servlet/InStockReview",
			data:{"optype":"delete","inid":rs.inid},
			async: false, method: 'post',    
			success: function(data) {
				alert(data);
				if(data=='success'){
					$("#dg").datagrid('deleteRow',index);
					$("#dg2").datagrid("loadData",[]);
				}
			} 
		});
   }
  function getDefColumns1(){
   return columnArray1=[[
    			{field:'id',title:'序号',width:20,checkbox:true,sortable:true,align:'center'},
 				{field:'inid',title:'入库单编号',width:100,align:'center'},
 				{field:'whid',title:'仓库编号',width:100,align:'center',
 					editor:{type:'combobox',options:{
 						valueField:'wid',
 						textField:'wname',
 						panelHeight:'auto',
 						required:true}
 					}
 				},
 				{field:'requestdate',title:'填制日期',width:100,align:'center'},
 				{field:'operator',title:'操作员',width:100,align:'center'},
 				{field:'state',title:'状态',width:100,align:'center'}
 		    ]];
  }
  function getDefColumns2(){
	   return columnArray1=[[
 				{field:'pid',title:'产品编号',width:100,align:'center',
 					editor:{type:'combobox',options:{
 						valueField:'pid',
 						textField:'pname',
 						panelHeight:'auto',
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
