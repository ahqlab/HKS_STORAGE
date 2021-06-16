<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/layout/tagLib.jsp" %>
<script>
function submit_stg_regist(){
	
	if($('#beforePassword').val() == ''){
		showModal('message', '이전 비밀번호를 입력하세요.');
	}else if($('#newPassword1').val() == ''){
		showModal('message', '변경 비밀번호를 입력하세요.');
	}else if($('#newPassword2').val() == ''){
		showModal('message', '변경 비밀번호 확인을 입력하세요.');
	}else if($('#beforePassword').val() == $('#newPassword1').val()){
		showModal('message', '새로운 비밀번호를 입력하세요.');
	}else if($('#beforePassword').val() == $('#newPassword2').val()){
		showModal('message', '새로운 비밀번호를 입력하세요.');
	}else if($('#newPassword1').val() != $('#newPassword2').val()){
		showModal('message', '비밀번호가 맞지 않습니다.');
	}else{
		regist_check();
	}
}

function regist_check(beforePassword, newPassword1, newPassword2){
	jQuery.ajax({
  		type : "POST",
  		url : "${pageContext.request.contextPath}/user/change/info",
  		dataType : "JSON", // 옵션이므로 JSON으로 받을게 아니면 안써도 됨
  		data: {
  			userId : $('#userId').val(),
  			beforePassword : $('#beforePassword').val(),
  			newPassword1: $('#newPassword1').val(),
  			newPassword2: $('#newPassword2').val()
  	    },
  		success : function(data) {
  			alert(data.status);
  			if(data.status == 0){
  				alert(data.status);
  			}else if(data.status == 1){
  				alert(data.responseMessage);
  				move_url('${pageContext.request.contextPath}/logout');
  			}
  		},
  		complete : function(data) {
  		},
  		error : function(xhr, status, error) {
  		}
  	});
}

</script>
<form:form method="POST" commandName="domain">
	<fieldset>
		<div class="form-group">
			<label for="userId">아이디</label>
				<form:input readonly="true" path="userId" class="form-control" placeholder="유저아이디"/>
				<form:hidden path="userId"/>
		</div>
		<div class="form-group">
			<label for="beforePassword">이전 비밀번호</label>
				<form:input path="beforePassword" class="form-control" placeholder="이전 비밀번호"/>
		</div>
		<div class="form-group">
			<label for="newPassword1">변경 비밀번호</label>
				<form:input readonly="false" path="newPassword1" class="form-control" placeholder="변경 비밀번호"/>
		</div>
		<div class="form-group">
			<label for="newPassword2">변경 비밀번호 확인</label>
				<form:input readonly="false" path="newPassword2" class="form-control" placeholder="변경 비밀번호 확인"/>
		</div>
		<div class="col-md-12">
			<span class="pull-right">
				<button type="button" class="btn btn-primary mb-2" onclick="return submit_stg_regist();">수정</button>
				<button type="button" class="btn btn-secondary" onclick="javascript:move_url('${pageContext.request.contextPath}/disability/list?pIdx=${pIdx}');">확인</button>
			</span>
		</div>
	</fieldset>
</form:form>
<!-- Modal -->
<div class="modal fade" id="newModal" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">×</button>
				<h4 class="modal-title">Sample Modal</h4>
			</div>
			<div class="modal-body">
				<p>This is demo text.</p>
			</div>
		</div>
	</div>
</div>

<script>
function showModal(title, content) {
	var modal = $('#newModal');
	modal.find('.modal-body p').text(content);
	modal.find('.modal-header h4').html(title);
	//modal.find('.modal-footer a.btn').text('Remove');
	$('#newModal').modal('show');
	$("#button1").click(function() {
		$("#newModal").modal("hide");
	});
};
</script>