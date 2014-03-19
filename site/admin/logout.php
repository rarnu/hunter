<?php
session_start();
unset($_SESSION["_user"]);
session_destroy();
header("Location: ../page/jobs.php");
?>
