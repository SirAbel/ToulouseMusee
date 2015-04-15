<%@ page import="MuseeToulouse.Musee" %>



<div class="fieldcontain ${hasErrors(bean: museeInstance, field: 'nom', 'error')} required">
	<label for="nom">
		<g:message code="musee.nom.label" default="Nom" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nom" required="" value="${museeInstance?.nom}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: museeInstance, field: 'gestionnaire', 'error')} ">
	<label for="gestionnaire">
		<g:message code="musee.gestionnaire.label" default="Gestionnaire" />
		
	</label>
	<g:select id="gestionnaire" name="gestionnaire.id" from="${MuseeToulouse.Gestionnaire.list()}" optionKey="id" value="${museeInstance?.gestionnaire?.id}" class="many-to-one" noSelection="['null': '']"/>

</div>

<div class="fieldcontain ${hasErrors(bean: museeInstance, field: 'horairesOuverture', 'error')} required">
	<label for="horairesOuverture">
		<g:message code="musee.horairesOuverture.label" default="Horaires Ouverture" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="horairesOuverture" required="" value="${museeInstance?.horairesOuverture}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: museeInstance, field: 'telephone', 'error')} required">
	<label for="telephone">
		<g:message code="musee.telephone.label" default="Telephone" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="telephone" required="" value="${museeInstance?.telephone}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: museeInstance, field: 'accesMetro', 'error')} required">
	<label for="accesMetro">
		<g:message code="musee.accesMetro.label" default="Acces Metro" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="accesMetro" required="" value="${museeInstance?.accesMetro}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: museeInstance, field: 'accesBus', 'error')} required">
	<label for="accesBus">
		<g:message code="musee.accesBus.label" default="Acces Bus" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="accesBus" required="" value="${museeInstance?.accesBus}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: museeInstance, field: 'visiteRequests', 'error')} ">
	<label for="visiteRequests">
		<g:message code="musee.visiteRequests.label" default="Visite Requests" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${museeInstance?.visiteRequests?}" var="v">
    <li><g:link controller="demandeVisiteMusee" action="show" id="${v.id}">${v?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="demandeVisiteMusee" action="create" params="['musee.id': museeInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'demandeVisiteMusee.label', default: 'DemandeVisiteMusee')])}</g:link>
</li>
</ul>


</div>
<fieldset class="embedded"><legend><g:message code="musee.adresse.label" default="Adresse" /></legend>
<div class="fieldcontain ${hasErrors(bean: museeInstance, field: 'adresse.codePostal', 'error')} required">
	<label for="adresse.codePostal">
		<g:message code="musee.adresse.codePostal.label" default="Code Postal" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="adresse.codePostal" type="number" min="10000" max="99999" value="${museeInstance.adresse?.codePostal}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: museeInstance, field: 'adresse.numero', 'error')} required">
	<label for="adresse.numero">
		<g:message code="musee.adresse.numero.label" default="Numero" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="adresse.numero" type="number" min="1" value="${museeInstance.adresse?.numero}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: museeInstance, field: 'adresse.rue', 'error')} required">
	<label for="adresse.rue">
		<g:message code="musee.adresse.rue.label" default="Rue" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="adresse.rue" required="" value="${museeInstance.adresse?.rue}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: museeInstance, field: 'adresse.ville', 'error')} required">
	<label for="adresse.ville">
		<g:message code="musee.adresse.ville.label" default="Ville" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="adresse.ville" required="" value="${museeInstance.adresse?.ville}"/>

</div>
</fieldset>
