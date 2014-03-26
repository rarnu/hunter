<?php

include "../database/database.php";

$from=$_POST["_from"];
$id=$_POST["_id"];
$publish_date=$_POST["_publish_date"];
$comment=$_POST["_comment"];

$ret = doEditTimeline(
    $id,
	$publish_date,
	$comment
);

if ($ret == 1) {
	if ($from == "site") {
		header("Location: ../admin/publish_timeline_result.php?stat=0&edit=1");
	} else {
		echo "{\"result\":0}";
	}
} else {
	if ($from == "site") {
		header("Location: ../admin/publish_timeline_result.php?stat=1&edit=1");
	} else {
		echo "{\"result\":1}";
	}
}

function doEditTimeline($id,$pd,$c) {
	$db = openConnection();
	$sql = "update hunter_timeline set t_publish_date='$pd', t_comment='$c' where id=$id";
	$result = query($db, $sql);
	closeConnection($db);
	return $result;
}

?>
