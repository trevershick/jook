<%@ page language="java" contentType="application/json" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
{
<c:forEach items="${news}" var="article">
	{ title : "${article.headline }", description : "${article.body }", published : "${article.published }" }
</c:forEach>
}