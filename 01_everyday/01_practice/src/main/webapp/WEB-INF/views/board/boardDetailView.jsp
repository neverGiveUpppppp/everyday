<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>prac</title>

<style type="text/css">
	#boardDetailTable{ width:800px; margin:auto; border-collapse:collapse; border-left:hidden; border-right:hidden;}
	#boardDetailTable tr td{ padding: 5px;}
	.replyTable{margin: auto; width:500px;}
</style>

</head>
<body>
	<c:import url="../common/menubar.jsp"/>
	
	<h1 align="center">${board.boardId }글의 상세보기</h1>
	<form action="bupView.bo" method="post">
		<table border="1" id="boardDetailTable">
			<tr>
				<th>번호</th>
				<td>
					${board.boardId }
					<input type="hidden" value="${ board.boardId }" name="boardId">
					<input type="hidden" value="${ page }" name="page">
				</td>
			</tr>
			<tr>
				<th>제목</th>
				<td>
					${ board.boardTitle }
					<input type="hidden" name="boardTitle" value="${ board.boardTitle }">
				</td>
			</tr>
			<tr>
				<th>작성자</th>
				<td>
					${ board.nickName }
					<input type="hidden" name="nickName" value="${ board.nickName }">
				</td>
			</tr>
			<tr>
				<th>작성날짜</th>
				<td>
					${ board.boardCreateDate }
				</td>
			</tr>

				<!-- 작성자
				작성날짜
				내용
				첨부파일 -->
		</table>
	</form>



</body>
</html>