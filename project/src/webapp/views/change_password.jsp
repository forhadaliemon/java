<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Change Password</title>
</head>
<body>
<h1>Change Password</h1>
<%-- Display validation errors if present --%>
<c:if test="${not empty validationErrors}">
<p style="color: red;">Please correct the following errors:</p>
<ul>
	<c:forEach var="error" items="${validationErrors}">
		<li>${error}</li>
	</c:forEach>
</ul>
</c:if>
<%-- Display success message if present --%>
<c:if test="${not empty successMessage}">
<p style="color: green;">${successMessage}</p>
</c:if>
<form method="post" action="changePassword">
	<label for="oldPassword">Old Password:</label>
	<input type="password" name="oldPassword" id="oldPassword
    <br>
    <label for="newPassword">New Password:</label>
	<input type="password" name="newPassword" id="newPassword">
	<br>
	<label for="confirmNewPassword">Confirm New Password:</label>
	<input type="password" name="confirmNewPassword" id="confirmNewPassword">
	<br>
	<input type="submit" value="Change Password">
</form>
</body>
</html>
