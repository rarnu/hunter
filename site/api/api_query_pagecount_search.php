<?php
include "../database/database.php";

$from = $_GET["_from"];
$keyword = $_GET["_keyword"];

$db = openConnection();
$sql = "select count(*) from hunter_job where t_status=0 and ( t_company_name like '%$keyword%' or t_work_area like '%$keyword%' or t_job_title like '%$keyword%' or t_job_accountability like '%$keyword%' or t_job_requirement like '%$keyword%' or t_keywords like '%$keyword%' or key2 like '%$keyword%' or key3 like '%$keyword%' or key4 like '%$keyword%' or key5 like '%$keyword%')";
$result = query($db, $sql);
closeConnection($db);
$count = 1;
while (list($c)=mysql_fetch_row($result)) {
	$count = $c;
}
$page_size = 10;
$page = floor($count / $page_size);
if ($count % $page_size != 0) {
	$page++;
}
if ($page == 0) {
	$page++;
}
if ($from == "site") {
	echo $page;
} else {
	echo "{\"count\":$page}";
}


?>
