create database stock;
use stock;
/*用户表*/
create table users(
uid nvarchar(15) primary key,
password nvarchar(15) not null,
uname nvarchar(25) not null,
gender char(1) not null,
birthday date,
utype char(15) not null,/*角色类型*/
phone char(11) 
);
/*产品表*/
create table products(
	pid nvarchar(6) primary key,
	pname nvarchar(25) not null,
	unitprice decimal(10,2) not null,
	units nvarchar(8) not null, /*计量单位*/
	ptype nvarchar(15) not null
);
/*仓库表*/
create table warehouses(
	wid nvarchar(6) primary key,
	wname nvarchar(15) not null
);
/*入库表*/
create table instock(
	inid nvarchar(6) primary key,/*入库单编号*/
	whid nvarchar(6) not null,/*仓库编号*/
	requestdate date not null,/*填制日期*/
	state nvarchar(15) not null, /*审核状态*/
	operator nvarchar(15) not null,
	constraint FK_instock_operator foreign key(operator) references users(uid),
	constraint FK_instock_whid foreign key(whid) references warehouses(wid)
);
/*入库详细表*/
create table instockitems(
	inid nvarchar(6) not null,
	pid nvarchar(6) not null,/*产品编号*/
	unitprice decimal(10,2) not null,
	numbers int not null,
	constraint FK_instockitems_inid foreign key(inid) references instock(inid),
	constraint FK_instockitems_pid foreign key(pid) references products(pid),
	primary key(inid,pid)
);
/*出库表*/
create table outstock(
	outid nvarchar(6) primary key,
	whid nvarchar(6) not null,
	requestdate date not null,
	state nvarchar(15) not null,
	operator nvarchar(15) not null,
	constraint FK_outstock_operator foreign key(operator) references users(uid),
	constraint FK_outstock_whid foreign key(whid) references warehouses(wid)
);
/*出库详细表*/
create table outstockitems(
	outid nvarchar(6) not null,
	pid nvarchar(6) not null,
	unitprice decimal(10,2) not null,
	numbers int not null,
	constraint FK_outstockitems_outid foreign key(outid) references outstock(outid),
	constraint FK_outstockitems_pid foreign key(pid) references products(pid),
	primary key(outid,pid)
);
/*存货表*/
create table inventories(
	wid nvarchar(6) not null,
	pid nvarchar(6) not null,
	unitprice decimal(10,2) not null, 
	numbers int not null,
	primary key(wid,pid,unitprice)
);
/*插入数据*/
insert into users values('admin','1','admin','M','2017-2-19','系统管理员','110110');


