<%@page import="java.util.Iterator"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.ArrayList"%>
<%@page import="fr.pizzeria.model.Client"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
							
	<form:form method="post" modelAttribute="ingredient">
		<table class="table table-striped">
			<tr>
				<td>Nom</td>
				<td><form:input path="name" /></td>
			</tr>
			<tr>
				<td>Prix</td>
				<td><form:input path="price" /></td>
			</tr>
			<tr>
				<td>Quantité</td>
				<td><form:input path="quantity" /></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="Valider"></td>
			</tr>
		</table>
	</form:form>
	</div>
</body>
</html>