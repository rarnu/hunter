<?php
include "navbar.php";
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
// api_query_view_count.php

function returnJson(data) {
	$("#_view_count")[0].innerHTML = data.result;
}

function getViewCount() {
	$.getJSON("../api/api_query_view_count.php", returnJson);
}

	var pagecount = 1;
        var page = 0;

        function returnData(data) {
                $("#_feedback")[0].innerHTML = "<tr><td><b>#</b></td><td><b>设备应用信息</b></td><td><b>帐号列表</b></td><td><b>日期</b></td><td><b>反馈信息</b></td></tr>"+data;
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
                $.get("../api/api_query_feedback.php?_from=site&_type=1&_page="+page, returnData);
        }
        function getPageCount() {
                $.get("../api/api_query_pagecount_feedback.php?_from=site&_type=1", returnPage);
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
        <div class="panel panel-default"><div class="panel-heading"><font size="3">用户反馈的信息</font></div>
                <table class="table" name="_feedback" id="_feedback">
                </table>
        </div>

        <div class="alert alert-warning" style="height:30px;">
                <table border="0" width="100%">
                        <tr><td align="right"><input type="button" value="上一页" class="btn btn-primary" name="btn_prev" id="btn_prev" onclick="prevPage();">&nbsp;&nbsp;&nbsp;&nbsp;<span name="_pages" id="_pages"></span>&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="下一页" class="btn btn-primary" name="btn_next" id="btn_next" onclick="nextPage();">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
                </table>
        </div>
</div>
<script type="text/javascript">
getViewCount();
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

