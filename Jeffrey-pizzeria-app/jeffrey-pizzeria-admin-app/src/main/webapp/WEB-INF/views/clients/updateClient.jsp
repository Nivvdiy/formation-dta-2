<%@page import="java.util.Iterator"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.ArrayList"%>
<%@page import="fr.pizzeria.model.Client"%>
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

<title>Mettre à jour un client</title>
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
				</tr>
			</thead>
			<tbody>
				<tr>
					<form>
						<%
							Client client = (Client) request.getAttribute("ModifiedClient");
						%>
						<td><input id="nom" type="text" nom="nom"
							value="<%=client.getNom()%>"></td>
						<td><input id="prenom" type="text" nom="prenom"
							value="<%=client.getPrenom()%>"></td>
						<td><input id="age" type="number" nom="age" step="1"
							value="<%=client.getAge()%>"></td>
						<td><input id="ville" type="text" nom="ville"
							value="<%=client.getVille()%>"></td>
						<td><input id="email" type="email" nom="email"
							value="<%=client.getEmail()%>"></td>
						<td><input id="id" type="hidden" nom="id"
							value="<%=client.getId()%>">
							<input id="submit" type="submit" nom="submit" value="Modifier"></td>
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
				var id = $("#id").val();
				var nom = $("#nom").val();
				var prenom = $("#prenom").val();
				var email = $("#email").val();
				var age = $("#age").val();
				var ville = $("#ville").val();
				$.ajax({
					type : "PUT",
					url : "./edit?" + "id=" + id + "&nom=" + nom + "&prenom=" + prenom + "&email=" + email + "&age=" + age + "&ville=" + ville,
					success : function(result) {
						window.location.href = "./list";
					},
					fail : function(result) {
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
