<?php

include "../database/database.php";

$from = $_GET["_from"];
$page = $_GET["_page"];
$type = $_GET["_type"];

$db = openConnection();
$page--;
$page_size = 10;
$from_idx = $page * $page_size;
$sql = "select * from hunter_feedback where t_type=$type order by id desc limit $from_idx, $page_size";
$result = query($db, $sql);
closeConnection($db);
$str = "";
if ($from == "mobile") {
	$str = "{\"data\":[";
}
while (list($id, $t, $d, $o,$a, $acc, $pd, $c)=mysql_fetch_row($result)) {
	if ($from == "site") {	
		$acc = str_replace("\n","<br>",$acc);
		$str = $str."<tr><td>$id</td><td>$d($o|$a)</td><td>$acc</td><td>$pd</td>";
		if ($type == 1) {
			$str = $str."<td>$c</td>";
		}
		$str = $str."</tr>";
	} else {
		$str = $str."{\"id\":$id,\"type\":$t,\"device\":".json_encode($d).",\"os\":".json_encode($o).",\"app\":".json_encode($a).",\"accounts\":".json_encode($acc).",\"publish_date\":".json_encode($pd).",\"comment\":".json_encode($c)."},";
	}
}
if ($from == "mobile") {
	$str = rtrim($str, ",");
	$str = $str."]}";
}

echo $str;

?>
