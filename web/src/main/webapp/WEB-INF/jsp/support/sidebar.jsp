<%@ include file="/WEB-INF/jsp/include.jsp"%>
	
<c:set value="${pageContext.request.contextPath}/main" var="main"/>
<c:set value="${pageContext.request.contextPath}/main/secure/support" var="support"/>



 <div class="sidebar2 stretchtoheight">	<ul class="verticalNav">
		
		<li id="module_support_console"><a href="${support }/">Console</a></li>
		<li id="module_support_snoop"><a href="${support }/snoop?${random }"><spring:message code="support.links.snooprequest"/></a></li>
		<li id="module_support_sysprops"><a href="${support }/properties?${random }"><spring:message code="support.links.sysprops"/></a></li>
		<li id="module_support_logging"><a href="${support }/logging/list?${random }"><spring:message code="support.links.log4jlevels"/></a></li>
		<li id="module_support_config"><a href="${support }/configuration/list?${random }"><spring:message code="support.links.configuration"/></a></li>
		</ul>
</div>
	<!-- end ~ application menu -->