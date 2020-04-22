<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
						<!-- C标签循环显示出所有的宿舍楼,显示的是build.name,点击后传递的是build.id -->
						<c:forEach items="${builds}"  var="build" varStatus="stat">
							<option value="${build.id}">${build.name}</option>
						</c:forEach>
						
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
				<!--items:表示要循环遍历的元素   var:代表当前集合中每一个元素     varStatus代表循环状态的变量名-->
					<c:forEach items="${builds}"  var="build" varStatus="stat">
						<tr>
							<td>${stat.index+1}</td>
							<td>${build.name}</td>
							<td>${build.remark}</td>
							<td>
								<button class="btn btn-mini btn-info" type="button" onclick="javascript:window.location='dormBuild.action?action=preUpdate&id=${build.id}'">修改</button>&nbsp;
								<!-- 根据宿舍楼的disabled值来判断要显示删除还是激活 -->>
								<c:if test="${build.disabled == 0 }">
									<button class="btn btn-mini btn-danger" type="button" onclick="dormBuildDeleteOrActive(${build.id},1)">删除</button>
								</c:if>
								<c:if test="${build.disabled == 1 }">
									<button class="btn btn-mini btn-danger" type="button" onclick="dormBuildDeleteOrActive(${build.id},0)">激活</button>
								</c:if>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div align="center"><font color="red"></font></div>
</div>