<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Login</title>
</head>
<body>
<h1>Login</h1>
<%-- Display error message if present --%>
<c:if test="${not empty errorMessage}">
	<p style="color: red;">${errorMessage}</p>
</c:if>
<form method="post" action="login">
	<label for="username">Username:</label>
	<input type="text" name="username" id="username">
	<br>
	<label for="password">Password:</label>
	<input type="password" name="password" id="password">
	<br>
	<input type="submit" value="Login">
</form>
</body>
</html>
