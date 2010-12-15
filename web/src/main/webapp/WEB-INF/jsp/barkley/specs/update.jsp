<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>

<%@page import="java.net.URLEncoder"%><jsp:useBean id="currentDate" class="java.util.Date" scope="request" />
<span id="moduleContentIdentifier" class="barkley_specs"></span>

<h1 id="pageTitle"> 
	<spring:message code="barkley.admin.breadcrumbs.updatespec"/>
</h1>

<form:form method="post" action="submit" modelAttribute="spec">
	<input type="hidden" name="q" value="${param.q }"/>
	<form:errors path="*" element="div" cssClass="noticeError noticeBox"></form:errors>
	<form:hidden path="id"/>



	<table>
		<tr>
			<td>
				<label>
				<spring:message code="barkley.admin.updatespec.fields.group"/> <spring:message code="barkley.admin.field.separator"/>
				</label>
			</td>
			<td>
				<form:select path="group" cssErrorClass="noticeError">
					<c:forEach items="${groups}" var="group">
						<form:option value="${group}">${group.applicationKey} - ${group.name}</form:option>
					</c:forEach>
				</form:select>
			</td>
		</tr>
		<tr>
			<td>
				<label>
					<spring:message code="barkley.admin.updatespec.fields.key"/> <spring:message code="barkley.admin.field.separator"/>
				</label>
			</td>
			<td>
				<form:input path="key" cssErrorClass="noticeError"/>
			</td>
		</tr>
		<tr>
			<td>
				<label><spring:message code="barkley.admin.updatespec.fields.name"/> <spring:message code="barkley.admin.field.separator"/></label>
			</td>
			<td><form:input path="name" cssErrorClass="noticeError"/></td>
		</tr>
		<tr>
			<td>
				<label>
					<spring:message code="barkley.admin.updatepref.fields.defvalue"/> <spring:message code="barkley.admin.field.separator"/>
				</label>
			</td>
			<td><form:input path="defaultValue" cssErrorClass="noticeError"/></td>
		</tr>		
		<tr>
			<td><label><spring:message code="barkley.admin.updatespec.fields.description"/> <spring:message code="barkley.admin.field.separator"/></label></td>
			<td><form:textarea path="description" cols="60" rows="6" cssErrorClass="noticeError"/></td>
		</tr>
	</table>
	
		<app:buttonbar />
	
	
</form:form>