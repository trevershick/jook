<%@page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="nm"><portlet:namespace/></c:set>
<portlet:renderURL windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>" var="theUrl"/>


<div id="${nm}rapidcasecount">
<!-- Build Status for ${product }<br/> -->

<table>
<tr>
	<th>Build</th>
	<th>Status</th>
</tr>
<c:forEach var="s" items="${status }">
<tr>
	<td>${s['name'] }</td>
	<td>${s['status'] }</td>
</tr>

</c:forEach>

</table>

<script language="JavaScript">
function ${nm}doit() {
	jQuery("#${nm}rapidcasecount").html("Loading...");
	jQuery("#${nm}rapidcasecount").load("${theUrl}");
}

if (!window.${nm}intervalId) {
	window.${nm}intervalId = setInterval(${nm}doit, 30000);
}
</script>
</div>