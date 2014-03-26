<script type="text/javascript">
var callbackFunction = null;
var innerId = -1;
function setInnerId(id) {
	innerId = id;
}
function doCallback() {
	if (callbackFunction != null) {
		callbackFunction(innerId);
		$("#_alert_confirm").modal("hide");
	}
}
</script>

<div id="_alert_confirm" class="modal hide" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-header">
        	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        	<h3 id="myModalLabel">确认</h3>
        </div>
        <div class="modal-body">
		<p><span id="_msg_confirm" name="_msg_text"></span></p>
	</div>
	<div class="modal-footer">
		<button class="btn btn-primary" onclick="doCallback();">确认</button>
		<button class="btn" data-dismiss="modal">取消</button>
	</div>
</div>

