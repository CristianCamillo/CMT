<%@ page language="java" contentType="text/html charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
	<head>
		<title>CMT - Basket</title>
		<link rel="stylesheet" type="text/css" href="css/base.css">
		<link rel="stylesheet" type="text/css" href="css/modal.css">
		<link rel="stylesheet" type="text/css" href="css/optionsList.css">
		<link rel="stylesheet" type="text/css" href="css/table.css">
		<script src="js/dataParser.js"></script>
		<script src="js/jquery.js"></script>
		<script src="js/basketLoader.js"></script>
		<script src="js/purchaseManager.js"></script>
	</head>
	<body>
		<header>
			<span onclick="location.href = 'homepage.jsp'">
				<img src="icons/siteIcon.png">
				<h1>Castle Movie Theater</h1>
			</span>
		</header>
		
		<hr>
		
		<h2>Carrello</h2>
		<br>
		<table id="ticketTable" class="table" style="min-width: 1000px;">
			<thead>
				<tr>
					<th>Titolo film</th><th>Data di proiezione</th><th>Orario</th><th>Numero sala</th><th>Posto a sedere</th><th>Costo (&euro;)</th>
				</tr>
			</thead>
			<tbody style="max-height: 500px">
			</tbody>					
		</table>
		<br>
		<form id="purchaseForm" action="${pageContext.request.contextPath}/purchase" method="post">
			<button id="buyButton" type="submit" style="width: 100%" disabled>Acquista biglietti</button>
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
					<span>Acquisto riuscito con successo</span>
					<span>&nbsp;</span>
					<button onclick="location.href = 'homepage.jsp'">Ok</button>
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
					<span id="errorMsg"></span>
					<span>&nbsp;</span>
					<button id="errorButton" onclick="document.getElementById('errorModal').style.display = 'none'">Ok</button>
				</div>
			</div>
		</div>
	</body>
</html>