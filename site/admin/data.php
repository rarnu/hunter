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

<script type="text/javascript">
function checkPwd() {
	var oldPwd = $("#_current_pwd")[0].value;
	var newPwd = $("#_new_pwd")[0].value;
	var newRepeatPwd = $("#_repeat_new_pwd")[0].value;
	var ret = true;
	if (oldPwd == "" || newPwd == "" || newRepeatPwd == "") {
		$("#_msg_text")[0].innerHTML = "密码不能为空";
		$("#_alert_message").modal("toggle");
		ret = false;
	} else if (newPwd != newRepeatPwd) {
		$("#_msg_text")[0].innerHTML = "两次输入的密码不一致";
		$("#_alert_message").modal("toggle");
		ret = false;
	}
	return ret;
}
</script>
</head>

<body>
<div class="container">
	<div class="hero-unit" align="center">
		<form method="POST" action="../api/api_edit_password.php" class="form-horizontal" onsubmit="return checkPwd();">
			<input type="hidden" name="_from" value="site">
			<?php
			$u_id = $_SESSION["_user_id"];
			echo "<input type='hidden' name='_id' value='$u_id'>";
			?>
			<table border="0" align="center" cellspacing="4px" cellpadding="4px">
				<tr><td colspan="2">管理员登录密码修改</td></tr>
				<tr><td>当前密码</td><td><input type="password" name="_current_pwd" id="_current_pwd" size="100" class="input-block-level"></td></tr>
				<tr><td>新密码</td><td><input type="password" name="_new_pwd" id="_new_pwd" size="100" class="input-block-level"></td></tr>
				<tr><td>重复新密码</td><td><input type="password" name="_repeat_new_pwd" id="_repeat_new_pwd" size="100" class="input-block-level"></td></tr>
				<tr><td colspan="2" align="right"><input type="submit" value="提交" class="btn btn-primary" style="width:80px;"></td></tr>
				
			</table>
		</form>
		<hr>
		<form method="POST" action="../api/api_edit_data.php" class="form-horizontal">
			<input type="hidden" name="_from" value="site">
			<input type="hidden" name="_id" value="0" >
			<table border="0" align="center" cellspacing="4px" cellpadding="4px">
				<tr><td colspan="2">联系方式数据修改</td></tr>
				<tr><td>工作邮箱</td><td><input type="text" name="_mail_work" id="_mail_work" size="100" class="input-block-level"></td></tr>	
				<tr><td>私人邮箱</td><td><input type="text" name="_mail_private" id="_mail_private" size="100" class="input-block-level"></td></tr>	
				<tr><td>QQ 号</td><td><input type="text" name="_qq" id="_qq" size="100" class="input-block-level"></td></tr>	
				<tr><td>微信号</td><td><input type="text" name="_wx" id="_wx" size="100" class="input-block-level"></td></tr>	
				<tr><td>Hangouts</td><td><input type="text" name="_hangouts" id="_hangouts" size="100" class="input-block-level"></td></tr>	
				<tr><td>工作电话</td><td><input type="text" name="_phone_work" id="_phone_work" size="100" class="input-block-level"></td></tr>	
				<tr><td>私人电话</td><td><input type="text" name="_phone_private" id="_phone_private" size="100" class="input-block-level"></td></tr>	
				<tr><td>公司地址</td><td><input type="text" name="_address" id="_address" size="100" class="input-block-level"></td></tr>	
				<tr><td colspan="2" align="right"><input type="submit" value="提交" class="btn btn-primary" style="width:80px;"></td></tr>
				<tr><td colspan="2" align="right"><font color="red">若要修改微信二维码或者地图，请联系开发工程师</font></td></tr>
			</table>
		</form>
	</div>
</div>

<?php
include "alert_message.php";
?>

<script type="text/javascript">
function returnJson(data) {
	$("#_mail_work")[0].value = data.mail_work;
	$("#_mail_private")[0].value = data.mail_private;
	$("#_qq")[0].value = data.qq;
	$("#_wx")[0].value = data.wx;
	$("#_hangouts")[0].value = data.hangouts;
	$("#_phone_work")[0].value = data.phone_work;
	$("#_phone_private")[0].value = data.phone_private;
	$("#_address")[0].value = data.address;
}
$.getJSON("../api/api_query_data.php", returnJson);
</script>
</body>

</html>

<?php
include "bottombar.php";

?>

