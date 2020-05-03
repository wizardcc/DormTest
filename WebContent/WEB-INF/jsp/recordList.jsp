<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
$(document).ready(function(){
	$('.form_date').datetimepicker({
		language:'zh-CN',/*语言  默认值：’en’ */
	    weekStart: 1,/* 一周从哪一天开始。0（星期日）到6（星期六） */
	    todayBtn:  1,/*当天日期将会被选中。  */
		autoclose: 1,//选择后自动关闭当前时间控件
		todayHighlight: 1,/*高亮当天日期。  */
		startView: 2,/* 从月视图开始，选中那一天  3为选月份*/
		minView: 2,/* 从月视图开始，选天   选完天后，不在出现下级时分秒时间选择 */
		forceParse: 0,/*就是你输入的可能不正规，但是它胡强制尽量解析成你规定的格式（format）  */
		/* format: "yyyy-mm-dd hh:ii:ss", //时间格式  yyyy-mm-dd hh:ii:ss */
	});
	
});


	
	function deleteOrAcive(recordId,disabled) {
		if(confirm("您确定要删除或激活这条记录吗？")) {
			window.location="record.action?action=deleteOrAcive&id="+recordId+"&disabled="+disabled;
		}
	}
	
	
	
	$(document).ready(function(){
		$("#record").addClass("active");
	});
</script>

<div class="data_list">
		<div class="data_list_title">
			缺勤记录
		</div>
		<form name="myForm" onsubmit="return checkForm()" action="record.action?action=list"  class="form-search" method="post"  style="padding-bottom: 0px">
				<c:if test="${session_user.roleId != 2 }">
					<button class="btn btn-success" type="button" style="margin-right: 50px;" onclick="javascript:window.location='record.action?action=preAdd'">添加</button>
				</c:if>
				<span class="data_search">
					<span class="controls input-append date form_date" style="margin-right: 10px" data-date-format="yyyy-mm-dd">
                    	<input id="startDate" name="startDate" style="width:120px;height: 30px;" placeholder="起始日期" type="text" value="" readonly >
                    	<span class="add-on"><i class="icon-remove"></i></span>
						<span class="add-on"><i class="icon-th"></i></span>
               		</span>
					<span class="controls input-append date form_date" style="margin-right: 10px" data-date-format="yyyy-mm-dd">
                    	<input id="endDate" name="endDate" style="width:120px;height: 30px;" placeholder="终止日期" type="text" value="" readonly>
                    	<span class="add-on"><i class="icon-remove"></i></span>
						<span class="add-on"><i class="icon-th"></i></span>
               		</span>
               		<c:if test="${session_user.roleId != 2 }">
						<select id="dormBuildId" name="dormBuildId" style="width: 100px;">
							<option value="">所有楼栋</option>
							<c:forEach items="${builds}" var="build">
								<option value="${build.id}">${build.name}</option>
							</c:forEach>
						</select>
					</c:if>
						<select id="sex" name="sex" style="width: 90px;">
							<option value="">全部</option>
							<option value="男">男</option>
							<option value="女">女</option>
						</select>
					
						<select id="searchType" name="searchType" style="width: 80px;">
							<option value="name">姓名</option>
							<option value="stuCode">学号</option>
							<option value="dormCode">宿舍编号</option>
						</select>
						&nbsp;<input id="keyword" name="keyword" value="" type="text"  style="width:120px;height: 30px;" class="input-medium search-query" >
					
					
					&nbsp;<button type="submit" class="btn btn-info" onkeydown="if(event.keyCode==13) myForm.submit()">搜索</button>
				</span>
		</form>
		<div>
			<table class="table table-striped table-bordered table-hover datatable">
				<thead>
					<tr>
					<th>日期</th>
					<th>学号</th>
					<th>姓名</th>
					<th>性别</th>
					<th>宿舍楼</th>
					<th>寝室</th>
					<th>备注</th>
					<c:if test="${session_user.roleId != 2 }">
						<th>操作</th>
					</c:if>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>2019-10-01</td>
						<td>412321</td>
						<td>张三</td>
						<td>男</td>
						<td>1号楼</td>
						<td>1-201</td>
						<td>无</td>
							<c:if test="${session_user.roleId != 2 }">
								<td>
									<button class="btn btn-mini btn-success" type="button" onclick="javascript:window.location='record.action?action=preUpdate&id=${record.id }'">修改</button>
									<c:if test="${record.disabled ==0}">
										<button class="btn btn-mini btn-danger" type="button" onclick="deleteOrAcive(${record.id },1)">删除</button>
									</c:if>
									<c:if test="${record.disabled ==1}">
										<button class="btn btn-mini btn-danger" type="button" onclick="deleteOrAcive(${record.id },0)">激活</button>
									</c:if>
								</td>
							</c:if>
					</tr>
				</tbody>
			</table>
		</div>
		<div align="center"><font color="red"></font></div>
</div>