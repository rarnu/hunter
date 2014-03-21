<?php

include "../database/database.php";
$db = openConnection();
$sql = "select sum(t_view_count) from hunter_job";
$result = query($db, $sql);
closeConnection($db);
$count = 0;
while (list($c)=mysql_fetch_row($result)) {
	$count = $c;
}
$str = "{\"result\":$count}";
echo $str;

?>
