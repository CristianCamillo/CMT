<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title>CMT - Login</title>
		<link rel="stylesheet" type="text/css" href="css/base.css">
		<link rel="stylesheet" type="text/css" href="css/button.css">
		<script src="js/fieldChecker.js"></script>
	</head>
	<body>
		<table>
			<tbody>
				<tr>
					<td><button class="homepageButton" onclick="location.href='homepage.jsp'"><b>CASTLE MOVIE THEATER</b></button></td>
				</tr>
			</tbody>
		</table>
		<hr>
		<form name="form" action="${pageContext.request.contextPath}/login" method="POST">
			<table style="width: 200px">
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