<img src="${pageContext.request.contextPath }/mysupportresources/images/${ incident.browser == null ? 'declined' : 'approved'}.png"/>
<p>${ incident.browser == null ? 'Unknown' : incident.browser}</p>