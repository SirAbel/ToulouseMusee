
<%@ page import="MuseeToulouse.Musee" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'musee.label', default: 'Musee')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>

		<!--<a href="#show-musee" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>-->
		<nav class="navbar navbar-default navigationBar">
			<a class="navbar-link" href="${createLink(uri: '/')}"><span class="glyphicon glyphicon-home" aria-hidden="true"></span> <g:message code="default.home.label"/></a>
		</nav>

		<div id="show-musee" class="content scaffold-show" role="main">
			<h1 class="text-center"><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>

			<div class="container col-md-10" style="float: none">
				<ol class="list-group">

					<g:if test="${museeInstance?.nom}">
						<li class="list-group-item">
							<span id="nom-label" class="label label-success lg-label"><g:message code="musee.nom.label" default="Nom" /></span>

							<span class="pull-right" aria-labelledby="nom-label"><g:fieldValue bean="${museeInstance}" field="nom"/></span>

						</li>
					</g:if>

					<g:if test="${museeInstance?.gestionnaire}">
						<li class="list-group-item">
							<span id="gestionnaire-label" class="label label-default label-lg"><g:message code="musee.gestionnaire.label" default="Gestionnaire" /></span>

							<span class="pull-right" aria-labelledby="gestionnaire-label"><g:link controller="gestionnaire" action="show" id="${museeInstance?.gestionnaire?.id}">${museeInstance?.gestionnaire?.encodeAsHTML()}</g:link></span>

						</li>
					</g:if>

					<g:if test="${museeInstance?.horairesOuverture}">
						<li class="list-group-item clearfix">
							<span id="horairesOuverture-label" class="label label-default"><g:message code="musee.horairesOuverture.label" default="Horaires Ouverture" /></span>

							<span class="pull-right" aria-labelledby="horairesOuverture-label"><g:fieldValue bean="${museeInstance}" field="horairesOuverture"/></span>

						</li>
					</g:if>

					<g:if test="${museeInstance?.telephone}">
						<li class="list-group-item">
							<span id="telephone-label" class="label label-default"><g:message code="musee.telephone.label" default="Telephone" /></span>

							<span class="pull-right" aria-labelledby="telephone-label"><g:fieldValue bean="${museeInstance}" field="telephone"/></span>

						</li>
					</g:if>

					<g:if test="${museeInstance?.accesMetro}">
						<li class="list-group-item">
							<span id="accesMetro-label" class="label label-info"><g:message code="musee.accesMetro.label" default="Acces Metro" /></span>

							<span class="pull-right" aria-labelledby="accesMetro-label"><g:fieldValue bean="${museeInstance}" field="accesMetro"/></span>

						</li>
					</g:if>

					<g:if test="${museeInstance?.accesBus}">
						<li class="list-group-item">
							<span id="accesBus-label" class="label label-info"><g:message code="musee.accesBus.label" default="Acces Bus" /></span>

							<span class="pull-right" aria-labelledby="accesBus-label"><g:fieldValue bean="${museeInstance}" field="accesBus"/></span>

						</li>
					</g:if>

					<g:if test="${museeInstance?.adresse}">
						<li class="list-group-item">
							<span id="adresse-label" class="label label-default"><g:message code="musee.adresse.label" default="Adresse" /></span>

							<span class="pull-right" aria-labelledby="adresse-label"><g:fieldValue bean="${museeInstance}" field="adresse"/></span>

						</li>
					</g:if>

				</ol>

			</div>

			<!--<g:form url="[resource:museeInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${museeInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>-->
		</div>
	</body>
</html>
