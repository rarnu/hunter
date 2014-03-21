<?php
include "../database/database.php";

$id = $_GET["_id"];

$db = openConnection();
$sql = "select id, t_keywords, key2, key3, key4, key5, t_emergency, t_color, t_status from hunter_job where id=$id";
$result = query($db, $sql);
closeConnection($db);
$str = "";
while (list($t_id, $t_keywords, $key2, $key3, $key4, $key5, $t_emergency,$t_color,$t_status)=mysql_fetch_row($result)) {
	$str = "{\"id\":$t_id,\"keywords\":".json_encode($t_keywords).",\"key2\":".json_encode($key2).",\"key3\":".json_encode($key3).",\"key4\":".json_encode($key4).",\"key5\":".json_encode($key5).",\"emergency\":$t_emergency,\"color\":$t_color,\"status\":$t_status}";
}
echo $str;

// t_keywords text, t_emergency int default 0, t_tags text, t_color int default 0, t_status

?>
