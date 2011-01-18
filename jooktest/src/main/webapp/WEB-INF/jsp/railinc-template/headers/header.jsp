
<!-- ui standards header -->
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set
	value="${pageContext.request.contextPath}/railinc-template/images"
	var="images" />
<tiles:useAttribute name="appTitle" />



<ul id="util-nav" class="horizontal-menu">
	<c:if test="${ not empty sessionScope.SSO_USER.userId }">
		<li class="userName"><fmt:message key="header.loggedin">
			<fmt:param value="${sessionScope.SSO_USER.userId }" />
			<fmt:param value="${sessionScope.SSO_USER.employer}" />
		</fmt:message></li>
		<li><a href="/sso/logout.do">Sign Out</a></li>
	</c:if>
	<c:if test="${ empty sessionScope.SSO_USER.userId }">
		<li><a href="/sso/login.do">Sign In</a></li>
	</c:if>
</ul>
<div id="logoArea"><a href="http://www.railinc.com" class="logo"><img
	src="${images }/Railinc-Logo.png" alt="Go to www.railinc.com" /></a>
<h1 class="logoText">Railinc</h1>
<h2 class="headerDescription">${appTitle}</h2>
</div>