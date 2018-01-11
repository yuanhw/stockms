function fnDefDataGridWithPagination(id,title,columnArray,url){
   $('#'+id).datagrid({
	   	title: title,
	    pagePosition: 'bottom',
	    rownumbers: true,
		pagination: true,
		pageSize: 10,
		pageNumber:1,
 		striped: true,
	    columns:columnArray
    });
	var pg = $("#"+id).datagrid("getPager");  
	(pg).pagination({
		showPageList: false,  
		beforePageText: '第',  
		afterPageText: '页    共 {pages} 页', 
		displayMsg: '当前显示{from}～{to}行，共{total}行', 
		onSelectPage:function(pageNumber,pageSize){  
			var opts = $('#'+id).datagrid('options');
			opts.pageNumber=pageNumber;
			opts.pageSize=pageSize;			
			fnLoadGridData(id,pageNumber,pageSize,url);
		}  
	}); 
}
function fnLoadGridData(id,pageNumber,pageSize,requrl){
   var opts =$("#"+id).datagrid('getPager').data("pagination").options; 
   opts.pageNumber=pageNumber;
   opts.pageSize=pageSize;
   $.ajax({       
		url: requrl,
		data:{"start":(pageNumber-1)*pageSize,"pagesize":pageSize,"optype":'select'},
		async: false, method: 'post',    
		success: function(data) { 
			if(data!=null&&data!=''){
				var source=eval("("+data+")");
				var rs=source.source;
				total=source.total;
				$("#"+id).datagrid("reload"); 
				$("#"+id).datagrid("loadData",rs); 
				$("#"+id).datagrid('resize'); 
				var pg = $("#"+id).datagrid("getPager"); 
				(pg).pagination('refresh',{
					total: source.total,
					pageNumber: pageNumber
				});
			}else{
				$("#"+id).datagrid("reload"); 
				$("#"+id).datagrid("loadData",[]); 
				$("#"+id).datagrid('resize'); 
				$.messager.alert('警告',"操作执行完毕! 状态：<span style='color:red;'>data is empty !</span>");
			}
		} 
	});
}
function fnAddDataGrid(id,columnArray){
   	$('#'+id).datagrid({
		columns:columnArray
	});
   	var pg = $("#"+id).datagrid("getPager"); 
   	(pg).pagination({
		showPageList: false,  
		layout:[],
		displayMsg: '当前显示{from}～{to}行，共{total}行'
	}); 
   	$("#"+id).datagrid("reload"); 
	$("#"+id).datagrid("loadData",[]); 
	$("#"+id).datagrid('resize');
}
function fnOperate(data,optype,requrl){
   $.ajax({       
		url: requrl,
		data:{"data":JSON.stringify(data),"optype":optype},
		async: false, method: 'post',    
		success: function(data) { 
			$.messager.alert('警告',"操作执行完毕! 状态：<span style='color:red;'>"+data+"</span>");
		} 
	});
}
//
updateIndexArray=new Array();//更新行索引数组
updateindexnum=0;//更新行数
addTag=0;//增加标志
function fn_Save_Add(id,title,columnArray,url){
	   $("#"+id).datagrid('checkAll');
	   var rows=$("#"+id).datagrid('getChecked');
	   for(var i=0;i<rows.length;i++){
			$('#'+id).datagrid('endEdit',i);
		}
	   fnOperate(rows,'insert',url);
	   fnDefDataGridWithPagination(id,title,columnArray,url);
	   fnLoadGridData(id,1,10,url);
}
function fn_Save_Update(id,url){
	   for(var i=0;i<updateindexnum;i++){
			$('#'+id).datagrid('endEdit',updateIndexArray[i]);
		}
	   var rows=$("#"+id).datagrid('getChanges');
	   $('#'+id).datagrid('acceptChanges');
	   for(var i=0;i<updateindexnum;i++){
			$('#'+id).datagrid('checkRow',updateIndexArray[i]);
	   }
	   fnOperate(rows,'update',url);
}

function fn_Add(id,columnArray){
	   if(addTag==0){
		   fnAddDataGrid(id,columnArray);
	   }
	   $('#'+id).datagrid('appendRow',{});
	   var editIndex = $('#'+id).datagrid('getRows').length-1;
	   $('#'+id).datagrid('selectRow', editIndex)
				.datagrid('beginEdit', editIndex);
	   addTag++;
}
function fn_Update(id){
	   if(addTag==0){
			var rows=$("#"+id).datagrid('getChecked');
			updateindexnum=rows.length;
			if(updateindexnum==0){
				$.messager.alert('警告',"请勾选将要修改的复选框！");
			}else{
				for(var i=0;i<rows.length;i++){
					updateIndexArray[i]=$("#"+id).datagrid('getRowIndex',rows[i]);
					$('#'+id).datagrid('beginEdit',updateIndexArray[i]);
				}
			}
		}
}
function fn_Delete(id,title,columns,url){
	    var sarray=$("#"+id).datagrid('getChecked');
		if(sarray.length!=0){
			if(addTag==0){
				var str='确定删除'+sarray.length+'条记录？';
				$.messager.confirm('警号',str,function(r){
				    if (r){
				    	fnOperate(sarray,'delete',url);
				    	fnLoadGridData(id,1,10,url);
				    }
				});
			}else{
				for(var i=0;i<sarray.length;i++){
					var temp=$("#"+id).datagrid('getRowIndex',sarray[i]);
					$('#'+id).datagrid('deleteRow',temp);
					addTag--;
				}
				if(addTag==0){
					fnDefDataGridWithPagination(id,title,columns,url);
					fnLoadGridData(id,1,10,url);
				}
			}
		}else{
			$.messager.alert('警告',"请勾选将要删除的复选框！");
		}
}
function fn_Cancel(id,title,columns,url){
	   if(updateindexnum!=0){
			for(var i=0;i<updateindexnum;i++){
				$('#'+id).datagrid('endEdit',updateIndexArray[i]);
			}
			$('#'+id).datagrid('rejectChanges');
			updateindexnum=0;
		}
		if(addTag!=0){
			fnDefDataGridWithPagination(id,title,columns,url);
			fnLoadGridData(id,1,10,url);
			addTag=0;
		}
}
function fn_Save(id,title,columnArray,url){
	   if(updateindexnum!=0){
			fn_Save_Update(id,url);
			updateindexnum=0;
		}
		if(addTag!=0){
			fn_Save_Add(id,title,columnArray,url);
		    addTag=0;
		}
}
function fn_refresh(id,title,columns,url){
	   if(addTag!=0){
		   fnDefDataGridWithPagination(id,title,columns,url);
		   addTag=0;
		}
	   fnLoadGridData(id,1,10,url);
}
//