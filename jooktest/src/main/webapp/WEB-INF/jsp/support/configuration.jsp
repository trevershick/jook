<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>

<%@page import="java.net.URLEncoder"%><jsp:useBean id="currentDate" class="java.util.Date" scope="request" />

<h1 id="pageTitle"> <a href="${pageContext.request.contextPath }/main/secure/support"><fmt:message key="support.breadcrumbs.console"/></a> 
	<fmt:message key="support.breadcrumbs.separator"/> 
	<fmt:message key="support.breadcrumbs.configuration">
		<fmt:param value="${hostname}"/>
		<fmt:param value="${address}"/>
	</fmt:message></h1>
<div class="formContainer">
<c:if test="${configurationValues != null}">
	<div class="section">

	<table class="data" width="100%">
		<tr>
			<th><fmt:message key="support.configuration.th.key"/></th>
			<th><fmt:message key="support.configuration.th.value"/></th>
		</tr>
		<c:forEach items="${configurationValues}" var="v" varStatus="status">
			<tr>
				<td>
					<b><a href="update?k=${v.key }">${v.key}</a></b><br/>
					<font size="-2">${v.description}</font>
				</td>
				<td>
				<a href="update?k=${v.key }">${v.stringValue }</a>
				
				</td>
			</tr>
		</c:forEach>
		<tr>
			<td style="padding-top:5px;"><a href="update"><b><fmt:message key="support.configuration.createnewentry"/></b></a></td>
			
			<td></td>
		</tr>
	</table>
	</div>	
</c:if>
</div>
