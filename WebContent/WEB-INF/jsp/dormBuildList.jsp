<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript">
	function dormBuildDeleteOrActive(dormBuildId,disabled) {
		if(confirm("您确定要删除这个宿舍楼吗？")) {
			window.location="dormBuild.action?action=deleteOrAcive&id="+dormBuildId+"&disabled="+disabled;
		}
	}
	
	//文档加载完后
	window.onload=function(){
		//获取后台保存的当前要修改的foodTypeId值
		var id = "${id}";
		//获取菜系select标签
		var idSelect = document.getElementById("id");
		//获取下拉框中所有的option
		var  options = idSelect.options;
		
		//遍历菜系select标签中所有的option标签
		$.each( options, function(i, option){
		  $(option).attr("selected",option.value == id);
		});
	}
	
	$(document).ready(function(){
		$("#dormBuild").addClass("active");
	});
</script>
<div class="data_list">
		<div class="data_list_title">
			宿舍楼管理
		</div>
		<form name="myForm" class="form-search" method="post" action="dormBuild.action?action=list">
				<button class="btn btn-success" type="button" style="margin-right: 50px;" onclick="javascript:window.location='dormBuild.action?action=preAdd'">添加</button>
				<span class="data_search">
					<select id="id" name="id" style="width: 120px;">
						<option value="">全部宿舍楼</option>
							<option value=""></option>
					</select>
					&nbsp;<button type="submit" class="btn btn-info" onkeydown="if(event.keyCode==13) myForm.submit()">搜索</button>
				</span>
		</form>
		<div>
			<table class="table table-striped table-bordered table-hover datatable">
				<thead>
					<tr>
						<th width="15%">序号</th>
						<th>名称</th>
						<th>简介</th>
						<th width="20%">操作</th>
					</tr>
				</thead>
				<tbody>
						<tr>
							<td>1</td>
							<td>1号楼</td>
							<td>无</td>
							<td>
								<button class="btn btn-mini btn-info" type="button" onclick="javascript:window.location='dormBuild.action?action=preUpdate&id=1'">修改</button>&nbsp;
									<button class="btn btn-mini btn-danger" type="button" onclick="dormBuildDeleteOrActive(1,1)">删除</button>
								
									<button class="btn btn-mini btn-danger" type="button" onclick="dormBuildDeleteOrActive(1,0)">激活</button>
							
							</td>
						</tr>
						<tr>
							<td>2</td>
							<td>2号楼</td>
							<td>无</td>
							<td>
								<button class="btn btn-mini btn-info" type="button" onclick="javascript:window.location='dormBuild.action?action=preUpdate&id=2'">修改</button>&nbsp;
									<button class="btn btn-mini btn-danger" type="button" onclick="dormBuildDeleteOrActive(2,1)">删除</button>
								
									<button class="btn btn-mini btn-danger" type="button" onclick="dormBuildDeleteOrActive(2,0)">激活</button>
							
							</td>
						</tr>
				</tbody>
			</table>
		</div>
		<div align="center"><font color="red"></font></div>
</div>