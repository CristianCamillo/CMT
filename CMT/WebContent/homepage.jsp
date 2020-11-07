<%@ page language="java" contentType="text/html charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
	<head>
		<title>CMT - Homepage</title>
		
		<link rel="stylesheet" type="text/css" href="css/base.css">
		<link rel="stylesheet" type="text/css" href="css/cart.css">
		<link rel="stylesheet" type="text/css" href="css/filmDetails.css">	
		<link rel="stylesheet" type="text/css" href="css/modal.css">	
		<link rel="stylesheet" type="text/css" href="css/optionsList.css">
		<link rel="stylesheet" type="text/css" href="css/poster.css">
		<link rel="stylesheet" type="text/css" href="css/room.css">
		<link rel="stylesheet" type="text/css" href="css/table.css">
		
		<script src="js/fieldValidator.js"></script>
		<script src="js/dataParser.js"></script>
		<script src="js/jquery.js"></script>
		<script src="js/homepageChain.js"></script>
				
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
			<div>
				<%			
					String userType = (String)session.getAttribute("userType");
					if(userType == "client")
						out.print("<img id=\"basketButton\" src=\"svg/shoppingCart.svg\" onclick=\"location.href = 'basket.jsp'\">");
				%>
				<div>
					<%						
						if(userType == null)							
							out.print("<button onclick=\"location.href = 'login.jsp'\">Login</button>" +
									  "<button onclick=\"location.href = 'registration.jsp'\">Registrazione</button>");
						else
							out.print("<button onclick=\"location.href = '" + userType + "PersonalArea.jsp'\">Area Personale</button>" +
									  "<button onclick=\"location.href = 'logout.jsp'\">Logout</button>");
					%>
				</div>
			</div>		
		</header>		
		<hr>		
		<form id="filtreForm" class="optionsList" action="${pageContext.request.contextPath}/filmFiltre" method="post">
			<span>
				<label for="titleF">Titolo</label>
				<input id="titleF" name="title" type="text" maxlength="30">
			</span>
			<span>
				<label for="genreF">Genere</label>
				<input id="genreF" name="genre" type="text" maxlength="30">
			</span>
			<span>
				<label for="directorF">Regista</label>
				<input id="directorF" name="director" type="text" maxlength="30">
			</span>
			<span>
				<label for="actorF">Attore</label>
				<input id="actorF" name="actor" type="text" maxlength="30">
			</span>
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
					<span id="title"></span>
					<h3>Durata</h3>
					<span id="runningTime"></span>
					<h3>Genere</h3>
					<span id="genre"></span>
					<h3>Regista</h3>
					<span id="director"></span>
					<h3>Attori</h3>
					<div class="actorSection">
						<span id="actor1"></span>
						<span id="actor2"></span>
					</div>
					<h3 id="descriptionH"></h3>
					<p id="description"></p>
				</div>
				<br>
				<form id="projectionsForm" action="${pageContext.request.contextPath}/filmProjectionsLoader" method="post">
					<button type="submit" style="width: 100%">Visualizza proiezioni</button>
					<input name="idFilm" type="hidden">
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
				<table id="projectionsTable" class="table handable smallTable">
					<thead>
						<tr>
							<th>Data</th><th>Orario</th><th>Costo (&euro;)</th>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
				<br>
				<form id="seatsForm" action="${pageContext.request.contextPath}/seatsLoader" method="post">
					<button id="seatsButton" type="submit" style="width: 100%">Visualizza posti a sedere</button>
					<input name="idProjection" type="hidden">
				</form>		
			</div>
		</div>
		
		<div id="seatsModal" class="modalContainer">
			<div>
				<header>
					<h2>Selezione posti</h2>
					<button onclick="document.getElementById('seatsModal').style.display = 'none'">&#x02716;</button>
				</header>
				<br>
				<div class="optionsList" style="width: 100%">
			 		<span><h3>Film</h3><span id="film"></span></span>
			 		<span>&nbsp;</span>
			 		<span><h3>Proiezione</h3><span id="projection"></span></span>
			 		<span>&nbsp;</span>
			 		<span><h3>Prezzo totale</h3><span><span id="price"></span><span> &euro;</span></span></span>
			 	</div>
			 	<br>
				<table id="seatsTable" class="room">
					<tbody>								
					</tbody>
				</table>
				<br>
				<button onclick="sendTickets()" style="width: 100%" <% if(userType != "client") out.print("disabled");%>>Aggiorna il carrello</button>
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
					<span>Carrello aggiornato</span>
					<span>&nbsp;</span>
					<button onclick="closeAllModals()">Ok</button>
				</div>
			</div>
		</div>
	</body>
</html>