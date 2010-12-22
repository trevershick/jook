<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>

<%@page import="java.net.URLEncoder"%><jsp:useBean id="currentDate"
	class="java.util.Date" scope="request" />
<span id="moduleContentIdentifier" class="providers"></span>
<form:form method="post" action="update" modelAttribute="provider">

	<form:errors path="*" element="div" cssClass="noticeBox noticeError" />
	<h1><spring:message code="providers.form.pagetitle" /></h1>
	<table>
		<tr>
			<td width="250"><form:label path="name">
				<spring:message code="providers.fields.name" />
				<spring:message code="support.field.separator" />
			</form:label></td>
			<td><c:choose>
				<c:when test="${empty provider.name }">
					<form:input path="name" cssErrorClass="noticeError" />
				</c:when>
				<c:otherwise>
					${ provider.name }<form:hidden path="name" />
				</c:otherwise>
			</c:choose></td>
		</tr>
		<tr>
			<td><form:label path="unsecureUrl">
				<spring:message code="providers.fields.unsecureUrl" />
				<spring:message code="support.field.separator" />
			</form:label></td>
			<td><form:input path="unsecureUrl"
				cssErrorClass="noticeError" size="40"/></td>
		</tr>		
		<tr>
			<td><form:label path="secureUrl">
				<spring:message code="providers.fields.secureUrl" />
				<spring:message code="support.field.separator" />
			</form:label></td>
			<td><img width="16" align="middle" src="${pageContext.request.contextPath }/railinc-template/images/icons/lock.png" /><form:input path="secureUrl"
				cssErrorClass="noticeError" size="40"/></td>
		</tr>
		<tr>
			<td><form:label path="applications">
				<spring:message code="providers.fields.apps" />
				<spring:message code="support.field.separator" />
			</form:label></td>
			<td><form:input path="applications" cssErrorClass="noticeError" size="35"/></td>
		</tr>
		<tr>
			<td><form:label path="requiredRoles">
				<spring:message code="providers.fields.requiredRoles" />
				<spring:message code="support.field.separator" />
			</form:label></td>
			<td><form:input path="requiredRoles" cssErrorClass="noticeError" size="35"/></td>
		</tr>

		<tr>
			<td>
				<form:label path="active">
					<spring:message code="providers.fields.active" />
				</form:label>
				<form:checkbox id="active" path="active" cssErrorClass="noticeError" />
			</td>
		</tr>
	
	
</table>	
<app:buttonbar />
</form:form>
<script>
var availableTags = [
                 <c:forEach items="${ applications}" var="a" varStatus="s">
                 	<c:if test="${!s.first }">,</c:if>"${a }"
                 </c:forEach>
                 ];

jQuery(function() {
	function split( val ) {
		return val.split( /,\s*/ );
	}
	function extractLast( term ) {
		return split( term ).pop();
	}

	jQuery( "#applications" ).autocomplete({
		source: function( request, response ) {
			$.getJSON( "/jook/main/secure/refdata/applications", {
				term: extractLast( request.term )
			}, response );
		},
		focus: function() {
			// prevent value inserted on focus
			return false;
		},
		select: function( event, ui ) {
			var terms = split( this.value );
			// remove the current input
			terms.pop();
			// add the selected item
			terms.push( ui.item.value );
			// add placeholder to get the comma-and-space at the end
			//terms.push( "" );
			this.value = terms.join( ", " );
			return false;
		}
	});	
	
	jQuery("#excludeApplications").button({icons: {primary:'ui-icon-check'}});
	jQuery("#active").button({icons: {primary:'ui-icon-check'}});
	});

</script>
