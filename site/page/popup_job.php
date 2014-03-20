<script type="text/javascript">
	function returnDetailData(data) {
		$("#_job_detail_body")[0].innerHTML = data;
	}
	function loadDetailData(id) {
		$.get("../api/api_query_job_detail.php?_from=site&_id="+id, returnDetailData);
	}
</script>
<div id="_job_detail" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-header">
        	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        	<h3 id="myModalLabel">职位详情</h3>
        </div>
        <div class="modal-body" id="_job_detail_body" name="_job_detail_body">
		<!-- 内容 -->
	</div>
	<div class="modal-footer">
		<button class="btn btn-primary">保存</button>
		<button class="btn" data-dismiss="modal">关闭</button>
	</div>
</div>

