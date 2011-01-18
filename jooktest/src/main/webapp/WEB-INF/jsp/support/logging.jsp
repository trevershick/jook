<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>

<%@page import="java.net.URLEncoder"%><jsp:useBean id="currentDate" class="java.util.Date" scope="request" />

<h1 id="pageTitle"> <a href="${pageContext.request.contextPath }/main/secure/support"><fmt:message key="support.breadcrumbs.console"/></a> 
	<fmt:message key="support.breadcrumbs.separator"/> 
	<fmt:message key="support.breadcrumbs.logging">
		<fmt:param value="${hostname}"/>
		<fmt:param value="${address}"/>
	</fmt:message></h1>

<div class="formContainer">
<c:if test="${currentLoggers != null}">
	<div class="section">
	<form action="update">
	<table class="data" width="100%">
		<tr>
			<th><fmt:message key="support.logging.th.category"/></th>
			<th><fmt:message key="support.logging.th.currentlevel"/></th>
			<th><fmt:message key="support.logging.th.newlevel"/></th>
		</tr>
		<tr>
			<td><input name="n" size="30"/></td>
			<td>
				<select name="l">
					<option value="DEBUG"><fmt:message key="support.logging.DEBUG"/></option>
					<option value="INFO"><fmt:message key="support.logging.INFO"/></option>
					<option value="WARN"><fmt:message key="support.logging.WARN"/></option>
					<option value="ERROR"><fmt:message key="support.logging.ERROR"/></option>
					<option value="FATAL"><fmt:message key="support.logging.FATAL"/></option>
				</select>
			</td>
			<td>
				<input type="submit" value="Add"/>
			</td>
		</tr>
		<c:forEach items="${currentLoggers}" var="result">
			<tr>
				<td>${result.name}</td>
				<td><fmt:message key="support.logging.${result.level}"/></td>
				<td>
					<c:if test="${result.level != 'DEBUG' }">
					<a href="update?n=${result.name }&l=DEBUG&r=${random }"><fmt:message key="support.logging.DEBUG"/></a>
					</c:if><fmt:message key="support.logging.separator"/>
					<c:if test="${result.level != 'INFO' }">
					<a href="update?n=${result.name }&l=INFO&r=${random }"><fmt:message key="support.logging.INFO"/></a>
					</c:if><fmt:message key="support.logging.separator"/>
					<c:if test="${result.level != 'WARN' }">
					<a href="update?n=${result.name }&l=WARN&r=${random }"><fmt:message key="support.logging.WARN"/></a>
					</c:if><fmt:message key="support.logging.separator"/>
					<c:if test="${result.level != 'ERROR' }">
					<a href="update?n=${result.name }&l=ERROR&r=${random }"><fmt:message key="support.logging.ERROR"/></a>
					</c:if><fmt:message key="support.logging.separator"/>
					<c:if test="${result.level != 'FATAL' }">
					<a href="update?n=${result.name }&l=FATAL&r=${random }"><fmt:message key="support.logging.FATAL"/></a>
					</c:if>
				</td>
			</tr>
		</c:forEach>
	</table>
	</form>
	</div>	
</c:if>
</div>
