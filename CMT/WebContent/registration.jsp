<%@ page language="java" contentType="text/html charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title>CMT - Registrazione</title>
		<link rel="stylesheet" type="text/css" href="css/base.css">
		<link rel="stylesheet" type="text/css" href="css/header.css">		
		<link rel="stylesheet" type="text/css" href="css/optionsList.css">		
		<script src="js/colorField.js"></script>
	</head>
	<body>
		<header>
			<span onclick="location.href='homepage.jsp'">
				<img src="icons/bigIcon.png">
				<h1>Castle Movie Theater</h1>					
			</span>
		</header>
		
		<hr>
		
		<form name="form" class="optionsList" style="margin: 100px auto" onsubmit="return password.value == confPassword.value" action="${pageContext.request.contextPath}/registration" method="post">
			<input name="username" type="text" placeholder="Username" oninput="checkFieldLength(this, 6, 20)" minlength="6" maxlength="20" required>
			<input name="password" type="password" placeholder="Password" oninput="checkFieldLength(this, 6, 20); checkPswMatching('password', 'confPassword');" minlength="6" maxlength="20" required>
			<input name="confPassword" type="password"  placeholder="Conferma password" oninput="checkPswMatching('password', 'confPassword')" minlength="6" maxlength="20" required>
			<input name="balance" type="number" placeholder="Saldo" oninput="checkIsNotNeg(this)"  min="0" required>
			<span><label for="dataTreatment">Trattamento dei dati personali</label><input id="dataTreatment" type="checkbox" style="width: 1px" required></span>
			<span>&nbsp;</span>
			<button type="submit">Registrati</button>
		</form>
	</body>
</html>