
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
	</nav>

		<div id="show-demandeVisite" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list demandeVisite">
			
				<g:if test="${demandeVisiteInstance?.code}">
				<li class="fieldcontain">
					<span id="code-label" class="property-label"><g:message code="demandeVisite.code.label" default="Code" /></span>
					
						<span class="property-value" aria-labelledby="code-label"><g:fieldValue bean="${demandeVisiteInstance}" field="code"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${demandeVisiteInstance?.nbPersonnes}">
				<li class="fieldcontain">
					<span id="nbPersonnes-label" class="property-label"><g:message code="demandeVisite.nbPersonnes.label" default="Nb Personnes" /></span>
					
						<span class="property-value" aria-labelledby="nbPersonnes-label"><g:fieldValue bean="${demandeVisiteInstance}" field="nbPersonnes"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${demandeVisiteInstance?.dateDebutPeriode}">
				<li class="fieldcontain">
					<span id="dateDebutPeriode-label" class="property-label"><g:message code="demandeVisite.dateDebutPeriode.label" default="Date Debut Periode" /></span>
					
						<span class="property-value" aria-labelledby="dateDebutPeriode-label"><g:formatDate date="${demandeVisiteInstance?.dateDebutPeriode}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${demandeVisiteInstance?.dateFinPeriode}">
				<li class="fieldcontain">
					<span id="dateFinPeriode-label" class="property-label"><g:message code="demandeVisite.dateFinPeriode.label" default="Date Fin Periode" /></span>
					
						<span class="property-value" aria-labelledby="dateFinPeriode-label"><g:formatDate date="${demandeVisiteInstance?.dateFinPeriode}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${demandeVisiteInstance?.statut}">
				<li class="fieldcontain">
					<span id="statut-label" class="property-label"><g:message code="demandeVisite.statut.label" default="Statut" /></span>
					
						<span class="property-value" aria-labelledby="statut-label"><g:fieldValue bean="${demandeVisiteInstance}" field="statut"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${demandeVisiteInstance?.museumsRequested}">
				<li class="fieldcontain">
					<span id="museumsRequested-label" class="property-label"><g:message code="demandeVisite.museumsRequested.label" default="Museums Requested" /></span>
					
						<g:each in="${demandeVisiteInstance.museumsRequested}" var="m">
						<span class="property-value" aria-labelledby="museumsRequested-label"><g:link controller="demandeVisiteMusee" action="show" id="${m.id}">${m?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:demandeVisiteInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${demandeVisiteInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
