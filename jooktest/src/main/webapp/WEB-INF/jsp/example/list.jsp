<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<jsp:useBean id="currentDate" class="java.util.Date" scope="request" />

<h1 id="pageTitle">Example Objects List</h1>

<div class="formContainer">
<c:if test="${results != null}">
	<div class="section">

	<table class="data" width="100%">
		<tr>
			<th>Id</th>
			<th>Text</th>
		</tr>
		<c:forEach items="${results.exampleObjects}" var="result">
			<tr>
				<td>${result.id}</td>
				<td>${result.text}</td>
			</tr>
		</c:forEach>
	</table>
	</div>	
</c:if>
</div>
