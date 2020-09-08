<%@ page language="java" contentType="text/html charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>	
		<title>CMT - Area Personale Cliente</title>
		<link rel="stylesheet" type="text/css" href="css/base.css">
		<link rel="stylesheet" type="text/css" href="css/header.css">
		<link rel="stylesheet" type="text/css" href="css/modal.css">
		<link rel="stylesheet" type="text/css" href="css/optionsList.css">
		<script src="http://code.jquery.com/jquery-latest.min.js"></script> 
		<script src="js/colorField.js"></script>
		<script type="text/javascript">
			$(document).ready(function()
			{
				$(document).on("submit", "#usernameForm", function(event)
				{
					var $form = $(this);
					
					$.post($form.attr("action"), $form.serialize(), function(responseText)
					{
						$("#username").text(responseText);
					});
				});
				
				event.preventDefault();
			});
		</script>
	</head>
	<body>
		<header>
			<span onclick="location.href='homepage.jsp'">
				<img src="icons/bigIcon.png">
				<h1>Castle Movie Theater</h1>					
			</span>
		</header>
		
		<hr>
		
		<div style="display: flex">
			<div class="optionsList" style="margin-right: 20px">
				<span><h3>Username</h3><label id="username"></label></span>
				<span><h3>Saldo</h3><label id="amount"></label></span>
				<span>&nbsp;</span>
				<span>&nbsp;</span>
				<button onclick="document.getElementById('usernameModal').style.display = 'flex'">Modifica username</button>
				<button onclick="document.getElementById('passwordModal').style.display = 'flex'">Modifica password</button>
				<button onclick="document.getElementById('amountModal').style.display = 'flex'">Aggiungi fondi</button>
			</div>
			
			<hr>
			
			<div>
			</div>
		</div>
		
		<div id="usernameModal" class="modalContainer">
			<div class="modalContent">
				<span class="modalHeader">
					<h2>Modifica username</h2>
					<button onclick="document.getElementById('usernameModal').style.display = 'none'">&#x02716;</button>
				</span>
				<br>
				<form id="usernameForm" class="optionsList" action="${pageContext.request.contextPath}/updateUserData" method="post">
					<input name="newUsername" type="text" oninput="checkFieldLength(this, 6, 20)" minlength="6" maxlength="20" required>
					<span>&nbsp;</span>
					<button type="submit">Aggiorna</button>
					<input name="toChange" type="hidden" value="0">
				</form>
			</div>
		</div>
		
		<div id="passwordModal" class="modalContainer">
			<div class="modalContent">
				<span class="modalHeader">
					<h2>Modifica password</h2>
					<button onclick="document.getElementById('passwordModal').style.display = 'none'">&#x02716;</button>
				</span>
				<br>
				<form id="passwordForm" class="optionsList" onsubmit="return password.value == confPassword.value" action="${pageContext.request.contextPath}/updateUserData" method="post">
					<input name="newPassword" type="password" oninput="checkFieldLength(this, 6, 20); checkPswMatching('newPassword', 'confPassword');" minlength="6" maxlength="20" required>
					<input name="confPassword" type="password" oninput="checkPswMatching('newPassword', 'confPassword')" minlength="6" maxlength="20" required>
					<span>&nbsp;</span>
					<button type="submit">Aggiorna</button>
					<input name="toChange" type="hidden" value="1">
				</form>
			</div>
		</div>
		
		<div id="amountModal" class="modalContainer">
			<div class="modalContent">
				<span class="modalHeader">
					<h2>Aggiunta fondi</h2>
					<button onclick="document.getElementById('amountModal').style.display = 'none'">&#x02716;</button>
				</span>
				<br>
				<form id="amountForm" class="optionsList" action="${pageContext.request.contextPath}/updateUserData" method="post">
					<input name="amount" type="number" oninput="checkIsPos(this)" min="0.000000000000000001" required>
					<span>&nbsp;</span>
					<button type="submit">Aggiungi</button>
					<input name="toChange" type="hidden" value="2">
				</form>
			</div>
		</div>
	</body>
</html>