<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/layout/tagLib.jsp"%>
<script>
	$(document).ready(function(e){
		$("#pageTitle").text('사용자 상세정보');
	});
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
				<button type="button" class="btn btn-primary mb-2">비밀번호 초기화</button>
			</div>
			<div class="col-xs-12">
			비밀번호 초기화 시 아이디앞 3글자 + 월일(0225) + !! 포멧으로 초기화 됩니다.
			</div>
		</div>
		<div class="form-group">
			<label for="company">■ 회사명 </label>
			<form:input path="company" class="form-control" placeholder="회사명"/>
		</div>
	</fieldset>
</form:form>
<div class="row">
	<div class="col-md-12">
		<span class="pull-right">
			<button type="button" class="btn btn-secondary" onclick="javascript:move_url('${pageContext.request.contextPath}/admin/user/update?id=${domain.id}');">수정</button>
			<button type="button" class="btn btn-secondary" onclick="javascript:confirm_delete('${pageContext.request.contextPath}/admin/user/delete?id=${domain.id}');">삭제</button>
		</span>
	</div>
</div>
<!-- Modal -->
