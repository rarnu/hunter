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
</script>

</head>

<body>
<div class="container">
	<div class="panel panel-default">
		<table class="table" border="0" cellspacing="4px" cellpadding="4px">
			<tr><td width="25%">总浏览量（次）</td><td id="_view_count"></td></tr>
		</table>
	</div>
</div>
<script type="text/javascript">
getViewCount();
</script>
</body>

</html>

<?php
include "bottombar.php";

?>

