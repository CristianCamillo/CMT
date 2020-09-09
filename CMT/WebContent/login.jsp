<%@ page language="java" contentType="text/html charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title>CMT - Login</title>
		<link rel="stylesheet" type="text/css" href="css/base.css">
		<link rel="stylesheet" type="text/css" href="css/header.css">
		<link rel="stylesheet" type="text/css" href="css/optionsList.css">
		<script src="js/colorField.js"></script>
	</head>
	<body>
		<header>
			<span onclick="location.href='homepage.jsp'">
				<img src="icons/bigIcon.png">
				<h1>Castle Movie Theater</h1>					
			</span>
		</header>
		
		<hr>
		
		<form class="optionsList" style="margin: 100px auto" action="${pageContext.request.contextPath}/login" method="POST">
			<input name="username" type="text" placeholder="Username" oninput="checkFieldLength(this, 6, 20)" minlength="6" maxlength="20" required>
			<input name="password" type="password" placeholder="Password" oninput="checkFieldLength(this, 6, 20)" minlength="6" maxlength="20" required>
			<span><label for="isManager">Accedi come manager</label><input id="isManager" name="isManager" type="checkbox" style="width: 1px"></span>
			<span>&nbsp;</span>
			<button type="submit">Accedi</button></td>
		</form>
	</body>
</html>