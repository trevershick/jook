<%@ include file="/WEB-INF/jsp/include.jsp"%>
<c:set value="${pageContext.request.contextPath}/railinc-template/images" var="images"/>
<span id="moduleContentIdentifier" class="interactions"></span>

<h1>
<spring:message code="interactions.list.pagetitle"/>
</h1>		

	<app:controlbar>
		<jsp:attribute name="body">
			<app:addicon url="update" message="interactions.list.newinteraction"/>
		</jsp:attribute>
	</app:controlbar>	


		<table class="results" width="100%" id="interactionsTable">
		<thead>
		<tr>
			<th><spring:message code="interactions.list.th.type"/></th>
			<th><spring:message code="interactions.list.th.title"/></th>
			<th><spring:message code="interactions.list.th.apps"/></th>
			<th><spring:message code="interactions.list.th.active"/></th>
			<th> </th>
		</tr>
		</thead>
		<tbody>
		<c:forEach items="${interactions}" var="p">
			<tr>
				<td>${p.verboseType }</td>
				<td>${p.title}</td>
				<td> 
				<c:choose>
				<c:when test="${p.excludeApplications and not empty p.applications}">
					<img width="16" src="${pageContext.request.contextPath }/railinc-template/images/icons/delete.png"/>${p.applications }
				</c:when>
				<c:when test="${!p.excludeApplications and not empty p.applications}">
					<img width="16" src="${pageContext.request.contextPath }/railinc-template/images/icons/add.png"/>${p.applications }
				</c:when>
				</c:choose>
				
				</td>
				<td>
				<c:choose>
				<c:when test="${p.active }">
					<img width="16" src="${pageContext.request.contextPath }/railinc-template/images/icons/green_check.png"/>
				</c:when>
				<c:otherwise>
					<!-- <img width="16" src="${pageContext.request.contextPath }/railinc-template/images/icons/red_x.png"/> -->
				</c:otherwise>
				</c:choose>
				</td>
				<td>
					<app:editicon url="update?id=${p.id }"/>
					<app:deleteicon url="update?id=${p.id }&_eventId_delete=_eventId_delete"/>
				</td>				
			</tr>
		</c:forEach>
		</tbody>
	</table>
	
<div style="min-height:100px;">
</div>
	
<h1>Current Response for ${pageContext.request.contextPath }/client/interactions/1.0/interactions.json?app=EXAMPLE </h1>
<pre id="currentJson" style="overflow:scroll;">
</pre>
<script>
$("#currentJson").load("${pageContext.request.contextPath }/client/interactions/1.0/interactions.json?app=EXAMPLE");
	
$().ready(function() { 
	$("#interactionsTable").tablesorter({widgets: ['zebra'], headers: {2:{sorter:false},3:{sorter:false},4:{sorter:false},5:{sorter:false}}});
    } 
); </script>