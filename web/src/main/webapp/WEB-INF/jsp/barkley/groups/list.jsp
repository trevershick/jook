<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<span id="moduleContentIdentifier" class="barkley_groups"></span>
<h1 id="pageTitle"><spring:message code="barkley.admin.breadcrumbs.groups"/></h1>

<div class="formContainer">


<div class="section">
<form>
<spring:message code="barkley.admin.groups.search.prompt"/>
<input type="text" name="q" value="${param.q }" />
<spring:message code="barkley.admin.groups.buttons.search" var="buttonSearch"/>
<input type="submit" name="_eventId_search" value="${buttonSearch }"/>
</form>
</div>
<c:if test="${groups != null}">
	<div class="results">
	<c:if test="${! empty param.q }"><div>
	<em>
	Query results for '${param.q }'
	</em>
	</div>	</c:if>
	
	<app:controlbar>
		<jsp:attribute name="body">
			<app:addicon url="update" message="barkley.admin.groups.createnewentry"/>
		</jsp:attribute>
	</app:controlbar>
	<table class="results" id="groupTable">
	<thead>
		<tr>
			<th width="20%"><spring:message code="barkley.admin.groups.th.appkey"/></th>
			<th><spring:message code="barkley.admin.groups.th.group"/></th>
			<th><spring:message code="barkley.admin.groups.th.actions"/></th>
		</tr>
		</thead>
		<tbody>
		<c:forEach items="${groups}" var="g">
			<tr>
				<td>${g.applicationKey}</td>
				<td>
					${h:highlight(g.name, param.q)} (${h:highlight(g.key, param.q)})
					<c:if test="${not empty g.description }">
					<br/>
					${h:highlight(g.description, param.q)}
					</c:if>
				</td>
				<td align="center" width="15%">
				<app:editicon url="update?groupId=${g.id }&q=${param.q }"/>
				<app:deleteicon url="submit?groupId=${g.id }&q=${param.q }&_eventId_delete=_eventId_delete"/>
				<app:objecthistoryicon objectId="${g.id}" objectType="com.railinc.jook.domain.PreferenceGroup"/>
				
				
				
				
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
	$("#groupTable").tablesorter({widgets: ['zebra']});
    } 
); </script>