<%@ page language="java" contentType="text/html charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>	
		<title>CMT - Area Personale Cliente</title>
		<link rel="stylesheet" type="text/css" href="css/base.css">
		<link rel="stylesheet" type="text/css" href="css/header.css">
		<link rel="stylesheet" type="text/css" href="css/modal.css">
		<link rel="stylesheet" type="text/css" href="css/optionsList.css">
		<script src="js/updateField.js"></script>
			<script type="text/javascript">
				$("document").ready(funtion()
				{
					$
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
		<div class="optionsList">
			<span><h3>Username</h3><label id="username"></label></span>
			<span><h3>Saldo</h3><label id="amount"></label></span>
			<span>&nbsp;</span>
			<span>&nbsp;</span>
			<button onclick="setupModal(<%=request.getSession().getAttribute("id")%>, 0)">Modifica username</button>
			<button onclick="setupModal(<%=request.getSession().getAttribute("id")%>, 1)">Modifica password</button>
			<button onclick="setupModal(<%=request.getSession().getAttribute("id")%>, 2)">Aggiungi fondi al saldo</button>
		</div>
		
		<div id="modalContainer" class="modalContainer">
			<div class="modalContent">
				<span class="modalHeader">
					<h2 id="modalTitle"></h2>
					<button onclick="document.getElementById('modalContainer').style.display = 'none'">&#x02716;</button>
				</span>
				<br>
				<form class="optionsList" name="form" method="post">
					<input id="modalInput" name="updateValue" required>
					<input id="modalConfPassword" name="confPassword" minlength="6" maxlength="20">
					<span>&nbsp;</span>
					<button type="submit" onclick="">Aggiorna</button>
				</form>
			</div>
		</div>		
	</body>
</html>