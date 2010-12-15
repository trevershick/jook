<%@ include file="/WEB-INF/jsp/include.jsp"%>

<c:set value="${pageContext.request.contextPath}/main" var="main"/>
<c:set value="${pageContext.request.contextPath}/main/secure/support" var="support"/>



 <div class="sidebar2 stretchtoheight">	
 	<ul class="verticalNav">
		<li id="module_eizoku_regedlinks"><a href="${main }/secure/eizoku/link/list">Registered Links</a></li>
		<li id="module_eizoku_cnewlink"><a href="${main }/secure/eizoku/link/create">Create a Link</a></li>
	</ul>
</div>
<!-- end ~ application menu -->