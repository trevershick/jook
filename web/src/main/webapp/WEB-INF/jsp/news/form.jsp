<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>

<%@page import="java.net.URLEncoder"%><jsp:useBean id="currentDate"
	class="java.util.Date" scope="request" />
<span id="moduleContentIdentifier" class="newsitems"></span>
<form:form method="post" action="update" modelAttribute="newsItem">

	<form:hidden path="id"/>





	
	
	<form:errors path="*" element="div" cssClass="noticeBox noticeError" />
	<h1><spring:message code="newsitems.form.pagetitle" /></h1>
	<table>
		<tr>
			<td><form:label path="title">
				<spring:message code="newsitems.fields.title" />
				<spring:message code="support.field.separator" />
			</form:label></td>
			<td>
				<form:input size="40" path="title" cssErrorClass="noticeError" />
				<form:label path="published">
					<spring:message code="newsitems.fields.published" />
				</form:label>
				<form:checkbox path="published" id="published" cssErrorClass="noticeError" />
				<span id="publishedState"></span>
			</td>
			
		</tr>


		<tr>
			<td><form:label path="launchDate">
				<spring:message code="newsitems.fields.launchdate" />
				<spring:message code="support.field.separator" />
			</form:label></td>
			<td><form:input size="10" id="launchDate" path="launchDate" cssErrorClass="noticeError" /></td>
		</tr>



		<tr>
			<td><form:label path="description">
				<spring:message code="newsitems.fields.description" />
				<spring:message code="support.field.separator" />
			</form:label></td>
			<td>
			<form:textarea id="description" path="description" />
			</td>
		</tr>
		

		<tr>
			<td><form:label path="link">
				<spring:message code="newsitems.fields.link" />
				<spring:message code="support.field.separator" />
			</form:label></td>
			<td><form:input size="60" path="link" cssErrorClass="noticeError" /></td>
		</tr>
		<tr>
			<td><form:label path="moduleId">
				<spring:message code="newsitems.fields.moduleid" />
				<spring:message code="support.field.separator" />
			</form:label></td>
			<td><form:input path="moduleId" cssErrorClass="noticeError" /></td>
		</tr>
		<tr>
			<td><form:label path="expirationDate">
				<spring:message code="newsitems.fields.expirationdate" />
				<spring:message code="support.field.separator" />
			</form:label></td>
			<td><form:input size="10" id="expirationDate" path="expirationDate" cssErrorClass="noticeError" /></td>
		</tr>
		

		<tr>
			<td><form:label path="authoredDate">
				<spring:message code="newsitems.fields.authoreddate" />
				<spring:message code="support.field.separator" />
			</form:label></td>
			<td><form:input size="10" readonly="true" path="authoredDate" cssErrorClass="noticeError" /></td>
		</tr>

	</table>
	<app:buttonbar/>

</form:form>
<script>
$('#description').markItUp(mySettings);
$("#launchDate").datepicker({ dateFormat : "yy-mm-dd" });
$("#expirationDate").datepicker({ dateFormat : "yy-mm-dd" });
$("#published").button();
$("#published").change(function() {updatePublishedState()});
	
function updatePublishedState() {
	$("#publishedState").html($("#published").is(":checked") ? "This article is published" : "This article is not published"); 
}
updatePublishedState();

</script>
<style>
.markItUpEditor { 
	height:100px; 
}
</style>


