<%@ include file="/views/inc/init.jsp" %>
<div class="navbar">
      <a class="navbar-brand" href="/admin"><img height="20px" src="/assets/admin/images/logo.png" alt="Google Creative Sandbox"></a>
      <ul class="nav navbar-nav">
     	<li <c:if test="${fn:contains(pageContext.request.requestURI,'blank') }">class="active"</c:if>><a href="/admin/blank">Blank</a></li>   
		<%-- c:if test="${empty sessionScope.userAdmin.userType || sessionScope.adminUser.userType == 'rga'}"--%>
         <li class="dropdown">
           <a href="#" class="dropdown-toggle" data-toggle="dropdown"> System <b class="caret"></b></a>
           <ul class="dropdown-menu">
             <li><a href="/admin/user_admin">Administradores</a></li>
           </ul>
         </li>   
		<%--</c:if>--%>
        <li class="divider-vertical"></li>
        <li><a href="/admin/logout">Sair</a></li>
      </ul>
 </div>