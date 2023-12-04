<%@page import="entities.Ville"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta charset="UTF-8">
<!-- Inclure les fichiers Bootstrap via CDN -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js"></script>
<style>
/* Custom CSS for centering */
</style>
<title>Insert title here</title>
</head>
<body>



	<div class="container-fluid d-flex justify-content-center">
		<div class="container mt-5 w-50 border border-black border-1 rounded">
			<h2 class="mt-4 mb-4">Gestion villes</h2>
			<!-- Create form -->
			<form id="createForm" action="VilleController" method="post">
				<div class="form-group">
					<input type="text" class="form-control" id="nom" name="villeNom"
						placeholder="Nom" required>
				</div>
				<button name="action" value="add"
					class="btn btn-primary mt-3">Create</button>
			</form>

			
		</div>
	</div>
	
	<div class="container mt-2 w-50 border border-black border-1 rounded">
		<!-- Table to display Ville instances -->
			<table class="table mt-4 ">
				<thead>
					<tr>
						<th>ID</th>
						<th>Nom</th>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody id="villeList">
					<!-- Display Ville instances here -->
					<c:forEach items="${villes}" var="v">
						<tr>
							<td>${v.id}</td>
							<td>${v.nom}</td>
							<td>
								<!-- Actions for each Ville -->
								<a href="VilleController?action=delete&id=${v.id}" class="btn btn-danger">Supprimer</a>
                                <a href="VilleController?action=update&id=${v.id}" class="btn btn-primary">Modifier</a>
                                
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
	
	</div>
</body>
</html>