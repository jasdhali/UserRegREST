<%@ page language="java"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body bgcolor="lightblue">
<span></span>
<div align="center">
<h4>Please use google POSTMAN to test the app.</h4>
<h4>Sample user registration app.Starts with some seed data.Survives server restart.</h4>
</div>

	<table width="100%" border="-1">
		<thead>
		</thead>
		<tr>
			<td width="25%">List Users</td>
			<td><a href="/UserRegREST/user/">List Users</a></td>
		</tr>
		<tr>
			<td width="25%">Single User</td>
			<td><a href="/UserRegREST/user/1">Show User{id=1}</a></td>
		</tr>
				<tr>
			<td width="25%">New User</td>
			<td>Please use a JSON client program like POST Man to execute this.
			Sample create user request. Request should be a POST.<b>
			{"userId":14,"username":"User No 14","email":"User No11@gmail.com"}</b></td>
		</tr>
	</table>
</body>
</html>