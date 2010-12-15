<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>

<%@page import="java.net.URLEncoder"%><jsp:useBean id="currentDate" class="java.util.Date" scope="request" />
<span id="moduleContentIdentifier" class="eizoku_cnewlink"></span>


<div class="appForm">
	<form:form method="post" action="create" modelAttribute="link">
	
	<form:errors path="*" element="div" cssClass="noticeError noticeBox"></form:errors>
	
	<table>
   	<tr>
   		<td class="fieldname">	
				<label><spring:message code="eizoku.updatelink.fields.key"/></label>
		</td>
		<td>
				${pageContext.request.scheme}://${pageContext.request.serverName }:${pageContext.request.serverPort }${pageContext.request.contextPath }/link/<form:input path="path" cssErrorClass="noticeError"/>
		</td>
	</tr>
	<tr>
   		<td class="fieldname">
   		<label><spring:message code="eizoku.updatelink.fields.value"/></label>
   		</td>
   		<td>
   		<form:input path="url" size="50" cssErrorClass="noticeError"/>
   		</td>	
	</tr>
	<tr>
   		<td class="fieldname">
   		<label><spring:message code="eizoku.updatelink.fields.description"/></label>
   		</td>
   		<td>
   		<form:textarea path="description" cols="40" rows="6" cssErrorClass="noticeError"/>
   		</td>	
	</tr>
	</table>
	<app:buttonbar showDelete="false" />
	
</form:form>
	</div>	
