<%@ page language="java" contentType="text/html charset=UTF-8" pageEncoding="UTF-8"
import="java.util.ArrayList,model.Film,model.Projection,model.Room,DAO.RoomDAO"%>
<!DOCTYPE html>
<html>
	<head>
		<title>CMT - Homepage</title>
		<link rel="stylesheet" type="text/css" href="css/base.css">
		<link rel="stylesheet" type="text/css" href="css/filmData.css">
		<link rel="stylesheet" type="text/css" href="css/form.css">
		<link rel="stylesheet" type="text/css" href="css/frame.css">
		<link rel="stylesheet" type="text/css" href="css/header.css">
		<link rel="stylesheet" type="text/css" href="css/poster.css">
		<link rel="stylesheet" type="text/css" href="css/projectionRoom.css">
		<link rel="stylesheet" type="text/css" href="css/table.css">
		<script src="js/filtroValidator.js"></script>
		<script type="text/javascript">
		
			var selectedFilm;
			var selectedProjection;
		
			var amount;
			
			var films = <%= request.getSession().getAttribute("films") %>;
			var projections2D = <%= request.getSession().getAttribute("projections") %>;
			var roomStates2D = <%= request.getSession().getAttribute("roomStates") %>;

			function openDetailsFrame(n)
			{			
				// salva il film selezionato
				selectedFilm = n;
				
				// prende il film selezionato
				var film = films[n];
				
				// aggiorna i campi dei dettagli
				document.getElementById("poster").src = "posters/" + film[8];
				document.getElementById("title").innerHTML = film[1];
				document.getElementById("runningTime").innerHTML = film[2] + " min";
				document.getElementById("genre").innerHTML = film[3] != "null" ? film[3] : "";
				document.getElementById("director").innerHTML = film[4] != "null" ? film[4] : "";
				document.getElementById("actor1").innerHTML = film[5] != "null" ? film[5] : "";
				document.getElementById("actor2").innerHTML = film[6] != "null" ? film[6] : "";
				document.getElementById("description").innerHTML = film[7] != "null" ? film[7] : "";
				
				// prende la tabella delle proiezioni e il suo tbody
				var table = document.getElementById("projectionsTable");
				var tbody = table.tBodies[0];
					
				// svuota la tabella delle proiezioni
				for(var i = 1, l = table.rows.length; i < l; i++)	// la tabella si accorcia ogni volta che si elimina una riga,
					table.deleteRow(1);								// quindi si salva la lunghezza iniziale e si elimina n volte la prima riga
				
				// riempie la tabella delle proiezioni con quelle del film selezionato
				// vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
				
				// si prende la lista delle proiezioni del film selezionato
				var projections = projections2D[selectedFilm];
					
				// si itera sulla lista delle proiezioni selezionata
				for(var i = 0; i < projections.length; i++)
				{
					// si prende una proiezione dalla lista delle proiezioni del film
					var projection = projections[i];
					
					// si crea la riga
					var tr = document.createElement('tr');
					tr.id = i;
					tr.onclick = function() { selectRow(this.id); };
					
					// si aggiunge una cella con la data
					var td = document.createElement('td');
					td.appendChild(document.createTextNode(parseDate(projection[1])));
					tr.appendChild(td);
															
					// si aggiunge una cella con l'orario
					td = document.createElement('td');
					td.appendChild(document.createTextNode(parseTime(projection[2])));
					tr.appendChild(td);
					
					// si aggiunge una cella con il costo
					td = document.createElement('td');
					td.appendChild(document.createTextNode(projection[3]));
					tr.appendChild(td);
					
					// si aggiunge la riga al tbody della tabella
					tbody.appendChild(tr);
				}
				
				// rende il frame visibile
				document.getElementById("detailsFrame").style.display = "flex";
			}
			
			function closeDetailsFrame()
			{
				// scrolla la tabella delle proiezioni all'inizio
				document.getElementById("projectionsTable").scrollTop = 0;
				
				// rende il frame non visibile				
				document.getElementById("detailsFrame").style.display = "none";
				
				deselectRows();
				
				// disabilita il pulsante di acquisto
				document.getElementById("selectSeatsButton").disabled = true;
			}
			
			function openPurchaseFrame()
			{		
				var projection = projections2D[selectedFilm][selectedProjection];
				amount = 0;

				document.getElementById("titleA").innerHTML = films[selectedFilm][1];
				document.getElementById("projectionA").innerHTML = parseDate(projection[1]) + " - " + parseTime(projection[2]);
				document.getElementById("amountA").innerHTML = "0";

				var table = document.getElementById("roomTable");
				var tbody = table.tBodies[0];

				for(var i = 0, l = table.rows.length; i < l; i++)
					table.deleteRow(0);

				var roomState = roomStates2D[selectedFilm][selectedProjection];
				var dims = roomState[0].split("-");
				var width = parseInt(dims[0]);
				var height = parseInt(dims[1]);

				for(var y = 0; y < height; y++)
				{
					var tr = document.createElement('tr');
					for(var x = 0; x < width; x++)
					{
						var td = document.createElement('td');
						
						var seat = document.createElement("img");
						
						seat.setAttribute("id", (x + 1) + "-" + (y + 1));
						seat.setAttribute("src", "seats/vacant.jpg");
						
						seat.onclick = function() { selectSeat(this.id); };
						
						td.appendChild(seat);
						tr.appendChild(td);
					}
					
					tbody.appendChild(tr);
				}
				
				for(var i = 1, l = roomState.length; i < l; i++)
				{					
					var pos = roomState[i].split("-");
					var x = parseInt(pos[0]);
					var y = parseInt(pos[1]);
					var id = x + "-" + y;
					
					document.getElementById(id).src = "seats/occupied.jpg";
				}
				
				document.getElementById("purchaseFrame").style.display = "flex";
			}
			
			function closePurchaseFrame()
			{		
				document.getElementById("purchaseFrame").style.display = "none";
			}			
			
			function selectRow(n)
			{			
				var userType = <% out.print("\"" + request.getSession().getAttribute("userType") + "\""); %>;
				
				if(userType != "client")
					return;
					
				n = parseInt(n);
				
				// deseleziona tutte le righe
				deselectRows();
				
				// aggiunge la classe "selected" alla riga indicata
				document.getElementById("projectionsTable").rows[n + 1].classList.add("selected"); 
				
				// salva la proiezione selezionata
				selectedProjection = n;
				
				// abilita il pulsante di acquisto
				document.getElementById("selectSeatsButton").disabled = false;
			}
			
			function selectSeat(id)
			{				
				var seat = document.getElementById(id);
				
				if(seat.src.includes("occupied.jpg"))
					return;				
				
				if(seat.src.includes("vacant.jpg"))
				{
					seat.src = "seats/selected.jpg";
					amount += parseInt(projections2D[selectedFilm][selectedProjection][3]);
				}
				else
				{
					seat.src = "seats/vacant.jpg";
					amount -= parseInt(projections2D[selectedFilm][selectedProjection][3]);
				}
				
				document.getElementById("amountA").innerHTML = amount;
			}
			
			function prepareTransaction()
			{
				if(amount == 0)
					return;
				
				var seats = "";
				
				var roomState = roomStates2D[selectedFilm][selectedProjection];
				var dims = roomState[0].split("-");
				var width = parseInt(dims[0]);
				var height = parseInt(dims[1]);
				
				for(var y = 0; y < height; y++)
					for(var x = 0; x < width; x++)
						if(document.getElementById((x + 1) + "-" + (y + 1)).src.includes("selected.jpg"))
							seats += x + "-" + y + "/";
				
				document.getElementById("seats").value = seats;
				document.getElementById("idProjection").value = projections2D[selectedFilm][selectedProjection][0];
				document.getElementById("amount").value = amount;
			}
			
			//---------------------//
			// operazione semplici //		
			//---------------------//
			
			// ritorna la data in forma DD-MM-YYYY
			function parseDate(date)
			{
				return date.substring(6, 8) + "-" + date.substring(4, 6) + "-" + date.substring(0, 4);
			}
			
			// ritorna l'orario in formato HH:MM
			function parseTime(time)
			{
				var h;
				var m;
				
				if(time.length <= 2)
				{
					h = "00";
					m = time.length == 2 ? time : ("0" + time);
				}
				else if(time.length == 3)
				{
					h = "0" + time.substring(0, 1);
					m = time.substring(1, 3);
				}
				else
				{
					h = time.substring(0, 2);
					m = time.substring(2, 4);
				}
				
				return h + ":" + m;
			}
			
			// deseleziona tutte le righe della tabella proiezioni
			function deselectRows()
			{
				var table = document.getElementById("projectionsTable");
				
				for(var i = 1; i < table.rows.length; i++)			// la riga 0 è l'intestazione, quindi viene saltata
					table.rows[i].classList.remove("selected");
			}
		</script>
	</head>
	<body>	
		<header>
			<span onclick="location.href='homepage.jsp'">
				<img src="icons/bigIcon.png">
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
		
		<form id="filtreForm" name="form" action="${pageContext.request.contextPath}/filtre" method="GET">
			<input type="text" name="title" placeholder="Titolo" maxlength="30">
			<input type="text" name="genre" placeholder="Genere" maxlength="30">
			<input type="text" name="director" placeholder="Regista" maxlength="30">
			<input type="text" name="actor" placeholder="Attore" maxlength="30">
			<br>
			<button type="submit">Filtra</button>
		</form>
		
		<hr>
		
		<table style="width: 100%; height: 100px; overflow: auto;">
			
			<tbody style="height: 200px">
				<tr>
					<td>Alfreds Futterkiste</td>
				    <td>Maria Anders</td>
				    <td>Germany</td>
				</tr>
				<tr>
					<td>Alfreds Futterkiste</td>
				    <td>Maria Anders</td>
				    <td>Germany</td>
				</tr>
				<tr>
					<td>Alfreds Futterkiste</td>
				    <td>Maria Anders</td>
				    <td>Germany</td>
				</tr>
				<tr>
					<td>Alfreds Futterkiste</td>
				    <td>Maria Anders</td>
				    <td>Germany</td>
				</tr>
				<tr>
					<td>Alfreds Futterkiste</td>
				    <td>Maria Anders</td>
				    <td>Germany</td>
				</tr>
				<tr>
					<td>Alfreds Futterkiste</td>
				    <td>Maria Anders</td>
				    <td>Germany</td>
				</tr>
				<tr>
					<td>Alfreds Futterkiste</td>
				    <td>Maria Anders</td>
				    <td>Germany</td>
				</tr>
				<tr>
					<td>Alfreds Futterkiste</td>
				    <td>Maria Anders</td>
				    <td>Germany</td>
				</tr>
				<tr>
					<td>Alfreds Futterkiste</td>
				    <td>Maria Anders</td>
				    <td>Germany</td>
				</tr>
				<tr>
					<td>Alfreds Futterkiste</td>
				    <td>Maria Anders</td>
				    <td>Germany</td>
				</tr>
				<tr>
					<td>Alfreds Futterkiste</td>
				    <td>Maria Anders</td>
				    <td>Germany</td>
				</tr>
				<tr>
					<td>Alfreds Futterkiste</td>
				    <td>Maria Anders</td>
				    <td>Germany</td>
				</tr>
			</tbody>
		</table>
		
		<div class="table" style="width: 300px; height: 300px">
			<span style="width: 100%; display: flex; "><h3>Data</h3><h3>Orario</h3><h3>Costo</h3></span>
			<div>		
				<table id="projectionsTable">
					<tr>
						<th>Data</th>
					    <th>Orario</th>
					    <th>Costo</th>
					</tr>
					<tr>
						<td>Alfreds Futterkiste</td>
					    <td>Maria Anders</td>
					    <td>Germany</td>
					</tr>
					<tr>
						<td>Alfreds Futterkiste</td>
					    <td>Maria Anders</td>
					    <td>Germany</td>
					</tr>
					<tr>
						<td>Alfreds Futterkiste</td>
					    <td>Maria Anders</td>
					    <td>Germany</td>
					</tr>
					<tr>
						<td>Alfreds Futterkiste</td>
					    <td>Maria Anders</td>
					    <td>Germany</td>
					</tr>
					<tr>
						<td>Alfreds Futterkiste</td>
					    <td>Maria Anders</td>
					    <td>Germany</td>
					</tr>
					<tr>
						<td>Alfreds Futterkiste</td>
					    <td>Maria Anders</td>
					    <td>Germany</td>
					</tr>
					<tr>
						<td>Alfreds Futterkiste</td>
					    <td>Maria Anders</td>
					    <td>Germany</td>
					</tr>
				</table>
			</div>
		</div>
		
		
		
		
		<!-- Lista dei poster dei film -->
		<%
			ArrayList<Film> films = (ArrayList<Film>)request.getSession().getAttribute("films");
			
			for(int i = 0, l = films != null ? films.size() : 0; i < l; i++)
				out.println("<img src=\"posters/" + films.get(i).getPoster() + "\" class=\"presPoster\" onclick=\"openDetailsFrame('"+ i +"')\">");
		%>

		<!-- DettagliFrame -->
		<div id="detailsFrame" class="frameContainer">
			<div class="frameContent">
				<span class="frameHeader">
					<h2>Dettagli film</h2>
					<button onclick="closeDetailsFrame()">&#x02716;</button>
				</span>
				<br>
				<div style="display: flex; flex-direction: column; max-width: 1000px;">
					<div class="filmDataContainer">
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
						<div class="multiple">
							<label id="actor1"></label>
							<label id="actor2"></label>
						</div>
						<h3>Descrizione</h3>
						<label id="description"></label>
					</div>
					<div>
						<hr>
						<h3>Proiezioni</h3>
						<br>
						
						<br>
						<button id="selectSeatsButton" onclick="openPurchaseFrame()" disabled>Procedi all'acquisto biglietti</button>
					</div>
				</div>
				
			</div>
		</div>
		
		<!-- AcquistoFrame -->
		<div id="purchaseFrame" class="frameContainer">
			<div class="frameContent">
				<span class="frameHeader">
					<h2>Acquisto biglietti</h2>
					<button onclick="closePurchaseFrame()">&#x02716;</button>
				</span>
				<br>
			 	<table>
			 		<colgroup>
						<col>
						<col style="width: 20px">
						<col style="width: 200px">
					</colgroup>
					<tbody>
						<tr><td><h3>Film</h3></td><td></td><td><label id="titleA"></label></td></tr>
						<tr><td>&nbsp;</td></tr>
						<tr><td><h3>Proiezione</h3></td><td></td><td><label id="projectionA"></label></td></tr>
						<tr><td>&nbsp;</td></tr>
						<tr><td><h3>Totale</h3></td><td></td><td><label id="amountA"></label></td></tr>
						<tr><td>&nbsp;</td></tr>
					</tbody>
			 	</table>
			 	<form onsubmit="prepareTransaction()" action="${pageContext.request.contextPath}/purchase" method="POST">
					<table id="roomTable" class="projectionRoom">
						<tbody>								
						</tbody>
					</table>
					<br>
					<button type="submit">Acquista biglietti</button>
					<input id="seats" name="seats" type="hidden" value="">
					<input id="idProjection" name="idProjection" type="hidden" value="">
					<input id="amount" name="amount" type="hidden" value="">
				</form>
			</div>
		</div>
	</body>
</html>