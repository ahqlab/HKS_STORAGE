<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/layout/tagLib.jsp" %>
<script>
function submit_stg_regist(){
	
	if($('#customerName').val() == ''){
		showModal('message', '고객사 명을 입력하세요.');
		return false;
	}else if($('#email').val() == ''){
		showModal('message', '발송 E-Mail 주소를 입력하세요.');
		return false;
	}else if($('#managerName').val() == ''){
		showModal('message', '담당자 이름을 입력하세요.');
		return false;
	}else if($('#managerHP').val() == ''){
		showModal('message', '담당자 H.P을 선택하세요.');
		return false;
	}
	return true;
}
</script>
<form:form method="POST" commandName="domain" >
	<fieldset>
		<div class="form-group">
			<label for="storageSnInput">고객사 명</label>
				<form:input readonly="true" disabled="disabled" path="customerName" class="form-control" placeholder="고객사 명"/>
		</div>
		<div class="form-group">
			<label for="storageNameInput">발송 E-Mail 주소</label>  
			<div class="input-group">
	      		<span class="input-group-addon">@</span>
	      		<form:input readonly="true" disabled="disabled" path="email" class="form-control" placeholder="e-mail" aria-describedby="inputGroupSuccess3Status"/>
	    	</div>
		</div>
		<div class="form-group">
			<label for="storageNameInput">담당자 이름</label>
				<form:input readonly="true" disabled="disabled" path="managerName" class="form-control" placeholder="담당자 이름"/>
		</div>
		<div class="form-group">
			<label for="storageNameInput">담당자 H.P</label>
				<form:input readonly="true" disabled="disabled" path="managerHP" class="form-control" placeholder="담당자 H.P"/>
		</div>
		<div class="form-group">
			<label for="sendNumber">문자송신번호</label>
				<form:input readonly="true" disabled="disabled" path="sendNumber" class="form-control" placeholder="문자송신번호"/>
		</div>
		<!-- </div>
			<div class="col-md-12">
			<span class="pull-right">
				<button type="submit" class="btn btn-primary mb-2" onclick="return submit_stg_regist();">등록</button>
			</span>
		</div> -->
	</fieldset>
</form:form>
<div class="row">
	<div class="col-md-12">
		<span class="pull-right">
			<button type="button" class="btn btn-primary" onclick="javascript:move_url('${pageContext.request.contextPath}/customer/update?id=${domain.id}');">수정</button>
			<button type="button" class="btn btn-danger" onclick="javascript:confirm_delete('${pageContext.request.contextPath}/customer/all/delete?id=${domain.id}');">삭제</button>
			<button type="button" class="btn btn-secondary" onclick="javascript:history.back(-1);">취소</button>
		</span>
	</div>
</div>
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