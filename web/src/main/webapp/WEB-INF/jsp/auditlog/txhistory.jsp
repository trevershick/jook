<%@ include file="/WEB-INF/jsp/include.jsp"%>
<h1>Transaction History for ${tx}</h1>

<h2>${updatedBy } on <fmt:formatDate value="${updatedDate }" pattern="yyyy-MM-dd HH:mm"/></h2>


<c:forEach items="${entityNames }" var="entityName">
<h1>Entity Name : ${entityName }</h1>
<c:set var="recordsById" value=""/>
<c:set var="rows" value="${model[entityName] }"/>
		<table>
			<tr>
				<th>Entity Id</th>
				<th>Transaction Type</th>
				<th>What</th>
				<th>New Value</th>
				<th>Diff</th>
				<th>Old Value</th>
			</tr>
		<c:forEach var="row" items="${rows }">
			<c:forEach var="auditLogRecord" items="${row.value }">
			<tr>
				<td>${row.key }</td>
				<td>${auditLogRecord.message}</td>
				<td>${auditLogRecord.entityAttribute }</td>
				<td>${auditLogRecord.newValue }</td>
				<td>${auditLogRecord.diff }</td>
				<td>${auditLogRecord.oldValue}</td>
			</tr>
			</c:forEach>
		</c:forEach>
		</table>
</c:forEach>
<!-- 		private String entityAttribute;
		private String entityId;
		private String entityName;
		private String message;
		private String newValue;
		private String oldValue;
		private String updatedBy;
		private String txRef;
		
		private Date updatedDate; -->
