<%@ page language="java" contentType="text/html charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title>CMT - Registrazione</title>
		<link rel="stylesheet" type="text/css" href="css/base.css">
		<link rel="stylesheet" type="text/css" href="css/header.css">
		<link rel="stylesheet" type="text/css" href="css/form.css">
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
			<input name="username" type="text" placeholder="Username" oninput="checkFieldLength('username', 6, 20)" minlength="6" maxlength="20" required>
			<input name="password" type="password"  placeholder="Password" oninput="checkFieldLength('password', 6, 20); checkPswMatching('password', 'confPassword');" minlength="6" maxlength="20" required>
			<input name="confPassword" type="password"  placeholder="Conferma password" oninput="checkPswMatching('password', 'confPassword')" minlength="6" maxlength="20" required>
			<input name="saldo" type="number" placeholder="Saldo" oninput="checkIsNotNeg('saldo')"  min="0" required>
			<span><label>Trattamento dei dati personali</label><input type="checkbox" name="termini" required></span>
			<br>
			<button type="submit">Registrati</button>
		</form>
	</body>
</html>