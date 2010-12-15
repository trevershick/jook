<%@ attribute name="url" required="true" rtexprvalue="true" %>
<%@ attribute name="message" required="true" rtexprvalue="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" prefix="spring" %>
<img src="${pageContext.request.contextPath }/railinc-template/images/icons/add.png" style="vertical-align:middle" /><a href="${url }"><spring:message code="${message }"/></a>