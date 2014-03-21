<?php

include "../database/database.php";

$from = $_GET["_from"];
$page = $_GET["_page"];

$db = openConnection();
$page--;
$page_size = 10;
$from_idx = $page * $page_size;
$sql = "select id, t_job_title, t_work_area, t_company_name, t_color, t_status from hunter_job order by t_emergency, id desc limit $from_idx, $page_size";
$result = query($db, $sql);
closeConnection($db);
$str = "";
if ($from == "mobile") {
	$str = "{\"data\":[";
}
while (list($id, $jt, $wa, $cn,$c,$s)=mysql_fetch_row($result)) {
	if ($from == "site") {
		$cstr = $jt;
		if ($s == 1) {
			$cstr = "<font color='gray'><s>$cstr</s></font>";
		} else {
			if ($c == 1) {
				$cstr = "<font color='red'>$cstr</font>";
			} else if ($c == 2) {
				$cstr = "<font color='blue'>$cstr</font>";
			} else if ($c == 3) {
				$cstr = "<font color='green'>$cstr</font>";
			}
		} 
		$str = $str."<tr><td>$id</td><td>$cstr</td><td>$wa</td><td>$cn</td><td><input type='button' value='修改' onclick='doEdit($id);' class='btn btn-primary'>&nbsp;&nbsp;<input type='button' value='属性' onclick='doProp($id);' class='btn btn-primary'>&nbsp;&nbsp;<input type='button' value='删除' onclick='doDelete($id);' class='btn btn-primary'></td></tr>";
	} else {
		$str = $str."{\"id\":$id,\"job_title\":".json_encode($jt).",\"work_area\":".json_encode($wa).",\"company_name\":".json_encode($cn).",\"color\":$c,\"status\":$s},";
	}
}
if ($from == "mobile") {
	$str = rtrim($str, ",");
	$str = $str."]}";
}

echo $str;

?>
