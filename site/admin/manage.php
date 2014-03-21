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
		$("#_jobs")[0].innerHTML = "<tr><td><b>#</b></td><td><b>职位名称</b></td><td><b>工作地区</b></td><td><b>公司名称（描述）</b></td><td> </td></tr>"+data;
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
		$.get("../api/api_query_manage.php?_from=site&_page="+page, returnData);
	}
	function getPageCount() {
		$.get("../api/api_query_pagecount.php?_from=site", returnPage);
	}

	function doEdit(id) {
		location.href="edit.php?_id="+id;
	}
	function doProp(id) {
		loadPropData(id);
		$("#_prop_detail").modal("show");
	}
	function returnDelete(data) {
		if (data.result == 0) {
			getPageCount();
			getData(page);
		} else {
			alert("删除数据失败");
		}
	}
	function doDelete(id) {
		var ret = confirm("确定要删除这条职位信息吗？");
		if (ret) {
			$.getJSON("../api/api_delete_job.php?_id="+id, returnDelete);
		}
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
        location.href="publish.php";
    }
</script>

</head>

<body>
<div class="container">
    <div class="panel panel-default"><input type="button" value="发布新职位" class="btn btn-primary" style="width:120px;" onclick="new_publish();"></div><br>
	<div class="panel panel-default"><div class="panel-heading"><font size="3">当前发布中的职位</font></div>
		<table class="table" name="_jobs" id="_jobs">
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
?>

<script type="text/javascript">
	if (page == 0) {
		getPageCount();
		getData(1);
	}
</script>

</body>

</html>

<?php
include "bottombar.php";

?>

