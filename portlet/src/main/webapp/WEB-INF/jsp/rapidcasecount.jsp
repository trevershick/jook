<%@page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="nm"><portlet:namespace/></c:set>
<div id="${nm}rapidcasecount">

<table width="100%">
	<tr>
		<th nowrap="nowrap">Case #</th>
		<th>Title</th>
		<th>Submitted</th>
	</tr>
<c:forEach var="t" items="${tickets }">
	<tr>
		<td>${t.id }</td>
		<td>${t.title }</td>
		<td nowrap="nowrap"><fmt:formatDate value="${t.submitDate }" type="date" dateStyle="short" /></td>
	</tr>	
<!-- 	String assignees;
	String firstName;
	long id;
	String impact;

	String lastName;
	String status;
	Date submitDate;
	String submitter;
	String title;
	String urgency;
 -->

</c:forEach>
</table>


<portlet:renderURL windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>" var="theUrl"/>
<script language="JavaScript">

function ${nm}doit() {
	jQuery("#${nm}rapidcasecount").html("Loading...");
	jQuery("#${nm}rapidcasecount").load("${theUrl}");
}

if (!window.${nm}intervalId) {
	window.${nm}intervalId = setInterval(${nm}doit, 120000);
}

</script>
</div>