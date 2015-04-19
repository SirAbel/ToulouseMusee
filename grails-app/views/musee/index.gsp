
<%@ page import="MuseeToulouse.Musee" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'musee.label', default: 'Musee')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
		<style>
			.favBtn {
				border-radius: 4px;
				horizontal-align: middle;
				display: block;
				margin:auto
			}

			.searchField {
				border-radius: 4px
			}

			#searchBtn {
				border-radius: 4px
			}

			table {
				box-shadow: 0px 10px 5px #888888;
			}

			table th {
				text-align: center;
			}
		</style>
	</head>
	<body>
		<a href="#list-musee" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<!-- <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li> -->
			</ul>
		</div>
		<div id="list-musee" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>

			<g:form>
				<fieldset class="form">
					<div class="fieldcontain">

						<label for="nom">
							Nom du musée :
						</label>
						<g:textField  class="searchField" name="nom" />

					</div>

					<div class="fieldcontain">

						<label for="CP">
							Code postal :
						</label>
						<g:select name="CP"
								  from="${MuseeToulouse.Musee.list().adresse.codePostal.unique()}"
								  noSelection="['0':'Choisissez un CP']"
						/>

					</div>

					<div class="fieldcontain">

						<label for="rue">
							Nom de la rue :
						</label>
						<g:textField class="searchField" name="rue" />

					</div>

					<div style="float: right" >
						<g:actionSubmit  id="searchBtn" action="doSearchMuseum" value="Search" />
					</div>
				</fieldset>
			</g:form>

			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="nom" title="${message(code: 'musee.nom.label', default: 'Nom')}" />
					
						<th><g:message code="musee.gestionnaire.label" default="Gestionnaire" /></th>
					
						<g:sortableColumn property="horairesOuverture" title="${message(code: 'musee.horairesOuverture.label', default: 'Horaires Ouverture')}" />
					
						<g:sortableColumn property="telephone" title="${message(code: 'musee.telephone.label', default: 'Telephone')}" />
					
						<g:sortableColumn property="accesMetro" title="${message(code: 'musee.accesMetro.label', default: 'Acces Metro')}" />
					
						<g:sortableColumn property="accesBus" title="${message(code: 'musee.accesBus.label', default: 'Acces Bus')}" />

						<th>Add to Favorite</th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${museeInstanceList}" status="i" var="museeInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
						<g:form>

							<td><g:link action="show" id="${museeInstance.id}">${fieldValue(bean: museeInstance, field: "nom")}</g:link></td>

							<td>${fieldValue(bean: museeInstance, field: "gestionnaire")}</td>

							<td>${fieldValue(bean: museeInstance, field: "horairesOuverture")}</td>

							<td>${fieldValue(bean: museeInstance, field: "telephone")}</td>

							<td>${fieldValue(bean: museeInstance, field: "accesMetro")}</td>

							<td>${fieldValue(bean: museeInstance, field: "accesBus")}</td>
							<td><g:actionSubmit class="favBtn" value="add" action="selectFavorite"/>
							<g:hiddenField name="instance" value="${fieldValue(bean: museeInstance, field: "nom")}"/>
							</td>
						</g:form>
					
					</tr>
				</g:each>
				</tbody>
			</table>

			<div class="pagination">
				<g:paginate total="${museeInstanceCount ?: 0}" max="5" />
			</div>

		<table>
			<thead>
			<tr>
				<g:sortableColumn property="nom" title="${message(code: 'musee.nom.label', default: 'Nom')}" />
				<th> Remove from Favorite</th>
			</tr>
			</thead>

			<tbody>
			<g:each in="${favoriteList}" status="i" var="favoriteInstance">
				<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					<g:form>

						<td><g:link action="show" id="${favoriteInstance.id}">${fieldValue(bean: favoriteInstance, field: "nom")}</g:link></td>
						<td><g:actionSubmit class="favBtn " value="remove" action="removeFromFavorite"/>
						<g:hiddenField name="instance" value="${fieldValue(bean: favoriteInstance, field: "nom")}"/>
						</td>
					</g:form>
				</tr>
			</g:each>
			</tbody>
		</table>

		</div>
	</body>
</html>
