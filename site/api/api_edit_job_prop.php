<?php
include "../database/database.php";

$id=$_GET["_id"];
$keywords=$_GET["_keywords"];
$key2=$_GET["_key2"];
$key3=$_GET["_key3"];
$key4=$_GET["_key4"];
$key5=$_GET["_key5"];
$emergency=$_GET["_emergency"];
$color=$_GET["_color"];
$status=$_GET["_status"];

$db = openConnection();
$sql = "update hunter_job set t_keywords='$keywords',key2='$key2', key3='$key3', key4='$key4', key5='$key5', t_emergency='$emergency', t_color='$color', t_status='$status' where id=$id";
$result = query($db, $sql);
closeConnection($db);
$str = "";
if ($result == 1) {
	$str = "{\"result\":0}";
} else {
	$str = "{\"result\":1}";
}
echo $str;
?>
