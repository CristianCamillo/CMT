<%@ page language="java" contentType="text/html charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
	<head>
		<title>CMT - Basket</title>
		<link rel="stylesheet" type="text/css" href="css/base.css">
		<link rel="stylesheet" type="text/css" href="css/modal.css">
		<link rel="stylesheet" type="text/css" href="css/table.css">
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
		
		<table id="ticketTable" class="table" style="min-width: 1000px; margin: 20px">
			<thead>
				<tr>
					<th>Id</th><th>Titolo film</th><th>Data di proiezione</th><th>Orario</th><th>Numero sala</th><th>Posto a sedere</th><th>Costo (&euro;)</th>
				</tr>
			</thead>
			<tbody style="height: 700px">
			</tbody>					
		</table>
		
	</body>
</html>