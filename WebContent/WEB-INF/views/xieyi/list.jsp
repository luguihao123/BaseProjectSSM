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
            <label>捐赠协议名称:</label><input id="search-name" class="wu-text" style="width:100px">
            <label>时间:</label><input id="search-time" class="wu-text" style="width:100px">
            <a href="#" id="search-btn" class="easyui-linkbutton" iconCls="icon-search">搜索</a>
        </div>
    </div>
    <!-- End of toolbar -->
    <table id="data-datagrid" class="easyui-datagrid" toolbar="#wu-toolbar"></table>
</div>

<!-- Begin of easyui-dialog添加框 -->
<div id="add-dialog" class="easyui-dialog" data-options="closed:true,iconCls:'icon-save'" style="width:500px; padding:10px;">
	<form id="add-form" method="post">
        <table>
            <tr>
                <td width="60" align="right">捐赠协议名称:</td>
                <td><input type="text" name="name" class="wu-text easyui-validatebox" data-options="required:true, missingMessage:'请填写捐赠协议'" /></td>
            </tr>
            <tr>
                <td width="60" align="right">联系电话:</td>
                <td><input type="text" name="phone" class="wu-text easyui-validatebox" data-options="required:true, missingMessage:'请填写联系电话'" /></td>
            </tr>
            <tr>
                <td width="60" align="right">金额:</td>
                <td><input type="text" name="jine" class="wu-text easyui-validatebox" data-options="required:true, missingMessage:'请填写金额'"/></td>
            </tr>
            <tr>
                <td width="60" align="right">支付方式:</td>
                <td><input type="text" name="fangshi" class="wu-text easyui-validatebox" data-options="required:true, missingMessage:'请填写支付方式'" /></td>
            </tr>
            <tr>
                <td width="60" align="right">资助项目:</td>
                <td><input type="text" name="xiangmu" class="wu-text easyui-validatebox" data-options="required:true, missingMessage:'请填写资助项目'" /></td>
            </tr>
            <tr>
                <td width="60" align="right">时间:</td>
                <td><input type="text" name="time" class="wu-text easyui-validatebox" data-options="required:true, missingMessage:'请填写时间'" /></td>
            </tr>
            <tr>
                <td width="60" align="right">备注:</td>
                <td><input type="text" name="beizhu" class="wu-text easyui-validatebox" data-options="required:true" /></td>
            </tr>
            <tr>
                <td width="60" align="right">附件 :</td>
                <td><input type="text" name="fujian" class="wu-text easyui-validatebox" data-options="required:true, missingMessage:'请填写附件'" /></td>
                <td><a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-upload" onclick="uploadPhoto()" plain="true">上传附件</a></td>
            </tr>
        </table>
    </form>
</div>

<%@include file="../common/footer.jsp"%>

<!-- End of easyui-dialog -->
<script type="text/javascript">
	/**
	*  添加捐赠协议
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
	* Name 打开添加窗口
	*/
	function openAdd(){
		//$('#add-form').form('clear');
		$('#add-dialog').dialog({
			closed: false,
			modal:true,
            title: "添加捐赠协议",
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
		var option = {name:$("#search-name").val()};
		var option = {time:$("#search-time").val()};
		$('#data-datagrid').datagrid('reload',option);
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
		idField:'id',
	    treeField:'name',
		fit:true,
		columns:[[
			{ field:'chk',checkbox:true},
			{ field:'name',title:'捐赠协议名称',width:100},
			{ field:'phone',title:'联系电话',width:100},
			{ field:'jine',title:'金额',width:100},
			{ field:'fangshi',title:'支付方式',width:200},
			{ field:'xiangmu',title:'资助的项目',width:100},
			{ field:'time',title:'时间',width:100},
			{ field:'beizhu',title:'备注',width:100},
			{ field:'fujian',title:'附件',width:200}
		]],
	});
</script>