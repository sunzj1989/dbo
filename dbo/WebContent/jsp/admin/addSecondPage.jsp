<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
$(function() {
	$('#addSecondForm').form({
		url : '${path }/items/addSecond',
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
		<form id="addSecondForm" method="post">
			<table class="grid">
				<tr>
					<td>所属小病种：</td>
					<td>
						 <c:choose>
							<c:when test="${empty chapterSubItems }">
								无
							</c:when>
							<c:otherwise>
								${chapterSubItems.chapter_sub_item_name }
							</c:otherwise>
						</c:choose>
						
					</td>
				</tr>
			 	 <tr>
					<td>文本块名称</td>
					<td><input name="items_name"  ></td>
					<td><input name="chapter_id" type="hidden"  value="${chapterId}">
					 <c:choose>
							<c:when test="${empty chapterSubItems }">
								<td><input name="chapter_sub_item_id" type="hidden"  value="0">
							</c:when>
							<c:otherwise>
								<td><input name="chapter_sub_item_id" type="hidden"  value="${chapterSubItems.chapter_sub_item_id }">
							</c:otherwise>
						</c:choose>
				</tr>
			</table>
		</form>
	</div>
</div>