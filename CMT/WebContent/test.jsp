<%@ page language="java" contentType="text/html charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
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
</head>
<body>
<form id="form" action="/CMT/test" method="post" enctype="multipart/form-data">
	<input name="image" type="file" accept="image/gif, image/jpeg, image/png">
	<button type="submit">Submit</button>
</form>
<form id="form" action="/CMT/test" method="post" enctype="multipart/form-data">
	<input name="image" type="file" accept="image/gif, image/jpeg, image/png">
	<button type="submit">Submit</button>
</form>
<form id="filmForm" class="optionsList" onsubmit="return validateFilmForm()" action="/CMT/addFilm"  method="post" enctype="multipart/form-data">
	<input name="idFilm" type="hidden">
	<input name="title" type="text" placeholder="Titolo" oninput="validateTitle(this)">
	<input name="runningTime" type="text" placeholder="Durata" oninput="validatePositiveInteger(this)">
	<input name="genre" type="text" placeholder="Genere" oninput="validateNominative(this)">
	<input name="director" type="text" placeholder="Regista" oninput="validateNominative(this)">
	<input name="actor1" type="text" placeholder="Attore 1" oninput="validateNominative(this)">
	<input name="actor2" type="text" placeholder="Attore 2" oninput="validateNominative(this)">
	<textarea name="description" placeholder="Descrizione" oninput="validateDescription(this)" rows="5"></textarea>
	<input name="poster" type="file" accept="image/gif, image/jpeg, image/png">
	<span>&nbsp;</span>
	<button id="filmModalButton" type="submit">Aggiungi</button>
</form>
</body>
</html>