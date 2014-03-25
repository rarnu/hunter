<?php

include "../database/database.php";

$from = $_GET["_from"];
$page = $_GET["_page"];

$db = openConnection();
$page--;
$page_size = 10;
$from_idx = $page * $page_size;
$sql = "select id, t_publish_date, t_comment from hunter_timeline order by id desc limit $from_idx, $page_size";
$result = query($db, $sql);
closeConnection($db);
$str = "";
if ($from == "mobile") {
	$str = "{\"data\":[";
}
while (list($id, $pd, $c)=mysql_fetch_row($result)) {
	if ($from == "site") {
		$str = $str."<tr><td>$id</td><td>$pd</td><td>$c</td><td><input type='button' value='修改' onclick='doEdit($id);' class='btn btn-primary'>&nbsp;&nbsp;<input type='button' value='删除' onclick='doDelete($id);' class='btn btn-primary'></td></tr>";
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
