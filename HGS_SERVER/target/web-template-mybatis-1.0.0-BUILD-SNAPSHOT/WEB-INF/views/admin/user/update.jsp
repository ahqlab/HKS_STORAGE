<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/layout/tagLib.jsp"%>
<script>
	$(document).ready(function(e){
		$("#pageTitle").text('사용자 상세정보');
	});
	function roleChange(value){
		if(value == 0){
			$('#roleKorNm').val('슈퍼관리자');
		}else if(value > 0){
			$('#roleKorNm').val('일반관리자');
		}
	}
	function submit_stg_regist() {
		if($('#userId').val() == ''){
			showModal('message', '고객사를 선택하세요.');
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
	
	function resetPassword(id){
		var userId = '${domain.userId}'.substring(0, 3);
		var password = userId + getMMdd() + '!!';
		jQuery.ajax({
	  		type : "GET",
	  		url : "${pageContext.request.contextPath}/admin/user/change/account?id=" + id + "&password=" + password,
	  		dataType : "JSON", // 옵션이므로 JSON으로 받을게 아니면 안써도 됨
	  		success : function(data) {
	  			if(data){
	  				alert('비밀번호 초기화에 성공하였습니다.!');
	  			}
	  		},
	  		complete : function(data) {
	  			//alert('비밀번호 초기화에 실패하였습니다. 계속 될 경우 관리자에게 문의하세요.');
	  		},
	  		error : function(xhr, status, error) {
	  			alert('비밀번호 초기화에 실패하였습니다. 계속 될 경우 관리자에게 문의하세요.');
	  		}
	  	});
	}
	
	function getMMdd(){
		var today = new Date();
		var dd = today.getDate();
		var mm = today.getMonth()+1; //January is 0!
		return pad(mm, 2) + "" + pad(dd, 2);
	}
	
	function pad(n, width) {
		  n = n + '';
		  return n.length >= width ? n : new Array(width - n.length + 1).join('0') + n;
	}
	
</script>
<form:form method="POST" commandName="domain">
	<fieldset>
		<div class="form-group">
			<label for="ctmIdx">■ 권한 선택</label>
			<form:select path="role" onchange="javascript:roleChange(this.value);" class="form-control">
				<form:option value="1">일반관리자</form:option>
				<form:option value="0">슈퍼관리자</form:option>
			</form:select>
			<form:hidden path="roleKorNm" value="슈퍼관리자"/>
		</div>
		<div class="form-group row">
			<div class="col-xs-12">
			<label for="ctmIdx">■ 아이디</label>
			</div>
			<div class="col-xs-10">
				<form:input path="userId" class="form-control" placeholder="아이디"/>
			</div>
			<div class="col-xs-2">
				<button type="button" class="btn btn-primary mb-2" onclick="javascript:resetPassword(${domain.id});">비밀번호 초기화</button>
			</div>
			<div class="col-xs-12">
			비밀번호 초기화 시 아이디앞 3글자 + 월일(0225) + !! 포멧으로 초기화 됩니다.
			</div>
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
				<button type="submit" class="btn btn-primary mb-2" onclick="return submit_stg_regist();">수정</button>
				<button type="button" class="btn btn-danger" onclick="javascript:confirm_delete('${pageContext.request.contextPath}/admin/user/delete?id=${domain.id}');">삭제</button>
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