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
		if (data == "") {
			data = "<tr><td colspan='5'>没有找到相关的职位</td></tr>";
		}
		$("#_jobs")[0].innerHTML = "<tr><td><b>#</b></td><td><b>职位名称</b></td><td><b>工作地区</b></td><td><b>公司名称（描述）</b></td><td> </td></tr>"+data;
		$("#_pages")[0].innerHTML = page+"/"+pagecount;
		$("#btn_prev")[0].disabled = (page <= 1);
		$("#btn_next")[0].disabled = (page >= pagecount);
	}

	function returnPage(data) {
		pagecount = data;
	}

	function getData(idx, key) {
		page = idx;
		$.get("../api/api_query_job_search.php?_from=site&_page="+page+"&_keyword="+key, returnData);
	}
	function getPageCount(key) {
		$.get("../api/api_query_pagecount_search.php?_from=site&_keyword="+key, returnPage);
	}
	function showDetail(id) {
		loadDetailData(id);
		$("#_job_detail").modal("toggle");
	}
	function prevPage() {
		if (page > 1) {
			var key = $("#_keywords")[0].value;
			getData(page - 1, key);
		}
	}
	function nextPage() {
		if (page < pagecount) {
			var key = $("#_keywords")[0].value;
			getData(page + 1, key);
		}
	}
	function doSearch() {
		var keyword = $("#_keywords")[0].value;
		if (keyword == "") {
			$("#_alert_search").modal("toggle");
			return;
		}
		getPageCount(keyword);
		getData(1, keyword);
	}
</script>

</head>

<body>
<div class="container">
	<div class="form-horizontal">
	<table border="0">
	<tr><td>输入关键字</td><td><input type="text" class="input-block-level" name="_keywords" id="_keywords" size="80"></td><td><input type="button" class="btn btn-primary" value="搜索" onclick="doSearch();"></td></tr>
	</table>
	</div><br>
	<div class="panel panel-default"><div class="panel-heading"><font size="3">搜索结果</font></div>
		<table class="table" name="_jobs" id="_jobs">
		</table>
	</div>

	<div class="alert alert-warning" style="height:30px;">
		<table border="0" align="right">
			<tr><td align="right"><input type="button" value="上一页" class="btn btn-primary" name="btn_prev" id="btn_prev" onclick="prevPage();">&nbsp;&nbsp;&nbsp;&nbsp;<span name="_pages" id="_pages"></span>&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="下一页" class="btn btn-primary" name="btn_next" id="btn_next" onclick="nextPage();">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
		</table>
	</div>
</div>
<?php
	include "popup_job.php";
	include "alert_search.php";
?>

</body>

</html>

<?php
include "bottombar.php";

?>

