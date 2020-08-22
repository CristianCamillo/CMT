<%@ page language="java" contentType="text/html charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title>CMT - Registrazione</title>
		<link rel="stylesheet" type="text/css" href="css/base.css">
		<link rel="stylesheet" type="text/css" href="css/button.css">
		<script src="js/fieldChecker.js"></script>
		<script src="js/regisChecker.js"></script>
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
						<td colspan="2"><input type="text" name="username" placeholder="Username"></td>
					</tr>
					<tr>
						<td colspan="2"><input type="password" name="password" placeholder="Password"></td>
					</tr>
					<tr>
						<td colspan="2"><input type="password" name="confPassword" placeholder="Conferma password"></td>
					</tr>
					<tr>														
						<td colspan="2"><input type="text" name="saldo" placeholder="Saldo"></td>
					</tr>
					<tr>														
						<td><label>Trattamento dei dati personali</label></td><td align="right"><input type="checkbox" name="termini"></td>
					</tr>
					<tr>
						<td colspan="2"><button class="regularButton" type="submit">Registrati</button></td>
					</tr>
				</tbody>
			</table>
		</form>
	</body>
</html>