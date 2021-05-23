<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/styles_and_scripts.jsp"%>
		
	<meta charset="UTF-8">
	<title>User Profile</title>
</head>
<body>

<div class="container">

	<div class="buttons-bar">
		<form action="/pwc_assesment/newComplaint">
			<button class="w-100 btn btn-lg btn-primary" type="submit" name="submit">Add Complaint</button>
		</form>

		<form action="/pwc_assesment/logout">
			<button class="w-100 btn btn-lg btn-secondary" type="submit" name="submit">Logout</button>
		</form>
	</div>
	
	
	<h2 class="table-title">Below are all user complaints:</h2>
	
	<table class="table table-striped table-hover">
		<thead>
			<tr>
				<th scope="col">ID</th>
				<th scope="col">Complain Name</th>
				<th scope="col">Department</th>
				<th scope="col">Description</th>
				<th scope="col">Status</th>
			</tr>
		</thead>
		
		<tbody>
			<c:forEach var = "record" items = "${allUserComplaints}">
			   <tr>
			   	<th scope="row">${record.id}</th>
			   	<td>${record.name}</td>
			   	<td>${record.department}</td>
			   	<td>${record.description}</td>
			   	<td>${record.status.name}</td>
			   </tr>
			</c:forEach>
		</tbody>
	</table>

</div><!-- End of the container -->


</body>
</html>