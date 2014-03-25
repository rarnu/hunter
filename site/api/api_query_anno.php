<?php

include "../database/database.php";
$from = $_GET["_from"];
$db = openConnection();
$sql = "select * from hunter_anno where id=0";
$result = query($db, $sql);
closeConnection($db);
$str = "";
while (list($id,$t_comment)=mysql_fetch_row($result)) {
	if ($from=="site") {
		$t_comment = str_replace("\n", "<br>", $t_comment);
	}
	$str = "{\"id\":$id,\"comment\":".json_encode($t_comment)."}";
	break;
}
echo $str;

?>
