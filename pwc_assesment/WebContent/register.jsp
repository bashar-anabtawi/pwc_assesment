<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/styles_and_scripts.jsp"%>
	
	<meta charset="UTF-8">
	<title>Insert title here</title>
</head>
<body>
<div class="container">

	<main class="form-signin">
	
		<c:if test="${not empty error}">
			<div style="color:red">
				<p>${error}</p>
			</div>
		</c:if>
		
		<form action="/pwc_assesment/register" method="post">
			<h1 class="h3 mb-3 fw-normal">Register new user</h1>
			
			<div class="form-floating">
		      <input type="text" class="form-control" name="username" id="username" placeholder="Please enter your username">
		      <label for="username">Username</label>
		    </div>
						
			<div class="form-floating">
		      <input type="password" class="form-control" name="password" id="password" placeholder="Password">
		      <label for="password">Password</label>
		    </div>
			
			<div class="form-check">
			  <input class="form-check-input" type="checkbox" name="isAdmin" value="true" id="isAdmin">
			  <label class="form-check-label" for="isAdmin">Is Admin</label>
			</div>
			
			<button class="w-100 btn btn-lg btn-primary" type="submit" style="margin-top: 1rem !important;">Register</button>
		</form>
		
		<form action="/pwc_assesment" method="post">
		    <button class="w-100 btn btn-lg btn-secondary" type="submit" name="submit" style="margin-top: 15px;">Cancel</button>
		</form>
	
	</main>

</div><!-- End of the container -->


</body>
</html>