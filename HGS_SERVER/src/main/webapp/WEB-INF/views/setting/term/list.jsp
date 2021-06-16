<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/layout/tagLib.jsp"%>
<script>
$(document).ready(function(e){
	$("#pageTitle").text('스토리지');
});
</script>
<div class="row">
	<div class="col-xs-8 col-xs-offset-2">
    <form:form id="searchForm" commandName="domainParam" method="POST">
	    <div class="input-group">
	        <div class="input-group-btn">
	            <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
	                <span id="srch-category">선택</span> 
	                <span class="caret"></span>
	            </button>
	            <ul class="dropdown-menu" id="mnu-category">
	                <li><a at="eventName" value="eventName">이벤트명</a></li>
	                <li><a at="storeageName" value="storeageName">스토리지명</a></li>
	                <li><a at="ip" value="ip">아이피</a></li>
	            </ul>
	        </div>
	        <input type="hidden" id="txt-category">
	        <form:hidden path="currentPage" />
	        <form:hidden path="searchField" />
	        <form:input path="searchWord" class="form-control" placeholder="Search for..."/>
	        <span class="input-group-btn">
	            <button id="btn-search" type="submit" class="btn btn-default">
	                <span class="glyphicon glyphicon-search"></span>
	            </button>
	        </span>
	    </div>
	    </form:form>
	</div>
	<script>
		$(document).ready(function(e){
		    $('#mnu-category').find('a').click(function(e) {
		        e.preventDefault();
		        var cat = $(this).text();
		        var at = $(this).attr('at');
		        $('#srch-category').text(cat);
		        $('#searchField').val(at);
		    });
		});
		</script>
	<br>
	<br>
	<br>
</div>
<div class="table-responsive">
	<table class="table table-striped">
		<thead>
			<tr >
				<th>No</th>
				<th>고객사명</th>
				<th>모델명</th>
				<th>스토리지 S/N</th>
				<th>제품 SPEC</th>
				<th>설치일</th>
				<th>설치장소</th>
				<th>아이피</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="domain" items="${domainList}" varStatus="status">
				<tr onclick="javascript:move_url('${pageContext.request.contextPath}/storeage/detail?id=${domain.id}');">
					<td><c:out value="${status.index}" /></td>
					<td><c:out value="${domain.customerName}" /></td>
					<td><c:out value="${domain.modelName}" /></td>
					<td><c:out value="${domain.storeageSn}" /></td>
					<td><c:out value="${domain.productSpec}" /></td>
					<td><c:out value="${domain.installDate}" /></td>
					<td><c:out value="${domain.installArea}" /></td>
					<td><c:out value="${domain.stgIp}" /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
<div class="row">
	<div class="col-md-12">
		<span class="pull-right">
			<button type="button" class="btn btn-secondary"
				onclick="javascript:move_url('${pageContext.request.contextPath}/storeage/regist');">신규등록</button>
		</span>
	</div>
	<div class="col-md-12">
		<span class="pull-right"> <%@ include
				file="/WEB-INF/views/layout/pagination.jsp"%>
		</span>
	</div>
</div>
