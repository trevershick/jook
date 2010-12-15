<%@ include file="/WEB-INF/jsp/include.jsp"%>
<tiles:definition  name="my404" extends=".railincMain">
	<tiles:putAttribute name="content" type="string">
	The page you were trying to find is NOT available.
	</tiles:putAttribute>
</tiles:definition >
<tiles:insertDefinition name="my404" />
