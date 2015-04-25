<%@ page import="MuseeToulouse.MuseeService" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>Musees Toulouse</title>
		<style type="text/css" media="screen">
			#status {
				background-color: #eee;
				border: 3px solid #fff;
				margin: 2em 2em 1em;
				padding: 1em;
				float: left;
				-moz-box-shadow: 0px 0px 1.25em #ccc;
				-webkit-box-shadow: 0px 0px 1.25em #ccc;
				box-shadow: 0px 0px 1.25em #ccc;
				-moz-border-radius: 0.6em;
				-webkit-border-radius: 0.6em;
				border-radius: 0.6em;
				width: 90%;
				text-align: center;
			}

			.ie6 #status {
				display: inline; /* float double margin fix http://www.positioniseverything.net/explorer/doubled-margin.html */
			}

			#status ul {
				font-size: 0.9em;
				list-style-type: none;
				margin-bottom: 0.6em;
				padding: 0;
			}

			#status li {
				line-height: 1.3;
			}

			#status h1 {
				text-transform: uppercase;
				font-size: 1.1em;
				margin: 0 0 0.3em;
			}

			h2 {
				margin-top: 1em;
				margin-bottom: 0.3em;
				font-size: 1em;
			}

			p {
				line-height: 1.5;
				margin: 0.25em 0;
			}

			#controller-list ul {
				list-style-position: inside;
			}

			#controller-list li {
				line-height: 1.3;
				list-style-position: inside;
				margin: 0.25em 0;
			}

			@media screen and (max-width: 480px) {
				#status {
					display: none;
				}

				#page-body {
					margin: 0 1em 1em;
				}

				#page-body h1 {
					margin-top: 0;
				}
			}

			body {
				margin: 0 auto;
			}



		</style>
	</head>
	<body>

	<div class="container">

		<div id="status" role="complementary" >

			<ul class="nav nav-pills nav-stacked">
				<li>
					<g:link class="btn btn-default" controller="musee" action="index" >Browse Available Museums</g:link>
				</li>

				<li>
					<g:link class="btn btn-primary" controller="musee" action="doSearchMuseum" params="[var:1, accueil:1]" ><span class="glyphicon glyphicon-search" aria-hidden="true"></span> Search Page</g:link>
				</li>
			</ul>

		</div>

	</div>


	</body>
</html>
