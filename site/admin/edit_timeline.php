<?php
include "navbar.php";
?>
<html>

<head>
<meta http-equiv="Content-Language" content="en-us">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content>
<meta name="author" content>

<script type="text/javascript">
function doSubmit() {
	var ret = true;
	if (document.getElementById("_publish_date").value == "") {
		$("#_msg_text")[0].innerHTML = "发布日期不能为空";
		$("#_alert_message").modal("toggle");
		ret = false;
	} else if (document.getElementById("_commment").value == "") {
		$("#_msg_text")[0].innerHTML = "文章内容不能为空";
		$("#_alert_message").modal("toggle");
		ret = false;
	} 
	return ret;
}
</script>

</head>

<body>
<div class="container">
	<div class="hero-unit" align="center">
		<div align="center">修改时间线</div><br>
		<form method="POST" action="../api/api_edit_timeline.php" class="form-horizontal" onsubmit="return doSubmit();">
		<input type="hidden" name="_from" value="site">
        <input type="hidden" name="_id" id="_id" value="">
		<table width="80%" border="0" align="center" cellspacing="4px" cellpadding="4px">
		<tr><td width="25%">发布日期</td><td><input type="text" style="width:35%;" name="_publish_date" id="_publish_date" class="input-block-level" onfocus="HS_setDate(this);"></td></tr>
		<tr><td width="25%">文章内容</td><td><textarea rows="20" style="width:100%;" name="_comment" id="_comment"></textarea></td>
		<tr><td colspan="2" align="center"><input type="submit" value="提交" class="btn btn-primary" style="width:20%;"></td></tr>
		</table>
		</form>
	</div>
</div>
<script src="../common/js/dateselect.js" ></script>

<?php
include "alert_message.php";
?>

<script type="text/javascript">
var url = location.href;
var params = url.substr(url.indexOf('?')+1).split('=');
var id = params[1];

function returnJson(data) {
    // $("#_id")[0].value = data.id;
    document.getElementById("_id").value = data.id;
    $("#_publish_date")[0].value = data.publish_date;
    $("#_comment")[0].value = data.comment;
 }

$.getJSON("../api/api_query_timeline_detail.php?_from=mobile&_id="+id, returnJson);

</script>
</body>

</html>

<?php
include "bottombar.php";
?>

