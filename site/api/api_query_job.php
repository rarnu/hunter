<?php

include "../database/database.php";

$from = $_GET["_from"];
$page = $_GET["_page"];

$db = openConnection();
$page--;
$page_size = 10;
$from_idx = $page * $page_size;
$sql = "select id, t_job_title, t_work_area, t_company_name from hunter_job order by id desc limit $from_idx, $page_size";
$result = query($db, $sql);
closeConnection($db);
$str = "";
if ($from == "mobile") {
	$str = "{\"data\":[";
}
while (list($id, $jt, $wa, $cn)=mysql_fetch_row($result)) {
	if ($from == "site") {
		$str = $str."<tr><td>$id</td><td>$jt</td><td>$wa</td><td>$cn</td><td><input type='button' value='详情' onclick='showDetail($id);' class='btn btn-primary' ></td></tr>";
	} else {
		$str = $str."{\"id\":$id,\"job_title\":".json_encode($jt).",\"work_area\":".json_encode($wa).",\"company_name\":".json_encode($cn)."},";
	}
}
if ($from == "mobile") {
	$str = rtrim($str, ",");
	$str = $str."]}";
}

echo $str;

?>
