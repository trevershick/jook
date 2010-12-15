<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<span id="moduleContentIdentifier" class="helpdesk"></span>
<h1 id="pageTitle"><spring:message code="incident.list.pagetitle" /></h1>

	
	<p>
	<spring:message code="incident.list.infocontent" arguments="${landingPageUrl }"/>
	</p>
	
	
	<table class="results" id="incidentTable">
		<caption><spring:message code="incident.list.tabletitle"/></caption>
		<thead>
		<tr>
			<th><spring:message code="incident.list.th.id"/></th>
			<th><spring:message code="incident.list.th.rapidcaseid"/></th>
			<th><spring:message code="incident.list.th.ssouserid"/></th>
			<th><spring:message code="incident.list.th.name"/></th>
			<th><spring:message code="incident.list.th.phone"/></th>
			<th><spring:message code="incident.list.th.actions"/></th>
		</tr>
		</thead>
		<tbody>
		<c:forEach items="${incidents}" var="g">
			<tr>
				<td><a href="details?id=${g.id }">${g.id }</a></td>
				<td>${g.rapidCaseId}</td>
				<td>${g.ssoUserId}</td>
				<td>${g.name}</td>
				<td>${g.phone}</td>
				<td align="center" >
				<app:deleteicon url="update?id=${g.id }&q=${param.q }&_eventId_delete=_eventId_delete"/>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>


<script>

$().ready(function() { $("#incidentTable").tablesorter({widgets: ['zebra']});} 
); </script>