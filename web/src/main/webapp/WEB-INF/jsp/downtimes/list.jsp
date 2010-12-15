<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<span id="moduleContentIdentifier" class="downtime"></span>
<h1 id="pageTitle"><spring:message code="downtime.pagetitle"/></h1>


	
	<app:controlbar>
		<jsp:attribute name="body">
			<app:addicon url="update" message="downtimes.createnewentry"/>
		</jsp:attribute>
	</app:controlbar>	
	
	<table class="results" id="downtimeTable">
		<thead>
		<tr>
			<th width="20%"><spring:message code="downtimes.th.appkey"/></th>
			<th><spring:message code="downtimes.th.startTime"/></th>
			<th><spring:message code="downtimes.th.duration"/></th>
			<th><spring:message code="downtimes.th.actions"/></th>
		</tr>
		</thead>
		<tbody>
		<c:forEach items="${downtimes}" var="g">
			<tr>
				<td>${g.moduleId}</td>
				<td>${g.startTime}</td>
				<td>${g.durationInMinutes}</td>
				<td align="center" width="10%">
				<app:editicon url="update?id=${g.id }&q=${param.q }"/>
				<app:deleteicon url="submit?id=${g.id }&q=${param.q }&_eventId_delete=_eventId_delete"/>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>

<script>

$().ready(function() { 
	$("#downtimeTable").tablesorter({widgets: ['zebra']});
    } 
); </script>