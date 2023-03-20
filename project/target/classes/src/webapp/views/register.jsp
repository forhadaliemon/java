<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Register</title>
</head>
<body>
<h1>Register</h1>
<%-- Display validation errors if present --%>
<c:if test="${not empty validationErrors}">
	<p style="color: red;">Please correct the following errors:</p>
	<ul>
		<c:forEach var="error" items="${validationErrors}">
			<li>${error}</li>
		</c:forEach>
	</ul>
</c:if>
<form method="post" action="register">
	<label for="username">Username:</label>
	<input type="text" name="username" id="username" value="${not empty param.username ? param.username : ''}">
	<br>
	<label for="password">Password:</label>
	<input type="password" name="password" id="password">
	<br>
	<label for="confirmPassword">Confirm Password:</label>
	<input type="password" name="confirmPassword" id="confirmPassword">
	<br>
	<input type="submit" value="Register">
</form>
</body>
</html>
