<?php
include "../database/database.php";

$from = $_GET["_from"];
$type = $_GET["_type"];

$db = openConnection();
$sql = "select count(*) from hunter_feedback where t_type=$type";
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
