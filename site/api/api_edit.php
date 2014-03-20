<?php

include "../database/database.php";

$from=$_POST["_from"];
$id=$_POST["_id"];
$company_name=$_POST["_company_name"];
$company_desc=$_POST["_company_desc"];
$company_heads=$_POST["_company_heads"];
$work_area=$_POST["_work_area"];
$work_years=$_POST["_work_years"];
$education=$_POST["_education"];
$in_heads=$_POST["_in_heads"];
$publish_date=$_POST["_publish_date"];
$end_date=$_POST["_end_aate"];
$salary_range=$_POST["_salary_range"];
$job_title=$_POST["_job_title"];
$job_accountability=$_POST["_job_accountability"];
$job_requirement=$_POST["_job_requirement"];

$ret = doEdit(
    $id,
	$company_name,
	$company_desc,
	$company_heads,
	$work_area,
	$work_years,
	$education,
	$in_heads,
	$publish_date,
	$end_date,
	$salary_range,
	$job_title,
	$job_accountability,
	$job_requirement
);

if ($ret == 1) {
	if ($from == "site") {
		header("Location: ../admin/publish_result.php?stat=0&edit=1");
	} else {
		echo "{\"result\":0}";
	}
} else {
	if ($from == "site") {
		header("Location: ../admin/publish_result.php?stat=1&edit=1");
	} else {
		echo "{\"result\":1}";
	}
}

function doEdit($id,$cn,$cd,$ch,$wa,$wy,$e,$ih,$pd,$ed,$sr,$jt,$ja,$jr) {
	if (empty($ih)) {
		$ih = 0;
	}
	$db = openConnection();
	$sql = "update hunter_job set t_company_name='$cn', t_company_desc='$cd', t_company_heads=$ch, t_work_area='$wa', t_work_years=$wy, t_education='$e', t_in_heads=$ih, t_publish_date='$pd', t_end_date='$ed', t_salary_range='$sr', t_job_title='$jt', t_job_accountability='$ja', t_job_requirement='$jr' where id=$id";
	$result = query($db, $sql);
	closeConnection($db);
	return $result;
}

?>
