<%@ page language="java" contentType="text/html charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
	<head>	
		<title>CMT - Area Personale Gestore</title>
		<link rel="stylesheet" type="text/css" href="css/base.css">
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
			<div class="optionsList" style="min-width: 300px; margin-right: 20px;">
				<span><h3>Username</h3><label id="username"><%= request.getSession().getAttribute("username") %></label></span>
				<span><h3>Saldo</h3><span><label id="balance"><%= request.getSession().getAttribute("balance") %></label><label> &euro;</label></span></span>
				<span>&nbsp;</span>
				<span>&nbsp;</span>
				<button onclick="document.getElementById('usernameModal').style.display = 'flex'">Modifica username</button>
				<button onclick="document.getElementById('passwordModal').style.display = 'flex'">Modifica password</button>
				<button onclick="document.getElementById('amountModal').style.display = 'flex'">Aggiungi fondi</button>
			</div>
			
			<hr>

			<table id="ticketTable" class="table" style="margin-left: 20px">
				<thead>
					<tr>
						<th>Id</th><th>Titolo film</th><th>Data di proiezione</th><th>Orario</th><th>Numero sala</th><th>Posto a sedere</th><th>Costo (&euro;)</th>
					</tr>
				</thead>
				<tbody style="height: 700px">
				</tbody>					
			</table>
		</div>
		
	</body>
</html>