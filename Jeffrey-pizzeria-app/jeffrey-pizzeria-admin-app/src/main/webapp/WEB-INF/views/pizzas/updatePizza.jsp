<%@page import="java.util.Iterator"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.ArrayList"%>
<%@page import="fr.pizzeria.model.Pizza.Category"%>
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

<title>Mettre à jour une pizza</title>
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
					<th>Code</th>
					<th>Catégorie</th>
					<th>Prix</th>
					<th>Image</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<form>
						<%
							Pizza pizza = (Pizza) request.getAttribute("ModifiedPizza");
						%>
						<td><input id="name" type="text" name="name"
							value="<%=pizza.getName()%>"></td>
						<td><input id="code" type="text" name="code"
							value="<%=pizza.getCode()%>"></td>
						<td><select id="category" name="category">
								<%
									List<String> categories = new ArrayList<>();
									Set cles = Category.getCatList().keySet();
									Iterator it = cles.iterator();
									while (it.hasNext()) {
										categories.add(it.next().toString());
									}
									for (String cat : categories) {
								%>
								<option value="<%=cat%>"
									<% if(pizza.getCategory().toString().equals(cat)) { %> selected
									<%}%>><%=cat.toLowerCase()%></option>

								<%
									}
								%>
						</select></td>
						<td><input id="price" type="number" name="price" step="0.01"
							value="<%=pizza.getPrice()%>"></td>
						<td><input id="image" type="file" name="image"
							value="<%=pizza.getImage()%>"></td>
						<td><input id="id" type="hidden" name="id"
							value="<%=pizza.getId()%>"> <input id="imageData"
							type="hidden" name="imageData" value="<%=pizza.getImage()%>">
							<input id="submit" type="submit" name="submit" value="Modifier"></td>
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
				var name = $("#name").val();
				var code = $("#code").val();
				var image = $("#image").val()=="" ? $("#imageData").val() : $("#image").val();
				var price = $("#price").val();
				var e = document.getElementById("category");
				var category = e.options[e.selectedIndex].value;
				$.ajax({
					type : "PUT",
					url : "./edit?" + "id=" + id + "&name=" + name + "&code=" + code + "&image=" + image + "&price=" + price + "&category=" + category,
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
