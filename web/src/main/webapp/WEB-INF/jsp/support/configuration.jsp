<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<c:set value="${pageContext.request.contextPath}/railinc-template/images" var="images" />
<%@page import="java.net.URLEncoder"%><jsp:useBean id="currentDate" class="java.util.Date" scope="request" />
<span id="moduleContentIdentifier" class="support_config"></span>

<c:if test="${configurationValues != null}">

	<app:controlbar>
		<jsp:attribute name="body">
			<app:addicon url="update" message="support.configuration.createnewentry"/>
		</jsp:attribute>
	</app:controlbar>	

	<table class="results" id="configurationTable">
	<thead>
		<tr>
			<th><spring:message code="support.configuration.th.key"/></th>
			<th><spring:message code="support.configuration.th.value"/></th>
		</tr>
		</thead>
		<tbody>
		<c:forEach items="${configurationValues}" var="v" varStatus="status">
			<tr>
				<td>
					<b><a href="update?k=${v.key }">${v.key}</a></b><br/>
					<font size="-2">${v.description}</font>
				</td>
				<td>
				<a href="update?k=${v.key }">${v.stringValue }</a>
				
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>

</c:if>


<script>

$().ready(function() 
    { 
        $("#configurationTable").tablesorter({widgets: ['zebra']} ); 
        var sorting = [[0,0]]; 
        // sort on the first column 
        $("#configurationTable").trigger("sorton",[sorting]);         
    } 
); </script>