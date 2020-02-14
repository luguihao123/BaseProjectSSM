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
            <label>财务报告名称:</label><input id="search-name" class="wu-text" style="width:100px">
            <label>撰写人:</label><input id="search-people" class="wu-text" style="width:100px">
            <a href="#" id="search-btn" class="easyui-linkbutton" iconCls="icon-search">搜索</a>
        </div>
    </div>
    <!-- End of toolbar -->
    <table id="data-datagrid" class="easyui-datagrid" toolbar="#wu-toolbar"></table>
</div>

<!-- Begin of easyui-dialog添加框 -->
<div id="add-dialog" class="easyui-dialog" data-options="closed:true,iconCls:'icon-save'" style="width:480px; padding:10px;">
	<form id="add-form" method="post">
        <table>
            <tr>
                <td width="60" align="right">报告名称:</td>
                <td><input type="text" name="name" class="wu-text easyui-validatebox" data-options="required:true, missingMessage:'请填写用户名'" /></td>
            </tr>
            <tr>
                <td width="60" align="right">撰写人:</td>
                <td><input type="text" name="people" class="wu-text easyui-validatebox" data-options="required:true, missingMessage:'请填写密码'" /></td>
            </tr>
            <tr>
                <td width="60" align="right">备注:</td>
                <td><input type="text" name="beizhu" class="wu-text easyui-validatebox" /></td>
            </tr>
            <tr>
                <td width="60" align="right">附件:</td>
                <td><input type="text" id="add-fujian" name="fujian" value="XX附件" readonly="readonly" class="wu-text"/><a herf="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-upload" onclick="uploadFujian()" plain="true">上传文件</a></td>
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
                <td width="60" align="right">报告名称:</td>
                <td><input type="text" id="edit-name" name="name" readonly="readonly" class="wu-text easyui-validatebox" data-options="required:true, missingMessage:'请填写报告名称'" /></td>
            </tr>
            
            <tr>
                <td width="60" align="right">撰写人:</td>
                <td><input type="text" id="edit-people" name="people" class="wu-text easyui-validatebox" data-options="required:true, missingMessage:'请填写撰写人'" /></td>
            </tr>
            <tr>
                <td width="60" align="right">备注:</td>
                <td><input type="text" id="edit-beizhu" name="beizhu" class="wu-text easyui-validatebox" /></td>
            </tr>
            <tr>
                <td width="60" align="right">附件:</td>
                <td><input type="text" id="edit-fujian" name="fujian" class="wu-text easyui-validatebox" data-options="required:true, missingMessage:'请填写附件'" /></td>
            </tr>
        </table>
    </form>
</div>
<div id="process-dialog" class="easyui-dialog" data-options="closed:true,iconCls:'icon-upload',title:'正在上传文档'" style="width:450px; padding:10px;">
<div id="p" class="easyui-progressbar" style="width:400px;" data-options="text:'正在上传中...'"></div>
</div>
<input type="file" id="fujian-file" style="display:none;" onchange="upload()">
<%@include file="../common/footer.jsp"%>

<!-- End of easyui-dialog -->
<script type="text/javascript">
//上传文件
    function start(){
	var value = $('#p').progressbar('getValue');
	if (value<100){
		value += Math.floor(Math.random() * 10);
		$('#p').progressbar('setValue',value);
	}else{
		$('#p').progressbar('setValue',0)
	}
}

	function upload(){
	if($("fujian-file").val() == '')return;
		var formData = new FormData();
		formData.append('fujian',document.getElementById('fujian-file').files[0]);
		$("#process-dialog").dialog('open');
		var interval=setInterval (start,200);
		$.ajax({
			url:'upload_fujian',
			type:'post',
			data:formData,
			contextType:false,
			processData:false,
			success:function(data){
				clearInterval(interval);
				$("#process-dialog").dialog('close');
				if(data.type == 'success'){
					$("#add-fujian").val(data.filepath);
				}else{
					$.messager.alert("消息提醒",data.msg,"warning");
				}
			},
			error:function(data){
				clearInterval(interval);
				$("#process-dialog").dialog('close');
				$.messager.alert("消息提醒","上传失败!","warning");
			}
		});
	}
	
	function uploadFujian(){
		$("#fujian-file").click();
	}
	
	/**
	*  添加财务报告
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
	* 修改财务报告
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
	* 删除财务报告
	*/
	function remove(){
		$.messager.confirm('信息提示','确定要删除该记录？', function(result){
			if(result){
				var item = $('#data-datagrid').datagrid('getSelected');
				$.ajax({
					url:'../../admin/caiwu/delete',
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
	
	/**
	* Name 打开添加窗口
	*/
	function openAdd(){
		//$('#add-form').form('clear');
		$('#add-dialog').dialog({
			closed: false,
			modal:true,
            title: "添加财务报告",
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
            title: "修改财务报告信息",
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
	
	
	//搜索按钮监听
	$("#search-btn").click(function(){
		var option = {name:$("#search-name").val()};
		var option2 = {people:$("#search-people").val()};
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
			{ field:'name',title:'财务报告名称',width:100},
			{ field:'people',title:'撰写人',width:100},
			{ field:'beizhu',title:'备注',width:100},
			{ field:'fujian',title:'附件',width:200}
		]],
	});
</script>