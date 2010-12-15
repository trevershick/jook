<%@ include file="/WEB-INF/jsp/include.jsp"%>
<c:set value="${pageContext.request.contextPath}/railinc-template/images" var="images"/>
<span id="moduleContentIdentifier" class="providers"></span>

<h1>
<spring:message code="providers.list.pagetitle"/>
</h1>		
	<app:controlbar>
		<jsp:attribute name="body">
			<app:addicon url="update" message="providers.list.newprovider"/>
		</jsp:attribute>
	</app:controlbar>	

		<table class="results" width="100%" id="providersTable">
		<thead>
		<tr>
			<th><spring:message code="providers.list.th.name"/></th>
			<th><spring:message code="providers.list.th.jsonpath"/></th>
			<th><spring:message code="providers.list.th.apps"/></th>
			<th><spring:message code="providers.list.th.active"/></th>
			<th></th>
		</tr>
		</thead>
		<tbody>
		<c:forEach items="${providers}" var="p">
			<tr>
				<td><a href="details?name=${p.name}">${p.name }</a></td>
				<td>${p.servicesJsonPath }</td>
				<td>${p.applications }</td>
				<td>
<c:choose>
				<c:when test="${p.active }">
					<img width="16" src="${pageContext.request.contextPath }/railinc-template/images/icons/green_check.png"/>
				</c:when>
				<c:otherwise>
					<img width="16" src="${pageContext.request.contextPath }/railinc-template/images/icons/red_x.png"/>
				</c:otherwise>
				</c:choose>
			</td>
				<td>
					<app:editicon url="update?name=${p.name}"/>
					<app:deleteicon url="submit?name=${p.name}&_eventId_delete=_eventId_delete"/>
				</td>				
			</tr>
		</c:forEach>
		</tbody>
	</table>
	

<h1>
<spring:message code="providers.list.integrationlinks.title"/>
</h1>

<a href="${pageContext.request.contextPath }/client/1.0/javascript/jook.js?app=EXAMPLE" target="_new">JavaScript Include</a>
<a href="${pageContext.request.contextPath }/client/interactions/1.0/interactions.json?app=EXAMPLE" target="_new">Interactions JSON</a>
<br/><br/><p>

A Jook Provider is a web service that returns JSON like the example below...  
A Jook service may take 'app' as a parameter. It may also take 'user' as well.  In this way,
the provider can specialize the output based on user and application.
<br/><br/>
<pre>
{'services' : {
    'tab' : [
      { 'title': 'feedback', 'url': '/jook/main/global/feedback/new' },
    ],
  }
}
</pre>
</p>

<h1>Current Response for ${pageContext.request.contextPath }/client/interactions/1.0/interactions.json?app=EXAMPLE</h1>
<pre id="currentJson">
</pre>

<script>
$("#currentJson").load("${pageContext.request.contextPath }/client/interactions/1.0/interactions.json?app=EXAMPLE");
$().ready(
	function() {
		$("#providersTable").tablesorter({widgets: ['zebra'], headers: {4:{sorter:false}}});
	} 
); </script>