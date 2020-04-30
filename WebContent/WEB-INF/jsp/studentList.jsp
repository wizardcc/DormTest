<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">

	function studentDelete(studentId,disabled) {
		if(confirm("您确定要删除这个学生吗？")) {
			window.location="student.action?action=deleteOrActive&id="+studentId+"&disabled="+disabled;
		}
	}
	
	
	
	$(document).ready(function(){
		$("#student").addClass("active");
	});
</script>
<style type="text/css">
	.span6 {
		width:0px;
		height: 0px;
		padding-top: 0px;
		padding-bottom: 0px;
		margin-top: 0px;
		margin-bottom: 0px;
	}

</style>
<div class="data_list">
		<div class="data_list_title">
			学生管理
		</div>
		<form name="myForm" class="form-search" method="post" action="student.action?action=list" style="padding-bottom: 0px">
				<button class="btn btn-success" type="button" style="margin-right: 50px;" onclick="javascript:window.location='student.action?action=preAdd'">添加</button>
				<span class="data_search">
						<select id="dormBuildId" name="dormBuildId" style="width: 110px;">
							<option value="">全部宿舍楼</option>
							<c:forEach items="${builds}" var="build">
								<option value="${build.id}">${build.name}</option>
							</c:forEach>
						</select>
					
					<select id="searchType" name="searchType" style="width: 80px;">
						<option value="name">姓名</option>
						<option value="stuCode">学号</option>
						<option value="tel">宿舍编号</option>
						<option value="sex">性别</option>
						<option value="tel">电话号码</option>
					</select>
					&nbsp;<input id="keyword" name="keyword" value="" type="text"  style="width:120px;height: 30px;" class="input-medium search-query">
					&nbsp;<button type="submit" class="btn btn-info" onkeydown="if(event.keyCode==13) myForm.submit()">搜索</button>
				</span>
		</form>
		<div>
			<table class="table table-striped table-bordered table-hover datatable">
				<thead>
					<tr>
					<th>学号</th>
					<th>姓名</th>
					<th>性别</th>
					<th>宿舍楼</th>
					<th>寝室编号</th>
					<th>电话</th>
					<th>操作</th>
				</tr>
				</thead>
				<tbody>
						<tr>
							<td>12321}</td>
							<td>张三</td>
							<td>男</td>
							<td>1号楼</td>
							<td>1-208</td>
							<td>15654678986</td>
							<td>
								<button class="btn btn-mini btn-info" type="button" onclick="javascript:window.location='student.action?action=preUpdate&id=1'">修改</button>&nbsp;
								
									<button class="btn btn-mini btn-danger" type="button" onclick="studentDelete(1,1)">删除</button>
								
									<button class="btn btn-mini btn-danger" type="button" onclick="javascript:window.location='student.action?action=deleteOrActive&id=1&disabled=0'">激活</button>
								
							</td>
						</tr>
				</tbody>
			</table>
		</div>
		<div align="center"><font color="red"></font></div>
</div>