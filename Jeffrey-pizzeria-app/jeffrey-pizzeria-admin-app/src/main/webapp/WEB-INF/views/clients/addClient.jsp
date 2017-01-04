<%@page import="java.util.Iterator"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.ArrayList"%>
<%@page import="fr.pizzeria.model.Client"%>
<%@page import="java.util.List"%>
<%@ page isELIgnored="false"%>
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

<title>Ajouter une client</title>
<link href="../bootstrap/css/bootstrap.css" rel="stylesheet">
<style>
body {
	padding: 10px;
}
</style>
</head>

<body>
	<div class="table-responsive">
		<table class="table table-striped">
			<thead>
				<tr>
					<th>Nom</th>
					<th>Prénom</th>
					<th>Age</th>
					<th>Ville</th>
					<th>Email</th>
					<th>Mot de passe</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<form id="clientForm">
						<%
							String nom = "";
							String prenom = "";
							int age = 18;
							String ville = "";
							String email = "";
							String password = "";
						%>
						<td><input required id="nom" type="text" name="nom"
							value="<%=nom%>"></td>
						<td><input required id="prenom" type="text" name="prenom"
							value="<%=prenom%>"></td>
						<td><input required id="age" type="number" name="price" step="1"
							value="<%=age%>"></td>
						<td><input required id="ville" type="text" name="ville"
							value="<%=ville%>"></td>
						<td><input id="email" type="email" name="email"
							value="<%=email%>"></td>
						<td><input id="password" type="password" name="password"
							value="<%=password%>"></td>
						<td><input id="submit" type="submit" name="submit"
							value="Ajouter"></td>
					</form>
				</tr>
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
			$('#submit').on('click', function(e) {
				e.preventDefault();
				var nom = $("#nom").val();
				var prenom = $("#prenom").val();
				var age = $("#age").val();
				var ville = $("#ville").val();
				var email = $("#email").val();
				var password = $("#password").val();
				$.ajax({
					type : "POST",
					dataType: "json",
					data: $("#clientForm").serialize(),
					url : "../api/rest/clients",
					success : function(result) {
						window.location.href = "./list";
					},
					failure : function(result) {
						console.log("Erreur " + result);
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
