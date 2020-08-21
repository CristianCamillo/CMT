<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title>CMT - Login</title>
		<link rel="stylesheet" type="text/css" href="css/base.css">
		<link rel="stylesheet" type="text/css" href="css/button.css">
		<link rel="stylesheet" type="text/css" href="css/frame.css">
		<script src="js/loginChecker.js"></script>
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
		<form name="form" onsubmit="return checkLoginForm()" action="${pageContext.request.contextPath}/login" method="POST">
			<table>
				<tbody>
					<tr>														
						<td><input name="username" type="text" placeholder="Username" maxlength="20"></td>
					</tr>
					<tr>
						<td><input name="password" type="password" placeholder="Password" maxlength="20"></td>
					</tr>
					<tr>
						<td><button type="submit">Accedi</button></td>
					</tr>
				</tbody>
			</table>
		</form>
		
		<!-- ErrorFrame 
		<div id="errorFrame" class="frameContainer">
			<div class="frame">
				<label id="errorMessage"></label>
				<button style="border: auto" onclick="document.getElementById('dettagliFrame').style.display = 'none'">OK</button>
			</div>
		</div>-->
	</body>
</html>