<%@ taglib prefix="jook" uri="http://railinc.com/taglib/jook/1.0" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set value="${pageContext.request.contextPath}/railinc-template/images" var="images"/>
<c:set value="${pageContext.request.contextPath}/railinc-template/js" var="scripts"/>
<c:set value="${pageContext.request.contextPath}/railinc-template/css" var="styles"/>
<c:set value="${pageContext.request.contextPath}/" var="basePath"/>

	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="shortcut icon" href="${images}/favicon.ico" />

<!-- Common script libraries -->


<link rel="stylesheet" type="text/css" href="${styles }/reset.css"/>
<link rel="stylesheet" type="text/css" href="${styles }/layout.css"/>
<link rel="stylesheet" type="text/css" href="${styles }/color.css"/>
<link rel="stylesheet" type="text/css" href="${styles }/typography.css"/>
<jook:styles app="jooktest" />

<script type="text/javascript" src="${scripts}/jquery-1.3.2.min.js"></script>
<jook:dependencies app="jooktest" jquery="false"/>

        
