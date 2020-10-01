<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/tooltip.css"><meta charset='UTF-8'><title>Products</title>
 <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
<title>login</title>
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <a class="navbar-brand" href="myhomepage.jsp">Home</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
    <div class="navbar-nav">
      <a class="nav-item nav-link active" href="Products">Products <span class="sr-only">(current)</span></a>
      <a class="nav-item nav-link" href="Basket">Basket</a>
      <div class="float-right">
      	<a class="nav-item nav-link" href="Logout" >Logout</a>
      </div>
    </div>
  </div>
</nav>

<body>

	<div class="container" align="center" style="margin-top: 5rem;">
		<h1 align="center">High End Gaming Hardware Buying Simulator</h1><br><br> 
		
		<div class="card" style="width: 28rem;">
		  	<div class="card-body">
				<form action="Register" method="post">
					<h3>Create new account: </h3>
					
					<div class="form-group">
					<label for="name">Username:</label>
					<input type="text" name="username" id ="name" value="${username}"></input>
					</div>
					<div class="form-group">
					<label for="fullname">Fullname:</label>
					<input type="text" name="fullname" id ="fullname"></input>
					</div>
					<div class="form-group">
					<label for="pass">Password:</label>
					<input type="password" name="pass" id ="pass"></input>
					</div>
					<div class="form-group">
					<label for="dob">Date of Birth:</label>
					<input type="date" name="birth" id ="dob"></input>
					</div>
					<input type="submit" value="Register"></input>
				</form>
	</div></div></div>
	</body>
</html>