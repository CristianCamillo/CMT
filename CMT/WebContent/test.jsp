<%@ page language="java" contentType="text/html charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
	<head>
		<title>Test</title>
		
		<link rel="stylesheet" type="text/css" href="css/base.css">
		<link rel="stylesheet" type="text/css" href="css/optionsList.css">
		
		<script src="js/fieldValidator.js"></script>
		<script src="js/jquery.js"></script>
		<script src="js/test.js"></script>
	</head>
	<body>
		<form id="filmForm" class="optionsList">
			<label>Titolo<input name="title" type="text" oninput="validateTitle(this)"></label>
			<label>
				Durata
				<span>
					<input name="runningTime" type="text" oninput="validatePositiveInteger(this)">
					<span>min</span>
				</span>
			</label>
			<label>Genere<input name="genre" type="text" oninput="validateNominative(this)"></label>
			<label>Regista<input name="director" type="text" oninput="validateNominative(this)"></label>
			<label>Attore 1<input name="actor1" type="text" oninput="validateNominative(this)"></label>
			<label>Attore 2<input name="actor2" type="text" oninput="validateNominative(this)"></label>
			<label>Descrizione<textarea name="description" oninput="validateDescription(this)" rows="5"></textarea></label>					
			<input name="poster" type="file" accept="image/gif, image/jpeg, image/png" oninput="validatePoster(this)">
			<span>&nbsp;</span>
			<button type="submit">Invia</button>
		</form>
	</body>
</html>