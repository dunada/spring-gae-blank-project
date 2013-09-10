<%@ include file="/views/inc/init.jsp" %>
<ol class="breadcrumb">
<c:set var="pages" value="${fn:split(breadcrumb, ',')}" />
<c:forEach items="${pages}" var="page" varStatus="status">
	<c:set var="item" value="${fn:split(page, ';')}" />
	<li <c:if test="${status.last}">class="active"</c:if>>
		<c:if test="${!status.last}">
			<a href="${item[1]}">
		</c:if>
		${item[0]}
		<c:if test="${!status.last}"></a></c:if>
	</li>
</c:forEach>
</ol>