<%@ page isErrorPage="true" %>
<%@ taglib prefix="jook" uri="http://railinc.com/taglib/jook/1.0" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set value="${pageContext.request.contextPath}/railinc-template/images" var="images"/>
<c:set value="${pageContext.request.contextPath}/railinc-template/js" var="scripts"/>
<c:set value="${pageContext.request.contextPath}/railinc-template/css" var="styles"/>
<c:set value="${pageContext.request.contextPath}/" var="basePath"/>

<%@page import="java.io.StringWriter"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="com.railinc.sso.rt.UserService"%>
<%@page import="com.railinc.sso.rt.LoggedUser"%>
<%@page import="java.util.Enumeration"%><html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="shortcut icon" href="${images}/favicon.ico" />

<!-- Common script libraries -->
<script type="text/javascript" src="${scripts}/jquery-1.3.2.min.js"></script>


<link rel="stylesheet" type="text/css" href="${styles }/reset.css"/>
<link rel="stylesheet" type="text/css" href="${styles }/layout.css"/>
<link rel="stylesheet" type="text/css" href="${styles }/color.css"/>
<link rel="stylesheet" type="text/css" href="${styles }/typography.css"/>


</head>

<body>



<div id="wrapper">
	<div id="header">
		<a href="http://www.railinc.com" target="_blank"><img alt="Railinc" title="Railinc" src="${images}/Railinc-Logo.png" class="inline" /></a>
	</div><!-- end header -->



	
<style>
h2 { font-size:10pt; margin-top:10px; margin-left:25px;}
ul { list-style-type:circle; padding:15px; }
li { font-size:9pt; } 
</style>
<div style="text-align:center;">
	<img src="${basePath }/images/train.jpg" width="200"/>
	<div style="margin:auto;width:450;">
		<h1>There was a problem serving the requested page.</h1>
		<h2 style="text-align:left">You're probably wondering, "What do I do now!?!". Well...</h2>
		<ul style="text-align:left;margin-left:25px">
			<li>you can try refreshing the page; the problem may be temporary</li>
			<li>if you entered the URL by hand or used a bookmark, double check that it is still valid</li>
			<li>report the issue so that we might ensure it doesn't happen again</li>
		</ul>
	</div>
<jook:supportform product="Equipment Health Mgmt System" 
	moduleId="EHMS">
	<input type="submit" value="Report this Error to Support"/>
</jook:supportform>
	
</div>
	


</body>
</html>