<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="col-sm-3 col-md-2 sidebar">
	<ul class="nav nav-sidebar">
		<li>
			<a href="${pageContext.request.contextPath}/disability/list?pIdx=${pIdx}"> 장애현황 </a>
		</li>
		<li>
			<a href="${pageContext.request.contextPath}/customer/list?pIdx=${pIdx}">고객사</a>
		</li>
		<li>
			<a href="${pageContext.request.contextPath}/storeage/list?pIdx=${pIdx} 
			
			">스토리지</a>
		</li>
	</ul>
	<ul class="nav nav-list ">
		<li class="nav-header">SETTING</li>
		<li class="active" >
		  	<a href="#">MESSAGE TERM</a>
	  	</li>
	</ul>
		<ul class="nav nav-sidebar">
		<li>
			<a href="${pageContext.request.contextPath}/user/change/account">계정</a>
		</li>
	</ul>
</div>