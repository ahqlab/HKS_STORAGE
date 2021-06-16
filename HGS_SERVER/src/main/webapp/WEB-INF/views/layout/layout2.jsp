<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/layout/tagLib.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>HANKISUL Storage</title>
	
	<tiles:insertAttribute name="script"/>
</head>

<body>

	<!-- Main navbar -->
	<tiles:insertAttribute name="h_menu"/>
	<!-- /main navbar -->
	
	<!-- Page container -->
	<div class="page-container">

		<!-- Page content -->
		<div class="page-content">

			<!-- Main sidebar -->
			<tiles:insertAttribute name="v_menu"/>
			<!-- /main sidebar -->


			<!-- Main content -->
			<div class="content-wrapper">

				<!-- Page header -->
				<tiles:insertAttribute name="header"/>
				<!-- /page header -->


				<!-- Content area -->
				<div class="content">
					
					<tiles:insertAttribute name="content"/>
					
					<!-- Footer -->
					<tiles:insertAttribute name="footer"/>
					<!-- /footer -->

				</div>
				<!-- /content area -->

			</div>
			<!-- /main content -->

		</div>
		<!-- /page content -->

	</div>
	<!-- /page container -->

</body>
</html>