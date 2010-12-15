<%@page import="com.railinc.jook.web.util.RequestHelper"%>
<%@page import="java.util.List"%>
<%@page import="com.railinc.jook.domain.JookInteractionProvider"%>
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
	//jQuery.getJSON('/jook/client/interactions/1.0/interactions.json', <%=helper.paramsAsJsonHash() %>, buildLinks);
<%
 	List providers = (List) request.getAttribute("providers");
 	for (int i=0;providers != null && i<providers.size();i++) {
 		JookInteractionProvider p = (JookInteractionProvider) providers.get(i);
 %>
	jQuery.getJSON("<%= p.getServicesJsonPath() %>", <%=helper.paramsAsJsonHash() %>, Jook.buildLinks);
<% } %>
}
jQuery(document).ready(loadJookInteractions);