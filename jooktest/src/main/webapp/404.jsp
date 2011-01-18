<%@page isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set value="${pageContext.request.contextPath}/railinc-template/images" var="images"/>
<c:set value="${pageContext.request.contextPath}/railinc-template/scripts" var="scripts"/>
<c:set value="${pageContext.request.contextPath}/railinc-template/styles" var="styles"/>
<c:set value="${pageContext.request.contextPath}/" var="basePath"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link type="text/css" rel="stylesheet" media="screen, projection, print" href="${styles}/stylesCustom.css" />
<link type="text/css" rel="stylesheet" media="screen, projection, print" href="${styles}/stylesStandard.css" />
<link rel="shortcut icon" href="${images}/favicon.ico" />
</head>
<body>
	<div id="uiStId_appTitle">
	  <table>
			<tr>
				<td nowrap="nowrap">
					<a href="http://www.railinc.com" target="_blank"><img alt="Railinc" title="Railinc" src="${images}/railincLogo.gif" class="inline" /></a>
					<span class="uiSt_appTitleText"><a href="${landingUrl }" title="${appTitle }">${appTitle }</a></span>
				</td>
				<td align="right" nowrap="nowrap">
					<div class="uiSt_globalNav">
					<c:forEach items="${headerLinks}" var="item" varStatus="idx">
						<c:if test="${idx.index > 0}"> | 
						</c:if>
						<a title="${item.value }" href="${item.link }">${fn:toLowerCase(item.value)}</a>
					</c:forEach>
					</div>
				</td>
			</tr>
		</table>
	</div>
<h3>${parentArtifactId } - Page Not Found</h3>
<br>
<div id="error">The page you're trying to load doesn't exist.</div>
<h3>Message</h3>
<%= request.getAttribute("javax.servlet.error.message") %>
<%
  out.println("Exception: " + exception);
%>
<br/>
</body>
</html>