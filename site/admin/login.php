<html>

<head>
<meta http-equiv="Content-Language" content="en-us">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content>
<meta name="author" content>
</head>

<body>
	<script type="text/javascript">
		function doreset() {
			document.getElementById("_user_name").value = "";
			document.getElementById("_user_pwd").value = "";
			document.getElementById("_error").innerHTML = "";
		}
	</script>
	<form action="admin_login.php" method="post">
	<table border="0" align="center" valign="middle">
		<tr><td colspan="2" align="center"><b>管理员登录</b></td></tr>
		<tr><td>用户名</td><td><input type="text" size="20" name="_user_name" id="_user_name"></td></tr>
		<tr><td>密码</td><td><input type="password" size="20" name="_user_pwd" id="_user_pwd"></td></tr>
		<tr><td colspan="2" align="center"><input type="submit" value="提交"><input type="button" value="重置" onclick="doreset();"></td></tr>
		<tr><td colspan="2" align="center" id="_error"><font color="red">
		<?php
		session_start();
		if (isset($_SESSION["error"])) {
			unset($_SESSION["error"]);
			echo "用户名或密码错误，请重新输入";
		}
		?>
		</font></td></tr>
	</table>
	</form>
	
</body>

</html>

