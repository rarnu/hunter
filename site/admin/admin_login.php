<?php

include "../database/database.php";

$user_name=$_POST["_user_name"];
$user_pwd=$_POST["_user_pwd"];
session_start();

$q_pwd = md5($user_pwd);
$db = openConnection();
$sql = "select * from hunter_user where t_user_name = '$user_name' and t_password = '$q_pwd'";
$result = query($db, $sql);
closeConnection($db);
$find_user = 0;
$is_admin = 0;
while (list($u_id, $u_name, $u_pwd, $u_admin) = mysql_fetch_row($result)) {
	$find_user = 1;
	$is_admin = $u_admin;
}
if ($find_user == 1) {
	if ($is_admin == 1) {
		unset($_SESSION["error"]);
		$_SESSION["_user"] = $user_name;
		header("Location: manage.php");
	} else {
		unset($_SESSION["_user"]);
		$_SESSION["error"] = "not_admin";
		header("Location: login.php");
	}
} else {
	unset($_SESSION["_user"]);
	$_SESSION["error"] = "no_user";
	header("Location: login.php");
}
?>
