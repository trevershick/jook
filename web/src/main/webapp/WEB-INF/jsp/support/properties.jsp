<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<jsp:useBean id="currentDate" class="java.util.Date" scope="request" />
<span id="moduleContentIdentifier" class="support_sysprops"></span>

<div id="supportConsole">
<c:if test="${properties != null}">
	<table class="results" id="syspropsTable">
	<thead>
		<tr>
			<th class="col1"><spring:message code="support.sysprops.th.key" /></th>
			<th class="col2"><spring:message code="support.sysprops.th.value" /></th>
		</tr>
		</thead>
		<tbody>
		<c:forEach items="${properties}" var="result">
			<tr>
				<td>${result.key}</td>
				<td>
					<c:choose>
						<c:when test="${result.key == 'SSO_USRDATA' }">
						${fn:replace(result.value,"*"," ")}
						</c:when>
						<c:otherwise>${fn:replace(fn:replace(result.value,";","; "), ",", ", ")}</c:otherwise>
					</c:choose>
					
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
</c:if>
</div>
