<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="../common/header.jsp"%>
<div class="easyui-layout" data-options="fit:true">
    <!-- Begin of toolbar -->
    <div id="wu-toolbar">
        <div class="wu-toolbar-button">
            <%@include file="../common/menus.jsp"%>
        </div>
        <div class="wu-toolbar-search">
            <label>用途:</label><input id="search-yongtu" class="wu-text" style="width:100px">
            <label>金额:</label><input id="search-jine" class="wu-text" style="width:100px">
            <a href="#" id="search-btn" class="easyui-linkbutton" iconCls="icon-search">搜索</a>
        </div>
    </div>
    <!-- End of toolbar -->
    <table id="data-datagrid" class="easyui-datagrid" toolbar="#wu-toolbar"></table>
</div>
<!-- Begin of easyui-dialog 添加框-->
<div id="add-dialog" class="easyui-dialog" data-options="closed:true,iconCls:'icon-save'" style="width:420px; padding:10px;">
	<form id="add-form" method="post">
        <table>
            <tr>
                <td width="60" align="right">用途:</td>
                <td><input type="text" name="yongtu" class="wu-text easyui-validatebox" data-options="required:true, missingMessage:'请填写用途'" /></td>
            </tr>
            <tr>
                <td width="60" align="right">金额:</td>
                <td><input type="text" name="jine" class="wu-text easyui-validatebox" data-options="required:true, missingMessage:'请填写金额'" /></td>
            </tr>
            <tr>
                <td width="60" align="right">备注:</td>
                <td><input type="text" name="beizhu" class="wu-text easyui-validatebox" data-options="required:true, missingMessage:'请填写备注'" /></td>
            </tr>
            <tr>
                <td width="60" align="right">附件:</td>
                <td><input type="text" name="fujian" class="wu-text easyui-validatebox" data-options="required:true, missingMessage:'请填写附件'"/></td>
            </tr>
        </table>
    </form>
</div>
<!-- 修改窗口 -->
<div id="edit-dialog" class="easyui-dialog" data-options="closed:true,iconCls:'icon-save'" style="width:450px; padding:10px;">
	<form id="edit-form" method="post">
        <input type="hidden" name="id" id="edit-id">
        <table>
            <tr>
                <td width="60" align="right">用途:</td>
                <td><input type="text" id="edit-yongtu" name="yongtu" readonly="readonly" class="wu-text easyui-validatebox" data-options="required:true, missingMessage:'请填写用途'" /></td>
            </tr>
            <tr>
                <td width="60" align="right">金额:</td>
                <td><input type="text" id="edit-jine" name="jine" class="wu-text easyui-validatebox" data-options="required:true, missingMessage:'请填写金额'" /></td>
            </tr>
            <tr>
                <td width="60" align="right">备注:</td>
                <td><input type="text" id="edit-beizhu" name="beizhu" class="wu-text easyui-validatebox" data-options="required:true, missingMessage:'请填写备注'"/></td>
            </tr>
            <tr>
                <td width="60" align="right">附件:</td>
                <td><input type="text" id="edit-fujian" name="fujian" class="wu-text easyui-validatebox" data-options="required:true, missingMessage:'请填写附件'"/></td>
            </tr>
        </table>
    </form>
</div>

<%@include file="../common/footer.jsp"%>

<!-- End of easyui-dialog -->
<script type="text/javascript">
	/**
	*  添加财务预算
	*/
	function add(){
		var validate = $("#add-form").form("validate");
		if(!validate){
			$.messager.alert("消息提醒","请检查你输入的数据!","warning");
			return;
		}
		var data = $("#add-form").serialize();
		$.ajax({
			url:'add',
			dataType:'json',
			type:'post',
			data:data,
			success:function(data){
				if(data.type == 'success'){
					$.messager.alert('信息提示','添加成功！','info');
					$('#add-dialog').dialog('close');
					$('#data-datagrid').datagrid('reload');
				}else{
					$.messager.alert('信息提示',data.msg,'warning');
				}
			}
		});
	}
	
	/**
	* 修改财务预算
	*/
	function edit(){
		var validate = $("#edit-form").form("validate");
		if(!validate){
			$.messager.alert("消息提醒","请检查你输入的数据!","warning");
			return;
		}
		var data = $("#edit-form").serialize();
		$.ajax({
			url:'edit',
			dataType:'json',
			type:'post',
			data:data,
			success:function(data){
				if(data.type == 'success'){
					$.messager.alert('信息提示','修改成功！','info');
					$('#edit-dialog').dialog('close');
					$('#data-datagrid').datagrid('reload');
				}else{
					$.messager.alert('信息提示',data.msg,'warning');
				}
			}
		});
	}
	
	/**
	* 删除财务预算
	*/
	function remove(){
		$.messager.confirm('信息提示','确定要删除该记录？', function(result){
			if(result){
				var item = $('#data-datagrid').datagrid('getSelected');
				$.ajax({
					url:'../../admin/yusuan/delete',
					dataType:'json',
					type:'post',
					data:{yongtu:item.yongtu},
					success:function(data){
						if(data.type == 'success'){
							$.messager.alert('信息提示','删除成功！','info');
							$('#data-datagrid').datagrid('reload');
						}else{
							$.messager.alert('信息提示',data.msg,'warning');
						}
					}
				});
			}	
		});
	}
	
	/**
	* Name 打开添加窗口
	*/
	function openAdd(){
		//$('#add-form').form('clear');
		$('#add-dialog').dialog({
			closed: false,
			modal:true,
            title: "添加财务预算信息",
            buttons: [{
                text: '确定',
                iconCls: 'icon-ok',
                handler: add
            }, {
                text: '取消',
                iconCls: 'icon-cancel',
                handler: function () {
                    $('#add-dialog').dialog('close');                    
                }
            }],
            onBeforeOpen:function(){
            	//$("#add-form input").val('');
            }
        });
	}
	
	/**
	* 打开修改窗口
	*/
	function openEdit(){
		//$('#edit-form').form('clear');
		var item = $('#data-datagrid').datagrid('getSelections');
		if(item == null || item.length == 0){
			$.messager.alert('信息提示','请选择要修改的数据！','info');
			return;
		}
		if(item.length > 1){
			$.messager.alert('信息提示','请选择一条数据进行修改！','info');
			return;
		}
		item = item[0];
		$('#edit-dialog').dialog({
			closed: false,
			modal:true,
            title: "修改财务预算信息",
            buttons: [{
                text: '确定',
                iconCls: 'icon-ok',
                handler: edit
            }, {
                text: '取消',
                iconCls: 'icon-cancel',
                handler: function () {
                    $('#edit-dialog').dialog('close');                    
                }
            }],
            onBeforeOpen:function(){
            	$("#edit-id").val(item.id);
            	$("#edit-yongtu").val(item.yongtu);
            	$("#edit-jine").val(item.jine);
            	$("#edit-beizhu").val(item.beizhu);
            	$("#edit-fujian").val(item.fujian);
            }
        });
	}	
	
	
	//搜索按钮监听
	$("#search-btn").click(function(){
		var option = {yongtu:$("#search-yongtu").val()};
		var option2 = {jine:$("#search-jine").val()};
		$('#data-datagrid').datagrid('reload',option);
		$('#data-datagrid').datagrid('reload',option2);
	});
	
	/** 
	* 载入数据
	*/
	$('#data-datagrid').datagrid({
		url:'list',
		rownumbers:true,
		singleSelect:false,
		pageSize:20,           
		pagination:true,
		multiSort:true,
		fitColumns:true,
		idField:'yongtu',
	    treeField:'yongtu',
		fit:true,
		columns:[[
			{ field:'chk',checkbox:true},
			{ field:'yongtu',title:'用途',width:100},
			{ field:'jine',title:'金额',width:100},
			{ field:'beizhu',title:'备注',width:100},
			{ field:'fujian',title:'附件',width:200}
		]],
	});
</script>