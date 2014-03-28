<?php 
include "../database/database.php";

$from=$_POST["_from"];
$id=$_POST["_id"];
$mail_work=$_POST["_mail_work"];
$mail_private=$_POST["_mail_private"];
$qq=$_POST["_qq"];
$wx=$_POST["_wx"];
$hangouts=$_POST["_hangouts"];
$phone_work=$_POST["_phone_work"];
$phone_private=$_POST["_phone_private"];
$address=$_POST["_address"];

$db = openConnection();
$sql = "update hunter_contact set t_mail_work='$mail_work', t_mail_private='$mail_private',t_qq='$qq', t_wx='$wx', t_hangouts='$hangouts', t_phone_work='$phone_work', t_phone_private='$phone_private', t_address='$address' where id=$id";
$result = query($db, $sql);
closeConnection($db);

if ($from == "site") {
	if ($result == 1) {
		header("Location: ../admin/data_result.php?stat=0");
	} else {
		header("Location: ../admin/data_result.php?stat=1");
	}
} else {
	if ($result == 1) {
		echo "{\"result\":0}";
	} else {
		echo "{\"result\":1}";
	}
}

?>
