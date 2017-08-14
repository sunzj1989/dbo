<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/commons/basejs.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="../scripts/jquery-form.js"></script>
<title>章节目录</title>
<script type="text/javascript">
    var index_layout;
    var index_tabs;
    var index_tabsMenu;
    var layout_west_tree;
   /*  全局变量，保存所选中章节id */
	var chapterId;
    /* 全局变量，保存所选小病种id */
    var subId;
   /* 全局变量，保存所选中文本块id */
   var itemId;
   /* 全局变量，保存当前操作的表名 */
   var tName;
    $(function() {
    	
        index_layout = $('#index_layout').layout({
            fit : true
        });

        index_tabs = $('#index_tabs').tabs({
            fit : true,
            border : false,
            tools : [{
                iconCls : 'icon-home',
                handler : function() {
                    index_tabs.tabs('select', 0);
                }
            }, {
                iconCls : 'icon-refresh',
                handler : function() {
                    var index = index_tabs.tabs('getTabIndex', index_tabs.tabs('getSelected'));
                    index_tabs.tabs('getTab', index).panel('open').panel('refresh');
                }
            }, {
                iconCls : 'icon-del',
                handler : function() {
                    var index = index_tabs.tabs('getTabIndex', index_tabs.tabs('getSelected'));
                    var tab = index_tabs.tabs('getTab', index);
                    if (tab.panel('options').closable) {
                        index_tabs.tabs('close', index);
                    }
                }
            } ]
        });


 		layout_west_tree = $('#layout_west_tree').tree({
 			/* 章节树 */
            url : '${path }/chapters/tree?book_id='+$('#book_id').val(),
            parentField : 'pid',
            lines : true,
            onClick : function(node) {
            	if($('#layout_west_tree').tree('isLeaf',node.target)){
	            	$("#center").show();
	            	$("#right").hide();
	            	$("#img").hide();
	            	$("#photo_show").hide();
	            	chapterId=node.id;
	            	 /* var father = $(this).tree("getParent",node.target); */
	            	 /* 标题树 */
	            	layout_west_tree = $('#center_tree').tree({
	                    url : '${path }/items/tree?chapterId='+node.id,
	                    /* checkbox: true, */
	                    parentField : 'pid',
	                    lines : true,
	                    onClick : function(node) {
	                    	itemId=node.id;
	                    	$("#img").hide();
	    		             /*若节点为文本标题， 异步文本块刷新内容  */
	                    //	if($('#center_tree').tree('isLeaf',node.target)){
	                   		 $.ajax({
	                 			url:'${path }/items/find',
	                 			data:{items_id:node.id,items_name:node.text},
	                 			type:"POST",
	                 			dataType:"json",
	                 			async:true,
	                 			cache:false,
	                 			success:function(result) {
	                 				if(result.success==true){
	                 					
	    	                    		$("#right").show();
	    	                    		$("#content").show();
	    	                    		
	    	                    		$("#photo_show").hide();
	    	                    		$("#right").css('overflow','hidden');
	    		                    	$.ajax({
	    		                			url:'${path }/content/show',
	    		                			data:{item_id:node.id,book_id:$('#book_id').val()},
	    		                			type:"POST",
	    		                			dataType:"json",
	    		                			async:true,
	    		                			cache:false,
	    		                			success:function(result) {
	    		                				if (result.success) {
	    		                					/* 查询出的内容赋值到textarea 并保存表名 */
	    		                					tName=result.str;
	    		                					if(result.obj!=null){
	    		                						$('#content_show').val(result.obj.detail_content);
	    		                					}else{
	    		                						$('#content_show').val("");
	    		                					}
	    		                				}
	    		                			}
	    		                		});
	                 					
	                 				}else{
	    	        	            	/* 若不是文本标题，则隐藏div */
	    	        	            	$("#right").show();
	    	        	            	$("#content").hide();
	    	        	            	 buttonShow();
	    	        	            	$("#photo_show").hide();
	    	        	            	subId=node.id;
	    	        	            	$.ajax({
	    		                			url:'${path }/imgs/find?book_id='+$('#book_id').val(),
	    		                			data:{subId:node.id},
	    		                			type:"POST",
	    		                			dataType:"json",
	    		                			async:true,
	    		                			cache:false,
	    		                			success:function(result) {
	    		                				tName=result.str;
	    		                				if (result.success) {
	    				        	            	$("#right").css('overflow','');
	    				        	            	/* var str="<img style=\"width:280px;height:210px;margin-top:0px;\" src=\"${path }/imgs/downLoad?image_id="+$('#image_id').val()+"&t_name="+$('#t_name').val()+"\" />"; */
	    			                			    $("#photo_show").load("../jsp/admin/show.jsp #photo_show");
	    				        	            	
	    			                			    $("#photo_show").show();
	    		                				}
	    		                			}
	    		                		});
	    	        	            }  
	                 			}
	                 		}); 		                    	
	                   	
	                    }
	            	});
	            }else{
	            	/* 若点击的为根节点，则隐藏div */
	            	$("#center").hide();
	            }  
            }
 		}); 
 		/* 控制右侧文本框显示 */
 		$("#center").hide();
 		$("#content_add").hide();
 		$("#right").hide();
 		//控制是否有添加和修改的权限
 		$('#annu').hide();
        buttonShow();
    });
    function buttonShow(){
    	 if($('#user').val()=="PIS"){
     		$('#annu').show();
     		$('#img').show();
     	} 
    }
    /* 添加小病种 */
    function addFirstFun() {
    	var node = $('#center_tree').tree('getSelected');
    	 if(node!=null){
    		 $.ajax({
     			url:'${path }/items/findFirst',
     			data:{subId:node.id,subName:node.text },
     			type:"POST",
     			dataType:"json",
     			async:true,
     			cache:false,
     			success:function(result) {
     				if(result.success==true){
     					  parent.$.modalDialog({
     				            title : '添加',
     				            width : 500,
     				            height : 150,
     				            href : '${path }/items/addFirstPage?subId='+node.id+'&chapter_id='+chapterId,
     				            buttons : [ {
     				                text : '添加',
     				                handler : function() {
     				                     parent.$.modalDialog.openner_tree = layout_west_tree;  //因为添加成功之后，需要刷新这个dataGrid，所以先预定义好 */ */
     				                    var f = parent.$.modalDialog.handler.find('#addForm');
     				                    f.submit();
     				                }
     				            } ]
     				        });
     				}
     			}
     		});
    		
    	 }
      
    } 
	/* 删除小病种 */
    function deleteFirstFun() {    
    	var node = $('#center_tree').tree('getSelected');
    	if(node){
    		 $.ajax({
      			url:'${path }/items/find',
      			data:{items_id:node.id,items_name:node.text},
      			type:"POST",
      			dataType:"json",
      			async:true,
      			cache:false,
      			success:function(result) {
      				if(result.success!=true){
      					parent.$.messager.confirm('询问', '您是否要删除当前小病种？', function(b) {
      			        	if (b) {
      			                progressLoad();
      			                $.post('${path}/items/deleteFirst', {
      			                	id : node.id,
      			                	book_id:$('#book_id').val()
      			                }, function(result) {
      			                    if (result.success) {
      			                        parent.$.messager.alert('提示', result.msg, 'info');
      			                        layout_west_tree.tree('reload');
      			                    }
      			                    progressClose();
      			                }, 'JSON');
      			            }
      			        
      			        }); 
      				}
      			}
      		});
   	  }
    }
	/*添加文本块标题  */
    function addSecondFun() {
    	 var checkId = 0;
    	 var node = $('#center_tree').tree('getSelected'); 
    	 if(node!=null){
    		 checkId=node.id; 
    		 $.ajax({
     			url:'${path }/items/find',
     			data:{items_id:node.id,items_name:node.text},
     			type:"POST",
     			dataType:"json",
     			async:true,
     			cache:false,
     			success:function(result) {
     				if(result.success!=true){
     			        parent.$.modalDialog({
     			            title : '添加',
     			            width : 500,
     			            height : 150,
     			            href : '${path }/items/addSecondPage?chapter_sub_item_id='+checkId+'&chapter_id='+chapterId,
     			            buttons : [ {
     			                text : '添加',
     			                handler : function() {
     			                     parent.$.modalDialog.openner_tree = layout_west_tree;  //因为添加成功之后，需要刷新这个dataGrid，所以先预定义好 */ */
     			                    var f = parent.$.modalDialog.handler.find('#addSecondForm');
     			                    f.submit();
     			                }
     			            } ]
     			        });
     				}
     			}
     		});
    		
    	 }
    	 if(node==null){
	    	 parent.$.modalDialog({
	 	            title : '添加',
	 	            width : 500,
	 	            height : 150,
	 	            href : '${path }/items/addSecondPage?chapter_sub_item_id=0&chapter_id='+chapterId,
	 	            buttons : [ {
	 	                text : '添加',
	 	                handler : function() {
	 	                     parent.$.modalDialog.openner_tree = layout_west_tree;  //因为添加成功之后，需要刷新这个dataGrid，所以先预定义好 */ */
	 	                    var f = parent.$.modalDialog.handler.find('#addSecondForm');
	 	                    f.submit();
	 	                }
	 	            } ]
	 	        });
    	 }
    } 
/*  删除文本块*/
    function deleteSecondFun() {    
    	var node = $('#center_tree').tree('getSelected');
    	if(node!=null){
   		 checkId=node.id; 
   		 $.ajax({
    			url:'${path }/items/find',
    			data:{items_id:node.id,items_name:node.text},
    			type:"POST",
    			dataType:"json",
    			async:true,
    			cache:false,
    			success:function(result) {
    				if(result.success==true){
    					parent.$.messager.confirm('询问', '您是否要删除当前文本块？', function(b) {
    			        	if (b) {
    			                progressLoad();
    			                $.post('${path}/items/deleteSecond', {
    			                	id : node.id,
    			                	t_name:tName
    			                }, function(result) {
    			                    if (result.success) {
    			                        parent.$.messager.alert('提示', result.msg, 'info');
    			                        layout_west_tree.tree('reload');
    			                    }
    			                    progressClose();
    			                }, 'JSON');
    			            }
    			        
    			        });
    				}
    			}
    		});
   		
   	 }
    }
    /*控制文本块是否可编辑  */
    function editFun(){
    	$('#content_show').removeAttr('disabled');
    }
	/* 文本添加 */
    function postFun(){
    	$('#content_show').attr('disabled','disabled');
    	$.ajax({
			url:'${path }/content/add',
			data:{item_id:itemId,detail_content:$('#content_show').val(),t_name:tName},
			type:"POST",
			dataType:"json",
			async:true,
			cache:false,
			success:function(result) {
				parent.$.messager.alert('成功', result.msg);
			}
		});
    }
	//上传图片
    function upLoadFun(){
    	if(($('#photo_file').val()!="")&&($('#photo_name').val()!="")){
	        $("#photo").ajaxSubmit({
	            type:'post',
	            url:'${path }/imgs/upLoad?t_name='+tName+'&subId='+subId, 
	            success:function(result){
	            	$('#photo_file').val("");
	            	$('#photo_name').val("");
	            	parent.$.messager.alert('成功', "上传成功！");
	            
	            },
	        });
    	}
    }  
</script>
</head>
<body>
    
    <div id="index_layout" >
           <div data-options="region:'west',border:true,split:false,title:'章节目录'"  style="width:300px;overflow: hidden;overflow-y:auto; padding:0px ">
       			 <ul id="layout_west_tree"  style="width:300px;margin: 10px 10px 10px 10px">
       			 </ul>
       		 </div>
		
          <div id="center" data-options="region:'center' ,border:false" style="overflow: hidden;overflow-y:auto; border-right:1px solid #999;">
	       		<div id="annu">
		       		<div id="toolbarFirst" style="float:left;" >
			            <a onclick="addFirstFun();" style="margin-top:10px;margin-left:10px;" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">添加小病种</a>
		    		</div>
		    		<div id="deletebarFirst"  >
			            <a onclick="deleteFirstFun();" style="margin-top:10px;margin-left:10px;" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">删除小病种</a>
		    		</div>
		    		<div id="toolbarSecond" style="float:left;" >
			            <a onclick="addSecondFun();" style="margin-top:10px;margin-left:10px;" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">添加文本块</a>
		    		</div>
		    		<div id="deletebarSecond"  >
			            <a onclick="deleteSecondFun();" style="margin-top:10px;margin-left:10px;" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">删除文本块</a>
		    		</div>
		    	</div>
	    		<div>
       		 		<ul id="center_tree"  style="width:220px;margin: 10px 10px 10px 10px;">
       		 			
       			    </ul>
       			</div>
       			<div >
       				<input  id="book_id" type="hidden" value="${book_id} " />
       				<input  id="user" type="hidden" value="${user}" />
       			</div>
       			
           </div>
            <div id="right" data-options="region:'east'" style="width:650px;float:right;">

	           		<div id="content" style="width:635px;height:470px;margin:15px auto;">
	           			<textarea id="content_show" rows="28" cols="80" disabled="disabled" style="resize:none; width:630px;height:420px;font-size:15px;"></textarea>
	           			<div style="width:100px;height:40px;margin:0 auto;">
		           			<input type="button" id="edit" onclick="editFun();" style="float:left;width:40px;height:30px;margin:5px auto;" value="编辑"/>
		           			<input type="button" id="post" onclick="postFun();" style="width:40px;height:30px;margin-top:5px;margin-left:10px;" value="保存"/>
	           			</div>
	           		</div>
	           		 
	           		<div id="img" style="margin-left:120px;" >
						<form id="photo" enctype="multipart/form-data">		
							<table>
								<tr>
									<td>
										照片说明:  <input id="photo_name" style="width:300px;" type="text" name="image_explain"/>
									</td>
								</tr>
								<tr>
									<td>
										<input id="photo_file" type="file" name="studentPhoto" style="border:1px solid #999;"/>
									</td>
								</tr>
								<tr>
									<td><input onclick="upLoadFun()" type="button" value="上传照片"></td>
								</tr>
							</table>
						</form>
					</div>
					<div id="photo_show">
						
					</div>
            </div>
     </div>

    <style>
        /*ie6提示*/
        #ie6-warning{width:100%;position:absolute;top:0;left:0;background:#fae692;padding:5px 0;font-size:12px}
        #ie6-warning p{width:960px;margin:0 auto;}
    </style>
</body>
</html>