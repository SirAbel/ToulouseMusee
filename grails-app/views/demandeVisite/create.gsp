<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'demandeVisite.label', default: 'DemandeVisite')}" />
		<title><g:message code="default.create.label" args="[entityName]" /></title>
	</head>
	<body>
		<nav class="navbar navbar-default navigationBar">
			<a class="navbar-link" href="${createLink(uri: '/')}"><span class="glyphicon glyphicon-home" aria-hidden="true"></span> <g:message code="default.home.label"/></a>
		</nav>

		<div class="container" style="background-color: #F7F7F9;">

			<div id="create-demandeVisite" class="content scaffold-create" role="main">
				<h1 class="text-center"><g:message code="default.create.label" args="[entityName]" /></h1>
				<g:if test="${flash.message}">
					<div class="message" role="status">${flash.message}</div>
				</g:if>
				<g:hasErrors bean="${demandeVisiteInstance}">
					<ul class="errors" role="alert">
						<g:eachError bean="${demandeVisiteInstance}" var="error">
							<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
						</g:eachError>
					</ul>
				</g:hasErrors>
				<hr>
				<g:form class="form" url="[resource:demandeVisiteInstance, action:'save']" >
				<div class="container pull-left col-md-6">
						<div class="form-group">
							<g:render template="form"/>
						</div>
						<!--<div class="col-sm-6 col-md-6 form-group">
							<g:submitButton name="create" class="btn btn-primary btn-block" value="${message(code: 'default.button.create.label', default: 'Create')}" />
						</div>-->
				</div>

				<div class="container pull-right col-md-6" style="margin-bottom: 20px;">
					<g:if test="${favoriteListCount ?: 0}">
						<table class="table table-striped">
							<thead>
							<tr>
								<th>Favorites</th>
							</tr>
							</thead>

							<tbody>
							<tr>
								<td>
									<div class="form-group">
										<g:select class="form-control" name="instance"
												  from="${favoriteList.nom}"
												  noSelection="['0':'Select Favorite']"
										/>

									</div>
								</td>
							</tr>
							</tbody>
						</table>
					</g:if>
					<div class="form-group col-md-6">
						<g:submitButton name="create" class="btn btn-primary btn-block" value="${message(code: 'default.button.create.label', default: 'Create')}" />

					</div>
				</g:form>

				</div>
				<div class="clearfix"></div>
			</div>

		</div>
	<footer style="height: 40px;"></footer>
	</body>
</html>
