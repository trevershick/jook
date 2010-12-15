<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>

<%@page import="java.net.URLEncoder"%><jsp:useBean id="currentDate" class="java.util.Date" scope="request" />
<span id="moduleContentIdentifier" class="barkley_uprefs"></span>

<p>
<spring:message code="barkley.prefs.upreferences.p"/>
</p>

<h1><spring:message code="barkley.prefs.upreferences.serviceurls.header"/></h1>
<c:if test="${! empty param.user && ! empty param.app}">
<ul>
<li><a href="${pageContext.request.contextPath }/main/global/service/preferences?user=${param.user }&app=${param.app }">/barkley/main/global/service?user=${param.user }&app=${param.app }</a></li>
<li><a href="${pageContext.request.contextPath }/main/global/service/preferences?user=${param.user }&app=${param.app }&format=xml">/barkley/main/global/service?user=${param.user }&app=${param.app }&format=xml</a></li>
</ul>
</c:if>


<c:if test="${empty param.user || empty param.app}">
<spring:message code="barkley.prefs.upreferences.serviceurls.mustfillinfields"/>
</c:if>

<div class="formContainer">
	<div class="section">
	<form>
			<spring:message code="barkley.prefs.upreferences.searchfield.app.prompt"  />
			<select name="app">
				<option value=""><spring:message code="barkley.prefs.upreferences.app.prompttext"/></option>
				<c:forEach items="${applications}" var="a">
				<c:choose>
					<c:when test="${a == param.app }">
						<option selected="selected">${a }</option>
					</c:when>
					<c:otherwise>
						<option>${a }</option>
					</c:otherwise>
				</c:choose>
				</c:forEach>
			</select>
			<spring:message code="barkley.prefs.upreferences.searchfield.user.prompt"  />
			<input name="user" value="${param.user }"/>
			<spring:message code="barkley.prefs.upreferences.buttons.search" var="searchButtonText" />
			<input type="submit" value="${searchButtonText }"/>
	</form>
	</div>
	<div class="section">
	<table class="results" id="daTableId">
	<thead>
		<tr>
			<th><spring:message code="barkley.prefs.upreferences.th.isdefault"/></th>
			<th width="10%"><spring:message code="barkley.prefs.upreferences.th.app"/></th>
			<th width="20%"><spring:message code="barkley.prefs.upreferences.th.group"/></th>
			<th><spring:message code="barkley.prefs.upreferences.th.user"/></th>
			<th><spring:message code="barkley.prefs.upreferences.th.spec"/></th>
			<th><spring:message code="barkley.prefs.upreferences.th.value"/></th>
			<th><spring:message code="barkley.prefs.upreferences.th.dvalue"/></th>
		</tr>
		</thead>
		<tbody>
		<c:forEach items="${preferences}" var="result">
			<tr>
				<td>
					<c:if test="${empty result.id }">
					<img width="16" "src="${pageContext.request.contextPath }/railinc-template/images/icons/green_check.png"/>
					</c:if>
				</td>
				<td>${result.specification.group.applicationKey}</td>
				<td>${result.specification.group.name}<br/>
				<td>${result.userId}</td>
				<td>${result.specification.name} (${result.specification.key})<br/>
				<font size="-2">${result.specification.description}</font>
				</td>
				<td>${result.value}</td>
				<td>${result.specification.defaultValue}</td>
			</tr>
		</c:forEach>
		<c:if test="${empty preferences && !empty param.user && !empty param.app}">
			<tr>
				<td colspan="5"><spring:message code="barkley.prefs.upreferences.noresults"/></td>
			</tr>
		</c:if>
		</tbody>
	</table>
	</div>	
</div>

<script>
$().ready(function() { 
	$("#daTableId").tablesorter({widgets: ['zebra']});
	        
    } 
); </script>


<c:if test="${not empty param.user and not empty param.app}">


<h1>JSON Results</h1>
<div id="jsonResults">
</div>

<script>
$().ready(function() 
    { 
        $("#jsonResults").load("${pageContext.request.contextPath }/main/global/service/preferences?user=${param.user }&app=${param.app }");         
    } 
); 
</script>
</c:if>