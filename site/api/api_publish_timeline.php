<?php

include "../database/database.php";

$from=$_POST["_from"];
$publish_date=$_POST["_publish_date"];
$comment=$_POST["_comment"];

$ret = doPublishTimeline(
	$publish_date,
	$comment
);

if ($ret == 1) {
	if ($from == "site") {
		header("Location: ../admin/publish_timeline_result.php?stat=0");
	} else {
		echo "{\"result\":0}";
	}
} else {
	if ($from == "site") {
		header("Location: ../admin/publish_timeline_result.php?stat=1");
	} else {
		echo "{\"result\":1}";
	}
}

function doPublishTimeline($pd,$c) {
	$db = openConnection();
	$sql = "insert into hunter_timeline (t_publish_date, t_comment) values ('$pd','$c')";
	$result = query($db, $sql);
	closeConnection($db);
	return $result;
}

?>
