<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<c:set value="${pageContext.request.contextPath}/main" var="main"/>
<jsp:useBean id="currentDate" class="java.util.Date" scope="request" />
<span id="moduleContentIdentifier" class="support_console"></span>
<div id="supportConsole">
<div id="serverInfo" class="supportConsoleSection">
	<h1><spring:message code="support.serverinfo.header"/></h1>
	<ul>
		<li><spring:message code="support.field.hostname"/> <spring:message code="support.field.separator"/> ${hostname }</li>
		<li><spring:message code="support.field.address"/> <spring:message code="support.field.separator"/> ${address }</li>
		<li><spring:message code="support.field.localport"/> <spring:message code="support.field.separator"/> <%=request.getLocalPort() %></li>
		<li><spring:message code="support.field.contextpath"/> <spring:message code="support.field.separator"/> <%=request.getContextPath() %></li>
		<li><spring:message code="support.field.serverinfo"/> <spring:message code="support.field.separator"/> <%=config.getServletContext().getServerInfo() %></li>
	</ul>
</div>
<div id="systemLinks" class="supportConsoleSection">
	<h1><spring:message code="support.links.header"/></h1>
	<ul>
	<li><a href="${main}/global/rem"><spring:message code="support.links.rem"/></a></li>
	<li><a href="${main}/global/version"><spring:message code="support.links.version"/></a></li>
	</ul>
</div>

<div id="svnInfo" class="supportConsoleSection">
	<h1><spring:message code="support.svnrevision.header"/></h1>
	<p>
	<c:forEach items="${svnRevisionText}" var="line">
	${line }<br/>
	</c:forEach>
	</p>
</div>

<div id="buildInfo" class="supportConsoleSection">
<h1><spring:message code="support.buildinfo.header"/></h1>
<p>	${versionText}</p>
</div>

<h1>Web Endpoints</h1>
<embed src="${pageContext.request.contextPath }/test.svg" width="640" height="480" type="image/svg+xml" pluginspage="http://www.adobe.com/svg/viewer/install/" />

</div>