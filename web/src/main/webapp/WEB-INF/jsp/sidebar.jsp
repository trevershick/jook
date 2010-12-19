<%@ include file="/WEB-INF/jsp/include.jsp"%>
	<c:set value="${pageContext.request.contextPath}/main/global/examples" var="examples"/>
	<c:set value="${pageContext.request.contextPath}/main" var="main"/>
    <div class="sidebar stretchtoheight">

		<ul class="verticalNav">

<% if (request.isUserInRole("eizoku_user") || request.isUserInRole("eizoku_adm")|| request.isUserInRole("jook_support")) { %>
			<li id="module_eizoku"><a href="${main }/secure/eizoku"><spring:message code="menus.eizoku"/></a></li>
<% } %>

<% if (request.isUserInRole("barkley_user") || request.isUserInRole("barkley_adm")|| request.isUserInRole("jook_support")) { %>
			<li id="module_barkley"><a href="${main }/secure/barkley"><spring:message code="menus.barkley"/></a></li>
<% } %>

<% if (request.isUserInRole("downtime_user") || request.isUserInRole("downtime_adm")|| request.isUserInRole("jook_support")) { %>
			<li id="module_downtime"><a href="${main }/secure/downtimes"><spring:message code="menus.downtime"/></a></li>
<% } %>

<% if (request.isUserInRole("news_user") || request.isUserInRole("news_adm")|| request.isUserInRole("jook_support")) { %>
			<li id="module_news"><a href="${main }/secure/news"><spring:message code="menus.news"/></a></li>
<% } %>

<% if (request.isUserInRole("helpdesk_user") || request.isUserInRole("helpdesk_adm") || request.isUserInRole("jook_support")) { %>
			<li id="module_helpdesk"><a href="${main }/secure/helpdesk"><spring:message code="menus.helpdesk"/></a></li>
<% } %>

			
<% if (request.isUserInRole("jook_adm") || request.isUserInRole("jook_support")) { %>
			<li id="module_jook"><a href="${main }/secure/jook"><spring:message code="menus.jook.main"/></a></li>
<% } %>
	
<% if (request.isUserInRole("jook_support")) { %>
			<li id="module_support"><a href="${main }/secure/support"><spring:message code="menus.support.console"/></a></li>
<% } %>
			
		</ul>
	</div>
	<!-- end ~ application menu -->