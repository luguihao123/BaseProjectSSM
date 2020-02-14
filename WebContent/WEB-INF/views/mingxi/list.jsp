<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="../common/header.jsp"%>

<!-- 基金收支明细记录列表 -->

<div class="easyui-layout" data-options="fit:true">
    <!-- Begin of toolbar -->
    <div id="wu-toolbar">
        <div class="wu-toolbar-button">
            <%@include file="../common/menus.jsp"%>
        </div>
        <div class="wu-toolbar-search">
            <label>时间:</label><input id="search-time" class="wu-text" style="width:100px">
            <label>类型:</label><input id="search-leixing" class="wu-text" style="width:100px">
            <label>操作人:</label><input id="search-people" class="wu-text" style="width:100px">
            <a href="#" id="search-btn" class="easyui-linkbutton" iconCls="icon-search">搜索</a>
        </div>
    </div>
    <!-- End of toolbar -->
    <table id="data-datagrid" class="easyui-datagrid" toolbar="#wu-toolbar"></table>
</div>

<!-- 添加框   -->
<div id="add-dialog" class="easyui-dialog" data-options="closed:true,iconCls:'icon-save'" style="width:420px; padding:10px;">
	<form id="add-form" method="post">
        <table>
            <tr>
                <td width="60" align="right">时间:</td>
                <td><input type="text" name="time" class="wu-text easyui-validatebox" data-options="required:true, missingMessage:'请填写时间'" /></td>
            </tr>
            <tr>
                <td width="60" align="right">类型:</td>
                <td><input type="text" name="leixing" class="wu-text easyui-validatebox" data-options="required:true, missingMessage:'请填写类型'" /></td>
            </tr>
            <tr>
                <td width="60" align="right">金额:</td>
                <td><input type="text" name="jine" class="wu-text easyui-validatebox" data-options="required:true, missingMessage:'请填写金额'" /></td>
            </tr>
            <tr>
                <td width="60" align="right">说明:</td>
                <td><input type="text" name="shuoming" class="wu-text easyui-validatebox" data-options="required:true, missingMessage:'请填写说明'" /></td>
            </tr>
            <tr>
                <td width="60" align="right">操作人:</td>
                <td><input type="text" name="people" class="wu-text easyui-validatebox" data-options="required:true, missingMessage:'请填写操作人'" /></td>
            </tr>
        </table>
    </form>
</div>


<%@include file="../common/footer.jsp"%>

<!-- End of easyui-dialog -->
<script type="text/javascript">

	/**
	*  添加基金收支明细
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
	* 打开添加窗口
	*/
	function openAdd(){
		//$('#add-form').form('clear');
		$('#add-dialog').dialog({
			closed: false,
			modal:true,
            title: "添加基金收支明细",
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
	
	
	
	
	//搜索按钮监听
	$("#search-btn").click(function(){
		var option = {time:$("#search-time").val()};
		if(option != null){
		$('#data-datagrid').datagrid('reload',option);
		}
		var a = {leixing:$("#search-leixing").val()};
		if(a != null){
		$('#data-datagrid').datagrid('reload',a);
		}
		var b = {people:$("#search-people").val()};
		if(b != null){
		$('#data-datagrid').datagrid('reload',b);
		}
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
		idField:'time',
	    treeField:'time',
		fit:true,
		columns:[[
			{ field:'time',title:'时间',width:100},
			{ field:'leixing',title:'类型',width:100},
			{ field:'jine',title:'金额',width:100},
			{ field:'shuoming',title:'说明',width:100},
			{ field:'people',title:'操作人',width:100},
		]]
	});
</script>