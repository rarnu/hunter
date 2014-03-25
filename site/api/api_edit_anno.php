<?php
include "../database/database.php";

$from=$_POST["_from"];
$comment=$_POST["_comment"];
$db = openConnection();
$sql = "update hunter_anno set t_comment='$comment' where id=0";
$result = query($db, $sql);
closeConnection($db);
if ($from=="site") {
	if ($result==1) {
		header("Location: ../admin/my_result.php?stat=0");
	} else {
		header("Location: ../admin/my_result.php?stat=1");
	}
} else {
	if ($result==1) {
		echo "{\"result\":0}";
	} else {
		echo "{\"result\":1}";
	}
}

?>
