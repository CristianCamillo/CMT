<%@ page language="java" contentType="text/html charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
	<head>	
		<title>CMT - Area Personale Gestore</title>
		
		<link rel="stylesheet" type="text/css" href="css/base.css">
		<link rel="stylesheet" type="text/css" href="css/eye.css">
		<link rel="stylesheet" type="text/css" href="css/modal.css">
		<link rel="stylesheet" type="text/css" href="css/optionsList.css">
		<link rel="stylesheet" type="text/css" href="css/personalArea.css">
		<link rel="stylesheet" type="text/css" href="css/table.css">
		
		<script src="js/alterPasswordVisibility.js"></script>
		<script src="js/dataParser.js"></script>
		<script src="js/fieldValidator.js"></script>
		<script src="js/jquery.js"></script>
		<script src="js/managerChain.js"></script>
		<script src="js/personalDataFormsManager.js"></script>
		
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
		<div class="areaBody">
			<div class="optionsList">
				<span><h3>Username</h3><span id="username"><%= request.getSession().getAttribute("username") %></span></span>
				<span>&nbsp;</span>
				<span>&nbsp;</span>
				<button onclick="document.getElementById('usernameModal').style.display = 'flex'">Modifica username</button>
				<button onclick="document.getElementById('passwordModal').style.display = 'flex'">Modifica password</button>
			</div>			
			<hr>
			<div>
				<h2>Film</h2>
				<span>&nbsp;</span>
				<table id="filmsTable" class="table managerTable handable">
					<thead>
						<tr>
							<th>Titolo</th><th>Durata (min)</th><th>Genere</th><th>Regista</th>
						</tr>
					</thead>
					<tbody>
					</tbody>					
				</table>
				<span class="buttonList">
					<button onclick="openAddFilmModal()">Aggiungi film</button>
					<button id="updateFilmButton" onclick="openUpdateFilmModal()" disabled>Modifica film</button>
					<button id="deleteFilmButton" onclick="openDeleteFilmModal()" disabled>Elimina film</button>
				</span>
				<hr>
				<h2>Proiezioni</h2>
				<span>&nbsp;</span>
				<table id="projectionsTable" class="table managerTable handable">
					<thead>
						<tr>
							<th>Film</th><th>Data</th><th>Orario</th><th>Costo (&euro;)</th><th>Sala</th>
						</tr>
					</thead>
					<tbody>
					</tbody>					
				</table>
				<span class="buttonList">
					<button onclick="openAddProjectionModal()">Aggiungi proiezione</button>
					<button id="updateProjectionButton" onclick="openUpdateProjectionModal()" disabled>Modifica proiezione</button>
					<button id="deleteProjectionButton" onclick="openDeleteProjectionModal()" disabled>Elimina proiezione</button>
				</span>
			</div>			
		</div>
		
		<div id="usernameModal" class="modalContainer">
			<div>
				<header>
					<h2>Modifica username</h2>
					<button onclick="document.getElementById('usernameModal').style.display = 'none'">&#x02716;</button>
				</header>
				<br>
				<form id="usernameForm" class="optionsList" onsubmit="return validateUsername(document.getElementsByName('newUsername')[0])" action="${pageContext.request.contextPath}/personalData" method="post">
					<span>
						<label for="newUsername">Username</label>
						<input id="newUsername" name="newUsername" type="text" oninput="validateUsername(this)">
					</span>
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
				<form id="passwordForm" class="optionsList" onsubmit="return validatePassword(document.getElementById('newPassword'))" action="${pageContext.request.contextPath}/personalData" method="post">
					<span>
						<label for="newPassword">Password</label>
						<span class="passwordCombo">
							<input id="newPassword" name="newPassword" type="password" oninput="validatePassword(this)">
							<img src="svg/eyeSlash.svg" class="eye" onclick="alterPasswordVisibility(document.getElementsByName('newPassword')[0], this)">
						</span>
					</span>
					<span>&nbsp;</span>
					<button type="submit">Aggiorna</button>
					<input name="toChange" type="hidden" value="1">
				</form>
			</div>
		</div>
		
		<div id="filmModal" class="modalContainer">
			<div>
				<header>
					<h2 id="filmModalTitle"></h2>
					<button onclick="document.getElementById('filmModal').style.display = 'none'">&#x02716;</button>
				</header>
				<br>
				<form id="filmForm" class="optionsList">
					<span>
						<label for="title">Titolo</label>
						<input id="title" name="title" type="text" oninput="validateTitle(this)">
					</span>
					<span>
						<label for="runningTime">Durata</label>
						<span class="minCombo">
							<input id="runningTime" name="runningTime" type="text" oninput="validatePositiveInteger(this)">
							<span>min</span>
						</span>						
					</span>
					<span>
						<label for="genre">Genere</label>
						<input id="genre" name="genre" type="text" oninput="validateNominative(this)">
					</span>
					<span>
						<label for="director">Regista</label>
						<input id="director" name="director" type="text" oninput="validateNominative(this)">
					</span>
					<span>
						<label for="actor1">Attore 1</label>
						<input id="actor1" name="actor1" type="text" oninput="validateNominative(this)">
					</span>
					<span>
						<label for="actor2">Attore 2</label>
						<input id="actor2" name="actor2" type="text" oninput="validateNominative(this)">
					</span>
					<span class="textareaSpan">
						<label for="description">Descrizio.</label>
						<textarea id="description" name="description" oninput="validateDescription(this)" rows="5"></textarea>
					</span>					
					<input id="poster" name="poster" type="file" accept="image/gif, image/jpeg, image/png" oninput="validatePoster(this)">
					<span>&nbsp;</span>
					<button id="filmModalButton" type="submit"></button>
					<input name="idFilm" type="hidden">
				</form>
			</div>
		</div>
		
		<div id="projectionModal" class="modalContainer">
			<div>
				<header>
					<h2 id="projectionModalTitle"></h2>
					<button onclick="document.getElementById('projectionModal').style.display = 'none'">&#x02716;</button>
				</header>
				<br>
				<form id="projectionForm" class="optionsList" onsubmit="" method="post">
					<span>
						<label for="titles">Film</label>
						<select id="titles">
						</select>
					</span>
					<span>
						<label for="date">Data</label>
						<input id="date" name="date" type="date">
					</span>	
					<span>
						<label for="time">Orario</label>
						<input id="time" name="time" type="time">
					</span>
					<span>
						<label for="price">Costo</label>
						<span class="euroCombo">
							<input id="price" name="price" type="text" oninput="validateNNegativeFloat(this)">
							<span>&euro;</span>
						</span>
					</span>
					<span>
						<label for="rooms">Sala</label>
						<select id="rooms">
						</select>
					</span>
					<span>&nbsp;</span>
					<button id="projectionModalButton" type="submit"></button>
					<input name="idProjection" type="hidden">
				</form>
			</div>
		</div>
		
		<div id="deleteFilmModal" class="modalContainer">
			<div>
				<header>
					<h2>Eliminazione film</h2>
					<button onclick="document.getElementById('deleteFilmModal').style.display = 'none'">&#x02716;</button>
				</header>
				<br>
				<form id="deleteFilmForm" class="optionsList" action="deleteFilm" method="post">
					<p>Confermi l'eliminazione del film selezionato?</p>
					<span>&nbsp;</span>
					<button type="submit">Conferma</button>
					<input name="idFilm" type="hidden">
				</form>
			</div>
		</div>
		
		<div id="deleteProjectionModal" class="modalContainer">
			<div>
				<header>
					<h2>Eliminazione proiezione</h2>
					<button onclick="document.getElementById('deleteProjectionModal').style.display = 'none'">&#x02716;</button>
				</header>
				<br>
				<form id="deleteProjectionForm" class="optionsList" action="deleteProjection" method="post">					
					<span>Confermi l'eliminazione</span>
					<span>della proiezione selezionata?</span>
					<span>&nbsp;</span>
					<button type="submit">Conferma</button>
					<input name="idProjection" type="hidden">
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
					<span id="successMsg"></span>
					<span>&nbsp;</span>
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
					<span id="errorMsg"></span>
					<span>&nbsp;</span>
					<button onclick="document.getElementById('errorModal').style.display = 'none'">Ok</button>
				</div>
			</div>
		</div>
	</body>
</html>