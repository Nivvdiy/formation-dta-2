<%@page import="fr.pizzeria.model.Pizza"%>
<%@page import="java.util.List"%>
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

<title>Starter Template for Bootstrap</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
<script src="../bootstrap/js/bootstrap.js"></script>
<link href="../bootstrap/css/bootstrap.css" rel="stylesheet">
</head>

<body>

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
					for(Pizza pizza :pizzas){
				%>
				<tr>
					<td><%= pizza.getName() %></td>
					<td><%= pizza.getCode() %></td>
					<td><%= pizza.getCategory() %></td>
					<td><%= pizza.getPrice() %></td>
					<td><img src="../Images/Pizzas/<%= pizza.getImage() %>" alt="Pizza <%= pizza.getName() %>" height="100px"></td>
					<td><a class="btn btn-default" href="./edit?code=<%= pizza.getCode() %>" role="button">Editer</a></td>
					<td><a class="btn btn-default" href="./delete?code=<%= pizza.getCode() %>" role="button">Supprimer</a></td>
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
		window.jQuery
				|| document
						.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')
	</script>
	<script src="../../dist/js/bootstrap.min.js"></script>
	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>
