<%@ include file="/WEB-INF/jsp/include.jsp"%>
	
	<c:set value="${pageContext.request.contextPath}/main" var="main"/>
	<c:set value="${pageContext.request.contextPath}/main/secure/support" var="support"/>



	 <div class="sidebar2 stretchtoheight">	
	 	<ul class="verticalNav">
			<li id="module_barkley_groups"><a href="${main }/secure/barkley/groups/list"><spring:message code="menus.barkley.preferences.groups"/></a></li>
			<li id="module_barkley_specs"><a href="${main }/secure/barkley/specs/list"><spring:message code="menus.barkley.preferences.specs"/></a></li>
			<li id="module_barkley_prefs"><a href="${main }/secure/barkley/prefs/list"><spring:message code="menus.barkley.preferences.prefs"/></a></li>
			<li id="module_barkley_pdefaults"><a href="${main }/secure/barkley/test/pdefaults"><spring:message code="menus.barkley.preferences.pdefaults"/></a></li>
			<li id="module_barkley_uprefs"><a href="${main }/secure/barkley/test/upreferences"><spring:message code="menus.barkley.preferences.upreferences"/></a></li>
			<li id="module_barkley_uoverrides"><a href="${main }/secure/barkley/test/uoverrides"><spring:message code="menus.barkley.preferences.uoverrides"/></a></li>

		</ul>
	</div>
	<!-- end ~ application menu -->