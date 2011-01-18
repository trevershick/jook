<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<jsp:useBean id="currentDate" class="java.util.Date" scope="request" />

<h1 id="pageTitle"> <a href="${pageContext.request.contextPath }/main/secure/support"><fmt:message key="support.breadcrumbs.console"/></a> 
	<fmt:message key="support.breadcrumbs.separator"/> 
	<fmt:message key="support.breadcrumbs.sysprops">
		<fmt:param value="${hostname}"/>
		<fmt:param value="${address}"/>
	</fmt:message></h1>

<div class="formContainer">
<c:if test="${properties != null}">
	<div class="section">
<div id="scrollContainer" class="scroll dataWindow" style="height:500px;">
	<table class="data" width="100%">
		<tr>
			<th><fmt:message key="support.sysprops.th.key"/></th>
			<th><fmt:message key="support.sysprops.th.value"/></th>
		</tr>
		<c:forEach items="${properties}" var="result">
			<tr>
				<td>${result.key}</td>
				<td>${result.value}</td>
			</tr>
		</c:forEach>
	</table>
	</div>
	</div>	
</c:if>
</div>
