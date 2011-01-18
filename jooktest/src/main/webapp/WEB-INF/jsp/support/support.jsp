<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<c:set value="${pageContext.request.contextPath}/main" var="main"/>
<jsp:useBean id="currentDate" class="java.util.Date" scope="request" />

<h1 id="pageTitle"><fmt:message key="support.breadcrumbs.console"/></h1>
<div class="formContainer">

<table width="100%">
	<tr>
		<td width="50%" valign="top">
			<span class="sectionHeader"><fmt:message key="support.serverinfo.header"/></span>
			<div class="section" style="min-height:100px;">
			<fmt:message key="support.field.hostname"/> <fmt:message key="support.field.separator"/> ${hostname }<br/>
			<fmt:message key="support.field.address"/> <fmt:message key="support.field.separator"/> ${address }<br/>
			<fmt:message key="support.field.localport"/> <fmt:message key="support.field.separator"/> <%=request.getLocalPort() %><br/>
			<fmt:message key="support.field.contextpath"/> <fmt:message key="support.field.separator"/> <%=request.getContextPath() %><br/>
			<fmt:message key="support.field.serverinfo"/> <fmt:message key="support.field.separator"/> <%=config.getServletContext().getServerInfo() %>
			</div>
		</td>
		<td width="50%" valign="top">
			<span class="sectionHeader"><fmt:message key="support.links.header"/></span>
			<div class="section" style="min-height:100px;">
			<a href="support/configuration/list?${random }"><fmt:message key="support.links.configuration"/></a><br/>
			<a href="support/logging/list?${random }"><fmt:message key="support.links.log4jlevels"/></a><br/>
			<a href="support/properties?${random }"><fmt:message key="support.links.sysprops"/></a><br/>
			<a href="support/snoop?${random }"><fmt:message key="support.links.snooprequest"/></a><br/>
			<a href="${main}/global/rem"><fmt:message key="support.links.rem"/></a><br/>
			<a href="${main}/global/version"><fmt:message key="support.links.version"/></a>
			</div>
		</td>
	</tr>
</table>

<table width="100%">
	<tr>
		<td width="50%" valign="top">
				<span class="sectionHeader"><fmt:message key="support.caches.header"/></span>
				<div class="section" style="min-height:100px;">
				Put a list of URLs here to clear caches (perhaps)
				</div>
		</td>
		<td width="50%" valign="top">
			<span class="sectionHeader"><fmt:message key="support.batchprocesses.header"/></span>
			<div class="section" style="min-height:100px;">
			Put a list of links here to spark batch jobs?
			</div>
		</td>
	</tr>
</table>

<table width="100%">
	<tr>
		<td width="50%" valign="top">
				<span class="sectionHeader"><fmt:message key="support.svnrevision.header"/></span>
				<div class="section" style="min-height:175px;">
				<c:forEach items="${svnRevisionText}" var="line">
				${line }<br/>
				</c:forEach>
				</div>
		</td>
		<td width="50%" valign="top">
			<span class="sectionHeader"><fmt:message key="support.buildinfo.header"/></span>
			<div class="section" style="height:175px;">
			${versionText}
			</div>
		</td>
	</tr>
</table>


</div>	
