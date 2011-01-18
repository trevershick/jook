<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<jsp:useBean id="currentDate" class="java.util.Date" scope="request" />


<h1 id="pageTitle"> <a href="${pageContext.request.contextPath }/main/secure/support"><fmt:message key="support.breadcrumbs.console"/></a> 
	<fmt:message key="support.breadcrumbs.separator"/> 
	<fmt:message key="support.breadcrumbs.snoop">
		<fmt:param value="${hostname}"/>
		<fmt:param value="${address}"/>
	</fmt:message></h1>
<div class="formContainer">

<span class="sectionHeader">Basic Request Information</span>
<div class="section">
	<table width="100%" class="data">
		<thead>
			<tr>
				<th align="left">Property Name</th>
				<th align="left">Property Value</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>Local Name</td>
				<td><%=request.getLocalName()%></td>
			</tr>
			<tr>
				<td>Local Port</td>
				<td><%=request.getLocalPort()%></td>
			</tr>
			<tr>
				<td>Remote Address</td>
				<td><%=request.getRemoteAddr()%></td>
			</tr>
			<tr>
				<td>Remote Port</td>
				<td><%=request.getRemotePort()%></td>
			</tr>
			<tr>
				<td>Remote Host</td>
				<td><%=request.getRemoteHost()%></td>
			</tr>
			<tr>
				<td>Remote User</td>
				<td><%=request.getRemoteUser()%></td>
			</tr>
		</tbody>
	</table>
		
</div>

<span class="sectionHeader">Cookies</span>
<div class="section">
	<table width="100%" class="data">
		<thead>
			<tr>
				<th align="left">Domain</th>
				<th align="left">Path</th>
				<th align="left">Name</th>
				<th align="left">Value</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="<%=request.getCookies()%>" var="c">
			<tr>
				<td>${c.domain}</td>
				<td>${c.path }</td>
				<td>${c.name }</td>
				<td>${c.value }</td>
			</tr>			
			</c:forEach>
		</tbody>
	</table>
</div>

<span class="sectionHeader">Headers</span>
<div class="section">
	<table width="100%" class="data">
		<thead>
			<tr>
				<th align="left">Property Name</th>
				<th align="left">Property Value</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${header}" var="n">
			<tr>
				<td>${n.key}</td>
				<td>${n.value}</td>
			</tr>			
			</c:forEach>
		</tbody>
	</table>
	
</div>



	
</div>	
