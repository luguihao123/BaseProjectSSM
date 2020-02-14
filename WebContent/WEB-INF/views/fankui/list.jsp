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
            <label>捐赠人姓名:</label><input id="search-name" class="wu-text" style="width:100px">
            <label>时间:</label><input id="search-time" class="wu-text" style="width:100px">
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
                <td width="60" align="right">捐赠人:</td>
                <td><input type="text" name="name" class="wu-text easyui-validatebox" data-options="required:true, missingMessage:'请填写捐赠人'" /></td>
            </tr>
            <tr>
                <td width="60" align="right">反馈信息:</td>
                <td><input type="text" name="xinxi" class="wu-text easyui-validatebox" data-options="required:true, missingMessage:'请填写信息'" /></td>
            </tr>
            <tr>
                <td width="60" align="right">反馈人:</td>
                <td><input type="text" name="fname" class="wu-text easyui-validatebox" data-options="required:true, missingMessage:'请填写反馈人'" /></td>
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
                <td width="60" align="right">时间:</td>
                <td><input type="text" id="edit-time" name="time" class="wu-text easyui-validatebox" data-options="required:true, missingMessage:'请填写时间'" /></td>
            </tr>
            
            <tr>
                <td width="60" align="right">捐赠人:</td>
                <td><input type="text" id="edit-name" name="name" readonly="readonly" class="wu-text easyui-validatebox" data-options="required:true, missingMessage:'捐赠人无法进行修改'" /></td>
            </tr>
            <tr>
                <td width="60" align="right">反馈信息:</td>
                <td><input type="text" id="edit-xinxi" name="xinxi" class="wu-text easyui-validatebox" data-options="required:true, missingMessage:'请填写反馈信息'"  /></td>
            </tr>
            <tr>
                <td width="60" align="right">反馈人:</td>
                <td><input type="text" id="edit-fname" name="fname" class="wu-text easyui-validatebox" data-options="required:true, missingMessage:'请填写反馈人'" /></td>
            </tr>
        </table>
    </form>
</div>
<%@include file="../common/footer.jsp"%>

<!-- End of easyui-dialog -->
<script type="text/javascript">
	
	/**
	*  添加捐赠反馈记录
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
            title: "添加捐赠反馈记录",
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
	
	//修改捐赠反馈记录
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
	
	//打开修改窗口
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
            title: "修改捐赠反馈信息",
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
            	$("#edit-time").val(item.time);
            	$("#edit-name").val(item.name);
            	$("#edit-xinxi").val(item.xinxi);
            	$("#edit-fname").val(item.fname);
            }
        });
	}	
	
	//删除捐赠反馈
	function remove(){
		$.messager.confirm('信息提示','确定要删除该记录？', function(result){
			if(result){
				var item = $('#data-datagrid').datagrid('getSelected');
				$.ajax({
					url:'../../admin/fankui/delete',
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
		var option2 = {time:$("#search-time").val()};
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
			{ field:'time',title:'时间',width:100,sortable:true},
			{ field:'name',title:'捐赠人',width:100,sortable:true},
			{ field:'xinxi',title:'反馈信息',width:100,sortable:true},
			{ field:'fname',title:'反馈人',width:100,sortable:true},
		]]
	});
</script>