<%@page import="com.railinc.jook.web.util.RequestHelper"%>
<%
	/**
		Called by the interactions servlet to return JSON to the client JavaScript	
	*/
RequestHelper helper = new RequestHelper(request);
Map<?,?> is = (Map<?,?>) request.getAttribute("interactions");
%>{ "services" : { <% 
		Iterator<?> typeIterator = is.keySet().iterator();
		boolean sawFirst = false;
		while (typeIterator.hasNext()) {
			String type = typeIterator.next().toString();
			if (sawFirst) {
				out.write(",");
			}
			sawFirst = true; %> 
	"<%=type %>" : [ <%
			Collection<?> collectionByType = (Collection<?>) is.get(type);
			Iterator<?> i = collectionByType.iterator();
			boolean first = true;
			while (i.hasNext()) {
				JookInteraction ji = (JookInteraction) i.next();
				if (!first) {%>,<%}%> { "title" : "<%=ji.getTitle() %>", "url" : "<%= helper.substituteParameters(ji.getUrl()) %>"<% if (ji.shake()){%>, "shake" : "true"<% } %> } <% 
				first = false;
	} %> ] <% } %>
	}
}<%@page import="com.railinc.jook.interaction.JookInteraction"%><%@page import="java.util.*"%><%@page import="java.util.Collection"%><%@page contentType="application/json" %>