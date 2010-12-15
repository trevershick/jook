<%@ page language="java" contentType="text/html charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>

<span id="moduleContentIdentifier" class="helpdesk"></span>

	<app:controlbar>
		<jsp:attribute name="body">
			<img src="${images }/icons/arrow_left.png" style="vertical-align:middle" /> <a href="list"><spring:message code="incidents.backtolist"/></a>
		</jsp:attribute>
	</app:controlbar>	


<table class="results">
	<caption>
	<spring:message code="incidents.details.incidentinfo"/>
	</caption>
	<tr>
		<td class="fieldname">
			<spring:message code="incidents.fields.id" />
		</td>
		<td>${incident.id}</td>
	</tr>
	<tr>
		<td class="fieldname">
			<spring:message code="incidents.fields.rapidCaseId" />
		</td>
		<td>${incident.rapidCaseId}</td>
	</tr>
		<tr>
		<td class="fieldname">
			<spring:message code="incidents.fields.incidentTime" />
		</td>
		<td>${incident.incidentTime}</td>
	</tr>
	
</table>


<table class="results">

<caption>
	<spring:message code="incidents.details.demographics"/>
</caption>
	<tr>
		<td class="fieldname">
			<spring:message code="incidents.fields.ssoUserId" />
		</td>
		<td>${incident.ssoUserId}</td>
	</tr>	

	<tr>
		<td class="fieldname">
			<spring:message code="incidents.fields.ssoRoles" />
		</td>
		<td>${incident.ssoRoles}</td>
	</tr>

	<tr>
		<td class="fieldname">
			<spring:message code="incidents.fields.name" />
		</td>
		<td>${incident.name}</td>
	</tr>
		<tr>
		<td class="fieldname">
			<spring:message code="incidents.fields.userEmail" />
		</td>
		<td>${incident.userEmail}</td>
	</tr>
		<tr>
		<td class="fieldname">
			<spring:message code="incidents.fields.company" />
		</td>
		<td>${incident.company}</td>
	</tr>
		<tr>
		<td class="fieldname">
			<spring:message code="incidents.fields.phone" />
		</td>
		<td>${incident.phone}</td>
	</tr>
</table>



<table class="results">
<caption>
	<spring:message code="incidents.details.environment"/>
</caption>
	<tr>
		<td class="fieldname">
			<spring:message code="incidents.fields.operatingSystem" />
		</td>
		<td>${incident.operatingSystem }</td>
	</tr>
	<tr>
		<td class="fieldname">
			<spring:message code="incidents.fields.browser" />
		</td>
		<td>${incident.browser }</td>
	</tr>
	<tr>
		<td class="fieldname">
			<spring:message code="incidents.fields.ipAddress" />
		</td>
		<td>${incident.ipAddress }</td>
	</tr>
	<tr>
		<td class="fieldname">
			<spring:message code="incidents.fields.product" />
		</td>
		<td>${incident.product }</td>
	</tr>
		<tr>
		<td class="fieldname">
			<spring:message code="incidents.fields.moduleId" />
		</td>
		<td>${incident.moduleId}</td>
	</tr>
		<tr>
		<td class="fieldname">
			<spring:message code="incidents.fields.cookiesEnabled" />
		</td>
		<td>${incident.cookiesEnabled}</td>
	</tr>
	<tr>
		<td class="fieldname">
			<spring:message code="incidents.fields.screenResolution" />
		</td>
		<td>${incident.screenWidth} x ${incident.screenHeight }</td>
	</tr>	

	<tr>
		<td class="fieldname">
			<spring:message code="incidents.fields.browserSize" />
		</td>
		<td>${incident.browserWidth} x ${incident.browserHeight }</td>
	</tr>

	<tr>
		<td class="fieldname">
			<spring:message code="incidents.fields.flashVersion" />
		</td>
		<td>${incident.flashVersion}</td>
	</tr>
	
		<tr>
		<td class="fieldname">
			<spring:message code="incidents.fields.sendDetails" />
		</td>
		<td>${incident.sendDetails}</td>
	</tr>
		<tr>
		<td class="fieldname">
			<spring:message code="incidents.fields.stackTrace" />
		</td>
		<td><pre>${incident.stackTrace}</pre></td>
	</tr>
		
	<tr>
		<td class="fieldname">
			<spring:message code="incidents.fields.requestDetails" />
		</td>
		<td><pre>${incident.requestDetails}</pre></td>
	</tr>
	

</table>


