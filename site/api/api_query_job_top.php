<?php

include "../database/database.php";

$from = $_GET["_from"];
$page = $_GET["_page"];

$db = openConnection();
$page--;
$page_size = 10;
$from_idx = $page * $page_size;
$sql = "select id, t_job_title, t_work_area, t_company_name, t_color from hunter_job where t_status=0 order by t_view_count desc limit $from_idx, $page_size";
$result = query($db, $sql);
closeConnection($db);
$str = "";
if ($from == "mobile") {
	$str = "{\"data\":[";
}
while (list($id, $jt, $wa, $cn,$c)=mysql_fetch_row($result)) {
	if ($from == "site") {
		$cstr = $jt;
		if ($c == 1) {
			$cstr = "<font color='red'>$cstr</font>";
		} else if ($c == 2) {
			$cstr = "<font color='blue'>$cstr</font>";
		} else if ($c == 3) {
			$cstr = "<font color='green'>$cstr</font>";
		}
		
		$str = $str."<tr><td>$id</td><td>$cstr</td><td>$wa</td><td>$cn</td><td><input type='button' value='详情' onclick='showDetail($id);' class='btn btn-primary' ></td></tr>";
	} else {
		$str = $str."{\"id\":$id,\"job_title\":".json_encode($jt).",\"work_area\":".json_encode($wa).",\"company_name\":".json_encode($cn).", \"color\":$c},";
	}
}
if ($from == "mobile") {
	$str = rtrim($str, ",");
	$str = $str."]}";
}

echo $str;

?>
