<%@page import="fr.pizzeria.model.Ingredient"%>
<%@page import="java.util.List"%>
<%@ page  isELIgnored="false" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta nom="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta nom="description" content="">
<meta nom="author" content="">
<link rel="icon" href="../../favicon.ico">

<title>Liste des clients</title>
<link href="../bootstrap/css/bootstrap.css" rel="stylesheet">
<style>
body{
padding:10px;
}
</style>
</head>

<body>
	<a class="btn btn-default" href="./new"
		role="button">Ajouter</a>
	<h2 class="sub-header">Liste des clients</h2>
	<div class="table-responsive">
		<table class="table table-striped">
			<thead>
				<tr>
					<th>ID</th>
					<th>Nom</th>
					<th>Prix</th>
					<th>Quantité</th>
				</tr>
			</thead>
			<tbody>
				<%
				%>
				<%
				%>
				<tr>
					<td>ID</td>
					<td>Name</td>
					<td>Price</td>
					<td>Quantity</td>
					<td><a class="btn btn-default"
						href="./edit?id=<%=%>" role="button">Editer</a></td>
					<td><a class="btn btn-default delete-client" href=""
						id="<%=%>" role="button">Supprimer</a></td>
				</tr>
				<%
				%>
			</tbody>
		</table>
	</div>

	<!-- /.container -->


	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script>
		$(function() {
			$('.delete-client').on('click', function(e) {
				e.preventDefault();
				var id = (this.id);
				$.ajax({
	                type: "DELETE",
	                url: "./delete?"+"id=" + id,
					success : function(result) {
	        			location.reload();
					},
					fail: function(result){
						console.log("Erreur "+result);
					}
				});
			});
		});
	</script>
	<script>
		window.jQuery
				|| document
						.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')
	</script>
	<script src="../bootstrap/js/bootstrap.js"></script>
</body>
</html>
