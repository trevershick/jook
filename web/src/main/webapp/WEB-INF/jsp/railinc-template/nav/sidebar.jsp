	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
	
	<c:set value="${pageContext.request.contextPath}/main/global/examples" var="examples"/>
	<c:set value="${pageContext.request.contextPath}/main" var="main"/>


    <div class="sidebar stretchtoheight">

		<ul class="verticalNav">
			
			<li id="module_products"><a href="${main }/secure/products/list">Products</a></li>

			<fmt:message key="support.role" var="supportRole" scope="request" />
					<li id="module_support"><a href="${main }/secure/support"><fmt:message key="menus.support.console"/></a></li>
		</ul>
	</div>
