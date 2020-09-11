<%@ page language="java" contentType="text/html charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title>CMT - Registrazione</title>
		<link rel="stylesheet" type="text/css" href="css/base.css">
		<link rel="stylesheet" type="text/css" href="css/header.css">
		<link rel="stylesheet" type="text/css" href="css/modal.css">
		<link rel="stylesheet" type="text/css" href="css/optionsList.css">
		<script src="js/colorField.js"></script>
		<script src="http://code.jquery.com/jquery-latest.min.js"></script>
		<script type="text/javascript">
			$(document).ready(function()
			{
				$(document).on("submit", "#registrationForm", function(event)
				{
					var $form = $(this);
					
					$.post($form.attr("action"), $form.serialize(), function(responseText)
					{					
						if(responseText === "0")
							$("#successModal").css("display", "flex");
						else
							$("#errorModal").css("display", "flex");
					});
					
					event.preventDefault();
				});
			});
		</script>	
	</head>
	<body id="prova">
		<header>
			<span onclick="location.href='homepage.jsp'">
				<img src="icons/siteIcon.png">
				<h1>Castle Movie Theater</h1>					
			</span>
		</header>
		
		<hr>
		
		<form id="registrationForm" class="optionsList" style="margin: 100px auto" onsubmit="return password.value == confPassword.value" action="${pageContext.request.contextPath}/registration" method="post">
			<input name="username" type="text" placeholder="Username" oninput="checkFieldLength(this, 6, 20)" minlength="6" maxlength="20" required>
			<input name="password" type="password" placeholder="Password" oninput="checkFieldLength(this, 6, 20); checkPswMatching('password', 'confPassword');" minlength="6" maxlength="20" required>
			<input name="confPassword" type="password"  placeholder="Conferma password" oninput="checkPswMatching('password', 'confPassword')" minlength="6" maxlength="20" required>
			<input name="balance" type="number" placeholder="Saldo" oninput="checkIsNotNeg(this)"  min="0" required>
			<span><label for="dataTreatment">Trattamento dei dati personali</label><input id="dataTreatment" type="checkbox" style="width: 1px" required></span>
			<span>&nbsp;</span>
			<button type="submit">Registrati</button>
		</form>
		
		<div id="successModal" class="modalContainer">
			<div class="modalContent">
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
					<button onclick="window.location.href = '${pageContext.request.contextPath}/homepage.jsp'">Ok</button>
				</div>
			</div>
		</div>
		
		<div id="errorModal" class="modalContainer">
			<div class="modalContent">
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