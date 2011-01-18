<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<h1 id="pageTitle">Query Form</h1>

<form name="simpleForm" method="post" action="">

<div class="formContainer">

	<div class="section">

		<label class="mandatory">Search For</label>
		<input type="text" name="q" value="${param.q }"/>
		<button type="submit">Search</button>
	</div>
</div>
</form>