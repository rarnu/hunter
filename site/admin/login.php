<html>

<head>
<meta http-equiv="Content-Language" content="en-us">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content>
<meta name="author" content>
<link href="../common/bootstrap/css/bootstrap.css" rel="stylesheet">
<link href="../common/bootstrap/css/bootstrap-responsive.css" rel="stylesheet">
    <script src="../common/bootstrap/js/jquery.js"></script>
    <script src="../common/bootstrap/js/bootstrap-transition.js"></script>
    <script src="../common/bootstrap/js/bootstrap-alert.js"></script>
    <script src="../common/bootstrap/js/bootstrap-modal.js"></script>
    <script src="../common/bootstrap/js/bootstrap-dropdown.js"></script>
    <script src="../common/bootstrap/js/bootstrap-scrollspy.js"></script>
    <script src="../common/bootstrap/js/bootstrap-tab.js"></script>
    <script src="../common/bootstrap/js/bootstrap-tooltip.js"></script>
    <script src="../common/bootstrap/js/bootstrap-popover.js"></script>
    <script src="../common/bootstrap/js/bootstrap-button.js"></script>
    <script src="../common/bootstrap/js/bootstrap-collapse.js"></script>
    <script src="../common/bootstrap/js/bootstrap-carousel.js"></script>
    <script src="../common/bootstrap/js/bootstrap-typeahead.js"></script>
</head>

<body>
	<script type="text/javascript">
		function doreset() {
			document.getElementById("_user_name").value = "";
			document.getElementById("_user_pwd").value = "";
			document.getElementById("_error").innerHTML = "";
		}
	</script>
<div class="container">
	<div class="hero-unit">
	<form action="admin_login.php" method="post" class="form-horizontal">
	<table border="0" align="center" cellspacing='4px' cellpadding='4px'>
		<tr><td colspan="2" align="center"><b>管理员登录</b></td></tr>
		<tr><td width='25%'>用户名</td><td><input type="text" size="50" name="_user_name" id="_user_name" class="input-block-level"></td></tr>
		<tr><td width='25%'>密码</td><td><input type="password" size="50" name="_user_pwd" id="_user_pwd" class="input-block-level"></td></tr>
		<tr><td colspan="2" align="center"><input type="submit" value="提交" class="btn btn-primary" style="width:80px;">&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="重置" onclick="doreset();" class="btn btn-primary" style="width:80px;"></td></tr>
		<tr><td colspan="2" align="center" id="_error"><font color="red">
		<?php
		session_start();
		if (isset($_SESSION["error"])) {
			unset($_SESSION["error"]);
			echo "用户名或密码错误，或者该用户不是管理员";
		}
		?>
		</font></td></tr>
	</table>
	</form>
	</div>
</div>
</body>

</html>

