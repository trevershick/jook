<%@ include file="/WEB-INF/jsp/include.jsp"%>
	
	<c:set value="${pageContext.request.contextPath}/main" var="main"/>
	<c:set value="${pageContext.request.contextPath}/main/secure/support" var="support"/>



	 <div class="sidebar2 stretchtoheight">	
	 	<ul class="verticalNav">
			<li id="module_jook_providers"><a href="${main }/secure/jook/providers"><spring:message code="menus.providers"/></a></li>
			<li id="module_jook_interactions"><a href="${main }/secure/jook/interactions"><spring:message code="menus.interactions"/></a></li>
		</ul>
	</div>
	<!-- end ~ application menu -->