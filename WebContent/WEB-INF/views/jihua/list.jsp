<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="../common/header.jsp"%>

<!-- 工作计划列表 -->

<div class="easyui-layout" data-options="fit:true">
    <!-- Begin of toolbar -->
    <div id="wu-toolbar">
        <div class="wu-toolbar-button">
            <%@include file="../common/menus.jsp"%>
        </div>
        <div class="wu-toolbar-search">
            <label>工作计划名称:</label><input id="search-name" class="wu-text" style="width:100px">
            <label>撰写人:</label><input id="search-people" class="wu-text" style="width:100px">
            <a href="#" id="search-btn" class="easyui-linkbutton" iconCls="icon-search">搜索</a>
        </div>
    </div>
    <!-- End of toolbar -->
    <table id="data-datagrid" class="easyui-datagrid" toolbar="#wu-toolbar"></table>
</div>
<!-- Begin of easyui-dialog 添加窗口 -->
<div id="add-dialog" class="easyui-dialog" data-options="closed:true,iconCls:'icon-save'" style="width:420px; padding:10px;">
	<form id="add-form" method="post">
        <table>
            <tr>
                <td width="60" align="right">工作计划名称:</td>
                <td><input type="text" name="name" class="wu-text easyui-validatebox" data-options="required:true, missingMessage:'请填写工作计划名称'" /></td>
            </tr>
            <tr>
                <td width="60" align="right">撰写人:</td>
                <td><input type="text" name="people" class="wu-text easyui-validatebox" data-options="required:true, missingMessage:'请填写撰写人'" /></td>
            </tr>
            <tr>
                <td width="60" align="right">备注:</td>
                <td><input type="text" name="beizhu" class="wu-text easyui-validatebox" data-options="required:true, missingMessage:'请填写备注'" /></td>
            </tr>
            <tr>
                <td width="60" align="right">附件:</td>
                <td><input type="text" name="fujian" class="wu-text easyui-validatebox" data-options="required:true, missingMessage:'请填写附件'"/></td>
            </tr>
            <tr>
                <td width="60" align="right">审核状态:</td>
                <td>
                	<select name="state" class="easyui-combobox" panelHeight="auto" style="width:268px">
		                <option value="0">未审核</option>
		            </select>
                </td>
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
                <td width="60" align="right">工作计划报告名称:</td>
                <td><input type="text" name="name" id="edit-name" readonly="readonly" class="wu-text easyui-validatebox" data-options="required:true, missingMessage:'工作计划名称无法修改'" /></td>
            </tr>
            <tr>
                <td width="60" align="right">撰写人:</td>
                <td><input type="text" name="people" id="edit-people" class="wu-text easyui-validatebox" data-options="required:true, missingMessage:'请填写撰写人'" /></td>
            </tr>
            <tr>
                <td width="60" align="right">备注:</td>
                <td><input type="text" name="beizhu" id="edit-beizhu" class="wu-text easyui-validatebox" data-options="required:true, missingMessage:'请填写备注'" /></td>
            </tr>
            <tr>
                <td width="60" align="right">附件:</td>
                <td><input type="text" name="fujian" id="edid-fujian" class="wu-text easyui-validatebox" data-options="required:true, missingMessage:'请填写附件'"/></td>
            </tr>
        </table>
    </form>
</div>

<!-- 审核工作计划 -->
<div id="review-dialog" class="easyui-dialog" data-options="closed:true,iconCls:'icon-save'" style="width:450px; padding:10px;">
	<form id="review-form" method="post">
        <table>
            <tr>
                <td>
                	<select id="review-state" name="state" class="easyui-combobox" panelHeight="auto" style="width:268px">
		                <option value="0">未审核</option>
            			<option value="1">审核通过</option>
            			<option value="2">审核未通过</option>
		            </select>
                </td>
            </tr>
        </table>
    </form>
</div>
<%@include file="../common/footer.jsp"%>

<!-- End of easyui-dialog -->
<script type="text/javascript">
	
	/**
	*  添加工作计划
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
	* Name 修改工作计划
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
	
	//审核工作计划
	function review(){
		var validate = $("#review-form").form("validate");
		if(!validate){
			$.messager.alert("消息提醒","请检查你输入的数据!","warning");
			return;
		}
		var data = $("#review-form").serialize();
		$.ajax({
			url:'review',
			dataType:'json',
			type:'post',
			data:data,
			success:function(data){
				if(data.type == 'success'){
					
					$.messager.alert('信息提示','审核状态修改成功！','info');
					$('#review-dialog').dialog('close');
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
            title: "添加工作计划",
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
            title: "修改工作计划",
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
            	$("#edit-name").val(item.name);
				$("#edit-people").val(item.people);
            	$("#edit-beizhu").val(item.beizhu);
            	$("#edit-fujian").val(item.fujian);
            }
        });
	}
	
	//打开审核窗口
	function openReview(){
		//$('#edit-form').form('clear');
		var item = $('#data-datagrid').datagrid('getSelections');
		if(item == null || item.length == 0){
			$.messager.alert('信息提示','请选择要审核的工作计划！','info');
			return;
		}
		if(item.length > 1){
			$.messager.alert('信息提示','请选择一个工作计划进行修改！','info');
			return;
		}
		item = item[0];
		$('#review-dialog').dialog({
			closed: false,
			modal:true,
            title: "审核工作计划",
            buttons: [{
                text: '确定',
                iconCls: 'icon-ok',
                handler: review
            }, {
                text: '取消',
                iconCls: 'icon-cancel',
                handler: function () {
                    $('#review-dialog').dialog('close');                    
                }
            }],
            onBeforeOpen:function(){
            	$("#review-state").combobox('setValue',item.state);
            }
        });
	}
	
	//删除工作计划
	function remove(){
		$.messager.confirm('信息提示','确定要删除该记录？', function(result){
			if(result){
				var item = $('#data-datagrid').datagrid('getSelected');
				$.ajax({
					url:'../../admin/jihua/delete',
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
		var option = {people:$("#search-people").val()};
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
		idField:'name',
	    treeField:'name',
		fit:true,
		columns:[[
			{ field:'chk',checkbox:true},
			{ field:'name',title:'工作计划名称',width:100,sortable:true},
			{ field:'people',title:'撰写人',width:100,sortable:true},
			{ field:'beizhu',title:'备注',width:100,sortable:true},
			{ field:'fujian',title:'附件',width:100,sortable:true},
			{ field:'state',title:'审核状态',width:100,formatter:function(value,row,index){
				switch(value){
					case 0:{
						return '未审核';
					}
					case 1:{
						return '审核通过';
					}
					case 2:{
						return '审核未通过';
					}
				}
				return value;
			}},
		]]
	});
</script>