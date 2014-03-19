<?php
$user_name=$_POST["_user_name"];
$user_pwd=$_POST["_user_pwd"];
echo "name:${user_name}, pwd:${user_pwd}";
session_start();

if ($user_name == "vicky") {
	unset($_SESSION["error"]);
	$_SESSION["_user"] = $user_name;
	header("Location: publish.php");
} else {
	unset($_SESSION["_user"]);
	$_SESSION["error"] = "error";
	header("Location: login.php");
}

?>
