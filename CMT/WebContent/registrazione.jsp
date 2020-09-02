<%@ page language="java" contentType="text/html charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title>CMT - Registrazione</title>
		<link rel="stylesheet" type="text/css" href="css/base.css">
		<link rel="stylesheet" type="text/css" href="css/header.css">
		<link rel="stylesheet" type="text/css" href="css/logRegForm.css">
		<script src="js/fieldChecker.js"></script>
	</head>
	<body>
		<header>
			<span onclick="location.href='homepage.jsp'">
				<img src="icons/bigIcon.png">
				<h1>Castle Movie Theater</h1>					
			</span>
		</header>
		<hr>
		
		<form id="regisForm" name="form" onsubmit="return password.value == confPassword.value" action="${pageContext.request.contextPath}/registrazione" method="POST">
			<table>
				<tbody>
					<tr>												
						<td colspan="2"><input name="username" type="text" placeholder="Username" oninput="checkFieldLength('username', 6, 20)" minlength="6" maxlength="20" required></td>
					</tr>
					<tr>
						<td colspan="2"><input name="password" type="password"  placeholder="Password" oninput="checkFieldLength('password', 6, 20); checkPswMatching('password', 'confPassword');" minlength="6" maxlength="20" required></td>
					</tr>
					<tr>
						<td colspan="2"><input name="confPassword" type="password"  placeholder="Conferma password" oninput="checkPswMatching('password', 'confPassword')" minlength="6" maxlength="20" required></td>
					</tr>
					<tr>														
						<td colspan="2"><input name="saldo" type="number" placeholder="Saldo" oninput="checkIsNotNeg('saldo')"  min="0" required></td>
					</tr>
					<tr>														
						<td><label>Trattamento dei dati personali</label></td><td align="right"><input type="checkbox" name="termini" required></td>
					</tr>
					<tr><td>&nbsp;</td></tr>
					<tr>
						<td colspan="2"><button type="submit">Registrati</button></td>
					</tr>
				</tbody>
			</table>
		</form>
	</body>
</html>