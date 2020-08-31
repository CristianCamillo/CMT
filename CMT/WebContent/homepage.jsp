<%@ page language="java" contentType="text/html charset=UTF-8" pageEncoding="UTF-8"
import="java.util.ArrayList, beans.Film, beans.Proiezione, beans.Sala, DAO.SalaDAO"%>
<!DOCTYPE html>
<html>
	<head>
		<title>CMT - Homepage</title>
		<link rel="stylesheet" type="text/css" href="css/base.css">
		<link rel="stylesheet" type="text/css" href="css/button.css">
		<link rel="stylesheet" type="text/css" href="css/poster.css">
		<link rel="stylesheet" type="text/css" href="css/frame.css">
		<link rel="stylesheet" type="text/css" href="css/table.css">
		<link rel="stylesheet" type="text/css" href="css/misc.css">		
		<script src="js/filtroValidator.js"></script>
		<script type="text/javascript">
		
			var selectedFilm;
			var selectedProiezione;
		
			var totale;
			
			var films = <%= request.getSession().getAttribute("films") %>;
			var proiezioni2D = <%= request.getSession().getAttribute("proiezioni") %>;
			var statoSale2D = <%= request.getSession().getAttribute("statoSale") %>;			
			
			function openDettagliFrame(n)
			{			
				// salva il film selezionato
				selectedFilm = n;
				
				// prende il film selezionato
				var film = films[n];
				
				// aggiorna i campi dei dettagli
				document.getElementById("locandina").src = "posters/" + film[8];
				document.getElementById("titolo").innerHTML = film[1];
				document.getElementById("durata").innerHTML = film[2] + " min";
				document.getElementById("genere").innerHTML = film[3] != "null" ? film[3] : "";
				document.getElementById("regista").innerHTML = film[4] != "null" ? film[4] : "";
				document.getElementById("attore1").innerHTML = film[5] != "null" ? film[5] : "";
				document.getElementById("attore2").innerHTML = film[6] != "null" ? film[6] : "";
				document.getElementById("descrizione").innerHTML = film[7] != "null" ? film[7] : "";
				
				// prende la tabella delle proiezioni e il suo tbody
				var table = document.getElementById("proiezioniTable");
				var tbody = table.tBodies[0];
					
				// svuota la tabella delle proiezioni
				for(var i = 1, l = table.rows.length; i < l; i++)	// la tabella si accorcia ogni volta che si elimina una riga,
					table.deleteRow(1);								// quindi si salva la lunghezza iniziale e si elimina n volte la prima riga
				
				// riempie la tabella delle proiezioni con quelle del film selezionato
				// vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
				
				// si prende la lista delle proiezioni del film selezionato
				var proiezioni = proiezioni2D[selectedFilm];
				
				// si itera sulla lista delle proiezioni selezionata
				for(var i = 0; i < proiezioni.length; i++)
				{
					// si prende una proiezione dalla lista delle proiezioni del film
					var proiezione = proiezioni[i];
					
					// si crea la riga
					var tr = document.createElement('tr');
					tr.id = i;
					tr.onclick = function() { selectRow(this.id); };
					
					// si aggiunge una cella con la data
					var td = document.createElement('td');
					td.appendChild(document.createTextNode(parseDate(proiezione[1])));
					tr.appendChild(td);
															
					// si aggiunge una cella con l'orario
					td = document.createElement('td');
					td.appendChild(document.createTextNode(parseTime(proiezione[2])));
					tr.appendChild(td);
					
					// si aggiunge una cella con il costo
					td = document.createElement('td');
					td.appendChild(document.createTextNode(proiezione[3]));
					tr.appendChild(td);
					
					// si aggiunge la riga al tbody della tabella
					tbody.appendChild(tr);
				}
				
				// rende il frame visibile
				document.getElementById("dettagliFrame").style.display = "block";
			}
			
			function closeDettagliFrame()
			{
				// scrolla la tabella delle proiezioni all'inizio
				document.getElementById("proiezioniDiv").scrollTop = 0;
				
				// rende il frame non visibile				
				document.getElementById("dettagliFrame").style.display = "none";
				
				deselectRows();
				
				// disabilita il pulsante di acquisto
				document.getElementById("acquistaButton").disabled = true;
			}
			
			function openAcquistoFrame()
			{		
				var proiezione = proiezioni2D[selectedFilm][selectedProiezione];
				totale = 0;
				
				document.getElementById("titoloA").innerHTML = films[selectedFilm][1];
				document.getElementById("proiezioneA").innerHTML = parseDate(proiezione[1]) + " - " + parseTime(proiezione[2]);
				document.getElementById("totaleA").innerHTML = "0";
				
				var table = document.getElementById("salaTable");
				var tbody = table.tBodies[0];
					
				for(var i = 0, l = table.rows.length; i < l; i++)
					table.deleteRow(0);
				
				var statoSala = statoSale2D[selectedFilm][selectedProiezione];
				var dims = statoSala[0].split("-");
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
						seat.setAttribute("width", 30);
						seat.setAttribute("height", 30);
						
						seat.onclick = function() { selectSeat(this.id); };
						
						td.appendChild(seat);
						tr.appendChild(td);
					}
					
					tbody.appendChild(tr);
				}
				
				for(var i = 1, l = statoSala.length; i < l; i++)
				{					
					var pos = statoSala[i].split("-");
					var x = parseInt(pos[0]);
					var y = parseInt(pos[1]);
					var id = x + "-" + y;
					
					document.getElementById(id).src = "seats/occupied.jpg";
				}
				
				document.getElementById("acquistoFrame").style.display = "block";
			}
			
			function closeAcquistoFrame()
			{		
				document.getElementById("acquistoFrame").style.display = "none";
			}			
			
			function selectRow(n)
			{			
				var tipo = <% out.print("\"" + request.getSession().getAttribute("tipo") + "\""); %>;
				
				if(tipo != "cliente")
					return;
					
				n = parseInt(n);
				
				// deseleziona tutte le righe
				deselectRows();
				
				// aggiunge la classe "selected" alla riga indicata
				document.getElementById("proiezioniTable").rows[n + 1].classList.add("selected"); 
				
				// salva la proiezione selezionata
				selectedProiezione = n;
				
				// abilita il pulsante di acquisto
				document.getElementById("acquistaButton").disabled = false;
			}
			
			function selectSeat(id)
			{				
				var seat = document.getElementById(id);
				
				if(seat.src.includes("occupied.jpg"))
					return;				
				
				if(seat.src.includes("vacant.jpg"))
				{
					seat.src = "seats/selected.jpg";
					totale += parseInt(proiezioni2D[selectedFilm][selectedProiezione][3]);
				}
				else
				{
					seat.src = "seats/vacant.jpg";
					totale -= parseInt(proiezioni2D[selectedFilm][selectedProiezione][3]);
				}
				
				document.getElementById("totaleA").innerHTML = totale;
			}
			
			function prepareTransaction()
			{
				var posti = "";
				
				var statoSala = statoSale2D[selectedFilm][selectedProiezione];
				var dims = statoSala[0].split("-");
				var width = parseInt(dims[0]);
				var height = parseInt(dims[1]);
				
				for(var y = 0; y < height; y++)
					for(var x = 0; x < width; x++)
						if(document.getElementById((x + 1) + "-" + (y + 1)).src.includes("selected.jpg"))
							posti += x + "-" + y + "/";
				
				document.getElementById("posti").value = posti;				
				document.getElementById("idProiezione").value = proiezioni2D[selectedFilm][selectedProiezione][0];
				document.getElementById("totale").value = totale;
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
				var table = document.getElementById("proiezioniTable");
				
				for(var i = 1; i < table.rows.length; i++)			// la riga 0 è l'intestazione, quindi viene saltata
					table.rows[i].classList.remove("selected");
			}
		</script>
	</head>
	<body>	
		<!-- Pulsante "Homepage" e "Login" "Registrazione" / "Area Personale" "Logout" -->
		<table>
			<colgroup>
				<col>
				<col width="100%">
				<col>
			</colgroup>
			<tbody>
				<tr>
					<td rowspan="2"><button class="homepageButton" onclick="location.href='homepage.jsp'"><b>CASTLE MOVIE THEATER</b></button></td>
					<td>&nbsp;</td>
					<td>
						<%			
							String tipo = (String)request.getSession().getAttribute("tipo");
						
							if(tipo == null)							
								out.print("<button onclick=\"location.href='login.jsp'\">Login</button>");
							else
								out.print("<button onclick=\"location.href='areaPersonale" + tipo.substring(0, 1).toUpperCase() + tipo.substring(1) + ".jsp'\">Area Personale</button>");
						%>
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>
						<%									
							if(tipo == null)							
								out.print("<button onclick=\"location.href='registrazione.jsp'\">Registrazione</button>");
							else
								out.print("<button onclick=\"location.href='logout.jsp'\">Logout</button>");
						%>
					</td>
				</tr>
			</tbody>
		</table>
		
		<hr>
		
		<!-- Form del filtro film -->
		<form name="form" action="${pageContext.request.contextPath}/filtro" method="GET">
			<table style="width: 300px">
				<tbody>
					<tr>
						<td><input type="text" name="titolo" placeholder="Titolo" maxlength="30"></td>
					</tr>
					<tr>
						<td><input type="text" name="genere" placeholder="Genere" maxlength="30"></td>
					</tr>
					<tr>
						<td><input type="text" name="regista" placeholder="Regista" maxlength="30"></td>
					</tr>
					<tr>
						<td><input type="text" name="attore" placeholder="Attore" maxlength="30"></td>
					</tr>
					<tr><td>&nbsp;</td></tr>
					<tr>						
						<td><button type="submit">Filtra</button></td>
					</tr>
				</tbody>
			</table>
		</form>
		
		<hr>
		
		<!-- Lista dei film -->
		<%
			ArrayList<Film> films = (ArrayList<Film>)request.getSession().getAttribute("films");
			
			for(int i = 0, l = films != null ? films.size() : 0; i < l; i++)
				out.println("<img src=\"images/" + films.get(i).getLocandina() + "\" class=\"presPoster\" onclick=\"openDettagliFrame('"+ i +"')\">");
		%>
		
		<!-- DettagliFrame -->
		<div id="dettagliFrame" class="frameContainer">
			<div class="frame">
				<table>
					<colgroup>
						<col width="100%">
						<col>
					</colgroup>
					<tbody>
						<tr><td><label style="font-size: 20px"><b>Dettagli film</b></label></td><td><button onclick="closeDettagliFrame()">&#x02716;</button></td></tr>
					</tbody>
				</table>
				<br>
				<table>
					<colgroup>
						<col>
						<col width="20px">
						<col>
					</colgroup>
					<tbody>
						<tr valign="top">
							<td><img id="locandina" class="poster"></td>
							<td></td>
							<td>
								<table>
									<colgroup>
										<col>
										<col width="15px">
										<col>
									</colgroup>
									<tbody>
										<tr><td><label><b>Titolo</b></label></td><td></td><td><label id="titolo"></label></td></tr>
										<tr><td>&nbsp;</td></tr>
										<tr><td><label><b>Durata</b></label></td><td></td><td><label id="durata"></label></td></tr>
										<tr><td>&nbsp;</td></tr>
										<tr><td><label><b>Genere</b></label></td><td></td><td><label id="genere"></label></td></tr>
										<tr><td>&nbsp;</td></tr>
										<tr><td><label><b>Regista</b></label></td><td></td><td><label id="regista"></label></td></tr>
										<tr><td>&nbsp;</td></tr>
										<tr><td><label><b>Attori</b></label></td><td></td><td><label id="attore1"></label></tr>
										<tr><td></td><td></td><td><label id="attore2"></label></td></tr>
										<tr><td>&nbsp;</td></tr>
										<tr><td valign="top"><label><b>Descrizione</b></label></td><td></td><td><label id="descrizione"></label></td></tr>
									</tbody>
								</table>
							</td>
						</tr>
					</tbody>
				</table>
				<hr>
				<table>
					<colgroup>
						<col>
						<col width="100%">
						<col>
					</colgroup>
					<tbody>
						<tr valign="top">
							<td><label>Proiezioni:</label></td>
							<td>
								<div id="proiezioniDiv" class="tableContainer">
									<table id="proiezioniTable" class="table maxWidth">
										<colgroup>
											<col width="50%">
											<col>
											<col>
										</colgroup>
										<tbody>
											<tr><th><label>Data</label></th><th><label>Orario</label></th><th><label>Costo</label></th></tr>
										</tbody>
									</table>
								</div>
							</td>
							<td><button id="acquistaButton" onclick="openAcquistoFrame()" disabled>Acquista biglietti</button></td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		
		<!-- AcquistoFrame -->
		<div id="acquistoFrame" class="frameContainer">
			<div class="frame">
				<table>
					<colgroup>
						<col width="100%">
						<col>
					</colgroup>
					<tbody>
						<tr><td><label><b>Acquisto biglietti</b></label></td><td><button onclick="closeAcquistoFrame()">&#x02716;</button></td></tr>
					</tbody>
				</table>
				<br>
			 	<table>
			 		<colgroup>
						<col>
						<col width="20px">
						<col width="200px">
					</colgroup>
					<tbody>
						<tr><td><label><b>Film:</b></label></td><td></td><td><label id="titoloA"></label></td></tr>
						<tr><td>&nbsp;</td></tr>
						<tr><td><label><b>Proiezione</b></label></td><td></td><td><label id="proiezioneA"></label></td></tr>
						<tr><td>&nbsp;</td></tr>
						<tr><td><label><b>Totale</b></label></td><td></td><td><label id="totaleA"></label></td></tr>
						<tr><td>&nbsp;</td></tr>
					</tbody>
			 	</table>
			 	<form onsubmit="prepareTransaction()" action="${pageContext.request.contextPath}/acquisto" method="POST">
					<table>
						<tbody>
							<tr>
								<td>
									<div style="width: 100%; border: 1px solid black; border-collapse: collapse;">
										<table id="salaTable">
											<tbody>								
											</tbody>
										</table>
									</div>
								</td>
							</tr>
							<tr><td>&nbsp;</td></tr>
							<tr><td><input type="submit" style="width: 100%; height: 80px;" value="Acquista biglietti"></td></tr> 
						</tbody>
					</table>
					<input id="posti"  name="posti" type="hidden" value="">
					<input id="idProiezione" name="idProiezione" type="hidden" value="">
					<input id="totale" name="totale" type="hidden" value="">
				</form>
			</div>
		</div>
	</body>
</html>