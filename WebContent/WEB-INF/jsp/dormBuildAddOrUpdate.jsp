<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript">
	function checkForm(){
		var name=document.getElementById("name").value;
		if(name==null||name==""){
			document.getElementById("error").innerHTML="名称不能为空！";
			return false;
		}
		return true;
	}
	$(document).ready(function(){
		$("#dormBuild").addClass("active");
	});	
</script>
<div class="data_list">
		<div class="data_list_title">
		
				修改宿舍楼/添加宿舍楼
		</div>
		<form action="dormBuild.action?action=save" method="post" onsubmit="return checkForm()">
			<div class="data_form" >
				<input type="hidden" id="id" name="id" value=""/>
				<table align="center">
					<tr>
						<td><font color="red">*</font>名称：</td>
						<td><input type="text" id="name"  name="name" value=""  style="margin-top:5px;height:30px;" /></td>
					</tr>
					<tr>
						<td>&nbsp;简介：</td>
						<td><textarea id="remark" name="remark" rows="10"></textarea></td>
					</tr>
				</table>
				<div align="center">
					<input type="submit" class="btn btn-primary" value="保存"/>
					&nbsp;<button class="btn btn-primary" type="button" onclick="javascript:history.back()">返回</button>
				</div>
				<div align="center">
					<font id="error" color="red"></font>
				</div>
			</div>
		</form>
</div>