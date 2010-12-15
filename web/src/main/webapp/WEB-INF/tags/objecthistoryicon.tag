<%@ attribute name="objectType" required="true" rtexprvalue="true" %>
<%@ attribute name="objectId" required="true" rtexprvalue="true" %>
<a alt="View Object History" href="#" onclick="showHistory('${objectType }', '${objectId}');return false;">
<img width="16" src="${pageContext.request.contextPath }/railinc-template/images/icons/tag_blue.png"/></a>