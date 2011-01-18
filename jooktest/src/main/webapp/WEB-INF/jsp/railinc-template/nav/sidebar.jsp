	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
	
	<c:set value="${pageContext.request.contextPath}/main/global/examples" var="examples"/>
	<c:set value="${pageContext.request.contextPath}/main" var="main"/>



		<ul>
			
			<li><a href="${main }/explicitlyMapped">Explicitly Mapped Controller</a></li>
					<li><a href="${main }/secure/example/list">List Example Objects</a></li>
					<li><a href="${main }/secure/example/query">Query Example Objects</a></li>
					<li><a href="${main }/global/error1">Cause An Error</a></li>

			<fmt:message key="support.role" var="supportRole" scope="request" />
			<% if (request.isUserInRole((String) request.getAttribute("supportRole"))) {%>
					<li><a href="${main }/secure/support" style="color:red;background-color:yellow;"><fmt:message key="menus.support.console"/></a></li>
			<% } %>
		</ul>

	<!-- end ~ application menu -->