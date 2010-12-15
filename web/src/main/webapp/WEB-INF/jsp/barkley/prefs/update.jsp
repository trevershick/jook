<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>

<%@page import="java.net.URLEncoder"%><jsp:useBean id="currentDate" class="java.util.Date" scope="request" />

<h1 id="pageTitle"> <a href="${pageContext.request.contextPath }/main/secure/prefs/list"><spring:message code="barkley.admin.breadcrumbs.prefs"/></a> 
	<spring:message code="barkley.admin.breadcrumbs.separator"/> 
	<spring:message code="barkley.admin.breadcrumbs.updatepref"/>
</h1>

<form:form method="post" action="submit" modelAttribute="pref">
	<input type="hidden" name="q" value="${param.q }"/>
	<form:errors path="*" element="div" cssClass="noticeError noticeBox"></form:errors>

	<table>

		<c:if test="${! empty pref.id}">
		<tr>
			<td width="25%"></td>
			<td width="1"></td>
			<td>
					<form:hidden path="id"/>
			</td>
		</tr>

		<tr>
			<td><spring:message code="barkley.admin.updatepref.fields.application"/></td>
			<td></td>
			<td>${pref.specification.group.applicationKey }</td>
		</tr>
		<tr>
			<td><spring:message code="barkley.admin.updatepref.fields.group"/></td>
			<td></td>
			<td>${pref.specification.group.name } (${pref.specification.group.key })</td>
			<td>${pref.specification.group.description}
			</td>
		</tr>
		<tr>
			<td><spring:message code="barkley.admin.updatepref.fields.specification"/></td>
			<td></td>
			<td><form:hidden path="specification"/> ${pref.specification.name } (${pref.specification.key })</td>
			<td>${pref.specification.description}
			</td>
		</tr>
		</c:if>
		
		
		<c:if test="${empty pref.id}">
		<tr>
			<td>
			<spring:message code="barkley.admin.updatepref.fields.spec"/> <spring:message code="barkley.admin.field.separator"/></td>
			<td>
			</td>
			<td>
			<form:select path="specification">
			<c:forEach items="${specs}" var="s">
				<form:option value="${s}">${s.group.applicationKey} - ${s.group.name} - ${s.name}</form:option>
			</c:forEach>
			</form:select>
			</td>
		</tr>
		</c:if>
		<tr>
			<td>
			<spring:message code="barkley.admin.updatepref.fields.userid"/> <spring:message code="barkley.admin.field.separator"/></td>
			<td>
			<spring:bind path="userId">
				<c:if test="${status.error}">
					<img src="${pageContext.request.contextPath }/railinc-template/images/icons/error.png"/>
				</c:if>
			</spring:bind>
			</td>
			<td>
			<form:input path="userId"/>
			</td>
		</tr>
		<tr>
			<td><spring:message code="barkley.admin.updatepref.fields.value"/> <spring:message code="barkley.admin.field.separator"/></td>
			<td></td>
			<td><form:input path="value"/></td>
		</tr>
	</table>
		<app:buttonbar />
	

</form:form>