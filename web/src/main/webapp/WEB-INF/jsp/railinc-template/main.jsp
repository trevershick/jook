<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">
<head>
	<tiles:useAttribute name="appTitle" ignore="true" scope="request"/>
	<tiles:useAttribute name="sidebarcolumns" />
	<title>${appTitle }</title>
	<tiles:insertAttribute name="headContent"/>
	<tiles:insertAttribute name="additionalHeadContent"/>
</head>
<body>


<div id="wrapper">

	<tiles:insertAttribute name="headerContent"/>
	
	<tiles:insertAttribute name="sidebarHead"/>
	<tiles:insertAttribute name="sidebarContent"/>
   
	<tiles:insertAttribute name="sidebarTail"/>
	<div id="container_${ sidebarcolumns}_cols" class="hidden">


	<!-- working message -->
	<c:if test="${messages != null}">
		<div class="noticeBox noticeOk">
		<ul>
		<c:forEach items="${messages}" var="msg">
		<li>${msg }</li>
		</c:forEach>
		</ul>
		</div>
	</c:if>
	<c:if test="${warnings != null}">
		<div class="noticeBox noticeWarning">
		<ul>
		<c:forEach items="${warnings}" var="msg">
		<li>${msg }</li>
		</c:forEach>
		</ul>
		</div>
	</c:if>
	<c:if test="${errors != null}">
		<div class="noticeBox noticeError">
		<ul>
		<c:forEach items="${errors}" var="msg">
		<li>${msg }</li>
		</c:forEach>
		</ul>
		</div>
	</c:if>
	

		<tiles:insertAttribute name="content"/>
		
<div id="objectHistory">
	
</div>


		
		
		<div id="contentSpacer"></div>
	</div><!-- end content -->

		<tiles:insertAttribute name="footerContent"/>



</div>
<script>
// highlight the menu
$().ready(function() {
	var mci = $("#moduleContentIdentifier");
	if (mci) {
		var moduleContentIdentifier = mci.attr("class");
		if (moduleContentIdentifier != undefined) {
		var identifiers = moduleContentIdentifier.split("_");
		var identifier = "#module";
		for (var i=0;i < identifiers.length;i++){
			identifier += "_" + identifiers[i];
			var navBarLi = $(identifier);
			if (navBarLi) navBarLi.addClass("navItemHighlight");
		}
		}
	}
});
$().ready(function() {
	$(".stretchtoheight").height(Math.max($("div#container_${ sidebarcolumns}_cols").height(), $(".stretchtoheight").height()));
});
$().ready(function() {
	// had to switch this for tablesorter
	$('table.results tbody tr:odd').addClass('odd');
	$('table.results tbody tr:even').addClass('even');
});


function showTx(id) {
	$('#objectHistory').html('<img width="16" src="${pageContext.request.contextPath }/mysupportresources/images/spinner.gif"/>');
	$('#objectHistory').load('/jook/main/secure/auditlog/snippet/txhistory?tx=' + id);
}
function showHistory(name,id) {
	$('#objectHistory').html('<img width="16" src="${pageContext.request.contextPath }/mysupportresources/images/spinner.gif"/>');
	$('#objectHistory').load('/jook/main/secure/auditlog/snippet/objecthistory?objType=' + name + '&objId=' + id);
}


</script>
<jook:script app="jooktest" />

</body>
</html>
