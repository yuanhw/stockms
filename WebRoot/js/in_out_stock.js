function def_combobox(id,value,text){
	$("#"+id).combobox({
	width:120,
	panelHeight:'auto',
    valueField:value,
    textField:text,
    required:true
    });
}
function getComboData(optype){
	var rs=null;
	$.ajax({       
		url: "../servlet/LoadData",
		data:{"optype":optype},
		async: false, method: 'post',    
		success: function(data) {
			rs=eval(data);
		} 
	});
	return rs;
}
function getId(){
   var date=new Date();
   var str=date.getHours()+""+date.getMinutes()+""+date.getSeconds();
   return str;
}
function fn_def_datagrid(id,columns){
 	 $('#'+id).datagrid({
		    rownumbers: true,
	 		striped: true,
		    columns:columns
	 });
}
function fn_Add(id){
   $('#'+id).datagrid('appendRow',{});
   var editIndex = $('#'+id).datagrid('getRows').length-1;
   $('#'+id).datagrid('selectRow', editIndex)
			.datagrid('beginEdit', editIndex);
}
function fn_Remove(id){
   var sarray=$("#"+id).datagrid('getChecked');
   for(var i=0;i<sarray.length;i++){
		var temp=$("#"+id).datagrid('getRowIndex',sarray[i]);
		$('#'+id).datagrid('deleteRow',temp);
   }
}
function fn_Save(id,data,url){
   $.ajax({       
		url: url,
		data:{"optype":"insert","data":JSON.stringify(data)},
		async: false, method: 'post',    
		success: function(data) {
			if(data=='success'){
				alert("保存成功！");
				window.location.href='../html/hello.html';
				
			}else{
				$.messager.alert("提示","保存失败！");
			}
		} 
   });
}