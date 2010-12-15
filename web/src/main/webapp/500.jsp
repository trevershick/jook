<%@page isErrorPage="true" %>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<tiles:definition  name="my404" extends=".railincMain">
	<tiles:putAttribute name="content" type="string">
			<h3>Unexpected Error</h3>
			<br>
			<div id="error">An Unexpected error has Occurred</div>
			<div>
			<h3><%= request.getAttribute("javax.servlet.error.status_code") %></h3>
			<h3>Message</h3>
			<%= request.getAttribute("javax.servlet.error.message") %>
			</div>
	</tiles:putAttribute>
</tiles:definition >
<tiles:insertDefinition name="my404" />