<%@ page import="MuseeToulouse.DemandeVisite" %>



<label class="control-label" for="nbPersonnes">
	<g:message code="demandeVisite.nbPersonnes.label" default="Nb Personnes" />
	<span class="required-indicator">*</span>
</label>
<g:field style="width: 100px;" class="form-control" name="nbPersonnes" type="number" min="1" max="6" value="${demandeVisiteInstance?.nbPersonnes}" required=""/>

<hr/>
<div class="fieldcontain ${hasErrors(bean: demandeVisiteInstance, field: 'dateDebutPeriode', 'error')} required">
	<label class="control-label" for="dateDebutPeriode">
		<g:message code="demandeVisite.dateDebutPeriode.label" default="Date Debut Periode" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker class="form-control" name="dateDebutPeriode" precision="day"  value="${demandeVisiteInstance?.dateDebutPeriode}"  />

</div>

<div class="fieldcontain ${hasErrors(bean: demandeVisiteInstance, field: 'dateFinPeriode', 'error')} required">
	<label class="control-label" for="dateFinPeriode">
		<g:message code="demandeVisite.dateFinPeriode.label" default="Date Fin Periode" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="dateFinPeriode" precision="day"  value="${demandeVisiteInstance?.dateFinPeriode}"  />

</div>

<g:hiddenField name="code" value="35"></g:hiddenField>
<g:hiddenField name="statut" value="Pending"></g:hiddenField>

<hr>

