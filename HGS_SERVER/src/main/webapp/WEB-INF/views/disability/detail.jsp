<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/layout/tagLib.jsp"%>
<script>
$(document).ready(function(e){
	$("#pageTitle").text('상세정보');
});
</script>
<form:form method="POST" commandName="domain">
	<fieldset>
		<div class="form-group">
			<label for="ctmIdx">제목</label>
				<%-- <form:input readonly="true" disabled="disabled" 
				<%-- 	value="${domain.eventMame} ${domain.storeageName} ${domain.uId} ${domain.ip} ${domain.mailDate}"
				
				value="<c:out value="${domain.eventMame}"/>_${domain.storeageName}"
				path="status" class="form-control" placeholder="고객사 명"/> --%>
				<input type="text" value="<c:out value="${domain.eventName}"/> , <c:out value="${domain.storeageName}"/> , <c:out value="${domain.ip}"/> , <c:out value="${domain.mailDate}"/>" readonly="true" disabled="disabled" 	class="form-control" />
				
		</div>
		<div class="form-group">
			<label readonly="true" disabled="disabled"  for="exampleFormControlTextarea1">내용</label>
			<form:textarea path="body" class="form-control" rows="5"/>
	  	</div>
	</fieldset>
</form:form>
<div class="row">
	<div class="col-md-12">
		<span class="pull-right">
			<button type="button" class="btn btn-danger"
				onclick="javascript:confirm_delete('${pageContext.request.contextPath}/disability/delete?id=${domain.id}');">삭제</button>
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