<%@ attribute name="showCancel" required="false" rtexprvalue="true" type="java.lang.Boolean"  %>
<%@ attribute name="showDelete" required="false" rtexprvalue="true" type="java.lang.Boolean"  %>
<%@ attribute name="showSave" required="false" rtexprvalue="true" type="java.lang.Boolean"  %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" prefix="spring" %>
<div class="controlBar">

	<spring:message code="default.buttons.save" var="buttonSave"/>
	<spring:message code="default.buttons.cancel" var="buttonCancel"/>
	<spring:message code="default.buttons.delete" var="buttonDelete"/>
	<div class="controlBar">
		<c:if test="${ showSave or showSave == null}">
			<input class="actionPositive" type="submit" id="_eventId_save" name="_eventId_save" value="${buttonSave }"/>
		</c:if>
		<c:if test="${ showCancel or showCancel == null}">
			<input class="actionModerate" type="submit" id="_eventId_cancel" name="_eventId_cancel" value="${buttonCancel }"/>
		</c:if>
		<c:if test="${ showDelete or showDelete == null}">
			<input class="actionNegative" type="submit" id="_eventId_delete" name="_eventId_delete" value="${buttonDelete }"/>
		</c:if>
	</div>
</div>