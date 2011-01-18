<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>

<%@page import="java.net.URLEncoder"%><jsp:useBean id="currentDate" class="java.util.Date" scope="request" />

<h1 id="pageTitle"> <a href="${pageContext.request.contextPath }/main/secure/support"><fmt:message key="support.breadcrumbs.console"/></a> 
	<fmt:message key="support.breadcrumbs.separator"/> 
	<fmt:message key="support.breadcrumbs.updateconfiguration"/>
</h1>

<form:form method="post" action="submit" modelAttribute="configurationValue">
<div class="formContainer">
	<div class="section">

	<table width="100%">
		<tr>
			<td><fmt:message key="support.updateconfiguration.fields.key"/> <fmt:message key="support.field.separator"/></td>
			<td>
			<c:choose>
				<c:when test="${configurationValue.key == null }">
					<form:input path="key"/>
				</c:when>
				<c:otherwise>
					${configurationValue.key }<form:hidden path="key"/>
				</c:otherwise>
			</c:choose>
			
			</td>
		</tr>
		<tr>
			<td><fmt:message key="support.updateconfiguration.fields.value"/> <fmt:message key="support.field.separator"/></td>
			<td><form:textarea path="stringValue" cols="60" rows="2"/></td>
		</tr>
		<tr>
			<td><fmt:message key="support.updateconfiguration.fields.description"/> <fmt:message key="support.field.separator"/></td>
			<td><form:textarea path="description" cols="60" rows="6"/></td>
		</tr>
	</table>
	</div>	
	
	<fmt:message key="support.updateconfiguration.buttons.save" var="buttonSave"/>
	<fmt:message key="support.updateconfiguration.buttons.cancel" var="buttonCancel"/>
	<fmt:message key="support.updateconfiguration.buttons.delete" var="buttonDelete"/>
	
	<input type="submit" name="_eventId_save" value="${buttonSave }"/>
	<input type="submit" name="_eventId_cancel" value="${buttonCancel }"/>
	<input type="submit" name="_eventId_delete" value="${buttonDelete }"/>
</div>
</form:form>