<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Login</title>
</head>
<body>
	<center>
	<form action="login" method="post"> 
	 	<h1>Welcome to Flight Management System</h1>
	 	<br/><br/>  
	    UserName:<input type="text" name="userName"/>
	    <br/><br/>  
	    Password:<input type="password" name="password"/>
	    <br/><br/>  
	    UserType:<input type="text" name="userType">
	    <br/><br/>  
	    <input type="submit" value="login"/>  
    </form>
    </center>
</body>
</html>