<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#navbar" aria-expanded="false"
				aria-controls="navbar">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="${pageContext.request.contextPath}/disability/list">HANKISUL Storage</a>
		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<ul class="nav navbar-nav navbar-right">
				<li><a href="${pageContext.request.contextPath}/disability/list?pIdx=${pIdx}">장애현황</a></li>
				<li><a href="${pageContext.request.contextPath}/customer/list?pIdx=${pIdx}">고객사</a></li>
				<li><a href="${pageContext.request.contextPath}/storeage/list?pIdx=${pIdx}">스토리지</a></li>
				<li><a href="${pageContext.request.contextPath}/user/change/account">계정</a></li>
				<li><a href="${pageContext.request.contextPath}/logout">로그아웃</a></li>
			</ul>
		</div>
	</div>
</nav>