<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/styles_and_scripts.jsp"%>
	
	<meta charset="UTF-8">
	<title>Complaint Form</title>
</head>
<body>

<div class="container">

<c:if test="${not empty error}">
	<div style="color:red">
		<p>${error}</p>
	</div>
</c:if>

<c:set var="action">
	<c:choose>
		<c:when test="${not empty complaint}">/pwc_assesment/editComplaint</c:when>
		<c:otherwise>/pwc_assesment/newComplaint</c:otherwise>
	</c:choose>
</c:set>

<form action="${action}" method="post">
	<input type="hidden" name="id" value="${not empty complaint ? complaint.id : -1}">

	<div class="mb-3">
		<label for="name" class="form-label"><span style="color: red">*</span>Complaint Name: </label>
		<c:choose>
			<c:when test="${not empty complaint}"><input id="name" name="name" type="text" class="form-control" value="${complaint.name}"/></c:when>
			<c:otherwise><input id="name" name="name" type="text" class="form-control"/></c:otherwise>
		</c:choose>
	</div><br>
	
	<h5>Please select your department:</h5>
	
	<div>
		<div class="form-check">
			<input class="form-check-input" type="radio" name="department" value="Sales" id="sales" ${not empty complaint and complaint.department eq 'Sales' ? 'checked' : ''}>
			<label class="form-check-label" for="sales">Sales</label>
		</div>
		
		<div class="form-check">
			<input class="form-check-input" type="radio" name="department" value="Accounting" id="accounting" ${not empty complaint and complaint.department eq 'Accounting' ? 'checked' : ''}>
			<label class="form-check-label" for="accounting">Accounting</label>
		</div>
		
		<div class="form-check">
			<input class="form-check-input" type="radio" name="department" value="Engineering" id="engineering" ${not empty complaint and complaint.department eq 'Engineering' ? 'checked' : ''}>
			<label class="form-check-label" for="engineering">Engineering</label>
		</div>
		
		<div class="form-check">
			<input class="form-check-input" type="radio" name="department" value="Management" id="management" ${not empty complaint and complaint.department eq 'Management' ? 'checked' : ''}>
		    <label class="form-check-label" for="management">Management</label>
		</div>
	</div>
	
	<br>
	
	
	<div class="mb-3">
	  <label for="description" class="form-label">Description:</label>
	  <textarea class="form-control" name="description" id="description" rows="5" cols="50">${not empty complaint ? complaint.description : ''}</textarea>
	</div>
	
	<c:if test="${not empty complaint}">
		<h5>Complaint Status:</h5>
		
		<div>
			<div class="form-check">
				<input class="form-check-input" type="radio" name="status" value="1" id="new" ${not empty complaint and complaint.status.id eq 1 ? 'checked' : ''}>
				<label class="form-check-label" for="new">New</label>
			</div>
			
			<div class="form-check">
				<input class="form-check-input" type="radio" name="status" value="2" id="pending-resolution" ${not empty complaint and complaint.status.id eq 2 ? 'checked' : ''}>
				<label class="form-check-label" for="pending-resolution">Pending Resolution</label>
			</div>
			
			<div class="form-check">
				<input class="form-check-input" type="radio" name="status" value="3" id="resolved" ${not empty complaint and complaint.status.id eq 3 ? 'checked' : ''}>
				<label class="form-check-label" for="resolved">Resolved</label>
			</div>
			
			<div class="form-check">
				<input class="form-check-input" type="radio" name="status" value="4" id="dismissed" ${not empty complaint and complaint.status.id eq 4 ? 'checked' : ''}>
			    <label class="form-check-label" for="dismissed">Dismissed</label>
			</div>
		</div>
	</c:if>
  	<br>
  	
  	<div class="col-auto">
	    <button type="submit" name="submit" class="btn btn-primary mb-3">Submit</button>
	</div>
</form>


<form action="/pwc_assesment/${not empty complaint ? 'adminDashboard' : 'userProfile'}" method="post">
	<div class="col-auto">
	    <button type="submit" name="submit" class="btn btn-secondary mb-3">Cancel</button>
	</div>
</form>

</div><!-- End of the container -->


</body>
</html>