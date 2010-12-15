<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>

<%@page import="java.net.URLEncoder"%><jsp:useBean id="currentDate" class="java.util.Date" scope="request" />
<span id="moduleContentIdentifier" class="barkley_pdefaults"></span>
<h1 id="pageTitle"><spring:message code="barkley.prefs.breadcrumbs.pdefaults"/></h1>
<p>
<spring:message code="barkley.prefs.pdefaults.p"/>
</p>

<div class="formContainer">
	<div class="section">
			<form>
			
			<spring:message code="barkley.prefs.pdefaults.searchfield.app.prompt"  />
			
			
			<select name="app">
				<option value=""><spring:message code="barkley.prefs.pdefaults.app.prompttext"/></option>
				<c:forEach items="${applications}" var="a">
				<option>${a }</option>
				</c:forEach>
			</select>
						<spring:message code="barkley.prefs.pdefaults.buttons.search" var="searchButtonText" />
			<input type="submit" value="${searchButtonText }"/>
			
			</form>
	</div>
	<div class="section">
	
	<table class="results" id="daTableId">
	<thead>
		<tr>
			<th width="10%"><spring:message code="barkley.prefs.pdefaults.th.app"/></th>
			<th width="20%"><spring:message code="barkley.prefs.pdefaults.th.group"/></th>
			<th><spring:message code="barkley.prefs.pdefaults.th.spec"/></th>
			<th><spring:message code="barkley.prefs.pdefaults.th.default"/></th>
		</tr>
		</thead>
		<tbody>
		<c:forEach items="${defaults}" var="result">
			<tr>
				<td>${result.specification.group.applicationKey}</td>
				<td>${result.specification.group.name}</td>
				<td>${result.specification.name} (${result.specification.key})<br/>
				<font size="-2">${result.specification.description}</font></td>
				<td>${result.value}</td>
			</tr>
		</c:forEach>
		<c:if test="${empty defaults && !empty param.app}">
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