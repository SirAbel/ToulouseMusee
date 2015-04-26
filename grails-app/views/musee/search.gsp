
<%@ page import="MuseeToulouse.Musee" %>
<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main">
        <g:set var="entityName" value="${message(code: 'musee.label', default: 'Musee')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
        <style>


            .favBtn {
                horizontal-align: middle;
                display: block;
                margin:auto
            }

            table {
                box-shadow: 0px 10px 5px #888888;
                margin-top: 30px !important;
            }

            .toHide {
                display: none;
            }
        </style>
    </head>
    <body>

    <nav class="navbar navbar-default navigationBar">
        <a class="navbar-link" href="${createLink(uri: '/')}"><span class="glyphicon glyphicon-home" aria-hidden="true"></span> <g:message code="default.home.label"/></a>
    </nav>

    <div class="container" style="background-color: #F7F7F9;">
        <div id="list-musee" class="content scaffold-list" role="main">
            <h1 class="text-center"><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>

        <hr>
        <div class="container pull-left col-md-6">
            <g:form class="form">
                <div class="form-group">

                    <label class="col-sm-4 control-label" for="nom">
                        Nom du musée :
                    </label>
                    <g:textField  class="form-control input-lg" name="nom" />

                </div>

                <div class="form-group">

                    <label class="col-sm-4 control-label" for="rue">
                        Nom de la rue :
                    </label>
                    <g:textField class="form-control input-lg" name="rue" />

                </div>

                <div class="form-group">

                    <label class="col-sm-4 control-label" for="CP">
                        Code postal :
                    </label>
                    <g:select class="form-control" name="CP"
                              from="${MuseeToulouse.Musee.list().adresse.codePostal.unique()}"
                              noSelection="['0':'Choisissez un CP']"
                    />

                </div>

                <div class="col-sm-6 col-md-6 form-group">
                    <br>
                    <g:actionSubmit  class="btn btn-info btn-block" action="doSearchMuseum" value="Search" />
                </div>
            </g:form>
        </div>

        <div class="container pull-right col-md-6">
            <g:if test="${favoriteListCount ?: 0}">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>Favorites</th>
                        <th><g:form>
                            <g:hiddenField name="favoriteList" value="${favoriteList}"></g:hiddenField>
                            <g:actionSubmit  class="btn btn-info btn-xs" action="redirectToDemandeVisite" value="Faire une demande" /></g:form>
                        </th>
                    </tr>
                    <tr>
                        <g:sortableColumn property="nom" title="${message(code: 'musee.nom.label', default: 'Nom')}" />
                        <th>Remove</th>
                    </tr>
                    </thead>

                    <tbody>
                    <g:each in="${favoriteList}" status="i" var="favoriteInstance">
                        <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                            <g:form>

                                <td><g:link action="show" id="${favoriteInstance.id}">${fieldValue(bean: favoriteInstance, field: "nom")}</g:link></td>
                                <td><g:actionSubmit class="btn btn-danger" value="remove" action="removeFromFavorite"/>
                                <g:hiddenField name="view" value="search"></g:hiddenField>
                                <g:hiddenField name="instance" value="${fieldValue(bean: favoriteInstance, field: "nom")}"/>
                                <g:hiddenField name="museeInstanceList" value="${museeInstanceList}"></g:hiddenField>
                                </td>
                            </g:form>
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </g:if>

        </div>
        <div class="clearfix"></div>

        <hr>

        <g:set var="startPoint" value="${(params.paginate==null) ? 0 : params.paginate}"/>
        <g:set var="upwardMax" value="${startPoint + 5}"/>
        <g:set var="downward" value="${startPoint - 5}"/>
        <table class="table table-striped table-hover">
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
                <tr class="${(i >= startPoint)&& (i<upwardMax) ? '':'toHide'}">
                    <g:form>

                        <td><g:link action="show" id="${museeInstance.id}">${fieldValue(bean: museeInstance, field: "nom")}</g:link></td>

                        <td>${fieldValue(bean: museeInstance, field: "gestionnaire")}</td>

                        <td>${fieldValue(bean: museeInstance, field: "horairesOuverture")}</td>

                        <td>${fieldValue(bean: museeInstance, field: "telephone")}</td>

                        <td>${fieldValue(bean: museeInstance, field: "accesMetro")}</td>

                        <td>${fieldValue(bean: museeInstance, field: "accesBus")}</td>
                        <td><g:actionSubmit class="favBtn btn btn-success" value="add" action="selectFavorite"/>
                        <g:hiddenField name="view" value="search"></g:hiddenField>
                        <g:hiddenField name="museeInstanceList" value="${museeInstanceList}"></g:hiddenField>
                        <g:hiddenField name="instance" value="${fieldValue(bean: museeInstance, field: "nom")}"/>
                        </td>
                    </g:form>
                </tr>
            </g:each>
            </tbody>
        </table>

        <g:if test="${museeInstanceCount > 5}">
            <ul class="pager">
                <g:if test="${downward >= 0}">
                    <li class="previous"><a href="${createLink(uri: '/musee/paginatedResults',params:[museeInstanceList:museeInstanceList,view:'search',paginate:downward])}">Previous</a></li>
                </g:if>
                <g:if test="${upwardMax <= museeInstanceCount}">
                    <li class="next"><a href="${createLink(uri: '/musee/paginatedResults',params:[museeInstanceList:museeInstanceList,view:'search',paginate:upwardMax])}">Next</a></li>
                </g:if>
            </ul>
        </g:if>

        <!--
        <div class="pagination">
            <g:paginate total="${museeInstanceCount ?: 0}" max="5" />
        </div>-->

    </div>
    </div>

    <footer style="height: 40px;"></footer>
    </body>
</html>
