<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="jook" uri="http://railinc.com/taglib/jook/1.0" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">
<head>
	<tiles:insertAttribute name="headContent"/>
	<tiles:insertAttribute name="additionalHeadContent"/>
</head>
<body>


<div id="wrapper">

	<div id="header">
		<tiles:insertAttribute name="headerContent"/>
	</div><!-- end header -->

    <div class="sidebar">
		<tiles:insertAttribute name="sidebarHead"/>
		<tiles:insertAttribute name="sidebarContent"/>
		<tiles:insertAttribute name="sidebarTail"/>
    </div>

	<div id="container" class="hidden">

	<!-- working message -->
	<spring:hasBindErrors name="${name}">
		<div class="noticeError">
	    <ul>
	        <c:forEach items="${errors.allErrors}" var="error">
	            <li><span class=".error"><c:out value="${error.defaultMessage}" /></span></li>
	        </c:forEach>
	    </ul>
	    </div>
	</spring:hasBindErrors>	
	<c:if test="${messages != null}">
		<div class="noticeOk">
		<ul>
		<c:forEach items="${messages}" var="msg">
		<li>${msg }</li>
		</c:forEach>
		</ul>
		</div>
	</c:if>
	<c:if test="${warnings != null}">
		<div class="noticeWarning">
		<ul>
		<c:forEach items="${warnings}" var="msg">
		<li>${msg }</li>
		</c:forEach>
		</ul>
		</div>
	</c:if>



		<tiles:insertAttribute name="content"/>
	</div><!-- end content -->

	<div id="footer">
		<tiles:insertAttribute name="footerContent"/>
	</div><!-- end footer -->



</div>

<jook:script app="jooktest" />
</body>
</html>
