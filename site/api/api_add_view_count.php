<?php

include "../database/database.php";
$id=$_GET["_id"];

$db = openConnection();
$sql = "update hunter_job set t_view_count = t_view_count + 1 where id=$id";
query($db, $sql);
closeConnection($db);

?>
