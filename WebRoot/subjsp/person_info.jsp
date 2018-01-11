<%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>个人信息</title>
<style>
	td{
		border:1px solid #00f;
	}
	tr{
		border:1px solid #0f0;
	}
	table{
		width:350px;
		border:1px solid #abc;
	}
	.td1{
		color:red;
	}
</style>
</head>
<body>
	<table>
		<tr>
			<td class="td1">账号：</td>
			<td>${user.uid}</td>
		</tr>
		<tr>
			<td class="td1">密码：</td>
			<td>${user.password}</td>
		</tr>
		<tr>
			<td class="td1">姓名：</td>
			<td>${user.uname}</td>
		</tr>
		<tr>
			<td class="td1">性别：</td>
			<td>${user.gender}</td>
		</tr>
		<tr>
			<td class="td1">生日：</td>
			<td>${user.birthdate}</td>
		</tr>
		<tr>
			<td class="td1">角色：</td>
			<td>${user.utype}</td>
		</tr>
		<tr>
			<td class="td1">电话：</td>
			<td>${user.phone}</td>
		</tr>
	</table>
</body>
</html>