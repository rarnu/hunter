<script type="text/javascript">
	function returnPropData(data) {
		document.getElementById("_id").value = data.id;
		$("#_keywords")[0].value = data.keywords;
		$("#_key2")[0].value = data.key2;
		$("#_key3")[0].value = data.key3;
		$("#_key4")[0].value = data.key4;
		$("#_key5")[0].value = data.key5;
		$("#_emergency")[0].value = data.emergency;
		$("#_color")[0].value = data.color;
		$("#_status")[0].value = data.status;
		
	}
	function loadPropData(id) {
		$.getJSON("../api/api_query_job_prop.php?_from=site&_id="+id, returnPropData); 
	}

	function returnSave(data) {
		
		$("#_prop_detail").modal("hide");
		if (data.result == 1) {
			$("#_msg_text")[0].innerHTML = "保存失败，请联系管理员";
			$("#_alert_message").modal("toggle");
		} else {
			getData(page);
		}
	}
	function doSaveProp() {
		var query = "../api/api_edit_job_prop.php?";
		query += "_id="+document.getElementById("_id").value;
		query += "&_keywords="+$("#_keywords")[0].value;
		query += "&_key2="+$("#_key2")[0].value;
		query += "&_key3="+$("#_key3")[0].value;
		query += "&_key4="+$("#_key4")[0].value;
		query += "&_key5="+$("#_key5")[0].value;
		query += "&_emergency="+$("#_emergency")[0].value;
		query += "&_color="+$("#_color")[0].value;
		query += "&_status="+$("#_status")[0].value;
		$.getJSON(query, returnSave);
	}
</script>
<div id="_prop_detail" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-header">
        	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        	<h3 id="myModalLabel">职位属性</h3>
        </div>
        <div class="modal-body">
	<div class="form-horizontal" id="_prop_detail_body" name="_prop_detail_body">
		<input type="hidden" name="_id" id="_id">
		<table border='0' width='100%' cellspacing='4px' cellpadding='4px'>
			<tr><td width='25%'>关键字</td><td>
				<input type='text' name='_keywords' id='_keywords' class='input-block-level' style='width:60px;'>
				<input type='text' name='_key2' id='_key2' class='input-block-level' style='width:60px;'>
				<input type='text' name='_key3' id='_key3' class='input-block-level' style='width:60px;'>
				<input type='text' name='_key4' id='_key4' class='input-block-level' style='width:60px;'>
				<input type='text' name='_key5' id='_key5' class='input-block-level' style='width:60px;'>
			</td></tr>
			<tr><td width='25%'>紧急程度</td><td><select name="_emergency" id="_emergency"><option value="2">非常高</option><option value="1">高</option><option value="0" selected="true">普通</option><option value="-1">低</option><option value="-2">非常低</option></select></td></tr>
			<tr><td width='25%'>标题颜色</td><td><select name="_color" id="_color"><option value="0" selected="true">黑色</option><option value="1">红色</option><option value="2">蓝色</option><option value="3">绿色</option></select></td></tr>
			<tr><td width='25%'>职位状态</td><td><select name="_status" id="_status"><option value="0" selected="true">打开</option><option value="1">关闭</option></select></td></tr>
		</table>
	</div>
	</div>
	<div class="modal-footer">
		<button class="btn btn-primary" onclick="doSaveProp();">保存</button>
		<button class="btn" data-dismiss="modal">关闭</button>
	</div>
</div>
<?php
include "alert_message.php";
?>
