<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>

<%@page import="java.net.URLEncoder"%><jsp:useBean id="currentDate" class="java.util.Date" scope="request" />
<span id="moduleContentIdentifier" class="barkley_uoverrides"></span>
<h1 id="pageTitle"><spring:message code="barkley.prefs.breadcrumbs.uoverrides"/></h1>
<p>
<spring:message code="barkley.prefs.uoverrides.p"/>
</p>

<div class="formContainer">
	<div class="section">
	<form>
			<spring:message code="barkley.prefs.uoverrides.searchfield.app.prompt"  />
			<select name="app" onchange="this.form.submit();">
				<option value=""><spring:message code="barkley.prefs.uoverrides.app.prompttext"/></option>
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
			<spring:message code="barkley.prefs.uoverrides.searchfield.user.prompt"  />
			<input name="user" value="${param.user }"/>
			<spring:message code="barkley.prefs.uoverrides.buttons.search" var="searchButtonText" />
			<input type="submit" value="${searchButtonText }"/>
	</form>
	</div>
	<div class="section">
	<table class="results" id="daTableId">
	<thead>
		<tr>
			<th width="10%"><spring:message code="barkley.prefs.uoverrides.th.app"/></th>
			<th width="20%"><spring:message code="barkley.prefs.uoverrides.th.group"/></th>
			<th><spring:message code="barkley.prefs.uoverrides.th.spec"/></th>
			<th><spring:message code="barkley.prefs.uoverrides.th.value"/></th>
			<th><spring:message code="barkley.prefs.uoverrides.th.dvalue"/></th>
		</tr>
		</thead>
		<tbody>
		<c:forEach items="${overrides}" var="result">
			<tr>
				<td>${result.specification.group.applicationKey}</td>
				<td>${result.specification.group.name}<br/>
				<font size="-2">${result.specification.group.description}</font></td>
				<td>${result.specification.name} (${result.specification.key})<br/>
				<font size="-2">${result.specification.description}</font>
				</td>
				<td>${result.value}</td>
				<td>${result.specification.defaultValue}</td>
			</tr>
		</c:forEach>
		<c:if test="${empty overrides && !empty param.user && !empty param.app}">
			<tr>
				<td colspan="5"><spring:message code="barkley.prefs.uoverrides.noresults"/></td>
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