<%@ include file="/WEB-INF/jsp/include.jsp"%>
<c:set value="${pageContext.request.contextPath}/railinc-template/images" var="images"/>
<span id="moduleContentIdentifier" class="newsitems"></span>

<h1>
<spring:message code="newsitems.list.pagetitle"/>
</h1>		
	<app:controlbar>
		<jsp:attribute name="body">
			<app:addicon url="update" message="newsitems.list.newnewsitem"/>
		</jsp:attribute>
	</app:controlbar>	



		<table class="results" width="100%" id="newsitemsTable">
		<thead>
		<tr>
			<th><spring:message code="newsitems.list.th.title"/></th>
			<th><spring:message code="newsitems.list.th.moduleid"/></th>
			<th><spring:message code="newsitems.list.th.launchdate"/></th>
			<th><spring:message code="newsitems.list.th.published"/></th>
			<th><spring:message code="newsitems.list.th.actions"/></th>
		</tr>
		</thead>
		<tbody>
		<c:forEach items="${newsItems}" var="p">
			<tr>
				<td><a href="details?id=${p.id}">${p.title }</a></td>
				<td>${p.moduleId }</td>
				<td><fmt:formatDate value="${p.launchDate }" type="date" pattern="yyyy-MM-dd" /></td>
				<td>${p.published }</td>
				
				
				<td>
					<app:editicon url="update?id=${p.id }"/>
					<app:deleteicon url="update?id=${p.id }&_eventId_delete=_eventId_delete"/>
				</td>				
			</tr>
		</c:forEach>
		</tbody>
	</table>
	
