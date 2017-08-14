<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/commons/basejs.jsp"%>
<meta http-equiv="X-UA-Compatible" content="edge" />
<title>随访管理</title>
<script type="text/javascript">
	var dataGrid;
	$(function() {
		dataGrid = $('#dataGrid')
				.datagrid(
						{
							url : '${path }' + '/hbpm/getVisitPlan',
							striped : true,
							rownumbers : true,
							pagination : true,
							singleSelect : true,
							idField : 'hbpCustomerMainId',
							sortName : 'hbpCustomerMainId',
							sortOrder : 'asc',
							pageSize : 20,
							pageList : [ 10, 20, 30, 40, 50, 100, 200, 300,
									400, 500 ],
							frozenColumns : [ [
									{
										width : '80',
										title : 'id',
										field : 'hbpCustomerMainId',
										sortable : true
									},
									{
										width : '80',
										title : '随访名称',
										field : 'itemName'
									},
									{
										width : '80',
										title : '患者名字',
										field : 'customerName'
									},
									{
										width : '30',
										title : '性别',
										field : 'sex'
									},
									{
										width : '50',
										title : '医生',
										field : 'createUser'
										
									},
									{
										width : '100',
										title : '计划时间',
										field : 'planTime'
										
									},
									{
										width : '200',
										title : '地址',
										field : 'address'
										
									},
									{
										width : '100',
										title : '联系电话',
										field : 'telphone'
										
									},
									{
										width : '80',
										title : '签约状态',
										field : 'contractStatus'
									}] ]
							/* ,
							onLoadSuccess : function(data) {
								$('.role-easyui-linkbutton-edit').linkbutton({
									text : '编辑',
									plain : true,
									iconCls : 'icon-edit'
								});
								$('.role-easyui-linkbutton-del').linkbutton({
									text : '删除',
									plain : true,
									iconCls : 'icon-del'
								});
							},
							toolbar : '#toolbar' */
						});
	});


</script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',fit:true,border:false">
		<table id="dataGrid" data-options="fit:true,border:false"></table>
	</div>
	<div id="toolbar" style="display: none;">
		<shiro:hasPermission name="/hospital/add">
			<a onclick="addFun();" href="javascript:void(0);"
				class="easyui-linkbutton"
				data-options="plain:true,iconCls:'icon-add'">添加</a>
		</shiro:hasPermission>
	</div>
</body>
</html>