<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/layout/tagLib.jsp"%>
<script>
$(document).ready(function(e){
	$("#pageTitle").text('고객사');
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
	                <li><a at="customerName" value="customerName">고객사명</a></li>
	                <li><a at="managerName" value="managerName">담당자이름</a></li>
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
			<tr>
				<th>No</th>
				<th>고객사명</th>
				<th>발송 E-Mail 주소</th>
				<th>담당자 이름</th>
				<th>담당자 H.P</th>
				<th>문자송신번호</th>
				<th>등록일</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="domain" items="${domainList}" varStatus="status">
				<tr onclick="javascript:move_url('${pageContext.request.contextPath}/customer/detail?id=${domain.id}');">
					<td><c:out value="${status.index}" /></td>
					<td><c:out value="${domain.customerName}" /></td>
					<td><c:out value="${domain.email}" /></td>
					<td><c:out value="${domain.managerName}" /></td>
					<td><c:out value="${domain.managerHP}" /></td>
					<td><c:out value="${domain.sendNumber}" /></td>
					<td><c:out value="${domain.createDate}" /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
<div class="row">
	<div class="col-md-12">
		<span class="pull-right">
			<button type="button" class="btn btn-success"
				onclick="javascript:move_url('${pageContext.request.contextPath}/customer/regist');">신규등록</button>
		</span>
	</div>
	<div class="col-md-12">
		<span class="pull-right"> <%@ include
				file="/WEB-INF/views/layout/pagination.jsp"%>
		</span>
	</div>
</div>
