<%@ include file="/WEB-INF/jsp/include.jsp"%>
<c:set value="${pageContext.request.contextPath}/railinc-template/images" var="images" scope="request"/>
<c:set value="${pageContext.request.contextPath}/railinc-template/js" var="scripts"/>
<c:set value="${pageContext.request.contextPath}/railinc-template/css" var="styles"/>
<c:set value="${pageContext.request.contextPath}/app/images" var="appimages" />
<c:set value="${pageContext.request.contextPath}/app/js" var="appscripts"/>
<c:set value="${pageContext.request.contextPath}/app/style" var="appstyles"/>
<c:set value="${pageContext.request.contextPath}/" var="basePath"/>

	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="shortcut icon" href="${images}/favicon.ico" />

	<!-- Common script libraries -->
	<script type="text/javascript" src="${scripts}/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="${appscripts }/jquery.tablesorter.min.js"></script>
	<script type="text/javascript" src="${appscripts }/jquery-ui-1.8.5.custom.min.js"></script>
	<script type="text/javascript" src="${appscripts }/timepicker.js"></script>

<link rel="stylesheet" type="text/css" href="${styles }/reset.css"/>
<link rel="stylesheet" type="text/css" href="${styles }/layout.css"/>
<link rel="stylesheet" type="text/css" href="${styles }/color.css"/>
<link rel="stylesheet" type="text/css" href="${styles }/typography.css"/>
<link rel="stylesheet" type="text/css" href="${basePath}app/style/app.css"/>
<link rel="stylesheet" type="text/css" href="${basePath}app/style/blitzer/jquery-ui-1.8.5.custom.css"/>

<!-- markItUp! -->
<script type="text/javascript" src="${pageContext.request.contextPath }/markitup/jquery.markitup.js"></script>
<!-- markItUp! toolbar settings -->
<script type="text/javascript" src="${pageContext.request.contextPath }/markitup/sets/html/set.js"></script>
<!-- markItUp! skin -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/markitup/skins/simple/style.css" />
<!--  markItUp! toolbar skin -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/markitup/sets/html/style.css" />