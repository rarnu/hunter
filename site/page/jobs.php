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
		$("#_jobs")[0].innerHTML = "<tr><td><b>#</b></td><td><b>职位名称</b></td><td><b>工作地区</b></td><td><b>公司名称（描述）</b></td><td> </td></tr>"+data;
		$("#_pages")[0].innerHTML = page+"/"+pagecount;
		$("#btn_prev")[0].disabled = (page <= 1);
		$("#btn_next")[0].disabled = (page >= pagecount);
	}

	function returnPage(data) {
		pagecount = data;
	}

	function getData(idx) {
		page = idx;
		$.get("../api/api_query_job.php?_from=site&_page="+page, returnData);
	}
	function getPageCount() {
		$.get("../api/api_query_pagecount.php?_from=site", returnPage);
	}
	function showDetail(id) {
		loadDetailData(id);
		$("#_job_detail").modal("toggle");
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
	<div class="panel panel-default"><div class="panel-heading"><font size="3">当前发布中的职位</font></div>
		<table class="table" name="_jobs" id="_jobs">
		</table>
	</div>

	<div class="alert alert-warning" style="height:30px;">
		<table border="0" align="right">
			<tr><td align="right"><input type="button" value="上一页" class="btn btn-primary" name="btn_prev" id="btn_prev" onclick="prevPage();">&nbsp;&nbsp;&nbsp;&nbsp;<span name="_pages" id="_pages"></span>&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="下一页" class="btn btn-primary" name="btn_next" id="btn_next" onclick="nextPage();">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
		</table>
	</div>
</div>
<script type="text/javascript">
	if (page == 0) {
		getPageCount();
		getData(1);
	}
</script>
<?php
	include "popup_job.php";
?>

</body>

</html>

<?php
include "bottombar.php";

?>

