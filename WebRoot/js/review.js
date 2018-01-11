function def_datagrid(id,title,columns){
 	 $('#'+id).datagrid({
 		 	title:title,
 		 	singleSelect:true,
		    rownumbers: true,
	 		striped: true,
		    columns:columns
	 });
}
function load_datalist(id,state,url){
	   $.ajax({       
			url: url,
			data:{"optype":"findlist","state":state},
			async: false, method: 'post',    
			success: function(data) { 
				var source=eval("("+data+")");
				$("#"+id).datagrid("reload"); 
				$("#"+id).datagrid("loadData",source); 
				$("#"+id).datagrid('resize'); 
			} 
		});
}