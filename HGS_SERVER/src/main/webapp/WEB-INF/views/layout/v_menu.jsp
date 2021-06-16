<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="col-sm-3 col-md-2 sidebar">
	<ul class="nav nav-sidebar">
		<li>
			<a href="${pageContext.request.contextPath}/disability/list">장애현황 </a>
		</li>
		<li>
			<a href="${pageContext.request.contextPath}/customer/list">고객사</a>
		</li>
		<li class="active">
			<a href="${pageContext.request.contextPath}/storeage/list">스토리지
				<span class="sr-only current">(current)</span>
			</a>
		</li>
	</ul>
	<ul class="nav nav-list">
		<li class="nav-header">설정</li>
		<li class="active">
		  	<a href="#">Home</a>
	  	</li>
	  	<li>
	  		<a href="#">Library</a>
	  	</li>
	</ul>
</div>