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
function doSubmit() {
	var ret = true;
	if (document.getElementById("_company_name").value == "") {
		alert("公司名称（描述）不能为空");
		ret = false;
	} else if (document.getElementById("_work_area").value == "") {
		alert("工作地区不能为空");
		ret = false;
	} else if (document.getElementById("_in_heads").value == "") {
		alert("招聘人数不能为空");
		ret = false;
	} else if (document.getElementById("_job_title").value == "") {
		alert("职位名称不能为空");
		ret = false;
	}
	return ret;
}
</script>

</head>

<body>
<div class="container">
	<div class="hero-unit" align="center">
		<div align="center">修改职位信息</div><br>
		<form method="POST" action="../api/api_edit.php" class="form-horizontal" onsubmit="return doSubmit();">
		<input type="hidden" name="_from" value="site">
        <input type="hidden" name="_id" id="_id" value="">
		<table border="0" align="center" cellspacing="4px" cellpadding="4px">
		<tr><td>公司名称(描述)<font color="red">*</font></td><td><input type="text" size="100" name="_company_name" id="_company_name" class="input-block-level"></td></tr>
		<tr><td>公司简介</td><td><textarea rows="5" style="width:100%;" name="_company_desc" id="_company_desc"></textarea></td></tr>
		<tr><td>公司规模(人数)</td><td><select name="_company_heads" id="_company_heads" style="width:35%;"><option value="0" selected="true">保密</option><option value="1">50人以下</option><option value="2">50~100人</option><option value="3">100~500人</option><option value="4">500~1000人</option><option value="5">1000~5000人</option><option value="6">5000人以上</option></select></td></tr>
		<tr><td>工作地区<font color="red">*</font></td><td><input type="text" size="100" name="_work_area" id="_work_area" class="input-block-level"></td></tr>
		<tr><td>工作年限</td><td><select name="_work_years" id="_work_years" style="width:35%;"><option value="0" selected="true">不限</option><option value="1">一年以上</option><option value="2">二年以上</option><option value="3">三年以上</option><option value="4">五年以上</option><option value="5">八年以上</option><option value="6">十年以上</option></select></td></tr>
		<tr><td>学历要求</td><td><select name="_education" id="_education" style="width:35%;"><option value="0" selected="true">不限</option><option value="1">大专以上</option><option value="2">本科以上</option><option value="3">硕士以上</option><option value="4">海外留学</option></select></td></tr>
		<tr><td>招聘人数<font color="red">*</font></td><td><input type="text" ime-mode:disabled" onkeydown="if(event.keyCode==13)event.keyCode=9" onKeypress="if ((event.keyCode<48 || event.keyCode>57)) event.returnValue=false" name="_in_heads" id="_in_heads" class="input-block-level"></td></tr>
		<tr><td>发布日期</td><td><input type="text" style="width:35%;" name="_publish_date" id="_publish_date" class="input-block-level" onfocus="HS_setDate(this);"></td></tr>
		<tr><td>结束日期</td><td><input type="text" style="width:35%;" name="_end_aate" id="_end_date" class="input-block-level" onfocus="HS_setDate(this);" ></td></tr>
		<tr><td>薪资范围</td><td><input type="text" size="100" name="_salary_range" id="_salary_range" class="input-block-level"></td></tr>
		<tr><td>职位名称<font color="red">*</font></td><td><input type="text" size="100" name="_job_title" id="_job_title" class="input-block-level"></td></tr>
		<tr><td>岗位职责</td><td><textarea rows="5" style="width:100%;" name="_job_accountability" id="_job_accountability"></textarea></td></tr>
		<tr><td>任职要求</td><td><textarea rows="5" style="width:100%;" name="_job_requirement" id="_job_requirement"></textarea></td>
		<tr><td colspan="2" align="center"><input type="submit" value="提交" class="btn btn-primary" style="width:20%;"></td></tr>
		</table>
		</form>
	</div>
</div>
<script src="../common/js/dateselect.js" ></script>
<script type="text/javascript">
var url = location.href;
var params = url.substr(url.indexOf('?')+1).split('=');
var id = params[1];

function returnJson(data) {
    // $("#_id")[0].value = data.id;
    document.getElementById("_id").value = data.id;
    $("#_company_name")[0].value = data.company_name;
    $("#_company_desc")[0].value = data.company_desc;
    $("#_company_heads")[0].value = data.company_heads;
    $("#_work_area")[0].value = data.work_area;
    $("#_work_years")[0].value = data.work_years;
    $("#_education")[0].value = data.education;
    $("#_in_heads")[0].value = data.in_heads;
    $("#_publish_date")[0].value = data.publish_date;
    $("#_end_date")[0].value = data.end_date;
    $("#_salary_range")[0].value = data.salary_range;
    $("#_job_title")[0].value = data.job_title;
    $("#_job_accountability")[0].value = data.job_accountability;
    $("#_job_requirement")[0].value = data.job_requirement;
 }

$.getJSON("../api/api_query_job_detail.php?_from=mobile&_id="+id, returnJson);

</script>
</body>

</html>

<?php
include "bottombar.php";
?>

