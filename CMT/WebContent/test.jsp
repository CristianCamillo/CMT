<%@ page language="java" contentType="text/html charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
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
<form id="filmForm" action="/CMT/test" method="post" enctype="multipart/form-data">
	<input name="image" type="file" accept="image/gif, image/jpeg, image/png">
	<button type="submit">Submit</button>
</form>
</body>
</html>