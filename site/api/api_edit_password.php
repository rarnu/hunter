<?php
include "../database/database.php";

$from=$_POST["_from"];
$id=$_POST["_id"];
$old_pwd=$_POST["_current_pwd"];
$new_pwd=$_POST["_new_pwd"];
echo "user:$id";
$md5_old = md5($old_pwd);
$md5_new = md5($new_pwd);

$db = openConnection();
$sql = "select id from hunter_user where id=$id and t_password='$md5_old'";
$result = query($db, $sql);
$find_user = 0;
$ret = 0;
while (list($t_id)=mysql_fetch_row($result)) {
	$find_user = 1;
	break;
}
if ($find_user == 1) {
	$sql = "update hunter_user set t_password='$md5_new' where id=$id and t_password='$md5_old'";
	$result = query($db, $sql);
	if ($result == 1) {
		$ret = 1;
	}
}
closeConnection();
if ($from == "site") {
	if ($ret == 1) {
		header("Location: ../admin/data_result.php?stat=0");
	} else {
		header("Location: ../admin/data_result.php?stat=1");
	}
} else {
	if ($ret == 1) {
		echo "{\"result\":0}";
	} else {
		echo "{\"result\":1}";
	}
}

?>
