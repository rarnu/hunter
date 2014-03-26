<?php

include "../database/database.php";

$user_name=$_POST["_user_name"];
$user_pwd=$_POST["_user_pwd"];

$q_pwd = md5($user_pwd);
$db = openConnection();
$sql = "select * from hunter_user where t_user_name = '$user_name' and t_password = '$q_pwd'";
$result = query($db, $sql);
closeConnection($db);
$find_user = 0;
$is_admin = 0;
$user_id = -1;
while (list($u_id, $u_name, $u_pwd, $u_admin) = mysql_fetch_row($result)) {
	$find_user = 1;
	$user_id = $u_id;
	$is_admin = $u_admin;
}
if ($find_user == 1 && $is_admin == 1) {
	echo "{\"result\":0,\"id\":$user_id,\"account\":$user_name}";
} else {
	echo "{\"result\":1}";
}
?>
