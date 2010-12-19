<%@ include file="/WEB-INF/jsp/include.jsp"%>
<h1>Object History for ${entityName }</h1>

<c:set scope="page" var="version" value="0"/>
<c:forEach items="${txReferences }" var="txref">
	<c:set var="rows" value="${records[txref] }"/>
	<c:set scope="page" var="version" value="${version + 1 }"/>
	
<b>v${version } ${rows[0].updatedBy } on <fmt:formatDate value="${rows[0].updatedDate }" pattern="yyyy-MM-dd HH:mm"/></b>

	<a href="#" onclick="showTx('${txref}');">see full transaction</a>
	<br/>
		<div>
		<table class="results" style="margin-top:5px;margin-bottom:5px;">
			<tr>
				<th>Transaction Type</th>
				<th>What</th>
				<th>New Value</th>
				<!-- <th>Diff</th> -->
				<th>Old Value</th>
			</tr>
		<c:forEach var="row" items="${rows }">
			<tr>
				<td>
				<c:choose>
					<c:when test="${row.message == 'INSERT' }"><c:set var="graphic" value="add"/></c:when>
					<c:when test="${row.message == 'UPDATE' }"><c:set var="graphic" value="edit"/></c:when>
					<c:otherwise><c:set var="graphic" value="delete"/></c:otherwise>
				</c:choose>
				
				<img width="16" src="${pageContext.request.contextPath }/railinc-template/images/icons/${graphic }.png"/>
				${row.message}
				</td>
				<td>${row.entityAttribute }</td>
				<td>${row.newValue }</td>
				<!-- <td>${row.diff }</td> -->
				<td>${row.oldValue}</td>
			</tr>
		</c:forEach>
		</table>
		
		</div>
</c:forEach>
