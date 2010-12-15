<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<span id="moduleContentIdentifier" class="barkley_specs"></span>
<h1 id="pageTitle"><spring:message code="barkley.admin.breadcrumbs.specs"/></h1>

<div class="formContainer">


<div class="section">
<form>
<spring:message code="barkley.admin.specs.search.prompt"/>
<input type="text" name="q" value="${param.q }" />
<spring:message code="barkley.admin.specs.buttons.search" var="buttonSearch"/>
<input type="submit" name="_eventId_search" value="${buttonSearch }"/>
</form>
</div>
<c:if test="${specs != null}">
	<div class="section">
	<c:if test="${! empty param.q }"><div>
	<em>
	Query results for '${param.q }'
	</em>
	</div>	</c:if>
	
	<app:controlbar>
		<jsp:attribute name="body">
			<app:addicon url="update" message="barkley.admin.specs.createnewentry"/>
		</jsp:attribute>
	</app:controlbar>	

	<table class="results" id="daTableId">
	<thead>
		<tr>
			<th><spring:message code="barkley.admin.specs.th.application"/></th>
			<th><spring:message code="barkley.admin.specs.th.group"/></th>
			<th><spring:message code="barkley.admin.specs.th.spec"/></th>
			<th><spring:message code="barkley.admin.specs.th.dvalue"/></th>
			<th></th>
		</tr>
		</thead>
		<tbody>
		<c:forEach items="${specs}" var="g">
			<tr>
				<td>${g.group.applicationKey}</td>
				<td>${g.group.name} (${g.group.key})</td>
				<td>
					${h:highlight(g.name, param.q)} (${h:highlight(g.key, param.q)})
					<c:if test="${not empty g.description }">
					<br/>
					${h:highlight(g.description, param.q)}
					</c:if>
				</td>
				<td><em>${g.defaultValue}</em></td>
				<td>
				<app:editicon url="update?specId=${g.id }&q=${param.q }"></app:editicon>
				<app:deleteicon url="submit?specId=${g.id}&q=${param.q }&_eventId_delete=_eventId_delete"></app:deleteicon>
				<app:objecthistoryicon objectId="${g.id}" objectType="com.railinc.jook.domain.PreferenceSpec"></app:objecthistoryicon>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	</div>	
</c:if>
</div>

<script>
$().ready(function() { 
	$("#daTableId").tablesorter({widgets: ['zebra']});
    } 
); </script>