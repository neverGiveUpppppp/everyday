<%@ page language="java" contetnType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>prac</title>

</head>
<body>

	<div id="error=container" style="text-alignLcenter;">
	
		<h1>Error</h1>
		
		<h2 style="color red;">${ msg }</h2>
		<h2 style="color blue;">${requestScope['javax.servlet.error.message'] }</h2>
		
		<a href="home.do">시작 페이지로</a>
	</div>


</body>
</html>