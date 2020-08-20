<%@ page language="java" contentType="text/html charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title>CMT - Registrazione</title>
		<link rel="stylesheet" type="text/css" href="css/base.css">
		<link rel="stylesheet" type="text/css" href="css/button.css">
		<script src="js/regisValidator.js"></script>  
	</head>
	<body>
		<table>
			<tbody>
				<tr>
					<td><button class="homepageButton" onclick="location.href='homepage.jsp'"><b>CASTLE MOVIE THEATER</b></button></td>
				</tr>
			</tbody>
		</table>
		<hr>
		<form name="form" onsubmit="return validateRegisForm()" action="${pageContext.request.contextPath}/registrazione" method="POST">
			<table>
				<tbody>
					<tr>												
						<td><label>Username:</label></td><td><input type="text" name="username"></td>
					</tr>
					<tr>
						<td><label>Password:</label></td><td><input type="password" name="password"></td>
					</tr>
					<tr>
						<td><label>Conferma password:</label></td><td><input type="password" name="confPassword"></td>
					</tr>
					<tr>														
						<td><label>Saldo:</label></td><td><input type="text" name="saldo"></td>
					</tr>
					<tr>														
						<td><label>Acconsento al trattamento dei miei dati personali</label></td><td><input type="checkbox" name="termini"></td>
					</tr>
					<tr>
						<td colspan="2"><button class="regularButton" type="submit">Registrati</button></td>
					</tr>
				</tbody>
			</table>
		</form>
	</body>
</html>