<%@page import="java.net.URLEncoder"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<jsp:useBean id="currentDate" class="java.util.Date" scope="request" />
<span id="moduleContentIdentifier" class="downtime"></span>


<form:form method="post" action="submit" modelAttribute="downtime">
	<input type="hidden" name="q" value="${param.q }"/>
	<form:errors path="*" element="div" cssClass="noticeError noticeBox"></form:errors>
	<form:hidden path="id"/>
	
<h1 id="pageTitle"> 
	<spring:message code="downtime.breadcrumbs.updatedowntime"/>
</h1>

	
	<table>
		<tr>
			<td><label><spring:message
				code="downtime.fields.moduleId" /> <spring:message
				code="downtime.field.separator" /></label></td>
			<td><form:input path="moduleId" cssErrorClass="noticeError" /></td>
		</tr>
		<tr>
			<td><label><spring:message
				code="downtime.fields.startTime" /> <spring:message
				code="downtime.field.separator" /></label></td>
			<td><form:input size="14" id="startTime" path="startTime" cssErrorClass="noticeError" /></td>
		</tr>
		<tr>
			<td><label><spring:message
				code="downtime.fields.durationInMinutes" /> <spring:message
				code="downtime.field.separator" /></label></td>
			<td><form:input path="durationInMinutes" size="3" cssErrorClass="noticeError"/></td>
		</tr>
		<tr>
			<td><label><spring:message
				code="downtime.fields.htmlContent" /> <spring:message
				code="downtime.field.separator" /></label></td>
			<td>
			<form:textarea path="htmlContent" cssErrorClass="noticeError"/>
			</td>
		</tr>
	</table>

	<app:buttonbar />


</form:form>


<script>
$('#htmlContent').markItUp(mySettings);
$("#startTime").datepicker({
	dateFormat : "yy-mm-dd",
	timeFormat : "H:m",
	time24h : true,
	duration: '',
    showTime: true,
    constrainInput: false
 });
</script>
<style>
.markItUpEditor { 
	height:100px; 
}
</style>


