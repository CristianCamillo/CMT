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
		
		<link rel="apple-touch-icon" sizes="57x57" href="favicons/apple-icon-57x57.png">
		<link rel="apple-touch-icon" sizes="60x60" href="favicons/apple-icon-60x60.png">
		<link rel="apple-touch-icon" sizes="72x72" href="favicons/apple-icon-72x72.png">
		<link rel="apple-touch-icon" sizes="76x76" href="favicons/apple-icon-76x76.png">
		<link rel="apple-touch-icon" sizes="114x114" href="favicons/apple-icon-114x114.png">
		<link rel="apple-touch-icon" sizes="120x120" href="favicons/apple-icon-120x120.png">
		<link rel="apple-touch-icon" sizes="144x144" href="favicons/apple-icon-144x144.png">
		<link rel="apple-touch-icon" sizes="152x152" href="favicons/apple-icon-152x152.png">
		<link rel="apple-touch-icon" sizes="180x180" href="favicons/apple-icon-180x180.png">
		<link rel="icon" type="image/png" sizes="192x192"  href="favicons/android-icon-192x192.png">
		<link rel="icon" type="image/png" sizes="32x32" href="favicons/favicon-32x32.png">
		<link rel="icon" type="image/png" sizes="96x96" href="favicons/favicon-96x96.png">
		<link rel="icon" type="image/png" sizes="16x16" href="favicons/favicon-16x16.png">
		<link rel="manifest" href="favicons/manifest.json">
		
		<meta name="msapplication-TileColor" content="#ffffff">
		<meta name="msapplication-TileImage" content="favicons/ms-icon-144x144.png">
		<meta name="theme-color" content="#ffffff">
		
		<meta name="viewport" content="width=device-width,initial-scale=1.0"> <!-- used for proper media query functionality on mobile -->
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
		<table id="ticketTable" class="table bigTable">
			<thead>
				<tr>
					<th>Film</th><th>Data</th><th>Orario</th><th>Sala</th><th>Posto</th><th>Costo (&euro;)</th>
				</tr>
			</thead>
			<tbody>
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
					<span>Acquisto riuscito</span>
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