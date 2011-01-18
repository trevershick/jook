<%@ page language="java" contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set value="${pageContext.request.contextPath}/railinc-template/styles" var="styles"/>
<html>
<head>
	<link type="text/css" rel="stylesheet" media="screen, projection, print" href="${styles}/stylesCustom.css" />
	<link type="text/css" rel="stylesheet" media="screen, projection, print" href="${styles}/stylesStandard.css" />
</head>
<body>
<c:forEach items="${news}" var="article">
<div class="newsArticle">
	<h1>${article.headline }</h1>
	<p>${article.body }xxx</p>
</div>
</c:forEach>
</body>
</html>
