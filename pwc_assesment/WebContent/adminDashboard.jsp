<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/styles_and_scripts.jsp"%>
	
	<meta charset="UTF-8">
	<title>Admin Dashboard</title>
</head>
<body>
<div class="container">

	<div class="buttons-bar">
		<form action="/pwc_assesment/logout">
			<button class="w-100 btn btn-lg btn-secondary" type="submit" name="submit">Logout</button>
		</form>
	</div>
	
	<h3 class="table-title">Below are all users:</h3>
	
	<table class="table table-striped table-hover">
		<thead>
			<tr>
				<th scope="col">ID</th>
				<th scope="col">UserName</th>
				<th scope="col">Password</th>
				<th scope="col">Is Admin</th>
			</tr>
		</thead>
		
		<tbody>
		<c:forEach var = "record" items = "${allUsers}">
		   <tr>
		   	<th scope="row">${record.id}</th>
		   	<td>${record.userName}</td>
		   	<td>${record.password}</td>
		   	<td>${record.admin}</td>
		   </tr>
		</c:forEach>
		</tbody>
	</table>
	
	<br><br>
	
	
	<h3 class="table-title">Below are all complaints:</h3>
	<table class="table table-striped table-hover">
		<thead>
			<tr>
				<th scope="col">ID</th>
				<th scope="col">Complain Name</th>
				<th scope="col">Department</th>
				<th scope="col">Description</th>
				<th scope="col">Status</th>
				<th scope="col">Complainer</th>
				<th scope="col">Edit</th>
			</tr>
		</thead>
		
		<tbody>
			<c:forEach var = "record" items = "${allComplaints}">
			   <tr>
			   	<th scope="row">${record.id}</th>
			   	<td>${record.name}</td>
			   	<td>${record.department}</td>
			   	<td>${record.description}</td>
			   	<td>${record.status.name}</td>
			   	<td>${record.complainer.userName}</td>
			   	<td>
			   		<a class="edit-btn" href="/pwc_assesment/editComplaint?id=${record.id}">
			   			<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-gear" viewBox="0 0 16 16">
						  <path d="M8 4.754a3.246 3.246 0 1 0 0 6.492 3.246 3.246 0 0 0 0-6.492zM5.754 8a2.246 2.246 0 1 1 4.492 0 2.246 2.246 0 0 1-4.492 0z"/>
						  <path d="M9.796 1.343c-.527-1.79-3.065-1.79-3.592 0l-.094.319a.873.873 0 0 1-1.255.52l-.292-.16c-1.64-.892-3.433.902-2.54 2.541l.159.292a.873.873 0 0 1-.52 1.255l-.319.094c-1.79.527-1.79 3.065 0 3.592l.319.094a.873.873 0 0 1 .52 1.255l-.16.292c-.892 1.64.901 3.434 2.541 2.54l.292-.159a.873.873 0 0 1 1.255.52l.094.319c.527 1.79 3.065 1.79 3.592 0l.094-.319a.873.873 0 0 1 1.255-.52l.292.16c1.64.893 3.434-.902 2.54-2.541l-.159-.292a.873.873 0 0 1 .52-1.255l.319-.094c1.79-.527 1.79-3.065 0-3.592l-.319-.094a.873.873 0 0 1-.52-1.255l.16-.292c.893-1.64-.902-3.433-2.541-2.54l-.292.159a.873.873 0 0 1-1.255-.52l-.094-.319zm-2.633.283c.246-.835 1.428-.835 1.674 0l.094.319a1.873 1.873 0 0 0 2.693 1.115l.291-.16c.764-.415 1.6.42 1.184 1.185l-.159.292a1.873 1.873 0 0 0 1.116 2.692l.318.094c.835.246.835 1.428 0 1.674l-.319.094a1.873 1.873 0 0 0-1.115 2.693l.16.291c.415.764-.42 1.6-1.185 1.184l-.291-.159a1.873 1.873 0 0 0-2.693 1.116l-.094.318c-.246.835-1.428.835-1.674 0l-.094-.319a1.873 1.873 0 0 0-2.692-1.115l-.292.16c-.764.415-1.6-.42-1.184-1.185l.159-.291A1.873 1.873 0 0 0 1.945 8.93l-.319-.094c-.835-.246-.835-1.428 0-1.674l.319-.094A1.873 1.873 0 0 0 3.06 4.377l-.16-.292c-.415-.764.42-1.6 1.185-1.184l.292.159a1.873 1.873 0 0 0 2.692-1.115l.094-.319z"/>
						</svg>
					</a>
				</td>
			   </tr>
			</c:forEach>
		</tbody>
	</table>
	
	
</div><!-- End of the container -->

</body>
</html>