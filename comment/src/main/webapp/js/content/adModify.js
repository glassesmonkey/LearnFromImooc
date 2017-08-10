$(function() {
	common.showMessage($("#message").val());
	$("#mainForm").validate({
		rules : {
			"title" : "required",
			"link" : "required",
			"weight" : {
				required : true,
				digits : true,
				maxlength : 6
			}
		},
		messages : {
			"title" : "请输入标题！",
				"weight" : {
					required : true,
					digits : "只能输入整数",
					maxlength : 6
				}
		}
	});
});

function modify() {
	$("#mainForm").submit();
}

function goback() {
	location.href = $('#basePath').val() + '/ad';
}