<%@ include file="/views/inc/admin/common/doctype.jsp" %><%@ include file="/views/inc/init.jsp" %>
<head>
	<title>Blank - Admin</title> 	
	<%@ include file="/views/inc/admin/common/head.jsp" %>
	<script type="text/javascript" src="/assets/admin/javascripts/pages/user_admin/index.js"></script>
	<link rel="stylesheet" href="/assets/admin/stylesheets/pages/user_admin/index.css" type="text/css">
</head>
<body id="index" ng-app="admin">
  <div class="container">
  	<%@ include file="/views/inc/admin/common/navbar.jsp" %>
  	<c:set var="breadcrumb" value="Administradores;/admin/user_admin"/>
  	<%@ include file="/views/inc/admin/common/breadcrumb.jsp" %>
  	<%@ include file="/views/inc/admin/common/messages.jsp" %>
  	
  	<!-- start page content -->
  	
  	<ul class="nav nav-pills pull-right">
		<li><a href="/admin/user_admin/detail"><b>+</b> Adicionar usuário</a></li>
	</ul>	
	
		
	<rga:pagination pagination="${pagination}"/>
	<table class="table table-bordered table-striped">
	  <thead>
	    <tr>
	      <th>Nome</th>
	      <th></th>
	    </tr>
	  </thead>
	  <tbody>
	    <c:choose>
	      <c:when test="${not empty userAdmins}">
	        <c:forEach var="userAdmin" items="${userAdmins}" varStatus="sts">
	          <tr>
	            <td>${userAdmin.name}</td>
	            <td>
	            	<a href="/admin/user_admin/detail/${userAdmin.key.id}" class="btn btn-mini btn-success">editar</a>
	            	<a href="/admin/user_admin/delete/${userAdmin.key.id}" class="btn btn-mini btn-danger">deletar</a>
	            </td>
	          </tr>
	        </c:forEach>
	      </c:when>
	      
	      <c:otherwise>
	        <tr>
	          <td colspan="3">Sem dados</td>
	        </tr>
	      </c:otherwise>
	    </c:choose>
	  </tbody>
	</table>
	<rga:pagination pagination="${pagination}"/>
	<!-- end page content -->
	
	
  </div>
	
</body>
</html>