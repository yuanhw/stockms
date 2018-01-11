$(document).ready(function(){
		$('#uid').textbox({
			width:173,
			prompt:'UserName',
			iconCls:'icon-man',
			iconWidth:38
		});
		$('#password').textbox({
			width:173,
			prompt:'PassWord',
			iconCls:'icon-lock',
			iconWidth:38
		});
		$('#utype').combobox({
			width:173,
			panelHeight:'auto',
		    url:'json/user_type.json',
		    valueField:'text',
		    textField:'text'
		});
		$("#bt1").bind('click',function(){
			var uid=$("#uid").textbox('getValue').trim();
			var password=$("#password").textbox('getValue').trim();
			var utype=$("#utype").combobox('getValue').trim();
			if(uid==''||password==''){
				$.messager.alert('警告','账号或密码不能为空！');
			}else{
				$.ajax({
					url: "servlet/LogIn",
					data: { "uid": uid, "password": password, "utype":utype}, 
					async: false, 
					method: 'post',    
					success: function(data) {
						if(data=='ok'){
							window.location.href='mainface.jsp';
						}else{
							$.messager.alert('警告',data);
						}
					}   
				});
			}
		});
		$("#bt2").bind('click',function(){
			$("#uid,#password").textbox('setValue','');
			$("#utype").combobox('setValue','');
		});
	});