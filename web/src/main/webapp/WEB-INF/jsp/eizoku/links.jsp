<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>


<%@page import="java.net.URLEncoder"%><jsp:useBean id="currentDate" class="java.util.Date" scope="request" />
<span id="moduleContentIdentifier" class="eizoku_regedlinks"></span>
<p>
This page contains a list of registered links within the system. You can add new links or maintain the existing links.
</p><br/>
<spring:message code="eizoku.links.createnewentry" var="buttonText"/>
	
	<app:controlbar>
		<jsp:attribute name="body">
			<app:addicon url="create" message="eizoku.links.createnewentry"/>
		</jsp:attribute>
	</app:controlbar>
	<table class="results" id="linksTable">
	<thead>
		<tr>
			<th class="col1"><spring:message code="eizoku.links.th.key"/></th>
			<th class="col2"><spring:message code="eizoku.links.th.value"/></th>
			<% if (request.isUserInRole("eizoku_adm"))  {%>
			<th><spring:message code="eizoku.links.th.creator"/></th>
			<% } %>
			<th class="col3"><spring:message code="eizoku.links.th.actions"/></th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${links}" var="v" varStatus="status">
			<tr class="${status.index % 2 == 0 ? 'even' : 'odd' }">
				<td>
					<a href="update?k=${v.path }"><b>${pageContext.request.scheme}://${pageContext.request.serverName }:${pageContext.request.serverPort }${pageContext.request.contextPath }/link/</b>${v.path}</a><br/>
					<p>${v.description}</p>
				</td>
				<td>
				<a href="update?k=${v.path }">${v.url }</a>
				</td>
			<% if (request.isUserInRole("eizoku_adm"))  {%>
			<td>${v.creator }</td>
			<% } %>
				<td align="center">
					<app:editicon url="update?k=${v.path }"/>
					<app:deleteicon url="update?path=${v.path}&_eventId_delete=_eventId_delete"/>
					<app:objecthistoryicon objectType="com.railinc.jook.domain.Link" objectId="${v.id}"/>
				</td>
			</tr>
		</c:forEach>
		</tbody>
		
	</table>


<script>
$().ready(function() 
	    { 
	        $("#linksTable").tablesorter({widgets: ['zebra']} ); 
	    } 
	);


</script>