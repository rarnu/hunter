<?php

include "../database/database.php";

$from = $_GET["_from"];
$page = $_GET["_page"];

$db = openConnection();
$page--;
$page_size = 10;
$from_idx = $page * $page_size;
$sql = "select * from hunter_timeline order by id desc limit $from_idx, $page_size";
$result = query($db, $sql);
closeConnection($db);
$str = "";
if ($from == "mobile") {
	$str = "{\"data\":[";
}
while (list($id, $pd, $c)=mysql_fetch_row($result)) {
	if ($from == "site") {	
		$c = str_replace("\n","<br>",$c);
		$str = $str."<div class='alert alert-warning'>$pd</br><div style='background-color:#fff;' class='alert'><font color='#0'>$c</font></div></div>";
	} else {
		$str = $str."{\"id\":$id,\"publish_date\":".json_encode($pd).",\"comment\":".json_encode($c)."},";
	}
}
if ($from == "mobile") {
	$str = rtrim($str, ",");
	$str = $str."]}";
}

echo $str;

?>
