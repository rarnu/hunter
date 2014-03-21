<?php 
include "../database/database.php";
$db = openConnection();
$sql = "select * from hunter_contact";
$result = query($db, $sql);
closeConnection($db);
$str = "";

while (list($t_id, $t_mail_work, $t_mail_private, $t_qq, $t_wx, $t_hangouts, $t_phone_work, $t_phone_private, $t_address)=mysql_fetch_row($result)) {
	$str = "{\"id\":$t_id,\"mail_work\":\"$t_mail_work\",\"mail_private\":\"$t_mail_private\",\"qq\":\"$t_qq\",\"wx\":\"$t_wx\",\"hangouts\":\"$t_hangouts\",\"phone_work\":\"$t_phone_work\",\"phone_private\":\"$t_phone_private\",\"address\":".json_encode($t_address)."}";
	break;
}
echo $str;

?>
