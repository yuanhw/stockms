<%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
  <head>
    <title>登录系统</title>
    <meta name="content-type" content="text/html; charset=UTF-8">
    
    <link rel="stylesheet" type="text/css" href="jeasyui1.5/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="jeasyui1.5/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="jeasyui1.5/demo/demo.css">
	
	<!-- 此页面css -->
	<link rel="stylesheet" type="text/css" href="css/index_ui.css">
	
	<script type="text/javascript" src="jeasyui1.5/jquery.min.js"></script>
	<script type="text/javascript" src="jeasyui1.5/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="jeasyui1.5/locale/easyui-lang-zh_CN.js"></script>
	
	<!-- 此页面js -->
	<script type="text/javascript" src="js/index_ui.js"></script>
  </head>
  <body>
    <h1>库存管理系统</h1>
    <p>系统管理员：admin &nbsp;&nbsp;密码：1</p>
    <div id="login" class="easyui-panel" title="login" style="width:400px;">
    	<p>账号：<input id="uid" class="easyui-textbox" /></p>
    	<p>密码：<input id="password" class="easyui-textbox" type="password"/></p>
    	<p>角色：<input id="utype" class="easyui-combobox"></p>
    	<p><input id="bt1" class="easyui-linkbutton" value="login"/>&nbsp;&nbsp;
    	   <input id="bt2" class="easyui-linkbutton" value="reset"/></p>
    </div>
  </body>
</html>
