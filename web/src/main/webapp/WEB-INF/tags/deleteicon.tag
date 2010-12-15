<%@ attribute name="url" required="true" rtexprvalue="true" %>
<a onclick="return confirm('Are you sure?');" href="${url }"><img width="16" src="${pageContext.request.contextPath }/railinc-template/images/icons/delete.png"/></a>
