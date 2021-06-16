<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/layout/tagLib.jsp"%>
<div class="col-sm-3 col-md-2 sidebar">
	<c:choose>
		<c:when test="${role > 0}">
			<ul class="nav nav-sidebar">
				<li class="active">
					<a href="${pageContext.request.contextPath}/disability/list?pIdx=${pIdx}">장애현황
						<span class="sr-only current">(current)</span>
					</a>
				</li>
				<li>
					<a href="${pageContext.request.contextPath}/customer/list?pIdx=${pIdx}">고객사</a>
				</li>
				<li>
					<a href="${pageContext.request.contextPath}/storeage/list?pIdx=${pIdx}">스토리지</a>
				</li>
			</ul>
			<ul class="nav nav-list">
				<li class="nav-header">SETTING</li>
				<li>
				  	<a href="${pageContext.request.contextPath}/setting/term/update2?pIdx=${pIdx}">MESSAGE TERM</a>
			  	</li>
			</ul>
			<ul class="nav nav-sidebar">
				<li>
					<a href="${pageContext.request.contextPath}/user/change/account">계정</a>
				</li>
			</ul>
		</c:when>
		<c:otherwise>
			<ul class="nav nav-sidebar">
				<li>
					<a href="${pageContext.request.contextPath}/user/change/account">계정</a>
				</li>
			</ul>
		</c:otherwise>
	</c:choose>
</div>