<%@ page language="java" contentType="text/html charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title>CMT - Registrazione</title>
		<link rel="stylesheet" type="text/css" href="css/base.css">
		<link rel="stylesheet" type="text/css" href="css/eye.css">
		<link rel="stylesheet" type="text/css" href="css/modal.css">
		<link rel="stylesheet" type="text/css" href="css/optionsList.css">
		<script src="js/alterPasswordVisibility.js"></script>
		<script src="js/fieldValidator.js"></script>
		<script src="http://code.jquery.com/jquery-latest.min.js"></script>
		<script src="js/registrationFormManager.js"></script>
	</head>
	<body>
		<header>
			<span onclick="location.href='homepage.jsp'">
				<img src="icons/siteIcon.png">
				<h1>Castle Movie Theater</h1>					
			</span>
		</header>
		
		<hr>
		
		<form id="registrationForm" class="optionsList" style="margin: 100px auto" onsubmit="return validateRegistrationForm()" action="${pageContext.request.contextPath}/registration" method="post">
			<input name="username" type="text" placeholder="Username" oninput="validateUsername(this)">
			<span class="passwordSpan"><input name="password" type="password" placeholder="Password" oninput="validatePassword(this)"><img src="svg/eyeSlash.svg" class="eye" onclick="alterPasswordVisibility(document.getElementsByName('password')[0], this)"></span>
			<span class="euroSpan"><input name="balance" type="text" placeholder="Saldo" oninput="validateBalance(this)"><label>&euro;</label></span>
			<span class="checkboxSpan"><label for="dataTreatment">Trattamento dei dati personali</label><input id="dataTreatment" type="checkbox" required></span>
			<span>&nbsp;</span>
			<button type="submit">Registrati</button>
		</form>
		
		<div id="successModal" class="modalContainer">
			<div>
				<header>
					<span>
						<img src="icons/successIcon.png">
						<h2>Successo</h2>
					</span>
				</header>
				<br>
				<div class="optionsList">
					<label>Registrazione effettuata con successo</label>
					<label>&nbsp;</label>
					<button onclick="window.location.href = 'homepage.jsp'">Ok</button>
				</div>
			</div>
		</div>
		
		<div id="errorModal" class="modalContainer">
			<div>
				<header>
					<span>
						<img src="icons/errorIcon.png">
						<h2>Errore</h2>
					</span>
				</header>
				<br>
				<div class="optionsList">
					<label>L'username indicato è già memorizzato</label>
					<label>&nbsp;</label>
					<button onclick="document.getElementById('errorModal').style.display = 'none'">Ok</button>
				</div>
			</div>
		</div>
	</body>
</html>