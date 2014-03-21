<?php
include "navbar.php";

?>
<html>

<head>
<meta http-equiv="Content-Language" content="en-us">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content>
<meta name="author" content>

<script type="text/javascript">
function showWxQr() {
	$("#_wx_qr").modal("toggle");
}
</script>

</head>

<body>
<div class="container">
		<div class="panel panel-default">
			<div class="panel-heading"><font size="3">Vicky: 专注于互联网和游戏职位</font></div>
  			<table class="table" width='100%'>
				<tr><td width='25%'><b>邮件联系</b></td><td width='75%'><table border="0"><tr><td>工作时间请联系</td><td id="_mail_work"></td></tr><tr><td>非工作时请联系</td><td id="_mail_private"></td></table></td></tr>
				<tr><td width='25%'><b>在线联系</b></td><td width='75%'><table border="0"><tr><td>QQ</td><td><a href="http://web.qq.com"><span id="_qq"></span></a></td></tr><tr><td>Hangouts</td><td><a href="http://www.google.com/hangouts/"><span id="_hangouts"></span></a></td></tr><tr><td><span style="padding-top:16px;">微信</span></td><td><span id="_wx" style="padding-top:16px;"></span>&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" class="btn" value="二维码" onclick="showWxQr();"></td></tr></table></td></tr>
				<tr><td width='25%'><b>电话联系</b></td><td width='75%'><table border="0"><tr><td>工作时间请联系</td><td id="_phone_work"></td></tr><tr><td>非工作时请联系</td><td id="_phone_private"></td></tr></table></td></tr>
				<tr><td width='25%'><b>上门联系</b></td><td width='75%'><span id="_address"></span><br><img src="../image/map.png" width="479px" height="311px"/></td></tr>
 			</table>
		</div>
</div>

<div id="_wx_qr" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="width:320px;" align="center" valign="middle">
        <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h3 id="myModalLabel">微信二维码</h3>
        </div>
        <div class="modal-body" id="_job_detail_body" name="_job_detail_body">
                <img src="../image/qrcode.jpg" width="300px" height="300px" />
        </div>
        <div class="modal-footer">
                <button class="btn" data-dismiss="modal">关闭</button>
        </div>
</div>

<script type="text/javascript">
function returnJson(data) {
	$("#_mail_work")[0].innerHTML = "<a href='mailto:"+data.mail_work+"'>"+data.mail_work+"</a>";
	$("#_mail_private")[0].innerHTML = "<a href='mailto:"+data.mail_private+"'>"+data.mail_private+"</a>";
	$("#_qq")[0].innerHTML = data.qq;
	$("#_hangouts")[0].innerHTML = data.hangouts;
	$("#_wx")[0].innerHTML = data.wx;
	$("#_phone_work")[0].innerHTML = "<a href='tel:"+data.phone_work+"'>"+data.phone_work+"</a>";
	$("#_phone_private")[0].innerHTML = "<a href='tel:"+data.phone_private+"'>"+data.phone_private+"</a>";
	$("#_address")[0].innerHTML = data.address;
}

$.getJSON("../api/api_query_data.php", returnJson);
</script>

</body>

</html>

<?php
include "bottombar.php";

?>

