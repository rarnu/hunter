<?php
include "navbar.php";
include "../database/database.php";
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
		$("#_timeline")[0].innerHTML = data;
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
		$.get("../api/api_query_timeline.php?_from=site&_page="+page, returnData);
	}
	function getPageCount() {
		$.get("../api/api_query_pagecount_timeline.php?_from=site", returnPage);
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
</script>

</head>

<body>
<div class="container">
    <div class="panel panel-default">
	<form method="POST" action="../api/api_edit_anno.php" class="form-horizontal">
	Vicky 的个人简介<br>
	<div class="alert" id="_comment" name="_comment"></div>
	</form>
<hr>
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
?>

<script type="text/javascript">
	if (page == 0) {
		getPageCount();
		getData(1);
	}
	function returnAnno(data) {
		$("#_comment")[0].innerHTML = data.comment;
	}
	$.getJSON("../api/api_query_anno.php?_from=site", returnAnno);
</script>

</body>

</html>

<?php
include "bottombar.php";

?>

