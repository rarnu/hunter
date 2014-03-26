<?php
include "../database/database.php";

$from = $_GET["_from"];
$id = $_GET["_id"];

$db = openConnection($db);
$sql = "select * from hunter_timeline where id=$id";
$result = query($db, $sql);
closeConnection($db);

$str = "";
while (list($t_id, $t_publish_date, $t_comment)=mysql_fetch_row($result)) {
	if ($from == "site") {
		$t_comment = str_replace("\n", "<br>", $t_comment);		
		
		$str = "<table border='0' cellspacing='4px' cellpadding='4px' width='80%'>";
		$str = $str."<tr><td width='25%'><b>发布日期</b></td><td>$t_publish_date</td></tr>";
		$str = $str."<tr><td width='25%' valign='top'><b>文章内容</b></td><td>$t_comment</td></tr>";
		$str = $str."</table>";
	} else {
		$str = "{\"id\":$t_id,\"publish_date\":".json_encode($t_publish_date).",\"comment\":".json_encode($t_comment)."}";
	}	
}

echo $str;

?>
