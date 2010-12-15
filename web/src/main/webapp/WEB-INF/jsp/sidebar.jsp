<%@ include file="/WEB-INF/jsp/include.jsp"%>
	<c:set value="${pageContext.request.contextPath}/main/global/examples" var="examples"/>
	<c:set value="${pageContext.request.contextPath}/main" var="main"/>
    <div class="sidebar stretchtoheight">

		<ul class="verticalNav">
			
<% if (request.isUserInRole("jook_USER")) { %>
			<li id="module_barkley"><a href="${main }/secure/barkley"><spring:message code="menus.barkley"/></a></li>
			<li id="module_eizoku"><a href="${main }/secure/eizoku"><spring:message code="menus.eizoku"/></a></li>
			<li id="module_downtime"><a href="${main }/secure/downtimes"><spring:message code="menus.downtime"/></a></li>
			<li id="module_news"><a href="${main }/secure/news"><spring:message code="menus.news"/></a></li>
			<li id="module_helpdesk"><a href="${main }/secure/helpdesk"><spring:message code="menus.helpdesk"/></a></li>
<% } %>
			
<% if (request.isUserInRole("jook_SUPPORT")) { %>
			<li id="module_support"><a href="${main }/secure/support"><spring:message code="menus.support.console"/></a></li>
<% } %>
			


<% if (request.isUserInRole("jook_ADMIN")) { %>
			<li id="module_providers"><a href="${main }/secure/providers"><spring:message code="menus.providers"/></a></li>
			<li id="module_interactions"><a href="${main }/secure/interactions"><spring:message code="menus.interactions"/></a></li>
<% } %>
			
		</ul>
	</div>
	<!-- end ~ application menu -->