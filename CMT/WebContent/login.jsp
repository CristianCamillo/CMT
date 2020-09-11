<%@ page language="java" contentType="text/html charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title>CMT - Login</title>
		<link rel="stylesheet" type="text/css" href="css/base.css">
		<link rel="stylesheet" type="text/css" href="css/eye.css">
		<link rel="stylesheet" type="text/css" href="css/header.css">
		<link rel="stylesheet" type="text/css" href="css/modal.css">
		<link rel="stylesheet" type="text/css" href="css/optionsList.css">
		<script src="js/alterPasswordVisibility.js"></script>
		<script src="js/fieldValidator.js"></script>
		<script src="http://code.jquery.com/jquery-latest.min.js"></script>
		<script type="text/javascript">
			function validateForm()
			{
				var usr = validateUsername(document.getElementsByName("username")[0]);
				var psw = validatePassword(document.getElementsByName("password")[0]);
				
				return usr && psw;
			}			
		</script>
		<script type="text/javascript">
			$(document).ready(function()
			{
				$(document).on("submit", "#loginForm", function(event)
				{
					var $form = $(this);
					
					$.post($form.attr("action"), $form.serialize(), function(responseText)
					{											
						if(responseText != "")
							if(responseText === "0")
								window.location.href = 'homepage.jsp';
							else 
							{
								var msg = "Dati non associati ad alcun ";
								if(responseText === "1")
									msg += "cliente";
								else
									msg += "gestore";
								
								$("#errorLabel").html(msg);
								$("#errorModal").css("display", "flex");
							}
					});
					
					event.preventDefault();
				});
			});
		</script>
	</head>
	<body>		
		<header>
			<span onclick="location.href='homepage.jsp'">
				<img src="icons/siteIcon.png">
				<h1>Castle Movie Theater</h1>					
			</span>
		</header>
		
		<hr>
		
		<form id="loginForm" class="optionsList" style="margin: 100px auto" onsubmit="return validateForm()" action="${pageContext.request.contextPath}/login" method="post">
			<input name="username" type="text" placeholder="Username" oninput="validateUsername(this)">
			<span class="passwordSpan"><input name="password" type="password" placeholder="Password" oninput="validatePassword(this)"><img src="svg/eyeSlash.svg" class="eye" onclick="alterPasswordVisibility(document.getElementsByName('password')[0], this)"></span>
			<span class="checkboxSpan"><label for="isManager">Accedi come manager</label><input id="isManager" name="isManager" type="checkbox"></span>
			<span>&nbsp;</span>
			<button type="submit">Accedi</button>
		</form>
		
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
					<label id="errorLabel"></label>
					<label>&nbsp;</label>
					<button onclick="document.getElementById('errorModal').style.display = 'none'">Ok</button>
				</div>
			</div>
		</div>
	</body>
</html>