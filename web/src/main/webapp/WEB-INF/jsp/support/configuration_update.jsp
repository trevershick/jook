<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>

<%@page import="java.net.URLEncoder"%><jsp:useBean id="currentDate" class="java.util.Date" scope="request" />
<span id="moduleContentIdentifier" class="support_config"></span>
<div class="appForm defaultRate">

<form:form method="post" action="submit" modelAttribute="configurationValue">
<ul>
   	<li><label><spring:message code="support.updateconfiguration.fields.key"/> <spring:message code="support.field.separator"/></label>
			<c:choose>
				<c:when test="${configurationValue.key == null }">
					<form:input path="key"/>
				</c:when>
				<c:otherwise>
					${configurationValue.key }<form:hidden path="key"/>
				</c:otherwise>
			</c:choose>
	</li>
	<li>
		<label><spring:message code="support.updateconfiguration.fields.value"/> <spring:message code="support.field.separator"/></label>
		<form:textarea path="stringValue"/>
	</li>
	<li>
		<label><spring:message code="support.updateconfiguration.fields.description"/> <spring:message code="support.field.separator"/></label>
		<form:textarea path="description"/>
	</li>
</ul>		
<app:buttonbar/>
</form:form>
</div>