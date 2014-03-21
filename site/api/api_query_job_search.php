<?php

include "../database/database.php";

$from = $_GET["_from"];
$page = $_GET["_page"];
$keyword = $_GET["_keyword"];

$db = openConnection();
$page--;
$page_size = 10;
$from_idx = $page * $page_size;

$sql = "select id, t_job_title, t_work_area, t_company_name from hunter_job where t_status=0 and  (t_company_name like '%$keyword%' or t_work_area like '%$keyword%' or t_job_title like '%$keyword%' or t_job_accountability like '%$keyword%' or t_job_requirement like '%$keyword%' or t_keywords like '%$keyword%' or key2 like '%$keyword%' or key3 like '%$keyword%' or key4 like '%$keyword%' or key5 like '%$keyword%')  order by id desc limit $from_idx, $page_size";
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
