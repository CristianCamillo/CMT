<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title>CMT - Login</title>
		<link rel="stylesheet" type="text/css" href="css/base.css">
		<link rel="stylesheet" type="text/css" href="css/header.css">
		<link rel="stylesheet" type="text/css" href="css/logRegForm.css">
		<script src="js/fieldChecker.js"></script>
	</head>
	<body>
		<header>
			<span onclick="location.href='homepage.jsp'">
				<img src="icons/bigIcon.png">
				<h1>Castle Movie Theater</h1>					
			</span>
		</header>
		<hr>
		
		<form id="loginForm" name="form" action="${pageContext.request.contextPath}/login" method="POST">
			<table>
				<tbody>
					<tr>														
						<td><input name="username" type="text" placeholder="Username" oninput="checkFieldLength('username', 6, 20)" minlength="6" maxlength="20" required></td>
					</tr>
					<tr>
						<td><input name="password" type="password" placeholder="Password" oninput="checkFieldLength('password', 6, 20)" minlength="6" maxlength="20" required></td>
					</tr>
					<tr><td>&nbsp;</td></tr>
					<tr>
						<td><button type="submit">Accedi</button></td>
					</tr>
				</tbody>
			</table>
		</form>
	</body>
</html>