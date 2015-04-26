
<%@ page import="MuseeToulouse.DemandeVisite" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'demandeVisite.label', default: 'DemandeVisite')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>

	<nav class="navbar navbar-default navigationBar">
		<a class="navbar-link" href="${createLink(uri: '/')}"><span class="glyphicon glyphicon-home" aria-hidden="true"></span> <g:message code="default.home.label"/></a>
		<a class="navbar-link" href="javascript:history.go(-1)">Go Back <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span></a>
	</nav>

		<div>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<dl class="dl-horizontal">
			
				<g:if test="${demandeVisiteInstance?.code}">
				<dt>
					<span><g:message code="demandeVisite.code.label" default="Code demande" /></span>
				</dt>
					<dd>
						<span><g:fieldValue bean="${demandeVisiteInstance}" field="code"/></span>
					</dd>
				</g:if>

				<br/>
				<g:if test="${flash.message}">
					<div class="container col-lg-6">
						<ul class="list-group">
							<li class="list-group-item list-group-item-success">Votre demande sera traitée dès que possible</li>
						</ul>
					</div>
				</g:if>
			
			</dl>
		</div>
	<footer style="height: 40px;"></footer>
	</body>
</html>
