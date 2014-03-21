<?php
session_start();
unset($_SESSION["_user_id"]);
unset($_SESSION["_user_name"]);
session_destroy();
header("Location: ../page/jobs.php");
?>
