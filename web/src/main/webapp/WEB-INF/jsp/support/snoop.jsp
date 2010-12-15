<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<jsp:useBean id="currentDate" class="java.util.Date" scope="request" />
<span id="moduleContentIdentifier" class="support_snoop"></span>

<div id="supportConsole">

<div id="systemLinks" class="supportConsoleSection">
<h1>Basic Request Information</h1>

<table id="requestInfo" class="results">
	<thead>
		<tr>
			<th class="col1">Property Name</th>
			<th class="col2">Property Value</th>
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

<div id="systemLinks" class="supportConsoleSection">
<h1>Cookies</h1>
<table id="cookiesTable" class="results">
	<thead>
		<tr>
			<th class="col1">Domain</th>
			<th class="col2">Path</th>
			<th class="col3">Name</th>
			<th class="col4">Value</th>
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

<div id="systemLinks" class="supportConsoleSection">
<h1>Headers</h1>
<table id="headersTable" class="results">
	<thead>
		<tr>
			<th class="col1">Property Name</th>
			<th class="col2">Property Value</th>
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
