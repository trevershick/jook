<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<span id="moduleContentIdentifier" class="barkley_prefs"></span>
<h1 id="pageTitle"><spring:message code="barkley.admin.breadcrumbs.prefs"/></h1>
<p>
<spring:message code="barkley.admin.prefs.p"/>
</p>
<form>
<spring:message code="barkley.admin.prefs.search.prompt"/>
<input type="text" name="q" value="${param.q }" />
<spring:message code="barkley.admin.prefs.buttons.search" var="buttonSearch"/>
<input type="submit" name="_eventId_search" value="${buttonSearch }"/>
</form>

<c:if test="${prefs != null}">
	<div class="section">
	<c:if test="${! empty param.q }"><div>
	<em>
	Query results for '${param.q }'
	</em>
	</div>	</c:if>
	
	<app:controlbar>
		<jsp:attribute name="body">
			<app:addicon url="update" message="barkley.admin.prefs.createnewentry"></app:addicon>
		</jsp:attribute>
	</app:controlbar>
	<table class="results" id="daTableId">
	<thead>
		<tr>
			<th><spring:message code="barkley.admin.prefs.th.application"/></th>
			<th><spring:message code="barkley.admin.prefs.th.group"/></th>
			<th><spring:message code="barkley.admin.prefs.th.spec"/></th>
			<th><spring:message code="barkley.admin.prefs.th.user"/></th>
			<th><spring:message code="barkley.admin.prefs.th.pref"/></th>
			<th></th>
		</tr>
		</thead>
		<tbody>
		<c:forEach items="${prefs}" var="p">
			<tr>
				<td>${h:highlight(p.specification.group.applicationKey, param.q)}</td>
				<td>${p.specification.group.name} (${p.specification.group.key})</td>
				<td>${h:highlight(p.specification.name, param.q)} (${h:highlight(p.specification.key, param.q)})</td>
				<td>${h:highlight(p.userId, param.q)}</td>
				<td>
					<font size="-2">${h:highlight(p.value, param.q)}</font>
				</td>
				<td>
				<app:editicon url="update?id=${result.id }"/>
				<app:deleteicon url="update?prefId=${p.id }&q=${param.q }"/>
				<app:objecthistoryicon objectId="${result.id}" objectType="com.railinc.jook.domain.PreferenceSpec"/>			
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	</div>	
</c:if>

<script>
$().ready(function() { 
	$("#daTableId").tablesorter({widgets: ['zebra']});
	        
    } 
); </script>