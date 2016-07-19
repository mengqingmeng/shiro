<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<%@ include file="/WEB-INF/jsp/include/head.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
<title><decorator:title /></title>
<sitemesh:head />
<style type="text/css">
</style>
</head>
<body class="header-fixed skin-blue">
	<div id="layout-body">
		<decorator:body />
	</div>
</body>
</html>
