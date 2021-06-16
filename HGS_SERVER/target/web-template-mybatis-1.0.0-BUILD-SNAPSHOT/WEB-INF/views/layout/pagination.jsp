<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="box-footer clearfix">
    <ul class="pagination pagination-sm no-margin pull-right">
	<c:if test="${page.priorPageGroup > 0}">
		<li><a href="javascript:movePage(1)">&laquo;</a></li>
	</c:if>

	<!-- 페이징 -->
	<c:forEach var="i" begin="${page.startPage}" end="${page.endPage}"
		step="1">
		<c:if test="${page.currentPage != i }">
			 <li><a href="javascript:movePage(${i})">${i}</a></li>
		</c:if>
		<c:if test="${page.currentPage == i }">
		 	<li><a href="#" class="active" style="color: blue;">${i}</a></li>
		</c:if>
	</c:forEach>

	<c:if test="${page.nextPageGroup > 0}">
		<li><a href="javascript:movePage(${page.nextPageGroup})">&raquo;</a></li>
	</c:if>
 	</ul>
 </div>