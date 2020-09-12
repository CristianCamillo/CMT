<%@ page language="java" contentType="text/html charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>	
		<title>CMT - Area Personale Cliente</title>
		<link rel="stylesheet" type="text/css" href="css/base.css">
		<link rel="stylesheet" type="text/css" href="css/eye.css">
		<link rel="stylesheet" type="text/css" href="css/modal.css">
		<link rel="stylesheet" type="text/css" href="css/optionsList.css">
		<script src="js/alterPasswordVisibility.js"></script>
		<script src="js/fieldValidator.js"></script>
		<script src="http://code.jquery.com/jquery-latest.min.js"></script>
		<script src="js/personalDataFormsManager.js"></script>
	</head>
	<body>
		<header>
			<span onclick="location.href='homepage.jsp'">
				<img src="icons/siteIcon.png">
				<h1>Castle Movie Theater</h1>					
			</span>
		</header>
		
		<hr>
		
		<div style="display: flex">
			<div class="optionsList" style="margin-right: 20px">
				<span><h3>Username</h3><label id="username"><%= request.getSession().getAttribute("username") %></label></span>
				<span><h3>Saldo</h3><span><label id="balance"><%= request.getSession().getAttribute("balance") %></label><label> &euro;</label></span></span>
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
			<div>
				<header>
					<h2>Modifica username</h2>
					<button onclick="document.getElementById('usernameModal').style.display = 'none'">&#x02716;</button>
				</header>
				<br>
				<form id="usernameForm" class="optionsList" onsubmit="return validateUsername(document.getElementsByName('newUsername')[0])" action="${pageContext.request.contextPath}/updateUserData" method="post">
					<input name="newUsername" type="text" oninput="validateUsername(this)">
					<span>&nbsp;</span>
					<button type="submit">Aggiorna</button>
					<input name="toChange" type="hidden" value="0">
				</form>
			</div>
		</div>
		
		<div id="passwordModal" class="modalContainer">
			<div>
				<header>
					<h2>Modifica password</h2>
					<button onclick="document.getElementById('passwordModal').style.display = 'none'">&#x02716;</button>
				</header>
				<br>
				<form id="passwordForm" class="optionsList" onsubmit="return validatePassword(document.getElementsByName('newPassword')[0])" action="${pageContext.request.contextPath}/updateUserData" method="post">
					<span class="passwordSpan"><input name="newPassword" type="password" oninput="validatePassword(this)"><img src="svg/eyeSlash.svg" class="eye" onclick="alterPasswordVisibility(document.getElementsByName('newPassword')[0], this)"></span>
					<span>&nbsp;</span>
					<button type="submit">Aggiorna</button>
					<input name="toChange" type="hidden" value="1">
				</form>
			</div>
		</div>
		
		<div id="amountModal" class="modalContainer">
			<div>
				<header>
					<h2>Aggiunta fondi</h2>
					<button onclick="document.getElementById('amountModal').style.display = 'none'">&#x02716;</button>
				</header>
				<br>
				<form id="amountForm" class="optionsList" onsubmit="return validateAmount(document.getElementsByName('amount')[0])" action="${pageContext.request.contextPath}/updateUserData" method="post">
					<span class="euroSpan"><input name="amount" type="text" oninput="validateAmount(this)"><label>&euro;</label></span>
					<span>&nbsp;</span>
					<button type="submit">Aggiungi</button>
					<input name="toChange" type="hidden" value="2">
				</form>
			</div>
		</div>
		
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
					<label id="successLabel"></label>
					<label>&nbsp;</label>
					<button onclick="document.getElementById('successModal').style.display = 'none'">Ok</button>
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
					<label id="errorLabel"></label>
					<label>&nbsp;</label>
					<button onclick="document.getElementById('errorModal').style.display = 'none'">Ok</button>
				</div>
			</div>
		</div>
	</body>
</html>