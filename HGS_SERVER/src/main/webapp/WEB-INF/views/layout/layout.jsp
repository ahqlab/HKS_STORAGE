<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/layout/tagLib.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">
<!-- <link rel="icon" href="../../favicon.ico"> -->
<title>HANKISUL Storage</title>
<!-- SCRIPT -->   
<tiles:insertAttribute name="script"/>
<!-- END SCRIPT --> 
</head>
<body>
	<!-- Main navbar -->
	<tiles:insertAttribute name="h_menu"/>
	<!-- /main navbar -->
	<div class="container-fluid">
		<div class="row">
			<!-- Main navbar -->
				<tiles:insertAttribute name="v_menu"/>
			<!-- /main navbar -->
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<tiles:insertAttribute name="header"/>
				<!-- <h2 class="sub-header">Section title</h2> -->
				<tiles:insertAttribute name="content"/>
			</div>
		</div>
	</div>

	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<tiles:insertAttribute name="footer"/>
</body>
</html>