<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
		format: "yyyy-mm-dd", //时间格式  yyyy-mm-dd hh:ii:ss */
	});
	
});


	
	$(document).ready(function(){
		$("#record").addClass("active");
	});
</script>
<div class="data_list">
		<div class="data_list_title">修改缺勤记录
				添加缺勤记录
		</div>
		<form action="record.action?action=save" method="post" onsubmit="return checkForm()">
			<div class="data_form" >
				<div align="center">
					<font id="error" color="red"></font>
				</div>
				<input type="hidden" id="id" name="id" value=""/>
				<table align="center">
					<tr>
						<td><font color="red">*</font>学号：</td>
						<td><input type="text" onblur="formOnblur(this.value);" id="stuCode"  name="stuCode" value=""  style="margin-top:5px;height:30px;" /></td>
					</tr>
					<tr>
						<td><font color="red">*</font>日期：</td>
	                    <td><input id="date" name="date" value="" style="margin-top:5px;height:30px;" placeholder="缺勤日期" type="text" class="controls input-append date form_date" readonly ></td>
					</tr>
					<tr>
						<td>备注：</td>
						<td><input type="text" id="remark"  name="remark" value=""  style="margin-top:5px;height:30px;" /></td>
					</tr>
				</table>
				<div align="center">
					<input type="submit" class="btn btn-primary" value="保存"/>
					&nbsp;<button class="btn btn-primary" type="button" onclick="javascript:history.back()">返回</button>
				</div>
			</div>
		</form>
</div>