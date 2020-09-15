<%@ page language="java" contentType="text/html charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
	<head>
		<title>CMT - Homepage</title>
		<link rel="stylesheet" type="text/css" href="css/base.css">
		<link rel="stylesheet" type="text/css" href="css/filmDetails.css">	
		<link rel="stylesheet" type="text/css" href="css/modal.css">	
		<link rel="stylesheet" type="text/css" href="css/optionsList.css">
		<link rel="stylesheet" type="text/css" href="css/poster.css">
		<link rel="stylesheet" type="text/css" href="css/room.css">
		<link rel="stylesheet" type="text/css" href="css/table.css">
		<script src="js/fieldValidator.js"></script>
		<script src="js/dataParser.js"></script>
		<script src="http://code.jquery.com/jquery-latest.min.js"></script>
		<script src="js/homepagePurchaseChain.js"></script>
	</head>
	<body>
		<header>
			<span onclick="location.href='homepage.jsp'">
				<img src="icons/siteIcon.png">
				<h1>Castle Movie Theater</h1>					
			</span>
			<div>
				<%			
					String userType = (String)request.getSession().getAttribute("userType");
				
					if(userType == null)							
						out.print("<button onclick=\"location.href='login.jsp'\">Login</button>" +
								  "<button onclick=\"location.href='registration.jsp'\">Registrazione</button>");
					else
						out.print("<button onclick=\"location.href='" + userType + "PersonalArea.jsp'\">Area Personale</button>" +
								  "<button onclick=\"location.href='logout.jsp'\">Logout</button>");
				%>
			</div>			
		</header>
		
		<hr>
		
		<form id="filtreForm" class="optionsList" action="${pageContext.request.contextPath}/filtre" method="post">
			<input name="title" type="text" placeholder="Titolo" maxlength="30">
			<input name="genre" type="text" placeholder="Genere" maxlength="30">
			<input name="director" type="text" placeholder="Regista" maxlength="30">
			<input name="actor" type="text" placeholder="Attore" maxlength="30">
			<span>&nbsp;</span>
			<button type="submit">Filtra</button>
		</form>
		
		<hr>
				
		<div id="posterContainer">
		</div>		

		<div id="detailsModal" class="modalContainer">
			<div>
				<header>
					<h2>Dettagli film</h2>
					<button onclick="document.getElementById('detailsModal').style.display = 'none'">&#x02716;</button>
				</header>
				<br>
				<div class="filmDetailsContainer">
					<img id="poster" class="poster posterItem">
					<h3>Titolo</h3>
					<label id="title"></label>
					<h3>Durata</h3>
					<label id="runningTime"></label>
					<h3>Genere</h3>
					<label id="genre"></label>
					<h3>Regista</h3>
					<label id="director"></label>
					<h3>Attori</h3>
					<div class="actorSection">
						<label id="actor1"></label>
						<label id="actor2"></label>
					</div>
					<h3>Descrizione</h3>
					<label id="description"></label>
				</div>
				<br>
				<form id="projectionsForm" action="${pageContext.request.contextPath}/projections" method="post">
					<input name="idFilm" type="hidden">
					<button type="submit" style="width: 100%">Visualizza proiezioni</button>
				</form>
			</div>
		</div>
		
		<div id="projectionsModal" class="modalContainer">
			<div>
				<header>
					<h2>Proiezioni</h2>
					<button onclick="document.getElementById('projectionsModal').style.display = 'none'">&#x02716;</button>
				</header>
				<br>
				<table id="projectionsTable" class="table handable" style="width: 500px">
					<thead>
						<tr>
							<th>Data</th><th>Orario</th><th>Costo (&euro;)</th>
						</tr>
					</thead>
					<tbody style="max-height: 500px">
					</tbody>					
				</table>
				<br>
				<form id="seatsForm" action="${pageContext.request.contextPath}/seats" method="post">
					<input name="idProjection" type="hidden">
					<button id="seatsButton" type="submit" style="width: 100%">Visualizza posti a sedere</button>
				</form>		
			</div>
		</div>
		
		<div id="seatsModal" class="modalContainer">
			<div>
				<header>
					<h2>Acquisto biglietti</h2>
					<button onclick="document.getElementById('seatsModal').style.display = 'none'">&#x02716;</button>
				</header>
				<br>
				<div class="optionsList" style="width: 100%">
			 		<span><h3>Film</h3><label id="film"></label></span>
			 		<span>&nbsp;</span>
			 		<span><h3>Proiezione</h3><label id="projection"></label></span>
			 		<span>&nbsp;</span>
			 		<span><h3>Prezzo totale</h3><span><label id="price"></label><label> &euro;</label></span></span>
			 	</div>
			 	<br>
			 	<form id="basketForm" onsubmit="prepareBasketData()" action="${pageContext.request.contextPath}/basket" method="post">
					<table id="seatsTable" class="room">
						<tbody>								
						</tbody>
					</table>
					<br>
					<input name="ticketsNumber" type="hidden">
					<button id="basketButton" type="submit" style="width: 100%">Aggiungi posti al carrello</button>					
				</form>
			</div>
		</div>
	</body>
</html>