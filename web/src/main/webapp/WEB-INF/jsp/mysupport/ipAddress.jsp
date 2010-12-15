<img src="${pageContext.request.contextPath }/mysupportresources/images/${ incident.ipAddress != null ? 'approved' : 'declined'}.png"/>
<p>${ incident.ipAddress != null  ? incident.ipAddress : 'Unknown' }</p>
