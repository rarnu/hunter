<?php
include "../database/database.php";

$from = $_GET["_from"];
$id = $_GET["_id"];

$db = openConnection($db);
$sql = "select * from hunter_job where id=$id";
$result = query($db, $sql);
closeConnection($db);

$str = "";
while (list($t_id, $t_company_name, $t_company_desc, $t_company_heads, $t_work_area, $t_work_years, $t_education, $t_in_heads, $t_publish_date, $t_end_date, $t_salary_range, $t_job_title, $t_job_accountability, $t_job_requirement, $t_keywords, $t_emergency, $t_tags, $t_color, $t_status, $key2, $key3, $key4, $key5)=mysql_fetch_row($result)) {
	if ($from == "site") {
		if ($t_company_desc == "" || $t_company_desc == "null" || empty($t_company_desc)) {
			$t_company_desc = "暂无";
		}
		if ($t_salary_range == "" || $t_salary_range == "null" || empty($t_salary_range)) {
			$t_salary_range = "面议";
		}
		if ($t_publish_date == "" || $t_publish_date == "null" || empty($t_publish_date)) {
			$t_publish_date = "长期招聘";
		}
		if ($t_end_date == "" || $t_end_date == "null" || empty($t_end_date)) {
			$t_end_date = "长期招聘";
		}
		$chstr = "保密";
		if ($t_company_heads == 1) { $chstr = "50人以下"; }
		else if ($t_company_heads == 2) { $chstr = "50~100人"; }
		else if ($t_company_heads == 3) { $chstr = "100~500人"; }
		else if ($t_company_heads == 4) { $chstr = "500~1000人"; }
		else if ($t_company_heads == 5) { $chstr = "1000~5000人"; }
		else if ($t_company_heads == 6) { $chstr = "5000人以上"; }
		$wystr = "不限";
		if ($t_work_years == 1) { $wystr = "一年以上"; }
		else if ($t_work_years == 2) { $wystr = "二年以上"; }
		else if ($t_work_years == 3) { $wystr = "三年以上"; }
		else if ($t_work_years == 4) { $wystr = "五年以上"; }
		else if ($t_work_years == 5) { $wystr = "八年以上"; }
		else if ($t_work_years == 6) { $wystr = "十年以上"; }
		$edustr = "不限";
		if ($t_education == 1) { $edustr = "大专以上"; }
		else if ($t_education == 2) { $edustr = "本科以上"; }
		else if ($t_education == 3) { $edustr = "硕士以上"; }
		else if ($t_education == 4) { $edustr = "海外留学"; }

		$t_company_desc = str_replace("\n", "<br>", $t_company_desc);		
		$t_job_accountability = str_replace("\n", "<br>", $t_job_accountability);
		$t_job_requirement = str_replace("\n", "<br>", $t_job_requirement);
		
		$str = "<table border='0' cellspacing='4px' cellpadding='4px' width='100%'>";
		$str = $str."<tr><td width='25%'><b>公司名称</b></td><td colspan='3'>$t_company_name</td></tr>";
		$str = $str."<tr><td width='25%' valign='top'><b>公司简介</b></td><td colspan='3'>$t_company_desc</td></tr>";
		$str = $str."<tr><td width='25%'><b>公司规模</b><td width='25%'>$chstr</td><td width='25%'><b>招聘人数</b></td><td width='25%'>$t_in_heads</td></tr>";
		$str = $str."<tr><td width='25%'><b>工作地区</b></td><td width='25%'>$t_work_area</td><td width='25%'><b>工作年限</b></td><td>$wystr</td></tr>";
		$str = $str."<tr><td width='25%'><b>学历要求</b></td><td width='25%'>$edustr</td><td width='25%'><b>薪资范围</b></td><td>$t_salary_range</td></tr>";
		$str = $str."<tr><td width='25%'><b>发布日期</b></td><td width='25%'>$t_publish_date</td><td width='25%'><b>结束日期</b></td><td width='25%'>$t_end_date</td></tr>";
		$str = $str."<tr><td width='25%'><b>职位名称</b></td><td colspan='3'><b>$t_job_title</b></td></tr>";
		$str = $str."<tr><td width='25%' valign='top'><b>岗位职责</b></td><td colspan='3'>$t_job_accountability</td></tr>";
		$str = $str."<tr><td width='25%' valign='top'><b>任职要求</b></td><td colspan='3'>$t_job_requirement</td></tr>";
		$str = $str."</table>";
	} else {
		$str = "{\"id\":$t_id,\"company_name\":".json_encode($t_company_name).",\"company_desc\":".json_encode($t_company_desc).",\"company_heads\":$t_company_heads,\"work_area\":".json_encode($t_work_area).",\"work_years\":$t_work_years,\"education\":".json_encode($t_education).",\"in_heads\":$t_in_heads,\"publish_date\":".json_encode($t_publish_date).",\"end_date\":".json_encode($t_end_date).",\"salary_range\":".json_encode($t_salary_range).",\"job_title\":".json_encode($t_job_title).",\"job_accountability\":".json_encode($t_job_accountability).",\"job_requirement\":".json_encode($t_job_requirement).",\"keywords\":".json_encode($t_keywords).",\"emergency\":$t_emergency,\"tags\":".json_encode($t_tags).",\"color\":$t_color,\"status\":$t_status}";
	}	
}

echo $str;

?>
