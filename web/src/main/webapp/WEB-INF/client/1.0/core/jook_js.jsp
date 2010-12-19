<%@page import="com.railinc.jook.web.util.RequestHelper"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
/*
This JSP will generate javascript that loops over the 'providers' and 
instructs the client java script ot load 'interaction' documents from 
the providers.

By default, the /jook/client/interactions/1.0/interactions.json should
be registered as a jook provider
 
 */
%>
<% RequestHelper helper = new RequestHelper(request); %>
function loadJookInteractions() {
	Jook.init();
	<c:forEach items="${providers}" var="p">
	jQuery.getJSON("${p}", <%=helper.paramsAsJsonHash() %>, Jook.buildLinks);
	</c:forEach>
}
jQuery(document).ready(loadJookInteractions);