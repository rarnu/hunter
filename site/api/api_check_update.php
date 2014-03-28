<?php

$version=$_GET["_version"];

// fill by administrator
$LAST_VERSION_CODE=1;
$LAST_VERSION_NAME="1.0";
$LAST_VERSION_DESC="";
$LAST_VESION_FILE="hunter_1.0.apk";

if ($version < $LAST_VERSION_CODE) {
	echo "{\"result\":0,\"code\":$LAST_VERSION_CODE,\"name\":".json_encode($LAST_VERSION_NAME).",\"desc\":".json_encode($LAST_VERSION_DESC).",\"file\":".json_encode($LAST_VESION_FILE)."}";
} else {
	echo "{\"result\":1}";
}

?>
