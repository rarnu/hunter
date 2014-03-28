<?php
// t_type int default 0, t_device text, t_os text, t_app text, t_accounts text, t_publish_date text, t_comment text
include "../database/database.php";

$type=$_POST["_type"];
$device=$_POST["_device"];
$os=$_POST["_os"];
$app=$_POST["_app"];
$accounts=$_POST["_accounts"];
$publish_date=$_POST["_publish_date"];
$comment=$_POST["_comment"];

$db=openConnection();
$sql = "insert into hunter_feedback (t_type,t_device,t_os,t_app,t_accounts,t_publish_date,t_comment) values ($type,'$device','$os','$app','$accounts','$publish_date','$comment')";
$result = query($db, $sql);
closeConnection($db);

?>
