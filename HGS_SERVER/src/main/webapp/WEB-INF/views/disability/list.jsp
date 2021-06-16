<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/layout/tagLib.jsp"%>
<script>
$(document).ready(function(e){
	$("#pageTitle").text('장애현황');
	setCurrentServerStatus();
});

function setCurrentServerStatus(){
	var status = $('#serverStatus').prop('checked');
	jQuery.ajax({
  		type : "GET",
  		url : "${pageContext.request.contextPath}/server/current/status",
  		dataType : "JSON", // 옵션이므로 JSON으로 받을게 아니면 안써도 됨
  		success : function(data) {
  			if(data){
  				$('#serverStatus').bootstrapToggle('on');
  			}else{
  				$('#serverStatus').bootstrapToggle('off');
  			}
  		},
  		complete : function(data) {
  		},
  		error : function(xhr, status, error) {
  		}
  	});
}

function onChangeServerStatus(obj){
	var status = $('#serverStatus').prop('checked');
	jQuery.ajax({
  		type : "GET",
  		url : "${pageContext.request.contextPath}/server/change/status?status=" + status,
  		dataType : "JSON", // 옵션이므로 JSON으로 받을게 아니면 안써도 됨
  		success : function(data) {
  			//setCurrentServerStatus();
  		},
  		complete : function(data) {
  		},
  		error : function(xhr, status, error) {
  		}
  	});
}

</script>
<div class="row">
	<div class="col-xs-12">
		<label for="serverStatus">서버 기동</label>
		<input type="checkbox"  id="serverStatus" name="serverStatus"  data-toggle="toggle"  onchange="javascript:onChangeServerStatus(this);">
	</div>
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
			<tr>
				<th>No</th>
				<th>구분</th>
				<th>이벤트명</th>
				<th>스토리지명</th>
				<th>IP</th>
				<th>날짜</th>
				<th>FROM</th>
				<th>날짜</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="domain" items="${domainList}" varStatus="status">
				<tr onclick="javascript:move_url('${pageContext.request.contextPath}/disability/detail?id=${domain.id}');">
					<td><c:out value="${status.index}" /></td>
					<td><c:out value="${domain.status}" /></td>
					<td><c:out value="${domain.eventName}" /></td>
					<td><c:out value="${domain.storeageName}" /></td>
					<td><c:out value="${domain.ip}" /></td>
					<td><c:out value="${domain.dateTime}" /></td>
					<td><c:out value="${domain.mailFrom}" /></td>
					<td><c:out value="${domain.mailDate}" /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
<div class="row">
	<div class="col-md-12">
		<span class="pull-right"> <%@ include
				file="/WEB-INF/views/layout/pagination.jsp"%>
		</span>
	</div>
</div>
