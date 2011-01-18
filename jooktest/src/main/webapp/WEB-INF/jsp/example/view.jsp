<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<html>
<head>
<title>Example Object</title>
</head>
<body>
	<jsp:useBean id="currentDate" class="java.util.Date" scope="request" />
<fmt:formatDate type="both" dateStyle="short" timeStyle="short"
			value="${currentDate}" />
	<br/>
	
	Example Object<br/>
	Id : ${exampleObject.id }<br/>
	Text : ${exampleObject.text }<br/>
</body>
</html>
