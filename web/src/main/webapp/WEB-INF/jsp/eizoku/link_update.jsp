<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>

<%@page import="java.net.URLEncoder"%><jsp:useBean id="currentDate" class="java.util.Date" scope="request" />
<span id="moduleContentIdentifier" class="eizoku_regedlinks"></span>



<div class="appForm">
<form:form method="post" action="update" modelAttribute="link">
	<form:errors path="*" element="div" cssClass="noticeError noticeBox"></form:errors>
<table>
   	<tr>
   		<td class="fieldname">		
			<label><spring:message code="eizoku.updatelink.fields.key"/> <spring:message code="eizoku.field.separator"/></label>
		</td>
		<td>
			${pageContext.request.scheme}://${pageContext.request.serverName }:${pageContext.request.serverPort }${pageContext.request.contextPath }/link/${link.path}<form:hidden path="path"/>
		</td>
		</tr><tr>
		<td class="fieldname">
			<label><spring:message code="eizoku.updatelink.fields.value"/> <spring:message code="eizoku.field.separator"/></label>
			</td>
			<td>
			<form:textarea path="url" cols="60" rows="2" cssErrorClass="noticeError"/>
		</td>
		</tr>
		<tr>
			<td class="fieldname"><label><spring:message
				code="eizoku.updatelink.fields.description" /> <spring:message
				code="eizoku.field.separator" /></label></td>
			<td><form:textarea path="description" cols="60" rows="6"
				cssErrorClass="noticeError" /></td>
		</tr>
	</table>
	<app:buttonbar  />
	
</form:form>
</div>