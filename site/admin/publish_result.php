<?php
include('navbar.php');

?>
<html>

<head>
<meta http-equiv="Content-Language" content="en-us">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content>
<meta name="author" content>
<link href="../../common/bootstrap/css/bootstrap.css" rel="stylesheet">
<link href="../../common/bootstrap/css/bootstrap-responsive.css" rel="stylesheet">

</head>

<body>
 <div class="container">
      <!-- Main hero unit for a primary marketing message or call to action -->
      <div class="hero-unit" align="center">

	<?php
	$stat=$_GET["stat"];
	if ($stat==0) {
        	echo "<div class=\"alert alert-success\" align=\"center\">";
		echo "发布职位成功";
	} else {
        	echo "<div class=\"alert alert-warning\" align=\"center\">";
		echo "发由职位失败";
	}
	?>
	</div>    
        <p>
          <a class="btn btn-large btn-success" href="publish.php">返回</a>
        </p>
             
      </div>

 </div>
</body>

</html>
