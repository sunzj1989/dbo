<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
$(function() {
	$('#addForm').form({
		url : '${path }/items/addFirst',
		onSubmit : function() {
			progressLoad();
			var isValid = $(this).form('validate');
			if (!isValid) {
				progressClose();
			}
			return isValid;
		},
		 success : function(result) {
			progressClose();
			 result = $.parseJSON(result);
			if (result.success) {
				parent.$.modalDialog.openner_tree.tree('reload'); //之所以能在这里调用到parent.$.modalDialog.openner_dataGrid这个对象，是因为user.jsp页面预定义好了
				parent.$.modalDialog.handler.dialog('close');
			} else {
				parent.$.messager.alert('错误', result.msg, 'error');
			}
		} 
	});
});
</script>

<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title=""
		style="overflow: hidden; padding: 3px;">
		<form id="addForm" method="post">
			<table class="grid">
				<tr>
					<td>所属父病种</td>
					<td>${chapterSubItems.chapter_sub_item_name} </td>
				</tr>
			 	 <tr>
					<td>小病种名称</td>
					<td><input name="chapter_sub_item_name"  ></td>
					 <c:choose>
						<c:when test="${empty subId} ">
						     <td></td>
						</c:when>
						<c:otherwise>
								<td><input name="parent_id" type="hidden"  value="${parent_id}">
						</c:otherwise>
					</c:choose>
					<td><input name="chapter_id" type="hidden"  value="${chapter_id}">
				</tr>
				
			</table>
		</form>
	</div>
</div>