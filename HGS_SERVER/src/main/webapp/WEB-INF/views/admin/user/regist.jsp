<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/layout/tagLib.jsp"%>
<script>

$(document).ready(function(e){
	$("#pageTitle").text('사용자 등록');
});

function submit_stg_regist(){
	
	if($('#userId').val() == 0){
		showModal('message', '아이디를 입력해주세요.');
		return false;
	}else if($('#password').val() == ''){
		showModal('message', '비밀번호를 입력하세요.');
		return false;
	}else if($('#newPassword1').val() == ''){
		showModal('message', '확인 비밀번호를 입력하세요.');
		return false;
	}else if($('#newPassword1').val() != $('#password').val()){
		showModal('message', '비밀번호가 맞지 않습니다.');
		return false;
	}else if($('#company').val() == ''){
		showModal('message', '회사명을 입력하세요.');
		return false;
	}else if($('#serviceEmail').val() == ''){
		showModal('message', '서비스 이메일 주소를 입력하세요.');
		return false;
	}else if($('#serviceEmailPassword').val() == ''){
		showModal('message', '서비스 이메일 주소의 비밀번호를 입력하세요.');
		return false;
	}
	return true;
}


function roleChange(value){
	if(value == 0){
		$('#roleKorNm').val('슈퍼관리자');
	}else if(value > 0){
		$('#roleKorNm').val('일반관리자');
	}
}
</script>
<form:form method="POST" commandName="domain" action="${pageContext.request.contextPath}/admin/user/regist2">
	<fieldset>
		<div class="form-group">
			<label for="ctmIdx">■ 권한 선택</label>
			<form:select path="role" onchange="javascript:roleChange(this.value);" class="form-control">
				<form:option value="1">일반관리자</form:option>
				<form:option value="0">슈퍼관리자</form:option>
			</form:select>
			<form:hidden path="roleKorNm" value="슈퍼관리자"/>
		</div>
		<div class="form-group">
			<label for="modelName">■ 아이디</label> 
			<form:input path="userId" class="form-control" placeholder="아이디"/>
		</div>
		<div class="form-group">
			<label for="password">■ 비밀번호</label>
				<form:password path="password" class="form-control" placeholder="비밀번호"/>
		</div>
		<div class="form-group">
			<label for="newPassword1">■ 비밀번호 확인</label>
				<form:password readonly="false" path="newPassword1" class="form-control" placeholder="비밀번호 확인"/>
		</div>
		<div class="form-group">
			<label for="company">■ 회사명 </label>
			<form:input path="company" class="form-control" placeholder="회사명"/>
		</div>
		<div class="form-group">
			<label for="serviceEmail">■ 서비스 이메일 </label>
			<form:input path="serviceEmail" class="form-control" placeholder="서비스 이메일"/>
		</div>
		<div class="form-group">
			<label for="serviceEmailPassword">■ 서비스 이메일 비밀번호 </label>
			<form:password path="serviceEmailPassword" class="form-control" placeholder="서비스 이메일 비밀번호"/>
		</div>
		<div class="col-md-12">
			<span class="pull-right">
				<button type="submit" class="btn btn-primary mb-2" onclick="return submit_stg_regist();">등록</button>
				<button type="button" class="btn btn-secondary" onclick="javascript:history.back(-1);">취소</button>
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