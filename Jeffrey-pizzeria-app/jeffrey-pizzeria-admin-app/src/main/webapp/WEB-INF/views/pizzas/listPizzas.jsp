<%@page import="fr.pizzeria.model.Pizza"%>
<%@page import="java.util.List"%>
<%@ page  isELIgnored="false" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">

<title>Liste des pizzas</title>
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
	<h2 class="sub-header">Liste des pizzas</h2>
	<div class="table-responsive">
		<table class="table table-striped">
			<thead>
				<tr>
					<th>Nom</th>
					<th>Code</th>
					<th>Catégorie</th>
					<th>Prix</th>
					<th>Image</th>
				</tr>
			</thead>
			<tbody>
				<%
					List<Pizza> pizzas = (List<Pizza>) request.getAttribute("ListPizzas");
				%>
				<%
					for (Pizza pizza : pizzas) {
				%>
				<tr>
					<td><%=pizza.getName()%></td>
					<td><%=pizza.getCode()%></td>
					<td><%=pizza.getCategory()%></td>
					<td><%=pizza.getPrice()%></td>
					<td><img src="../Images/Pizzas/<%=pizza.getImage()%>"
						alt="Pizza <%=pizza.getName()%>" height="100px"></td>
					<td><a class="btn btn-default"
						href="./edit?code=<%=pizza.getCode()%>" role="button">Editer</a></td>
					<td><a class="btn btn-default delete-pizza" href=""
						id="<%=pizza.getCode()%>" role="button">Supprimer</a></td>
				</tr>
				<%
					}
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
			$('.delete-pizza').on('click', function(e) {
				e.preventDefault();
				var id = (this.id);
				$.ajax({
	                type: "DELETE",
	                url: "./delete?"+"code=" + id,
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
