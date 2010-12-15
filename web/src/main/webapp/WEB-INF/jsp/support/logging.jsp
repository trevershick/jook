<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>

<%@page import="java.net.URLEncoder"%><jsp:useBean id="currentDate" class="java.util.Date" scope="request" />
<span id="moduleContentIdentifier" class="support_logging"></span>


<c:if test="${currentLoggers != null}">
	<div class="section">
	<form action="update">
	<table class="results">
		<tr>
			<th><spring:message code="support.logging.th.category"/></th>
			<th><spring:message code="support.logging.th.currentlevel"/></th>
			<th><spring:message code="support.logging.th.newlevel"/></th>
		</tr>
		<tr>
			<td><input name="n" size="30"/></td>
			<td>
				<select name="l">
					<option value="DEBUG"><spring:message code="support.logging.DEBUG"/></option>
					<option value="INFO"><spring:message code="support.logging.INFO"/></option>
					<option value="WARN"><spring:message code="support.logging.WARN"/></option>
					<option value="ERROR"><spring:message code="support.logging.ERROR"/></option>
					<option value="FATAL"><spring:message code="support.logging.FATAL"/></option>
				</select>
			</td>
			<td>
				<input type="submit" value="Add"/>
			</td>
		</tr>
		<c:forEach items="${currentLoggers}" var="result">
			<tr>
				<td>${result.name}</td>
				<td><spring:message code="support.logging.${result.level}"/></td>
				<td style="white-space:nowrap;">
					<c:if test="${result.level != 'DEBUG' }">
					<a href="update?n=${result.name }&l=DEBUG&r=${random }"><spring:message code="support.logging.DEBUG"/></a>
					</c:if><spring:message code="support.logging.separator"/>
					<c:if test="${result.level != 'INFO' }">
					<a href="update?n=${result.name }&l=INFO&r=${random }"><spring:message code="support.logging.INFO"/></a>
					</c:if><spring:message code="support.logging.separator"/>
					<c:if test="${result.level != 'WARN' }">
					<a href="update?n=${result.name }&l=WARN&r=${random }"><spring:message code="support.logging.WARN"/></a>
					</c:if><spring:message code="support.logging.separator"/>
					<c:if test="${result.level != 'ERROR' }">
					<a href="update?n=${result.name }&l=ERROR&r=${random }"><spring:message code="support.logging.ERROR"/></a>
					</c:if><spring:message code="support.logging.separator"/>
					<c:if test="${result.level != 'FATAL' }">
					<a href="update?n=${result.name }&l=FATAL&r=${random }"><spring:message code="support.logging.FATAL"/></a>
					</c:if>
				</td>
			</tr>
		</c:forEach>
	</table>
	</form>
	</div>	
</c:if>
