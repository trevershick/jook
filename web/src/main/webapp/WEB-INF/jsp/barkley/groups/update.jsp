<%@page import="java.net.URLEncoder"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<jsp:useBean id="currentDate" class="java.util.Date" scope="request" />
<span id="moduleContentIdentifier" class="barkley_groups"></span>

<h1 id="pageTitle"> 
	<spring:message code="barkley.admin.breadcrumbs.updategroup"/>
</h1>

<form:form method="post" action="submit" modelAttribute="group">
	<input type="hidden" name="q" value="${param.q }"/>
	<form:errors path="*" element="div" cssClass="noticeError noticeBox"></form:errors>
	<form:hidden path="id"/>
	<table>
		<tr>
			<td><label><spring:message
				code="barkley.admin.updategroup.fields.applicationkey" /> <spring:message
				code="barkley.admin.field.separator" /></label></td>
			<td><form:input path="applicationKey" cssErrorClass="noticeError" /></td>
		</tr>
		<tr>
			<td><label><spring:message code="barkley.admin.updategroup.fields.name" />
			<spring:message code="barkley.admin.field.separator" /></label></td>
			<td><form:input path="name" cssErrorClass="noticeError" /></td>
		</tr>
		<tr>
			<td><label><spring:message
				code="barkley.admin.updategroup.fields.key" /> <spring:message
				code="barkley.admin.field.separator" /></label></td>
			<td><form:input path="key" cssErrorClass="noticeError" /></td>
		</tr>
		<tr>
			<td><label><spring:message
				code="barkley.admin.updategroup.fields.description" /> <spring:message
				code="barkley.admin.field.separator" /></label></td>
			<td><form:textarea path="description" cols="60" rows="6" cssErrorClass="noticeError"/></td>
		</tr>
	</table>
	<app:buttonbar showDelete="${not empty group.id }" />

</form:form>