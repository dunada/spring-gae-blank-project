<%@page import="com.blank.util.enumerator.UserAdminStatusEnum"%>
<%@page import="com.blank.util.enumerator.UserAdminTypeEnum"%>
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
  	<c:set var="breadcrumb" value="Administradores;/admin/user_admin,Detalhe;#"/>
  	<%@ include file="/views/inc/admin/common/breadcrumb.jsp" %>
  	<%@ include file="/views/inc/admin/common/messages.jsp" %>
  	
  	<!-- start page content -->
  	
	<form  action="/admin/user_admin/save.action" class="well" method="post">
		<fieldset>
			<input type="hidden" name="id" value="${userAdmin.key.id}">
			<div class="form-group <c:if test="${not empty name_error}">has-error</c:if>">
				<label class="control-label" for="name">Nome</label>
				<input type="text" class="form-control" id="name" name="name" placeholder="Nome" value="${userAdmin.name}">
				<p class="help-block">${name_error}</p>
			</div>
			<div class="form-group <c:if test="${not empty email_error}">has-error</c:if>">
				<label class="control-label" for="email">Email</label>
				<input type="email" class="form-control" id="email" name="email" placeholder="Email" value="${userAdmin.email}">
				<p class="help-block">${email_error}</p>
			</div>
			<div class="form-group <c:if test="${not empty type_error}">has-error</c:if>">
			  <label class="control-label" for="type">Tipo</label>
			  <select name="type" class="form-control">
			  	<c:forEach var="type" items="<%=UserAdminTypeEnum.values()%>">
			  		<option value="${type}" <c:if test="${userAdmin.type==type}">selected="selected"</c:if> >${type.alias}</option>
			  	</c:forEach>
			  </select>
			  <p class="help-block">${type_error}</p>
			</div>
			<div class="form-group <c:if test="${not empty status_error}">has-error</c:if>">
			  <label class="control-label" for="status">Status</label>
			  <select name="status" class="form-control">
			  	<c:forEach var="status" items="<%=UserAdminStatusEnum.values()%>">
			  		<option value="${status}" <c:if test="${userAdmin.status==status}">selected="selected"</c:if> >${status.alias}</option>
			  	</c:forEach>
			  </select>
			  <p class="help-block">${status_error}</p>
			</div>
			<div class="text-right">
				<button type="submit" class="btn btn-success">Salvar</button>
				<a class="btn btn-danger" href="/admin/user_admin">Cancelar</a>
			</div>
		</fieldset>
	</form>
	
	
	
	<!-- end page content -->
	
	
  </div>
	
</body>
</html>