<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>

<%@page import="java.net.URLEncoder"%><jsp:useBean id="currentDate" class="java.util.Date" scope="request" />
<span id="moduleContentIdentifier" class="providers"></span>
<form:form method="post" action="update" modelAttribute="provider">

		<form:errors path="*" element="div" cssClass="noticeBox noticeError"/>
<h1>
<spring:message code="providers.form.pagetitle"/>
</h1>
<table>
   	<tr>
   		<td>
   		<form:label path="name"><spring:message code="providers.fields.name"/>
   			<spring:message code="support.field.separator"/>
   		</form:label>
   		</td>
   		<td>
 			<c:choose>
				<c:when test="${empty provider.name }">
					<form:input path="name" cssErrorClass="noticeError"/>
				</c:when>
				<c:otherwise>
					${ provider.name }<form:hidden path="name"/>
				</c:otherwise>
			</c:choose>
			</td>
	</tr>
	<tr>
	<td>
		<form:label path="servicesJsonPath"><spring:message code="providers.fields.servicesJsonPath"/> <spring:message code="support.field.separator"/></form:label>
	</td>
	<td>
		<form:input path="servicesJsonPath" cssErrorClass="noticeError"/>
	</td>
	</tr>
		<tr>
			<td><form:label path="applications">
				<spring:message code="providers.fields.apps" />
				<spring:message code="support.field.separator" />
			</form:label></td>
			<td><form:input path="applications" cssErrorClass="noticeError" /></td>
		</tr>
	<tr>
	<td>
		<form:label path="active"><spring:message code="providers.fields.active"/> <spring:message code="support.field.separator"/></form:label>
	</td>
	<td>
		<form:checkbox path="active" cssErrorClass="noticeError"/>
	</td>
	</tr>
	
	
</table>	
<app:buttonbar/>
</form:form>
