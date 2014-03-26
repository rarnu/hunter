<?php
include "navbar.php";
include "../database/database.php";
session_start();
if (!isset($_SESSION["_user_id"])) {
	header("Location: login.php");
}
?>
<html>

<head>
<meta http-equiv="Content-Language" content="en-us">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content>
<meta name="author" content>

<script type="text/javascript">
	var pagecount = 1;
	var page = 0;

	function returnData(data) {
		$("#_timeline")[0].innerHTML = "<tr><td><b>#</b></td><td><b>发布日期</b></td><td><b>摘要</b></td><td> </td></tr>"+data;
		$("#_pages")[0].innerHTML = page+"/"+pagecount;
		$("#btn_prev")[0].disabled = (page <= 1);
		$("#btn_next")[0].disabled = (page >= pagecount);
	}

	function returnPage(data) {
		pagecount = data;
		if (page > pagecount) {
			page = pagecount;
		}
	}

	function getData(idx) {
		page = idx;
		$.get("../api/api_query_manage_timeline.php?_from=site&_page="+page, returnData);
	}
	function getPageCount() {
		$.get("../api/api_query_pagecount_timeline.php?_from=site", returnPage);
	}

	function doEdit(id) {
		location.href="edit_timeline.php?_id="+id;
	}
	function returnDelete(data) {
		if (data.result == 0) {
			getPageCount();
			getData(page);
		} else {
			$("#_msg_text")[0].innerHTML = "删除数据失败，请联系管理员";
			$("#_alert_message").modal("toggle");
		}
	}

	function deleteCallback(id) {
		$.getJSON("../api/api_delete_timeline.php?_id="+id, returnDelete);
	}
	function doDelete(id) {
		setInnerId(id);
		callbackFunction = deleteCallback;
		$("#_msg_confirm")[0].innerHTML = "确定要删除这条时间线吗？";
		$("#_alert_confirm").modal("toggle");
	}
	function prevPage() {
		if (page > 1) {
			getData(page - 1);
		}
	}
	function nextPage() {
		if (page < pagecount) {
			getData(page + 1);
		}
	}
    function new_publish() {
        location.href="publish_timeline.php";
    }
</script>

</head>

<body>
<div class="container">
    <div class="panel panel-default">
	<form method="POST" action="../api/api_edit_anno.php" class="form-horizontal">
	我的个人简介<br>
	<input type="hidden" id="_from" name="_from" value="site">
	<textarea id="_comment" name="_comment" rows="10" style="width:100%"></textarea>
	<table border="0" width="100%"><tr><td align="right"><input type="submit" value="提交修改" class="btn btn-primary"></td></tr></table>
	</form>
<hr>
<div class="panel panel-default"><input type="button" value="发布新的时间线" class="btn btn-primary" style="width:120px;" onclick="new_publish();"></div><br>
	<div class="panel panel-default"><div class="panel-heading"><font size="3">已发布的时间线</font></div>
		<table class="table" name="_timeline" id="_timeline">
		</table>
	</div>

	<div class="alert alert-warning" style="height:30px;">
		<table border="0" width="100%">
			<tr><td align="right"><input type="button" value="上一页" class="btn btn-primary" name="btn_prev" id="btn_prev" onclick="prevPage();">&nbsp;&nbsp;&nbsp;&nbsp;<span name="_pages" id="_pages"></span>&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="下一页" class="btn btn-primary" name="btn_next" id="btn_next" onclick="nextPage();">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
		</table>
	</div>
</div>

<?php
include "popup_prop.php";
include "alert_message.php";
include "alert_confirm.php";
?>

<script type="text/javascript">
	if (page == 0) {
		getPageCount();
		getData(1);
	}
	function returnAnno(data) {
		$("#_comment")[0].value = data.comment;
	}
	$.getJSON("../api/api_query_anno.php", returnAnno);
</script>

</body>

</html>

<?php
include "bottombar.php";

?>

