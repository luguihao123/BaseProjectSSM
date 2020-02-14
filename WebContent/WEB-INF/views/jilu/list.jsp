<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="../common/header.jsp"%>

<!-- 捐赠反馈记录列表 -->

<div class="easyui-layout" data-options="fit:true">
    <!-- Begin of toolbar -->
    <div id="wu-toolbar">
        <div class="wu-toolbar-button">
            <%@include file="../common/menus.jsp"%>
        </div>
        <div class="wu-toolbar-search">
            <label>项目实施记录名称:</label><input id="search-name" class="wu-text" style="width:100px">
            <label>撰写人:</label><input id="search-xiepeople" class="wu-text" style="width:100px">
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
                <td width="60" align="right">项目实施记录名称:</td>
                <td><input type="text" name="name" class="wu-text easyui-validatebox" data-options="required:true, missingMessage:'请填写项目实施记录名称'" /></td>
            </tr>
            <tr>
                <td width="60" align="right">撰写人:</td>
                <td><input type="text" name="xiepeople" class="wu-text easyui-validatebox" data-options="required:true, missingMessage:'请填写撰写人'" /></td>
            </tr>
            <tr>
                <td width="60" align="right">事件:</td>
                <td><input type="text" name="shijian" class="wu-text easyui-validatebox" data-options="required:true, missingMessage:'请填写事件'" /></td>
            </tr>
            <tr>
                <td width="60" align="right">实施人:</td>
                <td><input type="text" name="zuopeople" class="wu-text easyui-validatebox" data-options="required:true, missingMessage:'请填写实施人'" /></td>
            </tr>
            <tr>
                <td width="60" align="right">支撑材料:</td>
                <td><input type="text" name="cailiao" class="wu-text easyui-validatebox" data-options="required:true, missingMessage:'请填写支撑材料'" /></td>
            </tr>
        </table>
    </form>
</div>

<%@include file="../common/footer.jsp"%>

<!-- End of easyui-dialog -->
<script type="text/javascript">

	/**
	*  添加项目实施记录
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
            title: "添加项目实施记录",
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
	//删除项目实施记录
	function remove(){
		$.messager.confirm('信息提示','确定要删除该记录？', function(result){
			if(result){
				var item = $('#data-datagrid').datagrid('getSelected');
				$.ajax({
					url:'../../admin/jilu/delete',
					dataType:'json',
					type:'post',
					data:{name:item.name},
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
	
	
	
	//搜索按钮监听
	$("#search-btn").click(function(){
		var option = {name:$("#search-name").val()};
		var option2 = {xiepeople:$("#search-xiepeople").val()};
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
		idField:'name',
	    treeField:'name',
		fit:true,
		columns:[[
			{ field:'chk',checkbox:true},
			{ field:'name',title:'项目实施记录名称',width:100},
			{ field:'xiepeople',title:'撰写人',width:100},
			{ field:'shijian',title:'事件',width:100},
			{ field:'zuopeople',title:'实施人',width:100},
			{ field:'cailiao',title:'支撑材料',width:100},
		]]
	});
</script>