<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>

<span id="moduleContentIdentifier" class="providers"></span>

<h1>
	<spring:message code="providers.pagetitle"/>
</h1>

	<app:controlbar>
		<jsp:attribute name="body">
			<img src="${images }/icons/arrow_left.png" style="vertical-align:middle" /> <a href="list"><spring:message code="providers.backtolist"/></a>
		</jsp:attribute>
	</app:controlbar>	



<table class="results">
	<tr>
		<td class="fieldname">
			<spring:message code="providers.fields.name" />
			
		</td>
		<td>${provider.name }</td>
	</tr>
	<tr>
		<td class="fieldname">
			<spring:message code="providers.fields.servicesJsonPath" />
			
		</td>
		<td>${provider.servicesJsonPath }</td>
	</tr>
	<tr>
		<td class="fieldname">
			<spring:message code="providers.fields.applications" />
			
		</td>
		<td>${provider.applications }</td>
	</tr>
	<tr>
		<td class="fieldname">
			<spring:message code="providers.fields.active" />
			
		</td>
		<td>${provider.active}</td>
	</tr>

</table>
	<app:controlbar>
		<jsp:attribute name="body">
	<img src="${images }/icons/edit.png" style="vertical-align:middle" /> <a href="update?name=${provider.name }"><spring:message code="providers.editdetails"/></a>
		</jsp:attribute>
	</app:controlbar>	


