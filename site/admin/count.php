<?php
include "navbar.php";
session_start();
if (!isset($_SESSION["_user_id"])) {
        header("Location: login.php");
}
?>
<html>

<head>
<meta http-equiv="Content-Language" content="en-us">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content>
<meta name="author" content>
</head>

<body>
<div class="container">
	<div class="hero-unit">

	</div>
</div>
</body>

</html>

<?php
include "bottombar.php";

?>

