<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Login</title>
</head>
<body>

	<div align="center">
		<h1>Login</h1>

		<form method="post">
			<table>
				<tr>
					<td>
						<fieldset>
							<legend><b>Login:</b></legend>
							<table>
								<tr>
									<th>
										<label for="email">Email</label>
									</th>
									<td>:</td>
									<td>
										<input type="email" name="email" id="email" value=${requestScope.email}>
									</td>
									<td>
									    ${requestScope.emailErrMsg}
  									</td>
								</tr>
								<tr>
									<th>
										<label for="password">Password</label>
									</th>
									<td>:</td>
									<td>
										<input type="password" name="password" id="password">
									</td>
									<td>
									    ${requestScope.passwordErrMsg}
									</td>
								</tr>
								<tr>
                                    <th>
                                        <label for="confirm_password">Confirm Password</label>
                                    </th>
                                    <td>:</td>
                                    <td>
                                        <input type="password" name="confirm_password" id="confirm_password">
                                    </td>
                                    <td>
                                        ${requestScope.confirmPasswordErrMsg}
                                    </td>
                                </tr>
								<tr>
									<th></th>
									<td></td>
									<td align="right">
										<input type="submit" value="Register">
									</td>
								</tr>
							</table>
						</fieldset>
					</td>
				</tr>
			</table>
		</form>

        ${requestScope.errMsg}

		<p>Already have an account? Login <a href="/lms/login">here</a>.</p>
	</div>

</body>
</html>