<?php

include "../database/database.php";

$id=$_GET["_id"];

$db = openConnection();
$sql = "delete from hunter_job where id=$id";
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
