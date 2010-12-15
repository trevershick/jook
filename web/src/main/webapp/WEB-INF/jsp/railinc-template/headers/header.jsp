<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<c:set value="${pageContext.request.contextPath}/railinc-template/images" var="images"/>
<div id="header">	
<ul id="util-nav" class="horizontal-menu">
<c:if test="${ not empty sessionScope.SSO_USER.userId }">
	<li class="userName"><fmt:message key="header.loggedin">
		<fmt:param value="${sessionScope.SSO_USER.userId }"/>
		<fmt:param value="${sessionScope.SSO_USER.employer}"/>
	</fmt:message></li>
</c:if>
    <li><a href="${templateLinks['link.signout'] }">Sign Out</a></li>
    <li><a href="${templateLinks['link.launchpad'] }">Launch Pad</a></li>
    <li><a href="${templateLinks['link.userservices'] }">User Services</a></li>
    <li><a href="${templateLinks['link.contactus'] }">Contact Us</a></li>
  </ul>
  <div id="logoArea"> <a href="http://www.railinc.com" class="logo"><img src="${images }/Railinc-Logo.png" alt="Go to www.railinc.com" /></a>
    <h2 class="headerDescription">${appTitle}</h2>
  </div>
</div>